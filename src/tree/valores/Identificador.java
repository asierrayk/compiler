package tree.valores;

import java.util.Iterator;
import java.util.LinkedList;

import codigo.InstruccionP;
import codigo.ProgramaP;
import codigo.TokenP;
import excepciones.CodeRException;
import aiden.Visitante;
import tree.expresion.Expresion;
import tree.tipos.Tipo;
import tree.tipos.TipoArray;

public class Identificador extends Valor implements Comparable<Identificador> {

	private String i;	
	public LinkedList<Object> params;
	public int rho = 5;
	public int frame = 0;
	public int marco = 0;

	public Identificador(String i, int left, int right) {
		super(left,right);
		this.i = i;
		this.params = new LinkedList<Object>();
	}

	public String toString() {
		return "\'" + i + "\'";
	}

	public int compareTo(Identificador arg0) {
		return i.compareTo(arg0.i);
	}

	public void addParam(String id) {
		params.add(new Identificador(id, left, right));
	}

	public void addParam(Expresion e) {
		params.add(e);
	}
	
	public void accept(Visitante v){
		v.visit(this);
		// System.out.println(this.left + " " + this.tipo);
	}
	
		public ProgramaP codeL(ProgramaP p) {
		try {
		if(Tipo.check(tipo, "basico"))
			p.add(new InstruccionP(TokenP.LDA,this.marco ,this.rho));//marco , y desplazamiento relativo
		else if(Tipo.check(tipo, "array")){
			p.add(new InstruccionP(TokenP.LDA,this.marco ,this.rho));
			p.add(new InstruccionP(TokenP.LDA,this.marco ,this.rho));
			
			for(Object e: params){
				p = ((Expresion) e).codeR(p);
				p.add(new InstruccionP(TokenP.IXA /*, g_d(i)*/));
			}
			p.add(new InstruccionP(TokenP.DEC/*, g_D*/));
		}
		} catch (CodeRException e1) {
			e1.printStackTrace();
		}	
		return p;

	}

	public Tipo getTipo() {
		Tipo t = this.tipo;
		Iterator<Object> it = params.iterator();
		while(it.hasNext()) {
			Object o = it.next();
			if (o instanceof Identificador)
				t = ((Identificador) o).tipo;
			else
				t = ((TipoArray) t).getSuperTipo();
		}
		return t;
	}
	
	public boolean equals(Object other) {
		if (other instanceof Identificador)
			return this.i.equals(((Identificador) other).i);
		return false;
	}
}
