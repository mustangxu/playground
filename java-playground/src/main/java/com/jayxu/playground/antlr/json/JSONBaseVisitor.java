package com.jayxu.playground.antlr.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import com.ibm.icu.math.BigDecimal;

/**
 * This class provides an empty implementation of {@link JSONVisitor},
 * which can be extended to create a visitor which only needs to handle a subset
 * of the available methods.
 *
 * @param <T>
 *        The return type of the visit operation. Use {@link Void} for
 *        operations with no return type.
 */
@SuppressWarnings("unchecked")
public class JSONBaseVisitor<T> extends AbstractParseTreeVisitor<T>
        implements JSONVisitor<T> {

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.
     * </p>
     */
    @Override
    public T visitPair(JSONParser.PairContext ctx) {
        Map<String, Object> pair = new HashMap<>();
        pair.put(ctx.getChild(0).getText(),
            ctx.getChild(2).accept(this));
//        log.debug("{}", pair);

        return (T) pair;
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.
     * </p>
     */
    @Override
    public T visitArr(JSONParser.ArrContext ctx) {
        var count = ctx.getChildCount();
        List<Object> list = new ArrayList<>(count);

        for (var i = 1; i < count; i++) {
            var c = ctx.getChild(i);
            if (c.getPayload() instanceof CommonToken) {
                continue;
            }

            list.add(c.accept(this));
        }

        return (T) list.toArray();
    }

    @Override
    public T visitTerminal(TerminalNode node) {
        return (T) switch (node.getSymbol().getType()) {
            case JSONParser.STRING -> node.getText();
            case JSONParser.NUMBER -> new BigDecimal(node.getText());
            case 7 -> true;
            case 8 -> false;
            case 9 -> null;
            default -> super.visitTerminal(node);
        };
    }

    @Override
    protected T defaultResult() {
        return (T) new HashMap<>();
    }

    @SuppressWarnings("rawtypes")
    @Override
    protected T aggregateResult(T t1, T t2) {
//        log.info("t1: {}, t2: {}", t1, t2);
        if (t1 instanceof Map m1 && t2 instanceof Map m2) {
            m1.putAll(m2);
            return (T) m1;
        }

        return super.aggregateResult(t1, t2);
    }
}