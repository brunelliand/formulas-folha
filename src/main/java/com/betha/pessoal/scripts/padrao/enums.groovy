package com.betha.pessoal.scripts.padrao


class enums {

    enum TipoPrevidencia {
        FUNDO_FINANCEIRO,
        PREVIDENCIA_PROPRIA,
        PREVIDENCIA_ESTADUAL,
        ASSISTENCIA_MUNICIPAL
    }

    enum ClassificacaoEvento {

        /**
         * 1, "1/12 aviso prévio indenizado"
         */
        AVPREINDUMDOZE,
        /**
         * 2, "13º salário adiantado"
         */
        ADI13SAL,
        /**
         * 3, "13º salário aviso prévio indenizado"
         */
        AVPREIND13SAL,
        /**
         * 4, "13º salário integral"
         */
        INTEG13SAL,
        /**
         * 5, "Abatimento para RPPS 13º salário"
         */
        RPPSABAT13,
        /**
         * 6, "Abatimento para RPPS"
         */
        RPPSABAT,
        /**
         * 7, "Abatimento salário maternidade 13º salário"
         */
        ABATSALM13,
        /**
         * 8, "Abatimento salário maternidade"
         */
        ABATSALM,
        /**
         * 9, "Adiantamento de férias"
         */
        ADIFERIAS,
        /**
         * 10, "Adiantamento salarial não descontado"
         */
        ADISALNDESC,
        /**
         * 11, "Auxílio natalidade"
         */
        AUXNAT,
        /**
         * 12, "Aviso prévio descontado"
         */
        AVPREDESC,
        /**
         * 13, "Aviso prévio indenizado"
         */
        AVPREIND,
        /**
         * 14, "Base abatimento INSS - duplo vínculo"
         */
        BASABATINSSDV,
        /**
         * 15, "Base INSS - duplo vínculo"
         */
        BASINSSDV,
        /**
         * 16, "Base INSS 13º salário - duplo vínculo"
         */
        BASINSSDV13SAL,
        /**
         * 17, "Base INSS outras empresas 13º salário"
         */
        BASINSSOUTEMP13SAL,
        /**
         * 18, "Base INSS outras empresas"
         */
        BASINSSOUTEMP,
        /**
         * 19, "Base IRRF - duplo vínculo"
         */
        BASIRRFDV,
        /**
         * 20, "Base IRRF 13º salário - duplo vínculo"
         */
        BASIRRFDV13SAL,
        /**
         * 21, "Base IRRF de férias anteriores"
         */
        BASIRRFFERIASANT,
        /**
         * 22, "Base IRRF férias resc. - duplo vínculo"
         */
        BASIRRFFERIASRESCDV,
        /**
         * 23, "Base IRRF outras empresas 13º salário"
         */
        BASIRRFOUTEMP13SAL,
        /**
         * 24, "Base IRRF outras empresas"
         */
        BASIRRFOUTEMP,
        /**
         * 25, "Desc. horas aux. maternidade 13º salário"
         */
        DESCHAUXMAT13SAL,
        /**
         * 26, "Desc. horas aux. maternidade"
         */
        DESCHAUXMAT,
        /**
         * 27, "Desconto 13º salário adiantado"
         */
        DESC13SALADI,
        /**
         * 28, "Desconto adiantamento salarial"
         */
        DESCADISAL,
        /**
         * 29, "Desconto de 13º salário devido reintegração"
         */
        DESC13SALDEVREINTG,
        /**
         * 30, "Desconto por dependente"
         */
        DESCDEP,
        /**
         * 31, "Devolução de INSS"
         */
        DEVINSS,
        /**
         * 32, "Devolução de IRRF"
         */
        DEVIRRF,
        /**
         * 33, "Devolução FGTS 13º salário"
         */
        DEVFGTS,
        /**
         * 34, "Empréstimos"
         */
        EMPRESTIMO,
        /**
         * 35, "Faltas DSR"
         */
        FALTADSR,
        /**
         * 36, "Faltas"
         */
        FALTAS,
        /**
         * 37, "FGTS 13º salário"
         */
        FGTS13SAL,
        /**
         * 38, "FGTS aviso prévio "
         */
        FGTSAVPRE,
        /**
         * 39, "FGTS mês anterior"
         */
        FGTSMESANT,
        /**
         * 40, "FGTS sobre férias"
         */
        FGTSSOBFERIAS,
        /**
         * 41, "FGTS"
         */
        FGTS,
        /**
         * 42, "Fundo assistência 13º salário"
         */
        FUNDASS13SAL,
        /**
         * 43, "Fundo assistência"
         */
        FUNDASS,
        /**
         * 44, "Fundo financeiro 13º salário"
         */
        FUNDFIN13SAL,
        /**
         * 45, "Fundo financeiro"
         */
        FUNDFIN,
        /**
         * 46, "Fundo previdência 13º salário"
         */
        FUNDPREV13SAL,
        /**
         * 47, "Fundo previdência"
         */
        FUNDPREV,
        /**
         * 48, "Horas aux. maternidade não pagas"
         */
        HAUXMATNPAG,
        /**
         * 49, "INSS 13º salário"
         */
        INSS13SAL,
        /**
         * 50, "INSS sobre horas aux. matern. não pagas"
         */
        INSSSOBHAUXMATNPAG,
        /**
         * 51, "INSS"
         */
        INSS,
        /**
         * 52, "IRRF  13º salário"
         */
        IRRF13SAL,
        /**
         * 53, "IRRF férias rescisão"
         */
        IRRFFERIASRESC,
        /**
         * 54, "IRRF"
         */
        IRRF,
        /**
         * 55, "ISS - Imposto Sobre Serviço"
         */
        ISS,
        /**
         * 56, "Multa do FGTS"
         */
        MULTAFGTS,
        /**
         * 57, "Nenhuma"
         */
        NENHUMA,
        /**
         * 58, "Parc. isenta IRRF moléstia grave férias adiant"
         */
        PARCISENIRRFMOLGRVFERIASADI,
        /**
         * 59, "Parc. isenta IRRF moléstia grave férias resc."
         */
        PARCISENIRRFMOLGRVFERIASRESC,
        /**
         * 60, "Parcela isenta fundo financeiro 13º salário"
         */
        PARCISENFUNDFIN13SAL,
        /**
         * 61, "Parcela isenta fundo financeiro"
         */
        PARCISENFUNDFIN,
        /**
         * 62, "Parcela isenta fundo previdência 13º salário"
         */
        PARCISENFUNDPREV13SAL,
        /**
         * 63, "Parcela isenta fundo previdência"
         */
        PARCISENFUNDPREV,
        /**
         * 64, "Parcela isenta IRRF 13º salário"
         */
        PARCISENIRRF13SAL,
        /**
         * 65, "Parcela isenta IRRF moléstia grave 13º salário"
         */
        PARCISENIRRFMOLGRV13SAL,
        /**
         * 66, "Parcela isenta IRRF moléstia grave férias"
         */
        PARCISENIRRFMOLGRVFERIAS,
        /**
         * 67, "Parcela isenta IRRF moléstia grave"
         */
        PARCISENIRRFMOLGRV,
        /**
         * 68, "Parcela isenta IRRF sobre férias resc."
         */
        PARCISENIRRFSOBFERIASRESC,
        /**
         * 69, "Parcela isenta IRRF"
         */
        PARCISENIRRF,
        /**
         * 70, "Pensão judicial - Desconto"
         */
        PENSJUDDESC,
        /**
         * 71, "Plano de saúde"
         */
        PLANOSAUDE,
        /**
         * 72, "Prev. estadual 13º salário"
         */
        PREVESTDECSAL,
        /**
         * 73, "Prev. estadual"
         */
        PREVEST,
        /**
         * 74, "Procedimentos médicos "
         */
        PROCEDMED,
        /**
         * 75, "Prontidão"
         */
        PONTIDAO,
        /**
         * 76, "Prorrog. do aux. maternidade - Lei 11770/2008"
         */
        PRORROGAUXMAT11770,
        /**
         * 77, "Redutor base INSS transportador autônomo"
         */
        REDBASINSSTRANSPAUT,
        /**
         * 78, "Redutor base IRRF transportador autônomo"
         */
        REDBASIRRFTRANSPAUT,
        /**
         * 79, "Salário família aposentado"
         */
        SALFAMAPOS,
        /**
         * 80, "Salário família celetista"
         */
        SALFAMCEL,
        /**
         * 81, "Salário família especial"
         */
        SALFAMESP,
        /**
         * 82, "Salário família estatutário"
         */
        SALFAMEST,
        /**
         * 83, "Salário família pago a maior"
         */
        SALFAMPAGMAIOR,
        /**
         * 84, "SENAT"
         */
        SENAT,
        /**
         * 85, "SEST"
         */
        SEST,
        /**
         * 86, "Sobreaviso"
         */
        SOBREAVISO,
        /**
         * 87, "Vale transporte - custo empregador"
         */
        VALETRANSP,
        /**
         * 88, "Horas férias"
         */
        HORASFERIAS,
        /**
         * 89, "Férias adiantadas"
         */
        FERIASADI,
        /**
         * 90, "Horas férias coletivas"
         */
        HORASFERIASCOL,
        /**
         * 91, "Horas férias aviso prévio indenizado"
         */
        HORASFERIASAVPREVIND,
        /**
         * 92, "1/3 de férias"
         */
        FERIASTERCO,
        /**
         * 93, "1/3 de abono"
         */
        ABONOTERCO,
        /**
         * 94, "1/3 de férias em dobro"
         */
        FERDOBROTERCO,
        /**
         * 95, "1/3 de férias indenizadas"
         */
        FERINDENTERCO,
        /**
         * 96, "1/3 de férias proporcionais"
         */
        FERPROPTERCO,
        /**
         * 97, "1/3 de férias vencidas"
         */
        FERVENCTERCO,
        /**
         * 98, "Base negativa INSS - Mensal"
         */
        BASEINSSNEG
    }

