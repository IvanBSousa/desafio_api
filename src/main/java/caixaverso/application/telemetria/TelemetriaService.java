package caixaverso.application.telemetria;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

@ApplicationScoped
public class TelemetriaService {

    private final Map<String, LongAdder> callCounts = new ConcurrentHashMap<>();
    private final Map<String, LongAdder> totalExecutionTimes = new ConcurrentHashMap<>();

    public void recordExecution(String serviceName, long executionTime) {
        callCounts.computeIfAbsent(serviceName, k -> new LongAdder()).increment();
        totalExecutionTimes.computeIfAbsent(serviceName, k -> new LongAdder()).add(executionTime);
    }

    public Map<String, TelemetriaDTO> getTelemetriaDTO() {
        Map<String, TelemetriaDTO> results = new ConcurrentHashMap<>();
        callCounts.forEach((serviceName, count) -> {
            long totalTime = totalExecutionTimes.getOrDefault(serviceName, new LongAdder()).sum();
            long callCount = count.sum();
            if (callCount > 0) {
                long averageTime = totalTime / callCount;
                results.put(serviceName, new TelemetriaDTO(serviceName, callCount, averageTime));
            }
        });
        return results;
    }
}