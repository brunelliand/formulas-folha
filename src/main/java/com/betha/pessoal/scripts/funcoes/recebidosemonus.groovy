package com.betha.pessoal.scripts.funcoes

import com.betha.pessoal.scripts.padrao.Afastamentos
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas

import static com.betha.pessoal.scripts.padrao.enums.*

/**
 * Dias recebidos sem ônus para o destino.
 */
class Recebidosemonus {
    public static long getRecebidosemonus() {
        /** Dentro deste bloco deverá esta o conteúdo do script, o mesmo do cadastro de funções para fórmulas **/
        if (!matricula.tipo.equals(TipoMatricula.FUNCIONARIO)) {
            return 0
        }

        if (!funcionario.tipoAdmissao.equals(TipoAdmissao.TRANSFERENCIA)) {
            return 0
        }

        def onusentidade = [ResponsabilidadePagamento.PAGAMENTO_EXCLUSIVAMENTE_PELO_CESSIONARIO_DESTINO, ResponsabilidadePagamento.PAGAMENTO_PELO_CEDENTE_ORIGEM_E_PELO_CESSIONARIO_DESTINO]

        if (onusentidade.indexOf(funcionario.responsabilidadePagamento) < 0) {
            return 0
        }

        return Funcoes.diastrab()
        /** FINAL **/
    }
}
