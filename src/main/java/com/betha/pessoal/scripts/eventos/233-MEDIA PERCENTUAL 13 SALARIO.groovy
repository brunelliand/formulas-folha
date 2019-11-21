package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.Eventos
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Bases
import com.betha.pessoal.scripts.padrao.Calculo.calculo
import com.betha.pessoal.scripts.padrao.MediasVantagens.mediaVantagem
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*


/**a declaração de dados do evento pode ser removida, utilize apenas quando necessário testar alguma informação diferente **/
def evento = [codigo: 233, taxa: 0, tipo: TipoEvento.VENCIMENTO, unidade: UnidadeEvento.AUTOMATICO];

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

boolean recebeDecimoTerceiro = Funcoes.recebeDecimoTerceiro()

if (!recebeDecimoTerceiro) {
    suspender "Cálculo do evento ${evento.codigo} cancelado"
}

if (!Funcoes.permitecalc13integral()) {
    suspender "Cálculo do evento ${evento.codigo} cancelado"
}
def vlrreffgts;
def vlrrefproc;
if (calculo.dataRescisao && TipoProcessamento.RESCISAO.equals(calculo.tipoProcessamento)) {
    vlrrefproc = Funcoes.avos13(calculo.competencia.mes)
    vlrreffgts = Funcoes.avos13(calculo.competencia.mes, true)
} else {
    vlrrefproc = Funcoes.avos13(12)
    vlrreffgts = Funcoes.avos13(12, true)
}

def vvar = Lancamentos.valor(evento)

if (vvar >= 0) {
    valorCalculado = vvar
    valorReferencia = vvar
    vlrcalcfgts = vvar * vlrreffgts / vlrrefproc
} else {
    valorReferencia = Funcoes.avos13(12)

    imprimir ' Avos para media horas : ' + valorReferencia
    def valorMedia = mediaVantagem.calcular(valorReferencia);
    valorCalculado = valorMedia;
}

Bases.compor(valorCalculado, Bases.FGTS13)



