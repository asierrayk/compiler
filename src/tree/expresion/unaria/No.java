package tree.expresion.unaria;

import aiden.Visitante;
import codigo.InstruccionP;
import codigo.ProgramaP;
import codigo.TokenP;
import tree.expresion.Expresion;

public class No extends ExpresionUnaria {
	public No(Expresion opnd1, int left, int right) {
		super(opnd1, left, right);
	}

	public String toString() {
		return "No";
	}
	
	public ProgramaP codeR(ProgramaP p) {
		try {
			p = super.codeR(p);
			p.add(new InstruccionP(TokenP.NOT));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}
	
	public void accept(Visitante v){
		if (!v.preVisit(this)) return;
		super.accept(v);
		v.postVisit(this);
	}
}
