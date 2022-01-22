package salondelafama.nba.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import salondelafama.nba.servicios.EquipoServicio;
import java.util.List;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import salondelafama.nba.entidades.Equipo;
import salondelafama.nba.entidades.Jugador;
import salondelafama.nba.errores.ErrorServicio;
import salondelafama.nba.servicios.JugadorServicio;

@Controller
@RequestMapping("/equipo")
public class EquipoControlador {

    @Autowired
    private EquipoServicio equiposervicio;
    @Autowired
    private JugadorServicio jugadorservicio;

    @GetMapping("/registro")
    public String registro(ModelMap modelo) {
        Equipo eq = new Equipo();
        modelo.put("accion", "crear");
        modelo.put("equipo", eq);
        return "registro_equipo.html";
    }

    @PostMapping("/crear")
    public String crear(ModelMap modelo, @ModelAttribute Equipo equipo, MultipartFile archivo, RedirectAttributes attributes) {

        try {
            equiposervicio.crear(equipo, archivo);
            attributes.addFlashAttribute("bien", "SE REGISTRO CON EXITO EL EQUIPO");
            modelo.put("accion", "bien");
            return "redirect:/equipo/lista";
        } catch (Exception e) {
            modelo.put("mal", e.getMessage());
            return "redirect:/equipo/registro";
        }
    }

    @GetMapping("/lista")
    public String listaEquipos(ModelMap modelo) {
        List<Equipo> lista = equiposervicio.ListaDeEquipos();
        modelo.put("equipos", lista);
        modelo.put("accion", "bien");
        return "lista_equipos";
    }

    @GetMapping("/lista-jugadoresxequipo/{id}")
    public String equipojugador(@PathVariable String id, ModelMap modelo) throws ErrorServicio {
        List<Jugador> lista = jugadorservicio.JugadoresxEquipo(id);
        modelo.addAttribute("equiJ", lista);
        return "lista_jug";
    }

    @GetMapping("/mod")
    public String lista_equipo_modificar(ModelMap modelo) {
        List<Equipo> lista = equiposervicio.ListaDeEquipos();
        modelo.put("equipos", lista);
        modelo.put("accion", "mal");
        return "lista_equipos";
    }

    @GetMapping("/modificacion/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo) {
        Equipo equipo = equiposervicio.BuscarXId(id);
        modelo.put("equipo", equipo);
        modelo.put("accion", "modificacion");
        return "registro_equipo.html";
    }

    @PostMapping("modificacion")
    public String modificacion(ModelMap modelo, @ModelAttribute Equipo equipo, MultipartFile archivo) {
        try {
            equiposervicio.modificar(equipo, archivo);
            return "redirect:/";
        } catch (ErrorServicio e) {
            Equipo eq = equiposervicio.BuscarXId(equipo.getId());
            modelo.put("equipo", eq);
            modelo.put("error_mod", "No se pudo modificar el equipo");
            modelo.put("error", e.getMessage());
            return "redirect:/jugador/modificacion/" + equipo.getId();
        }

    }

}
