package codigo;

public class Etiqueta implements InterfazInstruccionP {
	public int id;
	public int dir;
	
	public Etiqueta(int id){
		this.id = id;
	}

	public boolean esEtiqueta() {
		return true;
	}

}
