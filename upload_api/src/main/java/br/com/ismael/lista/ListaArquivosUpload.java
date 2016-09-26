package br.com.ismael.lista;

import java.util.LinkedList;
import java.util.List;

import br.com.ismael.beans.BeanArquivo;

/**
 * Serão armazenados nessa lista o histório de todas as tentativas de upload feitas no ciclo de vida da aplicação.
 * 
 * @author Ismael Júnior
 *
 */
public class ListaArquivosUpload {

    /**
     * Lista de {@link BeanArquivo} contendo o histórico de upload.
     */
    private List<BeanArquivo> lista;

    public static ListaArquivosUpload listaArquivos;

    private ListaArquivosUpload() {
        lista = new LinkedList<>();
    }

    /**
     * Singleton para controlar o acesso à variável estática que guarda o histórico de uploads realizados.
     * 
     * @return a única instância de {@link ListaArquivosUpload} disponível na aplicação
     */
    public static ListaArquivosUpload getInstance() {
        if (listaArquivos == null) {
            listaArquivos = new ListaArquivosUpload();
        }
        return listaArquivos;
    }

    public List<BeanArquivo> getLista() {
        return lista;
    }

    public void setLista(List<BeanArquivo> lista) {
        this.lista = lista;
    }

}
