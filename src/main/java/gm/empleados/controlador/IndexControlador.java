package gm.empleados.controlador;


import gm.empleados.modelo.Empleado;
import gm.empleados.servicio.EmpleadoServicio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class IndexControlador {

    private static final Logger logger=
    LoggerFactory.getLogger(IndexControlador.class);

    @Autowired
    EmpleadoServicio empleadoServicio;

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String iniciar(ModelMap modelo){

        List<Empleado> empleados = empleadoServicio.listarEmpleados();
        empleados.forEach((empleado) -> logger.info(empleado.toString()));

        //compartimos el modelo con la vista
        modelo.put("empleados", empleados);

        return "index"; //index.jsp
    }


    @RequestMapping(value="/agregar", method = RequestMethod.GET)
    public String mostrarFormulario(){
        return "/agregar";  //agregar.jsp

    }

    @RequestMapping(value="/agregar", method = RequestMethod.POST)
    public String agregar(@ModelAttribute ("empleadoForma") Empleado empleado){
        logger.info("Empleado a registrar" + empleado);
        empleadoServicio.guardarEmpleado(empleado);
        return "redirect:/"; //redirige al path "/"  de inicio
    }


    @RequestMapping(value = "/editar", method = RequestMethod.GET)
    public String mostrarEditar(@RequestParam int idEmpleado, ModelMap modelo){
        Empleado empleado = empleadoServicio.buscarEmpleadoPorId(idEmpleado);
        logger.info("Empleado a Editar: " + empleado);
        modelo.put("empleado", empleado);
        return "/editar";  //mostrar editar.jsp

    }

    @RequestMapping(value = "/editar", method = RequestMethod.POST)
    public String editar(@ModelAttribute("empleadoForma") Empleado empleado){
        logger.info("Emplaedo a editar: " + empleado);
        empleadoServicio.guardarEmpleado(empleado);
        return "redirect:/"; //redirigimos a la ruta de inicio ya con los cambios
    }


    @RequestMapping(value = "/eliminar", method = RequestMethod.GET)
    public String eliminar(@RequestParam int idEmpleado ){
        Empleado empleado = new Empleado();
        empleado.setIdEmpleado(idEmpleado);
        logger.info("Empleado a eliminar: " + empleado);
        empleadoServicio.eliminarEmpleado(empleado);
        return "redirect:/";  //redirigimos a la ruta de inicio ya con los cambios
    }

}
