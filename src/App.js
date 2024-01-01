import React, { Fragment } from "react";
import {BrowserRouter as Router, Route, Routes} from "react-router-dom"
import Login from './pages/login';
import CrearCuenta from "./pages/crearCuenta";
import HomeEmpleado from "./pages/empleado/HomeEmpleado";
import ListarEmpleados from "./pages/empleado/ListarEmpleados";

function App() {
  return (
    <Fragment>
      <Router>
        <Routes>
          <Route path="/" exact element = {<Login/>}></Route>
          <Route path="/crearCuenta" exact element = {<CrearCuenta/>}></Route>
          <Route path="/home_empleado" exact element = {<HomeEmpleado/>}/>
          <Route path="/listar_empleados" exact element = {<ListarEmpleados/>}/>
        </Routes>
      </Router>
    </Fragment>
  );
}

export default App;