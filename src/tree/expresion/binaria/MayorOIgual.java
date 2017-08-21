package tree.expresion.binaria;

import aiden.Visitante;
import codigo.InstruccionP;
import codigo.ProgramaP;
import codigo.TokenP;
import tree.expresion.Expresion;

public class MayorOIgual extends ExpresionBinaria {
	public MayorOIgual(Expresion opnd1, Expresion opnd2, int left, int right) {
		super(opnd1, opnd2,left,right);
	}

	public String toString() {
		return "Mayor o igual";
	}
	
	public ProgramaP codeR(ProgramaP p) {
		p = super.codeR(p);
		p.add(new InstruccionP(TokenP.LEQ));
		return p;
	}
	
	public void accept(Visitante v) {
		if (!v.preVisit(this)) return;
		super.accept(v);
		v.postVisit(this);
	}
}
