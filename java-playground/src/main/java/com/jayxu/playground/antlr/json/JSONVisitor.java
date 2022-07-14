package com.jayxu.playground.antlr.json;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link JSONParser}.
 *
 * @param <T>
 *        The return type of the visit operation. Use {@link Void} for
 *        operations with no return type.
 */
public interface JSONVisitor<T> extends ParseTreeVisitor<T> {
    /**
     * Visit a parse tree produced by {@link JSONParser#json}.
     *
     * @param ctx
     *        the parse tree
     * @return the visitor result
     */
    default T visitJson(JSONParser.JsonContext ctx) {
        return this.visitChildren(ctx);
    }

    /**
     * Visit a parse tree produced by {@link JSONParser#obj}.
     *
     * @param ctx
     *        the parse tree
     * @return the visitor result
     */
    default T visitObj(JSONParser.ObjContext ctx) {
        return this.visitChildren(ctx);
    }

    /**
     * Visit a parse tree produced by {@link JSONParser#pair}.
     *
     * @param ctx
     *        the parse tree
     * @return the visitor result
     */
    T visitPair(JSONParser.PairContext ctx);

    /**
     * Visit a parse tree produced by {@link JSONParser#arr}.
     *
     * @param ctx
     *        the parse tree
     * @return the visitor result
     */
    T visitArr(JSONParser.ArrContext ctx);

    /**
     * Visit a parse tree produced by {@link JSONParser#value}.
     *
     * @param ctx
     *        the parse tree
     * @return the visitor result
     */
    default T visitValue(JSONParser.ValueContext ctx) {
        return this.visitChildren(ctx);
    }
}