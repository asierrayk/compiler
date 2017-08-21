package tree.valores;

import tree.tipos.TipoBasico;


public class Binario extends Numero {

	public Binario(Object value, int left, int right) { 
		super(value, left, right);
		this.tipo = new TipoBasico("numero",left,right);
	}
}
