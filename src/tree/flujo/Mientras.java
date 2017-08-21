package tree.flujo;

import java.util.Iterator;
import java.util.LinkedList;

import codigo.Etiqueta;
import codigo.InstruccionP;
import codigo.ProgramaP;
import codigo.TokenP;
import excepciones.CodeException;
import excepciones.CodeRException;
import aiden.Visitante;
import tree.Sentencia;
import tree.expresion.Expresion;

public class Mientras extends Sentencia {

	private Expresion e;
	private LinkedList<Sentencia> il;
	public final int left;
	public final int right;

	public Mientras(Expresion e, LinkedList<Sentencia> il, int left, int right) {
		this.e = e;
		this.il = il;
		this.left = left;
		this.right = right;
	}

	public String toString() {
		return "mientras " + e.toString() + " hacer " + il.toString()
				+ " fmientras";
	}

	@Override
	public void accept(Visitante v) {
		if (!v.preVisit(this)) return;
		e.accept(v);
		Iterator<Sentencia> it = il.iterator();
		while(it.hasNext()) {
			it.next().accept(v);
		}
		v.postVisit(this);
	}
	
	// code(while e do st od) ρ = l1:codeR e ρ;fjp l2; code st ρ; ujp l1; l2:
	public ProgramaP code(ProgramaP p){
		try {
			int idIni = p.sigId();
			Etiqueta ini = new Etiqueta(idIni);
			
			p.add(ini);
			e.codeR(p);
			
			int idEnd = p.sigId();
			Etiqueta end = new Etiqueta(idEnd);
			
			p.add(new InstruccionP(TokenP.FJP, end));
			
			Iterator<Sentencia> it = il.iterator();
			while(it.hasNext()) {
				it.next().code(p);
			}
			
			p.add(new InstruccionP(TokenP.UJP, ini));
			
			p.add(end);
		
		} catch (CodeRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CodeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return p;
	}
}
