package com.jayxu.playground.antlr.minijava;
// Generated from by ANTLR 4.10.1

import java.util.List;

import org.antlr.v4.runtime.FailedPredicateException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.RuntimeMetaData;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.VocabularyImpl;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

@SuppressWarnings({ "all", "warnings", "unchecked", "unused", "cast" })
public class MiniJavaParser extends Parser {
    static {
        RuntimeMetaData.checkVersion("4.10.1", RuntimeMetaData.VERSION);
    }

    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache = new PredictionContextCache();
    public static final int T__0 = 1, T__1 = 2, T__2 = 3, T__3 = 4, T__4 = 5,
            T__5 = 6, T__6 = 7, T__7 = 8, T__8 = 9,
            T__9 = 10, T__10 = 11, T__11 = 12, T__12 = 13, T__13 = 14,
            T__14 = 15, T__15 = 16, T__16 = 17,
            T__17 = 18, T__18 = 19, T__19 = 20, T__20 = 21, T__21 = 22,
            T__22 = 23, T__23 = 24,
            T__24 = 25, T__25 = 26, T__26 = 27, T__27 = 28, T__28 = 29,
            T__29 = 30, T__30 = 31,
            T__31 = 32, T__32 = 33, T__33 = 34, T__34 = 35, Identifier = 36,
            NUMBER = 37, WS = 38;
    public static final int RULE_program = 0, RULE_mainClass = 1,
            RULE_classDeclaration = 2, RULE_varDeclaration = 3,
            RULE_type = 4, RULE_methodDeclaration = 5, RULE_statement = 6,
            RULE_expression = 7;

    private static String[] makeRuleNames() {
        return new String[] {
            "program", "mainClass", "classDeclaration", "varDeclaration",
            "type",
            "methodDeclaration", "statement", "expression"
        };
    }

    public static final String[] ruleNames = makeRuleNames();

    private static String[] makeLiteralNames() {
        return new String[] {
            null, "'public'", "'class'", "'{'", "'static'", "'void'", "'main'",
            "'('",
            "'String'", "'['", "']'", "')'", "'}'", "'extends'", "';'", "'int'",
            "'boolean'", "','", "'return'", "'if'", "'else'", "'while'",
            "'System.out.println'",
            "'='", "'&&'", "'<'", "'+'", "'-'", "'*'", "'.'", "'length'",
            "'new'",
            "'!'", "'true'", "'false'", "'this'"
        };
    }

    private static final String[] _LITERAL_NAMES = makeLiteralNames();

    private static String[] makeSymbolicNames() {
        return new String[] {
            null, null, null, null, null, null, null, null, null, null, null,
            null,
            null, null, null, null, null, null, null, null, null, null, null,
            null,
            null, null, null, null, null, null, null, null, null, null, null,
            null,
            "Identifier", "NUMBER", "WS"
        };
    }

    private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
    public static final Vocabulary VOCABULARY = new VocabularyImpl(
        _LITERAL_NAMES, _SYMBOLIC_NAMES);

    /**
     * @deprecated Use {@link #VOCABULARY} instead.
     */
    @Deprecated
    public static final String[] tokenNames;
    static {
        tokenNames = new String[_SYMBOLIC_NAMES.length];
        for (var i = 0; i < tokenNames.length; i++) {
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
    public String getGrammarFileName() {
        return "MiniJava.g4";
    }

    @Override
    public String[] getRuleNames() {
        return ruleNames;
    }

    @Override
    public String getSerializedATN() {
        return _serializedATN;
    }

    @Override
    public ATN getATN() {
        return _ATN;
    }

    public MiniJavaParser(TokenStream input) {
        super(input);
        this._interp = new ParserATNSimulator(this, _ATN, _decisionToDFA,
            _sharedContextCache);
    }

    public static class ProgramContext extends ParserRuleContext {
        public MainClassContext mainClass() {
            return this.getRuleContext(MainClassContext.class, 0);
        }

        public List<ClassDeclarationContext> classDeclaration() {
            return this.getRuleContexts(ClassDeclarationContext.class);
        }

        public ClassDeclarationContext classDeclaration(int i) {
            return this.getRuleContext(ClassDeclarationContext.class, i);
        }

        public ProgramContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_program;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof MiniJavaListener) {
                ((MiniJavaListener) listener).enterProgram(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MiniJavaListener) {
                ((MiniJavaListener) listener).exitProgram(this);
            }
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof MiniJavaVisitor) {
                return ((MiniJavaVisitor<? extends T>) visitor)
                    .visitProgram(this);
            }
            return visitor.visitChildren(this);
        }
    }

    public final ProgramContext program() throws RecognitionException {
        var _localctx = new ProgramContext(this._ctx, this.getState());
        this.enterRule(_localctx, 0, RULE_program);
        int _la;
        try {
            this.enterOuterAlt(_localctx, 1);
            {
                this.setState(16);
                this.mainClass();
                this.setState(20);
                this._errHandler.sync(this);
                _la = this._input.LA(1);
                while (_la == T__0 || _la == T__1) {
                    {
                        {
                            this.setState(17);
                            this.classDeclaration();
                        }
                    }
                    this.setState(22);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            this._errHandler.reportError(this, re);
            this._errHandler.recover(this, re);
        } finally {
            this.exitRule();
        }
        return _localctx;
    }

    public static class MainClassContext extends ParserRuleContext {
        public List<TerminalNode> Identifier() {
            return this.getTokens(MiniJavaParser.Identifier);
        }

        public TerminalNode Identifier(int i) {
            return this.getToken(MiniJavaParser.Identifier, i);
        }

        public StatementContext statement() {
            return this.getRuleContext(StatementContext.class, 0);
        }

        public MainClassContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_mainClass;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof MiniJavaListener) {
                ((MiniJavaListener) listener).enterMainClass(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MiniJavaListener) {
                ((MiniJavaListener) listener).exitMainClass(this);
            }
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof MiniJavaVisitor) {
                return ((MiniJavaVisitor<? extends T>) visitor)
                    .visitMainClass(this);
            }
            return visitor.visitChildren(this);
        }
    }

