package com.betha.pessoal.scripts.funcoes

import com.betha.pessoal.scripts.padrao.Afastamentos
import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas;
import com.betha.pessoal.scripts.padrao.Matricula.matricula;
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario;
import com.betha.pessoal.scripts.padrao.Calculo.calculo;
import static com.betha.pessoal.scripts.padrao.Calculo.evento
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir;
import static com.betha.pessoal.scripts.padrao.enums.*;

class Mesesafast {
    public static int getMesesafast(Date dataInicial, Date dataFinal, ArrayList classificacoes) {
        /** Dentro deste bloco deverá esta o conteúdo do script, o mesmo do cadastro de funções para fórmulas **/

        imprimir 'Iniciando consulta de meses afastado'

        def dini = dataInicial
        def dfin = dataFinal
        def dtrescisao = Funcoes.dtrescisao()

        if (Datas.diferencaDias(dini, funcionario.dataAdmissao) > 0) {
            dini = funcionario.dataAdmissao
        }

        if (dtrescisao) {
            if (dfin > dtrescisao) {
                dfin = dtrescisao
            }
        }

        def cptIni = Datas.data(Datas.ano(dini), Datas.mes(dini), 1)
        def cptFin = Datas.data(Datas.ano(dfin), Datas.mes(dfin), 1)

        long totalMeses = Datas.diferencaMeses(cptIni, cptFin) + 1

        def classifAfast;
        if (classificacoes.toString() == 'T') {
            classifAfast = Funcoes.listaafastamentos("T") //Retorna todos as classificações
        } else {
            classifAfast = classificacoes
        }

        int meses;

        for (int indice = 1; indice <= totalMeses; indice++) {
            def cptAux = Datas.adicionaMeses(dini, indice - 1);
            def fimCptAux = Datas.data(Datas.ano(cptAux), Datas.mes(cptAux), calculo.quantidadeDias(Datas.mes(cptAux), Datas.ano(cptAux)) )
            //imprimir "Verificando a competência ${cptAux}"
            Afastamentos.buscaPorPeriodo(cptAux, fimCptAux, *classifAfast).each { a ->
                def ultimoDiaComp = Datas.data(Datas.ano(cptAux), Datas.mes(cptAux), calculo.quantidadeDias(Datas.mes(cptAux), Datas.ano(cptAux)))


                def inicio = a.inicio
                def fim = a.fim

                if (inicio < cptAux) {
                    inicio = cptAux
                }
                if (fim > ultimoDiaComp) {
                    fim = ultimoDiaComp
                }


                imprimir "inicio ${inicio}"
                imprimir "fim ${fim}"

                def dias = Datas.diferencaDias(inicio, fim) + 1

                if (dias < 0 ) dias = 0;

                imprimir 'Dias ' + dias

                if (dias >= 15) {
                    meses++
                }

            }

        }

        imprimir 'Meses afastado ' + meses
        return meses


        /** FINAL **/
    }
}
