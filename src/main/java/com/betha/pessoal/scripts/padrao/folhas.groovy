package com.betha.pessoal.scripts.padrao

import com.betha.pessoal.scripts.padrao.enums.EncargosSociaisFpas
import com.betha.pessoal.scripts.utils

import static com.betha.pessoal.scripts.padrao.enums.*;
import static com.betha.pessoal.scripts.padrao.enums.*
import static com.betha.pessoal.scripts.utils.*

class Folhas {
    Object dadosFolha = getFile("folhas");
    class folhas {
        static buscaFolhas() {
            Folhas dados = new Folhas()
            ArrayList folhas = new ArrayList(dados.dadosFolha as Collection)
            Date incioCompetencia = startOfMonth(Calculo.calculo.competencia)

            return folhas.findAll { it.competencia == incioCompetencia }
        }
    }

    class folhasPeriodo {

        static buscaFolhas() {
            Folhas dados = new Folhas()
            ArrayList folhas = new ArrayList(dados.dadosFolha as Collection)
            Date incioCompetencia = startOfMonth(Calculo.calculo.competencia)

            return folhas.findAll { it.competencia == incioCompetencia }
        }

        static buscaFolhasProcessamento(Enum tipoProcessamento) {
            Folhas dados = new Folhas()
            ArrayList folhas = new ArrayList(dados.dadosFolha as Collection)
            Date incioCompetencia = startOfMonth(Calculo.calculo.competencia)

            return folhas.findAll {
                it.competencia == incioCompetencia && getEnumValue(TipoProcessamento, it.tipoProcessamento).equals(tipoProcessamento)
            }
        }

        static buscaFolhasInternas(Enum tipoProcessamento) {
            Folhas dados = new Folhas()
            ArrayList folhas = new ArrayList(dados.dadosFolha as Collection)
            Date incioCompetencia = startOfMonth(Calculo.calculo.competencia)

            return folhas.findAll {
                it.competencia == incioCompetencia && !it.folhaPagamento && getEnumValue(TipoProcessamento, it.tipoProcessamento).equals(tipoProcessamento)
            }
        }
    }


    class folha {
        static Date getCompetencia() {
            Folhas dados = new Folhas()
            ArrayList folhas = new ArrayList(dados.dadosFolha as Collection)
            Date incioCompetencia = startOfMonth(Calculo.calculo.competencia)
            Object folhaAtual = folhas.find { it.competencia == incioCompetencia }

            return folhaAtual.competencia
        }

        static Date competencia = getCompetencia()

        static long getDiasGozo() {
            Folhas dados = new Folhas()
            ArrayList folhas = new ArrayList(dados.dadosFolha as Collection)
            Date incioCompetencia = startOfMonth(Calculo.calculo.competencia)
            Object folhaAtual = folhas.find { it.competencia == incioCompetencia }

            return folhaAtual.diasGozo
        }

        static long diasgozo = getDiasGozo()

        static long getDiasAbono() {
            Folhas dados = new Folhas()
            ArrayList folhas = new ArrayList(dados.dadosFolha as Collection)
            Date incioCompetencia = startOfMonth(Calculo.calculo.competencia)
            Object folhaAtual = folhas.find { it.competencia == incioCompetencia }
            return folhaAtual.diasAbono
        }

        static Date getDataPagamento() {
            Folhas dados = new Folhas()
            ArrayList folhas = new ArrayList(dados.dadosFolha as Collection)
            Date incioCompetencia = startOfMonth(Calculo.calculo.competencia)
            Object folhaAtual = folhas.find { it.competencia == incioCompetencia }
            return folhaAtual.dataPagamento
        }

        static Boolean getFolhaPagamento() {
            Folhas dados = new Folhas()
            ArrayList folhas = new ArrayList(dados.dadosFolha as Collection)
            Date incioCompetencia = startOfMonth(Calculo.calculo.competencia)
            Object folhaAtual = folhas.find { it.competencia == incioCompetencia }
            return folhaAtual.folhaPagamento
        }

        static Enum getTipoProcessamento() {
            Folhas dados = new Folhas()
            ArrayList folhas = new ArrayList(dados.dadosFolha as Collection)
            Date incioCompetencia = startOfMonth(Calculo.calculo.competencia)
            Object folhaAtual = folhas.find { it.competencia == incioCompetencia }
            String value = folhaAtual.tipoProcessamento
            TipoProcessamento enumValue = value as TipoProcessamento
            return enumValue

        }

        static Enum getSubTipoProcessamento() {
            Folhas dados = new Folhas()
            ArrayList folhas = new ArrayList(dados.dadosFolha as Collection)
            Date incioCompetencia = startOfMonth(Calculo.calculo.competencia)
            Object folhaAtual = folhas.find { it.competencia == incioCompetencia }
            String value = folhaAtual.subTipoProcessamento
            SubTipoProcessamento enumValue = value as SubTipoProcessamento
            return enumValue
        }

        static BigDecimal getTotalBruto() {
            Folhas dados = new Folhas()
            ArrayList folhas = new ArrayList(dados.dadosFolha as Collection)
            Date incioCompetencia = startOfMonth(Calculo.calculo.competencia)
            Object folhaAtual = folhas.find { it.competencia == incioCompetencia }
            return folhaAtual.totalBruto
        }

        static BigDecimal getTotalLiquido() {
            Folhas dados = new Folhas()
            ArrayList folhas = new ArrayList(dados.dadosFolha as Collection)
            Date incioCompetencia = startOfMonth(Calculo.calculo.competencia)
            Object folhaAtual = folhas.find { it.competencia == incioCompetencia }
            return folhaAtual.totalLiquido
        }

        static BigDecimal getTotalDesconto() {
            Folhas dados = new Folhas()
            ArrayList folhas = new ArrayList(dados.dadosFolha as Collection)
            Date incioCompetencia = startOfMonth(Calculo.calculo.competencia)
            Object folhaAtual = folhas.find { it.competencia == incioCompetencia }
            return folhaAtual.totalDesconto
        }
        static long diasAbono = getDiasAbono()

        static Date dataPagamento = getDataPagamento()

        static Boolean folhaPagamento = getFolhaPagamento()

        static Enum tipoProcessamento = getTipoProcessamento()

        static Enum subTipoProcessamento = getSubTipoProcessamento()

        static BigDecimal totalBruto = getTotalBruto()

        static BigDecimal totalLiquido = getTotalLiquido()

        static BigDecimal totalDesconto = getTotalDesconto()


    }


}

