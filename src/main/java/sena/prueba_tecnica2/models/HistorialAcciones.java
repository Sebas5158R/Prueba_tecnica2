package sena.prueba_tecnica2.models;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "historial_acciones")
public class HistorialAcciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OnDelete(action =  OnDeleteAction.CASCADE)
    private Integer idHistorial;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEmpleado")
    private Empleado empleado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "accionRealizada",length = 50, nullable = false)
    private String accionRealizada;

    @Column(name = "descripcion", length = 120, nullable = false)
    private String descripcion;

}
