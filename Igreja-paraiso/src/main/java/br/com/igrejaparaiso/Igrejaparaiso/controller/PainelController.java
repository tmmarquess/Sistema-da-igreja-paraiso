package br.com.igrejaparaiso.Igrejaparaiso.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

import com.google.gson.Gson;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.igrejaparaiso.Igrejaparaiso.model.Evento;
import br.com.igrejaparaiso.Igrejaparaiso.model.LinkDoCulto;
import br.com.igrejaparaiso.Igrejaparaiso.model.LinkDoCultoParse;
import br.com.igrejaparaiso.Igrejaparaiso.model.LinkDoCultoSpring;
import br.com.igrejaparaiso.Igrejaparaiso.model.MembroParse;
import br.com.igrejaparaiso.Igrejaparaiso.model.MembroSpring;
import br.com.igrejaparaiso.Igrejaparaiso.service.EventoService;
import br.com.igrejaparaiso.Igrejaparaiso.service.LinkService;
import br.com.igrejaparaiso.Igrejaparaiso.service.MembroService;

@RestController
@RequestMapping("/painel")
public class PainelController {

    EventoService service;
    MembroService membroserv;
    MembroSpring logado = null;
    LinkService link;

    public PainelController(EventoService serv, MembroService mServ,LinkService link) {
        service = serv;
        membroserv = mServ;
        this.link = link;
    }

    @GetMapping("/")
    public ModelAndView redirecionar(@RequestParam(required = false, defaultValue = "") String id)
            throws InterruptedException, ExecutionException, IOException {
        ModelAndView modelo = new ModelAndView("redirect:/painel/eventos/");
        if (id.equals("")) {
            modelo.setViewName("redirect:/membros/login/");
        } else {
            logado = MembroParse.toSpring(membroserv.getMembroById(id));

            Path path = Paths.get("src/main/resources/static/images/perfil.jpg");
            if (path.toFile().exists()) {
                Files.delete(path);
            }
            if (logado.getImagem() != null) {
                Files.write(path, logado.getImagem());
            }
        }
        return modelo;
    }

    @GetMapping("/eventos")
    public ModelAndView eventos() throws IOException {
        ModelAndView modelo = new ModelAndView("painel/Eventos.html");

        if (logado == null) {
            modelo.setViewName("redirect:/membros/login/");
            return modelo;
        }

        String json = "";
        Gson gson = new Gson();
        try {
            ArrayList<Evento> allEventos = service.getAllEventos();
            json += gson.toJsonTree(allEventos);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        modelo.addObject("objetoJSON", json);

        modelo.addObject("membro", logado);

        modelo.addObject("nomePagina", "Eventos");

        return modelo;
    }

    @GetMapping("/logout")
    public ModelAndView logout() {
        ModelAndView modelo = new ModelAndView("redirect:/");

        logado = null;

        return modelo;
    }

    @GetMapping("/links")
    public ModelAndView LinksCulto() throws InterruptedException, ExecutionException {
        ModelAndView modelo = new ModelAndView("painel/Links.html");

        if (logado == null) {
            modelo.setViewName("redirect:/membros/login/");
            return modelo;
        }

        ArrayList<LinkDoCulto> links = link.getAllLinks();
        ArrayList<LinkDoCultoSpring> linksNovos = new ArrayList<LinkDoCultoSpring>();

        for(LinkDoCulto link : links){
            linksNovos.add(LinkDoCultoParse.toSpring(link));
        }

        Collections.reverse(linksNovos);

        modelo.addObject("links",linksNovos);
        modelo.addObject("nomePagina", "Links dos cultos");
        modelo.addObject("membro", logado);

        return modelo;
    }
}
