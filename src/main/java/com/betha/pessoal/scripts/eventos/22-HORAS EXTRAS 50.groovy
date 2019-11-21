package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.Calculo
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.Eventos
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Bases
import com.betha.pessoal.scripts.padrao.Calculo.calculo
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*

/**a declaração de dados do evento pode ser removida, utilize apenas quando necessário testar alguma informação diferente **/
def evento = [codigo: 22, taxa: 150, tipo: TipoEvento.VENCIMENTO, unidade: UnidadeEvento.AUTOMATICO];

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

Funcoes.somenteFuncionarios()
if (Calculo.calculo.tipoProcessamento == TipoProcessamento.FERIAS) {
	suspender "Cálculo do evento ${evento.codigo} cancelado"
}
valorReferencia = Lancamentos.valor(evento)
def base = 0
if (Eventos.valor(1) > 0) {
	base = Bases.valor(Bases.HORAEXTRA)
} else {
	base = Bases.valor(Bases.HORAEXTRA) + funcionario.salario
}

if (valorReferencia > 0) {
	valorCalculado = base / funcionario.quantidadeHorasMes * valorReferencia * evento.taxa / 100

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
