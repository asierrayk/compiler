package tree.declaracion;

import aiden.Visitante;
import tree.tipos.Tipo;
import tree.tipos.TipoPuntero;

public class DeclPunteros extends Declaracion {

	public DeclPunteros(Tipo t, String i, int left, int right) {
		super(new TipoPuntero(t), i, left, right);
	}
	
	public String toString() {
		return "punt " + super.toString();
	}
	
	public boolean esPuntero() {
		return true;
	}
	
	public void accept(Visitante v) {
		if (!v.preVisit(this)) return;
		super.accept(v);
		v.postVisit(this);
	}

}