    public final MainClassContext mainClass() throws RecognitionException {
        var _localctx = new MainClassContext(this._ctx, this.getState());
        this.enterRule(_localctx, 2, RULE_mainClass);
        int _la;
        try {
            this.enterOuterAlt(_localctx, 1);
            {
                this.setState(24);
                this._errHandler.sync(this);
                _la = this._input.LA(1);
                if (_la == T__0) {
                    {
                        this.setState(23);
                        this.match(T__0);
                    }
                }

                this.setState(26);
                this.match(T__1);
                this.setState(27);
                this.match(Identifier);
                this.setState(28);
                this.match(T__2);
                this.setState(29);
                this.match(T__0);
                this.setState(30);
                this.match(T__3);
                this.setState(31);
                this.match(T__4);
                this.setState(32);
                this.match(T__5);
                this.setState(33);
                this.match(T__6);
                this.setState(34);
                this.match(T__7);
                this.setState(35);
                this.match(T__8);
                this.setState(36);
                this.match(T__9);
                this.setState(37);
                this.match(Identifier);
                this.setState(38);
                this.match(T__10);
                this.setState(39);
                this.match(T__2);
                this.setState(40);
                this.statement();
                this.setState(41);
                this.match(T__11);
                this.setState(42);
                this.match(T__11);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            this._errHandler.reportError(this, re);
            this._errHandler.recover(this, re);
        } finally {
            this.exitRule();
        }
        return _localctx;
    }

    public static class ClassDeclarationContext extends ParserRuleContext {
        public List<TerminalNode> Identifier() {
            return this.getTokens(MiniJavaParser.Identifier);
        }

        public TerminalNode Identifier(int i) {
            return this.getToken(MiniJavaParser.Identifier, i);
        }

        public List<VarDeclarationContext> varDeclaration() {
            return this.getRuleContexts(VarDeclarationContext.class);
        }

        public VarDeclarationContext varDeclaration(int i) {
            return this.getRuleContext(VarDeclarationContext.class, i);
        }

        public List<MethodDeclarationContext> methodDeclaration() {
            return this.getRuleContexts(MethodDeclarationContext.class);
        }

        public MethodDeclarationContext methodDeclaration(int i) {
            return this.getRuleContext(MethodDeclarationContext.class, i);
        }

        public ClassDeclarationContext(ParserRuleContext parent,
                int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_classDeclaration;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof MiniJavaListener) {
                ((MiniJavaListener) listener).enterClassDeclaration(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MiniJavaListener) {
                ((MiniJavaListener) listener).exitClassDeclaration(this);
            }
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof MiniJavaVisitor) {
                return ((MiniJavaVisitor<? extends T>) visitor)
                    .visitClassDeclaration(this);
            }
            return visitor.visitChildren(this);
        }
    }

    public final ClassDeclarationContext classDeclaration()
            throws RecognitionException {
        var _localctx = new ClassDeclarationContext(this._ctx, this.getState());
        this.enterRule(_localctx, 4, RULE_classDeclaration);
        int _la;
        try {
            this.enterOuterAlt(_localctx, 1);
            {
                this.setState(45);
                this._errHandler.sync(this);
                _la = this._input.LA(1);
                if (_la == T__0) {
                    {
                        this.setState(44);
                        this.match(T__0);
                    }
                }

                this.setState(47);
                this.match(T__1);
                this.setState(48);
                this.match(Identifier);
                this.setState(51);
                this._errHandler.sync(this);
                _la = this._input.LA(1);
                if (_la == T__12) {
                    {
                        this.setState(49);
                        this.match(T__12);
                        this.setState(50);
                        this.match(Identifier);
                    }
                }

                this.setState(53);
                this.match(T__2);
                this.setState(57);
                this._errHandler.sync(this);
                _la = this._input.LA(1);
                while ((_la & ~0x3f) == 0 && (1L << _la
                    & (1L << T__14 | 1L << T__15 | 1L << Identifier)) != 0) {
                    {
                        {
                            this.setState(54);
                            this.varDeclaration();
                        }
                    }
                    this.setState(59);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
                }
                this.setState(63);
                this._errHandler.sync(this);
                _la = this._input.LA(1);
                while (_la == T__0) {
                    {
                        {
                            this.setState(60);
                            this.methodDeclaration();
                        }
                    }
                    this.setState(65);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
                }
                this.setState(66);
                this.match(T__11);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            this._errHandler.reportError(this, re);
            this._errHandler.recover(this, re);
        } finally {
            this.exitRule();
        }
        return _localctx;
    }

    public static class VarDeclarationContext extends ParserRuleContext {
        public TypeContext type() {
            return this.getRuleContext(TypeContext.class, 0);
        }

        public TerminalNode Identifier() {
            return this.getToken(MiniJavaParser.Identifier, 0);
        }

        public VarDeclarationContext(ParserRuleContext parent,
                int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_varDeclaration;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof MiniJavaListener) {
                ((MiniJavaListener) listener).enterVarDeclaration(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MiniJavaListener) {
                ((MiniJavaListener) listener).exitVarDeclaration(this);
            }
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof MiniJavaVisitor) {
                return ((MiniJavaVisitor<? extends T>) visitor)
                    .visitVarDeclaration(this);
            }
            return visitor.visitChildren(this);
        }
    }

