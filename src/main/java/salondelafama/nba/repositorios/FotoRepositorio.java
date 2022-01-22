
package salondelafama.nba.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import salondelafama.nba.entidades.Foto;


@Repository
public interface FotoRepositorio extends JpaRepository<Foto,String> {
    
}
