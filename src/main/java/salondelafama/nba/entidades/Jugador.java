
package salondelafama.nba.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
//import salondelafama.nba.entidades.Equipo;
import javax.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.OneToOne;


@Entity
public class Jugador {
    
@Id
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name="system-uuid",strategy = "uuid")
private String id;
private String nombre;
private String apellido;
private String descripcion;
private Integer anillos;
private String video;
@OneToOne
private Foto foto;
@OneToOne(cascade = CascadeType.ALL)
private Equipo equipo;

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }



    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

   
 

    public Integer getAnillos() {
        return anillos;
    }

    public void setAnillos(Integer anillos) {
        this.anillos = anillos;
    }

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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "Jugador{" + "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", descripcion=" + descripcion + ", anillos=" + anillos + ", foto=" + foto + ", equipo=" + equipo + '}';
    }

     
  


    
    
}
