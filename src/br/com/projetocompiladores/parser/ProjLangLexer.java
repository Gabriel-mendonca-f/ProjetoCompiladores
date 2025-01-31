// Generated from ProjLang.g4 by ANTLR 4.9.2
package br.com.projetocompiladores.parser;

	import br.com.projetocompiladores.datastructures.ProjSymbol;
	import br.com.projetocompiladores.datastructures.ProjVariable;
	import br.com.projetocompiladores.datastructures.ProjSymbolTable;
	import br.com.projetocompiladores.exceptions.ProjSemanticException;
	import br.com.projetocompiladores.ast.ProjProgram;
	import br.com.projetocompiladores.ast.AbstractCommand;
	import br.com.projetocompiladores.ast.CommandLeitura;
	import br.com.projetocompiladores.ast.CommandEscrita;
	import br.com.projetocompiladores.ast.CommandAtribuicao;
	import br.com.projetocompiladores.ast.CommandDecisao;
	import br.com.projetocompiladores.ast.CommandRepeticao;
	import br.com.projetocompiladores.ast.CommandRepeticaoFor;
	import br.com.projetocompiladores.ast.CommandFazerAte;
	import br.com.projetocompiladores.ast.CommandDecisaoTernario;
	import java.util.ArrayList;
	import java.util.Stack;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ProjLangLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, AP=14, FP=15, SC=16, OP=17, ATTR=18, 
		VIR=19, ACH=20, FCH=21, OPREL=22, ID=23, NUMBER=24, TEXT=25, WS=26, COMMENT=27, 
		LCOMMENT=28;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "AP", "FP", "SC", "OP", "ATTR", "VIR", 
			"ACH", "FCH", "OPREL", "ID", "NUMBER", "TEXT", "WS", "COMMENT", "LCOMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'programa'", "'fimprog;'", "'numero'", "'texto'", "'leia'", "'escreva'", 
			"'se'", "'senao'", "'?'", "':'", "'enquanto'", "'repetir'", "'fazer'", 
			"'('", "')'", "';'", null, "'='", "','", "'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, "AP", "FP", "SC", "OP", "ATTR", "VIR", "ACH", "FCH", "OPREL", 
			"ID", "NUMBER", "TEXT", "WS", "COMMENT", "LCOMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


		private int _tipo;
		private String _varName;
		private String _varValue;
		private ProjSymbolTable symbolTable = new ProjSymbolTable();
		private ProjSymbol symbol;
		private ProjProgram program = new ProjProgram();
		private ArrayList<AbstractCommand> curThread;
		private Stack<ArrayList<AbstractCommand>> stack = new Stack<ArrayList<AbstractCommand>>();
		private String _readID;
		private String _writeID;
		private String _exprID;
		private String _exprContent;
		private String _exprDecision;
		private String _left;
		private String _right;
		private String _actionID;
		private String _declID;
		private ArrayList<AbstractCommand> listaTrue;
		private ArrayList<AbstractCommand> listaFalse;
		private ArrayList<AbstractCommand> listaCmd;
		private ArrayList<String> exprTypeList = new ArrayList<String>();
		
		public void verificaID(String id) {
			if (!symbolTable.exists(id)){
				throw new ProjSemanticException("Symbol " + id + " not declared");
			}
		}
		
		public void exibeComandos(){
			for (AbstractCommand c: program.getComandos()){
				System.out.println("-- " + c);
			}
		}
		
		public void generateCode(){
			program.generateTarget();
		}
		
		public String getTypeByID(String id) {
			return symbolTable.getTypeByID(id);
		}
		
		public void checkType(String left, String id, String expression){
			for(String t : exprTypeList)  {
				if(left != t) {
					throw new ProjSemanticException("Incompatible types " + left + " and " + t + " in " + id + " = " + expression);
				}
			}
		}
		
		public String verifyAndGetType( String expression) {
			String t = exprTypeList.get(0);
			for (String tipo: exprTypeList) {
				if (tipo != t) {
					throw new ProjSemanticException("Incompatible types in expression: " + expression);
				}
			}
			return t;
		}
		
		public ArrayList<String> warnings() {
			ArrayList<String> l = new ArrayList<String>();
			for(ProjSymbol s: symbolTable.getNonUsed()) {
				l.add("Variável <" + s.getName() + "> declarada, mas nao usada");
			}
			return l;
		}
		
		public void exibeWarnings(){
			ArrayList<String> warnings = warnings();
			if(warnings.size() > 0) {
				System.out.println("*".repeat(45) + " WARNINGS " + "*".repeat(45));
				for(String w : warnings) {
					System.out.println("** " + w);
				}
				System.out.println("*".repeat(100) + "\n");
			}
		}


	public ProjLangLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "ProjLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 26:
			COMMENT_action((RuleContext)_localctx, actionIndex);
			break;
		case 27:
			LCOMMENT_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:

					System.out.println("Bloco de comentário encontrado, o trecho nao sera compilado\n");
					
			break;
		}
	}
	private void LCOMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1:

					System.out.println("Linha de comentário encontrada, ela nao sera compilado\n");
					
			break;
		}
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\36\u00e6\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23"+
		"\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\5\27\u00a5\n\27\3\30\3\30\7\30\u00a9\n\30\f\30\16\30\u00ac"+
		"\13\30\3\31\6\31\u00af\n\31\r\31\16\31\u00b0\3\31\3\31\6\31\u00b5\n\31"+
		"\r\31\16\31\u00b6\5\31\u00b9\n\31\3\32\3\32\3\32\3\32\7\32\u00bf\n\32"+
		"\f\32\16\32\u00c2\13\32\3\32\3\32\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3"+
		"\34\7\34\u00ce\n\34\f\34\16\34\u00d1\13\34\3\34\3\34\3\34\3\34\3\34\3"+
		"\34\3\34\3\35\3\35\3\35\3\35\7\35\u00de\n\35\f\35\16\35\u00e1\13\35\3"+
		"\35\3\35\3\35\3\35\4\u00c0\u00cf\2\36\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21"+
		"\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30"+
		"/\31\61\32\63\33\65\34\67\359\36\3\2\t\5\2,-//\61\61\4\2>>@@\3\2c|\5\2"+
		"\62;C\\c|\3\2\62;\5\2\13\f\17\17\"\"\4\2\f\f\17\17\2\u00f1\2\3\3\2\2\2"+
		"\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2"+
		"\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2"+
		"\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2"+
		"\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2"+
		"\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\3;\3\2\2\2\5D\3\2"+
		"\2\2\7M\3\2\2\2\tT\3\2\2\2\13Z\3\2\2\2\r_\3\2\2\2\17g\3\2\2\2\21j\3\2"+
		"\2\2\23p\3\2\2\2\25r\3\2\2\2\27t\3\2\2\2\31}\3\2\2\2\33\u0085\3\2\2\2"+
		"\35\u008b\3\2\2\2\37\u008d\3\2\2\2!\u008f\3\2\2\2#\u0091\3\2\2\2%\u0093"+
		"\3\2\2\2\'\u0095\3\2\2\2)\u0097\3\2\2\2+\u0099\3\2\2\2-\u00a4\3\2\2\2"+
		"/\u00a6\3\2\2\2\61\u00ae\3\2\2\2\63\u00ba\3\2\2\2\65\u00c5\3\2\2\2\67"+
		"\u00c9\3\2\2\29\u00d9\3\2\2\2;<\7r\2\2<=\7t\2\2=>\7q\2\2>?\7i\2\2?@\7"+
		"t\2\2@A\7c\2\2AB\7o\2\2BC\7c\2\2C\4\3\2\2\2DE\7h\2\2EF\7k\2\2FG\7o\2\2"+
		"GH\7r\2\2HI\7t\2\2IJ\7q\2\2JK\7i\2\2KL\7=\2\2L\6\3\2\2\2MN\7p\2\2NO\7"+
		"w\2\2OP\7o\2\2PQ\7g\2\2QR\7t\2\2RS\7q\2\2S\b\3\2\2\2TU\7v\2\2UV\7g\2\2"+
		"VW\7z\2\2WX\7v\2\2XY\7q\2\2Y\n\3\2\2\2Z[\7n\2\2[\\\7g\2\2\\]\7k\2\2]^"+
		"\7c\2\2^\f\3\2\2\2_`\7g\2\2`a\7u\2\2ab\7e\2\2bc\7t\2\2cd\7g\2\2de\7x\2"+
		"\2ef\7c\2\2f\16\3\2\2\2gh\7u\2\2hi\7g\2\2i\20\3\2\2\2jk\7u\2\2kl\7g\2"+
		"\2lm\7p\2\2mn\7c\2\2no\7q\2\2o\22\3\2\2\2pq\7A\2\2q\24\3\2\2\2rs\7<\2"+
		"\2s\26\3\2\2\2tu\7g\2\2uv\7p\2\2vw\7s\2\2wx\7w\2\2xy\7c\2\2yz\7p\2\2z"+
		"{\7v\2\2{|\7q\2\2|\30\3\2\2\2}~\7t\2\2~\177\7g\2\2\177\u0080\7r\2\2\u0080"+
		"\u0081\7g\2\2\u0081\u0082\7v\2\2\u0082\u0083\7k\2\2\u0083\u0084\7t\2\2"+
		"\u0084\32\3\2\2\2\u0085\u0086\7h\2\2\u0086\u0087\7c\2\2\u0087\u0088\7"+
		"|\2\2\u0088\u0089\7g\2\2\u0089\u008a\7t\2\2\u008a\34\3\2\2\2\u008b\u008c"+
		"\7*\2\2\u008c\36\3\2\2\2\u008d\u008e\7+\2\2\u008e \3\2\2\2\u008f\u0090"+
		"\7=\2\2\u0090\"\3\2\2\2\u0091\u0092\t\2\2\2\u0092$\3\2\2\2\u0093\u0094"+
		"\7?\2\2\u0094&\3\2\2\2\u0095\u0096\7.\2\2\u0096(\3\2\2\2\u0097\u0098\7"+
		"}\2\2\u0098*\3\2\2\2\u0099\u009a\7\177\2\2\u009a,\3\2\2\2\u009b\u00a5"+
		"\t\3\2\2\u009c\u009d\7@\2\2\u009d\u00a5\7?\2\2\u009e\u009f\7>\2\2\u009f"+
		"\u00a5\7?\2\2\u00a0\u00a1\7?\2\2\u00a1\u00a5\7?\2\2\u00a2\u00a3\7#\2\2"+
		"\u00a3\u00a5\7?\2\2\u00a4\u009b\3\2\2\2\u00a4\u009c\3\2\2\2\u00a4\u009e"+
		"\3\2\2\2\u00a4\u00a0\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a5.\3\2\2\2\u00a6"+
		"\u00aa\t\4\2\2\u00a7\u00a9\t\5\2\2\u00a8\u00a7\3\2\2\2\u00a9\u00ac\3\2"+
		"\2\2\u00aa\u00a8\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab\60\3\2\2\2\u00ac\u00aa"+
		"\3\2\2\2\u00ad\u00af\t\6\2\2\u00ae\u00ad\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0"+
		"\u00ae\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00b8\3\2\2\2\u00b2\u00b4\7\60"+
		"\2\2\u00b3\u00b5\t\6\2\2\u00b4\u00b3\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6"+
		"\u00b4\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00b9\3\2\2\2\u00b8\u00b2\3\2"+
		"\2\2\u00b8\u00b9\3\2\2\2\u00b9\62\3\2\2\2\u00ba\u00c0\7$\2\2\u00bb\u00bc"+
		"\7^\2\2\u00bc\u00bf\7$\2\2\u00bd\u00bf\13\2\2\2\u00be\u00bb\3\2\2\2\u00be"+
		"\u00bd\3\2\2\2\u00bf\u00c2\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c0\u00be\3\2"+
		"\2\2\u00c1\u00c3\3\2\2\2\u00c2\u00c0\3\2\2\2\u00c3\u00c4\7$\2\2\u00c4"+
		"\64\3\2\2\2\u00c5\u00c6\t\7\2\2\u00c6\u00c7\3\2\2\2\u00c7\u00c8\b\33\2"+
		"\2\u00c8\66\3\2\2\2\u00c9\u00ca\7\61\2\2\u00ca\u00cb\7,\2\2\u00cb\u00cf"+
		"\3\2\2\2\u00cc\u00ce\13\2\2\2\u00cd\u00cc\3\2\2\2\u00ce\u00d1\3\2\2\2"+
		"\u00cf\u00d0\3\2\2\2\u00cf\u00cd\3\2\2\2\u00d0\u00d2\3\2\2\2\u00d1\u00cf"+
		"\3\2\2\2\u00d2\u00d3\7,\2\2\u00d3\u00d4\7\61\2\2\u00d4\u00d5\3\2\2\2\u00d5"+
		"\u00d6\b\34\3\2\u00d6\u00d7\3\2\2\2\u00d7\u00d8\b\34\2\2\u00d88\3\2\2"+
		"\2\u00d9\u00da\7\61\2\2\u00da\u00db\7\61\2\2\u00db\u00df\3\2\2\2\u00dc"+
		"\u00de\n\b\2\2\u00dd\u00dc\3\2\2\2\u00de\u00e1\3\2\2\2\u00df\u00dd\3\2"+
		"\2\2\u00df\u00e0\3\2\2\2\u00e0\u00e2\3\2\2\2\u00e1\u00df\3\2\2\2\u00e2"+
		"\u00e3\b\35\4\2\u00e3\u00e4\3\2\2\2\u00e4\u00e5\b\35\2\2\u00e5:\3\2\2"+
		"\2\r\2\u00a4\u00a8\u00aa\u00b0\u00b6\u00b8\u00be\u00c0\u00cf\u00df\5\b"+
		"\2\2\3\34\2\3\35\3";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}