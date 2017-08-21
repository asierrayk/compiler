package codigo;

import java.util.Stack;

public class ProgramaP {
	public Stack<InterfazInstruccionP> il;
	private int sigId;
	private int rho;
	
	public ProgramaP(int rho){
		il = new Stack<InterfazInstruccionP>();
		sigId = 0;
		this.rho = rho;
	}
	
	public ProgramaP(Stack<InterfazInstruccionP> s){
		il = s;
		sigId = 0;
	}
	
	public void add(InterfazInstruccionP i){
		il.push(i);
	}
	
	public int size(){
		return il.size();
	}

	public int sigId() {
		int id = sigId;
		sigId++;
		return id;
	}
	
	public ProgramaP quitaEtiquetas(){
		Stack<InterfazInstruccionP> ilp = new Stack<InterfazInstruccionP>();
		
		ilp.push(new InstruccionP(TokenP.SSP, rho));
		
		int pc = 0;
		for(InterfazInstruccionP i: il){
			if(i.esEtiqueta()){
		    	((Etiqueta) i).dir = pc;
		    }
		    else{
		    	pc++;
		    	ilp.push((InstruccionP)i);
		    }
		}
		ilp.push(new InstruccionP(TokenP.STP));
		return (new ProgramaP(ilp));
	}
	
	public String toString(){
		String s = "";
		int pc = 0;
		for(InterfazInstruccionP i: il){
			String l = "{" + pc + "} " + i.toString() + ";\n";
			s = s + l;
			pc++;
		}
		
	return s;
	}
}
