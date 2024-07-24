package br.com.igrejaparaiso.Igrejaparaiso.model;

public class ComprovanteParse {
    

    public static Comprovante toGoogle(ComprovanteSpring antigo){
        Comprovante novo = new Comprovante(antigo.getId(),antigo.getIdMembro(),antigo.getNomeMembro(),antigo.getArquivoLocal(),antigo.getDataLocal(),antigo.getTipo());

        return novo;
    }

    public static ComprovanteSpring toSpring(Comprovante antigo){
        ComprovanteSpring novo = new ComprovanteSpring(antigo.getId(),antigo.getIdMembro(),antigo.getNomeMembro(),antigo.getArquivo(),antigo.getData(),antigo.getTipo());

        return novo;
    }
}
