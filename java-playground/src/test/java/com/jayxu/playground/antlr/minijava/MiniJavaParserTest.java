import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.Test;

class MiniJavaParserTest {

    @Test
    void test() throws Exception {
        try (var stream = this.getClass().getClassLoader().getResourceAsStream("Factorial.java")) {
            var cs = CharStreams.fromStream(stream);
            var lexer = new MiniJavaLexer(cs);
            var token = new CommonTokenStream(lexer);
            var parser = new MiniJavaParser(token);
            var java = parser.program();

            var vistor = new MiniJavaBaseVisitor<>();

            vistor.visit(java);
        }
    }

}
