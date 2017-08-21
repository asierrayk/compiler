package aiden;

import java.util.Stack;

import tree.tipos.Tipo;
import errors.GestionErroresTiny;

public class PilaDeTipos {
	private GestionErroresTiny errores;
	private Stack<Tipo> pila;

	public void inic() {
		pila = new Stack<Tipo>();
		errores = new GestionErroresTiny();
	}

	public void add(Tipo v) {
		pila.push(v);
	}

	public void compare(int line) {
		Tipo v2 = pila.pop();
		Tipo v1 = pila.pop();
		if (v1 == null || v2 == null)
			errores.errorDeTipos(line);
		else if (!v1.equals(v2))
			errores.errorDeTipos(line, v1.tipoError(), v2.tipoError());
	}

	public void check(String str, int line) {
		Tipo v = pila.pop();
		if (v == null)
			errores.errorDeTipos(line);
		else if (!Tipo.check(v, str))
			errores.errorDeTipos(line, v.tipoError());
	}
	
	public int getSize() {
		return this.pila.size();
	}
}
