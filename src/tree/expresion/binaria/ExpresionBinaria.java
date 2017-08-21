package tree.expresion.binaria;

import codigo.ProgramaP;
import excepciones.CodeRException;
import aiden.Visitante;
import tree.expresion.Expresion;


public abstract class ExpresionBinaria extends Expresion {
	private Expresion opnd1;
	private Expresion opnd2;
	public final int left;
	public final int right;

	public ExpresionBinaria(Expresion opnd1, Expresion opnd2, int left, int right) {
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
		this.left = left;
		this.right = right;
	}

	public Expresion opnd1() {
		return opnd1;
	}

	public Expresion opnd2() {
		return opnd2;
	}
	
	public void accept(Visitante v) {
		opnd1.accept(v);
		opnd2.accept(v);
	}
	
	public ProgramaP codeR(ProgramaP p) {
		try {
			p = opnd1.codeR(p);
			p = opnd2.codeR(p);
		} catch (CodeRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}
}