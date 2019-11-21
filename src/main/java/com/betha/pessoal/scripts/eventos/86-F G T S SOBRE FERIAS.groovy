package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.Eventos
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Bases
import com.betha.pessoal.scripts.padrao.evento
import com.betha.pessoal.scripts.padrao.Folhas.folha
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*


/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

if (!Funcoes.optanteFgts(matricula.tipo)) {
    suspender "Não é optante de FGTS"
}

if (folha.folhaPagamento) {
    def vaux = Lancamentos.valor(evento)
    if (vaux >= 0) {
        valorCalculado = vaux
    } else {
        double basefer = Bases.valorCalculado(Bases.FGTS, TipoProcessamento.FERIAS, SubTipoProcessamento.INTEGRAL)
        double fgtsfer; double fgts13a; double fgts13i; double base13a; double base13i;
        if (basefer > 0) {
            fgtsfer = Eventos.valorCalculado(evento.codigo, TipoValor.CALCULADO, TipoProcessamento.FERIAS, SubTipoProcessamento.INTEGRAL)
        }
        if (Funcoes.diasferias() >= 30) {
            base13a = Bases.valorCalculado(Bases.FGTS13, TipoProcessamento.DECIMO_TERCEIRO_SALARIO, SubTipoProcessamento.ADIANTAMENTO)
            base13i = Bases.valorCalculado(Bases.FGTS13, TipoProcessamento.DECIMO_TERCEIRO_SALARIO, SubTipoProcessamento.INTEGRAL)
            if (base13a > 0 || base13i > 0) {
                fgts13a = Eventos.valorCalculado(37, TipoValor.CALCULADO, TipoProcessamento.DECIMO_TERCEIRO_SALARIO, SubTipoProcessamento.ADIANTAMENTO)
                fgts13i = Eventos.valorCalculado(37, TipoValor.CALCULADO, TipoProcessamento.DECIMO_TERCEIRO_SALARIO, SubTipoProcessamento.INTEGRAL)
            }

        }

        double base = Bases.valor(Bases.FGTS) + basefer + base13a + base13i

        valorReferencia = evento.taxa

        vaux = base * valorReferencia / 100
        vaux = vaux - fgtsfer - fgts13a - fgts13i
        valorCalculado = vaux.trunc(2)


    }
}

imprimir(valorCalculado)