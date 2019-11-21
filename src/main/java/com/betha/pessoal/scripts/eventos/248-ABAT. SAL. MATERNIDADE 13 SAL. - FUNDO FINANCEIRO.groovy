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
def evento = [codigo: 242, taxa: 0, tipo: TipoEvento.DESCONTO, unidade: UnidadeEvento.AUTOMATICO]; //Configuracao do evento

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/


Funcoes.somenteFuncionarios()

if (!funcionario.possuiPrevidencia(TipoPrevidencia.FUNDO_FINANCEIRO)) {
    suspender "Calcular somente para previdência própria"
}

if (! Funcoes.permitecalc13integral()){
    suspender "Calculo do evento suspenso"
}

def base = Bases.valor(Bases.FUNDFIN13) - Bases.valor(Bases.DESC13REINT)
if (TipoProcessamento.RESCISAO.equals(calculo.tipoProcessamento)){
    valorCalculado = Funcoes.deducauxmat13(base, Funcoes.avos13(Datas.mes(calculo.competencia)) , 1 )
}else{
    valorCalculado = Funcoes.deducauxmat13(base, Funcoes.avos13(12) , 1 )
}

