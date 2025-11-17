package caixaverso.application.telemetria;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

@Interceptor
@Monitor(serviceName = "")
@Priority(Interceptor.Priority.APPLICATION)
public class Interceptador {

    private final TelemetriaService telemetryService;

    @Inject
    public Interceptador(TelemetriaService telemetryService) {
        this.telemetryService = telemetryService;
    }

    @AroundInvoke
    public Object monitor(InvocationContext context) throws Exception {
        long startTime = System.currentTimeMillis();
        try {
            return context.proceed();
        } finally {
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;

            Monitor monitoradoAnnotation = context.getMethod().getAnnotation(Monitor.class);
            if (monitoradoAnnotation != null) {
                String serviceName = monitoradoAnnotation.serviceName();
                telemetryService.recordExecution(serviceName, executionTime);
            }
        }
    }
}