package tree.tipos;

import tree.Sentencia;
import tree.valores.Identificador;
import aiden.Visitante;

public abstract class Tipo extends Sentencia {
	protected String nombreTipo;
	public final int left;
	public final int right;

	public Tipo(Tipo t) {
		this.nombreTipo = t.nombreTipo;
		this.left = t.left;
		this.right = t.right;
	}

	public Tipo(String tipo, int left, int right) {
		nombreTipo = tipo;
		this.left = left;
		this.right = right;
	}

	public String toString() {
		return nombreTipo;
	}

	public void accept(Visitante v) {
		v.visit(this);
	}

	public static boolean check(Tipo t, String str) {
		if (str.equals("puntero"))
			return t.esPuntero();
		if (str.equals("array"))
			return t.esArray();
		if (str.equals("funcion"))
			return t.esFuncion();
		if (str.equals("registro"))
			return t.esRegistro();
		if (str.equals("basico"))
			return t.esBasico();
		return t.nombreTipo.equals(str);
	}

	public boolean esPuntero() {
		return false;
	}

	public boolean esArray() {
		return false;
	}

	public boolean esFuncion() {
		return false;
	}

	public boolean esRegistro() {
		return false;
	}

	public boolean esBasico() {
		return false;
	}
	
	public boolean esRegistroInd() {
		return false;
	}

	public Identificador getId() {
		return new Identificador(nombreTipo, left, right);
	}
	
	public abstract String tipoError();
	
	public abstract int size();
}
