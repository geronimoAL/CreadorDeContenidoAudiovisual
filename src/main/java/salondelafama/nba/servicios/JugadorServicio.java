package salondelafama.nba.servicios;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import salondelafama.nba.entidades.Equipo;
import salondelafama.nba.entidades.Foto;
import salondelafama.nba.entidades.Jugador;
import salondelafama.nba.errores.ErrorServicio;
import salondelafama.nba.repositorios.EquipoRepositorio;
import salondelafama.nba.repositorios.JugadorRepositorio;

@Service
public class JugadorServicio {

    @Autowired
    private JugadorRepositorio jugadorrepositorio;
    @Autowired 
    private FotoServicio fotoservicio;
    @Autowired
    public EquipoRepositorio equiporepositorio;
    
@Transactional
    public void crearJugador(String nombre, String apellido, String nota,String equipo, Integer campeonatos, MultipartFile foto,String video) throws ErrorServicio {
        validaciones(nombre, apellido);
        if (buscarPorNombre(nombre)) {
            throw new ErrorServicio("El jugador ya está en la base de datos");
        }
        Jugador jugador= new Jugador();
        jugador.setNombre(nombre);
        jugador.setApellido(apellido);
        jugador.setDescripcion(nota);
        jugador.setAnillos(campeonatos);
        jugador.setVideo(video);
         Equipo equip=equiporepositorio.findById(equipo).get();
        jugador.setEquipo(equip);
        Foto foto1=fotoservicio.guardarFoto(foto);
        jugador.setFoto(foto1);
        jugadorrepositorio.save(jugador);
    }
    @Transactional
    public void modificarJugador(String id,String nombre, String apellido, String descripcion, String equipo,Integer anillos, MultipartFile foto,String video) throws ErrorServicio{
        validaciones(nombre, apellido);
//        if (buscarPorNombre(nombre)) {
//            throw new ErrorServicio("El nombre ya está en la base de datos");
//        }
        Jugador jugador=jugadorrepositorio.BuscarPorJugador(id);
        if (jugador !=null) {
            jugador.setNombre(nombre);
        jugador.setApellido(apellido);
        jugador.setDescripcion(descripcion);
        Equipo eq=equiporepositorio.BuscarPorID(equipo);
        jugador.setEquipo(eq);
        jugador.setAnillos(anillos);
        jugador.setVideo(video);
        Foto foto1=fotoservicio.actualizarFoto(id, foto);
        jugador.setFoto(foto1);
        jugadorrepositorio.save(jugador);
        }else{
            throw new ErrorServicio("El id del jugador es nulo y no pudo modificar");
        }
        
    }
    @Transactional(readOnly = true)
    public Jugador buscarPorID(String id) {
        Jugador libro =jugadorrepositorio.BuscarPorJugador(id);
            return libro;
        
    }
    @Transactional
    public void eliminar(String id){
        Jugador jugador=jugadorrepositorio.BuscarPorJugador(id);
        jugadorrepositorio.delete(jugador);
        
    }
    @Transactional(readOnly = true)
    public Jugador BusquedaDeJugador(String id) throws ErrorServicio{
        Jugador jugador=jugadorrepositorio.BuscarPorJugador(id);
        if (jugador !=null) {
            return jugador;
        }else{
            throw new ErrorServicio("No se encontro el jugador.Puede ser q el id sea nulo o no existe el jugador");
        }
    }
    @Transactional(readOnly = true)
    public List<Jugador>ListaJugadores(){
        List<Jugador>lista=jugadorrepositorio.ListaDeJugadores();
        return lista;
    }
    @Transactional(readOnly = true)
     public List<Jugador>JugadoresxEquipo(String id){
        List<Jugador>lista=jugadorrepositorio.EquipoJugadores(id);
        return lista;
    }
    
    public void validaciones(String nombre, String apellido) throws ErrorServicio {
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre es nulo o esta vacio");
        }
        if (apellido == null || nombre.isEmpty()) {
            throw new ErrorServicio("El apellido es nulo o esta vacio");
        }

//        if (descripcion == null || descripcion.isEmpty()) {
//            throw new ErrorServicio("La descripcion es nulo o esta vacio");
//        }

    }
    
    public Boolean buscarPorNombre(String nombre){
        Jugador jugador= jugadorrepositorio.BuscarPorNombre(nombre);
        if (jugador != null) {
            return true;
        }else{
            return false;
        }
    }
}
