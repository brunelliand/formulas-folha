package com.betha.pessoal.scripts.eventos
   
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.Eventos
import com.betha.pessoal.scripts.padrao.Calculo.calculo
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Bases
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*
   
/**a declaração de dados do evento pode ser removida, utilize apenas quando necessário testar alguma informação diferente **/
def evento = [codigo: 37 , taxa: 8 , tipo: TipoEvento.VENCIMENTO , unidade: UnidadeEvento.AUTOMATICO];

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

def optanteFgts = Funcoes.optanteFgts(matricula.tipo)

if ( ! optanteFgts || TipoMatricula.ESTAGIARIO.equals(matricula.tipo) ){
    suspender "Cálculo do evento ${evento.codigo} cancelado"
}

double vaux = Lancamentos.valor(evento)
if( vaux > 0) {
    vlrcalc = vaux;
}else {
    if (funcionario.categoriaSefipVinculo.toString() == 'MENOR_APRENDIZ') {
        valorReferencia = 2
    } else {
        valorReferencia = evento.taxa
    }
    double baseaux
    double fgtsaux
    vaux  = Eventos.valorCalculado(36, TipoValor.CALCULADO , TipoProcessamento.MENSAL, SubTipoProcessamento.INTEGRAL)
    vaux += Eventos.valorCalculado(86, TipoValor.CALCULADO , TipoProcessamento.FERIAS, SubTipoProcessamento.INTEGRAL)

    if (TipoProcessamento.DECIMO_TERCEIRO_SALARIO.equals(calculo.tipoProcessamento) && vaux > 0) {
        baseaux = Bases.valorCalculado(Bases.FGTS, TipoProcessamento.FERIAS, SubTipoProcessamento.INTEGRAL)
        baseaux += Bases.valorCalculado(Bases.FGTS, TipoProcessamento.MENSAL, SubTipoProcessamento.INTEGRAL)

        if (baseaux > 0) {
            fgtsaux = vaux
        }
        vaux = Bases.valor(Bases.FGTS13) + baseaux
        vaux = vaux * (valorReferencia / 100);
        vaux = vaux - fgtsaux;
        valorCalculado = vaux.trunc(2)
    } else {
        vaux = Bases.valor(Bases.FGTS13)  * valorReferencia / 100;
        valorCalculado = vaux.trunc(2)
    }
}