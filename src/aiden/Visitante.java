package aiden;

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
import tree.expresion.binaria.Menor;
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
import tree.valores.Bool;
import tree.valores.Identificador;
import tree.valores.Numero;
import tree.valores.Puntero;
import tree.valores.Valor;

public interface Visitante {
	public void postVisit(AsignaIgual a);

	public void postVisit(Conjuncion f);

	public void postVisit(Declaracion d);

	public void postVisit(DeclPunteros d);

	public void postVisit(DeclVariables d);

	public void postVisit(Desigualdad f);

	public void postVisit(Disyuncion f);

	public void postVisit(Division f);

	public void postVisit(ExpresionBinaria expresionBinaria);

	public void postVisit(ExpresionUnaria expresionUnaria);

	public void postVisit(Igualdad f);

	public void postVisit(Libera a);

	public void postVisit(Llamada i);

	public void postVisit(Mayor f);

	public void postVisit(Menor f);

	public void postVisit(Mientras i);

	public void postVisit(Multiopcion o);

	public void postVisit(Multiplicacion f);

	public void postVisit(Negativo f);

	public void postVisit(No f);

	public void postVisit(Opcion o);

	public void postVisit(Programa p);

	public void postVisit(Puntero puntero);

	public boolean postVisit(Reserva reserva);

	public void postVisit(Resta f);

	public void postVisit(SiEntonces i);

	public void postVisit(SiEntoncesCC i);

	public void postVisit(Suma f);

	public void postVisit(TipoDefinido f);

	public void postVisit(TipoFuncion f);

	public boolean preVisit(AsignaIgual a);

	public boolean preVisit(Conjuncion f);

	public boolean preVisit(Declaracion d);

	public boolean preVisit(DeclPunteros d);

	public boolean preVisit(DeclVariables d);

	public boolean preVisit(Desigualdad f);

	public boolean preVisit(Disyuncion f);

	public boolean preVisit(Division f);

	public boolean preVisit(ExpresionBinaria expresionBinaria);

	public boolean preVisit(ExpresionUnaria expresionUnaria);

	public boolean preVisit(Igualdad f);

	public boolean preVisit(Libera a);

	public boolean preVisit(Llamada i);

	public boolean preVisit(Mayor f);

	public boolean preVisit(Menor f);

	public boolean preVisit(Mientras i);

	public boolean preVisit(Multiopcion o);

	public boolean preVisit(Multiplicacion f);

	public boolean preVisit(Negativo f);

	public boolean preVisit(No f);

	public boolean preVisit(Opcion o);

	public boolean preVisit(Programa p);

	public boolean preVisit(Puntero puntero);

	public boolean preVisit(Reserva reserva);

	public boolean preVisit(Resta f);

	public boolean preVisit(SiEntonces i);

	public boolean preVisit(SiEntoncesCC i);

	public boolean preVisit(Suma f);

	public boolean preVisit(TipoDefinido f);

	public boolean preVisit(TipoFuncion f);
	
	public void visit(Bool t);

	public void visit(Identificador t);

	public void visit(Numero t);

	public void visit(Tipo t);

	public void visit(TipoArray t);

	public void visit(TipoBasico t);

	public void visit(Valor v);
}