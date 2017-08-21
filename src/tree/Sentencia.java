package tree;

import tree.valores.Identificador;
import codigo.ProgramaP;
import excepciones.CodeException;
import excepciones.CodeLException;
import excepciones.CodeRException;
import aiden.Visitante;

public abstract class Sentencia {
	public abstract String toString();
	public abstract void accept(Visitante v);
	
	public ProgramaP codeL(ProgramaP p) throws CodeLException{
		throw new CodeLException("CodeLException");
	}
	
	public ProgramaP codeR(ProgramaP p) throws CodeRException{
		throw new CodeRException("CodeRException");
	}
	
	public ProgramaP code(ProgramaP p) throws CodeException{
		throw new CodeException("CodeException");
	}
	
	public Identificador getId() {
		return null;
	}
}
