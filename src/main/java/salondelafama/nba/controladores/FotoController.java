
package salondelafama.nba.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import salondelafama.nba.entidades.Equipo;
import salondelafama.nba.errores.ErrorServicio;
import salondelafama.nba.servicios.EquipoServicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import salondelafama.nba.entidades.Jugador;
import salondelafama.nba.servicios.JugadorServicio;



@Controller
@RequestMapping("/foto")
public class FotoController {
     @Autowired
    public EquipoServicio equiposervicio;
     @Autowired
    public JugadorServicio jugadorservicio;
    
    @GetMapping("/equipo")
    public ResponseEntity<byte[]>fotoequipo(@RequestParam String id){
        try {
            Equipo equipo=equiposervicio.BuscarXId(id);
            if (equipo.getFoto() ==null) {
                throw new ErrorServicio("El jugador no tiene foto asignada");
            }
            byte[] foto=equipo.getFoto().getContenido();
            HttpHeaders headers=new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(foto,headers,HttpStatus.OK);
        } catch (ErrorServicio ex) {
            Logger.getLogger(FotoController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }        
    }
    
    
    @GetMapping("/jugador")
    public ResponseEntity<byte[]>fotojugador(@RequestParam String id){
        try {
            Jugador jugador=jugadorservicio.buscarPorID(id);
            if (jugador.getFoto() ==null) {
                throw new ErrorServicio("El jugador no tiene foto asignada");
            }
            byte[] foto=jugador.getFoto().getContenido();
            HttpHeaders headers=new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(foto,headers,HttpStatus.OK);
        } catch (ErrorServicio ex) {
            Logger.getLogger(FotoController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }        
    }
    
}
