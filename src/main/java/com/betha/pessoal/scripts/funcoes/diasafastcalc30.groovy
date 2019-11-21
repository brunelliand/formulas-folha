package com.betha.pessoal.scripts.funcoes

import com.betha.pessoal.scripts.padrao.Afastamentos
import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas;
import com.betha.pessoal.scripts.padrao.Matricula.matricula;
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario;
import com.betha.pessoal.scripts.padrao.Calculo.calculo
import groovy.lang.Binding;

import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.Calculo.evento;
import static com.betha.pessoal.scripts.padrao.enums.*;

class Diasafastcalc30 {

    static int getDiasafastcalc30(Date competencia, afastamentos) {
        Binding binding = new Binding()

        /** Dentro deste bloco deverá esta o conteúdo do script, o mesmo do cadastro de funções para fórmulas **/
        //Criado por: André Brunelli em: 11/04/2017

        def ano = Datas.ano(competencia)
        def mes = Datas.mes(competencia)

        def datainicial = Datas.data(ano, mes, 1)
        def datafinal = Datas.data(ano, mes, calculo.quantidadeDias(mes, ano))

        //Caso informe "T" ou não informe uma lista de afastamentos serão considerados todas as classificações de afastamentos
        def listaClassificacoes;
        if (binding.variables["afastamentos"]) {
            if (afastamentos.toString() == 'T') {
                listaClassificacoes = Funcoes.listaafastamentos('T')
            } else {
                listaClassificacoes = afastamentos
            }
        } else {
            listaClassificacoes = Funcoes.listaafastamentos('T')
        }

        int dias = 0;
        Afastamentos.buscaPorPeriodo(datainicial, datafinal, *listaClassificacoes).each { afast ->
            def inicio = afast.inicio
            if (inicio < Datas.data(ano, mes, 1)) {
                inicio = Datas.data(ano, mes, 1)
            }

            def fim = afast.fim
            def quantidadeDiasMes = calculo.quantidadeDias(mes, ano)
            if (quantidadeDiasMes > 30) {
                quantidadeDiasMes = 30
            }
            if (fim > Datas.data(ano, mes, quantidadeDiasMes)) {
                fim = Datas.data(ano, mes, quantidadeDiasMes)
            }

            dias = dias + Datas.diferencaDias(inicio, fim) + 1
        }

        if (dias <= 0) return 0

        if (UnidadePagamento.MENSALISTA.equals(funcionario.unidadePagamento)) {
            if (dias > 30) {
                dias = 30
            }
        }

        return dias

        /** FINAL **/
    }
}
