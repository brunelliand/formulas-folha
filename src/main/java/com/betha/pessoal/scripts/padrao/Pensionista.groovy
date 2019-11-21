package com.betha.pessoal.scripts.padrao

com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas;
import com.betha.pessoal.scripts.utils

import static com.betha.pessoal.scripts.padrao.enums.*

class DadosPensionista {
    Object data = utils.getFile("dadosMatricula")

    /**
     * Informações do pensionista que esta sendo calculado
     */
    class pensionista {

        static Date getDataInicioBeneficio(){
            DadosPensionista dadosPensionista = new DadosPensionista()
            return dadosPensionista.data.pensionista.dataInicioBeneficio
        }

        static Date dataInicioBeneficio = getDataInicioBeneficio()

        private static Boolean getRecebeDecimoTerceiro() {
            DadosPensionista object = new DadosPensionista();
            return object.data.pensionista.recebeDecimoTerceiro;
        }
        /**
         * Se aposentado recebe décimo terceiro, conforme cadastro de cargos
         */
        static Boolean recebeDecimoTerceiro = getRecebeDecimoTerceiro();

        public static BigDecimal getValorBeneficio() {
            DadosPensionista object = new DadosPensionista();
            return object.data.pensionista.valorBeneficio;
        }
        /**
         * valorBeneficio do funcionário
         */
        private BigDecimal valorBeneficio = getValorBeneficio();

        static Date getDataCessacaoBeneficio(){
            DadosPensionista object = new DadosPensionista();
            return object.data.pensionista.dataCessacaoBeneficio
        }
        static Date dataCessacaoBeneficio = getDataCessacaoBeneficio()

        /**
         * Unidade de pagamento
         */
        public static Enum getUnidadePagamento() {
            DadosPensionista object = new DadosPensionista();
            String value = object.data.pensionista.unidadePagamento

            UnidadePagamento convertedEnumValue = value as UnidadePagamento
            return convertedEnumValue
        }
        static unidadePagamento = getUnidadePagamento();

    }


}



