package com.jayxu.playground.antlr.json;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class AntlrDemo {
    public static void main(String[] args) throws Exception {
        var cs = CharStreams.fromStream(AntlrDemo.class.getClassLoader()
            .getResourceAsStream("test.json"));
        var lexer = new JSONLexer(cs);
        var token = new CommonTokenStream(lexer);
        var parser = new JSONParser(token);
        var json = parser.json();

        var vistor = new JSONBaseVisitor<>();
        var o = vistor.visit(json);
        System.out.println(o);
    }
}
