package com.betha.pessoal.scripts.funcoes

import com.betha.pessoal.scripts.padrao.Afastamentos
import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas;
import com.betha.pessoal.scripts.padrao.Matricula.matricula;
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario;
import com.betha.pessoal.scripts.padrao.Calculo.calculo;
import static com.betha.pessoal.scripts.padrao.Calculo.evento;
import static com.betha.pessoal.scripts.padrao.enums.*;

class Afasprorroglicmatlei11770 {
    public static long getAfasprorroglicmatlei11770() {
        /** Dentro deste bloco deverá esta o conteúdo do script, o mesmo do cadastro de funções para fórmulas **/
        def dias = 0;

        def classificacoes = ClassificacaoTipoAfastamento.PRORROGACAO_DA_LICENCA_MATERNIDADE_11_770

        Afastamentos.buscaPorPeriodo(classificacoes).each { afast ->
            def diaFim = afast.fim.dia
            def fim = afast.fim
            if (diaFim > 30) {
                fim = Datas.data(afast.fim.ano, afast.fim.mes, 30)
            }

            dias += Datas.diferencaDias(afast.inicio, fim) + 1
        }

        if (dias > 30 && funcionario.unidadePagamento == UnidadePagamento.MENSALISTA) {
            dias = 30
        }
        return dias
        /** FINAL **/
    }
}
