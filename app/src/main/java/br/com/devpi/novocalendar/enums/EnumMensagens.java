package br.com.devpi.novocalendar.enums;

/**
 * Created by esdraspinheiro on 01/07/17.
 */

public enum EnumMensagens {

    MENSAGEN_LOGIN_INVALIDO("Usuario e/ou senha inválidos!! Contate o administrador do sistema."),

    /*
     * MENSAGENS DE ERRO
     */
    ERRO_MENSAGEM_FORMATAR_DATA("Erro ao formatar data! ");

    private String mensagem;

    EnumMensagens(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem(){
        return mensagem;
    }
}
