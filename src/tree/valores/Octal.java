package tree.valores;

import tree.tipos.TipoBasico;


public class Octal extends Numero {

	public Octal(Object value, int left, int right) {
		super(value, left, right);
		this.tipo = new TipoBasico("numero",left,right);
	}
}
