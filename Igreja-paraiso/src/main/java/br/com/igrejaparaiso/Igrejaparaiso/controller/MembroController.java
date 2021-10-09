package br.com.igrejaparaiso.Igrejaparaiso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.igrejaparaiso.Igrejaparaiso.model.Membro;
import br.com.igrejaparaiso.Igrejaparaiso.repository.membroRepository;

@Controller
@RequestMapping("/membros")
public class MembroController {

    @Autowired
    membroRepository repositorio;

    @GetMapping("/")
    public ModelAndView membros() {
        ModelAndView modelo = new ModelAndView("membros/membros.html");
        modelo.addObject("membros", repositorio.findAll());
        return modelo;
    }

    @GetMapping("/{id}")
    public ModelAndView detalhar(@PathVariable long id) {
        ModelAndView modelo = new ModelAndView("membros/detalhemembro.html");

        modelo.addObject("membro", repositorio.getById(id));

        return modelo;
    }

    @GetMapping("/{id}/excluir")
    public ModelAndView excluir(@PathVariable long id) {
        ModelAndView modelo = new ModelAndView("redirect:/membros/");
        repositorio.deleteById(id);
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
        ModelAndView modelo = new ModelAndView("redirect:/login/");
        repositorio.save(cli);
        return modelo;
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable long id) {
        ModelAndView modelo = new ModelAndView("membros/formulario.html");

        modelo.addObject("membro", repositorio.getById(id));

        return modelo;
    }

    @PostMapping("/{id}/editar")
    public ModelAndView editar(Membro cli) {
        ModelAndView modelo = new ModelAndView("redirect:/membros/");

        repositorio.save(cli);

        return modelo;
    }
}
