package sena.prueba_tecnica2.models;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "roles")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OnDelete(action =  OnDeleteAction.CASCADE)
    private Integer idRol;

    @Column(name="NombreRol", length = 30, nullable = false)
    private String nombreRol;
}
