package com.taos.up.fragmentlistener.struct;

/**
 * Created by PrinceOfAndroid on 2018/4/12 0012.
 * 没有参数有返回值
 */

public abstract class FunctionNoParamWithResult<Result> extends Function {

    public FunctionNoParamWithResult(String mFunctionName) {
        super(mFunctionName);
    }

    public abstract Result function();
}
