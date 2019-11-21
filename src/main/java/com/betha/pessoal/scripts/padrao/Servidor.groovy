package com.betha.pessoal.scripts.padrao

import com.betha.pessoal.scripts.utils

import static com.betha.pessoal.scripts.padrao.enums.TipoMatricula

class Servidor {
    Object data = utils.getFile("dadosMatricula")
    /**
     * Matrícula
     */
    class servidor {

        static int getDependentesIrrf(){
            Servidor object = new Servidor();
            return object.data.servidor.dependentesIrrf;
        }

        static int dependentesIrrf = getDependentesIrrf()

        public static Enum getSexo() {
            Servidor object = new Servidor();
            String value = object.data.servidor.sexo;
            enums.Sexo convertedEnumValue = value as enums.Sexo
            return convertedEnumValue;
        }
        static Enum sexo = getSexo();

        private static Enum getGrauEscolaridade() {
            Servidor object = new Servidor();
            String value = object.data.servidor.grauEscolaridade;
            enums.GrauEscolaridade convertedEnumValue = value as enums.GrauEscolaridade
            return convertedEnumValue;
        }
        static Enum grauEscolaridade = getGrauEscolaridade();

        public static Date getDataNascimento() {
            Servidor object = new Servidor();
            return object.data.servidor.dataNascimento
        }
        static Date dataNascimento = getDataNascimento();

        private static Enum getEstadoCivil() {
            Servidor object = new Servidor();
            String value = object.data.servidor.estadoCivil;
            enums.EstadoCivil convertedEnumValue = value as enums.EstadoCivil
            return convertedEnumValue;
        }
        static Enum estadoCivil = getEstadoCivil();

        private static String getNome() {
            Servidor object = new Servidor();
            return object.data.servidor.nome
        }
        static String nome = getNome();

        private static String getNacionalidade() {
            Servidor object = new Servidor();
            return object.data.servidor.nacionalidade
        }
        static String nacionalidade = getNacionalidade();

        private static int getQuantidadeDependentes() {
            Servidor object = new Servidor();
            return object.data.servidor.dependentes
        }
        static int dependentes = getQuantidadeDependentes();

        public static ArrayList getBuscaDependentes() {
            Servidor object = new Servidor();
            return new ArrayList(object.data.servidor.listaDependentes)
        }
        static ArrayList buscaDependentes = getBuscaDependentes();

        public static Boolean getPossuiMolestiaGrave() {
            Servidor object = new Servidor();
            return object.data.servidor.possuiMolestiaGrave
        }
        static Boolean possuiMolestiaGrave = getPossuiMolestiaGrave();

        static long getDependenteSalarioFamilia(Enum tipo) {
            Servidor object = new Servidor();
            return object.data.servidor.getDependenteSalarioFamilia
        }


    }

}



