package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Bases
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*

/**a declaração de dados do evento pode ser removida, utilize apenas quando necessário testar alguma informação diferente **/
def evento = [codigo: 25, taxa: 0, tipo: TipoEvento.VENCIMENTO, unidade: UnidadeEvento.AUTOMATICO];

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

boolean recebeDecimoTerceiro = Funcoes.recebeDecimoTerceiro()

if (!recebeDecimoTerceiro) {
    suspender "Cálculo do evento ${evento.codigo} cancelado"
}
if (!Funcoes.permitecalc13integral()) {
    suspender "Cálculo do evento ${evento.codigo} cancelado"
}
double vlrreffgts = 0;
if (Funcoes.dtrescisao()) {
    valorReferencia = Funcoes.avos13(Datas.mes(calculo.competencia))
    vlrreffgts = Funcoes.avos13(Datas.mes(calculo.competencia), true)
} else {
    Funcoes.avos13(12, true)
    valorReferencia = Funcoes.avos13(12)
    vlrreffgts = Funcoes.avos13(12, true)
}

if ((valorReferencia + vlrreffgts) == 0) {
    suspender "Sem avos de direito"
}


vvar = Lancamentos.valor(evento)
if (vvar >= 0) {
    valorCalculado = vvar;
    vlrcalcfgts = vvar * vlrreffgts / valorReferencia;
} else {
    def salario = Funcoes.remuneracao(matricula.tipo).valor
    valorCalculado = salario * valorReferencia / 12;
    vlrcalcfgts = salario * vlrreffgts / 12
}

Bases.compor(valorCalculado,
        Bases.IRRF13,
        Bases.INSS13,
        Bases.PREVEST13,
        Bases.FUNDASS13,
        Bases.FUNDPREV13,
        Bases.COMPHORAMES,
        Bases.FUNDFIN13)

Bases.compor(valorCalculado, Bases.FGTS13)


imprimir valorCalculado