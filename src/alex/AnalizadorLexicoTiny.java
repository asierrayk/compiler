package alex;

import java_cup.runtime.Symbol;
import alex.ALexOperations;
import errors.GestionErroresTiny;

public class AnalizadorLexicoTiny implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 65536;
	private final int YY_EOF = 65537;

	private ALexOperations ops;
	private GestionErroresTiny errores;

	public String lexema() {
		return yytext();
	}

	public int fila() {
		return yyline + 1;
	}

	public void fijaGestionErrores(GestionErroresTiny errores) {
		this.errores = errores;
	}

	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	public AnalizadorLexicoTiny(java.io.Reader reader) {
		this();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	AnalizadorLexicoTiny(java.io.InputStream instream) {
		this();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(
				instream));
	}

	private AnalizadorLexicoTiny() {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;

		ops = new ALexOperations(this);
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = { 0 };

	private void yybegin(int state) {
		yy_lexical_state = state;
	}

	private int yy_advance() throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer, yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer, yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}

	private void yy_move_end() {
		if (yy_buffer_end > yy_buffer_start
				&& '\n' == yy_buffer[yy_buffer_end - 1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start
				&& '\r' == yy_buffer[yy_buffer_end - 1])
			yy_buffer_end--;
	}

	private boolean yy_last_was_cr = false;

	private void yy_mark_start() {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr = true;
			} else
				yy_last_was_cr = false;
		}
		yy_buffer_start = yy_buffer_index;
	}

	private void yy_mark_end() {
		yy_buffer_end = yy_buffer_index;
	}

	private void yy_to_mark() {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start)
				&& ('\r' == yy_buffer[yy_buffer_end - 1]
						|| '\n' == yy_buffer[yy_buffer_end - 1]
						|| 2028/* LS */== yy_buffer[yy_buffer_end - 1] || 2029/* PS */== yy_buffer[yy_buffer_end - 1]);
	}

	private java.lang.String yytext() {
		return (new java.lang.String(yy_buffer, yy_buffer_start, yy_buffer_end
				- yy_buffer_start));
	}

	private int yylength() {
		return yy_buffer_end - yy_buffer_start;
	}

	private char[] yy_double(char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2 * buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}

	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = { "Error: Internal error.\n",
			"Error: Unmatched input.\n" };

	private void yy_error(int code, boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}

	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i = 0; i < size1; i++) {
			for (int j = 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex == -1) ? st : st.substring(0,
						commaIndex);
				st = st.substring(commaIndex + 1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j] = Integer.parseInt(workString);
					continue;
				}
				lengthString = workString.substring(colonIndex + 1);
				sequenceLength = Integer.parseInt(lengthString);
				workString = workString.substring(0, colonIndex);
				sequenceInteger = Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}

	private int yy_acpt[] = {
	/* 0 */YY_NOT_ACCEPT,
	/* 1 */YY_NO_ANCHOR,
	/* 2 */YY_NO_ANCHOR,
	/* 3 */YY_NO_ANCHOR,
	/* 4 */YY_NO_ANCHOR,
	/* 5 */YY_NO_ANCHOR,
	/* 6 */YY_NO_ANCHOR,
	/* 7 */YY_NO_ANCHOR,
	/* 8 */YY_NO_ANCHOR,
	/* 9 */YY_NO_ANCHOR,
	/* 10 */YY_NO_ANCHOR,
	/* 11 */YY_NO_ANCHOR,
	/* 12 */YY_NO_ANCHOR,
	/* 13 */YY_NO_ANCHOR,
	/* 14 */YY_NO_ANCHOR,
	/* 15 */YY_NO_ANCHOR,
	/* 16 */YY_NO_ANCHOR,
	/* 17 */YY_NO_ANCHOR,
	/* 18 */YY_NO_ANCHOR,
	/* 19 */YY_NO_ANCHOR,
	/* 20 */YY_NO_ANCHOR,
	/* 21 */YY_NO_ANCHOR,
	/* 22 */YY_NO_ANCHOR,
	/* 23 */YY_NO_ANCHOR,
	/* 24 */YY_NO_ANCHOR,
	/* 25 */YY_NO_ANCHOR,
	/* 26 */YY_NO_ANCHOR,
	/* 27 */YY_NO_ANCHOR,
	/* 28 */YY_NO_ANCHOR,
	/* 29 */YY_NO_ANCHOR,
	/* 30 */YY_NO_ANCHOR,
	/* 31 */YY_NO_ANCHOR,
	/* 32 */YY_NO_ANCHOR,
	/* 33 */YY_NO_ANCHOR,
	/* 34 */YY_NO_ANCHOR,
	/* 35 */YY_NO_ANCHOR,
	/* 36 */YY_NO_ANCHOR,
	/* 37 */YY_NO_ANCHOR,
	/* 38 */YY_NO_ANCHOR,
	/* 39 */YY_NO_ANCHOR,
	/* 40 */YY_NO_ANCHOR,
	/* 41 */YY_NO_ANCHOR,
	/* 42 */YY_NO_ANCHOR,
	/* 43 */YY_NO_ANCHOR,
	/* 44 */YY_NO_ANCHOR,
	/* 45 */YY_NO_ANCHOR,
	/* 46 */YY_NO_ANCHOR,
	/* 47 */YY_NO_ANCHOR,
	/* 48 */YY_NO_ANCHOR,
	/* 49 */YY_NO_ANCHOR,
	/* 50 */YY_NO_ANCHOR,
	/* 51 */YY_NO_ANCHOR,
	/* 52 */YY_NO_ANCHOR,
	/* 53 */YY_NO_ANCHOR,
	/* 54 */YY_NO_ANCHOR,
	/* 55 */YY_NO_ANCHOR,
	/* 56 */YY_NOT_ACCEPT,
	/* 57 */YY_NO_ANCHOR,
	/* 58 */YY_NO_ANCHOR,
	/* 59 */YY_NOT_ACCEPT,
	/* 60 */YY_NO_ANCHOR,
	/* 61 */YY_NO_ANCHOR,
	/* 62 */YY_NOT_ACCEPT,
	/* 63 */YY_NO_ANCHOR,
	/* 64 */YY_NO_ANCHOR,
	/* 65 */YY_NOT_ACCEPT,
	/* 66 */YY_NO_ANCHOR,
	/* 67 */YY_NOT_ACCEPT,
	/* 68 */YY_NO_ANCHOR,
	/* 69 */YY_NO_ANCHOR,
	/* 70 */YY_NO_ANCHOR,
	/* 71 */YY_NO_ANCHOR,
	/* 72 */YY_NO_ANCHOR,
	/* 73 */YY_NO_ANCHOR,
	/* 74 */YY_NO_ANCHOR,
	/* 75 */YY_NO_ANCHOR,
	/* 76 */YY_NO_ANCHOR,
	/* 77 */YY_NO_ANCHOR,
	/* 78 */YY_NO_ANCHOR,
	/* 79 */YY_NO_ANCHOR,
	/* 80 */YY_NO_ANCHOR,
	/* 81 */YY_NO_ANCHOR,
	/* 82 */YY_NO_ANCHOR,
	/* 83 */YY_NO_ANCHOR,
	/* 84 */YY_NO_ANCHOR,
	/* 85 */YY_NO_ANCHOR,
	/* 86 */YY_NO_ANCHOR,
	/* 87 */YY_NO_ANCHOR,
	/* 88 */YY_NO_ANCHOR,
	/* 89 */YY_NO_ANCHOR,
	/* 90 */YY_NO_ANCHOR,
	/* 91 */YY_NO_ANCHOR,
	/* 92 */YY_NO_ANCHOR,
	/* 93 */YY_NO_ANCHOR,
	/* 94 */YY_NO_ANCHOR,
	/* 95 */YY_NO_ANCHOR,
	/* 96 */YY_NO_ANCHOR,
	/* 97 */YY_NO_ANCHOR,
	/* 98 */YY_NO_ANCHOR,
	/* 99 */YY_NO_ANCHOR,
	/* 100 */YY_NO_ANCHOR,
	/* 101 */YY_NO_ANCHOR,
	/* 102 */YY_NO_ANCHOR,
	/* 103 */YY_NO_ANCHOR,
	/* 104 */YY_NO_ANCHOR,
	/* 105 */YY_NO_ANCHOR,
	/* 106 */YY_NO_ANCHOR,
	/* 107 */YY_NO_ANCHOR,
	/* 108 */YY_NO_ANCHOR,
	/* 109 */YY_NO_ANCHOR,
	/* 110 */YY_NO_ANCHOR,
	/* 111 */YY_NO_ANCHOR,
	/* 112 */YY_NO_ANCHOR,
	/* 113 */YY_NO_ANCHOR,
	/* 114 */YY_NO_ANCHOR,
	/* 115 */YY_NO_ANCHOR,
	/* 116 */YY_NO_ANCHOR,
	/* 117 */YY_NO_ANCHOR,
	/* 118 */YY_NO_ANCHOR,
	/* 119 */YY_NO_ANCHOR,
	/* 120 */YY_NO_ANCHOR,
	/* 121 */YY_NO_ANCHOR,
	/* 122 */YY_NO_ANCHOR,
	/* 123 */YY_NO_ANCHOR,
	/* 124 */YY_NO_ANCHOR,
	/* 125 */YY_NO_ANCHOR,
	/* 126 */YY_NO_ANCHOR,
	/* 127 */YY_NO_ANCHOR,
	/* 128 */YY_NO_ANCHOR,
	/* 129 */YY_NO_ANCHOR,
	/* 130 */YY_NO_ANCHOR,
	/* 131 */YY_NO_ANCHOR,
	/* 132 */YY_NO_ANCHOR,
	/* 133 */YY_NO_ANCHOR,
	/* 134 */YY_NO_ANCHOR,
	/* 135 */YY_NO_ANCHOR,
	/* 136 */YY_NO_ANCHOR,
	/* 137 */YY_NO_ANCHOR,
	/* 138 */YY_NO_ANCHOR,
	/* 139 */YY_NO_ANCHOR,
	/* 140 */YY_NO_ANCHOR,
	/* 141 */YY_NO_ANCHOR,
	/* 142 */YY_NO_ANCHOR,
	/* 143 */YY_NO_ANCHOR,
	/* 144 */YY_NO_ANCHOR,
	/* 145 */YY_NO_ANCHOR,
	/* 146 */YY_NO_ANCHOR,
	/* 147 */YY_NO_ANCHOR,
	/* 148 */YY_NO_ANCHOR,
	/* 149 */YY_NO_ANCHOR,
	/* 150 */YY_NO_ANCHOR,
	/* 151 */YY_NO_ANCHOR,
	/* 152 */YY_NO_ANCHOR,
	/* 153 */YY_NO_ANCHOR,
	/* 154 */YY_NO_ANCHOR,
	/* 155 */YY_NO_ANCHOR,
	/* 156 */YY_NO_ANCHOR,
	/* 157 */YY_NO_ANCHOR,
	/* 158 */YY_NO_ANCHOR,
	/* 159 */YY_NO_ANCHOR,
	/* 160 */YY_NO_ANCHOR,
	/* 161 */YY_NO_ANCHOR,
	/* 162 */YY_NO_ANCHOR,
	/* 163 */YY_NO_ANCHOR,
	/* 164 */YY_NO_ANCHOR,
	/* 165 */YY_NO_ANCHOR,
	/* 166 */YY_NO_ANCHOR,
	/* 167 */YY_NO_ANCHOR,
	/* 168 */YY_NO_ANCHOR,
	/* 169 */YY_NO_ANCHOR

	};
	private int yy_cmap[] = unpackFromString(
			1,
			65538,
			"3:8,5:2,1,3:2,5,3:18,5,3:5,40,3,35,36,33,31,24,32,42,34,23,28,30:6,25:2,3:2"
					+ ",2,41,4,3,39,27:6,43:20,37,3,38,3:3,13,11,18,27,6,19,43,21,17,43:2,12,20,7,"
					+ "10,14,43,9,16,8,15,22,43,26,43:2,3,29,3:65411,0:2")[0];

	private int yy_rmap[] = unpackFromString(
			1,
			170,
			"0,1:2,2,1,3,4,5,6,7,8,1,9,1:8,10,1,11:4,1:4,11,12,13,14,1,11:20,15,7,16,12,"
					+ "17,18,13,19,20,14,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,"
					+ "40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,"
					+ "65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,"
					+ "90,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,107,108,109,110,1"
					+ "11,112,113,11,114,115,116,117,118,119,120,121,122,123")[0];

	private int yy_nxt[][] = unpackFromString(
			124,
			44,
			"1,2,3,4,5,2,6,58,61,140,154,157,158,159,160,159,64,159,66,89,161,162,159,7,"
					+ "57,8,159:2,8,60,8,9,10,11,12,13,14,15,16,17,63,18,19,159,-1:46,56,-1:38,20,"
					+ "-1:6,21,-1:36,22,-1:8,159,163,159:8,23,159:7,-1,159:4,-1,159,-1:12,159,-1:1"
					+ "1,59,-1:6,62,-1:7,65,-1:40,8,-1,8,-1:2,8,-1,8,-1:38,8,-1:2,8,-1,8,-1:17,28,"
					+ "-1:20,8,-1:2,8,-1,8,-1:54,29,-1:4,21:42,-1:6,159:18,-1,159:4,-1,159,-1:12,1"
					+

					"59,-1:23,32,-1:4,32:2,-1:37,33,-1:4,33,-1,33,-1:19,34,-1:4,34,-1,34,-1:4,34"
					+ ":2,-1:3,34,-1,34,-1,34:2,-1,34,-1:14,56:3,67,56:39,-1:6,159:4,24,159:13,-1,"
					+ "159:4,-1,159,-1:12,159,-1:29,27,-1:20,159:11,90,159:6,-1,159:4,-1,159,-1:12"
					+

					",159,-1:40,30,-1:9,159:11,25,159:6,-1,159:4,-1,159,-1:12,159,-1:6,159:11,97"
					+ ",26,159:5,-1,159:4,-1,159,-1:12,159,-1,56:3,35,56:39,-1:6,159:11,31,159:6,-"
					+

					"1,159:4,-1,159,-1:12,159,-1:6,159:4,36,159:13,-1,159:4,-1,159,-1:12,159,-1:"
					+ "6,159:2,37,159:15,-1,159:4,-1,159,-1:12,159,-1:6,38,159:17,-1,159:4,-1,159,"
					+ "-1:12,159,-1:6,159:4,39,159:13,-1,159:4,-1,159,-1:12,159,-1:6,159:4,40,159:"
					+

					"13,-1,159:4,-1,159,-1:12,159,-1:6,159:3,41,159:14,-1,159:4,-1,159,-1:12,159"
					+ ",-1:6,159:4,42,159:13,-1,159:4,-1,159,-1:12,159,-1:6,159,43,159:16,-1,159:4"
					+ ",-1,159,-1:12,159,-1:6,159:3,44,159:14,-1,159:4,-1,159,-1:12,159,-1:6,159:7"
					+

					",45,159:10,-1,159:4,-1,159,-1:12,159,-1:6,159:4,46,159:13,-1,159:4,-1,159,-"
					+ "1:12,159,-1:6,159:7,47,159:10,-1,159:4,-1,159,-1:12,159,-1:6,159,48,159:16,"
					+

					"-1,159:4,-1,159,-1:12,159,-1:6,159:10,49,159:7,-1,159:4,-1,159,-1:12,159,-1"
					+

					":6,159:4,50,159:13,-1,159:4,-1,159,-1:12,159,-1:6,159,51,159:16,-1,159:4,-1"
					+ ",159,-1:12,159,-1:6,159:10,52,159:7,-1,159:4,-1,159,-1:12,159,-1:6,159:10,5"
					+ "3,159:7,-1,159:4,-1,159,-1:12,159,-1:6,159,54,159:16,-1,159:4,-1,159,-1:12,"
					+

					"159,-1:6,159,55,159:16,-1,159:4,-1,159,-1:12,159,-1:6,159:2,98,159:4,99,159"
					+ ",100,68,159:2,101,102,159:3,-1,159:4,-1,159,-1:12,159,-1:6,104,159:7,69,159"
					+ ":9,-1,159:4,-1,159,-1:12,159,-1:6,159:10,164,159:7,-1,159:4,-1,159,-1:12,15"
					+

					"9,-1:6,159:12,143,159:5,-1,159:4,-1,159,-1:12,159,-1:6,159:4,156,159:13,-1,"
					+ "159:4,-1,159,-1:12,159,-1:6,159:7,105,159:10,-1,159:4,-1,159,-1:12,159,-1:6"
					+ ",159:5,155,159:12,-1,159:4,-1,159,-1:12,159,-1:6,159,70,159:16,-1,159:4,-1,"
					+

					"159,-1:12,159,-1:6,106,159:17,-1,159:4,-1,159,-1:12,159,-1:6,159:11,107,159"
					+ ":6,-1,159:4,-1,159,-1:12,159,-1:6,159:6,108,159:11,-1,159:4,-1,159,-1:12,15"
					+

					"9,-1:6,159,109,159:16,-1,159:4,-1,159,-1:12,159,-1:6,159:9,110,159:8,-1,159"
					+ ":4,-1,159,-1:12,159,-1:6,159:9,111,159,112,159:6,-1,159:4,-1,159,-1:12,159,"
					+ "-1:6,115,159:3,165,159:13,-1,159:4,-1,159,-1:12,159,-1:6,159,71,159:16,-1,1"
					+

					"59:4,-1,159,-1:12,159,-1:6,159:14,117,159:3,-1,159:4,-1,159,-1:12,159,-1:6,"
					+

					"159:3,119,159:14,-1,159:4,-1,159,-1:12,159,-1:6,159:8,72,159:9,-1,159:4,-1,"
					+ "159,-1:12,159,-1:6,159:10,73,159:7,-1,159:4,-1,159,-1:12,159,-1:6,159:12,12"
					+ "0,159:5,-1,159:4,-1,159,-1:12,159,-1:6,159,121,159:16,-1,159:4,-1,159,-1:12"
					+

					",159,-1:6,159:6,148,159:11,-1,159:4,-1,159,-1:12,159,-1:6,122,159:17,-1,159"
					+ ":4,-1,159,-1:12,159,-1:6,159:2,149,159:15,-1,159:4,-1,159,-1:12,159,-1:6,74"
					+

					",159:17,-1,159:4,-1,159,-1:12,159,-1:6,159:3,75,159:14,-1,159:4,-1,159,-1:1"
					+

					"2,159,-1:6,159:4,76,159:13,-1,159:4,-1,159,-1:12,159,-1:6,159:7,77,159:10,-"
					+ "1,159:4,-1,159,-1:12,159,-1:6,159:3,78,159:14,-1,159:4,-1,159,-1:12,159,-1:"
					+

					"6,159:2,79,159:15,-1,159:4,-1,159,-1:12,159,-1:6,159:11,126,159:6,-1,159:4,"
					+

					"-1,159,-1:12,159,-1:6,159:12,127,159:5,-1,159:4,-1,159,-1:12,159,-1:6,159,1"
					+ "66,159:16,-1,159:4,-1,159,-1:12,159,-1:6,159:2,129,159:15,-1,159:4,-1,159,-"
					+

					"1:12,159,-1:6,159:16,80,159,-1,159:4,-1,159,-1:12,159,-1:6,159:7,131,159:10"
					+

					",-1,159:4,-1,159,-1:12,159,-1:6,159:4,81,159:13,-1,159:4,-1,159,-1:12,159,-"
					+ "1:6,159:11,132,159:6,-1,159:4,-1,159,-1:12,159,-1:6,159:4,133,159:13,-1,159"
					+ ":4,-1,159,-1:12,159,-1:6,159:3,134,159:14,-1,159:4,-1,159,-1:12,159,-1:6,82"
					+

					",159:17,-1,159:4,-1,159,-1:12,159,-1:6,159,83,159:16,-1,159:4,-1,159,-1:12,"
					+

					"159,-1:6,159:4,84,159:13,-1,159:4,-1,159,-1:12,159,-1:6,159:8,136,159:9,-1,"
					+ "159:4,-1,159,-1:12,159,-1:6,159:7,85,159:10,-1,159:4,-1,159,-1:12,159,-1:6,"
					+

					"159:7,86,159:10,-1,159:4,-1,159,-1:12,159,-1:6,159:12,137,159:5,-1,159:4,-1"
					+

					",159,-1:12,159,-1:6,159:11,138,159:6,-1,159:4,-1,159,-1:12,159,-1:6,159:4,8"
					+ "7,159:13,-1,159:4,-1,159,-1:12,159,-1:6,159:4,88,159:13,-1,159:4,-1,159,-1:"
					+

					"12,159,-1:6,91,159:17,-1,159:4,-1,159,-1:12,159,-1:6,159:12,114,159:5,-1,15"
					+

					"9:4,-1,159,-1:12,159,-1:6,146,159:17,-1,159:4,-1,159,-1:12,159,-1:6,159:11,"
					+ "116,159:6,-1,159:4,-1,159,-1:12,159,-1:6,159:6,113,159:11,-1,159:4,-1,159,-"
					+ "1:12,159,-1:6,159:3,124,159:14,-1,159:4,-1,159,-1:12,159,-1:6,159,123,159:1"
					+

					"6,-1,159:4,-1,159,-1:12,159,-1:6,125,159:17,-1,159:4,-1,159,-1:12,159,-1:6,"
					+

					"159:2,151,159:15,-1,159:4,-1,159,-1:12,159,-1:6,159:11,128,159:6,-1,159:4,-"
					+

					"1,159,-1:12,159,-1:6,159:12,130,159:5,-1,159:4,-1,159,-1:12,159,-1:6,159:11"
					+ ",169,159:6,-1,159:4,-1,159,-1:12,159,-1:6,159:3,135,159:14,-1,159:4,-1,159,"
					+ "-1:12,159,-1:6,159:11,139,159:6,-1,159:4,-1,159,-1:12,159,-1:6,159:8,92,159"
					+

					":9,-1,159:4,-1,159,-1:12,159,-1:6,118,159:17,-1,159:4,-1,159,-1:12,159,-1:6"
					+ ",159:6,147,159:11,-1,159:4,-1,159,-1:12,159,-1:6,159:4,93,159:13,-1,159:4,-"
					+ "1,159,-1:12,159,-1:6,159:6,94,159:4,95,159:6,-1,159:4,-1,159,-1:12,159,-1:6"
					+ ",159:9,96,159:8,-1,159:4,-1,159,-1:12,159,-1:6,159:9,144,159,142,159:6,-1,1"
					+

					"59:4,-1,159,-1:12,159,-1:6,159:7,141,159:10,-1,159:4,-1,159,-1:12,159,-1:6,"
					+ "159:2,103,159:15,-1,159:4,-1,159,-1:12,159,-1:6,145,159:17,-1,159:4,-1,159,"
					+

					"-1:12,159,-1:6,159,150,159:16,-1,159:4,-1,159,-1:12,159,-1:6,159:2,152,159:"
					+ "15,-1,159:4,-1,159,-1:12,159,-1:6,159:12,153,159:5,-1,159:4,-1,159,-1:12,15"
					+ "9,-1:6,159:8,167,159:9,-1,159:4,-1,159,-1:12,159,-1:6,159:4,168,159:13,-1,1"
					+

					"59:4,-1,159,-1:12,159");

	public Symbol next_token() throws Exception {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol)
				yy_lookahead = YY_BOL;
			else
				yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {

				return ops.unidadEof();
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			} else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				} else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:

					case -2:
						break;
					case 2: {
					}
					case -3:
						break;
					case 3: {
						return ops.unidadMenor();
					}
					case -4:
						break;
					case 4: {
						ops.error();
					}
					case -5:
						break;
					case 5: {
						return ops.unidadMayor();
					}
					case -6:
						break;
					case 6: {
						return ops.unidadId();
					}
					case -7:
						break;
					case 7: {
						return ops.unidadCero();
					}
					case -8:
						break;
					case 8: {
						return ops.unidadEnt();
					}

					case -9:
						break;
					case 9: {
						return ops.unidadSuma();
					}

					case -10:
						break;
					case 10: {
						return ops.unidadResta();
					}

					case -11:
						break;
					case 11: {
						return ops.unidadMul();
					}
					case -12:
						break;
					case 12: {
						return ops.unidadDiv();
					}
					case -13:
						break;
					case 13: {
						return ops.unidadPAp();
					}

					case -14:
						break;
					case 14: {
						return ops.unidadPCierre();
					}

					case -15:
						break;
					case 15: {
						return ops.unidadCAp();
					}

					case -16:
						break;
					case 16: {
						return ops.unidadCCierre();
					}

					case -17:
						break;
					case 17: {
						return ops.unidadDir();
					}

					case -18:
						break;
					case 18: {
						return ops.unidadIgual();
					}

					case -19:
						break;
					case 19: {
						return ops.unidadPunto();
					}

					case -20:
						break;
					case 20: {
						return ops.unidadMenorOIgual();
					}

					case -21:
						break;
					case 21: {
					}
					case -22:
						break;
					case 22: {
						return ops.unidadMayorOIgual();
					}

					case -23:
						break;
					case 23: {
						return ops.unidadEs();
					}

					case -24:
						break;
					case 24: {
						return ops.unidadNeg();
					}

					case -25:
						break;
					case 25: {
						return ops.unidadSi();
					}
					case -26:
						break;
					case 26: {
						return ops.unidadcasoContrario();
					}
					case -27:
						break;
					case 27: {
						return ops.unidadO();
					}

					case -28:
						break;
					case 28: {
						return ops.unidadFlecha();
					}

					case -29:
						break;
					case 29: {
						return ops.unidadDistinto();
					}

					case -30:
						break;
					case 30: {
						return ops.unidadY();
					}

					case -31:
						break;
					case 31: {
						return ops.unidadFinSi();
					}

					case -32:
						break;
					case 32: {
						return ops.unidadBinaria();
					}

					case -33:
						break;
					case 33: {
						return ops.unidadOctal();
					}

					case -34:
						break;
					case 34: {
						return ops.unidadHexadecimal();
					}

					case -35:
						break;
					case 35: {
					}

					case -36:
						break;
					case 36: {
						return ops.unidadTipo();
					}

					case -37:
						break;
					case 37: {
						return ops.unidadPuntero();
					}

					case -38:
						break;
					case 38: {
						return ops.unidadTiene();
					}

					case -39:
						break;
					case 39: {
						return ops.unidadFinTipo();
					}

					case -40:
						break;
					case 40: {
						return ops.unidadFalso();
					}

					case -41:
						break;
					case 41: {
						return ops.unidadHacer();
					}

					case -42:
						break;
					case 42: {
						return ops.unidadEntero();
					}

					case -43:
						break;
					case 43: {
						return ops.unidadOpcion();
					}

					case -44:
						break;
					case 44: {
						return ops.unidadLlamar();
					}

					case -45:
						break;
					case 45: {
						return ops.unidadLibera();
					}

					case -46:
						break;
					case 46: {
						return ops.unidadCierto();
					}

					case -47:
						break;
					case 47: {
						return ops.unidadReserva();
					}

					case -48:
						break;
					case 48: {
						return ops.unidadFuncion();
					}

					case -49:
						break;
					case 49: {
						return ops.unidadEntonces();
					}

					case -50:
						break;
					case 50: {
						return ops.unidadBooleano();
					}

					case -51:
						break;
					case 51: {
						return ops.unidadFinFuncion();
					}

					case -52:
						break;
					case 52: {
						return ops.unidadMientras();
					}

					case -53:
						break;
					case 53: {
						return ops.unidadFinMientras();
					}

					case -54:
						break;
					case 54: {
						return ops.unidadMultiopcion();
					}

					case -55:
						break;
					case 55: {
						return ops.unidadFinMultiopcion();
					}

					case -56:
						break;
					case 57: {
						ops.error();
					}

					case -57:
						break;
					case 58: {
						return ops.unidadId();
					}

					case -58:
						break;
					case 60: {
						ops.error();
					}

					case -59:
						break;
					case 61:

					{
						return ops.unidadId();
					}
					case -60:
						break;
					case 63: {
						ops.error();
					}

					case -61:
						break;
					case 64: {
						return ops.unidadId();
					}

					case -62:
						break;
					case 66: {
						return ops.unidadId();
					}

					case -63:
						break;
					case 68:

					{
						return ops.unidadId();
					}
					case -64:
						break;
					case 69: {
						return ops.unidadId();
					}

					case -65:
						break;
					case 70:

					{
						return ops.unidadId();
					}
					case -66:
						break;
					case 71: {
						return ops.unidadId();
					}

					case -67:
						break;
					case 72:

					{
						return ops.unidadId();
					}
					case -68:
						break;
					case 73: {
						return ops.unidadId();
					}

					case -69:
						break;
					case 74:

					{
						return ops.unidadId();
					}
					case -70:
						break;
					case 75: {
						return ops.unidadId();
					}

					case -71:
						break;
					case 76: {
						return ops.unidadId();
					}
					case -72:
						break;
					case 77:

					{
						return ops.unidadId();
					}
					case -73:
						break;
					case 78:

					{
						return ops.unidadId();
					}
					case -74:
						break;
					case 79:

					{
						return ops.unidadId();
					}
					case -75:
						break;
					case 80:

					{
						return ops.unidadId();
					}
					case -76:
						break;
					case 81:

					{
						return ops.unidadId();
					}
					case -77:
						break;
					case 82:

					{
						return ops.unidadId();
					}
					case -78:
						break;
					case 83:

					{
						return ops.unidadId();
					}
					case -79:
						break;
					case 84:

					{
						return ops.unidadId();
					}
					case -80:
						break;
					case 85:

					{
						return ops.unidadId();
					}
					case -81:
						break;
					case 86:

					{
						return ops.unidadId();
					}
					case -82:
						break;
					case 87:

					{
						return ops.unidadId();
					}
					case -83:
						break;
					case 88:

					{
						return ops.unidadId();
					}
					case -84:
						break;
					case 89:

					{
						return ops.unidadId();
					}
					case -85:
						break;
					case 90:

					{
						return ops.unidadId();
					}
					case -86:
						break;
					case 91:

					{
						return ops.unidadId();
					}
					case -87:
						break;
					case 92:

					{
						return ops.unidadId();
					}
					case -88:
						break;
					case 93:

					{
						return ops.unidadId();
					}
					case -89:
						break;
					case 94:

					{
						return ops.unidadId();
					}
					case -90:
						break;
					case 95:

					{
						return ops.unidadId();
					}
					case -91:
						break;
					case 96:

					{
						return ops.unidadId();
					}
					case -92:
						break;
					case 97:

					{
						return ops.unidadId();
					}
					case -93:
						break;
					case 98:

					{
						return ops.unidadId();
					}
					case -94:
						break;
					case 99:

					{
						return ops.unidadId();
					}
					case -95:
						break;
					case 100:

					{
						return ops.unidadId();
					}
					case -96:
						break;
					case 101:

					{
						return ops.unidadId();
					}
					case -97:
						break;
					case 102:

					{
						return ops.unidadId();
					}
					case -98:
						break;
					case 103:

					{
						return ops.unidadId();
					}
					case -99:
						break;
					case 104:

					{
						return ops.unidadId();
					}
					case -100:
						break;
					case 105:

					{
						return ops.unidadId();
					}
					case -101:
						break;
					case 106:

					{
						return ops.unidadId();
					}
					case -102:
						break;
					case 107:

					{
						return ops.unidadId();
					}
					case -103:
						break;
					case 108:

					{
						return ops.unidadId();
					}
					case -104:
						break;
					case 109:

					{
						return ops.unidadId();
					}
					case -105:
						break;
					case 110:

					{
						return ops.unidadId();
					}
					case -106:
						break;
					case 111:

					{
						return ops.unidadId();
					}
					case -107:
						break;
					case 112:

					{
						return ops.unidadId();
					}
					case -108:
						break;
					case 113:

					{
						return ops.unidadId();
					}
					case -109:
						break;
					case 114:

					{
						return ops.unidadId();
					}
					case -110:
						break;
					case 115:

					{
						return ops.unidadId();
					}
					case -111:
						break;
					case 116:

					{
						return ops.unidadId();
					}
					case -112:
						break;
					case 117:

					{
						return ops.unidadId();
					}
					case -113:
						break;
					case 118:

					{
						return ops.unidadId();
					}
					case -114:
						break;
					case 119:

					{
						return ops.unidadId();
					}
					case -115:
						break;
					case 120:

					{
						return ops.unidadId();
					}
					case -116:
						break;
					case 121:

					{
						return ops.unidadId();
					}
					case -117:
						break;
					case 122:

					{
						return ops.unidadId();
					}
					case -118:
						break;
					case 123:

					{
						return ops.unidadId();
					}
					case -119:
						break;
					case 124:

					{
						return ops.unidadId();
					}
					case -120:
						break;
					case 125:

					{
						return ops.unidadId();
					}
					case -121:
						break;
					case 126:

					{
						return ops.unidadId();
					}
					case -122:
						break;
					case 127:

					{
						return ops.unidadId();
					}
					case -123:
						break;
					case 128:

					{
						return ops.unidadId();
					}
					case -124:
						break;
					case 129:

					{
						return ops.unidadId();
					}
					case -125:
						break;
					case 130:

					{
						return ops.unidadId();
					}
					case -126:
						break;
					case 131:

					{
						return ops.unidadId();
					}
					case -127:
						break;
					case 132:

					{
						return ops.unidadId();
					}
					case -128:
						break;
					case 133:

					{
						return ops.unidadId();
					}
					case -129:
						break;
					case 134:

					{
						return ops.unidadId();
					}
					case -130:
						break;
					case 135:

					{
						return ops.unidadId();
					}
					case -131:
						break;
					case 136:

					{
						return ops.unidadId();
					}
					case -132:
						break;
					case 137:

					{
						return ops.unidadId();
					}
					case -133:
						break;
					case 138:

					{
						return ops.unidadId();
					}
					case -134:
						break;
					case 139:

					{
						return ops.unidadId();
					}
					case -135:
						break;
					case 140:

					{
						return ops.unidadId();
					}
					case -136:
						break;
					case 141:

					{
						return ops.unidadId();
					}
					case -137:
						break;
					case 142:

					{
						return ops.unidadId();
					}
					case -138:
						break;
					case 143:

					{
						return ops.unidadId();
					}
					case -139:
						break;
					case 144:

					{
						return ops.unidadId();
					}
					case -140:
						break;
					case 145:

					{
						return ops.unidadId();
					}
					case -141:
						break;
					case 146:

					{
						return ops.unidadId();
					}
					case -142:
						break;
					case 147:

					{
						return ops.unidadId();
					}
					case -143:
						break;
					case 148:

					{
						return ops.unidadId();
					}
					case -144:
						break;
					case 149:

					{
						return ops.unidadId();
					}
					case -145:
						break;
					case 150:

					{
						return ops.unidadId();
					}
					case -146:
						break;
					case 151:

					{
						return ops.unidadId();
					}
					case -147:
						break;
					case 152:

					{
						return ops.unidadId();
					}
					case -148:
						break;
					case 153:

					{
						return ops.unidadId();
					}
					case -149:
						break;
					case 154:

					{
						return ops.unidadId();
					}
					case -150:
						break;
					case 155:

					{
						return ops.unidadId();
					}
					case -151:
						break;
					case 156:

					{
						return ops.unidadId();
					}
					case -152:
						break;
					case 157:

					{
						return ops.unidadId();
					}
					case -153:
						break;
					case 158:

					{
						return ops.unidadId();
					}
					case -154:
						break;
					case 159:

					{
						return ops.unidadId();
					}
					case -155:
						break;
					case 160:

					{
						return ops.unidadId();
					}
					case -156:
						break;
					case 161:

					{
						return ops.unidadId();
					}
					case -157:
						break;
					case 162:

					{
						return ops.unidadId();
					}
					case -158:
						break;
					case 163:

					{
						return ops.unidadId();
					}
					case -159:
						break;
					case 164:

					{
						return ops.unidadId();
					}
					case -160:
						break;
					case 165:

					{
						return ops.unidadId();
					}
					case -161:
						break;
					case 166:

					{
						return ops.unidadId();
					}
					case -162:
						break;
					case 167:

					{
						return ops.unidadId();
					}
					case -163:
						break;
					case 168:

					{
						return ops.unidadId();
					}
					case -164:
						break;
					case 169:

					{
						return ops.unidadId();
					}
					case -165:
						break;

					default:
						yy_error(YY_E_INTERNAL, false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
