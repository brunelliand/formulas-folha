package com.betha.pessoal.scripts.funcoes

import com.betha.pessoal.scripts.padrao.AvisoPrevio
import com.betha.pessoal.scripts.padrao.Calculo.calculo

import static com.betha.pessoal.scripts.padrao.enums.TipoAvisoPrevio
import static com.betha.pessoal.scripts.padrao.enums.TipoProcessamento;

class AvisoPrevioDescontado {
    static Boolean getAvisoPrevioDescontado() {
        /** Dentro deste bloco deverá esta o conteúdo do script, o mesmo do cadastro de funções para fórmulas **/
        boolean avisoDescontado;

        if (!TipoProcessamento.RESCISAO.equals(calculo.tipoProcessamento)) return avisoDescontado

        try {
            return TipoAvisoPrevio.DESCONTADO.equals(AvisoPrevio.tipo)
        } catch (ignored) {
            return false;
        }

        return avisoDescontado;
        /** FINAL **/
    }
}
