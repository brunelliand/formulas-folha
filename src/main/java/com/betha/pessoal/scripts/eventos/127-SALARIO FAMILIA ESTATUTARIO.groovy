package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.EncargosSociais
import com.betha.pessoal.scripts.padrao.Eventos
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Bases
import com.betha.pessoal.scripts.padrao.Servidor.servidor
import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas
import com.betha.pessoal.scripts.padrao.Calculo.calculo
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*

/**a declaração de dados do evento pode ser removida, utilize apenas quando necessário testar alguma informação diferente **/
def evento = [codigo: 127, taxa: 0, tipo: TipoEvento.VENCIMENTO, unidade: UnidadeEvento.AUTOMATICO];

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

Funcoes.somenteFuncionarios()
int tipo = 1  //1 - Normal, 2 - Por faixa

int dependentes = servidor.getDependenteSalarioFamilia(TipoSalarioFamilia.ESTATUTARIO)
if (dependentes < 1) {
    suspender "Sem dependentes"
}
def afastamentos
if (Funcoes.diasferias() == 0) {
    afastamentos = Eventos.valor(4) + Eventos.valor(5) + Eventos.valor(7) + Eventos.valor(8)
    if (afastamentos > 0 || Bases.valor(Bases.PAGAPROP) == 0) {
        suspender 'Funcionário afastado'
    }
} else {
    def dias = Funcoes.diastrab() + Funcoes.afaslicmat() + Funcoes.afasdirinteg()
    if (dias > 0) {
        suspender 'Sem dias de direito'
    }
}
valorReferencia = dependentes
def vvar = Lancamentos.valor(evento)
if (vvar >= 0) {
    valorCalculado = vvar
} else {
    if (tipo == 1) {
        valorCalculado = dependentes * EncargosSociais.SalarioFamilia.Estatutario.buscaMaior(2)
    } else {
        valorReferencia = EncargosSociais.SalarioFamilia.Estatutario.buscaContribuicao(funcionario.salario, 2)
        valorCalculado = dependentes * valorReferencia
    }
}

imprimir(valorCalculado)