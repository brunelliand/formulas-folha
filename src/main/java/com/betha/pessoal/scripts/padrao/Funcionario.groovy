package com.betha.pessoal.scripts.padrao

com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas;
import com.betha.pessoal.scripts.utils

import static com.betha.pessoal.scripts.padrao.enums.*

class DadosFuncionario {
    Object data = utils.getFile("dadosMatricula")

    /**
     * Informações do funcionário que esta sendo calculado
     */
    class funcionario {
        /**
         * Retorna o plano salarial da origem do rendimento
         * @param OrigemRendimento
         */
        private static String planoSalarialOrigemRendimento = OrigemRendimento.CARGO

        /**
         * Ocorrência sefip do funcionário
         */
        public static Enum ocorrenciaSefip = OcorrenciaSefip.EXPOSTO_APOSENTADORIA_15_ANOS;

        /**
         * Classe salarial do funcionário
         */
        private static final String classeSalarial = 'A-1'

        /**
         * Classe salarial conforme a origem do rendimento informada
         * @param OrigemRendimento
         * @return Retorna a classe salarial
         */
        private static String classeSalarialOrigemRendimento = 'A-1'

        /**
         * Mês de contribuição sindical configurado no cadastro do sindicato (cadastro de empresas)
         */
        private static Enum mesContribuicaoSindical = MesContribuicaoSindical.MAIO;


        private static Enum getTipoVinculo() {
            DadosFuncionario object = new DadosFuncionario();
            String value = object.data.funcionario.tipoVinculo

            TipoVinculo convertedEnumValue = value as TipoVinculo
            return convertedEnumValue
        }
        /**
         * Tipo do vínculo
         */
        static tipoVinculo = getTipoVinculo()


        public static String getCategoriaSefipVinculo() {
            DadosFuncionario object = new DadosFuncionario();
            return object.data.funcionario.categoriaSefipVinculo
        }
        /**
         * Categoria sefip do vínculo
         */
        static categoriaSefipVinculo = getCategoriaSefipVinculo();


        private static String getDescricaoOrganograma() {
            DadosFuncionario object = new DadosFuncionario();
            return object.data.funcionario.descricaoOrganograma
        }
        /**
         * Descrição do organograma
         */
        static String descricaoOrganograma = getDescricaoOrganograma();


        private static String getCodigoOrganograma() {
            DadosFuncionario object = new DadosFuncionario();
            return object.data.funcionario.codigoOrganograma
        }
        /**
         * Código do organograma
         */
        static String codigoOrganograma = getDescricaoOrganograma();


        private static Boolean getContaVaga() {
            DadosFuncionario object = new DadosFuncionario();
            return object.data.funcionario.contaVaga
        }
        /**
         * Se funcionario conta vaga
         */
        static Boolean contaVaga = getContaVaga();

        private static Boolean getContaVagaOrigemRendimento(Enum origem) {
            DadosFuncionario object = new DadosFuncionario();
            if (object.data.funcionario.contaVaga && origem.toString().equals("CARGO")) {
                return true
            }
            return false;
        }
        /**
         * Se funcionário conta vaga na origem de rendimento informada
         * @param OrigemRendimento
         */
        static Boolean contaVagaOrigemRendimento(Enum origem) {
            return getContaVagaOrigemRendimento(origem)
        }

        public static Boolean getRecebeDecimoTerceiro() {
            DadosFuncionario object = new DadosFuncionario();
            return object.data.funcionario.recebeDecimoTerceiro;
        }
        /**
         * Se funcionário recebe décimo terceiro, conforme cadastro de cargos
         */
        static Boolean recebeDecimoTerceiro = getRecebeDecimoTerceiro();


        /**
         * Se funcionário recebe décimo terceiro, conforme cadastro de cargos do cargo de origem de rendimento informado
         * @param OrigemRendimento
         * @return
         */
        private static Boolean recebeDecimoTerceiroPorOrigem(Enum OrigemRendimento) {
            return true
        }

        private static BigDecimal getDiasDireitoFerias() {
            DadosFuncionario object = new DadosFuncionario();
            return object.data.funcionario.diasDireitoFerias;
        }
        /**
         * Dias de direiro conforme a configuração de férias
         */
        static BigDecimal diasDireitoFerias = getDiasDireitoFerias();


