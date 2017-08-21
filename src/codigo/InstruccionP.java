package codigo;

public class InstruccionP implements InterfazInstruccionP{
	private TokenP token;
	private Object arg1;
	private Object arg2;

	public InstruccionP(TokenP token, Object arg1, Object arg2){
		this.token = token;
		this.arg1 = arg1;
		this.arg2 = arg2;
	}
	
	public InstruccionP(TokenP token, Object arg1){
		this.token = token;
		this.arg1 = arg1;
		arg2 = null;
	}
	
	public InstruccionP(TokenP token){
		this.token = token;
		this.arg1 = null;
		this.arg2 = null;
	}

	@Override
	public boolean esEtiqueta() {
		return false;
	}
	
	public String toString(){
		if(token.args == 0)
			return (token.toString());
		else if(token.args == 1)
			if (token == TokenP.UJP || token == TokenP.IXJ || token == TokenP.FJP) return (token.toString() + " " + ((Etiqueta) arg1).dir);
			else return (token.toString() + " " + arg1);
		else
			if (token == TokenP.CUP) return (token.toString() + " " + arg1 + " " + ((Etiqueta) arg2).dir);
			return (token.toString() + " " + arg1 + " " + arg2);
	}
}
