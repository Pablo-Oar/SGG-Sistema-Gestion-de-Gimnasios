package logica;

import java.util.*;
import datos.*;
import entidades.*;

public class LogicaCategoria {
	
	private CategoriaDao catDao;
	
	public LogicaCategoria() {
		catDao = new CategoriaDao();
	}
	
	public int modificarCategoriaTipo(Producto producto) {
		return catDao.modificarCategoriaTipo(producto);
	}
	
	public int setearTipoCategoria(Producto producto) {
		return  catDao.setearTipoCategoria(producto);
	}
	
	public void setCategoriaProducto(Producto producto) {
		 catDao.setCategoriaProducto(producto);
	}
	
	public int insertarIdCategoria(Producto producto) {
		return catDao.insertarIdCategoria(producto);
	}
	
	public List<CategoriaProducto> listarCategorias(){
		return catDao.listarCategorias();
	}
	
	public List<CategoriaProducto> encontrarCategoriaPorTipo(CategoriaProducto categoriaABuscar){
		return catDao.getByType(categoriaABuscar);
	}
	
	public CategoriaProducto encontrarCategoriaId(CategoriaProducto categoriaABuscar) {
		return  catDao.encontrarCategoriaById(categoriaABuscar);
	}
	
	
	public int insertarNuevaCategoria(CategoriaProducto categoriaAInsertar) {
		return catDao.insertarCategoria(categoriaAInsertar);
	}
	
	
	public int modificarCategoria(CategoriaProducto categoriaAModificar) {
		return catDao.modificarCategoria(categoriaAModificar);
	}
	
	
//	public int eliminarMembresia(CMembresia membresiaAEliminar) {
//		return memDao.eliminarMembresia(membresiaAEliminar);
//	}
	
	public void cambiarEstadoCategoria(CategoriaProducto categoriaCambiarEstado) {
		 try {
			 catDao.cambiarEstadoCategoria(categoriaCambiarEstado);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
