package tree.tipos;

import java.util.Iterator;
import java.util.LinkedList;

import tree.declaracion.Declaracion;
import tree.valores.Identificador;
import aiden.Visitante;

public class TipoDefinido extends Tipo {
	
	private LinkedList<Declaracion> t;
	public Identificador id;

	public TipoDefinido(String i, LinkedList<Declaracion> t, int left, int right) {
		super(i,left,right);
		id = new Identificador(i, left, right);
		this.t = t;
	}

	public String toString() {
		return "registro";
	}
	
	public void accept(Visitante v){
		if (!v.preVisit(this)) return;
		id.accept(v);
		Iterator<Declaracion> it = t.iterator();
		while(it.hasNext()) {
			it.next().accept(v);
		}
		v.postVisit(this);
	}

	
	public boolean equals(Tipo other) {
		if (other instanceof TipoDefinido) {
			Iterator<Declaracion> it1 = t.iterator();
			Iterator<Declaracion> it2 = ((TipoDefinido) other).t.iterator();
			while(it1.hasNext() && it2.hasNext()) {
				if (!it1.next().getTipo().equals(it2.next().getTipo()))
					return false;
			}
			if (it1.hasNext() || it2.hasNext())
				return false;
			return true;
		} else if (other instanceof TipoRegistro)
			return this.nombreTipo.equals(other.nombreTipo);
		return false;
	}
		
	public boolean esRegistro() {
		return true;
	}
	
	public LinkedList<Identificador> getIdentificadores() {
		Iterator<Declaracion> it = t.iterator();
		LinkedList<Identificador> list = new LinkedList<Identificador>();
		while(it.hasNext()) {
			Declaracion dec = it.next();
			Identificador iden = dec.getId();
			iden.tipo = dec.t;
			list.add(iden);
		}
		return list;
	}
	
	public Identificador getId() {
		return id;
	}
	
	public String tipoError() {
		return this.nombreTipo;
	}
	
	public int size() {
		int size = 0;
		Iterator<Declaracion> it = t.iterator();
		while(it.hasNext()) {
			Declaracion dec = it.next();
			size += dec.t.size();
		}
		return size;
	}

}