    public final VarDeclarationContext varDeclaration()
            throws RecognitionException {
        var _localctx = new VarDeclarationContext(this._ctx, this.getState());
        this.enterRule(_localctx, 6, RULE_varDeclaration);
        try {
            this.enterOuterAlt(_localctx, 1);
            {
                this.setState(68);
                this.type();
                this.setState(69);
                this.match(Identifier);
                this.setState(70);
                this.match(T__13);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            this._errHandler.reportError(this, re);
            this._errHandler.recover(this, re);
        } finally {
            this.exitRule();
        }
        return _localctx;
    }

    public static class TypeContext extends ParserRuleContext {
        public TerminalNode Identifier() {
            return this.getToken(MiniJavaParser.Identifier, 0);
        }

        public TypeContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_type;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof MiniJavaListener) {
                ((MiniJavaListener) listener).enterType(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MiniJavaListener) {
                ((MiniJavaListener) listener).exitType(this);
            }
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof MiniJavaVisitor) {
                return ((MiniJavaVisitor<? extends T>) visitor).visitType(this);
            }
            return visitor.visitChildren(this);
        }
    }

    public final TypeContext type() throws RecognitionException {
        var _localctx = new TypeContext(this._ctx, this.getState());
        this.enterRule(_localctx, 8, RULE_type);
        try {
            this.setState(78);
            this._errHandler.sync(this);
            switch (this.getInterpreter().adaptivePredict(this._input, 6,
                this._ctx)) {
                case 1:
                    this.enterOuterAlt(_localctx, 1); {
                    this.setState(72);
                    this.match(T__14);
                    this.setState(73);
                    this.match(T__8);
                    this.setState(74);
                    this.match(T__9);
                }
                    break;
                case 2:
                    this.enterOuterAlt(_localctx, 2); {
                    this.setState(75);
                    this.match(T__15);
                }
                    break;
                case 3:
                    this.enterOuterAlt(_localctx, 3); {
                    this.setState(76);
                    this.match(T__14);
                }
                    break;
                case 4:
                    this.enterOuterAlt(_localctx, 4); {
                    this.setState(77);
                    this.match(Identifier);
                }
                    break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            this._errHandler.reportError(this, re);
            this._errHandler.recover(this, re);
        } finally {
            this.exitRule();
        }
        return _localctx;
    }

    public static class MethodDeclarationContext extends ParserRuleContext {
        public List<TypeContext> type() {
            return this.getRuleContexts(TypeContext.class);
        }

        public TypeContext type(int i) {
            return this.getRuleContext(TypeContext.class, i);
        }

        public List<TerminalNode> Identifier() {
            return this.getTokens(MiniJavaParser.Identifier);
        }

        public TerminalNode Identifier(int i) {
            return this.getToken(MiniJavaParser.Identifier, i);
        }

        public ExpressionContext expression() {
            return this.getRuleContext(ExpressionContext.class, 0);
        }

        public List<VarDeclarationContext> varDeclaration() {
            return this.getRuleContexts(VarDeclarationContext.class);
        }

        public VarDeclarationContext varDeclaration(int i) {
            return this.getRuleContext(VarDeclarationContext.class, i);
        }

        public List<StatementContext> statement() {
            return this.getRuleContexts(StatementContext.class);
        }

        public StatementContext statement(int i) {
            return this.getRuleContext(StatementContext.class, i);
        }

        public MethodDeclarationContext(ParserRuleContext parent,
                int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_methodDeclaration;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof MiniJavaListener) {
                ((MiniJavaListener) listener).enterMethodDeclaration(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MiniJavaListener) {
                ((MiniJavaListener) listener).exitMethodDeclaration(this);
            }
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof MiniJavaVisitor) {
                return ((MiniJavaVisitor<? extends T>) visitor)
                    .visitMethodDeclaration(this);
            }
            return visitor.visitChildren(this);
        }
    }

    public final MethodDeclarationContext methodDeclaration()
            throws RecognitionException {
        var _localctx = new MethodDeclarationContext(this._ctx,
            this.getState());
        this.enterRule(_localctx, 10, RULE_methodDeclaration);
        int _la;
        try {
            int _alt;
            this.enterOuterAlt(_localctx, 1);
            {
                this.setState(80);
                this.match(T__0);
                this.setState(81);
                this.type();
                this.setState(82);
                this.match(Identifier);
                this.setState(83);
                this.match(T__6);
                this.setState(95);
                this._errHandler.sync(this);
                _la = this._input.LA(1);
                if ((_la & ~0x3f) == 0 && (1L << _la
                    & (1L << T__14 | 1L << T__15 | 1L << Identifier)) != 0) {
                    {
                        this.setState(84);
                        this.type();
                        this.setState(85);
                        this.match(Identifier);
                        this.setState(92);
                        this._errHandler.sync(this);
                        _la = this._input.LA(1);
                        while (_la == T__16) {
                            {
                                {
                                    this.setState(86);
                                    this.match(T__16);
                                    this.setState(87);
                                    this.type();
                                    this.setState(88);
                                    this.match(Identifier);
                                }
                            }
                            this.setState(94);
                            this._errHandler.sync(this);
                            _la = this._input.LA(1);
                        }
                    }
                }

                this.setState(97);
                this.match(T__10);
                this.setState(98);
                this.match(T__2);
                this.setState(102);
                this._errHandler.sync(this);
                _alt = this.getInterpreter().adaptivePredict(this._input, 9,
                    this._ctx);
                while (_alt != 2 && _alt != ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        {
                            {
                                this.setState(99);
                                this.varDeclaration();
                            }
                        }
                    }
                    this.setState(104);
                    this._errHandler.sync(this);
                    _alt = this.getInterpreter().adaptivePredict(this._input, 9,
                        this._ctx);
                }
                this.setState(108);
                this._errHandler.sync(this);
                _la = this._input.LA(1);
                while ((_la & ~0x3f) == 0
                    && (1L << _la & (1L << T__2 | 1L << T__18 | 1L << T__20
                        | 1L << T__21 | 1L << Identifier)) != 0) {
                    {
                        {
                            this.setState(105);
                            this.statement();
                        }
                    }
                    this.setState(110);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
                }
                this.setState(111);
                this.match(T__17);
                this.setState(112);
                this.expression(0);
                this.setState(113);
                this.match(T__13);
                this.setState(114);
                this.match(T__11);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            this._errHandler.reportError(this, re);
            this._errHandler.recover(this, re);
        } finally {
            this.exitRule();
        }
        return _localctx;
    }

    public static class StatementContext extends ParserRuleContext {
        public List<StatementContext> statement() {
            return this.getRuleContexts(StatementContext.class);
        }

        public StatementContext statement(int i) {
            return this.getRuleContext(StatementContext.class, i);
        }

        public List<ExpressionContext> expression() {
            return this.getRuleContexts(ExpressionContext.class);
        }

        public ExpressionContext expression(int i) {
            return this.getRuleContext(ExpressionContext.class, i);
        }

        public TerminalNode Identifier() {
            return this.getToken(MiniJavaParser.Identifier, 0);
        }

        public StatementContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_statement;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof MiniJavaListener) {
                ((MiniJavaListener) listener).enterStatement(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MiniJavaListener) {
                ((MiniJavaListener) listener).exitStatement(this);
            }
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof MiniJavaVisitor) {
                return ((MiniJavaVisitor<? extends T>) visitor)
                    .visitStatement(this);
            }
            return visitor.visitChildren(this);
        }
    }

    public final StatementContext statement() throws RecognitionException {
        var _localctx = new StatementContext(this._ctx, this.getState());
        this.enterRule(_localctx, 12, RULE_statement);
        int _la;
        try {
            this.setState(158);
            this._errHandler.sync(this);
            switch (this.getInterpreter().adaptivePredict(this._input, 13,
                this._ctx)) {
                case 1:
                    this.enterOuterAlt(_localctx, 1); {
                    this.setState(116);
                    this.match(T__2);
                    this.setState(120);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
                    while ((_la & ~0x3f) == 0
                        && (1L << _la & (1L << T__2 | 1L << T__18 | 1L << T__20
                            | 1L << T__21 | 1L << Identifier)) != 0) {
                        {
                            {
                                this.setState(117);
                                this.statement();
                            }
                        }
                        this.setState(122);
                        this._errHandler.sync(this);
                        _la = this._input.LA(1);
                    }
                    this.setState(123);
                    this.match(T__11);
                }
                    break;
                case 2:
                    this.enterOuterAlt(_localctx, 2); {
                    this.setState(124);
                    this.match(T__18);
                    this.setState(125);
                    this.match(T__6);
                    this.setState(126);
                    this.expression(0);
                    this.setState(127);
                    this.match(T__10);
                    this.setState(128);
                    this.statement();
                    this.setState(129);
                    this.match(T__19);
                    this.setState(130);
                    this.statement();
                }
                    break;
                case 3:
                    this.enterOuterAlt(_localctx, 3); {
                    this.setState(132);
                    this.match(T__20);
                    this.setState(133);
                    this.match(T__6);
                    this.setState(134);
                    this.expression(0);
                    this.setState(135);
                    this.match(T__10);
                    this.setState(136);
                    this.statement();
                }
                    break;
                case 4:
                    this.enterOuterAlt(_localctx, 4); {
                    this.setState(138);
                    this.match(T__21);
                    this.setState(139);
                    this.match(T__6);
                    this.setState(141);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
                    if ((_la & ~0x3f) == 0
                        && (1L << _la & (1L << T__6 | 1L << T__30 | 1L << T__31
                            | 1L << T__32 | 1L << T__33 | 1L << T__34
                            | 1L << Identifier | 1L << NUMBER)) != 0) {
                        {
                            this.setState(140);
                            this.expression(0);
                        }
                    }

                    this.setState(143);
                    this.match(T__10);
                    this.setState(144);
                    this.match(T__13);
                }
                    break;
                case 5:
                    this.enterOuterAlt(_localctx, 5); {
                    this.setState(145);
                    this.match(Identifier);
                    this.setState(146);
                    this.match(T__22);
                    this.setState(147);
                    this.expression(0);
                    this.setState(148);
                    this.match(T__13);
                }
                    break;
                case 6:
                    this.enterOuterAlt(_localctx, 6); {
                    this.setState(150);
                    this.match(Identifier);
                    this.setState(151);
                    this.match(T__8);
                    this.setState(152);
                    this.expression(0);
                    this.setState(153);
                    this.match(T__9);
                    this.setState(154);
                    this.match(T__22);
                    this.setState(155);
                    this.expression(0);
                    this.setState(156);
                    this.match(T__13);
                }
                    break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            this._errHandler.reportError(this, re);
            this._errHandler.recover(this, re);
        } finally {
            this.exitRule();
        }
        return _localctx;
    }

    public static class ExpressionContext extends ParserRuleContext {
        public List<ExpressionContext> expression() {
            return this.getRuleContexts(ExpressionContext.class);
        }

        public ExpressionContext expression(int i) {
            return this.getRuleContext(ExpressionContext.class, i);
        }

        public TerminalNode Identifier() {
            return this.getToken(MiniJavaParser.Identifier, 0);
        }

        public TerminalNode NUMBER() {
            return this.getToken(MiniJavaParser.NUMBER, 0);
        }

        public ExpressionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_expression;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof MiniJavaListener) {
                ((MiniJavaListener) listener).enterExpression(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MiniJavaListener) {
                ((MiniJavaListener) listener).exitExpression(this);
            }
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof MiniJavaVisitor) {
                return ((MiniJavaVisitor<? extends T>) visitor)
                    .visitExpression(this);
            }
            return visitor.visitChildren(this);
        }
    }

    public final ExpressionContext expression() throws RecognitionException {
        return this.expression(0);
    }

    private ExpressionContext expression(int _p) throws RecognitionException {
        var _parentctx = this._ctx;
        var _parentState = this.getState();
        var _localctx = new ExpressionContext(this._ctx, _parentState);
        var _prevctx = _localctx;
        var _startState = 14;
        this.enterRecursionRule(_localctx, 14, RULE_expression, _p);
        int _la;
        try {
            int _alt;
            this.enterOuterAlt(_localctx, 1);
            {
                this.setState(182);
                this._errHandler.sync(this);
                switch (this.getInterpreter().adaptivePredict(this._input, 14,
                    this._ctx)) {
                    case 1: {
                        this.setState(161);
                        this.match(T__30);
                        this.setState(162);
                        this.match(T__14);
                        this.setState(163);
                        this.match(T__8);
                        this.setState(164);
                        this.expression(0);
                        this.setState(165);
                        this.match(T__9);
                    }
                        break;
                    case 2: {
                        this.setState(167);
                        this.match(T__30);
                        this.setState(168);
                        this.match(Identifier);
                        this.setState(169);
                        this.match(T__6);
                        this.setState(170);
                        this.match(T__10);
                    }
                        break;
                    case 3: {
                        this.setState(171);
                        this.match(T__31);
                        this.setState(172);
                        this.expression(7);
                    }
                        break;
                    case 4: {
                        this.setState(173);
                        this.match(T__6);
                        this.setState(174);
                        this.expression(0);
                        this.setState(175);
                        this.match(T__10);
                    }
                        break;
                    case 5: {
                        this.setState(177);
                        this.match(NUMBER);
                    }
                        break;
                    case 6: {
                        this.setState(178);
                        this.match(T__32);
                    }
                        break;
                    case 7: {
                        this.setState(179);
                        this.match(T__33);
                    }
                        break;
                    case 8: {
                        this.setState(180);
                        this.match(Identifier);
                    }
                        break;
                    case 9: {
                        this.setState(181);
                        this.match(T__34);
                    }
                        break;
                }
                this._ctx.stop = this._input.LT(-1);
                this.setState(212);
                this._errHandler.sync(this);
                _alt = this.getInterpreter().adaptivePredict(this._input, 18,
                    this._ctx);
                while (_alt != 2 && _alt != ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        if (this._parseListeners != null) {
                            this.triggerExitRuleEvent();
                        }
                        _prevctx = _localctx;
                        {
                            this.setState(210);
                            this._errHandler.sync(this);
                            switch (this.getInterpreter()
                                .adaptivePredict(this._input, 17, this._ctx)) {
                                case 1: {
                                    _localctx = new ExpressionContext(
                                        _parentctx, _parentState);
                                    this.pushNewRecursionContext(_localctx,
                                        _startState, RULE_expression);
                                    this.setState(184);
                                    if (!this.precpred(this._ctx, 13)) {
                                        throw new FailedPredicateException(this,
                                            "precpred(_ctx, 13)");
                                    }
                                    this.setState(185);
                                    _la = this._input.LA(1);
                                    if ((_la & ~0x3f) != 0 || (1L << _la
                                        & (1L << T__23 | 1L << T__24
                                            | 1L << T__25 | 1L << T__26
                                            | 1L << T__27)) == 0) {
                                        this._errHandler.recoverInline(this);
                                    } else {
                                        if (this._input.LA(1) == Token.EOF) {
                                            this.matchedEOF = true;
                                        }
                                        this._errHandler.reportMatch(this);
                                        this.consume();
                                    }
                                    this.setState(186);
                                    this.expression(14);
                                }
                                    break;
                                case 2: {
                                    _localctx = new ExpressionContext(
                                        _parentctx, _parentState);
                                    this.pushNewRecursionContext(_localctx,
                                        _startState, RULE_expression);
                                    this.setState(187);
                                    if (!this.precpred(this._ctx, 12)) {
                                        throw new FailedPredicateException(this,
                                            "precpred(_ctx, 12)");
                                    }
                                    this.setState(188);
                                    this.match(T__8);
                                    this.setState(189);
                                    this.expression(0);
                                    this.setState(190);
                                    this.match(T__9);
                                }
                                    break;
                                case 3: {
                                    _localctx = new ExpressionContext(
                                        _parentctx, _parentState);
                                    this.pushNewRecursionContext(_localctx,
                                        _startState, RULE_expression);
                                    this.setState(192);
                                    if (!this.precpred(this._ctx, 11)) {
                                        throw new FailedPredicateException(this,
                                            "precpred(_ctx, 11)");
                                    }
                                    this.setState(193);
                                    this.match(T__28);
                                    this.setState(194);
                                    this.match(T__29);
                                }
                                    break;
                                case 4: {
                                    _localctx = new ExpressionContext(
                                        _parentctx, _parentState);
                                    this.pushNewRecursionContext(_localctx,
                                        _startState, RULE_expression);
                                    this.setState(195);
                                    if (!this.precpred(this._ctx, 10)) {
                                        throw new FailedPredicateException(this,
                                            "precpred(_ctx, 10)");
                                    }
                                    this.setState(196);
                                    this.match(T__28);
                                    this.setState(197);
                                    this.match(Identifier);
                                    this.setState(198);
                                    this.match(T__6);
                                    this.setState(207);
                                    this._errHandler.sync(this);
                                    _la = this._input.LA(1);
                                    if ((_la & ~0x3f) == 0
                                        && (1L << _la & (1L << T__6
                                            | 1L << T__30 | 1L << T__31
                                            | 1L << T__32 | 1L << T__33
                                            | 1L << T__34 | 1L << Identifier
                                            | 1L << NUMBER)) != 0) {
                                        {
                                            this.setState(199);
                                            this.expression(0);
                                            this.setState(204);
                                            this._errHandler.sync(this);
                                            _la = this._input.LA(1);
                                            while (_la == T__16) {
                                                {
                                                    {
                                                        this.setState(200);
                                                        this.match(T__16);
                                                        this.setState(201);
                                                        this.expression(0);
                                                    }
                                                }
                                                this.setState(206);
                                                this._errHandler.sync(this);
                                                _la = this._input.LA(1);
                                            }
                                        }
                                    }

                                    this.setState(209);
                                    this.match(T__10);
                                }
                                    break;
                            }
                        }
                    }
                    this.setState(214);
                    this._errHandler.sync(this);
                    _alt = this.getInterpreter().adaptivePredict(this._input,
                        18, this._ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            this._errHandler.reportError(this, re);
            this._errHandler.recover(this, re);
        } finally {
            this.unrollRecursionContexts(_parentctx);
        }
        return _localctx;
    }

    @Override
    public boolean sempred(RuleContext _localctx, int ruleIndex,
            int predIndex) {
        switch (ruleIndex) {
            case 7:
                return this.expression_sempred((ExpressionContext) _localctx,
                    predIndex);
        }
        return true;
    }

    private boolean expression_sempred(ExpressionContext _localctx,
            int predIndex) {
        switch (predIndex) {
            case 0:
                return this.precpred(this._ctx, 13);
            case 1:
                return this.precpred(this._ctx, 12);
            case 2:
                return this.precpred(this._ctx, 11);
            case 3:
                return this.precpred(this._ctx, 10);
        }
        return true;
    }

    public static final String _serializedATN = "\u0004\u0001&\u00d8\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"
        +
        "\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"
        +
        "\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0001"
        +
        "\u0000\u0001\u0000\u0005\u0000\u0013\b\u0000\n\u0000\f\u0000\u0016\t\u0000"
        +
        "\u0001\u0001\u0003\u0001\u0019\b\u0001\u0001\u0001\u0001\u0001\u0001\u0001"
        +
        "\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"
        +
        "\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"
        +
        "\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0003\u0002.\b\u0002"
        +
        "\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u00024\b\u0002"
        +
        "\u0001\u0002\u0001\u0002\u0005\u00028\b\u0002\n\u0002\f\u0002;\t\u0002"
        +
        "\u0001\u0002\u0005\u0002>\b\u0002\n\u0002\f\u0002A\t\u0002\u0001\u0002"
        +
        "\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004"
        +
        "\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004"
        +
        "O\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"
        +
        "\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0005\u0005"
        +
        "[\b\u0005\n\u0005\f\u0005^\t\u0005\u0003\u0005`\b\u0005\u0001\u0005\u0001"
        +
        "\u0005\u0001\u0005\u0005\u0005e\b\u0005\n\u0005\f\u0005h\t\u0005\u0001"
        +
        "\u0005\u0005\u0005k\b\u0005\n\u0005\f\u0005n\t\u0005\u0001\u0005\u0001"
        +
        "\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0005"
        +
        "\u0006w\b\u0006\n\u0006\f\u0006z\t\u0006\u0001\u0006\u0001\u0006\u0001"
        +
        "\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"
        +
        "\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"
        +
        "\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006\u008e\b\u0006\u0001"
        +
        "\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"
        +
        "\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"
        +
        "\u0006\u0001\u0006\u0001\u0006\u0003\u0006\u009f\b\u0006\u0001\u0007\u0001"
        +
        "\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"
        +
        "\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"
        +
        "\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"
        +
        "\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u00b7\b\u0007\u0001\u0007\u0001"
        +
        "\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"
        +
        "\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"
        +
        "\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0005\u0007\u00cb"
        +
        "\b\u0007\n\u0007\f\u0007\u00ce\t\u0007\u0003\u0007\u00d0\b\u0007\u0001"
        +
        "\u0007\u0005\u0007\u00d3\b\u0007\n\u0007\f\u0007\u00d6\t\u0007\u0001\u0007"
        +
        "\u0000\u0001\u000e\b\u0000\u0002\u0004\u0006\b\n\f\u000e\u0000\u0001\u0001"
        +
        "\u0000\u0018\u001c\u00f1\u0000\u0010\u0001\u0000\u0000\u0000\u0002\u0018"
        +
        "\u0001\u0000\u0000\u0000\u0004-\u0001\u0000\u0000\u0000\u0006D\u0001\u0000"
        +
        "\u0000\u0000\bN\u0001\u0000\u0000\u0000\nP\u0001\u0000\u0000\u0000\f\u009e"
        +
        "\u0001\u0000\u0000\u0000\u000e\u00b6\u0001\u0000\u0000\u0000\u0010\u0014"
        +
        "\u0003\u0002\u0001\u0000\u0011\u0013\u0003\u0004\u0002\u0000\u0012\u0011"
        +
        "\u0001\u0000\u0000\u0000\u0013\u0016\u0001\u0000\u0000\u0000\u0014\u0012"
        +
        "\u0001\u0000\u0000\u0000\u0014\u0015\u0001\u0000\u0000\u0000\u0015\u0001"
        +
        "\u0001\u0000\u0000\u0000\u0016\u0014\u0001\u0000\u0000\u0000\u0017\u0019"
        +
        "\u0005\u0001\u0000\u0000\u0018\u0017\u0001\u0000\u0000\u0000\u0018\u0019"
        +
        "\u0001\u0000\u0000\u0000\u0019\u001a\u0001\u0000\u0000\u0000\u001a\u001b"
        +
        "\u0005\u0002\u0000\u0000\u001b\u001c\u0005$\u0000\u0000\u001c\u001d\u0005"
        +
        "\u0003\u0000\u0000\u001d\u001e\u0005\u0001\u0000\u0000\u001e\u001f\u0005"
        +
        "\u0004\u0000\u0000\u001f \u0005\u0005\u0000\u0000 !\u0005\u0006\u0000"
        +
        "\u0000!\"\u0005\u0007\u0000\u0000\"#\u0005\b\u0000\u0000#$\u0005\t\u0000"
        +
        "\u0000$%\u0005\n\u0000\u0000%&\u0005$\u0000\u0000&\'\u0005\u000b\u0000"
        +
        "\u0000\'(\u0005\u0003\u0000\u0000()\u0003\f\u0006\u0000)*\u0005\f\u0000"
        +
        "\u0000*+\u0005\f\u0000\u0000+\u0003\u0001\u0000\u0000\u0000,.\u0005\u0001"
        +
        "\u0000\u0000-,\u0001\u0000\u0000\u0000-.\u0001\u0000\u0000\u0000./\u0001"
        +
        "\u0000\u0000\u0000/0\u0005\u0002\u0000\u000003\u0005$\u0000\u000012\u0005"
        +
        "\r\u0000\u000024\u0005$\u0000\u000031\u0001\u0000\u0000\u000034\u0001"
        +
        "\u0000\u0000\u000045\u0001\u0000\u0000\u000059\u0005\u0003\u0000\u0000"
        +
        "68\u0003\u0006\u0003\u000076\u0001\u0000\u0000\u00008;\u0001\u0000\u0000"
        +
        "\u000097\u0001\u0000\u0000\u00009:\u0001\u0000\u0000\u0000:?\u0001\u0000"
        +
        "\u0000\u0000;9\u0001\u0000\u0000\u0000<>\u0003\n\u0005\u0000=<\u0001\u0000"
        +
        "\u0000\u0000>A\u0001\u0000\u0000\u0000?=\u0001\u0000\u0000\u0000?@\u0001"
        +
        "\u0000\u0000\u0000@B\u0001\u0000\u0000\u0000A?\u0001\u0000\u0000\u0000"
        +
        "BC\u0005\f\u0000\u0000C\u0005\u0001\u0000\u0000\u0000DE\u0003\b\u0004"
        +
        "\u0000EF\u0005$\u0000\u0000FG\u0005\u000e\u0000\u0000G\u0007\u0001\u0000"
        +
        "\u0000\u0000HI\u0005\u000f\u0000\u0000IJ\u0005\t\u0000\u0000JO\u0005\n"
        +
        "\u0000\u0000KO\u0005\u0010\u0000\u0000LO\u0005\u000f\u0000\u0000MO\u0005"
        +
        "$\u0000\u0000NH\u0001\u0000\u0000\u0000NK\u0001\u0000\u0000\u0000NL\u0001"
        +
        "\u0000\u0000\u0000NM\u0001\u0000\u0000\u0000O\t\u0001\u0000\u0000\u0000"
        +
        "PQ\u0005\u0001\u0000\u0000QR\u0003\b\u0004\u0000RS\u0005$\u0000\u0000"
        +
        "S_\u0005\u0007\u0000\u0000TU\u0003\b\u0004\u0000U\\\u0005$\u0000\u0000"
        +
        "VW\u0005\u0011\u0000\u0000WX\u0003\b\u0004\u0000XY\u0005$\u0000\u0000"
        +
        "Y[\u0001\u0000\u0000\u0000ZV\u0001\u0000\u0000\u0000[^\u0001\u0000\u0000"
        +
        "\u0000\\Z\u0001\u0000\u0000\u0000\\]\u0001\u0000\u0000\u0000]`\u0001\u0000"
        +
        "\u0000\u0000^\\\u0001\u0000\u0000\u0000_T\u0001\u0000\u0000\u0000_`\u0001"
        +
        "\u0000\u0000\u0000`a\u0001\u0000\u0000\u0000ab\u0005\u000b\u0000\u0000"
        +
        "bf\u0005\u0003\u0000\u0000ce\u0003\u0006\u0003\u0000dc\u0001\u0000\u0000"
        +
        "\u0000eh\u0001\u0000\u0000\u0000fd\u0001\u0000\u0000\u0000fg\u0001\u0000"
        +
        "\u0000\u0000gl\u0001\u0000\u0000\u0000hf\u0001\u0000\u0000\u0000ik\u0003"
        +
        "\f\u0006\u0000ji\u0001\u0000\u0000\u0000kn\u0001\u0000\u0000\u0000lj\u0001"
        +
        "\u0000\u0000\u0000lm\u0001\u0000\u0000\u0000mo\u0001\u0000\u0000\u0000"
        +
        "nl\u0001\u0000\u0000\u0000op\u0005\u0012\u0000\u0000pq\u0003\u000e\u0007"
        +
        "\u0000qr\u0005\u000e\u0000\u0000rs\u0005\f\u0000\u0000s\u000b\u0001\u0000"
        +
        "\u0000\u0000tx\u0005\u0003\u0000\u0000uw\u0003\f\u0006\u0000vu\u0001\u0000"
        +
        "\u0000\u0000wz\u0001\u0000\u0000\u0000xv\u0001\u0000\u0000\u0000xy\u0001"
        +
        "\u0000\u0000\u0000y{\u0001\u0000\u0000\u0000zx\u0001\u0000\u0000\u0000"
        +
        "{\u009f\u0005\f\u0000\u0000|}\u0005\u0013\u0000\u0000}~\u0005\u0007\u0000"
        +
        "\u0000~\u007f\u0003\u000e\u0007\u0000\u007f\u0080\u0005\u000b\u0000\u0000"
        +
        "\u0080\u0081\u0003\f\u0006\u0000\u0081\u0082\u0005\u0014\u0000\u0000\u0082"
        +
        "\u0083\u0003\f\u0006\u0000\u0083\u009f\u0001\u0000\u0000\u0000\u0084\u0085"
        +
        "\u0005\u0015\u0000\u0000\u0085\u0086\u0005\u0007\u0000\u0000\u0086\u0087"
        +
        "\u0003\u000e\u0007\u0000\u0087\u0088\u0005\u000b\u0000\u0000\u0088\u0089"
        +
        "\u0003\f\u0006\u0000\u0089\u009f\u0001\u0000\u0000\u0000\u008a\u008b\u0005"
        +
        "\u0016\u0000\u0000\u008b\u008d\u0005\u0007\u0000\u0000\u008c\u008e\u0003"
        +
        "\u000e\u0007\u0000\u008d\u008c\u0001\u0000\u0000\u0000\u008d\u008e\u0001"
        +
        "\u0000\u0000\u0000\u008e\u008f\u0001\u0000\u0000\u0000\u008f\u0090\u0005"
        +
        "\u000b\u0000\u0000\u0090\u009f\u0005\u000e\u0000\u0000\u0091\u0092\u0005"
        +
        "$\u0000\u0000\u0092\u0093\u0005\u0017\u0000\u0000\u0093\u0094\u0003\u000e"
        +
        "\u0007\u0000\u0094\u0095\u0005\u000e\u0000\u0000\u0095\u009f\u0001\u0000"
        +
        "\u0000\u0000\u0096\u0097\u0005$\u0000\u0000\u0097\u0098\u0005\t\u0000"
        +
        "\u0000\u0098\u0099\u0003\u000e\u0007\u0000\u0099\u009a\u0005\n\u0000\u0000"
        +
        "\u009a\u009b\u0005\u0017\u0000\u0000\u009b\u009c\u0003\u000e\u0007\u0000"
        +
        "\u009c\u009d\u0005\u000e\u0000\u0000\u009d\u009f\u0001\u0000\u0000\u0000"
        +
        "\u009et\u0001\u0000\u0000\u0000\u009e|\u0001\u0000\u0000\u0000\u009e\u0084"
        +
        "\u0001\u0000\u0000\u0000\u009e\u008a\u0001\u0000\u0000\u0000\u009e\u0091"
        +
        "\u0001\u0000\u0000\u0000\u009e\u0096\u0001\u0000\u0000\u0000\u009f\r\u0001"
        +
        "\u0000\u0000\u0000\u00a0\u00a1\u0006\u0007\uffff\uffff\u0000\u00a1\u00a2"
        +
        "\u0005\u001f\u0000\u0000\u00a2\u00a3\u0005\u000f\u0000\u0000\u00a3\u00a4"
        +
        "\u0005\t\u0000\u0000\u00a4\u00a5\u0003\u000e\u0007\u0000\u00a5\u00a6\u0005"
        +
        "\n\u0000\u0000\u00a6\u00b7\u0001\u0000\u0000\u0000\u00a7\u00a8\u0005\u001f"
        +
        "\u0000\u0000\u00a8\u00a9\u0005$\u0000\u0000\u00a9\u00aa\u0005\u0007\u0000"
        +
        "\u0000\u00aa\u00b7\u0005\u000b\u0000\u0000\u00ab\u00ac\u0005 \u0000\u0000"
        +
        "\u00ac\u00b7\u0003\u000e\u0007\u0007\u00ad\u00ae\u0005\u0007\u0000\u0000"
        +
        "\u00ae\u00af\u0003\u000e\u0007\u0000\u00af\u00b0\u0005\u000b\u0000\u0000"
        +
        "\u00b0\u00b7\u0001\u0000\u0000\u0000\u00b1\u00b7\u0005%\u0000\u0000\u00b2"
        +
        "\u00b7\u0005!\u0000\u0000\u00b3\u00b7\u0005\"\u0000\u0000\u00b4\u00b7"
        +
        "\u0005$\u0000\u0000\u00b5\u00b7\u0005#\u0000\u0000\u00b6\u00a0\u0001\u0000"
        +
        "\u0000\u0000\u00b6\u00a7\u0001\u0000\u0000\u0000\u00b6\u00ab\u0001\u0000"
        +
        "\u0000\u0000\u00b6\u00ad\u0001\u0000\u0000\u0000\u00b6\u00b1\u0001\u0000"
        +
        "\u0000\u0000\u00b6\u00b2\u0001\u0000\u0000\u0000\u00b6\u00b3\u0001\u0000"
        +
        "\u0000\u0000\u00b6\u00b4\u0001\u0000\u0000\u0000\u00b6\u00b5\u0001\u0000"
        +
        "\u0000\u0000\u00b7\u00d4\u0001\u0000\u0000\u0000\u00b8\u00b9\n\r\u0000"
        +
        "\u0000\u00b9\u00ba\u0007\u0000\u0000\u0000\u00ba\u00d3\u0003\u000e\u0007"
        +
        "\u000e\u00bb\u00bc\n\f\u0000\u0000\u00bc\u00bd\u0005\t\u0000\u0000\u00bd"
        +
        "\u00be\u0003\u000e\u0007\u0000\u00be\u00bf\u0005\n\u0000\u0000\u00bf\u00d3"
        +
        "\u0001\u0000\u0000\u0000\u00c0\u00c1\n\u000b\u0000\u0000\u00c1\u00c2\u0005"
        +
        "\u001d\u0000\u0000\u00c2\u00d3\u0005\u001e\u0000\u0000\u00c3\u00c4\n\n"
        +
        "\u0000\u0000\u00c4\u00c5\u0005\u001d\u0000\u0000\u00c5\u00c6\u0005$\u0000"
        +
        "\u0000\u00c6\u00cf\u0005\u0007\u0000\u0000\u00c7\u00cc\u0003\u000e\u0007"
        +
        "\u0000\u00c8\u00c9\u0005\u0011\u0000\u0000\u00c9\u00cb\u0003\u000e\u0007"
        +
        "\u0000\u00ca\u00c8\u0001\u0000\u0000\u0000\u00cb\u00ce\u0001\u0000\u0000"
        +
        "\u0000\u00cc\u00ca\u0001\u0000\u0000\u0000\u00cc\u00cd\u0001\u0000\u0000"
        +
        "\u0000\u00cd\u00d0\u0001\u0000\u0000\u0000\u00ce\u00cc\u0001\u0000\u0000"
        +
        "\u0000\u00cf\u00c7\u0001\u0000\u0000\u0000\u00cf\u00d0\u0001\u0000\u0000"
        +
        "\u0000\u00d0\u00d1\u0001\u0000\u0000\u0000\u00d1\u00d3\u0005\u000b\u0000"
        +
        "\u0000\u00d2\u00b8\u0001\u0000\u0000\u0000\u00d2\u00bb\u0001\u0000\u0000"
        +
        "\u0000\u00d2\u00c0\u0001\u0000\u0000\u0000\u00d2\u00c3\u0001\u0000\u0000"
        +
        "\u0000\u00d3\u00d6\u0001\u0000\u0000\u0000\u00d4\u00d2\u0001\u0000\u0000"
        +
        "\u0000\u00d4\u00d5\u0001\u0000\u0000\u0000\u00d5\u000f\u0001\u0000\u0000"
        +
        "\u0000\u00d6\u00d4\u0001\u0000\u0000\u0000\u0013\u0014\u0018-39?N\\_f"
        +
        "lx\u008d\u009e\u00b6\u00cc\u00cf\u00d2\u00d4";
    public static final ATN _ATN = new ATNDeserializer()
        .deserialize(_serializedATN.toCharArray());
    static {
        _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
        for (var i = 0; i < _ATN.getNumberOfDecisions(); i++) {
            _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
        }
    }
}