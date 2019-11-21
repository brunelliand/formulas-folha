package com.betha.pessoal.scripts.padrao

import com.betha.pessoal.scripts.utils
import com.betha.pessoal.scripts.padrao.Calculo.calculo;
import static com.betha.pessoal.scripts.padrao.enums.*;

class PeriodosAquisitivosDecimoTerceiro {
    Map data = utils.getFile("periodoaquisitivodecimoterceiro");
    Object periodo = data.periodos.find{it.anoExercicio == calculo.anoDecimoTerceiro}

    private static ArrayList getBuscaPeriodosAquisitivos(){
        PeriodosAquisitivosDecimoTerceiro object = new PeriodosAquisitivosDecimoTerceiro();
        ArrayList dados = new ArrayList(object.data.periodos);
        return dados;
    }
    static ArrayList buscaPeriodosAquisitivos(){
        return getBuscaPeriodosAquisitivos()
    }

    static ArrayList buscaPeriodosAquisitivosBySituacao(Enum situacao){
        PeriodosAquisitivosDecimoTerceiro object = new PeriodosAquisitivosDecimoTerceiro();
        return object.data.periodos.findAll{ utils.getEnumValue(SituacaoPeriodoAquisitivoDecimoTerceiro, it.situacao).equals(situacao)  }

    }

    class periodoAquisitivoDecimoTerceiro {

        private static Date getDataInicial() {
            if (!calculo.tipoProcessamento.equals(TipoProcessamento.DECIMO_TERCEIRO_SALARIO)) return null
            PeriodosAquisitivosDecimoTerceiro object = new PeriodosAquisitivosDecimoTerceiro()
            return object.periodo.dataInicial
        }
        static Date dataInicial = getDataInicial();

        private static Date getDataFinal() {
            if (!calculo.tipoProcessamento.equals(TipoProcessamento.DECIMO_TERCEIRO_SALARIO)) return null
            PeriodosAquisitivosDecimoTerceiro object = new PeriodosAquisitivosDecimoTerceiro()
            return object.periodo.dataFinal
        }
        static Date dataFinal = getDataFinal();

        static int getAnoExercicio() {
            if (!calculo.tipoProcessamento.equals(TipoProcessamento.DECIMO_TERCEIRO_SALARIO)) return null
            PeriodosAquisitivosDecimoTerceiro object = new PeriodosAquisitivosDecimoTerceiro()
            return object.periodo.anoExercicio
        }
        static int anoExercicio = getAnoExercicio();

        static Enum getSituacao() {
            if (!calculo.tipoProcessamento.equals(TipoProcessamento.DECIMO_TERCEIRO_SALARIO)) return null
            PeriodosAquisitivosDecimoTerceiro object = new PeriodosAquisitivosDecimoTerceiro()
            String value = object.periodo.situacao
            SituacaoPeriodoAquisitivoDecimoTerceiro convertedEnumValue = value as SituacaoPeriodoAquisitivoDecimoTerceiro
            return convertedEnumValue
        }

        static situacao = getSituacao();

        static long getAvosAdquiridos() {
            if (!calculo.tipoProcessamento.equals(TipoProcessamento.DECIMO_TERCEIRO_SALARIO)) return null
            PeriodosAquisitivosDecimoTerceiro object = new PeriodosAquisitivosDecimoTerceiro()
            return object.periodo.avosAdquiridos
        }
        static long avosAdquiridos = getAvosAdquiridos();

        static long getAvosAdquiridosFgts() {
            if (!calculo.tipoProcessamento.equals(TipoProcessamento.DECIMO_TERCEIRO_SALARIO)) return null
            PeriodosAquisitivosDecimoTerceiro object = new PeriodosAquisitivosDecimoTerceiro()
            return object.periodo.avosAdquiridosFgts
        }
        static long avosAdquiridosFgts = getAvosAdquiridosFgts();

        static long getAvosPerdidos() {
            if (!calculo.tipoProcessamento.equals(TipoProcessamento.DECIMO_TERCEIRO_SALARIO)) return null
            PeriodosAquisitivosDecimoTerceiro object = new PeriodosAquisitivosDecimoTerceiro()
            return object.periodo.avosPerdidos
        }
        static long avosPerdidos = getAvosPerdidos();

        static long getAvosDireito() {
            if (!calculo.tipoProcessamento.equals(TipoProcessamento.DECIMO_TERCEIRO_SALARIO)) return null
            PeriodosAquisitivosDecimoTerceiro object = new PeriodosAquisitivosDecimoTerceiro()
            return object.periodo.avosDireito
        }
        static long avosDireito = getAvosDireito();

        static BigDecimal getTotalMovimentacoes(){
            if (!calculo.tipoProcessamento.equals(TipoProcessamento.DECIMO_TERCEIRO_SALARIO)) return null
            PeriodosAquisitivosDecimoTerceiro object = new PeriodosAquisitivosDecimoTerceiro()
            return BigDecimal.valueOf(object.periodo.totalMovimentacoes)
        }

        static BigDecimal totalMovimentacoes = getTotalMovimentacoes()


//    movimentacoes
//    movimentacoesByMotivo(motivos)
//    totalMovimentacoes
//    totalMovimentacoesByMotivo(motivos)
//    configuracao


    }
}

