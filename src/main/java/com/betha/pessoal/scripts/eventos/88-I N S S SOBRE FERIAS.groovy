package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.Calculo.calculo
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.EncargosSociais
import com.betha.pessoal.scripts.padrao.Eventos
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.padrao.Folhas.folha
import com.betha.pessoal.scripts.padrao.evento
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Bases
import com.betha.pessoal.scripts.padrao.Numeros

import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*


/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

if (! TipoProcessamento.FERIAS.equals(calculo.tipoProcessamento)){
    suspender "Este evento deve ser calculado apenas no processamento férias"
}

if (!Funcoes.possuiPrevidenciaFederal(matricula.tipo)) {
    suspender "Não possui previdência federal"
}

if (folha.folhaPagamento) {
    def baseprev = Bases.valor(Bases.INSS) + Bases.valor(Bases.INSSOUTRA)
    def vaux = Lancamentos.valor(evento)
    imprimir "Base de I.N.S.S. : ${baseprev}"
    valorCalculado = 0
    if (vaux >= 0) {
        valorReferencia = vaux
        valorCalculado = vaux
    } else {
        if (baseprev > 0 && Funcoes.possuiPrevidenciaFederal(matricula.tipo)) {
            def max = EncargosSociais.RGPS.buscaMaior(1)
            vaux = baseprev
            if (vaux > max) {
                vaux -= max
                Bases.compor(vaux, Bases.EXCEINSS)
                vaux = max
            }
            if (TipoMatricula.FUNCIONARIO.equals(matricula.tipo)) {
                vaux2 = Numeros.trunca(vaux, 2)
                valorReferencia = EncargosSociais.RGPS.buscaContribuicao(vaux2, 2)
            } else {
                valorReferencia = EncargosSociais.RGPS.buscaMaior(2)
            }
            vaux *= valorReferencia / 100
            valorCalculado = Numeros.trunca(vaux, 2) - Eventos.valor(169)

        }
    }
    Bases.compor(valorCalculado, Bases.IRRFFER, Bases.ABATIRRF)

}

