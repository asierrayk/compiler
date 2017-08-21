package tree.tipos;

import java.util.LinkedList;

public class TipoPuntero extends Tipo {
	
	private Tipo padre;

	public TipoPuntero(Tipo t) {
		super(t);
		this.padre = t;
	}
	
	public boolean equals(Object other) {
		if (other instanceof TipoPuntero) {
			return padre.equals(((TipoPuntero) other).padre);
		}
		return false;
	}
	
	public boolean esPuntero() {
		return true;
	}
	
	public Tipo getSuperTipo() {
		return padre;
	}
	
	public boolean checkInstancia(LinkedList<Object> params) {
		return true;
	}
	
	public String toString() {
		return "puntero";
	}

	public String tipoError() {
		return "puntero a " + padre.tipoError();
	}
	
	public int size() {
		return 1;
	}
}
