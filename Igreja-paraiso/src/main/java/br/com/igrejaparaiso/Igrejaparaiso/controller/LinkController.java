package br.com.igrejaparaiso.Igrejaparaiso.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.igrejaparaiso.Igrejaparaiso.model.LinkDoCulto;
import br.com.igrejaparaiso.Igrejaparaiso.model.LinkDoCultoParse;
import br.com.igrejaparaiso.Igrejaparaiso.model.LinkDoCultoSpring;
import br.com.igrejaparaiso.Igrejaparaiso.service.LinkService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/link")
public class LinkController {

    LinkService service;

    public LinkController(LinkService serv){
        service = serv;
    }
    
    @GetMapping("/cadastrar")
    public ModelAndView cadastro() {
        ModelAndView modelo = new ModelAndView("link/formulario.html");
        modelo.addObject("link", new LinkDoCulto());
        return modelo;
    }

    @PostMapping("/cadastrar")
    public ModelAndView cadastrar(LinkDoCulto link) throws InterruptedException{
        ModelAndView modelo = new ModelAndView("redirect:/painel/links/");

        if(!link.getLink().startsWith("https://")){
            link.setLink("https://"+link.getLink());
        }

        service.cadastrar(link);
        TimeUnit.SECONDS.sleep(2);

        return modelo;
    }

    @GetMapping("/{id}")
    public ModelAndView mostrarLink(@PathVariable String id) throws InterruptedException, ExecutionException{
        ModelAndView modelo = new ModelAndView("link/detalheLink.html");

        LinkDoCultoSpring link = LinkDoCultoParse.toSpring(service.getLinkById(id));

        modelo.addObject("link",link);

        return modelo;
    }
    
    @GetMapping("/{id}/editar")
    public ModelAndView EditaLink(@PathVariable String id) throws InterruptedException, ExecutionException{
        ModelAndView modelo = new ModelAndView("link/formulario.html");

        modelo.addObject("link", service.getLinkById(id));

        return modelo;
    }

    @PostMapping("/{id}/editar")
    public ModelAndView editar(LinkDoCulto link) throws InterruptedException{
        ModelAndView modelo = new ModelAndView("redirect:/painel/links");

        if(!link.getLink().startsWith("https://")){
            link.setLink("https://"+link.getLink());
        }
        TimeUnit.SECONDS.sleep(2);
        service.editar(link);

        return modelo;
    }

    @GetMapping("/{id}/excluir")
    public ModelAndView ApagarLink(@PathVariable String id) throws InterruptedException, ExecutionException{
        ModelAndView modelo = new ModelAndView("redirect:/painel/links");

        service.apagar(id);
        TimeUnit.SECONDS.sleep(2);

        return modelo;
    }
}