    /**
     * Tipos de aviso prévio
     */
    enum TipoAvisoPrevio {
        TRABALHADO_REDUCAO_2_HORAS_DIARIAS,
        TRABALHADO_REDUCAO_7_DIAS_CORRIDOS,
        INDENIZADO,
        TRABALHADO,
        DESCONTADO,
        DISPENSADO
    }

    /**
     * Tipos de processamento da folha de pagamento
     */
    enum TipoProcessamento {
        FERIAS,
        MENSAL,
        DECIMO_TERCEIRO_SALARIO,
        RESCISAO
    }

    /**
     * Subtipos de processamentos da folha de pagamento
     */
    enum SubTipoProcessamento {
        ADIANTAMENTO,
        INTEGRAL,
        COMPLEMENTAR
    }

    /**
     * Tipo de evento
     */
    enum TipoEvento {
        /**
         * Vencimento, provento ou pensão
         */
        VENCIMENTO,
        /**
         * Desconto
         */
        DESCONTO,
        /**
         * Informativo(+)
         */
        INFORMATIVO_MAIS,
        /**
         * Informativo(-)
         */
        INFORMATIVO_MENOS
    }

/**
 * Grau de escolaridade
 */
    enum GrauEscolaridade {
        ENSINO_MEDIO
    }


/**
 * Unidade do evento
 */
    enum UnidadeEvento {
        /**
         * 1, "Automático", 4
         */
        AUTOMATICO,
        /**
         * 2, "Avos", 4
         */
        AVOS,
        /**
         * 3, "Dias", 2
         */
        DIAS,
        /**
         * 4, "Horas", 2
         */
        HORAS,
        /**
         * 5, "Percentual", 4
         */
        PERCENTUAL,
        /**
         * 6, "Quantidade", 4
         */
        QUANTIDADE,
        /**
         * 7, "Valor", 2
         */
        VALOR
    }

/**
 * Forma de pagamento
 */
    enum FormaPagamento {
        /**
         * Dinheiro
         */
        DINHEIRO,
        /**
         * Cheque
         */
        CHEQUE,
        /**
         * Crédito em conta
         */
        CREDITO_EM_CONTA
    }

/**
 * Grau de deficiência
 */
    enum GrauDeficiencia {
        /**
         * Parcial
         */
        PARCIAL,
        /**
         * Total
         */
        TOTAL,
        /**
         * Não inválido
         */
        NAOINVALIDO
    }

/**
 * Mês de contribuição sindical
 */
    enum MesContribuicaoSindical {
        JANEIRO, FEVEREIRO, MARCO, ABRIL, MAIO, JUNHO, JULHO, AGOSTO, SETEMBRO, OUTUBRO, NOVEMBRO, DEZEMBRO;
    }

/**
 * Origem do rendimento
 */
    enum OrigemRendimento {
        CARGO, CARGO_COMISSIONADO;
    }

/**
 * Sexo
 */
    enum Sexo {
        MASCULINO, FEMININO;
    }

/**
 * Tipo de admissão
 */
    enum TipoAdmissao {
        ADMISSAO,
        TRANSFERENCIA,
        SUBSTITUICAO;
    }


