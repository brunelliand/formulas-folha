package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.Eventos
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Bases
import com.betha.pessoal.scripts.padrao.MediasVantagens.mediaVantagem
import com.betha.pessoal.scripts.padrao.PeriodosAquisitivos

import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*

/**a declaração de dados do evento pode ser removida, utilize apenas quando necessário testar alguma informação diferente **/
def evento = [codigo: 105, taxa: 0, tipo: TipoEvento.VENCIMENTO, unidade: UnidadeEvento.AUTOMATICO];

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

Funcoes.somenteFuncionarios()

def vaux = Lancamentos.valor(evento)
if (vaux > 0) {
    valorReferencia = vaux
    valorCalculado = vaux
} else {
    def base = Eventos.valor(106) + Eventos.valor(107) + Eventos.valor(236) //apenas para gerar dependência
    int vinf;
    double vger;
    double vtot;
    PeriodosAquisitivos.buscaVencidos().each { feriasvenc ->
        vinf++
        def diasferias = feriasvenc.saldo
        if (diasferias > 0) {
            def valorMedia = mediaVantagem.calcular(feriasvenc)
            vtot = valorMedia * diasferias / 30
            PeriodosAquisitivos.quitaPorRescisao(feriasvenc, 0, vtot, true)
            vger += vtot
        }
    }
    valorReferencia = vinf
    valorCalculado = vger

}

imprimir(valorReferencia)
imprimir(valorCalculado)
