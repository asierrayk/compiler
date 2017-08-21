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

public class SiEntoncesCC extends SiEntonces {

	private LinkedList<Sentencia> il2;

	public SiEntoncesCC(Expresion e, LinkedList<Sentencia> il1,
			LinkedList<Sentencia> il2, int left, int right) {
		super(e, il1, left, right);
		this.il2 = il2;
	}

	public String toString() {
		return super.output() + " cc " + il2.toString() + " fsi";
	}
	
	public void accept(Visitante v){
		super.accept(v);
		if (!v.preVisit(this)) return;
		Iterator<Sentencia> it = il2.iterator();
		while(it.hasNext()) {
			it.next().accept(v);
		}
		v.postVisit(this);
	}
	
	// code(if e then st1 else st2 fi) ρ = codeR e ρ;fjp l1; code st1 ρ; ujp l2; l1:code st2 ρ; l2: 
	public ProgramaP code(ProgramaP p){
		try {
			p = e.codeR(p);
			
			int id = p.sigId();
			Etiqueta noThen = new Etiqueta(id);
			p.add(new InstruccionP(TokenP.FJP, noThen));
			
			Iterator<Sentencia> it = il.iterator();
			while(it.hasNext()) {
				p = it.next().code(p);
			}
			
			id = p.sigId();
			Etiqueta end = new Etiqueta(id);
			p.add(new InstruccionP(TokenP.UJP, end));
			
			p.add(noThen);
			
			Iterator<Sentencia> it2 = il2.iterator();
			while(it2.hasNext()) {
				p = it2.next().code(p);
			}
			
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
