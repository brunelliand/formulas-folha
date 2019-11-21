package com.betha.pessoal.scripts.padrao

import com.betha.pessoal.scripts.utils
import com.betha.pessoal.scripts.padrao.Calculo.calculo;
import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas
import static com.betha.pessoal.scripts.padrao.enums.*;

import java.time.LocalDate;

class Afastamentos {
    Object data = utils.getFile("dadosMatricula");

    private static Enum getClassificacao(String classificacao) {
        ClassificacaoTipoAfastamento value = classificacao as ClassificacaoTipoAfastamento
        return value
    }

    private static ArrayList getBuscaPorPeriodo(Date dataInicial, Date dataFinal, Enum[] classificacoes) {
        Afastamentos object = new Afastamentos();

        Set<ClassificacaoTipoAfastamento> classificacoesSet = new HashSet<>();
        if (classificacoes != null) {
            classificacoesSet.addAll(Arrays.asList(classificacoes));
        }


        Date inicioCompetenciaDate =  Datas.data(Datas.ano(calculo.competencia), Datas.mes(calculo.competencia), 1)
        Date fimCompetenciaDate =  calculo.competencia

        LocalDate inicioCompetencia = inicioCompetenciaDate.toLocalDate();
        LocalDate fimCompetencia = fimCompetenciaDate.toLocalDate();



        def afastamentos = object.data.afastamentos;
        afastamentos = new ArrayList(afastamentos)

        afastamentos = afastamentos.findAll { afastamento ->
            classificacoesSet.contains(getClassificacao(afastamento.tipo.classificacao)) && !afastamento.inicio.toLocalDate().isAfter(fimCompetencia) && (afastamento.fim == null || !afastamento.fim.toLocalDate().isBefore(inicioCompetencia))
        }

        def afastamentosDaCompetencia = [];
        afastamentos.each { it ->
            LinkedHashMap item = it as LinkedHashMap;
            if (item.inicio < inicioCompetenciaDate) {
                item.inicio = inicioCompetenciaDate
            }
            if (item.fim > fimCompetenciaDate) {
                item.fim = fimCompetenciaDate
            }
            afastamentosDaCompetencia.push(item)
        }
        return afastamentosDaCompetencia
    }

    static ArrayList buscaPorPeriodo(Date dataInicial, Date dataFinal, Enum[] ClassificacaoTipoAfastamento) {

        return getBuscaPorPeriodo(dataInicial, dataFinal, *ClassificacaoTipoAfastamento)
    }

    private static ArrayList getBuscaPorPeriodoCompetencia(Enum[] classificacoes) {
        Afastamentos object = new Afastamentos();

        Set<ClassificacaoTipoAfastamento> classificacoesSet = new HashSet<>();
        if (classificacoes != null) {
            classificacoesSet.addAll(Arrays.asList(classificacoes));
        }

        Date inicioCompetenciaDate =  Datas.data(Datas.ano(calculo.competencia), Datas.mes(calculo.competencia), 1)
        Date fimCompetenciaDate =  calculo.competencia

        LocalDate inicioCompetencia = inicioCompetenciaDate.toLocalDate();
        LocalDate fimCompetencia = fimCompetenciaDate.toLocalDate();

        def afastamentos = object.data.afastamentos;
        afastamentos = new ArrayList(afastamentos)

        afastamentos = afastamentos.findAll { afastamento ->
            classificacoesSet.contains(getClassificacao(afastamento.tipo.classificacao)) && !afastamento.inicio.toLocalDate().isAfter(fimCompetencia) && (afastamento.fim == null || !afastamento.fim.toLocalDate().isBefore(inicioCompetencia))
        }

        def afastamentosDaCompetencia = [];
        afastamentos.each { it ->
            LinkedHashMap item = it as LinkedHashMap;
            if (item.inicio < inicioCompetenciaDate) {
                item.inicio = inicioCompetenciaDate
            }
            if (item.fim > fimCompetenciaDate) {
                item.fim = fimCompetenciaDate
            }
            afastamentosDaCompetencia.push(item)
        }
        return afastamentosDaCompetencia

    }

    static ArrayList buscaPorPeriodo(Enum[] ClassificacaoTipoAfastamento) {

        return getBuscaPorPeriodoCompetencia(*ClassificacaoTipoAfastamento)
    }

}

