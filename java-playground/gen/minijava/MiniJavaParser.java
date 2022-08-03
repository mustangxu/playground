// Generated from /Users/xujiajing/projects/playground/java-playground/src/main/resources/MiniJava.g4 by ANTLR 4.10.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MiniJavaParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.10.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, Identifier=36, NUMBER=37, WS=38;
	public static final int
		RULE_program = 0, RULE_mainClass = 1, RULE_classDeclaration = 2, RULE_varDeclaration = 3, 
		RULE_type = 4, RULE_methodDeclaration = 5, RULE_statement = 6, RULE_expression = 7;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "mainClass", "classDeclaration", "varDeclaration", "type", 
			"methodDeclaration", "statement", "expression"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'class'", "'{'", "'public'", "'static'", "'void'", "'main'", "'('", 
			"'String'", "'['", "']'", "')'", "'}'", "'extends'", "';'", "'int'", 
			"'boolean'", "','", "'return'", "'if'", "'else'", "'while'", "'System.out.println'", 
			"'='", "'&&'", "'<'", "'+'", "'-'", "'*'", "'.'", "'length'", "'new'", 
			"'!'", "'true'", "'false'", "'this'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			"Identifier", "NUMBER", "WS"
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

	@Override
	public String getGrammarFileName() { return "MiniJava.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MiniJavaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public MainClassContext mainClass() {
			return getRuleContext(MainClassContext.class,0);
		}
		public List<ClassDeclarationContext> classDeclaration() {
			return getRuleContexts(ClassDeclarationContext.class);
		}
		public ClassDeclarationContext classDeclaration(int i) {
			return getRuleContext(ClassDeclarationContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(16);
			mainClass();
			setState(20);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(17);
				classDeclaration();
				}
				}
				setState(22);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MainClassContext extends ParserRuleContext {
		public List<TerminalNode> Identifier() { return getTokens(MiniJavaParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(MiniJavaParser.Identifier, i);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public MainClassContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mainClass; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterMainClass(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitMainClass(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitMainClass(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MainClassContext mainClass() throws RecognitionException {
		MainClassContext _localctx = new MainClassContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_mainClass);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(23);
			match(T__0);
			setState(24);
			match(Identifier);
			setState(25);
			match(T__1);
			setState(26);
			match(T__2);
			setState(27);
			match(T__3);
			setState(28);
			match(T__4);
			setState(29);
			match(T__5);
			setState(30);
			match(T__6);
			setState(31);
			match(T__7);
			setState(32);
			match(T__8);
			setState(33);
			match(T__9);
			setState(34);
			match(Identifier);
			setState(35);
			match(T__10);
			setState(36);
			match(T__1);
			setState(37);
			statement();
			setState(38);
			match(T__11);
			setState(39);
			match(T__11);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassDeclarationContext extends ParserRuleContext {
		public List<TerminalNode> Identifier() { return getTokens(MiniJavaParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(MiniJavaParser.Identifier, i);
		}
		public List<VarDeclarationContext> varDeclaration() {
			return getRuleContexts(VarDeclarationContext.class);
		}
		public VarDeclarationContext varDeclaration(int i) {
			return getRuleContext(VarDeclarationContext.class,i);
		}
		public List<MethodDeclarationContext> methodDeclaration() {
			return getRuleContexts(MethodDeclarationContext.class);
		}
		public MethodDeclarationContext methodDeclaration(int i) {
			return getRuleContext(MethodDeclarationContext.class,i);
		}
		public ClassDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterClassDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitClassDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitClassDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassDeclarationContext classDeclaration() throws RecognitionException {
		ClassDeclarationContext _localctx = new ClassDeclarationContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_classDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41);
			match(T__0);
			setState(42);
			match(Identifier);
			setState(45);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__12) {
				{
				setState(43);
				match(T__12);
				setState(44);
				match(Identifier);
				}
			}

			setState(47);
			match(T__1);
			setState(51);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__14) | (1L << T__15) | (1L << Identifier))) != 0)) {
				{
				{
				setState(48);
				varDeclaration();
				}
				}
				setState(53);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(57);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(54);
				methodDeclaration();
				}
				}
				setState(59);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(60);
			match(T__11);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarDeclarationContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(MiniJavaParser.Identifier, 0); }
		public VarDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterVarDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitVarDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitVarDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDeclarationContext varDeclaration() throws RecognitionException {
		VarDeclarationContext _localctx = new VarDeclarationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_varDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			type();
			setState(63);
			match(Identifier);
			setState(64);
			match(T__13);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MiniJavaParser.Identifier, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_type);
		try {
			setState(72);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(66);
				match(T__14);
				setState(67);
				match(T__8);
				setState(68);
				match(T__9);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(69);
				match(T__15);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(70);
				match(T__14);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(71);
				match(Identifier);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodDeclarationContext extends ParserRuleContext {
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<TerminalNode> Identifier() { return getTokens(MiniJavaParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(MiniJavaParser.Identifier, i);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<VarDeclarationContext> varDeclaration() {
			return getRuleContexts(VarDeclarationContext.class);
		}
		public VarDeclarationContext varDeclaration(int i) {
			return getRuleContext(VarDeclarationContext.class,i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public MethodDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterMethodDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitMethodDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitMethodDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodDeclarationContext methodDeclaration() throws RecognitionException {
		MethodDeclarationContext _localctx = new MethodDeclarationContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_methodDeclaration);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			match(T__2);
			setState(75);
			type();
			setState(76);
			match(Identifier);
			setState(77);
			match(T__6);
			setState(89);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__14) | (1L << T__15) | (1L << Identifier))) != 0)) {
				{
				setState(78);
				type();
				setState(79);
				match(Identifier);
				setState(86);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__16) {
					{
					{
					setState(80);
					match(T__16);
					setState(81);
					type();
					setState(82);
					match(Identifier);
					}
					}
					setState(88);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(91);
			match(T__10);
			setState(92);
			match(T__1);
			setState(96);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(93);
					varDeclaration();
					}
					} 
				}
				setState(98);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			setState(102);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__18) | (1L << T__20) | (1L << T__21) | (1L << Identifier))) != 0)) {
				{
				{
				setState(99);
				statement();
				}
				}
				setState(104);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(105);
			match(T__17);
			setState(106);
			expression(0);
			setState(107);
			match(T__13);
			setState(108);
			match(T__11);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode Identifier() { return getToken(MiniJavaParser.Identifier, 0); }
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_statement);
		int _la;
		try {
			setState(152);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(110);
				match(T__1);
				setState(114);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__18) | (1L << T__20) | (1L << T__21) | (1L << Identifier))) != 0)) {
					{
					{
					setState(111);
					statement();
					}
					}
					setState(116);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(117);
				match(T__11);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(118);
				match(T__18);
				setState(119);
				match(T__6);
				setState(120);
				expression(0);
				setState(121);
				match(T__10);
				setState(122);
				statement();
				setState(123);
				match(T__19);
				setState(124);
				statement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(126);
				match(T__20);
				setState(127);
				match(T__6);
				setState(128);
				expression(0);
				setState(129);
				match(T__10);
				setState(130);
				statement();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(132);
				match(T__21);
				setState(133);
				match(T__6);
				setState(135);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__30) | (1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << Identifier) | (1L << NUMBER))) != 0)) {
					{
					setState(134);
					expression(0);
					}
				}

				setState(137);
				match(T__10);
				setState(138);
				match(T__13);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(139);
				match(Identifier);
				setState(140);
				match(T__22);
				setState(141);
				expression(0);
				setState(142);
				match(T__13);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(144);
				match(Identifier);
				setState(145);
				match(T__8);
				setState(146);
				expression(0);
				setState(147);
				match(T__9);
				setState(148);
				match(T__22);
				setState(149);
				expression(0);
				setState(150);
				match(T__13);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode Identifier() { return getToken(MiniJavaParser.Identifier, 0); }
		public TerminalNode NUMBER() { return getToken(MiniJavaParser.NUMBER, 0); }
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 14;
		enterRecursionRule(_localctx, 14, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(176);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				setState(155);
				match(T__30);
				setState(156);
				match(T__14);
				setState(157);
				match(T__8);
				setState(158);
				expression(0);
				setState(159);
				match(T__9);
				}
				break;
			case 2:
				{
				setState(161);
				match(T__30);
				setState(162);
				match(Identifier);
				setState(163);
				match(T__6);
				setState(164);
				match(T__10);
				}
				break;
			case 3:
				{
				setState(165);
				match(T__31);
				setState(166);
				expression(7);
				}
				break;
			case 4:
				{
				setState(167);
				match(T__6);
				setState(168);
				expression(0);
				setState(169);
				match(T__10);
				}
				break;
			case 5:
				{
				setState(171);
				match(NUMBER);
				}
				break;
			case 6:
				{
				setState(172);
				match(T__32);
				}
				break;
			case 7:
				{
				setState(173);
				match(T__33);
				}
				break;
			case 8:
				{
				setState(174);
				match(Identifier);
				}
				break;
			case 9:
				{
				setState(175);
				match(T__34);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(206);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(204);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(178);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(179);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(180);
						expression(14);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(181);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(182);
						match(T__8);
						setState(183);
						expression(0);
						setState(184);
						match(T__9);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(186);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(187);
						match(T__28);
						setState(188);
						match(T__29);
						}
						break;
					case 4:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(189);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(190);
						match(T__28);
						setState(191);
						match(Identifier);
						setState(192);
						match(T__6);
						setState(201);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__30) | (1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << Identifier) | (1L << NUMBER))) != 0)) {
							{
							setState(193);
							expression(0);
							setState(198);
							_errHandler.sync(this);
							_la = _input.LA(1);
							while (_la==T__16) {
								{
								{
								setState(194);
								match(T__16);
								setState(195);
								expression(0);
								}
								}
								setState(200);
								_errHandler.sync(this);
								_la = _input.LA(1);
							}
							}
						}

						setState(203);
						match(T__10);
						}
						break;
					}
					} 
				}
				setState(208);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 7:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 13);
		case 1:
			return precpred(_ctx, 12);
		case 2:
			return precpred(_ctx, 11);
		case 3:
			return precpred(_ctx, 10);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001&\u00d2\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0001"+
		"\u0000\u0001\u0000\u0005\u0000\u0013\b\u0000\n\u0000\f\u0000\u0016\t\u0000"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002.\b\u0002"+
		"\u0001\u0002\u0001\u0002\u0005\u00022\b\u0002\n\u0002\f\u00025\t\u0002"+
		"\u0001\u0002\u0005\u00028\b\u0002\n\u0002\f\u0002;\t\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004"+
		"I\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0005\u0005"+
		"U\b\u0005\n\u0005\f\u0005X\t\u0005\u0003\u0005Z\b\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0005\u0005_\b\u0005\n\u0005\f\u0005b\t\u0005\u0001"+
		"\u0005\u0005\u0005e\b\u0005\n\u0005\f\u0005h\t\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0005"+
		"\u0006q\b\u0006\n\u0006\f\u0006t\t\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006\u0088\b\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0003\u0006\u0099\b\u0006\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u00b1\b\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0005\u0007\u00c5"+
		"\b\u0007\n\u0007\f\u0007\u00c8\t\u0007\u0003\u0007\u00ca\b\u0007\u0001"+
		"\u0007\u0005\u0007\u00cd\b\u0007\n\u0007\f\u0007\u00d0\t\u0007\u0001\u0007"+
		"\u0000\u0001\u000e\b\u0000\u0002\u0004\u0006\b\n\f\u000e\u0000\u0001\u0001"+
		"\u0000\u0018\u001c\u00e9\u0000\u0010\u0001\u0000\u0000\u0000\u0002\u0017"+
		"\u0001\u0000\u0000\u0000\u0004)\u0001\u0000\u0000\u0000\u0006>\u0001\u0000"+
		"\u0000\u0000\bH\u0001\u0000\u0000\u0000\nJ\u0001\u0000\u0000\u0000\f\u0098"+
		"\u0001\u0000\u0000\u0000\u000e\u00b0\u0001\u0000\u0000\u0000\u0010\u0014"+
		"\u0003\u0002\u0001\u0000\u0011\u0013\u0003\u0004\u0002\u0000\u0012\u0011"+
		"\u0001\u0000\u0000\u0000\u0013\u0016\u0001\u0000\u0000\u0000\u0014\u0012"+
		"\u0001\u0000\u0000\u0000\u0014\u0015\u0001\u0000\u0000\u0000\u0015\u0001"+
		"\u0001\u0000\u0000\u0000\u0016\u0014\u0001\u0000\u0000\u0000\u0017\u0018"+
		"\u0005\u0001\u0000\u0000\u0018\u0019\u0005$\u0000\u0000\u0019\u001a\u0005"+
		"\u0002\u0000\u0000\u001a\u001b\u0005\u0003\u0000\u0000\u001b\u001c\u0005"+
		"\u0004\u0000\u0000\u001c\u001d\u0005\u0005\u0000\u0000\u001d\u001e\u0005"+
		"\u0006\u0000\u0000\u001e\u001f\u0005\u0007\u0000\u0000\u001f \u0005\b"+
		"\u0000\u0000 !\u0005\t\u0000\u0000!\"\u0005\n\u0000\u0000\"#\u0005$\u0000"+
		"\u0000#$\u0005\u000b\u0000\u0000$%\u0005\u0002\u0000\u0000%&\u0003\f\u0006"+
		"\u0000&\'\u0005\f\u0000\u0000\'(\u0005\f\u0000\u0000(\u0003\u0001\u0000"+
		"\u0000\u0000)*\u0005\u0001\u0000\u0000*-\u0005$\u0000\u0000+,\u0005\r"+
		"\u0000\u0000,.\u0005$\u0000\u0000-+\u0001\u0000\u0000\u0000-.\u0001\u0000"+
		"\u0000\u0000./\u0001\u0000\u0000\u0000/3\u0005\u0002\u0000\u000002\u0003"+
		"\u0006\u0003\u000010\u0001\u0000\u0000\u000025\u0001\u0000\u0000\u0000"+
		"31\u0001\u0000\u0000\u000034\u0001\u0000\u0000\u000049\u0001\u0000\u0000"+
		"\u000053\u0001\u0000\u0000\u000068\u0003\n\u0005\u000076\u0001\u0000\u0000"+
		"\u00008;\u0001\u0000\u0000\u000097\u0001\u0000\u0000\u00009:\u0001\u0000"+
		"\u0000\u0000:<\u0001\u0000\u0000\u0000;9\u0001\u0000\u0000\u0000<=\u0005"+
		"\f\u0000\u0000=\u0005\u0001\u0000\u0000\u0000>?\u0003\b\u0004\u0000?@"+
		"\u0005$\u0000\u0000@A\u0005\u000e\u0000\u0000A\u0007\u0001\u0000\u0000"+
		"\u0000BC\u0005\u000f\u0000\u0000CD\u0005\t\u0000\u0000DI\u0005\n\u0000"+
		"\u0000EI\u0005\u0010\u0000\u0000FI\u0005\u000f\u0000\u0000GI\u0005$\u0000"+
		"\u0000HB\u0001\u0000\u0000\u0000HE\u0001\u0000\u0000\u0000HF\u0001\u0000"+
		"\u0000\u0000HG\u0001\u0000\u0000\u0000I\t\u0001\u0000\u0000\u0000JK\u0005"+
		"\u0003\u0000\u0000KL\u0003\b\u0004\u0000LM\u0005$\u0000\u0000MY\u0005"+
		"\u0007\u0000\u0000NO\u0003\b\u0004\u0000OV\u0005$\u0000\u0000PQ\u0005"+
		"\u0011\u0000\u0000QR\u0003\b\u0004\u0000RS\u0005$\u0000\u0000SU\u0001"+
		"\u0000\u0000\u0000TP\u0001\u0000\u0000\u0000UX\u0001\u0000\u0000\u0000"+
		"VT\u0001\u0000\u0000\u0000VW\u0001\u0000\u0000\u0000WZ\u0001\u0000\u0000"+
		"\u0000XV\u0001\u0000\u0000\u0000YN\u0001\u0000\u0000\u0000YZ\u0001\u0000"+
		"\u0000\u0000Z[\u0001\u0000\u0000\u0000[\\\u0005\u000b\u0000\u0000\\`\u0005"+
		"\u0002\u0000\u0000]_\u0003\u0006\u0003\u0000^]\u0001\u0000\u0000\u0000"+
		"_b\u0001\u0000\u0000\u0000`^\u0001\u0000\u0000\u0000`a\u0001\u0000\u0000"+
		"\u0000af\u0001\u0000\u0000\u0000b`\u0001\u0000\u0000\u0000ce\u0003\f\u0006"+
		"\u0000dc\u0001\u0000\u0000\u0000eh\u0001\u0000\u0000\u0000fd\u0001\u0000"+
		"\u0000\u0000fg\u0001\u0000\u0000\u0000gi\u0001\u0000\u0000\u0000hf\u0001"+
		"\u0000\u0000\u0000ij\u0005\u0012\u0000\u0000jk\u0003\u000e\u0007\u0000"+
		"kl\u0005\u000e\u0000\u0000lm\u0005\f\u0000\u0000m\u000b\u0001\u0000\u0000"+
		"\u0000nr\u0005\u0002\u0000\u0000oq\u0003\f\u0006\u0000po\u0001\u0000\u0000"+
		"\u0000qt\u0001\u0000\u0000\u0000rp\u0001\u0000\u0000\u0000rs\u0001\u0000"+
		"\u0000\u0000su\u0001\u0000\u0000\u0000tr\u0001\u0000\u0000\u0000u\u0099"+
		"\u0005\f\u0000\u0000vw\u0005\u0013\u0000\u0000wx\u0005\u0007\u0000\u0000"+
		"xy\u0003\u000e\u0007\u0000yz\u0005\u000b\u0000\u0000z{\u0003\f\u0006\u0000"+
		"{|\u0005\u0014\u0000\u0000|}\u0003\f\u0006\u0000}\u0099\u0001\u0000\u0000"+
		"\u0000~\u007f\u0005\u0015\u0000\u0000\u007f\u0080\u0005\u0007\u0000\u0000"+
		"\u0080\u0081\u0003\u000e\u0007\u0000\u0081\u0082\u0005\u000b\u0000\u0000"+
		"\u0082\u0083\u0003\f\u0006\u0000\u0083\u0099\u0001\u0000\u0000\u0000\u0084"+
		"\u0085\u0005\u0016\u0000\u0000\u0085\u0087\u0005\u0007\u0000\u0000\u0086"+
		"\u0088\u0003\u000e\u0007\u0000\u0087\u0086\u0001\u0000\u0000\u0000\u0087"+
		"\u0088\u0001\u0000\u0000\u0000\u0088\u0089\u0001\u0000\u0000\u0000\u0089"+
		"\u008a\u0005\u000b\u0000\u0000\u008a\u0099\u0005\u000e\u0000\u0000\u008b"+
		"\u008c\u0005$\u0000\u0000\u008c\u008d\u0005\u0017\u0000\u0000\u008d\u008e"+
		"\u0003\u000e\u0007\u0000\u008e\u008f\u0005\u000e\u0000\u0000\u008f\u0099"+
		"\u0001\u0000\u0000\u0000\u0090\u0091\u0005$\u0000\u0000\u0091\u0092\u0005"+
		"\t\u0000\u0000\u0092\u0093\u0003\u000e\u0007\u0000\u0093\u0094\u0005\n"+
		"\u0000\u0000\u0094\u0095\u0005\u0017\u0000\u0000\u0095\u0096\u0003\u000e"+
		"\u0007\u0000\u0096\u0097\u0005\u000e\u0000\u0000\u0097\u0099\u0001\u0000"+
		"\u0000\u0000\u0098n\u0001\u0000\u0000\u0000\u0098v\u0001\u0000\u0000\u0000"+
		"\u0098~\u0001\u0000\u0000\u0000\u0098\u0084\u0001\u0000\u0000\u0000\u0098"+
		"\u008b\u0001\u0000\u0000\u0000\u0098\u0090\u0001\u0000\u0000\u0000\u0099"+
		"\r\u0001\u0000\u0000\u0000\u009a\u009b\u0006\u0007\uffff\uffff\u0000\u009b"+
		"\u009c\u0005\u001f\u0000\u0000\u009c\u009d\u0005\u000f\u0000\u0000\u009d"+
		"\u009e\u0005\t\u0000\u0000\u009e\u009f\u0003\u000e\u0007\u0000\u009f\u00a0"+
		"\u0005\n\u0000\u0000\u00a0\u00b1\u0001\u0000\u0000\u0000\u00a1\u00a2\u0005"+
		"\u001f\u0000\u0000\u00a2\u00a3\u0005$\u0000\u0000\u00a3\u00a4\u0005\u0007"+
		"\u0000\u0000\u00a4\u00b1\u0005\u000b\u0000\u0000\u00a5\u00a6\u0005 \u0000"+
		"\u0000\u00a6\u00b1\u0003\u000e\u0007\u0007\u00a7\u00a8\u0005\u0007\u0000"+
		"\u0000\u00a8\u00a9\u0003\u000e\u0007\u0000\u00a9\u00aa\u0005\u000b\u0000"+
		"\u0000\u00aa\u00b1\u0001\u0000\u0000\u0000\u00ab\u00b1\u0005%\u0000\u0000"+
		"\u00ac\u00b1\u0005!\u0000\u0000\u00ad\u00b1\u0005\"\u0000\u0000\u00ae"+
		"\u00b1\u0005$\u0000\u0000\u00af\u00b1\u0005#\u0000\u0000\u00b0\u009a\u0001"+
		"\u0000\u0000\u0000\u00b0\u00a1\u0001\u0000\u0000\u0000\u00b0\u00a5\u0001"+
		"\u0000\u0000\u0000\u00b0\u00a7\u0001\u0000\u0000\u0000\u00b0\u00ab\u0001"+
		"\u0000\u0000\u0000\u00b0\u00ac\u0001\u0000\u0000\u0000\u00b0\u00ad\u0001"+
		"\u0000\u0000\u0000\u00b0\u00ae\u0001\u0000\u0000\u0000\u00b0\u00af\u0001"+
		"\u0000\u0000\u0000\u00b1\u00ce\u0001\u0000\u0000\u0000\u00b2\u00b3\n\r"+
		"\u0000\u0000\u00b3\u00b4\u0007\u0000\u0000\u0000\u00b4\u00cd\u0003\u000e"+
		"\u0007\u000e\u00b5\u00b6\n\f\u0000\u0000\u00b6\u00b7\u0005\t\u0000\u0000"+
		"\u00b7\u00b8\u0003\u000e\u0007\u0000\u00b8\u00b9\u0005\n\u0000\u0000\u00b9"+
		"\u00cd\u0001\u0000\u0000\u0000\u00ba\u00bb\n\u000b\u0000\u0000\u00bb\u00bc"+
		"\u0005\u001d\u0000\u0000\u00bc\u00cd\u0005\u001e\u0000\u0000\u00bd\u00be"+
		"\n\n\u0000\u0000\u00be\u00bf\u0005\u001d\u0000\u0000\u00bf\u00c0\u0005"+
		"$\u0000\u0000\u00c0\u00c9\u0005\u0007\u0000\u0000\u00c1\u00c6\u0003\u000e"+
		"\u0007\u0000\u00c2\u00c3\u0005\u0011\u0000\u0000\u00c3\u00c5\u0003\u000e"+
		"\u0007\u0000\u00c4\u00c2\u0001\u0000\u0000\u0000\u00c5\u00c8\u0001\u0000"+
		"\u0000\u0000\u00c6\u00c4\u0001\u0000\u0000\u0000\u00c6\u00c7\u0001\u0000"+
		"\u0000\u0000\u00c7\u00ca\u0001\u0000\u0000\u0000\u00c8\u00c6\u0001\u0000"+
		"\u0000\u0000\u00c9\u00c1\u0001\u0000\u0000\u0000\u00c9\u00ca\u0001\u0000"+
		"\u0000\u0000\u00ca\u00cb\u0001\u0000\u0000\u0000\u00cb\u00cd\u0005\u000b"+
		"\u0000\u0000\u00cc\u00b2\u0001\u0000\u0000\u0000\u00cc\u00b5\u0001\u0000"+
		"\u0000\u0000\u00cc\u00ba\u0001\u0000\u0000\u0000\u00cc\u00bd\u0001\u0000"+
		"\u0000\u0000\u00cd\u00d0\u0001\u0000\u0000\u0000\u00ce\u00cc\u0001\u0000"+
		"\u0000\u0000\u00ce\u00cf\u0001\u0000\u0000\u0000\u00cf\u000f\u0001\u0000"+
		"\u0000\u0000\u00d0\u00ce\u0001\u0000\u0000\u0000\u0011\u0014-39HVY`fr"+
		"\u0087\u0098\u00b0\u00c6\u00c9\u00cc\u00ce";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}