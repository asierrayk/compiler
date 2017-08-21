package tree.expresion.binaria;

import aiden.Visitante;
import codigo.InstruccionP;
import codigo.ProgramaP;
import codigo.TokenP;
import tree.expresion.Expresion;

public class Suma extends ExpresionBinaria {
	public Suma(Expresion opnd1, Expresion opnd2, int left, int right) {
		super(opnd1, opnd2,left,right);
	}

	public String toString() {
		return " + ";
	}
	
	public ProgramaP codeR(ProgramaP p) {
		p = super.codeR(p);
		p.add(new InstruccionP(TokenP.ADD));
		return p;
	}
	
	public void accept(Visitante v) {
		if (!v.preVisit(this)) return;
		super.accept(v);
		v.postVisit(this);
	}
}
