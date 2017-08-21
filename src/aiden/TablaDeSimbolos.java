package aiden;

import java.util.Stack;
import java.util.TreeMap;

import errors.GestionErroresTiny;
import tree.Sentencia;
import tree.valores.Identificador;

public class TablaDeSimbolos {

	private Stack<TreeMap<Identificador, Sentencia>> pila;
	private GestionErroresTiny errores;

	public void inic() {
		pila = new Stack<TreeMap<Identificador, Sentencia>>();
		errores = new GestionErroresTiny();
	}

	public void abreBloque() {
		pila.push(new TreeMap<Identificador, Sentencia>());
	}

	public void cierraBloque() {
		pila.pop().clear();
	}

	public void insertaId(Identificador id, Sentencia puntero) {
		TreeMap<Identificador, Sentencia> actual = pila.peek();
		if (actual.containsKey(id))
			errores.errorIdentificadorExistente(id.left, id.toString());
		else
			actual.put(id, puntero);
	}

	public Sentencia buscaId(Identificador id) {
		int bloque = pila.size() - 1;
		while (bloque >= 0) {
			TreeMap<Identificador, Sentencia> actual = pila.get(bloque);
			if (actual.containsKey(id))
				return actual.get(id);
			bloque--;
		}
		errores.errorIdentificadorNoExiste(id.left, id.toString());
		return null;
	}

	public void thowError(Identificador id) {
		errores.errorIdentificadorIncorrecto(id.left, id.toString());
	}
}
