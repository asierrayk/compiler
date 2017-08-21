package tree.tipos;

import java.util.Iterator;
import java.util.LinkedList;

import codigo.Etiqueta;
import codigo.InstruccionP;
import codigo.ProgramaP;
import codigo.TokenP;
import excepciones.CodeException;
import aiden.Visitante;
import tree.Sentencia;
import tree.declaracion.Declaracion;
import tree.valores.Identificador;

public class TipoFuncion extends Tipo {

	public Identificador i;
	private LinkedList<Declaracion> t;
	private LinkedList<Sentencia> il;
	public Etiqueta etFun;
	private int initialSize;
	private int finalSize;

	public TipoFuncion(String i, LinkedList<Declaracion> t2,
			LinkedList<Sentencia> il, int left, int right) {
		super(i, left, right);
		this.i = new Identificador(i,left,right);
		this.t = t2;
		this.il = il;
	}

	public String toString() {
		return "funcion";
	}

	public void accept(Visitante v) {
		if (!v.preVisit(this))
			return;
		i.accept(v);
		Iterator<Declaracion> it = t.iterator();
		while (it.hasNext()) {
			it.next().accept(v);
		}
		Iterator<Sentencia> it2 = il.iterator();
		while (it2.hasNext()) {
			it2.next().accept(v);
		}
		v.postVisit(this);
	}

	public boolean equals(Tipo other) {
		if (other instanceof TipoFuncion) {
			Iterator<Declaracion> it1 = t.iterator();
			Iterator<Declaracion> it2 = ((TipoFuncion) other).t
					.iterator();
			while (it1.hasNext() && it2.hasNext()) {
				Declaracion p1 = it1.next();
				Declaracion p2 = it2.next();
				if (!p1.t.equals(p2.t))
					return false;
			}
			if (it1.hasNext() || it2.hasNext())
				return false;
			return true;
		}
		return false;
	}
	
	public boolean esFuncion() {
		return true;
	}
	public ProgramaP code(ProgramaP p) {
		try {
			int idEndFun = p.sigId();
			Etiqueta endFun = new Etiqueta(idEndFun);
			p.add(new InstruccionP(TokenP.UJP, endFun));

			int idFun = p.sigId();
			etFun = new Etiqueta(idFun);
			p.add(etFun);
			p.add(new InstruccionP(TokenP.SSP/*, longitud de la parte estatica del marco de activacion*/));
			p.add(new InstruccionP(TokenP.SEP/*, longitud de la pila*/));
			
			int idCodigo = p.sigId();
			Etiqueta etCodigo = new Etiqueta(idCodigo);
			p.add(new InstruccionP(TokenP.UJP, etCodigo));
			
			
			p.add(etCodigo);
			//CUERPO DE LA FUNCION

			Iterator<Sentencia> it = il.iterator();
			while (it.hasNext()) {
				it.next().code(p);
			}
			
			p.add(new InstruccionP(TokenP.RETP));

			p.add(endFun);
		} catch (CodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return p;
	}
	
	public Identificador getId() {
		return i;
	}
	
	public LinkedList<Tipo> getTipos() {
		Iterator<Declaracion> it = t.iterator();
		LinkedList<Tipo> list = new LinkedList<Tipo>();
		while (it.hasNext())
			list.add(it.next().t);
		return list;
	}
	
	public String tipoError() {
		return "funcion";
	}
	
	public int size() {
		System.out.println("No se deberia acceder a esto");
		return -1;
	}
	
	public void setInitial(int i) {
		this.initialSize = i;
	}
	
	public void setFinal (int f) {
		this.finalSize = f;
	}
	
	public int totalSize() {
		return finalSize - initialSize;
	}
}
