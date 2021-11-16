package logica;

import java.util.List;

import datos.ProductoDao;
import entidades.CategoriaProducto;
import entidades.Cliente;
import entidades.Producto;

public class LogicaProducto {

	private ProductoDao prodDao;
	
	public  LogicaProducto() {
		 prodDao = new ProductoDao();
	}
	
	public List<Producto> listarProductos(){
		return prodDao.listarProductos();
	}
	
	public int insertarNuevoProducto(Producto producto) {
		return prodDao.insertarNuevoProducto(producto);
	}
	
	public int modificarProducto(Producto producto) {
		return prodDao.modificarProducto(producto);
	}
	
	public int eliminarProducto(Producto producto) {
		return prodDao.eliminarProducto(producto);
	}
	
	public List<Producto> encontrarProductoNombre(Producto producto){
		return  prodDao.encontrarProductoNombre(producto);
	}
	
	public List<Producto> encontrarProductoCategoria(CategoriaProducto categoriaProd){
		return prodDao.encontrarProductoCategoria(categoriaProd);
	}
	
	public Producto encontrarProductoId(Producto producto)
	{
		return prodDao.encontrarProductoId(producto);
	}

	public void cambiarEstadoProducto(Producto producto)
	{
		 try {
			 prodDao.cambiarEstadoProducto(producto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int actualizarStock(Producto producto, int stock) {
		return this.prodDao.actualizarStock(producto, stock);
	}
}
