package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Bases
import com.betha.pessoal.scripts.padrao.Calculo.calculo
import com.betha.pessoal.scripts.padrao.EncargosSociais;
import com.betha.pessoal.scripts.padrao.evento
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/
Funcoes.somenteFuncionarios()
def vaux = Lancamentos.valor(evento)

boolean possuiOcorrencia
if (funcionario.ocorrenciaSefip.toString().equals('EXPOSTO_APOSENTADORIA_25_ANOS')) {
    possuiOcorrencia = true
}
if (vaux < 0 && !possuiOcorrencia) {
    suspender "Cálculo do evento ${evento.codigo} cancelado"
}

def base = 0;
valorReferencia = evento.taxa

valorCalculado = EncargosSociais.salarioMinimo * valorReferencia / 100

if (calculo.tipoProcessamento == TipoProcessamento.DECIMO_TERCEIRO_SALARIO) {
    def vcal = valorCalculado * Funcoes.avos13(12 ) / 12
    if (calculo.subTipoProcessamento == SubTipoProcessamento.ADIANTAMENTO) {
        vcal *= evento.getTaxa(26) / 100
    }
    if (vcal > 0) {
        valorCalculado = vcal
    } else {
        valorCalculado = 0
    }
} else {
    base = Bases.valor(Bases.PAGAPROP)
    if (base > 0) {
        Bases.compor(valorCalculado, Bases.HORAEXTRA, Bases.SALAFAM)
    }
    valorCalculado = Funcoes.calcprop(valorCalculado, base)
}
Bases.compor(valorCalculado,
        Bases.SALBASE,
        Bases.FGTS,
        Bases.IRRF,
        Bases.INSS,
        Bases.PREVEST,
        Bases.FUNDASS,
        Bases.FUNDOPREV,
        Bases.MEDIAUXMAT
)
