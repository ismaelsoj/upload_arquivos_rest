package br.com.ismael.lista;

import java.util.LinkedList;
import java.util.List;

import br.com.ismael.beans.BeanArquivo;

public class ListaArquivosUpload {

	private List<BeanArquivo> lista;

	public static ListaArquivosUpload listaArquivos;

	private ListaArquivosUpload() {
		lista = new LinkedList<>();
	}

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
