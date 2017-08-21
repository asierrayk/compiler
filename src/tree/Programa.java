package tree;

import java.util.Iterator;
import java.util.LinkedList;

import codigo.ProgramaP;
import excepciones.CodeException;
import aiden.Visitante;

public class Programa {
	private LinkedList<Sentencia> il;
	public final int left;
	public final int right;
	
	public Programa(LinkedList<Sentencia> il, int left, int right) {
		this.il = il;
		this.left = left;
		this.right = right;
		
	}
	
	public String toString() {
		Iterator<Sentencia> it = il.iterator();
		String str = "";
		while (it.hasNext())
			str += "\n" + it.next().toString();
		return str;
	}
	
	public void accept(Visitante v) {
		if (!v.preVisit(this)) return;
		Iterator<Sentencia> it = il.iterator();
		while(it.hasNext()) {
			Sentencia s = it.next();
			if (s != null)
				s.accept(v);
		}
		v.postVisit(this);
	}
	
	public ProgramaP code(ProgramaP p) {
		Iterator<Sentencia> it = il.iterator();
		while(it.hasNext()) {
			try {
				p = it.next().code(p);
			} catch (CodeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return p;
	}
}
