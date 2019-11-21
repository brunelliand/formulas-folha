package com.betha.pessoal.scripts.funcoes

import com.betha.pessoal.scripts.padrao.Calculo.calculo;
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario;
import com.betha.pessoal.scripts.padrao.Matricula.matricula;
import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas;
import static com.betha.pessoal.scripts.padrao.enums.*;

class ConverteBase {
    static BigDecimal getConverteBase(dias) {
        /** Dentro deste bloco deverá esta o conteúdo do script, o mesmo do cadastro de funções para fórmulas **/
        //Criado por André Brunelli - 11/01/2017
        def diasmes;
        def retorno = 0
        if (calculo.unidade.equals(UnidadeCalculo.DIAS)) {
            return dias
        } else {
            diasmes = 30
            if (!TipoMatricula.FUNCIONARIO.equals(matricula.tipo)) {
                return dias
            } else {
                def categ = funcionario.unidadePagamento

                def competenciaCalculo = calculo.competencia
                def horasMes = funcionario.quantidadeHorasMes

                if (categ.equals(UnidadePagamento.HORISTA) ||
                        categ.equals(UnidadePagamento.DIARISTA)) {
                    if (Datas.mes(competenciaCalculo) == 2) {
                        diasmes = Datas.dia(competenciaCalculo)
                    }

                }
                BigDecimal ret;
                ret = horasMes * dias / diasmes


                def unidade = calculo.unidade

                if (unidade.equals(UnidadeCalculo.DIAS)) {
                    ret = dias
                }

                return ret
            }
        }

        return retorno
        /** FINAL **/

    }

}