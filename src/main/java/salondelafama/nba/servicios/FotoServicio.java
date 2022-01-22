
package salondelafama.nba.servicios;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import salondelafama.nba.entidades.Foto;
import salondelafama.nba.repositorios.FotoRepositorio;

@Service
public class FotoServicio {
    
    @Autowired
    private FotoRepositorio fotorepositorio;
    
//    public Foto guardarFoto(List<MultipartFile> foto){
//         Foto fot=new Foto();
//        for (MultipartFile multipartFile : foto) {
//         if (multipartFile !=null) {
//            try {
//            fot.setMime(multipartFile.getContentType());
//            fot.setNombre(multipartFile.getName());
//            fot.setContenido(multipartFile.getBytes());
//            return fotorepositorio.save(fot);
//            } catch (Exception e) {
//                System.err.println(e.getMessage());
//            }   
//        }   
//        }
//        
//        return null;
//    }
    
    
    public Foto guardarFoto(MultipartFile foto){
        if (foto !=null) {
            try {
            Foto fot=new Foto();
            fot.setMime(foto.getContentType());
            fot.setNombre(foto.getName());
            fot.setContenido(foto.getBytes());
            return fotorepositorio.save(fot);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }   
        }
        return null;
    }
    
    public Foto actualizarFoto(String id, MultipartFile foto){
        if (foto !=null) {
            try {
                Foto fot=new Foto();
                if (id !=null) {
                    Optional<Foto>respuesta=fotorepositorio.findById(id);
                    if (respuesta.isPresent()) {
                        fot=respuesta.get();
                    }
                    
                }
                fot.setMime(foto.getContentType());
                fot.setNombre(foto.getName());
                fot.setContenido(foto.getBytes());
                return fotorepositorio.save(fot);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }
    
}
