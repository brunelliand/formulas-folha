package com.betha.pessoal.scripts.padrao

import com.betha.pessoal.scripts.padrao.Calculo.calculo
import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas
import com.betha.pessoal.scripts.utils

class Eventos {
    def data = utils.getFile("eventos");

    static BigDecimal valor(int codigo ) {
        Eventos object = new Eventos();
        ArrayList eventos = new ArrayList(object.data);
        return eventos.sum(0, { it.codigoEvento == codigo ? it.valorCalculado : 0 })

    }

    static BigDecimal valorReferencia(int codigo ) {
        Eventos object = new Eventos();
        ArrayList eventos = new ArrayList(object.data);
        return eventos.sum(0, { it.codigoEvento == codigo ? it.valorCalculado : 0 })

    }

    static BigDecimal valorCalculado(int codigoEvento , Enum tipoValor,  Enum tipoProcessamento , Enum subTipoProcessamento ){
        return valor(codigoEvento)
    }

    static BigDecimal valorCalculado(int codigoEvento , Enum tipoValor, Enum tipoProcessamento , Enum subTipoProcessamento, Date competencia ){
        return valor(codigoEvento)
    }

}

