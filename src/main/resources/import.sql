-- ============================================
-- POPULAR TABELA CLIENTE (30 registros)
-- ============================================

INSERT INTO cliente (perfil, pontuacao, descricao) VALUES
('Conservador', 25, 'Perfil focado em liquidez e segurança.'),
('Conservador', 32, 'Investidor com baixa tolerância a risco.'),
('Conservador', 28, 'Busca investimentos estáveis.'),
('Conservador', 30, 'Prefere previsibilidade financeira.'),
('Conservador', 22, 'Perfil de baixo risco.'),
('Moderado',    55, 'Equilibra risco e retorno.'),
('Moderado',    60, 'Flexível entre segurança e rentabilidade.'),
('Moderado',    52, 'Busca diversificação balanceada.'),
('Moderado',    65, 'Tolerância média ao risco.'),
('Moderado',    58, 'Busca ganhos moderados.'),
('Agressivo',   78, 'Busca alto retorno com maior risco.'),
('Agressivo',   82, 'Tolerância alta ao risco.'),
('Agressivo',   90, 'Focado em rentabilidade elevada.'),
('Agressivo',   85, 'Investe em produtos arrojados.'),
('Agressivo',   88, 'Perfil de risco elevado.'),
('Conservador', 27, 'Preferência por liquidez diária.'),
('Moderado',    57, 'Busca equilíbrio financeiro.'),
('Agressivo',   80, 'Voltado a alta performance.'),
('Conservador', 35, 'Evita volatilidade.'),
('Moderado',    61, 'Busca ganhos acima da média.'),
('Agressivo',   92, 'Aceita grandes oscilações.'),
('Conservador', 29, 'Risco mínimo.'),
('Moderado',    54, 'Mix de segurança e retorno.'),
('Agressivo',   87, 'Apetite elevado a risco.'),
('Conservador', 26, 'Preferência por renda fixa.'),
('Moderado',    63, 'Moderado com foco em crescimento.'),
('Agressivo',   95, 'Muito agressivo.'),
('Moderado',    59, 'Busca desempenho consistente.'),
('Conservador', 34, 'Estratégia segura.'),
('Agressivo',   93, 'Muito tolerante ao risco.');



-- ============================================
-- POPULAR TABELA PRODUTO (30 registros)
-- ============================================

INSERT INTO produto (nome, tipo, rentabilidade, risco) VALUES
('CDB Caixa Liquidez Diária',           'CDB',     0.10, 'Baixo'),
('CDB Caixa 2026',                      'CDB',     0.12, 'Baixo'),
('CDB Banco XP 2027',                   'CDB',     0.13, 'Baixo'),
('LCA Agrícola Prime',                  'LCA',     0.11, 'Baixo'),
('LCI Imobiliária Caixa',               'LCI',     0.10, 'Baixo'),
('Tesouro Selic 2029',                  'Tesouro', 0.09, 'Baixo'),
('Tesouro IPCA+ 2035',                  'Tesouro', 0.14, 'Médio'),
('Tesouro Prefixado 2028',              'Tesouro', 0.12, 'Médio'),
('Fundo Renda Fixa Caixa',              'Fundo',   0.11, 'Baixo'),
('Fundo Multimercado Alpha',            'Fundo',   0.16, 'Médio'),
('Fundo Ações Brasil',                  'Fundo',   0.22, 'Alto'),
('ETF BOVA11',                          'ETF',     0.18, 'Alto'),
('ETF SMALL11',                         'ETF',     0.20, 'Alto'),
('CRI Imobiliário Premium',             'CRI',     0.15, 'Médio'),
('CRA Agronegócio XP',                  'CRA',     0.17, 'Médio'),
('Debênture Incentivada Vale',          'Debênture',0.13,'Médio'),
('Debênture Energias Verdes',           'Debênture',0.14,'Médio'),
('FIIs - HGLG11',                       'FII',     0.12, 'Médio'),
('FIIs - KNRI11',                       'FII',     0.11, 'Médio'),
('Fundo Criptomoedas BTC',              'Fundo',   0.35, 'Alto'),
('Fundo Tech Innovation',               'Fundo',   0.25, 'Alto'),
('Fundo ESG Sustentável',               'Fundo',   0.14, 'Médio'),
('Fundo Cambial Dólar',                 'Fundo',   0.13, 'Médio'),
('Tesouro IPCA+ 2045',                  'Tesouro', 0.15, 'Médio'),
('CDB Médio Prazo Banco BMG',           'CDB',     0.14, 'Baixo'),
('LCA Agronegócio XPTO',                'LCA',     0.12, 'Baixo'),
('Fundo Private Agressivo',             'Fundo',   0.28, 'Alto'),
('ETF NASD11 - Nasdaq Index',           'ETF',     0.23, 'Alto'),
('ETF Ouro GOLD11',                     'ETF',     0.12, 'Médio'),
('CDB Digital Banco Inter',             'CDB',     0.13, 'Baixo');
