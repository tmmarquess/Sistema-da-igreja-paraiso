package br.com.igrejaparaiso.Igrejaparaiso.model;

public class LinkDoCulto {

    private String id;

    private String titulo;

    private String link;

    private String data;

    
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "LinkDoCulto [data=" + data + ", id=" + id + ", link=" + link + ", titulo=" + titulo + "]";
    }

    public LinkDoCulto(String id,String titulo, String link, String data) {
        this.titulo = titulo;
        this.id = id;
        this.link = link;
        this.data = data;
    }

    public LinkDoCulto() {
    }    
    
}