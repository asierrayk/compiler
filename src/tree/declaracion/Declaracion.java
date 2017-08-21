package tree.declaracion;

import codigo.ProgramaP;
import aiden.Visitante;
import tree.Sentencia;
import tree.tipos.Tipo;
import tree.valores.Identificador;

public class Declaracion extends Sentencia {
	public Tipo t;
	private Identificador i;
	public final int left;
	public final int right;

	public Declaracion(Tipo t, String i, int left, int right) {
		this.t = t;
		this.i = new Identificador(i, left, right);
		this.left = left;
		this.right = right;
	}

	public Declaracion(Tipo t2, Identificador i2, int tleft, int iright) {
		this.t = t2;
		this.i = i2;
		this.left = tleft;
		this.right = iright;
	}

	public String toString() {
		return t.toString() + i.toString() + ".";
	}

	public Identificador getId() {
		return i;
	}
	
	public Tipo getTipo() {
		return t;
	}

	public void accept(Visitante v) {
		t.accept(v);
		i.accept(v);
	}
	
	public ProgramaP code(ProgramaP p) {
		return p;
	}

}
