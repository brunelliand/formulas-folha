package com.betha.pessoal.scripts.funcoes

import com.betha.pessoal.scripts.padrao.Afastamentos
import com.betha.pessoal.scripts.padrao.DadosAposentado
import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas;
import com.betha.pessoal.scripts.padrao.Matricula.matricula;
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario;
import com.betha.pessoal.scripts.padrao.Calculo.calculo;
import static com.betha.pessoal.scripts.padrao.Calculo.evento
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender;
import static com.betha.pessoal.scripts.padrao.enums.*;

class Diasaposent {
    static long getDiasaposent() {
        /** Dentro deste bloco deverá esta o conteúdo do script, o mesmo do cadastro de funções para fórmulas **/
        if (!TipoMatricula.APOSENTADO.equals(matricula.tipo)) {
            suspender "Calcular somente para aposentados"
        }

        def dias = 0
        def inicioCompetencia = Funcoes.inicioCompetencia()
        def finalCompetencia = calculo.competencia

        def dataInicio = DadosAposentado.aposentado.dataAposentadoria
        def dataFinal = calculo.competencia

        if (dataInicio > finalCompetencia) {
            suspender "Data de inicio da aposentadoria é maior que o início da competência"
        }

        if (dataInicio < inicioCompetencia) {
            dataInicio = inicioCompetencia
        }

        def dataCessacaoAposentadoria = DadosAposentado.aposentado.dataCessacaoAposentadoria
        if (dataCessacaoAposentadoria != null) {
            if (dataCessacaoAposentadoria < inicioCompetencia) {
                suspender "Data de cessação da aposentadoria é menor que o início da competência"
            }

            if (dataCessacaoAposentadoria < dataFinal) {
                dataFinal = dataCessacaoAposentadoria
            }
        }

        def diasAposentado = Datas.diferencaDias(dataInicio, dataFinal) + 1

        if (diasAposentado == calculo.quantidadeDiasCompetencia) {
            diasAposentado = 30;
        }

        return diasAposentado

        /** FINAL **/
    }
}
