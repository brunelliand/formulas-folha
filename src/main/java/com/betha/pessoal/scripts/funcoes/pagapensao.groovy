package com.betha.pessoal.scripts.funcoes

import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas;
import com.betha.pessoal.scripts.padrao.Servidor.servidor

import static com.betha.pessoal.scripts.padrao.enums.*;

class Pagapensao {
    public static Boolean getPagapensao() {
        /** Dentro deste bloco deverá esta o conteúdo do script, o mesmo do cadastro de funções para fórmulas **/

        //Criado por André Brunelli - 24/06/2019

        def dependentesPensao = servidor.buscaDependentes.sum(0, { it.pensao ? 1 : 0 })

        if (dependentesPensao > 0) {
            return true
        } else {
            return false
        }
        /** FINAL **/
    }
}
