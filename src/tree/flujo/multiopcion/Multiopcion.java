package tree.flujo.multiopcion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import codigo.Etiqueta;
import codigo.InstruccionP;
import codigo.ProgramaP;
import codigo.TokenP;
import excepciones.CodeRException;
import aiden.Visitante;
import tree.Sentencia;
import tree.expresion.Expresion;

public class Multiopcion extends Sentencia {

	private Expresion e;
	private LinkedList<Opcion> ops;
	public final int left;
	public final int right;

	public Multiopcion(Expresion e, LinkedList<Opcion> ops, int left, int right) {
		this.e = e;
		this.ops = ops;
		this.left = left;
		this.right = right;
	}

	public String toString() {
		return "multiopcion " + e.toString() + " es " + ops.toString()
				+ " fmultiopcion";
	}

	public void accept(Visitante v) {
		if (!v.preVisit(this))
			return;
		e.accept(v);
		Iterator<Opcion> it = ops.iterator();
		while (it.hasNext()) {
			it.next().accept(v);
		}
		v.postVisit(this);
	}

	public ProgramaP code(ProgramaP p) {
		try {
			p = e.codeR(p);

			int idBranch = p.sigId();
			Etiqueta branch = new Etiqueta(idBranch);

			int idEnd = p.sigId();
			Etiqueta end = new Etiqueta(idEnd);

			p.add(new InstruccionP(TokenP.IXJ, branch));

			Iterator<Opcion> it = ops.iterator();
			int i = 0;
			ArrayList<Etiqueta> et = new ArrayList<Etiqueta>();
			while (it.hasNext()) {
				int id = p.sigId();
				et.add(i, new Etiqueta(id));
				p.add(et.get(i));

				it.next().code(p);

				p.add(new InstruccionP(TokenP.UJP, end));

				i++;
			}

			p.add(branch);

			int j = 0;
			while (j < i) {
				p.add(new InstruccionP(TokenP.UJP, et.get(j)));
				j++;
			}

			p.add(end);
		} catch (CodeRException e) {
			e.printStackTrace();
		}
		return p;
	}
}
