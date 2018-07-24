package demo;

import lib.annotations.callgraph.CallSite;

/**
 * This test case tests a new language feature from Java 9, namely private static interface methods.
 *
 * @author Michael Reif
 */
public class Demo {

    @CallSite(name = "publicTarget", line = 14, resolvedTargets = "Ldemo/Interface;")
    public static void main(String[] args) {
        Interface.publicTarget();
    }
}

interface Interface {

    @CallSite(name = "privateTarget", line = 22, resolvedTargets = "Ldemo/Interface;")
    static void publicTarget(){
        privateTarget();
    }

    private static void privateTarget(){}
}