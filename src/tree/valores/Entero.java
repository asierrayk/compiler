package tree.valores;

import codigo.InstruccionP;
import codigo.ProgramaP;
import codigo.TokenP;
import tree.tipos.TipoBasico;

public class Entero extends Numero {

	public Entero(Object value, int left, int right) {
		super(value, left, right);
		this.tipo = new TipoBasico("numero",left,right);
	}
	
	public ProgramaP codeL(ProgramaP p) {
		p.add(new InstruccionP(TokenP.LDC, Integer.parseInt((String) value)));
		return p;
	}
}
