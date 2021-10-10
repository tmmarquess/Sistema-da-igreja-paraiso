package br.com.igrejaparaiso.Igrejaparaiso.model;


public class EventoParse {
    
    public static EventoSpring toSpring(Evento antigo){
        EventoSpring novo = new EventoSpring(antigo.getId(), antigo.getNome(), antigo.getDescricao(), antigo.getData(), antigo.getHorario());

        return novo;
    }

    public static Evento toSpring(EventoSpring antigo){
        Evento novo = new Evento(antigo.getId(), antigo.getNome(), antigo.getDescricao(), antigo.getDataLocal(), antigo.getHorario());

        return novo;
    }
}
