package com.jayxu.playground.antlr.json;

import java.io.IOException;
import java.io.InputStream;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class XJson {
    public static Object
            parse(InputStream stream) throws IOException {
        var cs = CharStreams.fromStream(stream);
        var lexer = new JSONLexer(cs);
        var token = new CommonTokenStream(lexer);
        var parser = new JSONParser(token);
        var json = parser.json();

        var vistor = new JSONBaseVisitor<>();

        return vistor.visit(json);
    }
}
