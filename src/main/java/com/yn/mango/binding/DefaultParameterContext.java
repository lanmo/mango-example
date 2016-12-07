package com.yn.mango.binding;

import com.yn.mango.descriptor.ParameterDescriptor;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangnan on 16/11/8.
 */
public class DefaultParameterContext implements ParameterContext {

    private List<ParameterDescriptor> parameterDescriptors = new ArrayList<ParameterDescriptor>();
    /**
     * 位置到参数名字的映射
     */
    private Map<Integer, String> positionToNameMap = new HashMap<Integer, String>();
    /**
     * 参数名到参数类型的映射
     */
    private Map<String, Type> nameToTypeMap = new HashMap<String, Type>();

    public DefaultParameterContext(List<ParameterDescriptor> parameterDescriptors) {
        this.parameterDescriptors = parameterDescriptors;
        for (int i=0; i<parameterDescriptors.size(); i++) {
            ParameterDescriptor parameterDescriptor = parameterDescriptors.get(i);
            String name = parameterDescriptor.getName();
            int position = parameterDescriptor.getPosition();
            Type type = parameterDescriptor.getType();

            positionToNameMap.put(position, name);
            nameToTypeMap.put(name, type);
        }
    }

    public static DefaultParameterContext create(List<ParameterDescriptor> parameterDescriptors) {
        return new DefaultParameterContext(parameterDescriptors);
    }

    public List<ParameterDescriptor> getParameterDescriptors() {
        return parameterDescriptors;
    }

    public String getParameterNameByPosition(int position) {
        return positionToNameMap.get(position);
    }
}
