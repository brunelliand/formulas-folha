package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.Eventos
import com.betha.pessoal.scripts.padrao.Calculo.calculo
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*

/**a declaração de dados do evento pode ser removida, utilize apenas quando necessário testar alguma informação diferente **/
def evento = [codigo: 67, taxa: 0, tipo: TipoEvento.VENCIMENTO, unidade: UnidadeEvento.AUTOMATICO];

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

if (matricula.tipo == TipoMatricula.FUNCIONARIO) {
    def afas = Funcoes.afasacidtrab() + Funcoes.afasservmil() + Funcoes.afasauxdoenc() + Funcoes.afaslicsvenc()
    if (afas > 0) {
        suspender "Cálculo do evento cancelado"
    }

    int tipo = 1 //1-sal. contratual, 2-sal. contratual + adicionais
    def vvar = Lancamentos.valor(evento)

    if (vvar > 0) {
        valorCalculado = vvar
    } else {
        BigDecimal salario = Funcoes.remuneracao(matricula.tipo).valor
        def valorCompetencia = 0;

        def competencia = Datas.data(Datas.ano(calculo.competencia), Datas.mes(calculo.competencia), 1)
        if (tipo == 2) {
            def competenciaAnterior = Datas.removeMeses(competencia, 1)
            def eventos = [11, 12, 13, 16, 17, 18, 19, 20, 21]
            for (e in eventos) {
                valorCompetencia += Eventos.valorCalculado(e, TipoValor.CALCULADO, TipoProcessamento.MENSAL, SubTipoProcessamento.INTEGRAL, competenciaAnterior)
            }
        }
        salario += valorCompetencia - Funcoes.acumula(67, TipoValor.CALCULADO, competencia, competencia, ['MENSAL_ADIANTAMENTO'])
        if (salario > 0) {
            valorCalculado = salario * evento.taxa / 100
        }
    }
}
