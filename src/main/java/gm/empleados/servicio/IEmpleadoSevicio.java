package gm.empleados.servicio;

import gm.empleados.modelo.Empleado;

import java.util.List;

public interface IEmpleadoSevicio {

    public List<Empleado> listarEmpleados();

    public Empleado buscarEmpleadoPorId(Integer id_Empleado);

    public void guardarEmpleado(Empleado empleado);

    public void eliminarEmpleado(Empleado empleado);

}
