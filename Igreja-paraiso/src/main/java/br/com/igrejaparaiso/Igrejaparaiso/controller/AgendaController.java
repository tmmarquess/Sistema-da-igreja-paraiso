package br.com.igrejaparaiso.Igrejaparaiso.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.igrejaparaiso.Igrejaparaiso.model.Agenda;
import br.com.igrejaparaiso.Igrejaparaiso.service.AgendaService;

@RestController
@RequestMapping("/agenda")
public class AgendaController {

    AgendaService serv;

    public AgendaController(AgendaService serv){
        this.serv = serv;
    }
    
    @GetMapping("/cadastrar")
    public ModelAndView cadastro(){
        ModelAndView modelo = new ModelAndView("agenda/formulario.html");

        modelo.addObject("agenda", new Agenda());

        return modelo;
    }

    @PostMapping("/cadastrar")
    public ModelAndView cadastrar(Agenda ev) throws InterruptedException{
        ModelAndView modelo = new ModelAndView("redirect:/painel/agenda/");

            serv.cadastrar(ev);
            TimeUnit.SECONDS.sleep(3);

        return modelo;
    }

    @PostMapping("/editar")
    public ModelAndView editar(Agenda ev) throws InterruptedException{
        ModelAndView modelo = new ModelAndView("redirect:/painel/agenda/");

            serv.editar(ev);
            TimeUnit.SECONDS.sleep(3);

        return modelo;
    }

    @GetMapping("{id}/excluir")
    public ModelAndView apagar(@PathVariable String id) throws InterruptedException{
        ModelAndView modelo = new ModelAndView("redirect:/painel/agenda/");

            serv.apagar(id);
            TimeUnit.SECONDS.sleep(3);

        return modelo;
    }
}
