package com.betha.pessoal.scripts.funcoes

import com.betha.pessoal.scripts.padrao.Afastamentos
import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas;
import com.betha.pessoal.scripts.padrao.Matricula.matricula;
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario;
import com.betha.pessoal.scripts.padrao.Calculo.calculo;
import static com.betha.pessoal.scripts.padrao.Calculo.evento
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir;
import static com.betha.pessoal.scripts.padrao.enums.*;

class Diastransf {
    static Long getDiastransf(String tipo, Integer quantidadeTransferencias, String onus ) {
        /** Dentro deste bloco deverá esta o conteúdo do script, o mesmo do cadastro de funções para fórmulas **/

        if (! TipoMatricula.FUNCIONARIO.equals(matricula.tipo)) return 0;

        def dias = 0

        if ( tipo == "R" ){
            //Recebidos no cloud não é por meio de afastamentos
            imprimir "Recebidos no cloud não é por meio de afastamentos, essa função irá retornar 0"
            return 0;
        }

        def tipoOnus = TipoOnus.CESSIONARIO
        if ( onus == "S" ){
            tipoOnus = TipoOnus.CEDENTE
        }

        def dataFinal = calculo.competencia;
        if (funcionario.dataRescisao != null ) dataFinal = funcionario.dataRescisao;
        int quantidadeAfast;
        Afastamentos.buscaPorPeriodo( funcionario.dataAdmissao, dataFinal,  ClassificacaoTipoAfastamento.CEDENCIA).each { afast ->
            if (afast.tipoOnus.equals(tipoOnus)) {
                def diaFim = Datas.dia(afast.fim)
                def fim = afast.fim
                if (diaFim > 30) {
                    fim = Datas.data(Datas.ano(afast.fim), Datas.mes(afast.fim), 30)
                }

                dias += Datas.diferencaDias(afast.inicio, fim) + 1
                quantidadeAfast++;
            }
            if ( quantidadeAfast == 1  && quantidadeTransferencias == 1 ) return dias;
        }


        return dias



        /** FINAL **/
    }
}
