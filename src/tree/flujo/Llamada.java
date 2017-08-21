package tree.flujo;

import java.util.Iterator;
import java.util.LinkedList;

import tree.Sentencia;
import tree.expresion.Expresion;
import tree.tipos.TipoFuncion;
import tree.valores.Identificador;
import aiden.Visitante;
import codigo.InstruccionP;
import codigo.ProgramaP;
import codigo.TokenP;
import excepciones.CodeLException;

public class Llamada extends Sentencia {

	private Identificador i;
	private LinkedList<Expresion> lv;
	public final int left;
	public final int right;

	public Llamada(String i, LinkedList<Expresion> lv, int left, int right) {
		this.i = new Identificador(i, left, right);
		this.lv = lv;
		this.left = left;
		this.right = right;
	}

	public String toString() {
		return "llamar " + i.toString() + " " + lv.toString() + ".";
	}

	public void accept(Visitante v) {
		if (!v.preVisit(this))
			return;
		i.accept(v);
		Iterator<Expresion> it = lv.iterator();
		while (it.hasNext()) {
			it.next().accept(v);
		}
		v.postVisit(this);
	}

	public Identificador getId() {
		return i;
	}

	public ProgramaP code(ProgramaP p) {
		try {
			p.add(new InstruccionP(TokenP.MST, i.marco));
			

			Iterator<Expresion> it = lv.iterator();
			while (it.hasNext()) {
				it.next().codeL(p); // codeL ya que los valores son pasados por referencia, por valor seria codeR
			}

			p.add(new InstruccionP(TokenP.CUP, i.params.size(),
					((TipoFuncion) i.tipo).etFun));
		} catch (CodeLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}
}
