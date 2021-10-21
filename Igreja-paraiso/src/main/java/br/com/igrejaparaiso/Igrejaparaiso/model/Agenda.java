package br.com.igrejaparaiso.Igrejaparaiso.model;

public class Agenda {
    
    private String id;

    private String nome;

    private String diaSemana;

    private String horaInicio;

    private String horaFim;

    private String design;

    private String descricao;

    public Agenda(String id, String nome, String diaSemana, String horaInicio, String horaFim, String design,
            String descricao) {
        this.id = id;
        this.nome = nome;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.design = design;
        this.descricao = descricao;
    }

    public Agenda(){

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

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(String horaFim) {
        this.horaFim = horaFim;
    }

    public String getDesign() {
        return design;
    }

    public void setDesign(String design) {
        this.design = design;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Agenda [descricao=" + descricao + ", design=" + design + ", diaSemana=" + diaSemana + ", horaFim="
                + horaFim + ", horaInicio=" + horaInicio + ", id=" + id + ", nome=" + nome + "]";
    }

    
}
