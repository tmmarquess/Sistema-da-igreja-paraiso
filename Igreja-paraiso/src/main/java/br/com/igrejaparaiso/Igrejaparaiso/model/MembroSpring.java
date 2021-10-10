package br.com.igrejaparaiso.Igrejaparaiso.model;

import java.time.LocalDate;

public class MembroSpring {
    
    private String id;

    private String nome;

    private String numero;

    private LocalDate dataNasc;

    private String email;

    private String senha;

    private String cep;

    private String logradouro;

    private int numeroEnd;

    private String complemento;

    private String bairro;

    private String estado;

    private String cidade;


    
    public MembroSpring(String id, String nome, String numero, String dataNasc, String email, String senha, String cep,
            String logradouro, int numeroEnd, String complemento, String bairro, String estado, String cidade) {
        this.id = id;
        this.nome = nome;
        this.numero = numero;
        setDataNascLocal(dataNasc);
        this.email = email;
        this.senha = senha;
        this.cep = cep;
        this.logradouro = logradouro;
        this.numeroEnd = numeroEnd;
        this.complemento = complemento;
        this.bairro = bairro;
        this.estado = estado;
        this.cidade = cidade;
    }

    public MembroSpring() {
        //id = null;
    }

    @Override
    public String toString() {
        return "Membro [bairro=" + bairro + ", cep=" + cep + ", cidade=" + cidade + ", complemento=" + complemento
                + ", dataNasc=" + dataNasc + ", email=" + email + ", estado=" + estado + ", id=" + id + ", logradouro="
                + logradouro + ", nome=" + nome + ", numero=" + numero + ", numeroEnd=" + numeroEnd + ", senha=" + senha
                + "]";
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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDataNascLocal() {
        return dataNasc.toString();
    }

    public void setDataNascLocal(String dataNasc) {
        this.dataNasc = LocalDate.parse(dataNasc);
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public int getNumeroEnd() {
        return numeroEnd;
    }

    public void setNumeroEnd(int numeroEnd) {
        this.numeroEnd = numeroEnd;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    

}
