package sena.prueba_tecnica2.models;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OnDelete(action =  OnDeleteAction.CASCADE)
    private Integer idCliente;

    @Column(name = "nombre", length = 70, nullable = false)
    private String nombre;

    @Column(name = "apellidos", length = 80, nullable = false)
    private String apellidos;

    @Column(name = "telefono", length = 10, nullable = false)
    private String telefono;

    @Column(name = "email", length = 120, nullable = false, unique = true)
    private String email;

    @Column(name = "numeroDocumento", length = 10, nullable = false, unique = true)
    private String numeroDocumento;

    @ManyToOne()
    @JoinColumn(name = "nitEmpresa")
    private Empresa nitEmpresa;

    @Column(name = "password", length = 120, nullable = false)
    private String password;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<HistorialAcciones> historialesAcciones;
}
