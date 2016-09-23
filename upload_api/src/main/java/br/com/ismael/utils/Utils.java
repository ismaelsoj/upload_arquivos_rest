package br.com.ismael.utils;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import br.com.ismael.constantes.Constantes;

public class Utils {

	public static File getArquivoDiretorioUpload(String arquivo) {
		List<File> listaArquivos = Arrays.asList(Constantes.DIRETORIO_ARQUIVOS.listFiles());
		File arquivoRetorno = null;
		for (File file : listaArquivos) {
			if (file.getName().equals(arquivo)) {
				arquivoRetorno = file;
				break;
			}
		}
		return arquivoRetorno;
	}

}
