package br.com.ismael.beans;

import java.io.File;

import br.com.ismael.utils.Status;

public class BeanArquivo {

	private String identificadorUsuario;
	private File arquivo;
	private double tempoUpload;
	private Status status;

	public File getArquivo() {
		return arquivo;
	}

	public void setArquivo(File arquivo) {
		this.arquivo = arquivo;
	}

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



}