        /**
         * Meses para aquisição conforme a configuração de férias
         */
        static Long getMesesAquisicaoFerias() {
            DadosFuncionario object = new DadosFuncionario();
            return object.data.funcionario.mesesAquisicaoFerias;
        }
        private static Long mesesAquisicaoFerias = getMesesAquisicaoFerias()

        /**
         * Data da opção de FGTS
         */
        public static Date getDataOpcaoFgts() {
            DadosFuncionario object = new DadosFuncionario();

            return object.data.funcionario.dataOpcaoFgts;


        }
        static Date dataOpcaoFgts = getDataOpcaoFgts();

        /**
         * Data final do vínculo temporário, para data de rescisão use funcionario.dataRescisao
         */
        private static Date getDataFimVinculo() {
            DadosFuncionario object = new DadosFuncionario();
            return object.data.funcionario.dataFimVinculo

        }
        static Date dataFimVinculo = getDataFimVinculo();


        public static BigDecimal getSalario() {
            DadosFuncionario object = new DadosFuncionario();
            return object.data.funcionario.salario;
        }
        /**
         * Salário do funcionário
         */
        private BigDecimal salario = getSalario();


        /**
         * Coeficente do nível salarial
         */
        private static BigDecimal nivelSalarialCoeficiente = BigDecimal.valueOf(0.1);

        /**
         * Unidade de pagamento
         */
        public static Enum getUnidadePagamento() {
            DadosFuncionario object = new DadosFuncionario();
            String value = object.data.funcionario.unidadePagamento

            UnidadePagamento convertedEnumValue = value as UnidadePagamento
            return convertedEnumValue
        }
        static unidadePagamento = getUnidadePagamento();

        /**
         * Quantidade de horas semanais
         */
        private static BigDecimal getQuantidadeHorasSemana() {
            DadosFuncionario object = new DadosFuncionario();
            return object.data.funcionario.quantidadeHorasSemana
        }
        static BigDecimal quantidadeHorasSemana = getQuantidadeHorasSemana();

        /**
         * Coeficente do nível salarial conforme origem de rendimento
         * @param OrigemRendimento
         * @return
         */
        private static BigDecimal nivelSalarialCoeficienteOrigemRendimento(Enum OrigemRendimento) {
            return BigDecimal.valueOf(0.1)
        }

        /**
         * Plano salarial
         */
        private static String getPlanoSalarial() {
            DadosFuncionario object = new DadosFuncionario();
            return object.data.funcionario.planoSalarial
        }
        static String planoSalarial = getPlanoSalarial();

        /**
         * Se é optante do FGTS
         */
        public static Boolean getOptanteFgts() {
            DadosFuncionario object = new DadosFuncionario();
            return object.data.funcionario.optanteFgts
        }
        static Boolean optanteFgts = getOptanteFgts();

        /**
         * Data base
         */
        public static Date getDataBase() {
            DadosFuncionario object = new DadosFuncionario();
            return object.data.funcionario.dataBase;

        }
        private static Date dataBase = getDataBase();

        /**
         * Sindicato do funcionário
         */
        private static String getSindicato() {
            DadosFuncionario object = new DadosFuncionario();
            return object.data.funcionario.sindicato
        }
        static String sindicato = getSindicato();

        /**
         * Responsabilidade do pagamento
         */
        public static Enum getResponsabilidadePagamento() {
            DadosFuncionario object = new DadosFuncionario();
            def value = object.data.funcionario.responsabilidadePagamento

            ResponsabilidadePagamento convertedEnumValue = value as ResponsabilidadePagamento
            return convertedEnumValue

            return responsabilidadePagamento
        }

        static Enum responsabilidadePagamento = getResponsabilidadePagamento();


        /**
         * Nível salarial
         */
        static String getNivelSalarial() {
            DadosFuncionario object = new DadosFuncionario();
            return object.data.funcionario.nivelSalarial
        }
        static String nivelSalarial = getNivelSalarial();

        /**
         * Se é estatutário
         */
        private static Boolean getEstatutario() {
            DadosFuncionario object = new DadosFuncionario();
            return object.data.funcionario.estatutario
        }

        static Boolean estatutario = getEstatutario()

        /**
         * Data da rescisção, se não existir retorna null
         */
        static Date getDataRescisao() {
            DadosFuncionario object = new DadosFuncionario();
            return object.data.funcionario.dataRescisao;

        }
        static Date dataRescisao = getDataRescisao();

