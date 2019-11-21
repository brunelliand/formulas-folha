package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Bases
import com.betha.pessoal.scripts.padrao.Calculo.calculo;
import com.betha.pessoal.scripts.padrao.Eventos;
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*

/**a declaração de dados do evento pode ser removida, utilize apenas quando necessário testar alguma informação diferente **/
def evento = [codigo: 10, taxa: 0, tipo: TipoEvento.VENCIMENTO, unidade: UnidadeEvento.AUTOMATICO];

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

def vvar = Lancamentos.valor(evento)
def vaux = calculo.quantidadeDiasCompetencia - vvar
if (vvar > 0) {
    vaux = (Eventos.valor(22) + Eventos.valor(23) + Eventos.valor(24)) / vaux * vvar
    valorReferencia = vvar
    valorCalculado = vaux
    Bases.compor(valorCalculado,
            Bases.SALBASE,
            Bases.FGTS,
            Bases.IRRF,
            Bases.INSS,
            Bases.PREVEST,
            Bases.FUNDASS,
            Bases.FUNDOPREV,
            Bases.SALAFAM,
            Bases.MEDIAUXMAT,
            Bases.FUNDFIN)
}

imprimir(valorCalculado)