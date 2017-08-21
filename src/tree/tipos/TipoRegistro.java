package tree.tipos;

import tree.valores.Identificador;

public class TipoRegistro extends Tipo {

	public Identificador id;

	public TipoRegistro(String i, int left, int right) {
		super(i, left, right);
		id = new Identificador(i, left, right);
	}

	public String toString() {
		return "registro";
	}

	public boolean equals(Object other) {
		if ((other instanceof TipoRegistro || other instanceof TipoDefinido)
				&& this.nombreTipo.equals(((Tipo) other).nombreTipo)) {
			return true;
		}
		return false;
	}

	public boolean esRegistroInd() {
		return true;
	}
	
	public String tipoError() {
		return this.nombreTipo;
	}
	
	public int size() {
		System.out.println("Linea " + this.left + ": No deberia ser tipo registro");
		return -1;
	}

}
