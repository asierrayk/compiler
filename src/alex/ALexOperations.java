package alex;

import java_cup.runtime.Symbol;
import alex.AnalizadorLexicoTiny;
import alex.UnidadLexica;
import asint.sym;

public class ALexOperations {
  private AnalizadorLexicoTiny alex;
  public ALexOperations(AnalizadorLexicoTiny alex) {
   this.alex = alex;   
  }
  public UnidadLexica unidadId() {
     return new UnidadLexica(alex.fila(),sym.IDEN,
                                         alex.lexema()); 
  } 
  public UnidadLexica unidadSi() {
     return new UnidadLexica(alex.fila(),sym.SI, alex.lexema()); 
  } 
  public UnidadLexica unidadEntonces() {
     return new UnidadLexica(alex.fila(),sym.ENTONCES, alex.lexema()); 
  }
  public UnidadLexica unidadcasoContrario() {
     return new UnidadLexica(alex.fila(),sym.CC, alex.lexema()); 
  }
  public UnidadLexica unidadFinSi() {
	  return new UnidadLexica(alex.fila(),sym.FSI, alex.lexema());
  }
  public UnidadLexica unidadMientras() {
     return new UnidadLexica(alex.fila(),sym.MIENTRAS, alex.lexema()); 
  }
  public UnidadLexica unidadFinMientras() {
     return new UnidadLexica(alex.fila(),sym.FMIENTRAS, alex.lexema()); 
  }
  public UnidadLexica unidadHacer() {
     return new UnidadLexica(alex.fila(),sym.HACER, alex.lexema()); 
  }
  public UnidadLexica unidadFuncion() {
     return new UnidadLexica(alex.fila(),sym.FUN, alex.lexema()); 
  }
  public UnidadLexica unidadEs() {
     return new UnidadLexica(alex.fila(),sym.ES, alex.lexema()); 
  }
  public UnidadLexica unidadFinFuncion() {
     return new UnidadLexica(alex.fila(),sym.FFUN, alex.lexema()); 
  }
  public UnidadLexica unidadY() {
     return new UnidadLexica(alex.fila(),sym.Y, alex.lexema()); 
  }
  public UnidadLexica unidadO() {
     return new UnidadLexica(alex.fila(),sym.O, alex.lexema()); 
  }
  public UnidadLexica unidadFlecha() {
     return new UnidadLexica(alex.fila(),sym.FLECHA, alex.lexema()); 
  }
  public UnidadLexica unidadTipo() {
     return new UnidadLexica(alex.fila(),sym.TIPO, alex.lexema()); 
  }
  public UnidadLexica unidadPuntero() {
     return new UnidadLexica(alex.fila(),sym.PUNTERO, alex.lexema()); 
  }
  public UnidadLexica unidadTiene() {
     return new UnidadLexica(alex.fila(),sym.TIENE, alex.lexema()); 
  }
  public UnidadLexica unidadFinTipo() {
     return new UnidadLexica(alex.fila(),sym.FTIPO, alex.lexema()); 
  }
  public UnidadLexica unidadEntero() {
     return new UnidadLexica(alex.fila(),sym.ENTERO, alex.lexema()); 
  }
  public UnidadLexica unidadOpcion() {
     return new UnidadLexica(alex.fila(),sym.OPCION, alex.lexema()); 
  }
  public UnidadLexica unidadLlamar() {
     return new UnidadLexica(alex.fila(),sym.LLAMAR, alex.lexema()); 
  }
  public UnidadLexica unidadLibera() {
     return new UnidadLexica(alex.fila(),sym.LIBERA, alex.lexema()); 
  }
  public UnidadLexica unidadBooleano() {
     return new UnidadLexica(alex.fila(),sym.BOOL, alex.lexema()); 
  }
  public UnidadLexica unidadMultiopcion() {
     return new UnidadLexica(alex.fila(),sym.MULTIOPCION, alex.lexema()); 
  }
  public UnidadLexica unidadFinMultiopcion() {
     return new UnidadLexica(alex.fila(),sym.FMULTIOPCION, alex.lexema()); 
  }
  public UnidadLexica unidadReserva() {
     return new UnidadLexica(alex.fila(),sym.RESERVA, alex.lexema()); 
  }
  public UnidadLexica unidadCero() {
     return new UnidadLexica(alex.fila(),sym.CERO,alex.lexema()); 
  } 
  public UnidadLexica unidadEnt() {
     return new UnidadLexica(alex.fila(),sym.ENT,alex.lexema()); 
  } 
  public UnidadLexica unidadHexadecimal() {
     return new UnidadLexica(alex.fila(),sym.HEX,alex.lexema()); 
  }
  public UnidadLexica unidadBinaria() {
     return new UnidadLexica(alex.fila(),sym.BIN,alex.lexema()); 
  }
  public UnidadLexica unidadOctal() {
     return new UnidadLexica(alex.fila(),sym.OCT,alex.lexema()); 
  }
  public UnidadLexica unidadSuma() {
     return new UnidadLexica(alex.fila(),sym.MAS, alex.lexema()); 
  } 
  public UnidadLexica unidadResta() {
     return new UnidadLexica(alex.fila(),sym.MENOS, alex.lexema()); 
  } 
  public UnidadLexica unidadMul() {
     return new UnidadLexica(alex.fila(),sym.POR, alex.lexema()); 
  } 
  public UnidadLexica unidadDiv() {
     return new UnidadLexica(alex.fila(),sym.DIV, alex.lexema()); 
  } 
  public UnidadLexica unidadPAp() {
     return new UnidadLexica(alex.fila(),sym.PAP, alex.lexema()); 
  } 
  public UnidadLexica unidadPCierre() {
     return new UnidadLexica(alex.fila(),sym.PCIERRE, alex.lexema()); 
  }
  public UnidadLexica unidadCAp() {
	  return new UnidadLexica(alex.fila(),sym.CAP, alex.lexema());
  }
  public UnidadLexica unidadCCierre() {
	  return new UnidadLexica(alex.fila(),sym.CCIERRE, alex.lexema());
  }
  public UnidadLexica unidadDir() {
	  return new UnidadLexica(alex.fila(),sym.DIR, alex.lexema());
  }
  public UnidadLexica unidadIgual() {
     return new UnidadLexica(alex.fila(),sym.IGUAL, alex.lexema()); 
  }
  public UnidadLexica unidadDistinto() {
     return new UnidadLexica(alex.fila(),sym.DISTINCT, alex.lexema()); 
  }
  public UnidadLexica unidadMenor() {
     return new UnidadLexica(alex.fila(),sym.MENOR, alex.lexema()); 
  } 
  public UnidadLexica unidadMayor() {
     return new UnidadLexica(alex.fila(),sym.MAYOR, alex.lexema()); 
  }
  public UnidadLexica unidadPunto() {
     return new UnidadLexica(alex.fila(),sym.PUNTO, alex.lexema()); 
  }
  public UnidadLexica unidadNeg() {
     return new UnidadLexica(alex.fila(),sym.NO, alex.lexema()); 
  }
  public UnidadLexica unidadFalso() {
     return new UnidadLexica(alex.fila(),sym.FALSO, alex.lexema()); 
  }
  public UnidadLexica unidadMenorOIgual() {
	     return new UnidadLexica(alex.fila(),sym.CIERTO, alex.lexema()); 
  }
  public UnidadLexica unidadMayorOIgual() {
	     return new UnidadLexica(alex.fila(),sym.CIERTO, alex.lexema()); 
  }
  public UnidadLexica unidadCierto() {
	     return new UnidadLexica(alex.fila(),sym.CIERTO, alex.lexema()); 
  }
  public UnidadLexica unidadEof() {
     return new UnidadLexica(alex.fila(),sym.EOF, alex.lexema()); 
  }
  public void error() {
    System.err.println("ERROR fila "+alex.fila()+": Caracter inesperado: "+alex.lexema());
  }
}
