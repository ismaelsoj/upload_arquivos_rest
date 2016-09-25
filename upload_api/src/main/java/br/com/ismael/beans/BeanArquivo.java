package br.com.ismael.beans;

import br.com.ismael.utils.Status;

public class BeanArquivo {

	private String identificadorUsuario;
	private String arquivo;
	private double tempoUpload;
	private Status status;
	private String link;

	public String getIdentificadorUsuario() {
		return identificadorUsuario;
	}

	public void setIdentificadorUsuario(String identificadorUsuario) {
		this.identificadorUsuario = identificadorUsuario;
	}

	public double getTempoUpload() {
		return tempoUpload;
	}

	public void setTempoUpload(double tempoUpload) {
		this.tempoUpload = tempoUpload;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getArquivo() {
		return arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
