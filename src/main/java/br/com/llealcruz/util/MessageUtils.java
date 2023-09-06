package br.com.llealcruz.util;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class MessageUtils {

    public static final String ERRO_PROCESSAR_ARQUIVO_PAR1 = "Erro ao processar arquivo: {}";
    public static final String ERRO_AO_TENTAR_MOVER_ARQUIVO_PAR1 = "Erro ao tentar mover arquivo: {}";
    public static final String CAMINHO_NAO_ENCONTRADO_PAR1 = "Caminho não encontrado: %s";
    public static final String ARQUIVOS_ENCONTRADOS_PAR1 = "{} Arquivo(s) encontrado(s)";
    public static final String MOVENDO_ARQUIVO_PAR1 = "Movendo o arquivo {}";
    public static final String ITERANDO_LISTA = "Iterando Lista";
    public static final String ERRO_REALIZAR_OPERACAO = "Erro ao realizar operação";

    private MessageUtils() {
        // vazio
    }

}
