package sena.prueba_tecnica2.models;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "empresas")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OnDelete(action =  OnDeleteAction.CASCADE)
    private Integer nitEmpresa;

    @Column(name = "nombreEmpresa", length = 70, nullable = false)
    private String nombreEmpresa;

    @Column(name = "direccion", length = 120, nullable = false)
    private String direccion;

    @Column(name = "documento", length = 200, nullable = false)
    private String documento;
}
