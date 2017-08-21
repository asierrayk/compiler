package tree.tipos;

public class TipoArray extends Tipo {
	private int size;
	private Tipo padre;

	public TipoArray(Object n, Tipo tipo, int left, int right) {
		super(tipo);
		this.padre = tipo;
		this.size = Integer.parseInt((String) n);
	}

	public String toString() {
		return "array";
	}

	public boolean equals(Object other) {
		if (other instanceof TipoArray) {
			return (this.size == ((TipoArray) other).size)
					&& padre.equals(((TipoArray) other).padre);
		}
		return false;
	}

	public boolean esArray() {
		return true;
	}

	public Tipo getSuperTipo() {
		return padre;
	}
	
	public int getSize() {
		return size;
	}

	public String tipoError() {
		return "array (tam " + this.size + ") de " + padre.tipoError();
	}
	
	public int size() {
		return this.size * padre.size();
	}
}
