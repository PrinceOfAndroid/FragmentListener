package com.taos.up.fragmentlistener.struct;

/**
 * Created by PrinceOfAndroid on 2018/4/12 0012.
 * 只有参数，没有返回值
 */

public abstract class FunctionWithParamNoResult<Param> extends Function {

    public FunctionWithParamNoResult(String mFunctionName) {
        super(mFunctionName);
    }

    public abstract void function (Param param);
}
