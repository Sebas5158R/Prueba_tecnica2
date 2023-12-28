package sena.prueba_tecnica2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import sena.prueba_tecnica2.services.ClienteServiceImpl;

@Controller
@SessionAttributes("cliente")
@RequestMapping(value = "/cliente")
public class ClienteController {

    @Autowired
    private ClienteServiceImpl clienteServiceImpl;

    @GetMapping(path = { "/listarClientes", "clientes" })
    @ResponseBody
    public String listarClientes(Model m) {
        m.addAttribute("clientes", clienteServiceImpl.findAll());
        return "Acá se listaran los clientes";
    }

}
