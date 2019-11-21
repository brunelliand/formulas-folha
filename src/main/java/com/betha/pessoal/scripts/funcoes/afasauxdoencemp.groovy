package com.betha.pessoal.scripts.funcoes

import com.betha.pessoal.scripts.padrao.Afastamentos
import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas;
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario;
import static com.betha.pessoal.scripts.padrao.enums.*;

class Afasauxdoencemp {
    public static long getAfasauxdoencemp() {
        /** Dentro deste bloco deverá esta o conteúdo do script, o mesmo do cadastro de funções para fórmulas **/
        def dias = 0

        def classificacoes = ClassificacaoTipoAfastamento.AUXILIO_DOENCA_EMPREGADOR

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
