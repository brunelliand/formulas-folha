package com.betha.pessoal.scripts.padrao

class bfcScript {
    static void imprimir(mensagem){
        println(mensagem)
    }

    static void suspender(mensagem){
        println("Cálculo suspenso : ${mensagem}");
        throw new java.lang.InterruptedException("Cálculo suspenso : ${mensagem}")
    }

    


}

