package com.betha.pessoal.scripts.padrao

import com.betha.pessoal.scripts.utils;

class BasesOutrasEmpresas {
    def data = utils.getFile("basesoutrasempresas");

    static ArrayList buscaPor(Enum TipoProcessamento, Date competencia) {
        BasesOutrasEmpresas basesOutrasEmpresas = new BasesOutrasEmpresas()
        return new ArrayList(basesOutrasEmpresas.data)
    }

    static ArrayList buscaPor(Enum TipoProcessamento) {
        BasesOutrasEmpresas basesOutrasEmpresas = new BasesOutrasEmpresas()
        return new ArrayList(basesOutrasEmpresas.data)
    }


}

