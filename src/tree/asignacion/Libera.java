package tree.asignacion;

import aiden.Visitante;
import tree.Sentencia;
import tree.valores.Identificador;

public class Libera extends Sentencia {

	private Identificador i;
	public final int left;
	public final int right;

	public Libera(Identificador e, int left, int right) {
		this.i = e;
		this.left = left;
		this.right = right;
	}

	public String toString() {
		return "libera " + i.toString() + ".";
	}
	
	public void accept(Visitante v){
		if (!v.preVisit(this)) return;
		i.accept(v);
		v.postVisit(this);
	}
}
