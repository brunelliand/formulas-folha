package com.betha.pessoal.scripts.funcoes

import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas
import com.betha.pessoal.scripts.padrao.Matricula.matricula;
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.DadosPensionista.pensionista
import com.betha.pessoal.scripts.padrao.DadosAposentado.aposentado
import com.betha.pessoal.scripts.padrao.DadosAutonomo.autonomo;
import com.betha.pessoal.scripts.padrao.Calculo.calculo
import static com.betha.pessoal.scripts.padrao.Calculo.evento
import static com.betha.pessoal.scripts.padrao.enums.*

class Dtrescisao {
    public static getDtrescisao() {
        /** Dentro deste bloco deverá esta o conteúdo do script, o mesmo do cadastro de funções para fórmulas **/
        def dataRescisao = 0;

        if (TipoMatricula.FUNCIONARIO.equals(matricula.tipo) ||
                TipoMatricula.ESTAGIARIO.equals(matricula.tipo)) {
            if (calculo.dataRescisao) {
                return calculo.dataRescisao
            }
        }

        if (TipoMatricula.PENSIONISTA.equals(matricula.tipo)) {
            if (pensionista.dataCessacaoBeneficio) {
                return pensionista.dataCessacaoBeneficio
            }
        }

        if (TipoMatricula.APOSENTADO.equals(matricula.tipo)) {
            if (aposentado.dataCessacaoAposentadoria) {
                return aposentado.dataCessacaoAposentadoria
            }
        }

        if (TipoMatricula.AUTONOMO.equals(matricula.tipo)) {
            if (autonomo.dataFimServico) {
                return autonomo.dataFimServico
            }
        }

        return dataRescisao

        /** FINAL **/
    }
}
