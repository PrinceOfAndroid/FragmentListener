package com.taos.up.fragmentlistener.struct;

/**
 * Created by PrinceOfAndroid on 2018/4/12 0012.
 * 有返回值也有参数的接口
 */

public abstract class FunctionWithParamWithResult<Param, Result> extends Function {

    public FunctionWithParamWithResult(String mFunctionName) {
        super(mFunctionName);
    }

    public abstract Result function(Param param);
}
