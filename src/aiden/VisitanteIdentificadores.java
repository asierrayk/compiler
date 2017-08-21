package aiden;

import java.util.Iterator;
import java.util.Stack;

import tree.*;
import tree.asignacion.*;
import tree.declaracion.*;
import tree.expresion.Expresion;
import tree.expresion.Reserva;
import tree.expresion.binaria.*;
import tree.expresion.unaria.*;
import tree.flujo.*;
import tree.flujo.multiopcion.*;
import tree.tipos.*;
import tree.valores.*;

public class VisitanteIdentificadores implements Visitante {

	private TablaDeSimbolos tabla;
	private Stack<Integer> rho;
	private int frame;

	public VisitanteIdentificadores() {
		tabla = new TablaDeSimbolos();
		tabla.inic();
		rho = new Stack<Integer>();
		rho.push(5);
		frame = 0;
	}

	public boolean preVisit(Programa p) {
		tabla.abreBloque();
		Identificador idEntero = new Identificador("entero", 0, 0);
		Identificador idBooleano = new Identificador("booleano", 0, 0);
		Sentencia sEntero = new TipoBasico("entero", 0, 0);
		Sentencia sBooleano = new TipoBasico("booleano", 0, 0);
		tabla.insertaId(idEntero, sEntero);
		tabla.insertaId(idBooleano, sBooleano);
		return true;
	}

	public void postVisit(Programa p) {
		tabla.cierraBloque();
	}

	public boolean preVisit(AsignaIgual a) {
		return true;
	}

	public void postVisit(AsignaIgual a) {
	}

	public boolean preVisit(Libera a) {
		return true;
	}

	public void postVisit(Libera a) {
	}

	public boolean preVisit(Llamada i) {
		tabla.buscaId(i.getId());
		return true;
	}

	public void postVisit(Llamada i) {
	}

	public boolean preVisit(Mientras i) {
		tabla.abreBloque();
		return true;
	}

	public void postVisit(Mientras i) {
		tabla.cierraBloque();
	}

	public boolean preVisit(SiEntonces i) {
		tabla.abreBloque();
		return true;
	}

	public void postVisit(SiEntonces i) {
		tabla.cierraBloque();
	}

	public boolean preVisit(SiEntoncesCC i) {
		tabla.abreBloque();
		return true;
	}

	public void postVisit(SiEntoncesCC i) {
		tabla.cierraBloque();
	}

	public boolean preVisit(Multiopcion o) {
		tabla.abreBloque();
		return true;
	}

	public void postVisit(Multiopcion o) {
		tabla.cierraBloque();
	}

	public boolean preVisit(Opcion o) {
		tabla.abreBloque();
		return true;
	}

	public void postVisit(Opcion o) {
		tabla.cierraBloque();
	}

	public void visit(Tipo t) {
		tabla.buscaId(t.getId());
	}

	public void visit(TipoArray t) {
		t.getSuperTipo().accept(this);
	}

	public void visit(TipoBasico t) {
	}

	public boolean preVisit(Puntero t) {
		return true;
	}

	public void postVisit(Puntero t) {
	}

	public void visit(Valor v) {
	}

	public boolean preVisit(Conjuncion f) {
		return true;
	}

	public void postVisit(Conjuncion f) {
	}

	public boolean preVisit(Desigualdad f) {
		return true;
	}

	public void postVisit(Desigualdad f) {
	}

	public void postVisit(Disyuncion f) {
	}

	public boolean preVisit(Disyuncion f) {
		return true;
	}

	public boolean preVisit(Division f) {
		return true;
	}

	public void postVisit(Division f) {
	}

	public boolean preVisit(Igualdad f) {
		return true;
	}

	public void postVisit(Igualdad f) {
	}

	public boolean preVisit(Mayor f) {
		return true;
	}

	public void postVisit(Mayor f) {
	}

	public boolean preVisit(Menor f) {
		return true;
	}

	public void postVisit(Menor f) {
	}

	public boolean preVisit(Multiplicacion f) {
		return true;
	}

	public void postVisit(Multiplicacion f) {
	}

	public boolean preVisit(Resta f) {
		return true;
	}

	public void postVisit(Resta f) {
	}

	public boolean preVisit(Suma f) {
		return true;
	}

	public void postVisit(Suma f) {
	}

	public boolean preVisit(Negativo f) {
		return true;
	}

	public void postVisit(Negativo f) {
	}

	public boolean preVisit(No f) {
		return true;
	}

	public void postVisit(No f) {
	}

	public void visit(Bool t) {
		t.tipo = new TipoBasico("booleano", t.left, t.right);
	}

