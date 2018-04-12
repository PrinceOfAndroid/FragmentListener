package com.taos.up.fragmentlistener.struct;

/**
 * Created by PrinceOfAndroid on 2018/4/12 0012.
 * 没有参数也没有返回值
 */

public abstract class FunctionNoParamNoResult extends Function {

    public FunctionNoParamNoResult(String mFunctionName) {
        super(mFunctionName);
    }

    public abstract void function();
}
