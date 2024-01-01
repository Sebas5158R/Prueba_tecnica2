import React, { useState } from "react";
import { Link } from "react-router-dom"
import axios from "axios";

const Login = () => {

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  async function login(event) {
    event.preventDefault();

    try {
      await axios.post("http://localhost:8080/api/login", {
        email: email,
        password: password,
      }).then((res) => {
        console.log(res.data);

        if(res.status === 200) {
          alert("Login exitoso")
          window.location.href = '/home_empleado'
        }else {
          alert("Credenciales incorrectas")
        }
      });
    }catch(error) {
      console.log(error);
      alert("Error al intentar iniciar sesión");
    }

  }

  return (
    <div className="hold-transition login-page">
      <div className="login-box">
        <div className="login-logo">
          <Link to={"#"}>
            <b>Inicio de Sesión</b>
          </Link>
        </div>
        <div className="card">
          <div className="card-body login-card-body">
            <p className="login-box-msg">
              Bienvenido, Ingrese sus credenciales
            </p>
            <form onSubmit={'#'}>
              <div className="input-group mb-3">
                <input
                  type="email"
                  className="form-control"
                  placeholder="Email"
                  id="email"
                  name="email"
                  value={email}
                  onChange={(event) => {
                    setEmail(event.target.value);
                  }}
                  required
                />

                <div className="input-group-append">
                  <div className="input-group-text">
                    <span className="fas fa-envelope" />
                  </div>
                </div>
              </div>
              <div className="input-group mb-3">
                <input
                  type="password"
                  className="form-control"
                  placeholder="Password"
                  id="password"
                  name="password"
                  value={password}
                  onChange={(event) => {
                    setPassword(event.target.value);
                  }}
                  required
                />

                <div className="input-group-append">
                  <div className="input-group-text">
                    <span className="fas fa-lock" />
                  </div>
                </div>
              </div>

              <div className="social-auth-links text-center mb-3">
                <button
                  type="submit"
                  className="btn btn-block btn-primary"
                  onClick={login}
                >
                  Ingresar
                </button>
                <Link to={"crearCuenta"} className="btn btn-block btn-danger">
                  Crear una cuenta
                </Link>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};

//
export default Login;