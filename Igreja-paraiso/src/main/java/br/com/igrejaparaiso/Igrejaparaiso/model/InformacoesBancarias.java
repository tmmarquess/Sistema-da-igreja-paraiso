package br.com.igrejaparaiso.Igrejaparaiso.model;

public class InformacoesBancarias {
    private String id;

    private String agencia;

    private String conta;

    private String banco;

    private String pixEmail;

    private String pixCnpj;

    private String pixTel;



    public InformacoesBancarias(String id, String agencia, String conta, String banco, String pixEmail, String pixCnpj,
            String pixTel) {
        this.id = id;
        this.agencia = agencia;
        this.conta = conta;
        this.banco = banco;
        this.pixEmail = pixEmail;
        this.pixCnpj = pixCnpj;
        this.pixTel = pixTel;
    }

    public InformacoesBancarias(){
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getPixEmail() {
        return pixEmail;
    }

    public void setPixEmail(String pixEmail) {
        this.pixEmail = pixEmail;
    }

    public String getPixCnpj() {
        return pixCnpj;
    }

    public void setPixCnpj(String pixCnpj) {
        this.pixCnpj = pixCnpj;
    }

    public String getPixTel() {
        return pixTel;
    }

    public void setPixTel(String pixTel) {
        this.pixTel = pixTel;
    }

    @Override
    public String toString() {
        return "InformacoesBancarias [agencia=" + agencia + ", banco=" + banco + ", conta=" + conta + ", id=" + id
                + ", pixCnpj=" + pixCnpj + ", pixEmail=" + pixEmail + ", pixTel=" + pixTel + "]";
    }


    
}
