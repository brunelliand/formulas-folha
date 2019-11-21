package com.betha.pessoal.scripts.funcoes

import com.betha.pessoal.scripts.padrao.Matricula.matricula

import static com.betha.pessoal.scripts.padrao.bfcScript.suspender

class SomenteAposentadosBeneficiarios {
    static void getSomenteAposentadosBeneficiarios() {
        /** Dentro deste bloco deverá esta o conteúdo do script, o mesmo do cadastro de funções para fórmulas **/
        String tipo = matricula.tipo.toString()

        if (! (tipo.equals('APOSENTADO')  ||  tipo.equals('BENEFICIARIO'))) {
            suspender('Esse cálculo é executado somente para aposentados e beneficiarios');
        }
        /** FINAL **/
    }
}
