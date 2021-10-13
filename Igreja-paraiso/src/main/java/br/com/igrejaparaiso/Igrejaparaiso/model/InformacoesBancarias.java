package br.com.igrejaparaiso.Igrejaparaiso.model;

import java.util.Arrays;

public class InformacoesBancarias {
    private String agencia;

    private String conta;

    private String chavePix[];

    

    public InformacoesBancarias(String agencia, String conta, String[] chavePix) {
        this.agencia = agencia;
        this.conta = conta;
        this.chavePix = chavePix;
    }


    public InformacoesBancarias() {
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

    public String[] getChavePix() {
        return chavePix;
    }

    public void setChavePix(String[] chavePix) {
        this.chavePix = chavePix;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((agencia == null) ? 0 : agencia.hashCode());
        result = prime * result + Arrays.hashCode(chavePix);
        result = prime * result + ((conta == null) ? 0 : conta.hashCode());
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
        InformacoesBancarias other = (InformacoesBancarias) obj;
        if (agencia == null) {
            if (other.agencia != null)
                return false;
        } else if (!agencia.equals(other.agencia))
            return false;
        if (!Arrays.equals(chavePix, other.chavePix))
            return false;
        if (conta == null) {
            if (other.conta != null)
                return false;
        } else if (!conta.equals(other.conta))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "InformacoesBancarias [Agencia=" + agencia + ", chavePix=" + Arrays.toString(chavePix) + ", conta="
                + conta + "]";
    }

    
}
