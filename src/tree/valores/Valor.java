package tree.valores;

import codigo.InstruccionP;
import codigo.ProgramaP;
import codigo.TokenP;
import excepciones.CodeLException;
import tree.expresion.Expresion;
import tree.tipos.Tipo;

public abstract class Valor extends Expresion {
	public final int left;
	public final int right;
	public Tipo tipo;
	
	public Valor(int left, int right) {
		this.left = left;
		this.right = right;
	}
	
	public ProgramaP codeR(ProgramaP p) {
		try {
			p = this.codeL(p);
			p.add(new InstruccionP(TokenP.IND));
		} catch (CodeLException e) {
			e.printStackTrace();
		}
		return p;
	}
}
