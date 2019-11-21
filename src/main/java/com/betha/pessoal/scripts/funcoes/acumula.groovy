package com.betha.pessoal.scripts.funcoes

import com.betha.pessoal.scripts.padrao.Eventos
import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas;
import com.betha.pessoal.scripts.padrao.Matricula.matricula;
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario;
import com.betha.pessoal.scripts.padrao.Calculo.calculo;
import static com.betha.pessoal.scripts.padrao.Calculo.evento
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir;
import static com.betha.pessoal.scripts.padrao.enums.*;

/**
 * Acumula a movimentação de um determinado evento
 */
class Acumula {
    public static BigDecimal getAcumula(int codigoEvento, Enum tipo, Date competenciaInicial, Date competenciaFinal, ArrayList tiposProcessamento) {
        /** Dentro deste bloco deverá esta o conteúdo do script, o mesmo do cadastro de funções para fórmulas **/

        def enumProc = [
                'MENSAL'               : [TipoProcessamento.MENSAL, SubTipoProcessamento.INTEGRAL],
                'MENSAL_ADIANTAMENTO'  : [TipoProcessamento.MENSAL, SubTipoProcessamento.ADIANTAMENTO],
                'MENSAL_COMPLEMENTAR'  : [TipoProcessamento.MENSAL, SubTipoProcessamento.COMPLEMENTAR],
                'FERIAS'               : [TipoProcessamento.FERIAS, SubTipoProcessamento.INTEGRAL],
                'DECIMO'               : [TipoProcessamento.DECIMO_TERCEIRO_SALARIO, SubTipoProcessamento.INTEGRAL],
                'DECIMO_ADIANTAMENTO'  : [TipoProcessamento.DECIMO_TERCEIRO_SALARIO, SubTipoProcessamento.ADIANTAMENTO],
                'DECIMO_COMPLEMENTAR'  : [TipoProcessamento.DECIMO_TERCEIRO_SALARIO, SubTipoProcessamento.COMPLEMENTAR],
                'RESCISAO'             : [TipoProcessamento.RESCISAO, SubTipoProcessamento.INTEGRAL],
                'RESCISAO_COMPLEMENTAR': [TipoProcessamento.RESCISAO, SubTipoProcessamento.COMPLEMENTAR]
        ]

        def inicio = competenciaInicial
        def fim = competenciaFinal

        if (Datas.dia(inicio) > 1) {
            inicio = Datas.data(Datas.ano(inicio), Datas.mes(inicio), 1)
        }

        if (Datas.dia(fim) > 1) {
            fim = Datas.data(Datas.ano(fim), Datas.mes(fim), 1)
        }

        def competencias = Datas.diferencaMeses(inicio, fim)

        def acumula = 0
        def valorCompetencia = 0;
        for (int i = 0; i <= competencias; i++) {
            def competencia = Datas.adicionaMeses(inicio, i)
            for (processamento in tiposProcessamento) {
                valorCompetencia = Eventos.valorCalculado(codigoEvento, tipo, *enumProc[processamento], competencia)
                acumula += valorCompetencia
            }
        }

        return acumula


        /** FINAL **/
    }
}
