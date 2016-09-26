package br.com.ismael.utils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import br.com.ismael.constantes.Constantes;

public class Utils {

    /**
     * Procura por um nome de arquivo dentro do diretório de upload da API.
     * 
     * @param arquivo
     *            - Nome do arquivo a ser pesquisado.
     * @return Um {@link File} caso encontre o arquivo especificado ou <code>null</code> caso contrário;
     */
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

    public static String getVersion() {
        return getPropertyInfo("versao");
    }

    public static String getNomeProjeto() {
        return getPropertyInfo("nomeProjeto");
    }

    /**
     * Procura uma propriedade Maven dentro de um arquivo do tipo {@link Properties}
     * 
     * @param property
     *            - propriedade a ser procurada.
     * @return O valor da propriedade especificada
     */
    private static String getPropertyInfo(String property) {
        Properties prop = new Properties();
        String retorno = "";
        try {
            prop.load(Utils.class.getResourceAsStream("/info.properties"));
            retorno = prop.getProperty(property);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return retorno;
    }

}
