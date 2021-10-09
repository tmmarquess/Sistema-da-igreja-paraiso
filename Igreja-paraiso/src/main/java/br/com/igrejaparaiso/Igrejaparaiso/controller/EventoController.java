package br.com.igrejaparaiso.Igrejaparaiso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.igrejaparaiso.Igrejaparaiso.model.Evento;
import br.com.igrejaparaiso.Igrejaparaiso.repository.eventoRepository;

@Controller
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    eventoRepository repositorio;

    @GetMapping("/")
    public ModelAndView eventos(){
        ModelAndView modelo = new ModelAndView("eventos/eventos.html");
        modelo.addObject("eventos",repositorio.findAll());
        return modelo;
    }

    @GetMapping("/{id}")
    public ModelAndView detalhar(@PathVariable long id){
        ModelAndView modelo = new ModelAndView("eventos/detalheevento.html");

        modelo.addObject("evento", repositorio.getById(id));

        return modelo;
    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        ModelAndView modelo = new ModelAndView("eventos/formulario.html");
        modelo.addObject("evento",new Evento());
        return modelo;
    }

    @PostMapping("/cadastrar")
    public ModelAndView cadastrar(Evento eve){
        ModelAndView modelo = new ModelAndView("redirect:/eventos/");
        repositorio.save(eve);
        return modelo;
    }
    
    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable long id) {
        ModelAndView modelo = new ModelAndView("eventos/formulario.html");

        modelo.addObject("evento",repositorio.getById(id));

        return modelo;
    }

    @PostMapping("/{id}/editar")
    public ModelAndView editar(Evento eve) {
        ModelAndView modelo = new ModelAndView("redirect:/eventos/");

        repositorio.save(eve);

        return modelo;
    }

    @GetMapping("/{id}/excluir")
    public ModelAndView excluir(@PathVariable long id) {
        ModelAndView modelo = new ModelAndView("redirect:/eventos/");
        repositorio.deleteById(id);
        return modelo;
    }
}
