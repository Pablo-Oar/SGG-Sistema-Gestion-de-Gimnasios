package entidades;


public class Producto {
	private int idProducto;
	private String nombre;
	private String descripcion;
	private double precio;
	private int stock; 
	private CategoriaProducto categoria;
	private Proveedor proveedor;
	private boolean estado;
	//private Marca marca;
	
	public Producto() {
		this.categoria = new CategoriaProducto();
	}
	
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getDescripcionCategoria() {
		return categoria.getDescripcion();
	}
	public CategoriaProducto getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaProducto categoria) {
		this.categoria = categoria;
	}
	public void setDescripcionCategoria(String categoriaProducto) {
		this.categoria.setDescripcion(categoriaProducto);
	}

	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
//	public Marca getMarca() {
//		return marca;
//	}
//	public void setMarca(Marca marca) {
//		this.marca = marca;
//	}
	@Override
	public String toString() {
		return "Producto [idProducto=" + idProducto + ", nombre=" + nombre + ", descripcion=" + descripcion
				+ ", precio=" + precio + ", stock=" + stock + ", categoria=" + categoria + ", proveedor=" + proveedor
				+ ", estado=" + estado + "]";
	}
	
	
}
