package com.yn.mango.binding;

import com.yn.mango.descriptor.ParameterDescriptor;

import java.util.List;

/**
 * Created by yangnan on 16/11/8.
 */
public interface ParameterContext {
    /**
     * 获取参数描述
     *
     * @return
     */
    List<ParameterDescriptor> getParameterDescriptors();

    /**
     * 根据参数位置获得参数名
     */
    String getParameterNameByPosition(int position);
}
