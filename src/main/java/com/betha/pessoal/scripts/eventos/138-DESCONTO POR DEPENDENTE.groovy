package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.EncargosSociais
import com.betha.pessoal.scripts.padrao.Eventos
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Bases
import com.betha.pessoal.scripts.padrao.Calculo.calculo
import com.betha.pessoal.scripts.padrao.Servidor.servidor
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*

/**a declaração de dados do evento pode ser removida, utilize apenas quando necessário testar alguma informação diferente **/
def evento = [codigo: 138, taxa: 0, tipo: TipoEvento.DESCONTO, unidade: UnidadeEvento.AUTOMATICO];

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

Funcoes.somenteFuncionarios()
if (Lancamentos.valor(evento) > 0) {
    valorCalculado = Lancamentos.valor(evento)
} else {
    def valor13 = Eventos.valor(25) + Eventos.valor(28) + Eventos.valor(29) + Eventos.valor(30)
    if ((calculo.tipoProcessamento == TipoProcessamento.DECIMO_TERCEIRO_SALARIO && calculo.subTipoProcessamento == SubTipoProcessamento.INTEGRAL && valor13 <= 0) ||
            ((calculo.tipoProcessamento == TipoProcessamento.MENSAL && (Bases.valor(Bases.PAGAPROP) <= 0 && matricula.tipo != TipoMatricula.AUTONOMO)) ||
                    (Bases.valor(Bases.SALBASE) <= 0 && matricula.tipo == TipoMatricula.AUTONOMO))) {
        suspender "Calculo cancelado"
    }
    valorReferencia = servidor.dependentesIrrf
    imprimir 'Dependentes ' + valorReferencia
    imprimir 'Desconto por dependente ' + EncargosSociais.IRRF.deducaoPorDependente
    valorCalculado = valorReferencia * EncargosSociais.IRRF.deducaoPorDependente
}
if (calculo.tipoProcessamento == TipoProcessamento.FERIAS) {
    Bases.compor(valorCalculado, Bases.IRRF, Bases.IRRFFER)
} else {
    Bases.compor(valorCalculado, Bases.IRRF, Bases.IRRF13, Bases.IRRFFERRESC)
}

imprimir(valorCalculado)