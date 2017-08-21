package tree.flujo.multiopcion;

import java.util.Iterator;
import java.util.LinkedList;

import codigo.ProgramaP;
import excepciones.CodeException;
import aiden.Visitante;
import tree.Sentencia;
import tree.expresion.Expresion;

public class Opcion extends Sentencia {
	private Expresion e;
	private LinkedList<Sentencia> il;
	public final int left;
	public final int right;

	public Opcion(Expresion e, LinkedList<Sentencia> il, int left, int right) {
		this.e = e;
		this.il = il;
		this.left = left;
		this.right = right;
	}

	public String toString() {
		if (e != null)
			return "opcion " + e.toString() + " entonces " + il.toString();
		else
			return "otraopcion " + " entonces " + il.toString();
	}

	public void accept(Visitante v) {
		if (!v.preVisit(this))
			return;
		e.accept(v);
		Iterator<Sentencia> it = il.iterator();
		while (it.hasNext()) {
			it.next().accept(v);
		}
		v.postVisit(this);
	}

	public ProgramaP code(ProgramaP p) {
		try {
			Iterator<Sentencia> it = il.iterator();
			while (it.hasNext()) {
				p = it.next().code(p);
			}
		} catch (CodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}
}
