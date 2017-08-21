package tree.expresion.binaria;

import aiden.Visitante;
import tree.expresion.Expresion;
import codigo.InstruccionP;
import codigo.ProgramaP;
import codigo.TokenP;

public class Desigualdad extends ExpresionBinaria {
	public Desigualdad(Expresion opnd1, Expresion opnd2, int left, int right) {
		super(opnd1, opnd2,left,right);
	}

	public String toString() {
		return "Desigualdad";
	}
	
	public ProgramaP codeR(ProgramaP p) {
		p = super.codeR(p);
		p.add(new InstruccionP(TokenP.NEQ));
		return p;
	}
	
	public void accept(Visitante v) {
		if (!v.preVisit(this)) return;
		super.accept(v);
		v.postVisit(this);
	}
}
