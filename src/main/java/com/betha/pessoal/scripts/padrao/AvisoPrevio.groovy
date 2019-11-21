package com.betha.pessoal.scripts.padrao

import com.betha.pessoal.scripts.utils;

class AvisoPrevio {
    Object data = utils.getFile("avisoprevio");

    private static Enum getTipoAvisoPrevio() {
        AvisoPrevio object = new AvisoPrevio();
        String value = object.data.tipo

        enums.TipoAvisoPrevio convertedEnumValue = value as enums.TipoAvisoPrevio
        return convertedEnumValue
    }

    static Enum tipo = getTipoAvisoPrevio();

    static long getQuantidadeDiasAviso(){
        AvisoPrevio object = new AvisoPrevio();
        return object.data.quantidadeDiasAviso
    }

    static long quantidadeDiasAviso = getQuantidadeDiasAviso();



}

