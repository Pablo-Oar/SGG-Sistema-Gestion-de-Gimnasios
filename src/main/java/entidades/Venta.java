 package entidades;

public class Venta {
	private int idVenta;
	private int id_Usuario;
	private int idDetalleVentas;
	private int item;
	private int idProducto;
	private String nroSerie;
	private String descripcion;
	private String fecha;
	private double precio;
	private int cantidad;
	private double subtotal;
	private double monto;
	private String estado;
	
	
	public int getIdVenta() {
		return idVenta;
	}
	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}
	public int getId_Usuario() {
		return id_Usuario;
	}
	public void setId_Usuario(int id_Usuario) {
		this.id_Usuario = id_Usuario;
	}
	public int getIdDetalleVentas() {
		return idDetalleVentas;
	}
	public void setIdDetalleVentas(int idDetalleVentas) {
		this.idDetalleVentas = idDetalleVentas;
	}
	public int getItem() {
		return item;
	}
	public void setItem(int item) {
		this.item = item;
	}
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public String getNroSerie() {
		return nroSerie;
	}
	public void setNroSerie(String nroSerie) {
		this.nroSerie = nroSerie;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	@Override
	public String toString() {
		return "Venta [idVenta=" + idVenta + ", item=" + item + ", idProducto=" + idProducto + ", nroSerie=" + nroSerie
				+ ", descripcion=" + descripcion + ", fecha=" + fecha + ", precio=" + precio + ", cantidad=" + cantidad
				+ ", subtotal=" + subtotal + ", monto=" + monto + ", estado=" + estado + "]";
	}
	
	
	
}
