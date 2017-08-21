package aiden;

import java.util.LinkedList;

import tree.Programa;
import tree.asignacion.AsignaIgual;
import tree.asignacion.Libera;
import tree.declaracion.DeclPunteros;
import tree.declaracion.DeclVariables;
import tree.declaracion.Declaracion;
import tree.expresion.Reserva;
import tree.expresion.binaria.Conjuncion;
import tree.expresion.binaria.Desigualdad;
import tree.expresion.binaria.Disyuncion;
import tree.expresion.binaria.Division;
import tree.expresion.binaria.ExpresionBinaria;
import tree.expresion.binaria.Igualdad;
import tree.expresion.binaria.Mayor;
import tree.expresion.binaria.MayorOIgual;
import tree.expresion.binaria.Menor;
import tree.expresion.binaria.MenorOIgual;
import tree.expresion.binaria.Multiplicacion;
import tree.expresion.binaria.Resta;
import tree.expresion.binaria.Suma;
import tree.expresion.unaria.ExpresionUnaria;
import tree.expresion.unaria.Negativo;
import tree.expresion.unaria.No;
import tree.flujo.Llamada;
import tree.flujo.Mientras;
import tree.flujo.SiEntonces;
import tree.flujo.SiEntoncesCC;
import tree.flujo.multiopcion.Multiopcion;
import tree.flujo.multiopcion.Opcion;
import tree.tipos.Tipo;
import tree.tipos.TipoArray;
import tree.tipos.TipoBasico;
import tree.tipos.TipoDefinido;
import tree.tipos.TipoFuncion;
import tree.tipos.TipoPuntero;
import tree.valores.Bool;
import tree.valores.Identificador;
import tree.valores.Numero;
import tree.valores.Puntero;
import tree.valores.Valor;

public class VisitanteTipos implements Visitante {
	private PilaDeTipos pila = new PilaDeTipos();

	public VisitanteTipos() {
		pila.inic();
	}

	public void postVisit(AsignaIgual a) {
		pila.compare(a.left);
	}

	public void postVisit(Conjuncion f) {
		pila.check("booleano", f.left);
		pila.check("booleano", f.left);
		pila.add(new TipoBasico("booleano", f.left, f.right));
	}

	public void postVisit(Declaracion d) {
		pila.compare(d.left);
	}

	public void postVisit(DeclPunteros d) {
		pila.compare(d.left);
	}

	public void postVisit(DeclVariables d) {
		pila.compare(d.left);
	}

	public void postVisit(Desigualdad f) {
		pila.compare(f.left);
		pila.add(new TipoBasico("booleano", f.left, f.right));
	}

	public void postVisit(Disyuncion f) {
		pila.check("booleano", f.left);
		pila.check("booleano", f.left);
		pila.add(new TipoBasico("booleano", f.left, f.right));
	}

	public void postVisit(Division f) {
		pila.check("entero", f.left);
		pila.check("entero", f.left);
		pila.add(new TipoBasico("entero", f.left, f.right));
	}

	public void postVisit(ExpresionBinaria expresionBinaria) {
	}

	public void postVisit(ExpresionUnaria expresionUnaria) {
	}

	public void postVisit(Igualdad f) {
		pila.compare(f.left);
		pila.add(new TipoBasico("booleano", f.left, f.right));
	}

	public void postVisit(Libera a) {
		pila.check("puntero", a.left);
	}

	public void postVisit(Llamada i) {
		LinkedList<Tipo> list = ((TipoFuncion) i.getId().tipo).getTipos();
		for (int j = list.size() - 1; j >= 0; j--)
			pila.check(list.get(j).toString(), i.left);
		pila.check("funcion", i.left);
	}

	public void postVisit(Mayor f) {
		pila.check("entero", f.left);
		pila.check("entero", f.left);
		pila.add(new TipoBasico("booleano", f.left, f.right));
	}

	public void postVisit(Menor f) {
		pila.check("entero", f.left);
		pila.check("entero", f.left);
		pila.add(new TipoBasico("booleano", f.left, f.right));
	}
	
	public void postVisit(MayorOIgual f) {
		pila.check("entero", f.left);
		pila.check("entero", f.left);
		pila.add(new TipoBasico("booleano", f.left, f.right));
	}

	public void postVisit(MenorOIgual f) {
		pila.check("entero", f.left);
		pila.check("entero", f.left);
		pila.add(new TipoBasico("booleano", f.left, f.right));
	}