    /**
     * Tipo de vinculo
     */
    enum TipoVinculo {
        CELETISTA,
        ESTATURARIO,
        OUTROS
    }

/**
 * Tipo de matrícula
 */
    enum TipoMatricula {
        FUNCIONARIO,
        ESTAGIARIO,
        AUTONOMO,
        APOSENTADO,
        PENSIONISTA
    }

/**
 * Responsabilidade do pagamento
 */
    enum ResponsabilidadePagamento {
        PAGAMENTO_EXCLUSIVAMENTE_PELO_CEDENTE_ORIGEM,
        PAGAMENTO_EXCLUSIVAMENTE_PELO_CESSIONARIO_DESTINO,
        PAGAMENTO_PELO_CEDENTE_ORIGEM_E_PELO_CESSIONARIO_DESTINO,
        PAGAMENTO_PELO_CEDENTE_ORIGEM_COM_RESSARCIMENTO_PELO_CESSIONARIO_DESTINO;
    }

/**
 * Estado civil
 */
    enum EstadoCivil {
        /**
         * Solteiro
         */
        SOLTEIRO,
        /**
         * Casado
         */
        CASADO,
        /**
         * Divorciado
         */
        DIVORCIADO,
        /**
         * Viúvo
         */
        VIUVO,
        /**
         * Separado consensualmente
         */
        SEPARADO_CONSENSUALMENTE,
        /**
         * Separado judicialmente
         */
        SEPARADO_JUDICIALMENTE,
        /**
         * União estável
         */
        UNIAO_ESTAVEL;
    }


/**
 * Grau de dependência
 */
    enum GrauDependencia {
        /**
         * Filho / Filha
         */
        FILHO,
        /**
         * Conjuge
         */
        CONJUGE,
        /**
         * Pai ou Mãe
         */
        PAI_MAE,
        /**
         * Irmão / Irmã
         */
        IRMAO,
        /**
         * Avô / Avó
         */
        AVO(false),
        /**
         * Neto / Neta
         */
        NETO,
        /**
         * Enteado / Enteada
         */
        ENTEADO,
        /**
         * Menor tutelado
         */
        MENOR_TUTELADO,
        /**
         * Companheiro
         */
        COMPANHEIRO,
        /**
         * Bisneto / Bisneta
         */
        BISNETO,
        /**
         * Bisavô / Bisavó
         */
        BISAVO,
    }


/**
 * Tipo de onus referente a cessão
 */
    enum TipoOnus {
        /**
         * Cedente
         */
        CEDENTE,
        /**
         * Cessionário
         */
        CESSIONARIO;
    }

/**
 * Unidade de tempo
 */
    enum UnidadeTempo {
        NANOS, MICROS, MILLIS, SEGUNDOS, MINUTOS, HORAS, DIAS, SEMANAS, MESES, ANOS
    }

/**
 * Tipo de regime
 */
    enum TipoRegime {
        COMPETENCIA, CAIXA;
    }

/**
 * Tipo de valor
 */
    enum TipoValor {
        CALCULADO, REFERENCIA;
    }

/**
 * Encargos sociaos Fpas do cadastro de manutenção da entidade
 */
    enum EncargosSociaisFpas {
        EMPRESA_SERVICO_PROC_DADOS,
        SINDICATO_ASSOCIACAO_PROF_EMPREGADO,
        EMPRESA_COMUNICACAO,
        ESTABELECIMENTO_ENSINO,
        ORGAO_PODER_PUBLICO,
        CARTORIO,
        EMPRESA_TRANSP_RODOVIARIO,
        TOMADOR_SERVIDO_TRANSP_AUTONOMO,
        ENTIDADE_BENEFICENTE,
        ASSOCIACAO_DESPORTIVA,
        EMPRESA_TRABALHO_TEMPORARIO,
        TRATAMENTO_DISP_RESIDUOS,
        BANCOS_INST_FINANCEIRAS;
    }


/**
 * Tipo de regime previdênciário
 */
    enum TipoRegimePrevidenciario {
        CLT, REGIME_PROPRIO, OUTROS;
    }

/**
 * Unidade de pagamento da matrícula
 */
    enum UnidadePagamento {
        MENSALISTA, QUINZENALISTA, SEMANALISTA, DIARISTA, HORISTA, TAREFEIRO, OUTROS;
    }

/**
 * Unidade de cálculo (parâmetros da entidade)
 */
    enum UnidadeCalculo {
        DIAS, HORAS;
    }

/**
 * Tipos de despesa de plano de saúde
 */
    enum TipoDespesaPlanoSaude {
        MENSALIDADE, ADESAO, PROCEDIMENTOS;

    }


/**
 * Tipos de aplicação de desconto de pensão alimentícia
 */
    enum AplicacaoDesconto {
        /**
         * Percentual
         */
        PERCENTUAL,
        /**
         * Valor
         */
        VALOR
    }

/**
 * Classificações de tipos de afastamento conforme cadastro de tipos de afastamentos
 */
    enum ClassificacaoTipoAfastamento {
        ABORTO_NAO_CRIMINOSO,
        ACIDENTE_DE_TRABALHO_EMPREGADOR,
        ACIDENTE_DE_TRABALHO_PREVIDENCIA,
        ACIDENTE_DE_TRAJETO_EMPREGADOR,
        ACIDENTE_DE_TRAJETO_PREVIDENCIA,
        DOENCA_DO_TRABALHO_EMPREGADOR,
        DOENCA_DO_TRABALHO_PREVIDENCIA,
        AUXILIO_DOENCA_EMPREGADOR,
        AUXILIO_DOENCA_PREVIDENCIA,
        ACOMPANHAR_MEMBRO_DA_FAMILIA_ENFERMO,
        ADOCAO_GUARDA_JUDICIAL_DE_CRIANCA,
        APOSENTADORIA_POR_INVALIDEZ,
        CANDIDATO_A_CARGO_ELETIVO,
        CARCERE,
        CEDENCIA,
        FALTA,
        FERIAS,
        LICENCA_COM_VENCIMENTOS,
        LICENCA_SEM_VENCIMENTOS,
        LICENCA_MATERNIDADE,
        MANDATO_ELEITORAL_COM_REMUNERACAO,
        MANDATO_ELEITORAL_SEM_REMUNERACAO,
        MANDATO_SINDICAL,
        MULHER_VITIMA_DE_VIOLENCIA_LEI_MARIA_DA_PENHA,
        PRORROGACAO_DA_LICENCA_MATERNIDADE,
        PRORROGACAO_DA_LICENCA_MATERNIDADE_11_770,
        SERVICO_MILITAR,
        SERVIDOR_PUBLICO_EM_DISPONIBILIDADE,
        SUSPENSAO_DISCIPLINAR_ART474_CLT,
        SUSPENSAO_DO_CONTRATO_ART476_CLT,
        APOSENTADO,
        DEMITIDO;
    }

/**
 * Situação do período aquisitivo de décimo terceiro salário
 */
    enum SituacaoPeriodoAquisitivoDecimoTerceiro {
        /**
         * Atrasado
         */
        ATRASADO,

