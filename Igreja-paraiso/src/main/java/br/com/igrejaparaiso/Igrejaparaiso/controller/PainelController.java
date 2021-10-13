package br.com.igrejaparaiso.Igrejaparaiso.controller;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import com.google.gson.Gson;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.igrejaparaiso.Igrejaparaiso.model.Evento;
import br.com.igrejaparaiso.Igrejaparaiso.model.Membro;
import br.com.igrejaparaiso.Igrejaparaiso.service.EventoService;

@RestController
@RequestMapping("/painel")
public class PainelController {

    EventoService service;

    public PainelController(EventoService serv){
        service = serv;
    }
    
    @GetMapping("/")
    public ModelAndView redirecionar(Membro logado){
        ModelAndView modelo = new ModelAndView("redirect:/painel/agenda/");
        modelo.addObject("membro", logado);
        return modelo;
    }

    @GetMapping("/agenda")
    public ModelAndView agenda(Membro logado){
        ModelAndView modelo = new ModelAndView("painel/Agenda.html");
        
        String json = "";
		Gson gson = new Gson();
		try {
			ArrayList<Evento> allEventos = service.getAllEventos();
			json +=gson.toJsonTree(allEventos);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

        modelo.addObject("objetoJSON", json);
        if(logado != null){
            modelo.addObject("membro", logado);
        }
        modelo.addObject("nomePagina","Agenda");

        return modelo;
    }
}
