package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Bases
import com.betha.pessoal.scripts.padrao.Numeros
import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas
import com.betha.pessoal.scripts.padrao.Servidor.servidor
import com.betha.pessoal.scripts.padrao.Calculo.calculo
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*

/**a declaração de dados do evento pode ser removida, utilize apenas quando necessário testar alguma informação diferente **/
def evento = [codigo: 195, taxa: 0, tipo: TipoEvento.VENCIMENTO, unidade: UnidadeEvento.AUTOMATICO];

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

Funcoes.somenteFuncionarios()
if (! servidor.sexo == Sexo.FEMININO) {
    suspender 'Calcular apenas para sexo feminino'
}
double vlrbase
def vaux = Lancamentos.valor(evento)
if (vaux > 0) {
    valorReferencia = vaux
} else {
    def diasaux = Funcoes.afaslicmat() + Funcoes.afasprorroglicmat() + Funcoes.afasaborto()
    if (diasaux == 0) {
        suspender 'Sem dias de direito'
    }
    def classificacoes = [
            [ClassificacaoTipoAfastamento.LICENCA_MATERNIDADE, false],
            [ClassificacaoTipoAfastamento.PRORROGACAO_DA_LICENCA_MATERNIDADE, false],
            [ClassificacaoTipoAfastamento.ABORTO_NAO_CRIMINOSO, false],
            [ClassificacaoTipoAfastamento.PRORROGACAO_DA_LICENCA_MATERNIDADE, true]
    ]
    def cptiniauxmat = null
    for (classificacao in classificacoes) {
        if (cptiniauxmat == null) {
            cptiniauxmat = Funcoes.dtafast(*classificacao)
        }
    }
    if (!cptiniauxmat) {
        suspender "Nenhuma licença"
    }
    cptiniauxmat = Datas.data(Datas.ano(cptiniauxmat), Datas.mes(cptiniauxmat), 1)
    for (int i = 1; i <= 6; i++) {
        def cptaux = Datas.removeMeses(cptiniauxmat, i)

        vlrbase += Bases.valorCalculado(Bases.MEDIAUXMAT, TipoProcessamento.MENSAL, SubTipoProcessamento.INTEGRAL, cptaux) +
                Bases.valorCalculado(Bases.MEDIAUXMAT, TipoProcessamento.MENSAL, SubTipoProcessamento.COMPLEMENTAR, cptaux)
    }
    if (vlrbase == 0) suspender "Sem valor de base"

    vaux = vlrbase / 6
    vaux = Funcoes.calcprop(vaux, Funcoes.cnvdpbase(diasaux))
}
if (vaux > 0) {
    valorCalculado = Numeros.arredonda(vaux, 2)
    Bases.compor(valorCalculado,
            Bases.SALBASE,
            Bases.PERIC,
            Bases.SIND,
            Bases.FGTS,
            Bases.IRRF,
            Bases.INSS,
            Bases.PREVEST,
            Bases.FUNDASS,
            Bases.FUNDOPREV,
            Bases.FUNDFIN)
}
imprimir(valorCalculado)