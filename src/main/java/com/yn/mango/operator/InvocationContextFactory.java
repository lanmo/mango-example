package com.yn.mango.operator;

import com.yn.mango.binding.DefaultInvocationContext;
import com.yn.mango.binding.InvocationContext;
import com.yn.mango.binding.ParameterContext;

/**
 * Created by yangnan on 2016/12/6.
 */
public class InvocationContextFactory {
    private ParameterContext parameterContext;

    public InvocationContextFactory(ParameterContext parameterContext) {
        this.parameterContext = parameterContext;
    }

    public InvocationContext newInvocationContext(Object[] values) {
        InvocationContext context = DefaultInvocationContext.create();
        for (int i=0; i<values.length; i++) {
            context.addParameter(parameterContext.getParameterNameByPosition(i), values[i]);
        }

        return context;
    }

    public static InvocationContextFactory create(ParameterContext parameterContext) {
        return new InvocationContextFactory(parameterContext);
    }
}