	public void postVisit(Mientras i) {
		pila.check("basico", i.left);
	}

	public void postVisit(Multiopcion o) {
		pila.check("basico", o.left);
	}

	public void postVisit(Multiplicacion f) {
		pila.check("entero", f.left);
		pila.check("entero", f.left);
		pila.add(new TipoBasico("entero", f.left, f.right));
	}

	public void postVisit(Negativo f) {
		pila.check("entero", f.left);
		pila.add(new TipoBasico("entero", f.left, f.right));
	}

	public void postVisit(No f) {
		pila.check("booleano", f.left);
		pila.add(new TipoBasico("booleano", f.left, f.right));
	}

	public void postVisit(Opcion o) {
		pila.check("basico", o.left);
	}

	public void postVisit(Programa p) {
	}

	public void postVisit(Puntero puntero) {
		pila.add(puntero.i.tipo);
	}

	public boolean postVisit(Reserva reserva) {
		pila.check("basico", reserva.left);
		pila.check("basico", reserva.left);
		return true;
	}

	public void postVisit(Resta f) {
		pila.check("entero", f.left);
		pila.check("entero", f.left);
		pila.add(new TipoBasico("entero", f.left, f.right));
	}

	public void postVisit(SiEntonces i) {
		pila.check("booleano", i.left);
	}

	public void postVisit(SiEntoncesCC i) {
	}

	public void postVisit(Suma f) {
		pila.check("entero", f.left);
		pila.check("entero", f.left);
		pila.add(new TipoBasico("entero", f.left, f.right));
	}

	public void postVisit(TipoDefinido f) {
		// TODO Auto-generated method stub

	}

	public void postVisit(TipoFuncion f) {
		f.setFinal(pila.getSize());
	}

	public boolean preVisit(AsignaIgual a) {
		return true;
	}

	public boolean preVisit(Conjuncion f) {
		return true;
	}

	public boolean preVisit(Declaracion d) {
		return true;
	}

	public boolean preVisit(DeclPunteros d) {
		return true;
	}

	public boolean preVisit(DeclVariables d) {
		return true;
	}

	public boolean preVisit(Desigualdad f) {
		return true;
	}

	public boolean preVisit(Disyuncion f) {
		return true;
	}

	public boolean preVisit(Division f) {
		return true;
	}

	public boolean preVisit(ExpresionBinaria expresionBinaria) {

		return true;
	}

	public boolean preVisit(ExpresionUnaria expresionUnaria) {
		return true;
	}

	public boolean preVisit(Igualdad f) {
		return true;
	}

	public boolean preVisit(Libera a) {
		return true;
	}

	public boolean preVisit(Llamada i) {
		return true;
	}

	public boolean preVisit(Mayor f) {
		return true;
	}

	public boolean preVisit(Menor f) {
		return true;
	}

	public boolean preVisit(Mientras i) {
		return true;
	}

	public boolean preVisit(Multiopcion o) {

		return true;
	}

	public boolean preVisit(Multiplicacion f) {
		return true;
	}

	public boolean preVisit(Negativo f) {
		return true;
	}

	public boolean preVisit(No f) {
		return true;
	}

	public boolean preVisit(Opcion o) {

		return true;
	}

	public boolean preVisit(Programa p) {
		return true;
	}

	public boolean preVisit(Puntero puntero) {

		return true;
	}

	public boolean preVisit(Reserva reserva) {
		return true;
	}

	public boolean preVisit(Resta f) {
		return true;
	}

	public boolean preVisit(SiEntonces i) {

		return true;
	}

	public boolean preVisit(SiEntoncesCC i) {
		return true;
	}

	public boolean preVisit(Suma f) {
		return true;
	}

	public boolean preVisit(TipoDefinido f) {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean preVisit(TipoFuncion f) {
		f.setInitial(pila.getSize());
		return true;
	}

	public void visit(Bool t) {
		pila.add(t.tipo);
	}

	public void visit(Identificador t) {
		pila.add(t.getTipo());
	}

	public void visit(Numero t) {
		pila.add(t.tipo);
	}

	public void visit(Puntero t) {
		pila.add(new TipoPuntero(t.tipo));
	}

	public void visit(Tipo t) {
		pila.add(t);
	}

	public void visit(TipoArray t) {
		pila.add(t);
	}

	public void visit(TipoBasico t) {
		pila.add(t);
	}

	public void visit(Valor v) {
		pila.add(new TipoBasico(v.id(), 0, 0));
	}

}
