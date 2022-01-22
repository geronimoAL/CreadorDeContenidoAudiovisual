package salondelafama.nba.controladores;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import salondelafama.nba.entidades.Equipo;
import salondelafama.nba.entidades.Jugador;
import salondelafama.nba.errores.ErrorServicio;
import salondelafama.nba.repositorios.EquipoRepositorio;
import salondelafama.nba.repositorios.JugadorRepositorio;
import salondelafama.nba.servicios.JugadorServicio;

@Controller
@RequestMapping("/jugador")
public class JugadorControlador {

    @Autowired
    public JugadorRepositorio jugadorrepositorio;
    @Autowired
    public EquipoRepositorio equiporepositorio;
    @Autowired
    public JugadorServicio jugadorservicio;

    @GetMapping("/registro")
    public String registro(ModelMap modelo) {
        List<Equipo> lista = equiporepositorio.findAll();
        modelo.put("lista", lista);
        return "registro_jugador.html";
    }

    @PostMapping("/crear")
    public String crear(ModelMap modelo, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String equipo, @RequestParam Integer campeonatos, MultipartFile archivo, @RequestParam String nota, @RequestParam String video,RedirectAttributes attributes) throws ErrorServicio {
        try {
            jugadorservicio.crearJugador(nombre, apellido, nota,equipo, campeonatos, archivo,video);
            attributes.addFlashAttribute("excelente", "Se cargó con exito el jugador");
            return "redirect:/";
        } catch (ErrorServicio e) {
            List<Equipo> lista = equiporepositorio.ListaEquipos();
            modelo.put("lista", lista);
            attributes.addFlashAttribute("pesimo", e.getMessage());
            return "redirect:/jugador/registro";
        }
    }
    @GetMapping("/lista")
    public String lista_jugadores(ModelMap modelo){
        List<Jugador>lista=jugadorrepositorio.ListaDeJugadores();
         modelo.put("accion", "lista");
        modelo.put("equiJ",lista);
        return "lista_jug";
    }
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id,ModelMap modelo){
//        Jugador jug=jugadorrepositorio.BuscarPorJugador(id);
        List<Equipo>lista=equiporepositorio.ListaEquipos();
        modelo.put("jug",jugadorservicio.buscarPorID(id));
        modelo.put("lista",lista);
        return "modificar_jugador.html";
    }

    @PostMapping("/modificacion")
    public String modificacion(ModelMap modelo,@RequestParam String id ,@RequestParam String nombre, @RequestParam String apellido, @RequestParam String equipo, @RequestParam Integer campeonatos, MultipartFile archivo, @RequestParam String nota,@RequestParam String video, RedirectAttributes attributes){
         Jugador jug=null;
        try {
        jugadorservicio.modificarJugador(id, nombre, apellido,nota, equipo, campeonatos, archivo,video);
        attributes.addFlashAttribute("modificacion","Se modifico con exito el jugador");
        return "redirect:/"; 
        } catch (ErrorServicio e) {
        jug=jugadorservicio.buscarPorID(id);
        List<Equipo>lista=equiporepositorio.ListaEquipos();
        modelo.put("jug",jug);
        modelo.put("lista",lista);
        modelo.put("error", e.getMessage());
           return "redirect:/jugador/modificar/{id}";   
        }
        
    }
            
            
}
