package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.Eventos
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Bases
import com.betha.pessoal.scripts.padrao.Folhas.folha
import com.betha.pessoal.scripts.padrao.Folhas.folhas
import com.betha.pessoal.scripts.padrao.Calculo.calculo
import com.betha.pessoal.scripts.padrao.evento
import com.betha.pessoal.scripts.padrao.PeriodosAquisitivos.periodoConcessao
import com.betha.pessoal.scripts.padrao.PeriodosAquisitivos.periodoAquisitivo
import com.betha.pessoal.scripts.padrao.MediasVantagens.mediaVantagem
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

def valorFerias = Funcoes.replicaFeriasNaFolhaMensal(evento.codigo)
valorCalculado = valorFerias.valor
valorReferencia = valorFerias.referencia

if (calculo.tipoProcessamento != TipoProcessamento.FERIAS) {
  if (folha.folhaPagamento) {
    def diasAbono = periodoConcessao.diasAbono
    if (diasAbono <= 0) suspender "Sem dias de abono"
    def vaux = Lancamentos.valor(evento)
    if (vaux > 0) {
      valorReferencia = vaux
      valorCalculado = vaux
    } else {
      valorReferencia = diasAbono
      def vint = mediaVantagem.calcular()
      if (vint <= 0) {
        suspender "Sem valor de média"
      }
      valorCalculado = vint
    }

  }
}
