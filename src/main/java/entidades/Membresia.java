package entidades;

public class Membresia {
	private int idMembresia;
	private String tipoMembresia;
	private double precio;
	private int meses;
	private boolean estado; 
	
	
	public int getIdMembresia() {
		return idMembresia;
	}
	public void setIdMembresia(int idMembresia) {
		this.idMembresia = idMembresia;
	}
	public String getTipoMembresia() {
		return tipoMembresia;
	}
	public void setTipoMembresia(String tipoMembresia) {
		this.tipoMembresia = tipoMembresia;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getMeses() {
		return meses;
	}
	public void setMeses(int meses) {
		this.meses = meses;
	}
	
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	@Override
	public String toString() {
		return "CMembresia [idMembresia=" + idMembresia + ", tipoMembresia=" + tipoMembresia + ", precio=" + precio
				+ ", meses=" + meses + "]";
	}
	
	
	
}
