package tree.expresion;

import aiden.Visitante;
import tree.tipos.Tipo;
import tree.valores.Entero;

public class Reserva extends Expresion {

	private Entero n;
	public Tipo t;
	public final int left;
	public final int right;

	public Reserva(Object n, Tipo t, int left, int right) {
		this.n = new Entero(n,left,right);
		this.t = t;
		this.left = left;
		this.right = right;
	}

	public String toString() {
		return "reserva " + n.toString() + " " + t.toString();
	}
	
	public void accept(Visitante v){
		if (!v.preVisit(this)) return;
		n.accept(v);
		t.accept(v);
		v.postVisit(this);
	}
}
