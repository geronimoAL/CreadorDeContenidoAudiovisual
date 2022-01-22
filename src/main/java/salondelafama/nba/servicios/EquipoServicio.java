package salondelafama.nba.servicios;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import salondelafama.nba.entidades.Equipo;
import salondelafama.nba.entidades.Foto;
import salondelafama.nba.errores.ErrorServicio;
import salondelafama.nba.repositorios.EquipoRepositorio;
//import salondelafama.nba.repositorios.FotoServicio;

@Service
public class EquipoServicio {

    @Autowired
    private EquipoRepositorio equiporepositorio;
    @Autowired
    private FotoServicio fotoservicio;

     @Transactional
    public void crear(Equipo equipo , MultipartFile archivo) throws ErrorServicio {
        validaciones(equipo.getNombre());
         if (buscarPorNombre(equipo.getNombre())) {
             throw new ErrorServicio("El nombre del equipo ya se encuentra en la base de datos");
         }
        Equipo eq = new Equipo();
        eq.setNombre(equipo.getNombre());
        if (!archivo.isEmpty()) {
            Foto foto1 = fotoservicio.guardarFoto(archivo);
            eq.setFoto(foto1);
        }

        equiporepositorio.save(eq);

    }
 @Transactional
    public void modificar(Equipo eq,MultipartFile foto) throws ErrorServicio {
        validaciones(eq.getNombre());
        Equipo equipo = equiporepositorio.findById(eq.getId()).get();
        buscarPorNombre(eq.getNombre());
        equipo.setNombre(eq.getNombre());
        Foto fot=fotoservicio.actualizarFoto(equipo.getId(), foto);
        equipo.setFoto(fot);
        equiporepositorio.save(equipo);

    }
  
    public void validaciones(String nombre) throws ErrorServicio {
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre es nulo o esta vacio");
        }

    }
     @Transactional(readOnly=true)
    public List<Equipo> ListaDeEquipos() {
        List<Equipo> lista = equiporepositorio.ListaEquipos();
        return lista;

    }
     @Transactional(readOnly=true)
    public Equipo BuscarXId(String id){
        Equipo equipo=equiporepositorio.findById(id).get();
//        if (equipo !=null) {
             return equipo;
//        }else{
//            throw new ErrorServicio("El id no se ha encontrado");
//        }
       
      
    }
 @Transactional
    public void Eliminar(String id) {
        Equipo equipo = equiporepositorio.BuscarPorNombre(id);
        equiporepositorio.delete(equipo);
    }
 @Transactional(readOnly=true)
    public Boolean buscarPorNombre(String nombre) {
        Equipo equipo = equiporepositorio.BuscarPorNombre(nombre);
        if (equipo != null) {
            return true;
        } else {
            return false;
        }
    }

}
