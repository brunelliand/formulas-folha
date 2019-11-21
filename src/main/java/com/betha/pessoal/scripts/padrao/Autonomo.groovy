package com.betha.pessoal.scripts.padrao

com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas;
import com.betha.pessoal.scripts.utils

import static com.betha.pessoal.scripts.padrao.enums.*

class DadosAutonomo {
    Object data = utils.getFile("dadosMatricula")

    /**
     * Informações do autônomo que esta sendo calculado
     */
    class autonomo {

        public static BigDecimal getTotalServicosAutonomo() {
            DadosAutonomo object = new DadosAutonomo();
            return object.data.autonomo.totalServicosAutonomo;
        }
        /**
         * valor serviço do autônomo
         */
        private BigDecimal valorBeneficio = getTotalServicosAutonomo();

        /**
         * Unidade de pagamento
         */
        public static Enum getUnidadePagamento() {
            DadosAutonomo object = new DadosAutonomo();
            String value = object.data.autonomo.unidadePagamento

            UnidadePagamento convertedEnumValue = value as UnidadePagamento
            return convertedEnumValue
        }
        static unidadePagamento = getUnidadePagamento();

        static Date getDataInicioServico(){
            DadosAutonomo object = new DadosAutonomo();
            return object.data.autonomo.dataInicioServico
        }
        static Date dataInicioServico = getDataInicioServico()

        static Date getDataFimServico(){
            DadosAutonomo object = new DadosAutonomo();
            return object.data.autonomo.dataFimServico
        }
        static Date dataFimServico = getDataFimServico()

    }


}



