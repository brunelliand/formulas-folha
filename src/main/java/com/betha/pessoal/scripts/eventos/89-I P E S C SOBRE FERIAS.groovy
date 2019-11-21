package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.EncargosSociais
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.padrao.evento
import com.betha.pessoal.scripts.padrao.Folhas.folha
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Bases
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*


/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

Funcoes.somenteFuncionarios()

int forma = 1 //1-Aplica %, 2-Considera Minimo e Maximo Tabela

if (!funcionario.possuiPrevidencia(TipoPrevidencia.PREVIDENCIA_ESTADUAL)) {
    suspender 'Apenas para previdencia estadual'
}

if (folha.folhaPagamento) {
    double baseprev = Bases.valor(Bases.PREVEST)
    if ( ! baseprev <= 0 ){
        suspender "Sem base de previdencia estadual"
    }

    def vaux = Lancamentos.valor(evento)
    if (vaux >= 0) {
        valorReferencia = vaux
        valorCalculado = vaux
    } else {
        valorReferencia = evento.taxa
        vaux = baseprev * valorReferencia / 100
        if (forma == 2) {
            def minprevest = EncargosSociais.RPPS.PrevidenciaEstadual.minimo
            def maxprevest = EncargosSociais.RPPS.PrevidenciaEstadual.maximo
            if (vaux < minprevest) {
                vaux = minprevest
            } else {
                if (vaux > maxprevest) {
                    vaux = maxprevest
                }
            }
        }
        valorCalculado = vaux
    }

}

