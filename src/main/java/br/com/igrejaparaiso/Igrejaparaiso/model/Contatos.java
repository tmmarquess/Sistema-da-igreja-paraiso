package br.com.igrejaparaiso.Igrejaparaiso.model;

public class Contatos {

    private String email;

    private String endereco;

    private String telefone;

    private String instagram;

    private String facebook;

    private String linkInstagram;

    private String linkFacebook;

    private String cep;

    private String logradouro;

    private int numeroEnd;

    private String complemento;

    private String bairro;

    private String estado;

    private String cidade;

    

    public Contatos(String email, String endereco, String telefone, String instagram, String facebook,
            String linkInstagram, String linkFacebook, String cep, String logradouro, int numeroEnd, String complemento,
            String bairro, String estado, String cidade) {
        this.email = email;
        this.endereco = endereco;
        this.telefone = telefone;
        this.instagram = instagram;
        this.facebook = facebook;
        this.linkInstagram = linkInstagram;
        this.linkFacebook = linkFacebook;
        this.cep = cep;
        this.logradouro = logradouro;
        this.numeroEnd = numeroEnd;
        this.complemento = complemento;
        this.bairro = bairro;
        this.estado = estado;
        this.cidade = cidade;
    }

    public Contatos(){
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public void setInstagramPadrao(){
        String insta = this.instagram;

        if(insta.startsWith("@")){
            setLinkInstagram("https://instagram.com/"+instagram.substring(1));
        }else{
            if(insta.startsWith("instagram.com") || insta.startsWith("www")){
                setLinkInstagram("https://"+insta);
            }else{
                if(insta.startsWith("https://")){
                    setLinkInstagram(insta);
                }else{
                    setLinkInstagram("https://instagram.com/"+insta);
                }
            }
        }
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public void setFacebookPadrao(){
        String facebook = this.facebook;

        if(facebook.startsWith("/")){
            setLinkFacebook("https://facebook.com"+facebook);
        }else{
            if(facebook.startsWith("facebook.com") || facebook.startsWith("www")){
                setLinkFacebook("https://"+facebook);
            }else{
                if(facebook.startsWith("https://")){
                    setLinkFacebook(linkFacebook);
                }else{
                    setLinkFacebook("https://facebook.com/"+facebook);
                }
            }
        }
    }

    public String getLinkInstagram() {
        return linkInstagram;
    }

    public void setLinkInstagram(String linkInstagram) {
        this.linkInstagram = linkInstagram;
    }

    public String getLinkFacebook() {
        return linkFacebook;
    }

    public void setLinkFacebook(String linkFacebook) {
        this.linkFacebook = linkFacebook;
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
