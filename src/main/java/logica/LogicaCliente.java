package logica;

import java.util.*;
import datos.*;
import entidades.*;

public class LogicaCliente {
	
	private ClienteDao cliDao;
	
	public  LogicaCliente() {
		 cliDao = new ClienteDao();
	}
	
	public List<Cliente> listarClientes(){
		return cliDao.listarClientes();
	}
	
	public List<Cliente> encontrarClienteApellido(Cliente cliente){
		return  cliDao.encontrarClienteApellido(cliente);
	}
	
	public List<Cliente> encontrarClienteDni(Cliente cliente){
		return  cliDao.encontrarClienteDni(cliente);
	}
	
	public Cliente encontrarClienteId(Cliente cliente) {
		return  cliDao.encontrarClienteId(cliente);
	}
	
	public int insertarNuevoCliente(Cliente cliente) {
		return cliDao.insertarNuevoCliente(cliente);
	}
	
	public int modificarCliente(Cliente cliente) {
		return cliDao.modificarCliente(cliente);
	}
	
	public int eliminarCliente(Cliente cliente) {
		return cliDao.eliminarCliente(cliente);
	}
	
	public void cambiarEstadoCliente(Cliente cliente) {
		 try {
			cliDao.cambiarEstadoCliente(cliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
