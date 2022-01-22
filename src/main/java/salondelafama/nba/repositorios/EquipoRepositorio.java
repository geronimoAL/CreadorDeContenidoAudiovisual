
package salondelafama.nba.repositorios;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import salondelafama.nba.entidades.Equipo;


@Repository
public interface EquipoRepositorio extends JpaRepository<Equipo,String>{
    
    @Query("SELECT f FROM Equipo f")
    public List<Equipo>ListaEquipos();
    
    @Query("SELECT f FROM Equipo f Where f.id = :id")
    public Equipo BuscarPorID(@Param("id")String id); 
    @Query("SELECT f FROM Equipo f Where f.nombre = :nombre")
    public Equipo BuscarPorNombre(@Param("nombre")String nombre);
    
}
