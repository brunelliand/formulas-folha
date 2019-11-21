package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.Calculo.calculo
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.Eventos
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Folhas.folha
import com.betha.pessoal.scripts.padrao.evento
import com.betha.pessoal.scripts.padrao.Folhas.folhas
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*



/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

def valorFerias = Funcoes.replicaFeriasNaFolhaMensal(evento.codigo)
valorCalculado = valorFerias.valor
valorReferencia = valorFerias.referencia

if (TipoProcessamento.FERIAS.equals(calculo.tipoProcessamento)) {
    if (folha.folhaPagamento) {
        def vaux
        def vvar = Lancamentos.valor(evento)
        if (vvar >= 0) {
            valorCalculado = vvar
            valorReferencia = vvar
        } else {
            valorReferencia = evento.taxa
            vaux = Eventos.valor(76) + Eventos.valor(83) + Eventos.valor(84) + Eventos.valor(85) + Eventos.valor(239)
            valorCalculado = vaux * valorReferencia / 100
        }
    }
}
imprimir(valorCalculado)