package com.betha.pessoal.scripts.funcoes

import com.betha.pessoal.scripts.padrao.Calculo.calculo
import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas

class PrimeiroDia {
    static Date getPrimeiroDia(Date data) {
        /** Dentro deste bloco deverá esta o conteúdo do script, o mesmo do cadastro de funções para fórmulas **/
        return Datas.data(Datas.ano(data),Datas.mes(data),1)

        /** FINAL **/
    }
}
