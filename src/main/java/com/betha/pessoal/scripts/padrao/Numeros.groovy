package com.betha.pessoal.scripts.padrao

import com.betha.pessoal.scripts.utils;

class Numeros {
    static BigDecimal absoluto(valor) {
        return valor.abs();
    }

    static BigDecimal arredonda(valor, casasDecimais) {
        return valor.round(casasDecimais);
    }

    static Boolean ehNumero(valor) {
        if (valor instanceof java.math.BigDecimal ||
                valor instanceof java.lang.Double ||
                valor instanceof java.lang.Integer) {
            return true
        }
        return false
    }

    static BigDecimal numero(value) {
        BigDecimal valorConvertido = new BigDecimal(value);
        return valorConvertido;
    }

    static Double resto(valorDividendo, valorDivisor) {
        try {
            return valorDividendo % valorDivisor
        } catch (ignore) {
            return 0
        }
    }

    static BigDecimal trunca(valor, quantidadeCasasDecimais) {
        BigDecimal value = BigDecimal.valueOf(valor)
        return value.trunc(quantidadeCasasDecimais);
    }


}

