package com.betha.pessoal.scripts.padrao

import com.betha.pessoal.scripts.utils;

class LancamentoEvento {
    Object data = utils.getFile("lancamentoEvento");

    /**
     * Retorna o valor de Lancamentos de eventos em variáveis
     */
    class Lancamentos {
        /**
         * Valor do evento lançado em variáveis
         * @param evento
         * @return o valor do lançamento, caso não tenha valor lançado retorna -1
         */
        private static BigDecimal getValor(Integer evento) {
            LancamentoEvento lancamentos = new LancamentoEvento();
            if (lancamentos.data.lancamentos.count{it.evento.codigo.equals(evento)} == 0 ) return -1

            return lancamentos.data.lancamentos.sum( 0, { it.evento.codigo.equals(evento) ? it.valor : 0 })
        }

        static BigDecimal valor(evento) {
            if (evento instanceof java.lang.Object) {
                evento = evento.codigo
            }
            BigDecimal valor = BigDecimal.valueOf(getValor(evento));

            return getValor(evento)
        }

        static BigDecimal valorComposicaoMes() {
            return BigDecimal.valueOf(0)
        }


    }


}