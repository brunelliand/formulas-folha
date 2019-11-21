package com.betha.pessoal.scripts.padrao

import com.betha.pessoal.scripts.utils;

class EncargosSociais {
    Object data = utils.getFile("encargossociais");

    static getSalarioMinimo() {
        EncargosSociais encargos = new EncargosSociais();
        return encargos.data.salarioMinimo
    }
    static salarioMinimo = getSalarioMinimo();

    //SalarioFamilia.Celetista.buscaContribuicao

    class SalarioFamilia{
        class Celetista{
            static BigDecimal buscaContribuicao(BigDecimal valor, int coluna) {
                EncargosSociais encargos = new EncargosSociais();
                ArrayList tabela = new ArrayList(encargos.data.salarioFamiliaCeletista).sort { it.valor }

                def faixa
                for (i in tabela) {
                    faixa = i;
                    if (faixa.valor > valor) break

                }
                switch (coluna) {
                    case 1:
                        return faixa.valor
                    case 2:
                        return faixa.aliquota
                }
            }

            static BigDecimal buscaMaior(int coluna){
                return buscaContribuicao(BigDecimal.valueOf(99999999999), coluna)
            }
        }

        class Estatutario{
            static BigDecimal buscaContribuicao(BigDecimal valor, int coluna) {
                EncargosSociais encargos = new EncargosSociais();
                ArrayList tabela = new ArrayList(encargos.data.salarioFamiliaEstatutario).sort { it.valor }

                def faixa
                for (i in tabela) {
                    faixa = i;
                    if (faixa.valor > valor) break

                }
                switch (coluna) {
                    case 1:
                        return faixa.valor
                    case 2:
                        return faixa.aliquota
                }
            }

            static BigDecimal buscaMaior(int coluna){
                return buscaContribuicao(BigDecimal.valueOf(99999999999), coluna)
            }
        }

        class Especial{
            static BigDecimal buscaContribuicao(BigDecimal valor, int coluna) {
                EncargosSociais encargos = new EncargosSociais();
                ArrayList tabela = new ArrayList(encargos.data.salarioFamiliaEspecial).sort { it.valor }

                def faixa
                for (i in tabela) {
                    faixa = i;
                    if (faixa.valor > valor) break

                }
                switch (coluna) {
                    case 1:
                        return faixa.valor
                    case 2:
                        return faixa.aliquota
                }
            }

            static BigDecimal buscaMaior(int coluna){
                return buscaContribuicao(BigDecimal.valueOf(99999999999), coluna)
            }
        }


        class Aposentado{
            static BigDecimal buscaContribuicao(BigDecimal valor, int coluna) {
                EncargosSociais encargos = new EncargosSociais();
                ArrayList tabela = new ArrayList(encargos.data.salarioFamiliaAposentado).sort { it.valor }

                def faixa
                for (i in tabela) {
                    faixa = i;
                    if (faixa.valor > valor) break

                }
                switch (coluna) {
                    case 1:
                        return faixa.valor
                    case 2:
                        return faixa.aliquota
                }
            }

            static BigDecimal buscaMaior(int coluna){
                return buscaContribuicao(BigDecimal.valueOf(99999999999), coluna)
            }
        }




    }



    class IRRF {

        static getDeducaoPorDependente(){
            EncargosSociais encargos = new EncargosSociais()
            return encargos.data.deducaoPorDependente
        }

        static deducaoPorDependente = getDeducaoPorDependente()

        static getMinimoIrrfDarf(){
            EncargosSociais encargos = new EncargosSociais()
            return encargos.data.minimoIrrfDarf
        }

        static minimoIrrfDarf = getMinimoIrrfDarf()

        static BigDecimal buscaContribuicao(BigDecimal valor, int coluna) {
            EncargosSociais encargos = new EncargosSociais();
            ArrayList tabelaIrrf = new ArrayList(encargos.data.IRRF).sort { it.valor }

            def irrf
            for (i in tabelaIrrf) {
                irrf = i;
                if (irrf.valor > valor) break

            }
            switch (coluna) {
                case 1:
                    return irrf.valor
                case 2:
                    return irrf.aliquota
                case 3:
                    return irrf.deducao
            }

        }
        static BigDecimal buscaMaior(int coluna){
            return buscaContribuicao(BigDecimal.valueOf(99999999999), coluna)
        }

    }

