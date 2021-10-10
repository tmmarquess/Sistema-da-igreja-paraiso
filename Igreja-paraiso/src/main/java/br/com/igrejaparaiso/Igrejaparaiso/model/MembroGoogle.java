package br.com.igrejaparaiso.Igrejaparaiso.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.cloud.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table
public class MembroGoogle {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String numero;

    @Column(nullable = false,name = "data_nascimento")
    @DateTimeFormat(iso = ISO.DATE)
    private Date dataNasc;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private String cep;

    @Column(nullable = false)
    private String logradouro;

    @Column(nullable = false)
    private int numeroEnd;

    @Column(nullable = true)
    private String complemento;

    @Column(nullable = false)
    private String bairro;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private String cidade;


    
    public MembroGoogle(String id, String nome, String numero, LocalDate dataNasc, String email, String senha, String cep,
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

    public MembroGoogle() {
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

    public LocalDate getDataNascLocal() {
        return LocalDate.parse(dataNasc.getYear()+"-"+dataNasc.getMonth()+"-"+dataNasc.getDayOfMonth());
    }

    public void setDataNascLocal(LocalDate dataNasc) {
        this.dataNasc = Date.fromYearMonthDay(dataNasc.getYear(), dataNasc.getMonthValue(), dataNasc.getDayOfMonth());
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
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
