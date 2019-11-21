package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.AvisoPrevio
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Bases
import com.betha.pessoal.scripts.padrao.Calculo.calculo
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*

/**a declaração de dados do evento pode ser removida, utilize apenas quando necessário testar alguma informação diferente **/
def evento = [codigo: 94, taxa: 0, tipo: TipoEvento.VENCIMENTO, unidade: UnidadeEvento.AUTOMATICO];

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

Funcoes.somenteFuncionarios()

def avisoinden = Funcoes.avisoPrevioIndenizado()

if (!avisoinden) {
    suspender "Sem aviso prévio indenizado"
}

def diasavisoinden = AvisoPrevio.quantidadeDiasAviso
def vvar = Lancamentos.valor(evento)

if (vvar >= 0) {
    valorReferencia = vvar
    valorCalculado = vvar
} else {
    if (diasavisoinden > 0) {
        def diasM = 30
        if (UnidadePagamento.HORISTA.equals(funcionario.unidadePagamento) || UnidadePagamento.DIARISTA.equals(funcionario.unidadePagamento)) {
            diasM = calculo.quantidadeDiasCompetencia
        }
        valorCalculado = (funcionario.salario / diasM) * diasavisoinden
    } else {
        valorCalculado = funcionario.salario
    }
}

if (valorCalculado > 0) {
    Bases.compor(valorCalculado, Bases.INSS, Bases.FGTSAVISO)
}