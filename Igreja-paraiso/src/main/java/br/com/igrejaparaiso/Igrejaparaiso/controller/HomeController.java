package br.com.igrejaparaiso.Igrejaparaiso.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.igrejaparaiso.Igrejaparaiso.model.Membro;
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
    @GetMapping("/login")
    public ModelAndView login(@RequestParam(required=false, defaultValue = "") String erro){
     ModelAndView modelo = new ModelAndView("Login");
     modelo.addObject("user", new Membro());
     modelo.addObject("erro",erro);
     return modelo;
    }

    @PostMapping("/login")
    public ModelAndView autenticar(Membro login){
        ModelAndView modelo = new ModelAndView();
        Membro teste = repositorio.findByEmailAndSenha(login.getEmail(),login.getSenha());
        if(teste == null){
            modelo.setViewName("redirect:/login/");
            modelo.addObject("erro","Email ou senha incorretos");
        }else{
            modelo.setViewName("logado");
            modelo.addObject("user",teste);
        }
        return modelo;
    }
}
