package br.com.igrejaparaiso.Igrejaparaiso.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.igrejaparaiso.Igrejaparaiso.repository.membroRepository;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    membroRepository repositorio;

    @GetMapping("/")
    public ModelAndView home(){
        ModelAndView modelo = new ModelAndView("home");
        //ModelAndView modelo = new ModelAndView("redirect:/membros/");
        return modelo;
    }

}
