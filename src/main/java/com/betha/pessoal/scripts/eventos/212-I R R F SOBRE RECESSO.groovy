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
def evento = [codigo: 212, taxa: 0, tipo: TipoEvento.DESCONTO, unidade: UnidadeEvento.AUTOMATICO]; //Configuracao do evento

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

if ( ! TipoMatricula.ESTAGIARIO.equals(matricula.tipo) ){
    suspender "Calcular apenas para estagiarios"
}

double vaux2;
base = Bases.valor(Bases.IRRF) - Eventos.valor(135) - Eventos.valor(189) - Eventos.valor(192) + Bases.valor(Bases.IRRFOUTRA)
if (SubTipoProcessamento.COMPLEMENTAR.equals(calculo.subTipoProcessamento)) {
    base += Bases.valorCalculado(Bases.IRRFOUTRA, TipoProcessamento.MENSAL, SubTipoProcessamento.INTEGRAL)
}
vaux = Lancamentos.valor(evento);
double desc;
if (vaux >= 0) {
    valorCalculado = vaux
} else {
    vaux2 = Numeros.trunca(base, 2);
    desc = EncargosSociais.IRRF.buscaContribuicao(vaux2, 3)
    valorReferencia = EncargosSociais.IRRF.buscaContribuicao(vaux2, 2)

    double valor = (((vaux2 * valorReferencia) / 100) - desc - Eventos.valor(173));
    valorCalculado = valor;
}

if (valorCalculado < EncargosSociais.IRRF.minimoIrrfDarf) {
    valorCalculado = 0
} else {
    Bases.compor(desc, Bases.DESCIRRF)
}



