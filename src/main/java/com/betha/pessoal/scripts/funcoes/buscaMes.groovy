package com.betha.pessoal.scripts.funcoes

class BuscaMes {
    static int getBuscaMes(mes) {
        /** Dentro deste bloco deverá esta o conteúdo do script, o mesmo do cadastro de funções para fórmulas **/
        String mesAno = mes.toString();
        def meses = [
                "JANEIRO"  : 1,
                "FEVEREIRO": 2,
                "MARCO"    : 3,
                "ABRIL"    : 4,
                "MAIO"     : 5,
                "JUNHO"    : 6,
                "JULHO"    : 7,
                "AGOSTO"   : 8,
                "SETEMBRO" : 9,
                "OUTUBRO"  : 10,
                "NOVEMBRO" : 11,
                "DEZEMBRO" : 12
        ]

        return meses.getAt(mesAno)

        /** FINAL **/
    }

}