        /**
         * Em andamento
         */
        EM_ANDAMENTO,

        /**
         * Perda de direito
         */
        PERDA_DIREITO,

        /**
         * Quitado
         */
        QUITADO,

        /**
         * Quitado parcialmente
         */
        QUITADO_PARCIALMENTE,

        /**
         * Anulado
         */
        ANULADO
    }

/**
 * Situações de período aquisitivos de férias
 */
    enum SituacaoPeriodoAquisitivo {
        /**
         * Em andamento
         * @return
         */
        EM_ANDAMENTO,

        /**
         * Quitado
         */
        QUITADO,

        /**
         * Cancelado
         */
        CANCELADO,

        /**
         * Adquirido
         */
        ADQUIRIDO,

        /**
         * Anulado
         */
        ANULADO,
    }

/**
 * Enum de ocorrências sefip
 */
    enum OcorrenciaSefip {
        NUNCA_EXPOSTO_AGENTES_NOCIVOS,
        EXPOSTO_ALGUMA_VEZ,
        EXPOSTO_APOSENTADORIA_15_ANOS,
        EXPOSTO_APOSENTADORIA_20_ANOS,
        EXPOSTO_APOSENTADORIA_25_ANOS,
    }

    enum TipoSalarioFamilia {
        ESTATUTARIO, APOSENTADO, ESPECIAL, CELETISTA;
    }

