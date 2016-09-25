package br.com.ismael.constantes;

import java.io.File;

public class Constantes {

	public static final File DIRETORIO_ARQUIVOS = new File("arquivos_upload");
	public static final String CHUNK_TEMP_DIR = "tmp" + File.separator + "upload";
	public static final long CHUNK_SIZE = 1048576L;

}
