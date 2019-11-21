package com.betha.pessoal.scripts.funcoes

import com.betha.pessoal.scripts.padrao.Afastamentos
import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas;
import com.betha.pessoal.scripts.padrao.Matricula.matricula;
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario;
import com.betha.pessoal.scripts.padrao.Calculo.calculo;
import static com.betha.pessoal.scripts.padrao.Calculo.evento;
import static com.betha.pessoal.scripts.padrao.enums.*;

class Diasafast {
    public static long getDiasafast(Date datainicial, Date datafinal, def afastamentos) {
        /** Dentro deste bloco deverá esta o conteúdo do script, o mesmo do cadastro de funções para fórmulas **/

        //Criado por: André Brunelli em: 11/04/2017

        def listaClassificacoes
        if (afastamentos == 'T') {
            listaClassificacoes = Funcoes.listaafastamentos('T')
        } else {
            listaClassificacoes = afastamentos
        }
        int dias = 0
        Afastamentos.buscaPorPeriodo(datainicial, datafinal, *listaClassificacoes).each
                { afast ->
                    dias += Datas.diferencaDias(afast.inicio, afast.fim) + 1
                }

        if (dias <= 0) {
            dias = 0
        }

        return dias


        /** FINAL **/
    }
}
