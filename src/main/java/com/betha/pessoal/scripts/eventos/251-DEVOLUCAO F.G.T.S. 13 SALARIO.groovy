package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.*
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Bases
import com.betha.pessoal.scripts.padrao.Calculo.calculo
import com.betha.pessoal.scripts.padrao.Servidor.servidor
import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*

/**a declaração de dados do evento pode ser removida, utilize apenas quando necessário testar alguma informação diferente **/
def evento = [codigo: 251, taxa: 8, tipo: TipoEvento.DESCONTO, unidade: UnidadeEvento.AUTOMATICO]; //Configuracao do evento

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

if (!TipoProcessamento.RESCISAO.equals(calculo.tipoProcessamento)) {
    suspender "Calcular somente na rescisão"
}

def competenciaRescisao = Funcoes.primeiroDia(calculo.dataRescisao)

def baseFgts = Bases.valor(Bases.FGTS) + Bases.valor(Bases.FGTS13)

if (baseFgts > 0 || !Funcoes.optanteFgts(matricula.tipo)) {
    suspender "Calculo do evento cancelado"
}

def vaux = Lancamentos.valor(evento)
if (vaux > 0) {
    valorCalculado = vaux
} else {
    if (TipoMatricula.AUTONOMO.equals(matricula.tipo)) {
        valorReferencia = 2
    } else {
        valorReferencia = evento.taxa
    }
    def baseDevolucao = baseFgts * (-1)

    vaux = baseDevolucao * (valorReferencia / 100)
    valorCalculado = Numeros.trunca(vaux, 2)
    valorReferencia = baseDevolucao
}
