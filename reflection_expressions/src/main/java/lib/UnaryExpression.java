/*
 * BSD 2-Clause License:
 * Copyright (c) 2009 - 2016
 * Software Technology Group
 * Department of Computer Science
 * Technische Universität Darmstadt
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  - Redistributions of source code must retain the above copyright notice,
 *     this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright notice,
 *     this list of conditions and the following disclaimer in the documentation
 *     and/or other materials provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 *  AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 *  IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 *  ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 *  LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 *  SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 *  INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 *  CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 *  ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 *  POSSIBILITY OF SUCH DAMAGE.
 */

package lib;

import lib.annotations.callgraph.IndirectCall;
import lib.annotations.documentation.CGFeature;
import lib.annotations.properties.EntryPoint;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static lib.annotations.callgraph.AnalysisMode.CPA;
import static lib.annotations.callgraph.AnalysisMode.OPA;

/**
 * An abstract unary Expression where the operation has to be implemented
 * via a lambda function.
 * <p>
 * <p>
 * <!--
 * <b>NOTE</b><br>
 * This class is not meant to be (automatically) recompiled; it just serves documentation
 * purposes.
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * INTENTIONALLY LEFT EMPTY TO MAKE SURE THAT THE SPECIFIED LINE NUMBERS ARE STABLE IF THE
 * CODE (E.G. IMPORTS) CHANGE.
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * -->
 *
 * @author Michael Reif
 * @author Roberts Kolosovs
 */

public abstract class UnaryExpression implements Expression {

    public static final String FQN = "Llib/UnaryExpression;";

    protected Expression expr;

    @EntryPoint(value = {OPA, CPA})
    @IndirectCall(name = "<init>", declaringClass = SquareExpression.FQN, parameterTypes = Expression.class, feature = CGFeature.ComplexReflection)
    public static UnaryExpression createUnaryExpressions(
            UnaryOperator operator,
            final Expression expr) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        Class<?> clazz = Class.forName(operator.toString());
        Constructor<?> constructor = clazz.getConstructor(Expression.class);
        return (UnaryExpression) constructor.newInstance(expr);
    }

    @EntryPoint(value = {OPA, CPA})
    @IndirectCall(name = "<init>", declaringClass = NegationExpression.FQN, parameterTypes = Expression.class, feature = CGFeature.ContextSensitiveReflection)
    public static UnaryExpression createUnaryExpressions(
            String operator,
            final Expression expr) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        Class<?> clazz = Class.forName(operator);
        Constructor<?> constructor = clazz.getConstructor(Expression.class);
        return (UnaryExpression) constructor.newInstance(expr);
    }

    @EntryPoint(value = {OPA, CPA})
    @IndirectCall(name = "<init>", declaringClass = IdentityExpression.FQN, parameterTypes = Expression.class, feature = CGFeature.TrivialReflection)
    public static UnaryExpression createIdentityExpression(final Expression expr) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> clazz = Class.forName("lib.IdentityExpression");
        Constructor<?> constructor = clazz.getConstructor(Expression.class);
        return (UnaryExpression) constructor.newInstance(expr);
    }

    @EntryPoint(value = {OPA, CPA})
    public UnaryExpression(Expression expr) {
        this.expr = expr;
    }

    public abstract String toString();

    public abstract Constant eval(Map<String, Constant> values);
}