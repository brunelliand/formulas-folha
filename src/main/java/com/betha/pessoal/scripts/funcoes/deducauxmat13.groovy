package com.betha.pessoal.scripts.funcoes

import com.betha.pessoal.scripts.utils;

class Deducauxmat13 {
    static BigDecimal getDeducauxmat13(BigDecimal base, int avos) {
        /** Dentro deste bloco deverá esta o conteúdo do script, o mesmo do cadastro de funções para fórmulas **/
        int avos13 = avos
        int mesesmat13 = Funcoes.mesesmat13()
        if (mesesmat13.equals(0)) return 0

        if(avos13.equals(0)) return 0

        if (mesesmat13 > avos13) {
            avos13 = mesesmat13
        }

        double vlrDeducao = base / avos13 * mesesmat13

        return vlrDeducao
        /** FINAL **/
    }

}

