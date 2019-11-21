package com.betha.pessoal.scripts.funcoes

import com.betha.pessoal.scripts.padrao.Afastamentos
import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas;
import com.betha.pessoal.scripts.padrao.Matricula.matricula;
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario;
import com.betha.pessoal.scripts.padrao.Calculo.calculo;
import static com.betha.pessoal.scripts.padrao.Calculo.evento;
import static com.betha.pessoal.scripts.padrao.enums.*;

class Afasservmil {
    public static long getAfasservmil() {
        /** Dentro deste bloco deverá esta o conteúdo do script, o mesmo do cadastro de funções para fórmulas **/
        def dias = 0;

        def classificacoes = ClassificacaoTipoAfastamento.SERVICO_MILITAR

        Afastamentos.buscaPorPeriodo(classificacoes).each { afast ->
            def diaFim = Datas.dia(afast.fim)
            def fim = afast.fim
            if (diaFim > 30) {
                fim = Datas.data( Datas.ano(afast.fim), Datas.mes(afast.fim), 30)
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
