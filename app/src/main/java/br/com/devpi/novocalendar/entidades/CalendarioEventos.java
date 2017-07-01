package br.com.devpi.novocalendar.entidades;

/**
 * Created by esdraspinheiro on 29/06/17.
 */

public class CalendarioEventos {

    private Long id;
    private String nomeEvento;
    private String descricaocao;
    private String dataEvento;
    private String horaEvento;

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public String getDescricaocao() {
        return descricaocao;
    }

    public void setDescricaocao(String descricaocao) {
        this.descricaocao = descricaocao;
    }

    public String getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(String dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getHoraEvento() {
        return horaEvento;
    }

    public void setHoraEvento(String horaEvento) {
        this.horaEvento = horaEvento;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