    enum AbreviacaoBases {
        PARCINSENIRRF,
        PARCINSENIRRF13,
        PAGAPROP,
        SALBASE,
        HORAEXTRA,
        PERIC,
        SIND,
        FGTS,
        FGTS13,
        IRRF,
        IRRF13,
        IRRFFERRESC,
        INSS,
        INSS13,
        INSSFER,
        PREVEST,
        PREVEST13,
        FUNDASS,
        FUNDASS13,
        FUNDOPREV,
        FUNDPREV13,
        OUTRASBASES,
        FGTSAVISO,
        ABATIRRF,
        ABATIRRF13,
        DESCIRRF,
        DESCIRRF13,
        DESCIRRFERES,
        EXCEINSS,
        EXCEINSS13,
        ABATINSS,
        DESCTERFER,
        SALAFAM,
        INSSOUTRA,
        INSSOUTRA13,
        IRRFOUTRA,
        IRRFOUTRA13,
        IRRFFER,
        CONTSIND,
        MEDIAUXMAT,
        DESC13REINT,
        COMPHORAMES,
        TERFERVENRES,
        FUNDFIN,
        FUNDFIN13,
        MEDAUXMATPR,
        IN13SADI,
        FUPR13SAPR,
        FUPR13SAAJPR,
        FUPR13SAESPR,
        FUPR13SADI,
        PRES13SAPR,
        PRES13SAAJPR,
        PRES13SAESPR,
        PRES13SADI,
        FUAS13SAPR,
        FUAS13SAAJPR,
        FUAS13SAESPR,
        FUAS13SADI,
        FG13SAPR,
        FG13SAAJPR,
        FG13SAESPR,
        INFEPR,
        INFEAJPR,
        INFEESPR,
        INFEDI,
        FUPRFEPR,
        FUPRFEAJPR,
        FUPRFEESPR,
        FUPRFEDI,
        PRESFEPR,
        PRESFEAJPR,
        PRESFEESPR,
        PRESFEDI,
        FUASFEPR,
        FUASFEAJPR,
        FUASFEESPR,
        FUASFEDI,
        FGFEPR,
        FGFEAJPR,
        FGFEESPR,
        PRBAAUMEHOFE,
        PRBAAUMEHO13,
        PRBAAUDI13SA,
        PRBAAUDIFE,
        DEVIRRF,
        DEVINSS,
    }


}


