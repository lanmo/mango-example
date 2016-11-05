package com.yn.mango.descriptor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yangnan on 16/10/26.
 */
public class Methods {
    public static MethodDescriptor getMethodDescriptor(Method method, ParameterNameDiscover parameterNameDiscover) {
        List<Annotation> ans = new ArrayList<Annotation>();
        for (Annotation annotation : method.getAnnotations()) {
            ans.add(annotation);
        }
        for (Annotation annotation : method.getDeclaredAnnotations()) {
            ans.add(annotation);
        }

        ReturnDescriptor returnDescriptor =  ReturnDescriptor.create(method.getGenericReturnType(), ans);

        List<ParameterDescriptor> psl = new ArrayList<ParameterDescriptor>();
        Type[] paramterTypes = method.getGenericParameterTypes();
        String[] names = parameterNameDiscover.getParameterNames(method);
        Annotation[][] annotations = method.getParameterAnnotations();
        for (int i=0; i<names.length; i++) {
            String name = names[i];
            Annotation[] annotations1 = annotations[i];
            psl.add(ParameterDescriptor.create(paramterTypes[i], Arrays.asList(annotations1), i,name));
        }

        return MethodDescriptor.create(method.getDeclaringClass(), psl, returnDescriptor);
    }
}
