
package salondelafama.nba.repositorios;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import salondelafama.nba.entidades.Jugador;



@Repository
public interface JugadorRepositorio extends JpaRepository<Jugador,String> {
    
    @Query("SELECT f FROM Jugador f WHERE f.id = :id")
    public Jugador BuscarPorJugador(@Param("id")String id);
    
    @Query("SELECT f From Jugador f")
    public List<Jugador>ListaDeJugadores();
    
    @Query("SELECT f FROM Jugador f Where f.nombre = :nombre")
    public Jugador BuscarPorNombre(@Param("nombre")String nombre);
    
    @Query("SELECT f FROM Jugador f Where f.equipo.id = :id")
    public List<Jugador> EquipoJugadores(@Param("id")String id);
    
}
