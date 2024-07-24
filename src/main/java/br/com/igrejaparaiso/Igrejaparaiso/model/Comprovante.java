package br.com.igrejaparaiso.Igrejaparaiso.model;

import java.time.LocalDate;
import java.util.Base64;

import com.google.cloud.firestore.annotation.Exclude;

public class Comprovante {
    
    private String id;

    private String idMembro;

    private String nomeMembro;

    private String arquivo;

    private String data;

    private String tipo;

    public Comprovante(String id, String idMembro, String nomeMembro, String arquivo, String data, String tipo) {
        this.id = id;
        this.idMembro = idMembro;
        this.nomeMembro = nomeMembro;
        this.arquivo = arquivo;
        this.data = data;
        this.tipo = tipo;
    }

    public Comprovante() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdMembro() {
        return idMembro;
    }

    public void setIdMembro(String idMembro) {
        this.idMembro = idMembro;
    }

    public String getNomeMembro() {
        return nomeMembro;
    }

    public void setNomeMembro(String nomeMembro) {
        this.nomeMembro = nomeMembro;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    @Exclude
    public byte[] getArquivoLocal() {
        byte[] decodedBytes = Base64.getDecoder().decode(arquivo);
        return decodedBytes;

    }

    @Exclude
    public void setArquivoLocal(byte[] arquivo) {
        String encodedString = Base64.getEncoder().encodeToString(arquivo);
        this.arquivo = encodedString;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Exclude
    public LocalDate getDataLocal() {
        return LocalDate.parse(data);
    }

    @Exclude
    public void setDataLocal(LocalDate data) {
        if(data != null){
            this.data = data.toString();
        }else{
            this.data = null;
        }
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Comprovante [arquivo=" + arquivo + ", data=" + data + ", id=" + id + ", idMembro=" + idMembro
                + ", nomeMembro=" + nomeMembro + ", tipo=" + tipo + "]";
    }

    
}
