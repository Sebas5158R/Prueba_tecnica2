import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom"
import axios from "axios";

const Login = () => {

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [redirect, setRedirect] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    if (redirect) {
      console.log("Redirigiendo a /home_empleado");
      navigate("/home_empleado");
    }
  }, [redirect, navigate]);

  async function login(event) {
    event.preventDefault();

    try {
      const res = await axios.post("http://localhost:8080/api/login", {
        email: email,
        password: password,
      });

      console.log(res.data);

      if (res.status === 200) {
        localStorage.setItem("token", res.data);
        alert("Login exitoso");
        setRedirect(true);
      } else {
        alert("Credenciales incorrectas");
      }

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
            <form onSubmit={login}>
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