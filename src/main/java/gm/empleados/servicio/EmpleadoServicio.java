package gm.empleados.servicio;

import gm.empleados.modelo.Empleado;
import gm.empleados.repositorio.EmpleadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoServicio implements IEmpleadoSevicio {

    @Autowired
    private EmpleadoRepositorio empleadoRepositorio;


    @Override
    public List<Empleado> listarEmpleados() {
        return empleadoRepositorio.findAll();
    }


    @Override
    public Empleado buscarEmpleadoPorId(Integer id_Empleado) {
        return empleadoRepositorio.findById(id_Empleado).orElseThrow(null);
    }


    @Override
    public void guardarEmpleado(Empleado empleado) {
        empleadoRepositorio.save(empleado);

    }


    @Override
    public void eliminarEmpleado(Empleado empleado) {
        empleadoRepositorio.delete(empleado);

    }
}
