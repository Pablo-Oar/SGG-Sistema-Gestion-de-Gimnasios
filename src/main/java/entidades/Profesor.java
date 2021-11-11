package entidades;



public class Profesor extends AbsPersona{
	private int idProfesor;
	private String horario_inicio;
	private String horario_fin;
	private AreaProfesor area;
	
	public int getIdProfesor() {
		return idProfesor;
	}
	public void setIdProfesor(int idProfe) {
		this.idProfesor = idProfe;
	}

	public String getHorario_inicio() {
		return horario_inicio;
	}
	public void setHorario_inicio(String horario_inicio) {
		this.horario_inicio = horario_inicio;
	}
	public String getHorario_fin() {
		return horario_fin;
	}
	public void setHorario_fin(String horario_fin) {
		this.horario_fin = horario_fin;
	}
	public AreaProfesor getArea() {
		return area;
	}
	public void setArea(AreaProfesor area) {
		this.area = area;
	}
	@Override
	public String toString() {
		return super.toString()+ "CProfesor [idProfesor=" + idProfesor + ", horario_inicio=" + horario_inicio + ", horario_fin="
				+ horario_fin + ", area=" + area + "]";
	}
	
	
}
