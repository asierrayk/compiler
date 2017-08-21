package errors;

import alex.UnidadLexica;

public class GestionErroresTiny {
   public void errorLexico(int fila, String lexema) {
     System.err.println("ERROR fila "+fila+": Caracter inesperado: "+lexema+"\n"); 
   }  
   public void errorSintactico(UnidadLexica unidadLexica) {
     System.err.print("ERROR fila "+unidadLexica.fila()+": Elemento inesperado "+unidadLexica.value+"\n");
   }
   public void errorIdentificadorExistente(int i, String string) {
     System.err.print("ERROR fila " + i + ": Identificador " + string + " ya definido\n");
   }
   public void errorIdentificadorNoExiste(int i, String string) {
     System.err.print("ERROR fila " + i + ": Identificador " + string + " no definido\n");
   }
   public void errorDeTipos(int i, String c1, String c2) {
     System.err.print("ERROR fila " + i + ": No se pudieron unificar las clases "+c1+" y "+c2+"\n");
   }
   public void errorDeTipos(int i, String string) {
	 System.err.print("ERROR fila " + i + ": No es compatible el tipo "+string+"\n");
   }
   public void errorIdentificadorIncorrecto(int i, String string) {
	   System.err.print("ERROR fila " + i + ": Identificador " + string + " invalido\n");
   }
   public void errorDeTipos(int line) {
	   System.err.print("ERROR fila "+ line +": Se ha intentado comprobar el tipo de un identificador incorrecto\n");
   }
}
