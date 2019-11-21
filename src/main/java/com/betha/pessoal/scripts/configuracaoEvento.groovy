package com.betha.pessoal.scripts

interface ConfiguraCaoEvento {
    public int CODIGO_EVENTO = 89
    public Enum TIPO_EVENTO = com.betha.pessoal.scripts.padrao.enums.TipoEvento.VENCIMENTO
    public Enum UNIDADE_EVENTO = com.betha.pessoal.scripts.padrao.enums.UnidadeEvento.AUTOMATICO
    public BigDecimal TAXA_EVENTO = 8
}

//somente para passar evento na média
public interface MediasVantagensInfos {
    public static final String CODIGO = 107
}
