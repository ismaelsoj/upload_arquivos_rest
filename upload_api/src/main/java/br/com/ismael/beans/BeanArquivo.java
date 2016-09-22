package br.com.ismael.beans;

import java.io.File;

public class BeanArquivo {

	private String identificadorUsuario;
	private File arquivo;

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

}
