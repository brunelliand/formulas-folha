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
def evento = [codigo: 225, taxa: 0, tipo: TipoEvento.DESCONTO, unidade: UnidadeEvento.AUTOMATICO]; //Configuracao do evento

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

Funcoes.somenteFuncionarios()

if (!Funcoes.avisoPrevioDescontado()) {
    suspender "Sem aviso prévio descontado"
}
def vvar = Lancamentos.valor(evento)
if (vvar >= 0) {
    valorCalculado = vvar
    valorReferencia = vvar
} else {
    valorReferencia = 0
    int diasAvisoPrevioDescontado = AvisoPrevio.quantidadeDiasAviso
    if (diasAvisoPrevioDescontado > 0) {
        valorReferencia = diasAvisoPrevioDescontado
        int diasM = 30
        if (UnidadePagamento.HORISTA.equals(funcionario.unidadePagamento) || UnidadePagamento.DIARISTA.equals(funcionario.unidadePagamento)) {
            diasM = calculo.quantidadeDiasCompetencia
        }
        valorCalculado = (funcionario.salario / diasM) * diasAvisoPrevioDescontado
    } else {
        valorCalculado = funcionario.salario
    }
}

