package com.betha.pessoal.scripts.padrao

import com.betha.pessoal.scripts.utils;

class Caracteres {
    static tamanho(String valor){
        return  valor.length()
    }

    static subTexto(String valor, int posicaoInicial, int posicaoFinal){
        return valor.substring(posicaoInicial,posicaoFinal)
    }


}

