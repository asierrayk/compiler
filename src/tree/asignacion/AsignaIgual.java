package tree.asignacion;

import codigo.InstruccionP;
import codigo.ProgramaP;
import codigo.TokenP;
import excepciones.CodeRException;
import aiden.Visitante;
import tree.Sentencia;
import tree.expresion.Expresion;
import tree.valores.Identificador;

public class AsignaIgual extends Sentencia {

	private Identificador i;
	private Expresion e;
	public final int left;
	public final int right;

	public AsignaIgual(Identificador i, Expresion e, int left, int right) {
		this.i = i;
		this.e = e;
		this.left = left;
		this.right = right;
	}

	public String toString() {
		return i.toString() + " = " + e.toString() + ".";
	}

	public void accept(Visitante v){
		if (!v.preVisit(this)) return;
		i.accept(v);
		e.accept(v);
		v.postVisit(this);
	}
	
	public ProgramaP code(ProgramaP p){
		try {
			p = i.codeL(p);
			p = e.codeR(p);
			p.add(new InstruccionP(TokenP.STO));
		} catch (CodeRException e) {
			e.printStackTrace();
		}
		return p;
	}
}