        /**
         * Nível salarial conforme origem de rendimento
         * @param OrigemRendimento
         * @return
         */
        private static String getNivelSalarialOrigemRendimento(Enum origemRendimento) {
            DadosFuncionario object = new DadosFuncionario();
            if (origemRendimento.toString() == "CARGO_COMISSIONADO") {
                return object.data.funcionario.nivelSalarialComissionado
            }
            return object.data.funcionario.nivelSalarial
        }

        static nivelSalarialOrigemRendimento(Enum origemRendimento) {
            return getNivelSalarialOrigemRendimento(origemRendimento)
        }

        /**
         * Salário conforme origem de rendimento
         * @param OrigemRendimento
         * @return
         */
        private static BigDecimal getSalarioOrigemRendimento(Enum origemRendimento) {
            DadosFuncionario object = new DadosFuncionario();
            if (origemRendimento.toString() == "CARGO_COMISSIONADO") {
                return object.data.funcionario.salarioComissionado
            }
            return object.data.funcionario.salario
        }

        static BigDecimal salarioOrigemRendimento(Enum origemRendimento) {
            return getSalarioOrigemRendimento(origemRendimento);
        }

        /**
         * Vínculo empregatício do funcionário
         */
        private static String getVinculoEmpregaticio() {
            DadosFuncionario object = new DadosFuncionario();
            return object.data.funcionario.vinculoEmpregaticio
        }
        static String vinculoEmpregaticio = getVinculoEmpregaticio();

        /**
         * Grupo funcional
         */
        private static String getGrupoFuncional() {
            DadosFuncionario object = new DadosFuncionario();
            return object.data.funcionario.grupoFuncional
        }
        private static String grupoFuncional = getGrupoFuncional();

        /**
         * Cargo do funcionário (descrição)
         */
        public static String getCargo() {
            DadosFuncionario object = new DadosFuncionario();
            return object.data.funcionario.cargo
        }
        static String cargo = getCargo();

        /**
         * Data de admissão
         */
        public static Date getDataAdmissao() {
            DadosFuncionario object = new DadosFuncionario();
            return object.data.funcionario.dataAdmissao;


        }
        static Date dataAdmissao = getDataAdmissao();

        /**
         * Se possui previdência
         * @param TipoPrevidencia
         * @return verdadeiro ou falso
         */
        private static Boolean getPossuiPrevidencia(Enum tipoPrevidencia) {
            DadosFuncionario object = new DadosFuncionario();
            String tipo = object.data.funcionario.previdencia;
            if (tipo == "CELETISTA") return false
            if (tipoPrevidencia.equals(tipo as TipoPrevidencia)) {
                return true
            }
            return false;
        }

        static boolean possuiPrevidencia(Enum TipoPrevidencia) {
            return getPossuiPrevidencia(TipoPrevidencia)
        }

        /**
         * Se possui previdência federal
         */
        static Boolean getPossuiPrevidenciaFederal() {
            DadosFuncionario object = new DadosFuncionario();
            String tipo = object.data.funcionario.previdencia;
            if (tipo.equals("CELETISTA")) {
                return true
            }
            return false;
        }

        static Boolean possuiPrevidenciaFederal = getPossuiPrevidenciaFederal();

        /**
         * Tipo de admissão
         */
        public static Enum getTipoAdmissao() {
            DadosFuncionario object = new DadosFuncionario();
            String value = object.data.funcionario.tipoAdmissao

            TipoAdmissao convertedEnumValue = value as TipoAdmissao
            return convertedEnumValue
        }

        static Enum tipoAdmissao = getTipoAdmissao();

        /**
         * Quantidade de horas mensais
         */
        public static BigDecimal getQuantidadeHorasMes() {
            DadosFuncionario object = new DadosFuncionario();
            return object.data.funcionario.quantidadeHorasMes
        }

        static BigDecimal quantidadeHorasMes = getQuantidadeHorasMes();

        /**
         * Cargo conforme a origem do rendimento
         * @param OrigemRendimento
         * @return um cargo
         */
        private static String getCargoOrigemRendimento(Enum origemRendimento) {
            DadosFuncionario object = new DadosFuncionario();
            if (origemRendimento.toString() == "CARGO_COMISSIONADO") {
                return object.data.funcionario.cargoComissionado
            }
            return object.data.funcionario.cargo
        }

        static String cargoOrigemRendimento(Enum OrigemRendimento) {
            return getCargoOrigemRendimento(OrigemRendimento)
        }
    }


}



