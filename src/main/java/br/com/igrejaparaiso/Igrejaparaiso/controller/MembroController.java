package br.com.igrejaparaiso.Igrejaparaiso.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.igrejaparaiso.Igrejaparaiso.model.CropImageToSquare;
import br.com.igrejaparaiso.Igrejaparaiso.model.Membro;
import br.com.igrejaparaiso.Igrejaparaiso.model.MembroParse;
import br.com.igrejaparaiso.Igrejaparaiso.model.MembroSpring;
import br.com.igrejaparaiso.Igrejaparaiso.service.EventoService;
import br.com.igrejaparaiso.Igrejaparaiso.service.MembroService;
import net.coobird.thumbnailator.Thumbnails;

@RestController
@RequestMapping("/membros")
public class MembroController {

    MembroService service;
    EventoService serviceEv;

    public MembroController(MembroService serv,EventoService evServ) {
        service = serv;
        serviceEv = evServ;
    }

    @GetMapping("/login")
    public ModelAndView login(@RequestParam(required = false, defaultValue = "") String erro) {
        ModelAndView modelo = new ModelAndView("membros/Login.html");
        /*modelo.addObject("user", new Membro());
        modelo.addObject("erro", erro);*/
        return modelo;
    }

    @GetMapping("/")
    public ModelAndView membros() throws InterruptedException, ExecutionException, IOException {
        ModelAndView modelo = new ModelAndView("membros/membros.html");
        ArrayList<Membro> membrosGoogle = service.getAllMembros();
        ArrayList<MembroSpring> membroSpring = new ArrayList<>();
        for (Membro membro : membrosGoogle) {
            membroSpring.add(MembroParse.toSpring(membro));
        }
        Path path = Paths.get("src/main/resources/static/images/perfil.jpg");        
        if(path.toFile().exists()){
            Files.delete(path);
        }
        modelo.addObject("membros", membroSpring);
        return modelo;
    }

    @GetMapping("/{id}")
    public ModelAndView detalhar(@PathVariable String id) throws InterruptedException, ExecutionException, IOException {
        ModelAndView modelo = new ModelAndView("membros/detalhemembro.html");
        MembroSpring membro = MembroParse.toSpring(service.getMembroById(id));

        Path path = Paths.get("src/main/resources/static/images/perfil.jpg");        
        if(path.toFile().exists()){
            Files.delete(path);
        }
        if (membro.getImagem() != null) {
            Files.write(path, membro.getImagem());
        }

        TimeUnit.SECONDS.sleep(2);

        modelo.addObject("membro", membro);

        return modelo;
    }

    @GetMapping("/{id}/excluir")
    public ModelAndView excluir(@PathVariable String id) {
        ModelAndView modelo = new ModelAndView("redirect:/membros/login/");
        service.apagar(id);
        return modelo;
    }

    @GetMapping("/cadastro")
    public ModelAndView cadastrar() throws InterruptedException, ExecutionException {
        ModelAndView modelo = new ModelAndView("membros/formulario.html");
        modelo.addObject("membro", new Membro());
        modelo.addObject("emailrepetido", "");
        return modelo;
    }

    @PostMapping("/cadastro")
    public ModelAndView cadastrar(@RequestParam("file") MultipartFile file, Membro cli)
            throws InterruptedException, ExecutionException {
        ModelAndView modelo = new ModelAndView("redirect:/membros/login/");
        
        cli.setAdm(false);
        cli.setEnderecoPadrao();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String senhaEncriptada = encoder.encode(cli.getSenha());
        cli.setSenha(senhaEncriptada);

        if (!file.isEmpty()) {
            try {
                // tranforma a imagem em Bytes
                byte[] bytes = file.getBytes();
                //diz o caminho pra onde a imagem vai ser armazenada
                Path path = Paths.get("src/main/resources/static/images/" + file.getOriginalFilename());
                //cria o arquivo na pasta solicitada
                Files.write(path, bytes);

                //diminui o tamanho máximo da imagem pra 300x300
                Thumbnails.of(path.toFile()).size(300, 300).allowOverwrite(true).toFile(path.toFile());

                //corta a imagem em um quadrado
                CropImageToSquare.crop(path);

                cli.setImagemLocal(Files.readAllBytes(path));
                Files.delete(path);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        TimeUnit.SECONDS.sleep(3);
        
        if (!service.cadastrar(cli)) {
            modelo.setViewName("membros/formulario.html");
            modelo.addObject("emailrepetido", "email já cadastrado");
            cli.setId(null);
            modelo.addObject("membro", cli);
        }

        return modelo;
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable String id, Principal principal) throws InterruptedException, ExecutionException {
        ModelAndView modelo = new ModelAndView("membros/formulario.html");
        Membro membro = service.getMembroById(id);

        Membro logado = service.getMembroByEmail(principal.getName());
        modelo.addObject("logado", logado);

        modelo.addObject("membro", membro);

        return modelo;
    }

    @PostMapping("/{id}/editar")
    public ModelAndView editar(@RequestParam("file") MultipartFile file, Membro cli)
            throws InterruptedException, ExecutionException {
        ModelAndView modelo = new ModelAndView("redirect:/painel/membros/");

        cli.setEnderecoPadrao();

        if(!cli.getSenha().isEmpty()){
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String senhaEncriptada = encoder.encode(cli.getSenha());
            cli.setSenha(senhaEncriptada);
        }else{
            cli.setSenha(service.getMembroById(cli.getId()).getSenha());
        }

        if (!file.isEmpty()) {
            try {
                // Get the file and save it somewhere
                byte[] bytes = file.getBytes();
                Path path = Paths.get("src/main/resources/static/images/" + file.getOriginalFilename());
                Files.write(path, bytes);

                //diminui o tamanho máximo da imagem pra 300x300
                Thumbnails.of(path.toFile()).size(300, 300).allowOverwrite(true).toFile(path.toFile());

                //corta a imagem em um quadrado
                CropImageToSquare.crop(path);

                cli.setImagemLocal(Files.readAllBytes(path));
                Files.delete(path);

                path = Paths.get("src/main/resources/static/images/perfil.jpg");
                if (path.toFile().exists()) {
                    Files.delete(path);
                }
                if (cli.getImagem() != null) {
                    Files.write(path, cli.getImagemLocal());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            TimeUnit.SECONDS.sleep(2);
        }else{
            cli.setImagem(service.getMembroById(cli.getId()).getImagem());
        }
        if (!service.editar(cli)) {
            modelo.setViewName("membros/formulario.html");
            modelo.addObject("emailrepetido", "email já cadastrado");
            modelo.addObject("membro", cli);
        }

        return modelo;
    }
}