	public void visit(Numero t) {
		t.tipo = new TipoBasico("entero", t.left, t.right);
	}

	public boolean preVisit(Reserva reserva) {
		reserva.t.accept(this);
		return true;
	}

	public boolean postVisit(Reserva reserva) {
		return true;
	}

	public boolean preVisit(ExpresionBinaria expresionBinaria) {
		return true;
	}

	public void postVisit(ExpresionBinaria expresionBinaria) {
	}

	public boolean preVisit(ExpresionUnaria expresionUnaria) {
		return true;
	}

	public void postVisit(ExpresionUnaria expresionUnaria) {
	}

	public boolean preVisit(TipoFuncion f) {
		tabla.insertaId(f.i, f);
		f.i.tipo = f;
		tabla.abreBloque();
		frame++;
		rho.push(5);
		return true;
	}

	public void postVisit(TipoFuncion f) {
		frame--;
		rho.pop();
		tabla.cierraBloque();
	}

	public boolean preVisit(TipoDefinido f) {
		tabla.insertaId(f.id, f);
		f.id.tipo = f;
		tabla.abreBloque();
		return true;
	}

	public void postVisit(TipoDefinido f) {
		tabla.cierraBloque();
	}

	public boolean preVisit(Declaracion d) {
		tabla.insertaId(d.getId(), d);
		d.getId().tipo = d.getTipo();
		if (d.getId().tipo.esRegistroInd())
			d.getId().tipo = ((TipoDefinido) tabla.buscaId(d.getId().tipo
					.getId()));
		d.getId().rho = rho.peek();
		rho.push(rho.pop() + d.getId().tipo.size());
		d.getId().frame = frame;
		return true;
	}

	public void postVisit(Declaracion d) {
	}

	public boolean preVisit(DeclPunteros d) {
		tabla.insertaId(d.getId(), d);
		d.getId().tipo = d.getTipo();
		d.getId().rho = rho.peek();
		rho.push(rho.pop() + 1);
		d.getId().frame = frame;
		return true;
	}

	public void postVisit(DeclPunteros d) {
	}

	public boolean preVisit(DeclVariables d) {
		tabla.insertaId(d.getId(), d);
		d.getId().tipo = d.getTipo();

		if (d.getId().tipo.esRegistroInd())
			d.getId().tipo = ((TipoDefinido) tabla.buscaId(d.getId().tipo
					.getId()));
		d.getId().rho = rho.peek();
		rho.push(rho.pop() + d.getId().tipo.size());
		d.getId().frame = frame;
		return true;
	}

	public void postVisit(DeclVariables d) {
	}

	public void visit(Identificador id) {
		Sentencia sent = tabla.buscaId(id);
		id.frame = frame;
		if (sent != null) {
			id.rho = sent.getId().rho;
			id.marco = sent.getId().frame - id.frame;
			id.tipo = sent.getId().tipo;
			if (id.tipo.esBasico()) {
				if (id.params.size() > 0)
					tabla.thowError(id);
				else
					return;
			} else if (id.tipo.esPuntero()) {
				if (!((TipoPuntero) id.tipo).checkInstancia(id.params))
					tabla.thowError(id);
				else
					return;
			} else if (id.tipo.esArray()) {
				Iterator<Object> it = id.params.iterator();
				while (it.hasNext()) {
					if (!(it.next() instanceof Expresion))
						tabla.thowError(id);
				}
			} else if (id.tipo.esFuncion()) {
				if (id.params.size() > 0)
					tabla.thowError(id);
				else
					return;
			} else if (id.tipo.esRegistro()) {
				Identificador newId = id;
				Iterator<Object> it = newId.params.iterator();
				while (it.hasNext()) {
					Object subId = it.next();
					if (!(subId instanceof Identificador)
							|| !newId.tipo.esRegistro())
						tabla.thowError(newId);
					else {
						Iterator<Identificador> it2 = ((TipoDefinido) newId.tipo)
								.getIdentificadores().iterator();
						Identificador identificador = it2.next();
						int pos = 0;
						boolean bool = false;
						while (it2.hasNext() && !bool) {
							it2.next();
							bool = identificador.equals(subId);
						}
						if (!bool)
							tabla.thowError(newId);
						else {
							((Identificador) subId).tipo = ((TipoDefinido) newId.tipo)
									.getIdentificadores().get(pos).tipo;
							newId = ((TipoDefinido) newId.tipo)
									.getIdentificadores().get(pos);
						}
					}
				}
			} else if (id.tipo.esRegistroInd()) {
				id.tipo = ((TipoDefinido) tabla.buscaId(id.tipo.getId()));
			}
		}
	}

	public int getRho() {
		return rho.peek();
	}
}
