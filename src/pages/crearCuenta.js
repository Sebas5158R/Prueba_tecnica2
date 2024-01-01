import React from "react";
import { Link } from 'react-router-dom';

const CrearCuenta = () => {

return (
    <div className="hold-transition register-page">
      <div className="register-box">
        <div className="register-logo">
          <Link to={"#"}>
            <b>Bienve</b>nido
          </Link>
        </div>
        <div className="card">
          <div className="card-body register-card-body">
            <p className="login-box-msg">Registrate, para poder continuar</p>

            {/* Form */}
            <form onSubmit={'onSubmit'}>
              {/* Div for name */}
              <div className="input-group mb-3">
                <input
                  type="text"
                  className="form-control"
                  placeholder="Nombre"
                  name="nombre"
                  id="nombre"
                  required
                />
                <div className="input-group-append">
                  <div className="input-group-text">
                    <span className="fas fa-user" />
                  </div>
                </div>
              </div>

              <div className="input-group mb-3">
                <input
                  type="text"
                  className="form-control"
                  placeholder="Apellidos "
                  name="apellidos"
                  id="apellidos"
                  required
                />
                <div className="input-group-append">
                  <div className="input-group-text">
                    <span className="fas fa-user" />
                  </div>
                </div>
              </div>

              {/* Div for email */}
              <div className="input-group mb-3">
                <input
                  type="email"
                  className="form-control"
                  placeholder="Email"
                  name="email"
                  required
                />
                <div className="input-group-append">
                  <div className="input-group-text">
                    <span className="fas fa-envelope" />
                  </div>
                </div>
              </div>

              {/* Div for document number */}
              <div className="input-group mb-3">
                <input
                  type="number"
                  className="form-control"
                  placeholder="Numero de documento"
                  name="numeroDocumento"
                  required
                />
                <div className="input-group-append">
                  <div className="input-group-text">
                    <span className="fas fa-user" />
                  </div>
                </div>
              </div>

              {/* Div for password */}
              <div className="input-group mb-3">
                <input
                  type="password"
                  className="form-control"
                  placeholder="Password"
                  name="password"
                  value={'password'}
                  onChange={'onChange'}
                  required
                />
                <div className="input-group-append">
                  <div className="input-group-text">
                    <span className="fas fa-lock" />
                  </div>
                </div>
              </div>

              <div className="row">
                <div className="col-8">
                  <div className="icheck-primary">
                    <input
                      type="checkbox"
                      id="agreeTerms"
                      name="terms"
                      defaultValue="agree"
                    />
                    <label htmlFor="agreeTerms">
                      Acepto los <Link to={"#"}>terminos y condiciones</Link>
                    </label>
                  </div>
                </div>
                <div className="col-4">
                  <button type="submit" className="btn btn-primary btn-block">
                    Register
                  </button>
                </div>
              </div>
            </form>
            <div className="social-auth-links text-center">
              <p>- OR -</p>
              <Link to={"/"} className="btn btn-block btn-danger">
                Cancelar
              </Link>
            </div>
            <Link to={"/"} className="text-center">
              ¿Ya tienes una cuenta?
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
};

export default CrearCuenta;