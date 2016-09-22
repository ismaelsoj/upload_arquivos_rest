package br.com.ismael.beans;

public class ResponseListagemArquivos {

	private String usuario;
	private String nomeArquivo;
	private String statusUpload;
	private long tempoEnvio;
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

	public String getStatusUpload() {
		return statusUpload;
	}

	public void setStatusUpload(String statusUpload) {
		this.statusUpload = statusUpload;
	}

	public long getTempoEnvio() {
		return tempoEnvio;
	}

	public void setTempoEnvio(long tempoEnvio) {
		this.tempoEnvio = tempoEnvio;
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

}
