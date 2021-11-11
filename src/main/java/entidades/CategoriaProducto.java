package entidades;

public class CategoriaProducto {
	private int idCategoria;
	private String descripcion;
	private boolean estado;
	
	public int getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String categoriaProducto) {
		this.descripcion = categoriaProducto;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	@Override
	public String toString() {
		return "CategoriaProducto [idCategoria=" + idCategoria + ", descripcion=" + descripcion + ", estado=" + estado
				+ "]";
	}
	
	
}
