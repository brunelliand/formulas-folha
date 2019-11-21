package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.EncargosSociais
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
def evento = [codigo: 51, taxa: 0, tipo: TipoEvento.VENCIMENTO, unidade: UnidadeEvento.AUTOMATICO];

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

if (TipoMatricula.FUNCIONARIO.equals(matricula.tipo) && TipoMatricula.AUTONOMO.equals(matricula.tipo)) {
    suspender 'calculo apenas para funcionarios e autonomos'
};

double vaux;
double baseprev;

if (!funcionario.possuiPrevidenciaFederal) {
    suspender 'matricula nao tem previdencia federal '
}
def baseexcedinss = 0;
baseprev = Bases.valor(Bases.INSS13) + Bases.valor(Bases.INSSOUTRA13) + Eventos.valor(200);
def basefolhaatual = baseprev;

if (TipoProcessamento.MENSAL.equals(calculo.tipoProcessamento)) {
    baseprev += Bases.valorCalculado(Bases.INSS13, TipoProcessamento.DECIMO_TERCEIRO_SALARIO, SubTipoProcessamento.INTEGRAL) +
            Bases.valorCalculado(Bases.INSSOUTRA13, TipoProcessamento.DECIMO_TERCEIRO_SALARIO, SubTipoProcessamento.INTEGRAL) +
            Eventos.valorCalculado(200, TipoValor.CALCULADO, TipoProcessamento.DECIMO_TERCEIRO_SALARIO, SubTipoProcessamento.INTEGRAL);
}
vaux = Lancamentos.valor(evento)
if (baseprev <= 0) {
    suspender 'Sem base de calculo'
}
if (vaux >= 0) {
    valorReferencia = vaux
    valorCalculado = vaux
} else {
    vaux = 0
    def max = EncargosSociais.RGPS.buscaMaior(1)
    imprimir 'Limite tabela  : ' + max
    if (baseprev > max) {
        baseexcedinss = baseprev - max
        baseprev = max
    }
    vaux = baseprev
    imprimir "Bases INSS ${vaux}"
    if (matricula.tipo != TipoMatricula.AUTONOMO) {
        def vaux2 = vaux.trunc(2)
        valorReferencia = EncargosSociais.RGPS.buscaContribuicao(vaux2, 2)
    } else {
        def codfpas = 0
        if (EncargosSociaisFpas.ENTIDADE_BENEFICENTE.equals(EncargosSociais.RGPS.codigoFpas) &&
                TipoMatricula.AUTONOMO.equals(matricula.tipo)) {
            valorReferencia = 20
        } else {
            valorReferencia = EncargosSociais.RGPS.buscaMaior(2)
        }
    }
    baseprev *= valorReferencia / 100
    valorCalculado = baseprev.trunc(2) - Eventos.valor(171)
    Bases.compor(baseexcedinss, Bases.EXCEINSS13)
    Bases.compor(valorCalculado, Bases.IRRF13, Bases.ABATIRRF13)
}

imprimir(valorCalculado)