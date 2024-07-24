package br.com.igrejaparaiso.Igrejaparaiso.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class EventoSpring{
    
    private String id;   
    
    private String nome;

    private String descricao;

    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate data; // datas PRECISAM SER LocalDate

    private String horaInicio;

    private String horaFim;

    public EventoSpring() {
    }

    public EventoSpring(String id, String nome, String descricao, String data, String horaInicio, String horaFim) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        setDataLocal(data);
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataLocal() {
        return data.toString();
    }

    public void setDataLocal(String data) {
        this.data = LocalDate.parse(data);
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(String horaFim) {
        this.horaFim = horaFim;
    }

    @Override
    public String toString() {
        return "EventoSpring [data=" + data + ", descricao=" + descricao + ", horaFim=" + horaFim + ", horaInicio="
                + horaInicio + ", id=" + id + ", nome=" + nome + "]";
    }


   
    

}