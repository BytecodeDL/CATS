/* BSD 2Clause License:
 * Copyright (c) 2009 - 2015
 * Software Technology Group
 * Department of Computer Science
 * Technische Universität Darmstadt
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  - Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED T
 * PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package callgraph.properties.methodAccessibility.instanceMethods;

import org.opalj.annotations.callgraph.properties.ProjectAccessibilityKeys;
import org.opalj.annotations.callgraph.properties.ProjectAccessibilityProperty;

/**
*
* This class is for test purposes only.
* It is public which implies that all public and protected methods
* are visible to the client. (regarding the closed packages assumption)
* 
* It implements the PackagePrivateInterface which defines a default method.
* The interface method is overwridden by publicMethod. Hence, it is not inherited
* and due to that not exposed to the client.
* 
* @author Michael Reif
*
*/
public class PublicClass implements PackagePrivateInterface{

	@ProjectAccessibilityProperty
	public void publicMethod(){
	}
	
	@ProjectAccessibilityProperty(
			cpa=ProjectAccessibilityKeys.PackageLocal)
	void packagePrivateMethod(){
	}
	
	@ProjectAccessibilityProperty
	protected void protectedMethod(){
	}
	
	@ProjectAccessibilityProperty(
			opa=ProjectAccessibilityKeys.ClassLocal,
			cpa=ProjectAccessibilityKeys.ClassLocal)
	private void privateMethod(){
	}
}
