package com.betha.pessoal.scripts.funcoes

import com.betha.pessoal.scripts.padrao.Matricula.matricula

import static com.betha.pessoal.scripts.padrao.bfcScript.suspender

class SomenteFuncionarios {
    static void getSomenteFuncionarios() {
        /** Dentro deste bloco deverá esta o conteúdo do script, o mesmo do cadastro de funções para fórmulas **/
        String tipo = matricula.tipo.toString()
        if (!tipo.equals('FUNCIONARIO')) {
            suspender('Esse cálculo é executado somente para funcionários');
        }
        /** FINAL **/
    }
}
