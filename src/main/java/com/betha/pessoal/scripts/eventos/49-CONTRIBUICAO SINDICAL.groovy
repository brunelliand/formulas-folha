package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.Calculo.calculo
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.Eventos
import com.betha.pessoal.scripts.padrao.LancamentoEvento
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Bases
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*

/**a declaração de dados do evento pode ser removida, utilize apenas quando necessário testar alguma informação diferente **/
def evento = [codigo: 49, taxa: 0, tipo: TipoEvento.VENCIMENTO, unidade: UnidadeEvento.AUTOMATICO];

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

Funcoes.somenteFuncionarios()

valorReferencia = 1 //1 dia
def vvar = Lancamentos.valor(evento)
if (vvar > 0) {
    valorCalculado = vvar
} else {
    if (funcionario.sindicato == null) {
        suspender 'Nenhum sindicato informado'
    }

    if (funcionario.mesContribuicaoSindical == null) {
        suspender 'Mês da contribuição sindical não foi informado no cadastro do sindicato (empresas)'
    }

    def diasMensal = Funcoes.diastrab() +
            Funcoes.afasaborto() +
            Funcoes.afasacidtrab() +
            Funcoes.afasaborto() +
            Funcoes.afasauxdoencemp() +
            Funcoes.afasauxdoenc() +
            Funcoes.afasservmil() +
            Funcoes.afaslicmat() +
            Funcoes.afasprorroglicmat() +
            Funcoes.afasprorroglicmatlei11770()

    if (diasMensal <= 0 && TipoProcessamento.MENSAL.equals(calculo.tipoProcessamento) && SubTipoProcessamento.INTEGRAL.equals(calculo.subTipoProcessamento)) {
        suspender "Sem dias trabalhados"
    }

    if (Eventos.valorCalculado(49, TipoValor.CALCULADO, TipoProcessamento.FERIAS, SubTipoProcessamento.INTEGRAL) > 0 && TipoProcessamento.MENSAL.equals(calculo.tipoProcessamento) && SubTipoProcessamento.COMPLEMENTAR.equals(calculo.subTipoProcessamento)) {
        suspender "Evento já calculado no processamento férias"
    }

    def mescontrib = Funcoes.buscaMes(funcionario.mesContribuicaoSindical)
    valorReferencia = 1;


    if (Datas.mes(calculo.competencia).equals(mescontrib)) {
        suspender 'Não é o mes de contribuição'
    }
    def diast = 30
    if (funcionario.unidadePagamento == UnidadePagamento.DIARISTA || funcionario.unidadePagamento == UnidadePagamento.HORISTA) {
        diast = calculo.quantidadeDiasCompetencia
    }

    if (diast <= 0) {
        suspender 'Sem dias totais'
    }
    valorCalculado = funcionario.salario + Bases.valor(Bases.CONTSIND)
    if (funcionario.unidadePagamento == UnidadePagamento.DIARISTA ||
            funcionario.unidadePagamento == UnidadePagamento.HORISTA) {
        valorCalculado /= calculo.quantidadeDiasCompetencia
    } else {
        valorCalculado /= 30
    }


}



