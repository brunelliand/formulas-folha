package com.betha.pessoal.scripts.padrao

import com.betha.pessoal.scripts.utils;
import static com.betha.pessoal.scripts.padrao.enums.*

class PlanoSaudeDespesas {
    def data = utils.getFile("planosaudedespesas");

    static ArrayList buscaDespesasPlanoSaude() {
        PlanoSaudeDespesas planoSaudeDespesas = new PlanoSaudeDespesas()
        ArrayList despesas = new ArrayList()
        planoSaudeDespesas.data.each { despesa ->
            final Enum tipo = utils.getEnumValue(TipoDespesaPlanoSaude, despesa.tipo)
            final BigDecimal valor = despesa.valor
            final BigDecimal valorTotal = despesa.valorTotal
            final BigDecimal valorSubsidio = despesa.valorSubsidio

            despesas.push([tipo: tipo, valor: valor, valorTotal: valorTotal, valorSubsidio: valorSubsidio])

        }

        return despesas
    }


}

