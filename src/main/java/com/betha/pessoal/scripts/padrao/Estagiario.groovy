package com.betha.pessoal.scripts.padrao

com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas;
import com.betha.pessoal.scripts.utils

import static com.betha.pessoal.scripts.padrao.enums.*

class DadosEstagiario {
    Object data = utils.getFile("dadosMatricula")

    /**
     * Informações do estagiário que esta sendo calculado
     */
    class estagiario {

        static Date getDataInicioEstagio(){
            DadosEstagiario object = new DadosEstagiario();
            return object.data.estagiario.dataInicioEstagio;
        }
        static Date dataInicioEstagio = getDataInicioEstagio()

        static Date getDataFinalEstagio() {
            DadosEstagiario object = new DadosEstagiario();
            return object.data.estagiario.dataFinalEstagio
        }
        static Date dataFinalEstagio = getDataFinalEstagio()


        public static Boolean getRecebeDecimoTerceiro() {
            DadosEstagiario object = new DadosEstagiario();
            return object.data.estagiario.recebeDecimoTerceiro;
        }
        /**
         * Se funcionário recebe décimo terceiro, conforme cadastro de cargos
         */
        static Boolean recebeDecimoTerceiro = getRecebeDecimoTerceiro();

        public static BigDecimal getBolsaEstudos() {
            DadosEstagiario object = new DadosEstagiario();
            return object.data.estagiario.bolsaEstudos;
        }
        /**
         * valorBeneficio do funcionário
         */
        private BigDecimal bolsaEstudos = getBolsaEstudos();

        /**
         * Unidade de pagamento
         */
        public static Enum getUnidadePagamento() {
            DadosEstagiario object = new DadosEstagiario();
            String value = object.data.estagiario.unidadePagamento

            UnidadePagamento convertedEnumValue = value as UnidadePagamento
            return convertedEnumValue
        }
        static unidadePagamento = getUnidadePagamento();

    }


}



