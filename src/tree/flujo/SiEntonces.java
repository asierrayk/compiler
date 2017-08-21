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

public class SiEntonces extends Sentencia {
	
	protected Expresion e;
	protected LinkedList<Sentencia> il;
	public final int left;
	public final int right;

	public SiEntonces(Expresion e, LinkedList<Sentencia> il, int left, int right) {
		this.e = e;
		this.il = il;
		this.left = left;
		this.right = right;
	}
	
	protected String output() {
		return "si " + e.toString() + " entonces " + il.toString();
	}
	
	public String toString() {
		return output() + " fsi";
	}

	public void accept(Visitante v) {
		if (!v.preVisit(this)) return;
		e.accept(v);
		Iterator<Sentencia> it = il.iterator();
		while(it.hasNext()) {
			it.next().accept(v);
		}
		v.postVisit(this);
	}
	 
	public ProgramaP code(ProgramaP p){
		try {
			p = e.codeR(p);
			
			int id = p.sigId();
			Etiqueta noThen = new Etiqueta(id);
			p.add(new InstruccionP(TokenP.FJP, noThen));
			
			Iterator<Sentencia> it = il.iterator();
			while(it.hasNext()) {
				it.next().code(p);
			}
			
			p.add(noThen);
		
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
