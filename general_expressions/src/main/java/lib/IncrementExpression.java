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

import lib.annotations.callgraph.*;
import lib.annotations.documentation.CGNote;
import lib.annotations.properties.EntryPoint;

import java.util.function.UnaryOperator;

import static lib.annotations.callgraph.AnalysisMode.CPA;
import static lib.annotations.callgraph.AnalysisMode.OPA;
import static lib.annotations.callgraph.CallGraphAlgorithm.CHA;
import static lib.annotations.callgraph.TargetResolution.DYNAMIC;
import static lib.annotations.documentation.CGCategory.INVOKEDYNAMIC;

/**
 * A IncrementExpression represents an unary operation that increments a constant.
 *
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
 * INTENTIONALLY LEFT EMPTY TO MAKE SURE THAT THE SPECIFIED LINE NUMBERS ARE STABLE IF THE
 * CODE (E.G. IMPORTS) CHANGE.
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * -->
 *
 * @author Michael Reif
 */
public class IncrementExpression extends UnaryExpression {

    public static final String FQN = "lib/IncrementExpression";

    public IncrementExpression(Expression expr) {
        super(expr);
    }

    @EntryPoint(value = {OPA, CPA})
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @CallSite(name= "toString", resolvedMethods = {
            @ResolvedMethod(receiverType = IncrementExpression.FQN),
            @ResolvedMethod(receiverType = IdentityExpression.FQN),
            @ResolvedMethod(receiverType = SquareExpression.FQN),
            @ResolvedMethod(receiverType = DecrementExpression.FQN, iff = @ResolvingCondition(containedInMax = CHA)),
            @ResolvedMethod(receiverType = AddExpression.FQN),
            @ResolvedMethod(receiverType = SubExpression.FQN, iff = @ResolvingCondition(containedInMax = CHA)),
            @ResolvedMethod(receiverType = MultExpression.FQN)
    }, returnType = String.class, line = 110)
    @EntryPoint(value = {OPA, CPA})
    public String toString(){
        return "Inc("+ expr.toString() + ")";
    }

	@Override
	public Constant eval(Map<String, Constant> values) {
		return new Constant(expr.eval(values).getValue() + 1);
	}
}
