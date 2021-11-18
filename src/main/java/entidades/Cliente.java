package entidades;

public class Cliente extends AbsPersona {
		private int idCliente;
		private Membresia membresia;

		public Cliente() {
			this.membresia = new Membresia();
		}
		
		public String getTipoMembresia() {
			return membresia.getTipoMembresia();
		}

		public void setTipoMembresia(String tipoMembresia) {
			membresia.setTipoMembresia(tipoMembresia);
		}
		public boolean getEstadoMembresia() {
			return membresia.isEstado();
		}

		public void setEstadoMembresia(boolean estadoMembresia) {
			membresia.setEstado(estadoMembresia);
		}

		public int getIdMembresia() {
			return membresia.getIdMembresia();
		}
		
		public int getIdCliente() {
			return idCliente;
		}
		
		public void setIdCliente(int idCliente) {
			this.idCliente = idCliente;
		}

		public Membresia getMembresia() {
			return membresia;
		}

		public void setMembresia(Membresia membresia) {
			this.membresia = membresia;
		}
		
		@Override
		public String toString() {
			return super.toString()+ "Cliente [idCliente=" + idCliente + ", membresia=" + membresia + "]";
		}


		
}
