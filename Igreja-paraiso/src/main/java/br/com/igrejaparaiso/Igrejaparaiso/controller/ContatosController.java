package br.com.igrejaparaiso.Igrejaparaiso.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.igrejaparaiso.Igrejaparaiso.model.Contatos;
import br.com.igrejaparaiso.Igrejaparaiso.service.ContatosService;

@RestController
@RequestMapping("/contatos")
public class ContatosController {
    
    ContatosService serv;
    public ContatosController(ContatosService serv) {
        this.serv = serv;
    }

    @PostMapping("/cadastrar")
    public ModelAndView cadastrar(Contatos cntt) throws InterruptedException{
        ModelAndView modelo = new ModelAndView("redirect:/painel/contatos");

        cntt.setEnderecoPadrao();
        cntt.setFacebookPadrao();
        cntt.setInstagramPadrao();

        serv.cadastrar(cntt);

        TimeUnit.SECONDS.sleep(1);

        return modelo;
    }
}
