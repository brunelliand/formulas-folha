package com.betha.pessoal.scripts.funcoes

import com.betha.pessoal.scripts.padrao.Afastamentos
import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas;
import com.betha.pessoal.scripts.padrao.Matricula.matricula;
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario;
import com.betha.pessoal.scripts.padrao.Calculo.calculo;
import static com.betha.pessoal.scripts.padrao.Calculo.evento
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir;
import static com.betha.pessoal.scripts.padrao.enums.*;

class Dtafast {

    static Date getDtafast(Enum classificacao, Boolean licencaMaternidadeOrgininal) {
        /** Dentro deste bloco deverá esta o conteúdo do script, o mesmo do cadastro de funções para fórmulas **/

        boolean licencaMatOriginal = false
        def classif = classificacao

        if (licencaMaternidadeOrgininal) {
            licencaMatOriginal = licencaMaternidadeOrgininal
        }

        def dataInicial = funcionario.dataAdmissao
        def dataFinal = Datas.data(Datas.ano(calculo.competencia), Datas.mes(calculo.competencia), calculo.quantidadeDiasCompetencia)

        if (licencaMatOriginal) {
            if (classif == ClassificacaoTipoAfastamento.PRORROGACAO_DA_LICENCA_MATERNIDADE) {
                classif = ClassificacaoTipoAfastamento.LICENCA_MATERNIDADE
            }
        }

        def inicio = false
        Afastamentos.buscaPorPeriodo(dataInicial, dataFinal, classif).each { afastamento ->
            if (!inicio) {
                // parar() atualizar engine para usar essa função
                inicio = afastamento.inicio
            }
        }

        if (!inicio) {
            inicio = false
        }
        imprimir 'Inicio do afastamento ' + inicio
        return inicio


        /** FINAL **/
    }
}
