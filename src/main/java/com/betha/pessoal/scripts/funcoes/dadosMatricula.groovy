package com.betha.pessoal.scripts.funcoes

import com.betha.pessoal.scripts.padrao.Calculo.calculo;
import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas;
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario;
import com.betha.pessoal.scripts.padrao.DadosEstagiario.estagiario
import com.betha.pessoal.scripts.padrao.DadosAutonomo.autonomo
import com.betha.pessoal.scripts.padrao.DadosAposentado.aposentado
import com.betha.pessoal.scripts.padrao.DadosPensionista.pensionista
import static com.betha.pessoal.scripts.padrao.enums.*;


class DadosMatricula {

    static LinkedHashMap getDadosMatricula() {

        /** Dentro deste bloco deverá esta o conteúdo do script, o mesmo do cadastro de funções para fórmulas **/

        def retorno = [:]
        def dataInicio = 0
        def dataSaida = 0
        boolean existeSaida = false
        def competencia = calculo.competencia;

        def finalCompetencia = Datas.data(Datas.ano(calculo.competencia), Datas.mes(calculo.competencia), calculo.quantidadeDiasCompetencia);

        if (TipoMatricula.FUNCIONARIO.equals(matricula.tipo)) {
            dataInicio = funcionario.dataAdmissao
            dataSaida = calculo.dataRescisao
            if (dataSaida != null) {
                existeSaida = true
            }
        }

        if(matricula.tipo == TipoMatricula.ESTAGIARIO){
            dataInicio = estagiario.dataInicioEstagio
            dataSaida = estagiario.dataFinalEstagio
            if(dataSaida != null){
                existeSaida = true
            }
        }
        if(matricula.tipo == TipoMatricula.PENSIONISTA){
            dataInicio = pensionista.dataInicioBeneficio
            dataSaida = pensionista.dataCessacaoBeneficio
            if(dataSaida != null){
                existeSaida = true
            }
        }
        if(matricula.tipo == TipoMatricula.APOSENTADO){
            dataInicio = aposentado.dataAposentadoria
            dataSaida = aposentado.dataCessacaoAposentadoria
            if(dataSaida != null){
                existeSaida = true
            }
        }
        if(matricula.tipo == TipoMatricula.AUTONOMO){
            dataInicio = autonomo.dataInicioServico
            dataSaida = autonomo.dataFimServico
            if(dataSaida != null){
                existeSaida = true
            }
        }

        retorno['dataInicio'] = dataInicio
        retorno['dataSaida'] = dataSaida
        retorno['existeSaida'] = existeSaida

        return retorno
        /** FINAL **/

    }
}

