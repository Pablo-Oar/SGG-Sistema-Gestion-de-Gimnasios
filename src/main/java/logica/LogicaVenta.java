package logica;

import datos.VentaDao;
import entidades.Venta;

public class LogicaVenta {
	int dato;
	String numero;
	private VentaDao ventaDao;
	
	public LogicaVenta() {
		this.ventaDao = new VentaDao();
	}
	
	public int idVentas() {
		return this.ventaDao.idVentas();
	}
	public int guardarDetalleVentas(Venta venta) {
		return this.ventaDao.guardarDetalleVentas(venta);
	}
	
	public int insertarVenta(Venta venta) {
		return this.ventaDao.insertarVenta(venta);
	}
	
	public String GenerarNroSerie() {
		return ventaDao.GenerarNroSerie();
	}
	
	public String NumeroSerie(int dato) {
		this.dato = dato + 1;
		if((this.dato>=10000000)&&(this.dato<100000000)) {
			this.numero = ""+this.dato;
		}
		if((this.dato>=1000000)&&(this.dato<=10000000)) {
			this.numero = "00"+this.dato;
		}
		if((this.dato>=100000)&&(this.dato<=1000000)) {
			this.numero = "000"+this.dato;
		}
		if((this.dato>=10000)&&(this.dato<=100000)) {
			this.numero = "0000"+this.dato;
		}
		if((this.dato>=1000)&&(this.dato<=10000)) {
			this.numero = "00000"+this.dato;
		}
		if((this.dato>=100)&&(this.dato<=1000)) {
			this.numero = "000000"+this.dato;
		}
		if((this.dato>=10)&&(this.dato<=100)) {
			this.numero = "0000000"+this.dato;
		}
		if(this.dato<10) {
			this.numero = "00000000"+this.dato;
		}
		
		return this.numero;
	}
}
