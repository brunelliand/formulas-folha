package com.betha.pessoal.scripts.padrao

import com.betha.pessoal.scripts.utils;
import static com.betha.pessoal.scripts.padrao.enums.*

class Matricula {
    Object data = utils.getFile("dadosMatricula")


    /**
     * Matrícula
     */
    class matricula {

        private static Integer getCodigo() {
            Matricula object = new Matricula();
            return object.data.matricula.codigo
        }
        /**
         * Código da matrícula
         */
        static codigo = getCodigo();


        public static Enum getTipo() {
            Matricula object = new Matricula();
            String value = object.data.matricula.tipo

            TipoMatricula convertedEnumValue = value as TipoMatricula
            return convertedEnumValue
        }
        /**
         * Tipo da matrícula
         * FUNCIONARIO / ESTAGIÁRIO / PENSIONISTA / APOSENTADO / AUTONOMO
         */
        static tipo = getTipo();


    }

}



