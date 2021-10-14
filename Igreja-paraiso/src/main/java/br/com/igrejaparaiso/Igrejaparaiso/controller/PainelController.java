package br.com.igrejaparaiso.Igrejaparaiso.controller;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import com.google.gson.Gson;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.igrejaparaiso.Igrejaparaiso.model.Evento;
import br.com.igrejaparaiso.Igrejaparaiso.model.Membro;
import br.com.igrejaparaiso.Igrejaparaiso.service.EventoService;
import br.com.igrejaparaiso.Igrejaparaiso.service.MembroService;

@RestController
@RequestMapping("/painel")
public class PainelController {

    EventoService service;
    MembroService membroserv;
    Membro logado = null;

    public PainelController(EventoService serv,MembroService mServ){
        service = serv;
        membroserv = mServ;
    }
    
    @GetMapping("/")
    public ModelAndView redirecionar(@RequestParam(required = false, defaultValue = "") String id) throws InterruptedException, ExecutionException{
        ModelAndView modelo = new ModelAndView("redirect:/painel/agenda/");
        if(id.equals("")){
            modelo.setViewName("redirect:/membros/login/");
        }else{
            logado = membroserv.getMembroById(id);
        }
        return modelo;
    }

    @GetMapping("/agenda")
    public ModelAndView agenda() {
        ModelAndView modelo = new ModelAndView("painel/Agenda.html");

        if(logado == null){
            modelo.setViewName("redirect:/membros/login/");  
            return modelo;  
        }
        
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
