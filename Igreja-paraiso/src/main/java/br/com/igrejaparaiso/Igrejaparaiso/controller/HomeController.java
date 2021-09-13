package br.com.igrejaparaiso.Igrejaparaiso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {
    
    @GetMapping("/")
    public ModelAndView home(){
        ModelAndView modelo = new ModelAndView("home");
        return modelo;
    }
}
