package tree.valores;

import tree.tipos.TipoBasico;


public class Hexadecimal extends Numero {

	public Hexadecimal(Object value, int left, int right) {
		super(value, left, right);
		this.tipo = new TipoBasico("numero",left,right);
	}
}
