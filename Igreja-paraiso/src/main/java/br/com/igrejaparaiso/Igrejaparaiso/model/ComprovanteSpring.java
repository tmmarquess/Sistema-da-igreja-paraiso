package br.com.igrejaparaiso.Igrejaparaiso.model;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Base64;

public class ComprovanteSpring {

    private String id;

    private String idMembro;

    private String nomeMembro;

    private byte[] arquivo;

    private LocalDate data;

    private String tipo;

    public ComprovanteSpring(String id, String idMembro, String nomeMembro, String arquivo, String data, String tipo) {
        this.id = id;
        this.idMembro = idMembro;
        this.nomeMembro = nomeMembro;
        setArquivoLocal(arquivo);
        setDataLocal(data);
        this.tipo = tipo;
    }

    public ComprovanteSpring() {
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

    public byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }

    public String getArquivoLocal() {
        String encodedString = Base64.getEncoder().encodeToString(arquivo);
        return encodedString;
    }

    public void setArquivoLocal(String arquivo) {
        if(arquivo != null){
            byte[] decodedBytes = Base64.getDecoder().decode(arquivo);
            this.arquivo = decodedBytes;
        }
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
        if(data != null){
            this.data = LocalDate.parse(data);
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
        return "ComprovanteSpring [arquivo=" + Arrays.toString(arquivo) + ", data=" + data + ", id=" + id
                + ", idMembro=" + idMembro + ", nomeMembro=" + nomeMembro + ", tipo=" + tipo + "]";
    }

    
}