package com.betha.pessoal.scripts.padrao

import com.betha.pessoal.scripts.ConfiguraCaoEvento

import com.betha.pessoal.scripts.ConfiguraCaoEvento

import static com.betha.pessoal.scripts.padrao.enums.*;
/**
 * Retorna informações do evento que esta sendo calculado ou buscado na folha
 */
public class evento {


    /**
     * Seta um evento como replicado
     */
    static void replicado(Boolean eventoReplicado){
        if ( eventoReplicado ){
            System.out.println("Evento ${eventoReplicado} marcado como replicado do cálculo de férias")
            //TODO
        }

    }

    /**
     * Código do evento
     */

    public static int getCodigo(){
        if ( ConfiguraCaoEvento.CODIGO_EVENTO ){
            return ConfiguraCaoEvento.CODIGO_EVENTO
        }
        return 1
    }

    private static int codigo = getCodigo()

    private static BigDecimal getTaxaEvento(){
        if ( ConfiguraCaoEvento.TAXA_EVENTO ){
            return ConfiguraCaoEvento.TAXA_EVENTO
        }
        return BigDecimal.valueOf(0)
    }

    /**
     * Taxa do evento
     */
    private static BigDecimal taxa = getTaxaEvento()
    /**
     * Permite busca a taxa de um outro evento informando-o no argumento
     * @param codigoEvento
     * @return Taxa informada no evento
     */
    private static BigDecimal getTaxa(Integer codigoEvento) {
        return BigDecimal.valueOf(100)
    }
    /**
     * Nome/Descrição do evento
     */
    private static String descricao = 'HORAS NORMAIS';
    /**
     * Tipo do evento (VENCIMENTO/DESCONTO/INFORMATIVO + / INFORMATIVO -)
     */
    private static Enum tipo = TipoEvento.VENCIMENTO;
    /**
     * Unidade do evento
     */
    private static Enum unidade = UnidadeEvento.DIAS;
    /**
     * Descrição da classificação do evento
     */
    private static String descricaoClassificacao = 'NENHUMA';
    /**
     * Classificação do evento
     */
    private static Enum classificacao = ClassificacaoEvento.INSS
}