package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.Eventos
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Servidor.servidor
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*

/**a declaração de dados do evento pode ser removida, utilize apenas quando necessário testar alguma informação diferente **/
def evento = [codigo: 46, taxa: 0, tipo: TipoEvento.VENCIMENTO, unidade: UnidadeEvento.AUTOMATICO];

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

Funcoes.somenteFuncionarios()
if (!funcionario.possuiPrevidenciaFederal) {
    valorReferencia = Eventos.valorReferencia(7)
    valorCalculado = Eventos.valor(7)
} else {
    valorReferencia = Eventos.valorReferencia(4) + Eventos.valorReferencia(5) + Eventos.valorReferencia(7) + Eventos.valorReferencia(8)
    valorCalculado = Eventos.valor(4) + Eventos.valor(5) + Eventos.valor(7) + Eventos.valor(8)
    if (servidor.sexo == Sexo.MASCULINO) {
        valorReferencia += Eventos.valorReferencia(3) + Eventos.valorReferencia(163) + Eventos.valorReferencia(164) + Eventos.valorReferencia(165) + Eventos.valorReferencia(199);
        valorCalculado += Eventos.valor(3) + Eventos.valor(163) + Eventos.valor(164) + Eventos.valor(165) + Eventos.valor(199)
    }
}
