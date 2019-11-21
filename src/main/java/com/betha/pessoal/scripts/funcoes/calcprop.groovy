package com.betha.pessoal.scripts.funcoes

import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas;
import com.betha.pessoal.scripts.padrao.Matricula.matricula;
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario;
import com.betha.pessoal.scripts.padrao.Calculo.calculo;
import static com.betha.pessoal.scripts.padrao.Calculo.evento;
import static com.betha.pessoal.scripts.padrao.enums.*;



class CalculoProporcional {
    public static BigDecimal getCalcProp(BigDecimal vlrCalc, BigDecimal proporcional) {
        /** Dentro deste bloco deverá esta o conteúdo do script, o mesmo do cadastro de funções para fórmulas **/
        def carga = 0;
        def diasmes = 30;
        def categ;

        def unidade = calculo.unidade
        def unidade_evento = evento.unidade

        if (matricula.tipo.equals(TipoMatricula.FUNCIONARIO)) {
            categ = funcionario.unidadePagamento
        } else {
            categ = UnidadePagamento.MENSALISTA
        }

        if (unidade_evento.equals(UnidadeCalculo.DIAS) ||
                unidade_evento.equals(UnidadeCalculo.HORAS)) {
            unidade = unidade_evento
        }

        if (categ.equals(UnidadePagamento.HORISTA) ||
                categ.equals(UnidadePagamento.DIARISTA)) {
            if (Datas.mes(calculo.competencia) == 2) {
                diasmes = calculo.quantidadeDiasCompetencia
            }
        }


        if (unidade.equals(UnidadeCalculo.HORAS) && matricula.tipo.equals(TipoMatricula.FUNCIONARIO)) {
            carga = funcionario.quantidadeHorasMes
        } else {
            carga = diasmes
        }

        double prop = proporcional;

        def valor = (vlrCalc / carga) * prop

        return valor
        /**FINAL **/

    }
}