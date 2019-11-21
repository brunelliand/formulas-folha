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
def evento = [codigo: 213, taxa: 0, tipo: TipoEvento.DESCONTO, unidade: UnidadeEvento.AUTOMATICO]; //Configuracao do evento

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

if ( ! TipoMatricula.ESTAGIARIO.equals(matricula.tipo) ){
    suspender "Calcular apenas para estagiarios"
}

def vaux = Lancamentos.valor(evento)
def desc
if (vaux >= 0) {
    valorCalculado = vaux
} else {
    def base = Bases.valor(Bases.IRRFFERRESC) - Eventos.valor(214) - Eventos.valor(216)
    if (base < EncargosSociais.IRRF.buscaContribuicao(0, 1)) {
        suspender 'Sem IRRF a ser calculado!'
    }
    desc = EncargosSociais.IRRF.buscaContribuicao(base, 3)
    valorReferencia = EncargosSociais.IRRF.buscaContribuicao(base, 2)
    valorCalculado = (base * valorReferencia / 100) - desc
}
if(valorCalculado < EncargosSociais.IRRF.minimoIrrfDarf) {
    valorCalculado = 0
} else {
    Bases.compor(desc, Bases.DESCIRRF)
}

imprimir(valorCalculado)