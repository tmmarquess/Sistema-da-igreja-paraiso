package br.com.igrejaparaiso.Igrejaparaiso.model;

public class LinkDoCultoParse {
    

    public static LinkDoCulto toGoogle(LinkDoCultoSpring antigo){
        LinkDoCulto novo = new LinkDoCulto(antigo.getId(),antigo.getTitulo(),antigo.getLink(),antigo.getDataLocal());
        return novo;
    }

    public static LinkDoCultoSpring toSpring(LinkDoCulto antigo){
        LinkDoCultoSpring novo = new LinkDoCultoSpring(antigo.getId(),antigo.getTitulo(),antigo.getLink(),antigo.getData());
        return novo;
    }

}
