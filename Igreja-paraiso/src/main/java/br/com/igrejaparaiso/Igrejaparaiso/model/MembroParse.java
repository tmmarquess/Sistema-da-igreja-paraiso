package br.com.igrejaparaiso.Igrejaparaiso.model;

public class MembroParse{

    public static Membro toGoogle(MembroSpring antigo){
        Membro novo = new Membro(antigo.getId(), antigo.getNome(),(antigo.getImagemLocal() != null ? antigo.getImagemLocal():""),antigo.getEndereco() ,antigo.isAdm(), antigo.getNumero(), antigo.getDataNascLocal(), antigo.getEmail(), antigo.getSenha(), antigo.getCep(), antigo.getLogradouro() , antigo.getNumeroEnd(), antigo.getComplemento(), antigo.getBairro(), antigo.getEstado(), antigo.getCidade());
        
        return novo;
    }

    public static MembroSpring toSpring(Membro antigo){
        MembroSpring novo = new MembroSpring(antigo.getId(), antigo.getNome(),antigo.getImagem(), antigo.getEndereco(), antigo.isAdm(), antigo.getNumero(), antigo.getDataNasc(), antigo.getEmail(), antigo.getSenha(), antigo.getCep(), antigo.getLogradouro() , antigo.getNumeroEnd(), antigo.getComplemento(), antigo.getBairro(), antigo.getEstado(), antigo.getCidade());

        return novo;
    }
}
