package br.com.devpi.novocalendar.enums;

/**
 * Created by esdraspinheiro on 01/07/17.
 */

public enum EnumTitulo {

    TITULO_AVISO("Aviso"),
    TITULO_OK("Ok");

    private String titulo;

    EnumTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo(){
        return titulo;
    }
}
