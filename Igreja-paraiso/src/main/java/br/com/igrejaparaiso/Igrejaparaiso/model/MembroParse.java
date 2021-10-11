package br.com.igrejaparaiso.Igrejaparaiso.model;

public class MembroParse{

    public static Membro toGoogle(MembroSpring antigo){
        Membro novo = new Membro(antigo.getId(), antigo.getNome(),antigo.getImagemLocal(), antigo.getNumero(), antigo.getDataNascLocal(), antigo.getEmail(), antigo.getSenha(), antigo.getCep(), antigo.getLogradouro() , antigo.getNumeroEnd(), antigo.getComplemento(), antigo.getBairro(), antigo.getEstado(), antigo.getCidade());
        
        return novo;
    }

    public static MembroSpring toSpring(Membro antigo){
        MembroSpring novo = new MembroSpring(antigo.getId(), antigo.getNome(),antigo.getImagem(), antigo.getNumero(), antigo.getDataNasc(), antigo.getEmail(), antigo.getSenha(), antigo.getCep(), antigo.getLogradouro() , antigo.getNumeroEnd(), antigo.getComplemento(), antigo.getBairro(), antigo.getEstado(), antigo.getCidade());

        return novo;
    }
}
