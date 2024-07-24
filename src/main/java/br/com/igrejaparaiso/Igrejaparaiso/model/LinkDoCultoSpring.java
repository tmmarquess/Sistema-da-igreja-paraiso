package br.com.igrejaparaiso.Igrejaparaiso.model;

import java.time.LocalDate;

public class LinkDoCultoSpring {

    private String id;
    
    private String titulo;

    private String link;

    private LocalDate data;
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getDataLocal() {
        return data.toString();
    }

    public void setDataLocal(String data) {
        this.data = LocalDate.parse(data);
    }


    @Override
    public String toString() {
        return "LinkDoCultoSpring [data=" + data + ", id=" + id + ", link=" + link + ", titulo=" + titulo + "]";
    }

    public LinkDoCultoSpring(String id, String titulo,String link, String data) {
        this.titulo = titulo;
        this.id = id;
        this.link = link;
        setDataLocal(data);
    }

    public LinkDoCultoSpring() {
    }    
    
}