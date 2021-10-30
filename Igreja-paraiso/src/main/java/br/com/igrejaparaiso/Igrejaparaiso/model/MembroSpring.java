package br.com.igrejaparaiso.Igrejaparaiso.model;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Base64;

public class MembroSpring {
    
    private String id;

    private String nome;

    private byte[] imagem;

    private String endereco;

    private boolean adm;

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


    
    public MembroSpring(String id, String nome, String imagem, String endereco, boolean adm, String numero, String dataNasc, String email, String senha, String cep,
            String logradouro, int numeroEnd, String complemento, String bairro, String estado, String cidade) {
        this.id = id;
        this.nome = nome;
        setImagemLocal(imagem);
        this.endereco = endereco;
        this.adm = adm;
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
        return "MembroSpring [adm=" + adm + ", bairro=" + bairro + ", cep=" + cep + ", cidade=" + cidade
                + ", complemento=" + complemento + ", dataNasc=" + dataNasc + ", email=" + email + ", estado=" + estado
                + ", id=" + id + ", imagem=" + Arrays.toString(imagem) + ", logradouro=" + logradouro + ", nome=" + nome
                + ", numero=" + numero + ", numeroEnd=" + numeroEnd + ", senha=" + senha + "]";
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (adm ? 1231 : 1237);
        result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
        result = prime * result + ((cep == null) ? 0 : cep.hashCode());
        result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
        result = prime * result + ((complemento == null) ? 0 : complemento.hashCode());
        result = prime * result + ((dataNasc == null) ? 0 : dataNasc.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((estado == null) ? 0 : estado.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + Arrays.hashCode(imagem);
        result = prime * result + ((logradouro == null) ? 0 : logradouro.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((numero == null) ? 0 : numero.hashCode());
        result = prime * result + numeroEnd;
        result = prime * result + ((senha == null) ? 0 : senha.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MembroSpring other = (MembroSpring) obj;
        if (adm != other.adm)
            return false;
        if (bairro == null) {
            if (other.bairro != null)
                return false;
        } else if (!bairro.equals(other.bairro))
            return false;
        if (cep == null) {
            if (other.cep != null)
                return false;
        } else if (!cep.equals(other.cep))
            return false;
        if (cidade == null) {
            if (other.cidade != null)
                return false;
        } else if (!cidade.equals(other.cidade))
            return false;
        if (complemento == null) {
            if (other.complemento != null)
                return false;
        } else if (!complemento.equals(other.complemento))
            return false;
        if (dataNasc == null) {
            if (other.dataNasc != null)
                return false;
        } else if (!dataNasc.equals(other.dataNasc))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (estado == null) {
            if (other.estado != null)
                return false;
        } else if (!estado.equals(other.estado))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (!Arrays.equals(imagem, other.imagem))
            return false;
        if (logradouro == null) {
            if (other.logradouro != null)
                return false;
        } else if (!logradouro.equals(other.logradouro))
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (numero == null) {
            if (other.numero != null)
                return false;
        } else if (!numero.equals(other.numero))
            return false;
        if (numeroEnd != other.numeroEnd)
            return false;
        if (senha == null) {
            if (other.senha != null)
                return false;
        } else if (!senha.equals(other.senha))
            return false;
        return true;
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

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public void setImagemLocal(String imagem) {
        if(imagem != null){
            byte[] decodedBytes = Base64.getDecoder().decode(imagem);
            this.imagem = decodedBytes;
        }else{
            this.imagem = null;
        }
    }

    public String getImagemLocal(){
        String encodedString = null;
        if(imagem != null){
            encodedString = Base64.getEncoder().encodeToString(imagem);
        }
        return encodedString;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setEnderecoPadrao(){
        this.endereco = logradouro + ", "+numeroEnd+". "+bairro+". "+ (complemento.isEmpty() ? "":complemento+". ")+cidade+"/"+estado;
    }

    public boolean isAdm() {
        return adm;
    }

    public void setAdm(boolean adm) {
        this.adm = adm;
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
        if(dataNasc != null){
            this.dataNasc = LocalDate.parse(dataNasc);
        }else{
            this.dataNasc = null;
        }
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
