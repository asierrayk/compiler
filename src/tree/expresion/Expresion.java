package tree.expresion;

import aiden.Visitante;
import tree.Sentencia;
import tree.expresion.binaria.*;

public abstract class Expresion extends Sentencia {

	public Expresion opnd1() {
		throw new UnsupportedOperationException("opnd1");
	}

	public Expresion opnd2() {
		throw new UnsupportedOperationException("opnd2");
	}

	public Expresion exp() {
		throw new UnsupportedOperationException("exp");
	}

	public String id() {
		throw new UnsupportedOperationException("id");
	}

	public String val() {
		throw new UnsupportedOperationException("real");
	}

	public static Expresion Operation(Expresion e1, String op, Expresion e2, int left, int right) {
		switch (op) {
		case "igual":
			return new Igualdad(e1, e2, left, right);
		case "distinct":
			return new Desigualdad(e1, e2, left, right);
		case "mayor":
			return new Mayor(e1, e2, left, right);
		case "menor":
			return new Menor(e1, e2, left, right);
		case "mayoroigual":
			return new MayorOIgual(e1, e2, left, right);
		case "menoroigual":
			return new MenorOIgual(e1, e2, left, right);
		case "mas":
			return new Suma(e1, e2, left, right);
		case "menos":
			return new Resta(e1, e2, left, right);
		case "por":
			return new Multiplicacion(e1, e2, left, right);
		case "division":
			return new Division(e1, e2, left, right);
		default:
			return null;
		}
	}

	public abstract void accept(Visitante v);
}
