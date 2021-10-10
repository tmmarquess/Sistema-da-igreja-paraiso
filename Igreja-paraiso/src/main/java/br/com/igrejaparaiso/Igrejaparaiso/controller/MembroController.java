package br.com.igrejaparaiso.Igrejaparaiso.controller;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.igrejaparaiso.Igrejaparaiso.model.Membro;
import br.com.igrejaparaiso.Igrejaparaiso.model.MembroParse;
import br.com.igrejaparaiso.Igrejaparaiso.model.MembroSpring;
import br.com.igrejaparaiso.Igrejaparaiso.repository.membroRepository;
import br.com.igrejaparaiso.Igrejaparaiso.service.MembroService;

@RestController
@RequestMapping("/membros")
public class MembroController {

    @Autowired
    membroRepository repositorio;

    MembroService service;

    public MembroController(MembroService serv){
        service = serv;
    }

    @GetMapping("/login")
    public ModelAndView login(@RequestParam(required=false, defaultValue = "") String erro){
     ModelAndView modelo = new ModelAndView("Login");
     modelo.addObject("user", new Membro());
     modelo.addObject("erro",erro);
     return modelo;
    }

    @PostMapping("/login")
    public ModelAndView autenticar(Membro login) throws InterruptedException, ExecutionException{
        ModelAndView modelo = new ModelAndView();
        Membro teste = service.login(login);
        MembroSpring membro = MembroParse.toSpring(teste);
        if(teste == null){
            modelo.setViewName("redirect:/login/");
            modelo.addObject("erro","Email ou senha incorretos");
        }else{
            modelo.setViewName("logado");
            modelo.addObject("user",membro);
        }
        return modelo;
    }

    @GetMapping("/")
    public ModelAndView membros() throws InterruptedException, ExecutionException {
        ModelAndView modelo = new ModelAndView("membros/membros.html");
        ArrayList<Membro> membrosGoogle = service.getAllMembros();
        ArrayList<MembroSpring> membroSpring = new ArrayList<>();
        for(Membro membro : membrosGoogle){
            membroSpring.add(MembroParse.toSpring(membro));
        }

        modelo.addObject("membros", membroSpring);
        return modelo;
    }

    @GetMapping("/{id}")
    public ModelAndView detalhar(@PathVariable String id) throws InterruptedException, ExecutionException {
        ModelAndView modelo = new ModelAndView("membros/detalhemembro.html");
        MembroSpring membro = MembroParse.toSpring(service.getMembroById(id));

        modelo.addObject("membro", membro);

        return modelo;
    }

    @GetMapping("/{id}/excluir")
    public ModelAndView excluir(@PathVariable String id) {
        ModelAndView modelo = new ModelAndView("redirect:/membros/");
        service.apagar(id);
        return modelo;
    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        ModelAndView modelo = new ModelAndView("membros/formulario.html");
        modelo.addObject("membro", new Membro());
        return modelo;
    }

    @PostMapping("/cadastrar")
    public ModelAndView cadastrar(Membro cli) {
        ModelAndView modelo = new ModelAndView("redirect:/membros/login/");
        service.cadastrar(cli);
        return modelo;
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable String id) throws InterruptedException, ExecutionException {
        ModelAndView modelo = new ModelAndView("membros/formulario.html");
        Membro membro = service.getMembroById(id);

        modelo.addObject("membro", membro);

        return modelo;
    }

    @PostMapping("/{id}/editar")
    public ModelAndView editar(Membro cli) {
        ModelAndView modelo = new ModelAndView("redirect:/membros/");

        service.editar(cli);

        return modelo;
    }
}
