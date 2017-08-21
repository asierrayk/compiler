package tree.expresion.binaria;

import tree.expresion.Expresion;
import codigo.InstruccionP;
import codigo.ProgramaP;
import codigo.TokenP;

public class Conjuncion extends ExpresionBinaria {
	public Conjuncion(Expresion opnd1, Expresion opnd2, int left, int right) {
		super(opnd1, opnd2, left, right);
	}

	public String toString() {
		return "Conjuncion";
	}

	public ProgramaP codeR(ProgramaP p) {
		p = super.codeR(p);
		p.add(new InstruccionP(TokenP.AND));
		return p;
	}
}
