package br.com.igrejaparaiso.Igrejaparaiso.controller;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.igrejaparaiso.Igrejaparaiso.model.Evento;
import br.com.igrejaparaiso.Igrejaparaiso.model.EventoParse;
import br.com.igrejaparaiso.Igrejaparaiso.model.EventoSpring;
import br.com.igrejaparaiso.Igrejaparaiso.service.EventoService;

@Controller
@RequestMapping("/eventos")
public class EventoController {

    EventoService service;

    public EventoController(EventoService serv){
        service = serv;
    }

    @GetMapping("/")
    public ModelAndView eventos() throws InterruptedException, ExecutionException{
        ModelAndView modelo = new ModelAndView("eventos/eventos.html");

        ArrayList<Evento> antes = service.getAllEventos();
        ArrayList<EventoSpring> eventos = new ArrayList<>();
        for(Evento evento : antes){
            eventos.add(EventoParse.toSpring(evento));
        }

        modelo.addObject("eventos",eventos);
        return modelo;
    }

    @GetMapping("/{id}")
    public ModelAndView detalhar(@PathVariable String id) throws InterruptedException, ExecutionException{
        ModelAndView modelo = new ModelAndView("eventos/detalheevento.html");
        EventoSpring evento = EventoParse.toSpring(service.getEventoById(id));

        modelo.addObject("evento", evento);

        return modelo;
    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        ModelAndView modelo = new ModelAndView("eventos/formulario.html");
        modelo.addObject("evento",new Evento());
        return modelo;
    }

    @PostMapping("/cadastrar")
    public ModelAndView cadastrar(Evento eve) throws InterruptedException{
        ModelAndView modelo = new ModelAndView("redirect:/painel/eventos/");
        service.cadastrar(eve);

        TimeUnit.SECONDS.sleep(2);
        return modelo;
    }
    
    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable String id) throws InterruptedException, ExecutionException {
        ModelAndView modelo = new ModelAndView("eventos/formulario.html");

        modelo.addObject("evento",service.getEventoById(id));

        return modelo;
    }

    @PostMapping("/{id}/editar")
    public ModelAndView editar(Evento eve) throws InterruptedException {
        ModelAndView modelo = new ModelAndView("redirect:/painel/eventos/");

        service.editar(eve);
        TimeUnit.SECONDS.sleep(2);

        return modelo;
    }

    @GetMapping("/{id}/excluir")
    public ModelAndView excluir(@PathVariable String id) {
        ModelAndView modelo = new ModelAndView("redirect:/painel/eventos/");
        service.apagar(id);
        return modelo;
    }
}
