package tree.tipos;

public class TipoBasico extends Tipo {

	public TipoBasico(String tipo, int left, int right) {
		super(tipo, left, right);
	}

	public boolean equals(Object other) {
		if (other instanceof TipoBasico) {
			return this.nombreTipo.equals(((TipoBasico) other).nombreTipo);
		}
		return false;
	}

	public boolean esBasico() {
		return true;
	}
	
	public String tipoError() {
		return this.nombreTipo;
	}
	
	public int size() {
		return 1;
	}

}
