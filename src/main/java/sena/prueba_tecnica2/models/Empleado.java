package sena.prueba_tecnica2.models;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@Table(name = "empleados")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OnDelete(action =  OnDeleteAction.CASCADE)
    private Integer idEmpleado;

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
    @JoinColumn(name = "idRol")
    private Rol idRol;

    @Column(name = "password", length = 20, nullable = false)
    private String password;

    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL)
    private List<HistorialAcciones> historialesAcciones;

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public Rol getIdRol() {
        return idRol;
    }

    public void setIdRol(Rol idRol) {
        this.idRol = idRol;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<HistorialAcciones> getHistorialesAcciones() {
        return historialesAcciones;
    }

    public void setHistorialesAcciones(List<HistorialAcciones> historialesAcciones) {
        this.historialesAcciones = historialesAcciones;
    }
}
