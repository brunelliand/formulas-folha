package com.betha.pessoal.scripts.padrao

import com.betha.pessoal.scripts.utils;
import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas
import static com.betha.pessoal.scripts.padrao.enums.*

class PeriodosAquisitivos {
    Object data = utils.getFile("periodoaquisitivo");
    ArrayList periodos = new ArrayList(data.periodos)
    //Object periodo = periodos.find { Datas.ano(it.dataFinal) == Datas.ano(Calculo.calculo.competencia) }
    Object periodo = periodos.sort { it.dataFinal }.getAt(0)
    Object concessao = data.concessao

    static void quitaPorRescisao(periodo, BigDecimal valorCalculado) {
        System.out.println("Valor ${valorCalculado} quitado para o período")
    }

    static void quitaPorRescisao(periodo, BigDecimal valorReferencia, BigDecimal valorCalculado) {
        System.out.println("Valor ${valorCalculado} quitado para o período")
    }

    static void quitaPorRescisao(periodo, BigDecimal valorReferencia, BigDecimal valorCalculado, Boolean compoeMediaVantagem) {
        System.out.println("Valor ${valorCalculado} quitado para o período")
    }

    static Object buscaProporcional() {
        PeriodosAquisitivos periodosAquisitivos = new PeriodosAquisitivos()
        return periodosAquisitivos.periodos.find {
            utils.getEnumValue(SituacaoPeriodoAquisitivo, it.situacao) == SituacaoPeriodoAquisitivo.EM_ANDAMENTO
        }
    }

    static ArrayList buscaVencidos() {
        PeriodosAquisitivos periodosAquisitivos = new PeriodosAquisitivos()
        return periodosAquisitivos.periodos.findAll {
            utils.getEnumValue(SituacaoPeriodoAquisitivo, it.situacao) == SituacaoPeriodoAquisitivo.ADQUIRIDO
        }
    }


    class periodoAquisitivo {
        static Enum getSituacao() {
            PeriodosAquisitivos periodoAquisitivo = new PeriodosAquisitivos()


            return utils.getEnumValue(SituacaoPeriodoAquisitivo, periodoAquisitivo.periodo.situacao)
        }

        static Enum situacao = getSituacao()

        static Date getDataInicial() {
            PeriodosAquisitivos periodoAquisitivo = new PeriodosAquisitivos()
            return periodoAquisitivo.periodo.dataInicial
        }

        static Date dataInicial = getDataInicial()

        static Date getDataFinal() {
            PeriodosAquisitivos periodoAquisitivo = new PeriodosAquisitivos()
            return periodoAquisitivo.periodo.dataFinal
        }

        static Date dataFinal = getDataFinal()

        static BigDecimal getFaltas() {
            PeriodosAquisitivos periodoAquisitivo = new PeriodosAquisitivos()
            return periodoAquisitivo.periodo.faltas
        }

        static BigDecimal faltas = getFaltas()

        static Boolean getPagouUmTercoIntegral() {
            PeriodosAquisitivos periodoAquisitivo = new PeriodosAquisitivos()
            return periodoAquisitivo.periodo.pagouUmTercoIntegral
        }

        static Boolean pagouUmTercoIntegral = getPagouUmTercoIntegral()

        static BigDecimal getSaldo() {
            PeriodosAquisitivos periodoAquisitivo = new PeriodosAquisitivos()
            return periodoAquisitivo.periodo.saldo
        }
        static BigDecimal saldo = getSaldo()


        class configuracaoFerias {

            static int getDiasParaAdquirirNoPeriodo() {
                PeriodosAquisitivos periodoAquisitivo = new PeriodosAquisitivos()
                return periodoAquisitivo.periodo.configuracaoFerias.diasParaAdquirirNoPeriodo
            }
            static int diasParaAdquirirNoPeriodo = getDiasParaAdquirirNoPeriodo()


            static int getMesesParaAquisicao() {
                PeriodosAquisitivos periodoAquisitivo = new PeriodosAquisitivos()
                return periodoAquisitivo.periodo.configuracaoFerias.mesesParaAquisicao
            }
            static int mesesParaAquisicao = getMesesParaAquisicao()

            static int getMesesParaConcessao() {
                PeriodosAquisitivos periodoAquisitivo = new PeriodosAquisitivos()
                return periodoAquisitivo.periodo.configuracaoFerias.mesesParaConcessao
            }
            static int mesesParaConcessao = getMesesParaConcessao()

            static int getDiasAbono() {
                PeriodosAquisitivos periodoAquisitivo = new PeriodosAquisitivos()
                return periodoAquisitivo.periodo.configuracaoFerias.diasAbono
            }
            static int diasAbono = getDiasAbono()
        }
    }

    class periodoConcessao {

        static Date getDataFimGozo() {
            PeriodosAquisitivos periodosAquisitivos = new PeriodosAquisitivos()
            return periodosAquisitivos.concessao.dataFimGozo
        }
        static Date dataFimGozo = getDataFimGozo()

        static Date getDataInicioGozo() {
            PeriodosAquisitivos periodosAquisitivos = new PeriodosAquisitivos()
            return periodosAquisitivos.concessao.dataInicioGozo
        }
        static Date dataInicioGozo = getDataInicioGozo()

        static Date getDataPagamento() {
            PeriodosAquisitivos periodosAquisitivos = new PeriodosAquisitivos()
            return periodosAquisitivos.concessao.dataPagamento
        }
        static Date dataPagamento = getDataPagamento()


        static BigDecimal getDiasAbono() {
            PeriodosAquisitivos periodosAquisitivos = new PeriodosAquisitivos()
            return BigDecimal.valueOf(periodosAquisitivos.concessao.diasAbono)
        }
        static BigDecimal diasAbono = getDiasAbono()

        static BigDecimal getDiasFalta() {
            PeriodosAquisitivos periodosAquisitivos = new PeriodosAquisitivos()
            return BigDecimal.valueOf(periodosAquisitivos.concessao.diasFalta)
        }
        static BigDecimal diasFalta = getDiasFalta()

        static BigDecimal getDiasGozo() {
            PeriodosAquisitivos periodosAquisitivos = new PeriodosAquisitivos()
            return BigDecimal.valueOf(periodosAquisitivos.concessao.diasGozo)
        }
        static BigDecimal diasGozo = getDiasGozo()


    }


}



