package tree.expresion.unaria;

import codigo.ProgramaP;
import excepciones.CodeRException;
import aiden.Visitante;
import tree.expresion.Expresion;


public abstract class ExpresionUnaria extends Expresion {
	private Expresion opnd1;
	public final int left;
	public final int right;

	public ExpresionUnaria(Expresion opnd1, int left, int right) {
		this.opnd1 = opnd1;
		this.left = left;
		this.right = right;
	}

	public Expresion opnd1() {
		return opnd1;
	}
	
	public void accept(Visitante v){
		opnd1.accept(v);
	}
	
	public ProgramaP codeR(ProgramaP p) {
		try {
			opnd1.codeR(p);
		} catch (CodeRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}
}
