package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.Eventos
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Bases
import com.betha.pessoal.scripts.padrao.Calculo.calculo
import com.betha.pessoal.scripts.padrao.MediasVantagens.mediaVantagem
import com.betha.pessoal.scripts.padrao.PeriodosAquisitivosDecimoTerceiro.periodoAquisitivoDecimoTerceiro

import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*

//somente para passar evento na média
//public interface MediasVantagensInfos{
//  public static final String CODIGO = 28
//}


/**a declaração de dados do evento pode ser removida, utilize apenas quando necessário testar alguma informação diferente **/
def evento = [codigo: 28, taxa: 0, tipo: TipoEvento.VENCIMENTO, unidade: UnidadeEvento.AUTOMATICO];

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

boolean recebeDecimoTerceiro = Funcoes.recebeDecimoTerceiro()

if (!recebeDecimoTerceiro) {
    suspender "Cálculo do evento ${evento.codigo} cancelado"
}

if (!TipoProcessamento.DECIMO_TERCEIRO_SALARIO.equals(calculo.tipoProcessamento) ||
        !SubTipoProcessamento.ADIANTAMENTO.equals(calculo.subTipoProcessamento)) {
    suspender "Calcular apenas para adiantamento de décimo terceiro salário"
}

boolean periodoEmAndamento;
periodoEmAndamento = periodoAquisitivoDecimoTerceiro.situacao.equals(SituacaoPeriodoAquisitivoDecimoTerceiro.EM_ANDAMENTO) ||
        periodoAquisitivoDecimoTerceiro.situacao.equals(SituacaoPeriodoAquisitivoDecimoTerceiro.ATRASADO)

if (!periodoEmAndamento) {
    suspender "Cálculo do evento ${evento.codigo} cancelado"
}

def vvar = Lancamentos.valor(evento)

if (vvar >= 0) {
    valorCalculado = vvar
    valorReferencia = vvar
} else {
    double base = Eventos.valor(223) + Eventos.valor(224) + Eventos.valor(238) //apenas para gerar dependencia
    valorReferencia = Funcoes.avos13(12)

    def valorMedia = mediaVantagem.calcular(valorReferencia);
    valorCalculado = valorMedia;
}

Bases.compor(valorCalculado, Bases.FGTS13)



