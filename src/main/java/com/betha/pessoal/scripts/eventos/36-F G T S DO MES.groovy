package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.Eventos
import com.betha.pessoal.scripts.padrao.Calculo.calculo
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Bases
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*

/**a declaração de dados do evento pode ser removida, utilize apenas quando necessário testar alguma informação diferente **/
def evento = [codigo: 36, taxa: 8, tipo: TipoEvento.VENCIMENTO, unidade: UnidadeEvento.AUTOMATICO];

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/
def optanteFgts = Funcoes.optanteFgts(matricula.tipo)

if (!optanteFgts || TipoMatricula.ESTAGIARIO.equals(matricula.tipo)) {
    suspender "Cálculo do evento ${evento.codigo} cancelado"
}

def vaux = Lancamentos.valor(evento)
if (vaux >= 0) {
    valorCalculado = vaux;
} else {
    double fgtsaux
    vaux = 0
    double baseaux
    baseaux += Bases.valorCalculado(Bases.FGTS, TipoProcessamento.FERIAS, SubTipoProcessamento.INTEGRAL)
    baseaux += Bases.valorCalculado(Bases.FGTS13, TipoProcessamento.DECIMO_TERCEIRO_SALARIO, SubTipoProcessamento.INTEGRAL)
    baseaux += Bases.valorCalculado(Bases.FGTS13, TipoProcessamento.DECIMO_TERCEIRO_SALARIO, SubTipoProcessamento.ADIANTAMENTO)

    if (Bases.valor(Bases.FGTS13) > 0) {
        baseaux += Bases.valor(Bases.FGTS)
    }

    if (baseaux > 0) {
        fgtsaux += Eventos.valorCalculado(86, TipoValor.CALCULADO, TipoProcessamento.FERIAS, SubTipoProcessamento.INTEGRAL)
        fgtsaux += Eventos.valorCalculado(37, TipoValor.CALCULADO, TipoProcessamento.DECIMO_TERCEIRO_SALARIO, SubTipoProcessamento.INTEGRAL)
        fgtsaux += Eventos.valorCalculado(37, TipoValor.CALCULADO, TipoProcessamento.DECIMO_TERCEIRO_SALARIO, SubTipoProcessamento.ADIANTAMENTO)
        fgtsaux += Eventos.valor(37)
    }
    def base = Bases.valor(Bases.FGTS)

    if (!(TipoProcessamento.MENSAL.equals(calculo.tipoProcessamento) && SubTipoProcessamento.COMPLEMENTAR.equals(calculo.subTipoProcessamento)) && (baseaux > 0)) {
        base += baseaux;
    }
    if (funcionario.categoriaSefipVinculo.toString() == 'MENOR_APRENDIZ') {
        valorReferencia = 2
    } else {
        valorReferencia = evento.taxa
    }

    vaux = base * valorReferencia / 100;
    if (!(TipoProcessamento.MENSAL.equals(calculo.tipoProcessamento) && SubTipoProcessamento.COMPLEMENTAR.equals(calculo.subTipoProcessamento))) {
        vaux = vaux - fgtsaux;
    }
    valorCalculado = vaux.trunc(2)
}

