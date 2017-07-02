package br.com.devpi.novocalendar.enums;

/**
 * Created by esdraspinheiro on 01/07/17.
 */

public enum EnumConexoes {

    /**
     * Concatenar o id, retorna o json do {@link br.com.devpi.novocalendar.entidades.CalendarioEventos}
     */
    GET_CALENDARIO_EVENTO_BY_ID(EnumWebService.WEB_SERVICE.getWebService()+"calendario/eventos/"),

    /**
     * Retorna um json em lista do {@link br.com.devpi.novocalendar.entidades.CalendarioEventos}
     */
    GET_LISTA_CALENDARIO_EVENTO(EnumWebService.WEB_SERVICE.getWebService()+"calendario/eventos/listaCalendarioEventos"),

    /**
     * Concatenar o login do usuario, Retorna um json do {@link br.com.devpi.novocalendar.entidades.Usuario}
     */
    GET_USUARIO_BY_LOGIN(EnumWebService.WEB_SERVICE.getWebService()+"usuarios/");

    private String conexao;

    EnumConexoes(String conexao) {
        this.conexao = conexao;
    }

    public String getConexao(){
        return conexao;
    }

}
