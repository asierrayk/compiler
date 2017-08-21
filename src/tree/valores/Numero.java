package tree.valores;

import aiden.Visitante;

public class Numero extends Valor {
	
	protected Object value;

	public Numero(Object value, int left, int right) {
		super(left, right);
		this.value = value;
	}

	public String toString() {
		return value.toString();
	}
	
	public void accept(Visitante v){
		v.visit(this);
	}

}
