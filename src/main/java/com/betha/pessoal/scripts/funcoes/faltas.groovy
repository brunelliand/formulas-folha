package com.betha.pessoal.scripts.funcoes

import com.betha.pessoal.scripts.padrao.Afastamentos
import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas;
import com.betha.pessoal.scripts.padrao.Matricula.matricula;
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario;
import com.betha.pessoal.scripts.padrao.Calculo.calculo;
import static com.betha.pessoal.scripts.padrao.Calculo.evento;
import static com.betha.pessoal.scripts.padrao.enums.*;

/**
 * Busca o número de faltas em determinado período especificado. O número de faltas é buscado independentemente de ter sido processado o cálculo na época da falta, ou seja, mesmo que não exista cálculo nas competências das faltas lançadas.
 */
class Faltas {

    public static BigDecimal getFaltas(def justificada, Date dataCompetenciaInicial, Date dataCompetenciaFinal, def motivo, def abonada) {
        Binding binding = new Binding()

        /** Dentro deste bloco deverá esta o conteúdo do script, o mesmo do cadastro de funções para fórmulas **/

        def _dataCompetenciaInicial;
        def _dataCompetenciaFinal;
        def _motivo;
        def _abonada;
        def _justificada;

        if (binding.variables["dataCompetenciaInicial"]) {
            _dataCompetenciaInicial = dataCompetenciaInicial
        } else {
            _dataCompetenciaInicial = Datas.data(Datas.ano(calculo.competencia), Datas.mes(calculo.competencia), 1)
        }

        if (binding.variables["dataCompetenciaFinal"]) {
            _dataCompetenciaFinal = dataCompetenciaFinal
        } else {
            _dataCompetenciaFinal = Datas.data(Datas.ano(calculo.competencia), Datas.mes(calculo.competencia), calculo.quantidadeDiasCompetencia)
        }

        if (binding.variables["motivo"]) {
            _motivo = motivo
        } else {
            _motivo = 0
        }

        if (binding.variables["abonada"]) {
            _abonada = abonada
        } else {
            _abonada = 0
        }

        if (binding.variables["justificada"]) {
            _justificada = justificada
        } else {
            _justificada = 0
        }


        def classificacao = ClassificacaoTipoAfastamento.FALTA
        def faltas = Afastamentos.buscaPorPeriodo(_dataCompetenciaInicial, _dataCompetenciaFinal, classificacao)

        if (faltas.size() == 0) return 0;

        double dias;
        double horas;
        faltas.findAll {
            _justificada == 0 ? true : it.tipo.justificado == _justificada &&
                    _abonada == 0 ? true : it.abonar == _abonada &&
                    _motivo == 0 ? true : it.tipo.descricao == _motivo &&
                    falta.abonar ? it.competenciaDesconto == calculo.competencia : true
        }.each { falta ->
            if (falta.unidade.toString() == 'DIAS') {
                dias += (Datas.diferencaDias(falta.inicio, falta.fim) + 1)
            } else {
                horas += falta.quantidade
            }
        }

        if ((dias + horas) <= 0) {
            return 0
        }

        imprimir 'Dias ' + dias
        imprimir 'Horas ' + horas

        if (dias > 0) {
            def horasDia = 0;
            def horasMes = Funcoes.remuneracao(matricula.tipo).quantidadeHorasMes
            if (Funcoes.remuneracao(matricula.tipo).unidade != UnidadePagamento.DIARISTA) {
                horasDia = horasMes / 30
            } else {
                horasDia = horasMes / calculo.quantidadeDiasCompetencia
            }
            horas = horas + (dias * horasDia)
        }

        return horas


        /** FINAL **/
    }
}
