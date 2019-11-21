package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.EncargosSociais
import com.betha.pessoal.scripts.padrao.Eventos
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas
import com.betha.pessoal.scripts.padrao.Calculo.calculo
import com.betha.pessoal.scripts.padrao.Servidor.servidor
import com.betha.pessoal.scripts.padrao.Bases
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*

/**a declaração de dados do evento pode ser removida, utilize apenas quando necessário testar alguma informação diferente **/
def evento = [codigo: 135, taxa: 0, tipo: TipoEvento.VENCIMENTO, unidade: UnidadeEvento.AUTOMATICO];

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

if (matricula.tipo != TipoMatricula.APOSENTADO && matricula.tipo != TipoMatricula.PENSIONISTA) {
    suspender 'Parcela isenta somente para aposentados ou pensinistas'
}
if (servidor.possuiMolestiaGrave) {
    suspender 'possui molestia, este evento nao sera calculado'
}
if (servidor.dataNascimento == null) {
    suspender 'O servidor nao possui data de nascimento informada'
}

def finalCpt = Datas.data(Datas.ano(calculo.competencia), Datas.mes(calculo.competencia), calculo.quantidadeDiasCompetencia)

int idade = Funcoes.idade(servidor.dataNascimento, finalCpt);
imprimir "Idade ${idade}"
if (idade < 65) {
    suspender 'O aposentado / pensionista deve ter pelo menos 65 anos de idade '
}
parcelaIsenta = Bases.valor(Bases.IRRF) + Eventos.valor(138) + Eventos.valor(50) + Eventos.valor(52) + Eventos.valor(54) + Eventos.valor(56) + Eventos.valor(243)
primeiraFaixaIrrf = EncargosSociais.IRRF.buscaContribuicao(0, 1) //retorna o valor da menor faixa de IRRF
if (parcelaIsenta > primeiraFaixaIrrf) {
    parcelaIsenta = primeiraFaixaIrrf
}
parcelaIsenta -= Eventos.valor(192)
valorCalculado = parcelaIsenta

imprimir(valorCalculado)