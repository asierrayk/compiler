package asint;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import codigo.ProgramaP;
import tree.Programa;
import aiden.VisitanteIdentificadores;
import aiden.VisitanteTipos;
import alex.AnalizadorLexicoTiny;

public class Main {
   public static void main(String[] args) throws Exception {
     Reader input = new InputStreamReader(new FileInputStream("input.txt"));
	 AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
	 parser asint = new parser(alex);
	 asint.setScanner(alex);
	 Programa p = (Programa) asint.parse().value;
	 VisitanteIdentificadores vi = new VisitanteIdentificadores();
	 p.accept(vi);
	 p.accept(new VisitanteTipos());
	 
	 ProgramaP pr = new ProgramaP(vi.getRho());
	 pr = p.code(pr);
	 
	 pr = pr.quitaEtiquetas();
	 
	 System.out.println("CODIGO: ");
	 System.out.println(pr.toString());
 }
}   
   
