package br.com.ismael.constantes;

import java.io.File;

/**
 * Classe que define constantes utilizadas no projeto
 * 
 * @author Ismael
 *
 */
public class Constantes {

    /**
     * Diretório para armazenar os uploads realizados.
     */
    public static final File DIRETORIO_ARQUIVOS = new File("arquivos_upload");
    /**
     * Diretório temporário onde serão armazenados os blocos de arquivos enquanto o upload é realizado.
     */
    public static final String CHUNK_TEMP_DIR = "tmp" + File.separator + "upload";
    /**
     * Define o tamanho máximo de um bloco.
     */
    public static final long CHUNK_SIZE = 1048576L;

}
