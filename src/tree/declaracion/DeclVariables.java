package tree.declaracion;

import aiden.Visitante;
import tree.tipos.Tipo;
import tree.valores.Identificador;

public class DeclVariables extends Declaracion {

	public DeclVariables(Tipo t, String i, int left, int right) {
		super(t, i, left, right);
	}

	public DeclVariables(Tipo t, Identificador i, int tleft, int iright) {
		super(t, i, tleft, iright);
	}

	public boolean esVariable() {
		return true;
	}
	
	public void accept(Visitante v) {
		if (!v.preVisit(this)) return;
		super.accept(v);
		v.postVisit(this);
	}

}
