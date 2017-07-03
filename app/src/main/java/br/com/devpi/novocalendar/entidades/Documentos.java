package br.com.devpi.novocalendar.entidades;

/**
 * Created by esdraspinheiro on 02/07/17.
 */

public class Documentos {

    private Long id;
    private String nomeDocumento;
    private String linkDoc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeDocumento() {
        return nomeDocumento;
    }

    public void setNomeDocumento(String nomeDocumento) {
        this.nomeDocumento = nomeDocumento;
    }

    public String getLinkDoc() {
        return linkDoc;
    }

    public void setLinkDoc(String linkDoc) {
        this.linkDoc = linkDoc;
    }
}
