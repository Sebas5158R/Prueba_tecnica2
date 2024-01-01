import React from "react";
import Navbar from "../../components/NavBar";
import SidebarContainer from "../../components/SidebarContent";
import ContentHeader from "../../components/ContentHeader";
import Footer from "../../components/Footer";
import { Link } from "react-router-dom";

const HomeEmpleado = () => {
    return (
        <div class="wrapper">
            <Navbar></Navbar>
            <SidebarContainer></SidebarContainer>
            <div className="content-wrapper">

                <ContentHeader
                    titulo={"Dashboard"}
                    breadCrumb1={"Inicio"}
                    breadCrumb2={"Dashboard"}
                    ruta1={"/home"}
                />

                <section className="content">
                    <div className="container-fluid">
                        <div className="row">
                            <div className="col-lg-3 col-6">
                                <div className="small-box bg-info">
                                    <div className="inner">
                                        <h3>Empleados</h3>
                                        <p>&nbsp;</p>
                                    </div>
                                    <div className="icon">
                                        <i className="fa fa-edit" />
                                    </div>
                                    <Link to={"/listar_empleados"} className="small-box-footer">Ver empleados <i className="fas fa-arrow-circle-right" /></Link>
                                </div>

                            </div>

                            <div className="col-lg-3 col-6">
                                <div className="small-box bg-warning">
                                    <div className="inner">
                                        <h3>Clientes</h3>
                                        <p>&nbsp;</p>
                                    </div>
                                    <div className="icon">

                                        <i className="fa fa-chart-pie"></i>
                                    </div>
                                    <Link to={"/listar_clientes"} className="small-box-footer">Ver clientes<i className="fas fa-arrow-circle-right" /></Link>
                                </div>

                            </div>
                        </div>
                    </div>
                </section>
            </div>
            <Footer></Footer>
        </div>
    );
}

export default HomeEmpleado;