package br.com.ismael.beans;

import java.net.URL;

import br.com.ismael.utils.Status;

/**
 * Classe que representa todas as propriedades de um upload.
 * 
 * @author Ismael Júnior
 *
 */
public class BeanArquivo {

    /**
     * Usuário que realizou o upload.
     */
    private String identificadorUsuario;
    /**
     * Nome do arquivo.
     */
    private String arquivo;
    /**
     * Tempo gasto para realizar o upload.
     */
    private double tempoUpload;
    /**
     * {@link Status} do upload.
     */
    private Status status;
    /**
     * {@link URL} para download do arquivo.
     */
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
