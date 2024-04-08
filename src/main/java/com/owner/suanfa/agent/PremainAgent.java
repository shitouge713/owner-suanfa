package com.owner.suanfa.agent;

import java.lang.instrument.Instrumentation;

public class PremainAgent {
    private static Instrumentation instrumentation;
    //agentArgs是premain函数得到的程序参数，通过– javaagent命令行参数传入。
    public static void premain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new SimClassFileTransformer());
    }
    public static long getObjectSize(Object obj) {
        if (instrumentation == null) {
            throw new IllegalStateException("Instrumentation is not available");
        }
        return instrumentation.getObjectSize(obj);
    }
}
