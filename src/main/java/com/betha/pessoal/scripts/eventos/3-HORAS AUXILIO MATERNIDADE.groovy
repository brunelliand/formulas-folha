package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.padrao.Servidor.servidor
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Bases
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*

/**a declaração de dados do evento pode ser removida, utilize apenas quando necessário testar alguma informação diferente **/
def evento = [codigo: 3, taxa: 0, tipo: TipoEvento.VENCIMENTO, unidade: UnidadeEvento.AUTOMATICO];

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/


Funcoes.somenteFuncionarios();

def afaslicmat = Funcoes.afaslicmat();

def vaux = Lancamentos.valor(evento)

if (vaux >= 0) {
    valorReferencia = vaux
} else {
    if (afaslicmat == 0) {
        suspender 'Sem dias de licença'
    }
    imprimir 'Dias ' + afaslicmat
    vaux = Funcoes.cnvdpbase(afaslicmat)
    valorReferencia = vaux
    imprimir 'vaux ' + vaux
}
valorCalculado = Funcoes.calcprop(funcionario.salario, vaux)
Bases.compor(valorReferencia, Bases.PAGAPROP, Bases.MEDAUXMATPR)
if (servidor.sexo == Sexo.MASCULINO) {
    Bases.compor(valorCalculado, Bases.SALBASE, Bases.PERIC, Bases.SIND, Bases.FGTS, Bases.IRRF, Bases.PREVEST, Bases.FUNDASS, Bases.FUNDOPREV, Bases.COMPHORAMES, Bases.FUNDFIN)
} else {
    Bases.compor(valorCalculado, Bases.SALBASE, Bases.PERIC, Bases.SIND, Bases.FGTS, Bases.IRRF, Bases.INSS, Bases.PREVEST, Bases.FUNDASS, Bases.FUNDOPREV, Bases.COMPHORAMES, Bases.FUNDFIN)
}

