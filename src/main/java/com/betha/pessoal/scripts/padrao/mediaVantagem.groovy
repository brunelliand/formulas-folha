package com.betha.pessoal.scripts.padrao

import com.betha.pessoal.scripts.MediasVantagensInfos
import com.betha.pessoal.scripts.utils;
import com.betha.pessoal.scripts.padrao.evento

class MediasVantagens {
    Object data = utils.getFile("mediavantagem");
    class mediaVantagem{
        static BigDecimal calcular(){
            MediasVantagens mediasVantagens = new MediasVantagens()
            BigDecimal valor = mediasVantagens.data.sum(0, {it.evento as int == MediasVantagensInfos.CODIGO as int ? it.valor : 0 })
        }

        static BigDecimal calcular(BigDecimal valorReferencia){
            MediasVantagens mediasVantagens = new MediasVantagens()
            BigDecimal valor = mediasVantagens.data.sum(0, {it.evento as int == MediasVantagensInfos.CODIGO as int ? it.valor : 0 })
        }

        static BigDecimal calcular(periodoAquisitivoDecimoTerceiro, int avosAPagar){
            MediasVantagens mediasVantagens = new MediasVantagens()
            BigDecimal valor = mediasVantagens.data.sum(0, {it.evento as int == MediasVantagensInfos.CODIGO as int ? it.valor : 0 })
        }

        static BigDecimal calcular(periodoAquisitivo){
            MediasVantagens mediasVantagens = new MediasVantagens()
            BigDecimal valor = mediasVantagens.data.sum(0, {it.evento as int == MediasVantagensInfos.CODIGO as int ? it.valor : 0 })
        }

        static BigDecimal calcular(Date dataInicial, Date dataFinal){
            MediasVantagens mediasVantagens = new MediasVantagens()
            BigDecimal valor = mediasVantagens.data.sum(0, {it.evento as int == MediasVantagensInfos.CODIGO as int ? it.valor : 0 })
        }


    }
}

