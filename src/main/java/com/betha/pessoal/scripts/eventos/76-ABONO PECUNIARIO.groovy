package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.Calculo
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Calculo.calculo
import com.betha.pessoal.scripts.padrao.evento
import com.betha.pessoal.scripts.padrao.Folhas.folha
import com.betha.pessoal.scripts.padrao.Folhas.folhas
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*

/**a declaração de dados do evento pode ser removida, utilize apenas quando necessário testar alguma informação diferente **/



/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

def valorFerias = Funcoes.replicaFeriasNaFolhaMensal(evento.codigo)
valorCalculado = valorFerias.valor
valorReferencia = valorFerias.referencia

if (TipoProcessamento.FERIAS.equals(calculo.tipoProcessamento)) {
    def diasAbono = folha.diasAbono
    def vaux = Lancamentos.valor(evento)
    if (diasAbono > 0 || vaux >= 0) {
        if (vaux >= 0) {
            valorReferencia = vaux
        } else {
            vaux = Funcoes.cnvdpbase(diasAbono)
            valorReferencia = vaux
        }
        if (vaux > 0) {
            valorCalculado = Funcoes.calcprop(funcionario.salario, vaux)
        }
    }
}
