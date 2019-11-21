package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.DadosEstagiario
import com.betha.pessoal.scripts.padrao.DadosEstagiario.estagiario
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Bases
import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*

/**a declaração de dados do evento pode ser removida, utilize apenas quando necessário testar alguma informação diferente **/
def evento = [codigo: 1, taxa: 0, tipo: TipoEvento.VENCIMENTO, unidade: UnidadeEvento.AUTOMATICO];

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

if (!TipoMatricula.ESTAGIARIO.equals(matricula.tipo)) {
    suspender 'Calculo apenas para estagiários'
}

def vaux = Lancamentos.valor(evento);
if (vaux >= 0) {
    valorReferencia = vaux
} else {
    def diastrab = Funcoes.diastrab()
    def horastrab = Funcoes.cnvdpbase(diastrab);
    def compoemes = Lancamentos.valorComposicaoMes();
    vaux = horastrab - Lancamentos.valorComposicaoMes();
    if (vaux < 0) {
        vaux = 0
    }
    valorReferencia = vaux;
}
def salario = estagiario.bolsaEstudos

valorCalculado = Funcoes.calcprop(salario, vaux);
if (valorCalculado > 0) {
    Bases.compor(salario, Bases.HORAEXTRA)
    Bases.compor(valorReferencia, Bases.PAGAPROP)
    Bases.compor(valorCalculado,
            Bases.SALBASE,
            Bases.FGTS,
            Bases.IRRF,
            Bases.INSS,
            Bases.COMPHORAMES,)
}

imprimir(valorCalculado)