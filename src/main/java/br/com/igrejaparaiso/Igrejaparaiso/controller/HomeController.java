package br.com.igrejaparaiso.Igrejaparaiso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.igrejaparaiso.Igrejaparaiso.service.EventoService;


@Controller
@RequestMapping("/")
public class HomeController {

    EventoService service;

    public HomeController(EventoService serv) {
        service = serv;
    }

    @GetMapping("/")
    public ModelAndView home(){
        ModelAndView modelo = new ModelAndView("home");
        
        return modelo;
    }

    @GetMapping("/static/**")
    public ModelAndView redireciona(){
        ModelAndView modelo = new ModelAndView("redirect:/painel/");

        return modelo;
    }

}
