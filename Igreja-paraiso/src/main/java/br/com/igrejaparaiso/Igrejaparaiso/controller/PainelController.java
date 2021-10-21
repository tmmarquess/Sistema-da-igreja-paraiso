package br.com.igrejaparaiso.Igrejaparaiso.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.igrejaparaiso.Igrejaparaiso.model.Agenda;
import br.com.igrejaparaiso.Igrejaparaiso.model.Comprovante;
import br.com.igrejaparaiso.Igrejaparaiso.model.ComprovanteParse;
import br.com.igrejaparaiso.Igrejaparaiso.model.ComprovanteSpring;
import br.com.igrejaparaiso.Igrejaparaiso.model.Evento;
import br.com.igrejaparaiso.Igrejaparaiso.model.InformacoesBancarias;
import br.com.igrejaparaiso.Igrejaparaiso.model.LinkDoCulto;
import br.com.igrejaparaiso.Igrejaparaiso.model.LinkDoCultoParse;
import br.com.igrejaparaiso.Igrejaparaiso.model.LinkDoCultoSpring;
import br.com.igrejaparaiso.Igrejaparaiso.model.Membro;
import br.com.igrejaparaiso.Igrejaparaiso.model.MembroParse;
import br.com.igrejaparaiso.Igrejaparaiso.model.MembroSpring;
import br.com.igrejaparaiso.Igrejaparaiso.service.AgendaService;
import br.com.igrejaparaiso.Igrejaparaiso.service.ComprovanteService;
import br.com.igrejaparaiso.Igrejaparaiso.service.EventoService;
import br.com.igrejaparaiso.Igrejaparaiso.service.InformacoesBancariasService;
import br.com.igrejaparaiso.Igrejaparaiso.service.LinkService;
import br.com.igrejaparaiso.Igrejaparaiso.service.MembroService;

@RestController
@RequestMapping("/painel")
public class PainelController {

    EventoService service;
    MembroService membroserv;
    MembroSpring logado = null;
    LinkService link;
    InformacoesBancariasService BankServ;
    ComprovanteService compServ;
    AgendaService agendaService;

    public PainelController(EventoService serv, MembroService mServ,LinkService link,InformacoesBancariasService BankServ,ComprovanteService compServ, AgendaService agendaService) throws InterruptedException, ExecutionException {
        service = serv;
        membroserv = mServ;
        this.link = link;
        this.BankServ = BankServ;
        this.compServ = compServ;
        this.agendaService = agendaService;
    }

    @GetMapping("/")
    public ModelAndView redirecionar(Principal principal) throws InterruptedException, ExecutionException, IOException {
        ModelAndView modelo = new ModelAndView("redirect:/painel/agenda/");

        logado = MembroParse.toSpring(membroserv.getMembroByEmail(principal.getName()));

        Path path = Paths.get("src/main/resources/static/images/perfil.jpg");
        if (path.toFile().exists()) {
            Files.delete(path);
        }
        if (logado.getImagem() != null) {
            Files.write(path, logado.getImagem());
        }

        return modelo;
    }

    @GetMapping("/eventos")
    public ModelAndView eventos() throws IOException {
        ModelAndView modelo = new ModelAndView("painel/Eventos.html");

        if(logado == null){
            modelo.setViewName("redirect:/painel/");
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

    @GetMapping("/links")
    public ModelAndView LinksCulto() throws InterruptedException, ExecutionException {
        ModelAndView modelo = new ModelAndView("painel/Links.html");

        if(logado == null){
            modelo.setViewName("redirect:/painel/");
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

    @GetMapping("/pagamentos")
    public ModelAndView pagamentos() throws InterruptedException, ExecutionException{
        ModelAndView modelo = new ModelAndView("painel/pagamentos.html");

        if(logado == null){
            modelo.setViewName("redirect:/painel/");
            return modelo;
        }

        InformacoesBancarias inf = BankServ.getInformacoes();
        ArrayList<Comprovante> antigo = compServ.getComprovantesByMembroId(logado.getId());
        ArrayList<ComprovanteSpring> comps;

        if(antigo!= null){
            comps = new ArrayList<ComprovanteSpring>();
            for(Comprovante parse : antigo){
                comps.add(ComprovanteParse.toSpring(parse));
            }
            comps.sort(Comparator.comparing(ComprovanteSpring::getData));
            Collections.reverse(comps);

            modelo.addObject("comprovantes", comps);
        }else{
            modelo.addObject("comprovantes",new ComprovanteSpring());
        }

        modelo.addObject("nomePagina", "Pagamentos");
        modelo.addObject("membro", logado);

        if(inf == null){
            modelo.addObject("informacoes", new InformacoesBancarias());
        }else{
            modelo.addObject("informacoes",inf);
        }

        modelo.addObject("comprovante",new Comprovante());

        return modelo;
    }

    @PostMapping("/pagamentos/editadados/")
    public ModelAndView editaPagamentos(InformacoesBancarias inform) throws InterruptedException, ExecutionException{
        ModelAndView modelo = new ModelAndView("redirect:/painel/pagamentos/");

        BankServ.cadastrar(inform);

        TimeUnit.SECONDS.sleep(3);

        return modelo;
    }

    @PostMapping("/pagamentos/uploadcomprovante")
    public ModelAndView salvaPdf(@RequestParam("file") MultipartFile file, Comprovante comp) throws InterruptedException{
        ModelAndView modelo = new ModelAndView("redirect:/painel/pagamentos/");

        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get("src/main/resources/static/pdfs/" + file.getOriginalFilename());
            Files.write(path, bytes);

            comp.setArquivoLocal(Files.readAllBytes(path));
            Files.delete(path);

            comp.setData(LocalDate.now().toString());

            comp.setIdMembro(logado.getId());

            compServ.cadastrar(comp);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return modelo;
    }

    @GetMapping("/aniversariantes")
    public ModelAndView aniversariantes() throws InterruptedException, ExecutionException{
        ModelAndView modelo = new ModelAndView("painel/aniversariantes.html");

        if(logado == null){
            modelo.setViewName("redirect:/painel/");
            return modelo;
        }

        ArrayList<Membro> aniversariantes = membroserv.getAniversariantes();

        if(aniversariantes == null){
            modelo.addObject("aniversariantes", new ArrayList<Membro>());
        }else{
            modelo.addObject("aniversariantes", aniversariantes);
        }

        modelo.addObject("nomePagina", "Aniversários");

        modelo.addObject("membro", logado);

        return modelo;
    }

    @GetMapping("/agenda")
    public ModelAndView agenda() throws InterruptedException, ExecutionException{
        ModelAndView modelo = new ModelAndView("painel/agenda.html");

        if(logado == null){
            modelo.setViewName("redirect:/painel/");
            return modelo;
        }

        ArrayList<ArrayList<Agenda>> todos = agendaService.getAllEvs();

        modelo.addObject("evs1", todos.get(0));
        modelo.addObject("evs2", todos.get(1));
        modelo.addObject("evs3", todos.get(2));
        modelo.addObject("evs4", todos.get(3));
        modelo.addObject("evs5", todos.get(4));
        modelo.addObject("evs6", todos.get(5));
        modelo.addObject("evs7", todos.get(6));

        modelo.addObject("nomePagina", "Agenda");

        modelo.addObject("membro", logado);

        modelo.addObject("agenda", new Agenda());

        return modelo;
    }
}
