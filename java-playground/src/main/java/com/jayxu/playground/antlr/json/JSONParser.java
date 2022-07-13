package com.jayxu.playground.antlr.json;

import java.util.List;

import org.antlr.v4.runtime.NoViableAltException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.RuntimeMetaData;
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
public class JSONParser extends Parser {
    static {
        RuntimeMetaData.checkVersion("4.10.1", RuntimeMetaData.VERSION);
    }

    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache = new PredictionContextCache();
    public static final int T__0 = 1, T__1 = 2, T__2 = 3, T__3 = 4, T__4 = 5,
            T__5 = 6, T__6 = 7, T__7 = 8, T__8 = 9,
            STRING = 10, NUMBER = 11, WS = 12;
    public static final int RULE_json = 0, RULE_obj = 1, RULE_pair = 2,
            RULE_arr = 3, RULE_value = 4;

    private static String[] makeRuleNames() {
        return new String[] {
            "json", "obj", "pair", "arr", "value"
        };
    }

    public static final String[] ruleNames = makeRuleNames();

    private static String[] makeLiteralNames() {
        return new String[] {
            null, "'{'", "','", "'}'", "':'", "'['", "']'", "'true'", "'false'",
            "'null'"
        };
    }

    private static final String[] _LITERAL_NAMES = makeLiteralNames();

    private static String[] makeSymbolicNames() {
        return new String[] {
            null, null, null, null, null, null, null, null, null, null,
            "STRING",
            "NUMBER", "WS"
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
        return "JSON.g4";
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

    public JSONParser(TokenStream input) {
        super(input);
        this._interp = new ParserATNSimulator(this, _ATN, _decisionToDFA,
            _sharedContextCache);
    }

    public static class JsonContext extends ParserRuleContext {
        public ValueContext value() {
            return this.getRuleContext(ValueContext.class, 0);
        }

        public JsonContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_json;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof JSONListener) {
                ((JSONListener) listener).enterJson(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof JSONListener) {
                ((JSONListener) listener).exitJson(this);
            }
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof JSONVisitor) {
                return ((JSONVisitor<? extends T>) visitor).visitJson(this);
            }
            return visitor.visitChildren(this);
        }
    }

