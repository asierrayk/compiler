package tree.valores;

import aiden.Visitante;


public class Puntero extends Valor {

	public Identificador i;

	public Puntero(Identificador i2, int left, int right) {
		super(left,right);
		this.i = i2;
	}

	public String toString() {
		return "@" + i.toString(); 
	}
	
	public void accept(Visitante v){
		if (!v.preVisit(this)) return;
		i.accept(v);
		v.postVisit(this);
	}

}
