
package salondelafama.nba.entidades;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;



@Entity
public class Equipo {
  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name="system-uuid",strategy = "uuid")
  private String id;
  private String nombre;
  private Integer campeonatos;
  @OneToOne(cascade = CascadeType.ALL)
  private Foto foto;


    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCampeonatos() {
        return campeonatos;
    }

    public void setCampeonatos(Integer campeonatos) {
        this.campeonatos = campeonatos;
    }

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "Equipo{" + "id=" + id + ", nombre=" + nombre + ", campeonatos=" + campeonatos + ", foto=" + foto + '}';
    }

  

   
    
    
   
  
    
}
