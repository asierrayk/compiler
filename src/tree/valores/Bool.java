package tree.valores;

import codigo.InstruccionP;
import codigo.ProgramaP;
import codigo.TokenP;
import aiden.Visitante;
import tree.tipos.TipoBasico;

public class Bool extends Valor {

	private boolean value;

	public Bool(String value, int left, int right) {
		super(left, right);
		this.tipo = new TipoBasico("bool",left,right);
		if (value == "cierto")
			this.value = true;
		else
			this.value = false;
	}

	public String toString() {
		if (value)
			return "cierto";
		else
			return "falso";
	}
	
	public void accept(Visitante v){
		v.visit(this);
	}
	
	public ProgramaP codeL(ProgramaP p) {
		if(value) p.add(new InstruccionP(TokenP.LDC, "true"));
		else p.add(new InstruccionP(TokenP.LDC, "false"));
		return p;
	}
}