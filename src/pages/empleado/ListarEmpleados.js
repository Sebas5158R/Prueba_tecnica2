import React, { useEffect, useState } from "react";
import Modal from "react-modal";
import Navbar from "../../components/NavBar";
import SidebarContainer from "../../components/SidebarContent";
import ContentHeader from "../../components/ContentHeader";
import Footer from "../../components/Footer";
import axios from "axios";


const RegistroEmpleadoForm = ({ onSubmit }) => {
    const [nombre, setNombre] = useState("");
    const [apellidos, setApellidos] = useState("");
    const [email, setEmail] = useState("");
    const [telefono, setTelefono] = useState("");
    const [numeroDocumento, setNumeroDocumento] = useState("");
    const [idRol, setIdRol] = useState("");
    const [password, setPassword] = useState("");
  
    const handleSubmit = async (event) => {
      event.preventDefault();
  
      // Enviar la solicitud al backend
      const nuevoEmpleado = {
        nombre,
        apellidos,
        email,
        telefono,
        numeroDocumento,
        rol: {
            idRol
        },
        password
      };
  
      onSubmit(nuevoEmpleado);
  
      setNombre("");
      setApellidos("");
      setEmail("");
      setTelefono("");
      setNumeroDocumento("");
      setIdRol("");
      setPassword("");
    };
  
    return (
      <form onSubmit={handleSubmit}>
        <label>
          Nombre:
          <input type="text" value={nombre} onChange={(e) => setNombre(e.target.value)} />
        </label>
        <label>
          Apellidos:
          <input type="text" value={apellidos} onChange={(e) => setApellidos(e.target.value)} />
        </label>
        <label>
          Email:
          <input type="text" value={email} onChange={(e) => setEmail(e.target.value)} />
        </label>
        <label>
          Telefono:
          <input type="number" value={telefono} onChange={(e) => setTelefono(e.target.value)} />
        </label>
        <label>
          Numero de documento:
          <input type="number" value={numeroDocumento} onChange={(e) => setNumeroDocumento(e.target.value)} />
        </label>
        <label>
          Rol:
          <select value={idRol} onChange={(e) => setIdRol(e.target.value)}>
            <option value={1}>Super administrador</option>
            <option value={2}>Administrador</option>
            <option value={3}>Empleado</option>
          </select>
        </label>
        <label>
          Password:
          <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
        </label>
        <button type="submit">Registrar</button>
      </form>
    );
  };

const ListarEmpleados = () => {

    const [empleados, setEmpleados] = useState([]);
    const [modalIsOpen, setModalIsOpen] = useState(false);

    useEffect(() => {
        axios.get("http://localhost:8080/empleado/listarEmpleados")
          .then(response => {
            const empleadosOrdenados = response.data.sort((a, b) => a.idEmpleado - b.idEmpleado);
            setEmpleados(empleadosOrdenados);
          })
          .catch(error => {
            console.error("Error al obtener la lista de empleados:", error);
          });
      }, []);

    const openModal = () => {
        setModalIsOpen(true);
    };
    
    const closeModal = () => {
        setModalIsOpen(false);
    };
    
    const handleRegistroEmpleado = async (nuevoEmpleado) => {
        try {
        const response = await axios.post("http://localhost:8080/empleado/add", nuevoEmpleado);
    
        if (response.status === 200) {
            setEmpleados([...empleados, response.data]);
            closeModal();
            window.location.reload();

        } else {
            console.error("Error al registrar empleado:", response.data);
        }
        } catch (error) {
        console.error("Error al realizar la solicitud al backend:", error);
        }
    };

    return ( 
        <div className="wrapper">
      <Navbar></Navbar>
      <SidebarContainer></SidebarContainer>
      <div className="content-wrapper">
        <ContentHeader
          titulo={"Listado de empleados"}
          breadCrumb1={"Inicio"}
          breadCrumb2={"Empleados"}
          ruta1={"/home"}
        />

        <section className="content">
          <div className="card">
            <div className="card-header">
              <h3 className="card-title">
                <button className="btn btn-block btn-primary btn-sm" onClick={openModal}>
                    Registrar Empleado
                </button>
              </h3>

              <div className="card-tools">
                <button
                  type="button"
                  className="btn btn-tool"
                  data-card-widget="collapse"
                  title="Collapse"
                >
                  <i className="fas fa-minus"></i>
                </button>
                <button
                  type="button"
                  className="btn btn-tool"
                  data-card-widget="remove"
                  title="Remove"
                >
                  <i className="fas fa-times"></i>
                </button>
              </div>
            </div>
            <div className="card-body">
              <table className="table table-bordered">
                <thead>
                  <tr>
                    <th>#</th>
                    <th>Nombre</th>
                    <th>Apellidos</th>
                    <th>Email</th>
                    <th>Numero de documento</th>
                    <th>Telefono</th>
                    <th>Rol</th>
                    <th>Acciones</th>
                  </tr>
                </thead>
                <tbody>
                {empleados.map((empleado, index) => (
                    <tr key={index}>
                        <td>{empleado.idEmpleado}</td>
                        <td>{empleado.nombre}</td>
                        <td>{empleado.apellidos}</td>
                        <td>{empleado.email}</td>
                        <td>{empleado.numeroDocumento}</td>
                        <td>{empleado.telefono}</td>
                        <td>{empleado.rol ? empleado.rol.nombreRol : 'Sin Rol'}</td>
                    </tr>
                ))}
                </tbody>
              </table>
            </div>
          </div>
        </section>

        <Modal isOpen={modalIsOpen} onRequestClose={closeModal} contentLabel="Registrar Nuevo Empleado"
        style={{
            overlay: {
              backgroundColor: 'rgba(0, 0, 0, 0.5)',
              zIndex: 1000
            },
            content: {
              zIndex: 1001,
              width: '60%',
              margin: 'auto',
              maxWidth: '600px',
              marginTop: '50px'
            },
          }}>
            <h2>Registrar Nuevo Empleado</h2>
            <RegistroEmpleadoForm onSubmit={handleRegistroEmpleado} />
        </Modal>
      </div>
      <Footer></Footer>
    </div>
     );

}

export default ListarEmpleados;