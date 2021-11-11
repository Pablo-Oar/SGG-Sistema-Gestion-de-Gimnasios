package logica;

import java.util.List;

import datos.MembresiaDao;
import entidades.*;

public class LogicaMembresia {

	private MembresiaDao memDao;
	
	public LogicaMembresia() {
		memDao = new MembresiaDao();
	}
	
	
	public int modificarMembresiaTipo(Cliente cliente) {
		return  memDao.modificarMembresiaTipo(cliente);
	}
	
	public int setearTipoMembresia(Cliente cliente) {
		return memDao.setearTipoMembresia(cliente);
	}
	
	public int insertarIdMemebresia(Cliente cliente) {
		return memDao.insertarIdMemebresia(cliente);
	}
	
	public List<Membresia> listarMembresias(){
		return memDao.listarMembresias();
	}
	
	public List<Membresia> encontrarMembresiaPorTipo(Membresia membresiaABuscar){
		return memDao.getByType(membresiaABuscar);
	}
	
	public Membresia encontrarMembresiaId(Membresia membresiaABuscar) {
		return  memDao.encontrarMembresiaById(membresiaABuscar);
	}
	
	public int insertarNuevaMembresia(Membresia membresiaAInsertar) {
		return memDao.insertarMembresia(membresiaAInsertar);
	}
	
	
	public int modificarMembresia(Membresia membresiaAModificar) {
		return memDao.modificarMembresia(membresiaAModificar);
	}
	
	
//	public int eliminarMembresia(CMembresia membresiaAEliminar) {
//		return memDao.eliminarMembresia(membresiaAEliminar);
//	}
	
	public void cambiarEstadoMembresia(Membresia membresiaCambiarEstado) {
		 try {
			 memDao.cambiarEstadoMembresia(membresiaCambiarEstado);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
