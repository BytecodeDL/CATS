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

import static lib.annotations.callgraph.AnalysisMode.*;

import lib.annotations.callgraph.CallSite;
import lib.annotations.callgraph.ResolvedMethod;
import lib.annotations.properties.EntryPoint;

import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * This class simply wraps an integer value. Defines methods to be called during (de-)serialization.
 *
 * <!--
 * <b>NOTE</b><br>
 * This class is not meant to be (automatically) recompiled; it just serves documentation
 * purposes.
 *
 *
 *
 *
 *
 *
 * INTENTIONALLY LEFT EMPTY TO MAKE SURE THAT THE SPECIFIED LINE NUMBERS ARE STABLE IF THE
 * CODE (E.G. IMPORTS) CHANGE.
 *
 *
 *
 *
 *
 * -->
 *
 * @author Michael Eichberg
 * @author Micahel Reif
 * @author Roberts Kolosovs
 */
public class Constant implements Expression, Serializable{
	
	public static final String FQN = "expressions/Constant";

    private final int value;

    public Constant(int value) {
        this.value = value;
    }

    @EntryPoint(value = {OPA, CPA})
    public int getValue() {
        return value;
    }

    @EntryPoint(value = {OPA, CPA})
    public Constant eval(Map<String,Constant> values) {
        return this;
    }

    @CallSite(name = "visit",
            resolvedMethods = {@ResolvedMethod(receiverType = "expressions/ExpressionPrinter")},
            returnType = Object.class,
            line = 96
    )
    @EntryPoint(value = {OPA, CPA})
    public <T> T accept(ExpressionVisitor <T> visitor) {
        return visitor.visit(this);
    }

    @EntryPoint(value = {OPA, CPA})
    public native float toFloat();

    @EntryPoint(value = {OPA, CPA})
    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
    	out.defaultWriteObject();
    }
    
    @EntryPoint(value = {OPA, CPA})
    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
    	in.defaultReadObject();
    }
    
    @EntryPoint(value = {OPA, CPA})
    private Object writeReplace() throws ObjectStreamException {
    	return this;
    }
    
    @EntryPoint(value = {OPA, CPA})
    private Object readResolve() throws ObjectStreamException {
    	return this;
    }
}
