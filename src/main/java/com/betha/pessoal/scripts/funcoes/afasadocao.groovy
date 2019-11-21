package com.betha.pessoal.scripts.funcoes

import com.betha.pessoal.scripts.padrao.Afastamentos
import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas;
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario;
import static com.betha.pessoal.scripts.padrao.enums.*;

class Afasadocao {
    public static long getAfasadocao() {
        /** Dentro deste bloco deverá esta o conteúdo do script, o mesmo do cadastro de funções para fórmulas **/
        def dias = 0

        def classificacoes = ClassificacaoTipoAfastamento.ADOCAO_GUARDA_JUDICIAL_DE_CRIANCA

        Afastamentos.buscaPorPeriodo(classificacoes).each { afast ->
            def diaFim = Datas.dia(afast.fim)
            def fim = afast.fim
            if (diaFim > 30) {
                fim = Datas.data(Datas.ano(afast.fim), Datas.mes(afast.fim), 30)
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
