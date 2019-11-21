package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.*
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Bases
import com.betha.pessoal.scripts.padrao.Calculo.calculo
import com.betha.pessoal.scripts.padrao.Servidor.servidor
import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*

/**a declaração de dados do evento pode ser removida, utilize apenas quando necessário testar alguma informação diferente **/
def evento = [codigo: 243, taxa: 4, tipo: TipoEvento.DESCONTO, unidade: UnidadeEvento.AUTOMATICO]; //Configuracao do evento

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/


Funcoes.somenteFuncionarios()

if (!funcionario.possuiPrevidencia(TipoPrevidencia.FUNDO_FINANCEIRO)) {
    suspender "Apenas para fundo financeiro"
}
def baseprev = Bases.valor(Bases.FUNDFIN) - Eventos.valor(245)

if (baseprev == 0) {
    suspender "Sem valor de base"
}

def vvar = Lancamentos.valor(evento)
if (vvar >= 0) {
    valorReferencia = vvar
    valorCalculado = vvar
} else {
    valorReferencia = evento.taxa
    valorCalculado = baseprev * evento.taxa / 100
}

Bases.compor(valorCalculado, Bases.IRRF, Bases.ABATIRRF)