    public final JsonContext json() throws RecognitionException {
        var _localctx = new JsonContext(this._ctx, this.getState());
        this.enterRule(_localctx, 0, RULE_json);
        try {
            this.enterOuterAlt(_localctx, 1);
            {
                this.setState(10);
                this.value();
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

    public static class ObjContext extends ParserRuleContext {
        public List<PairContext> pair() {
            return this.getRuleContexts(PairContext.class);
        }

        public PairContext pair(int i) {
            return this.getRuleContext(PairContext.class, i);
        }

        public ObjContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_obj;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof JSONListener) {
                ((JSONListener) listener).enterObj(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof JSONListener) {
                ((JSONListener) listener).exitObj(this);
            }
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof JSONVisitor) {
                return ((JSONVisitor<? extends T>) visitor).visitObj(this);
            }
            return visitor.visitChildren(this);
        }
    }

    public final ObjContext obj() throws RecognitionException {
        var _localctx = new ObjContext(this._ctx, this.getState());
        this.enterRule(_localctx, 2, RULE_obj);
        int _la;
        try {
            this.setState(25);
            this._errHandler.sync(this);
            switch (this.getInterpreter().adaptivePredict(this._input, 1,
                this._ctx)) {
                case 1:
                    this.enterOuterAlt(_localctx, 1); {
                    this.setState(12);
                    this.match(T__0);
                    this.setState(13);
                    this.pair();
                    this.setState(18);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
                    while (_la == T__1) {
                        {
                            {
                                this.setState(14);
                                this.match(T__1);
                                this.setState(15);
                                this.pair();
                            }
                        }
                        this.setState(20);
                        this._errHandler.sync(this);
                        _la = this._input.LA(1);
                    }
                    this.setState(21);
                    this.match(T__2);
                }
                    break;
                case 2:
                    this.enterOuterAlt(_localctx, 2); {
                    this.setState(23);
                    this.match(T__0);
                    this.setState(24);
                    this.match(T__2);
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

    public static class PairContext extends ParserRuleContext {
        public TerminalNode STRING() {
            return this.getToken(JSONParser.STRING, 0);
        }

        public ValueContext value() {
            return this.getRuleContext(ValueContext.class, 0);
        }

        public PairContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_pair;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof JSONListener) {
                ((JSONListener) listener).enterPair(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof JSONListener) {
                ((JSONListener) listener).exitPair(this);
            }
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof JSONVisitor) {
                return ((JSONVisitor<? extends T>) visitor).visitPair(this);
            }
            return visitor.visitChildren(this);
        }
    }

    public final PairContext pair() throws RecognitionException {
        var _localctx = new PairContext(this._ctx, this.getState());
        this.enterRule(_localctx, 4, RULE_pair);
        try {
            this.enterOuterAlt(_localctx, 1);
            {
                this.setState(27);
                this.match(STRING);
                this.setState(28);
                this.match(T__3);
                this.setState(29);
                this.value();
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

    public static class ArrContext extends ParserRuleContext {
        public List<ValueContext> value() {
            return this.getRuleContexts(ValueContext.class);
        }

        public ValueContext value(int i) {
            return this.getRuleContext(ValueContext.class, i);
        }

        public ArrContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_arr;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof JSONListener) {
                ((JSONListener) listener).enterArr(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof JSONListener) {
                ((JSONListener) listener).exitArr(this);
            }
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof JSONVisitor) {
                return ((JSONVisitor<? extends T>) visitor).visitArr(this);
            }
            return visitor.visitChildren(this);
        }
    }

    public final ArrContext arr() throws RecognitionException {
        var _localctx = new ArrContext(this._ctx, this.getState());
        this.enterRule(_localctx, 6, RULE_arr);
        int _la;
        try {
            this.setState(44);
            this._errHandler.sync(this);
            switch (this.getInterpreter().adaptivePredict(this._input, 3,
                this._ctx)) {
                case 1:
                    this.enterOuterAlt(_localctx, 1); {
                    this.setState(31);
                    this.match(T__4);
                    this.setState(32);
                    this.value();
                    this.setState(37);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
                    while (_la == T__1) {
                        {
                            {
                                this.setState(33);
                                this.match(T__1);
                                this.setState(34);
                                this.value();
                            }
                        }
                        this.setState(39);
                        this._errHandler.sync(this);
                        _la = this._input.LA(1);
                    }
                    this.setState(40);
                    this.match(T__5);
                }
                    break;
                case 2:
                    this.enterOuterAlt(_localctx, 2); {
                    this.setState(42);
                    this.match(T__4);
                    this.setState(43);
                    this.match(T__5);
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

    public static class ValueContext extends ParserRuleContext {
        public TerminalNode STRING() {
            return this.getToken(JSONParser.STRING, 0);
        }

        public TerminalNode NUMBER() {
            return this.getToken(JSONParser.NUMBER, 0);
        }

        public ObjContext obj() {
            return this.getRuleContext(ObjContext.class, 0);
        }

        public ArrContext arr() {
            return this.getRuleContext(ArrContext.class, 0);
        }

        public ValueContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_value;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof JSONListener) {
                ((JSONListener) listener).enterValue(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof JSONListener) {
                ((JSONListener) listener).exitValue(this);
            }
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof JSONVisitor) {
                return ((JSONVisitor<? extends T>) visitor).visitValue(this);
            }
            return visitor.visitChildren(this);
        }
    }

    public final ValueContext value() throws RecognitionException {
        var _localctx = new ValueContext(this._ctx, this.getState());
        this.enterRule(_localctx, 8, RULE_value);
        try {
            this.setState(53);
            this._errHandler.sync(this);
            switch (this._input.LA(1)) {
                case STRING:
                    this.enterOuterAlt(_localctx, 1); {
                    this.setState(46);
                    this.match(STRING);
                }
                    break;
                case NUMBER:
                    this.enterOuterAlt(_localctx, 2); {
                    this.setState(47);
                    this.match(NUMBER);
                }
                    break;
                case T__0:
                    this.enterOuterAlt(_localctx, 3); {
                    this.setState(48);
                    this.obj();
                }
                    break;
                case T__4:
                    this.enterOuterAlt(_localctx, 4); {
                    this.setState(49);
                    this.arr();
                }
                    break;
                case T__6:
                    this.enterOuterAlt(_localctx, 5); {
                    this.setState(50);
                    this.match(T__6);
                }
                    break;
                case T__7:
                    this.enterOuterAlt(_localctx, 6); {
                    this.setState(51);
                    this.match(T__7);
                }
                    break;
                case T__8:
                    this.enterOuterAlt(_localctx, 7); {
                    this.setState(52);
                    this.match(T__8);
                }
                    break;
                default:
                    throw new NoViableAltException(this);
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

    public static final String _serializedATN = "\u0004\u0001\f8\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"
        +
        "\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0001"
        +
        "\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0005"
        +
        "\u0001\u0011\b\u0001\n\u0001\f\u0001\u0014\t\u0001\u0001\u0001\u0001\u0001"
        +
        "\u0001\u0001\u0001\u0001\u0003\u0001\u001a\b\u0001\u0001\u0002\u0001\u0002"
        +
        "\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"
        +
        "\u0005\u0003$\b\u0003\n\u0003\f\u0003\'\t\u0003\u0001\u0003\u0001\u0003"
        +
        "\u0001\u0003\u0001\u0003\u0003\u0003-\b\u0003\u0001\u0004\u0001\u0004"
        +
        "\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004"
        +
        "6\b\u0004\u0001\u0004\u0000\u0000\u0005\u0000\u0002\u0004\u0006\b\u0000"
        +
        "\u0000<\u0000\n\u0001\u0000\u0000\u0000\u0002\u0019\u0001\u0000\u0000"
        +
        "\u0000\u0004\u001b\u0001\u0000\u0000\u0000\u0006,\u0001\u0000\u0000\u0000"
        +
        "\b5\u0001\u0000\u0000\u0000\n\u000b\u0003\b\u0004\u0000\u000b\u0001\u0001"
        +
        "\u0000\u0000\u0000\f\r\u0005\u0001\u0000\u0000\r\u0012\u0003\u0004\u0002"
        +
        "\u0000\u000e\u000f\u0005\u0002\u0000\u0000\u000f\u0011\u0003\u0004\u0002"
        +
        "\u0000\u0010\u000e\u0001\u0000\u0000\u0000\u0011\u0014\u0001\u0000\u0000"
        +
        "\u0000\u0012\u0010\u0001\u0000\u0000\u0000\u0012\u0013\u0001\u0000\u0000"
        +
        "\u0000\u0013\u0015\u0001\u0000\u0000\u0000\u0014\u0012\u0001\u0000\u0000"
        +
        "\u0000\u0015\u0016\u0005\u0003\u0000\u0000\u0016\u001a\u0001\u0000\u0000"
        +
        "\u0000\u0017\u0018\u0005\u0001\u0000\u0000\u0018\u001a\u0005\u0003\u0000"
        +
        "\u0000\u0019\f\u0001\u0000\u0000\u0000\u0019\u0017\u0001\u0000\u0000\u0000"
        +
        "\u001a\u0003\u0001\u0000\u0000\u0000\u001b\u001c\u0005\n\u0000\u0000\u001c"
        +
        "\u001d\u0005\u0004\u0000\u0000\u001d\u001e\u0003\b\u0004\u0000\u001e\u0005"
        +
        "\u0001\u0000\u0000\u0000\u001f \u0005\u0005\u0000\u0000 %\u0003\b\u0004"
        +
        "\u0000!\"\u0005\u0002\u0000\u0000\"$\u0003\b\u0004\u0000#!\u0001\u0000"
        +
        "\u0000\u0000$\'\u0001\u0000\u0000\u0000%#\u0001\u0000\u0000\u0000%&\u0001"
        +
        "\u0000\u0000\u0000&(\u0001\u0000\u0000\u0000\'%\u0001\u0000\u0000\u0000"
        +
        "()\u0005\u0006\u0000\u0000)-\u0001\u0000\u0000\u0000*+\u0005\u0005\u0000"
        +
        "\u0000+-\u0005\u0006\u0000\u0000,\u001f\u0001\u0000\u0000\u0000,*\u0001"
        +
        "\u0000\u0000\u0000-\u0007\u0001\u0000\u0000\u0000.6\u0005\n\u0000\u0000"
        +
        "/6\u0005\u000b\u0000\u000006\u0003\u0002\u0001\u000016\u0003\u0006\u0003"
        +
        "\u000026\u0005\u0007\u0000\u000036\u0005\b\u0000\u000046\u0005\t\u0000"
        +
        "\u00005.\u0001\u0000\u0000\u00005/\u0001\u0000\u0000\u000050\u0001\u0000"
        +
        "\u0000\u000051\u0001\u0000\u0000\u000052\u0001\u0000\u0000\u000053\u0001"
        +
        "\u0000\u0000\u000054\u0001\u0000\u0000\u00006\t\u0001\u0000\u0000\u0000"
        +
        "\u0005\u0012\u0019%,5";
    public static final ATN _ATN = new ATNDeserializer()
        .deserialize(_serializedATN.toCharArray());
    static {
        _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
        for (var i = 0; i < _ATN.getNumberOfDecisions(); i++) {
            _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
        }
    }
}