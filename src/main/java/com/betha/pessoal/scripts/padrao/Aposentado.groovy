package com.betha.pessoal.scripts.padrao

com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas;
import com.betha.pessoal.scripts.utils

import static com.betha.pessoal.scripts.padrao.enums.*

class DadosAposentado {
    Object data = utils.getFile("dadosMatricula")

    /**
     * Informações do funcionário que esta sendo calculado
     */
    class aposentado {

        static Date getDataAposentadoria(){
            DadosAposentado dadosAposentado = new DadosAposentado()
            return dadosAposentado.data.aposentado.dataAposentadoria
        }

        static Date dataAposentadoria = getDataAposentadoria()

        private static Boolean getRecebeDecimoTerceiro() {
            DadosAposentado object = new DadosAposentado();
            return object.data.aposentado.recebeDecimoTerceiro;
        }
        /**
         * Se aposentado recebe décimo terceiro, conforme cadastro de cargos
         */
        static Boolean recebeDecimoTerceiro = getRecebeDecimoTerceiro();

        public static BigDecimal getValorBeneficio() {
            DadosAposentado object = new DadosAposentado();
            return object.data.aposentado.valorBeneficio;
        }
        /**
         * valorBeneficio do funcionário
         */
        private BigDecimal valorBeneficio = getValorBeneficio();

        /**
         * Unidade de pagamento
         */
        public static Enum getUnidadePagamento() {
            DadosAposentado object = new DadosAposentado();
            String value = object.data.aposentado.unidadePagamento

            UnidadePagamento convertedEnumValue = value as UnidadePagamento
            return convertedEnumValue
        }
        static unidadePagamento = getUnidadePagamento();

        static Date getDataCessacaoAposentadoria(){
            DadosAposentado object = new DadosAposentado();
            return object.data.aposentado.dataCessacaoBeneficio
        }
        static Date dataCessacaoBeneficio = getDataCessacaoAposentadoria()


    }


}



