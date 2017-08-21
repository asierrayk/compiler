package codigo;

public enum TokenP {
//Load and store
	
  LDO(1), // Int
  LDC(1), // Val
  IND(0),
  SRO(1), // Int
  STO(0),
  LOD(2), // Int Int
  LDA(2), // Int Int
  STR(2), // Int Int

// Arithmetic and logical ones

  ADD(0),           
  SUB(0),
  MUL(0),
  DIV(0),
  NEG(0),
  AND(0),
  OR(0),
  NOT(0),
  EQU(0),
  GEQ(0),
  LEQ(0),
  LES(0),
  GRT(0),
  NEQ(0),

// Jumps

  UJP(1), // Int
  FJP(1), // Int
  IXJ(1), // Int
  CUP(2), // Int Int

// Access to arrays and to the heap

  IXA(1), // Int 
  CHK(2), // Int Int
  INC(1), // Int
  DEC(1), // Int
  DPL(0), 
  LDD(1), // Int
  SLI(0),
  NEW(0),

// Procedures, functions, and main program 

  MOVS(1), // Int
  MOVD(1), // Int
  MST(1),  // Int
  SSP(1), // Int
  SEP(1), // Int
  RETF(0),
  RETP(0),
  STP(0);
  
  
  protected final int args;
  
  TokenP(int args){
	  this.args = args;
  }
  
  public String toString(){
	  return(super.toString().toLowerCase());
  }
}
