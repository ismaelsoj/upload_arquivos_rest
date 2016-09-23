package br.com.ismael.beans;

import br.com.ismael.utils.Status;

public class ResponseListagemArquivos {

	private String usuario;
	private String nomeArquivo;
	private Status statusUpload;
	private Double tempoEnvio;
	private int blocosArquivo;
	private String link;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public int getBlocosArquivo() {
		return blocosArquivo;
	}

	public void setBlocosArquivo(int blocosArquivo) {
		this.blocosArquivo = blocosArquivo;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Double getTempoEnvio() {
		return tempoEnvio;
	}

	public void setTempoEnvio(Double tempoEnvio) {
		this.tempoEnvio = tempoEnvio;
	}

	public Status getStatusUpload() {
		return statusUpload;
	}

	public void setStatusUpload(Status statusUpload) {
		this.statusUpload = statusUpload;
	}

}