    class RGPS {

        static Enum codigoFpas = com.betha.pessoal.scripts.padrao.enums.EncargosSociaisFpas.ORGAO_PODER_PUBLICO

        static BigDecimal buscaContribuicao(BigDecimal valor, int coluna) {
            EncargosSociais encargos = new EncargosSociais();
            ArrayList tabelaInss = new ArrayList(encargos.data.INSS).sort { it.valor }

            def inss
            for (i in tabelaInss) {
                inss = i;
                if (inss.valor > valor) break

            }
            switch (coluna) {
                case 1:
                    return inss.valor
                case 2:
                    return inss.aliquota
            }
        }

        static BigDecimal buscaMaior(int coluna){
            return buscaContribuicao(BigDecimal.valueOf(99999999999), coluna)
        }
    }

    class RPPS{
        class PrevidenciaEstadual {

            static BigDecimal getMinimo(){
                EncargosSociais encargos = new EncargosSociais();
                return encargos.data.minimo
            }

            static BigDecimal getMaximo(){
                EncargosSociais encargos = new EncargosSociais();
                return encargos.data.maximo
            }

            static BigDecimal maximo = getMaximo()
            static BigDecimal minimo = getMinimo()



            static BigDecimal buscaContribuicao(BigDecimal valor, int coluna) {
                EncargosSociais encargos = new EncargosSociais();
                ArrayList tabela = new ArrayList(encargos.data.previdenciaEstadual).sort { it.valor }

                def previdencia
                for (i in tabela) {
                    previdencia = i;
                    if (previdencia.valor > valor) break

                }
                switch (coluna) {
                    case 1:
                        return previdencia.valor
                    case 2:
                        return previdencia.aliquota
                }
            }

            static BigDecimal buscaMaior(int coluna){
                return buscaContribuicao(BigDecimal.valueOf(99999999999), coluna)
            }
        }

        class FundoAssistencia {

            static BigDecimal getMinimo(){
                EncargosSociais encargos = new EncargosSociais();
                return encargos.data.minimo
            }

            static BigDecimal getMaximo(){
                EncargosSociais encargos = new EncargosSociais();
                return encargos.data.maximo
            }

            static BigDecimal maximo = getMaximo()
            static BigDecimal minimo = getMinimo()



            static BigDecimal buscaContribuicao(BigDecimal valor, int coluna) {
                EncargosSociais encargos = new EncargosSociais();
                ArrayList tabela = new ArrayList(encargos.data.fundoAssistencia).sort { it.valor }

                def previdencia
                for (i in tabela) {
                    previdencia = i;
                    if (previdencia.valor > valor) break

                }
                switch (coluna) {
                    case 1:
                        return previdencia.valor
                    case 2:
                        return previdencia.aliquota
                }
            }

            static BigDecimal buscaMaior(int coluna){
                return buscaContribuicao(BigDecimal.valueOf(99999999999), coluna)
            }
        }

        class PrevidenciaPropria {

            static BigDecimal getMinimo(){
                EncargosSociais encargos = new EncargosSociais();
                return encargos.data.minimo
            }

            static BigDecimal getMaximo(){
                EncargosSociais encargos = new EncargosSociais();
                return encargos.data.maximo
            }

            static BigDecimal maximo = getMaximo()
            static BigDecimal minimo = getMinimo()



            static BigDecimal buscaContribuicao(BigDecimal valor, int coluna) {
                EncargosSociais encargos = new EncargosSociais();
                ArrayList tabela = new ArrayList(encargos.data.previdenciaPropria).sort { it.valor }

                def previdencia
                for (i in tabela) {
                    previdencia = i;
                    if (previdencia.valor > valor) break

                }
                switch (coluna) {
                    case 1:
                        return previdencia.valor
                    case 2:
                        return previdencia.aliquota
                }
            }

            static BigDecimal buscaMaior(int coluna){
                return buscaContribuicao(BigDecimal.valueOf(99999999999), coluna)
            }
        }



    }




}

