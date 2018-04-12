package com.taos.up.fragmentlistener.struct;

import android.text.TextUtils;

import java.util.HashMap;

/**
 * Created by PrinceOfAndroid on 2018/4/12 0012.
 */

public class FunctionsManager {
    private static FunctionsManager instance = null;

    public FunctionsManager() {
        functionNoParamNoResultHashMap = new HashMap<>();
        functionNoParamWithResultHashMap = new HashMap<>();
        functionWithParamNoResultHashMap = new HashMap<>();
        functionWithParamWithResultHashMap = new HashMap<>();
    }

    /**
     * 单例模式
     *
     * @return
     */
    public static FunctionsManager getInstance() {
        if (instance == null) {
            instance = new FunctionsManager();
        }
        return instance;
    }


    /**
     * 定义容器保存接口
     */

    private HashMap<String, FunctionNoParamNoResult> functionNoParamNoResultHashMap;
    private HashMap<String, FunctionNoParamWithResult> functionNoParamWithResultHashMap;
    private HashMap<String, FunctionWithParamNoResult> functionWithParamNoResultHashMap;
    private HashMap<String, FunctionWithParamWithResult> functionWithParamWithResultHashMap;


    /**
     * 无参无返回值的回调
     */


    //添加
    public FunctionsManager addFunction(FunctionNoParamNoResult function) {
        functionNoParamNoResultHashMap.put(function.mFunctionName, function);
        return this;
    }

    //加载
    public void invokeFunction(String functionName) {
        if (TextUtils.isEmpty(functionName) == true) {
            return;
        }
        if (functionNoParamNoResultHashMap != null) {
            FunctionNoParamNoResult f = functionNoParamNoResultHashMap.get(functionName);
            if (f != null) {
                f.function();
            } else {
                try {
                    throw new FunctionException("has no this Function " + functionName);
                } catch (FunctionException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 无参有返回值的回调
     */

    public FunctionsManager addFunction(FunctionNoParamWithResult function) {
        functionNoParamWithResultHashMap.put(function.mFunctionName, function);
        return this;
    }


    /**
     * 加载无参有返回值的方法
     *
     * @param functionName 名称定义(也就是map中的key值)
     * @param resultClass  返回结果定义
     * @param <Result>     返回结果
     * @return
     */
    public <Result> Result invokeFunction(String functionName, Class<Result> resultClass) {
        if (TextUtils.isEmpty(functionName) == true) {
            return null;
        }
        if (functionNoParamWithResultHashMap != null) {
            FunctionNoParamWithResult f = functionNoParamWithResultHashMap.get(functionName);
            if (f != null) {
                if (resultClass != null) {
                    return resultClass.cast(f.function());
                } else {
                    return (Result) f.function();
                }
            } else {
                try {
                    throw new FunctionException("has no this Function " + functionName);
                } catch (FunctionException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }



    /**
     * 有参无返回值的回调
     */


    //添加
    public FunctionsManager addFunction(FunctionWithParamNoResult function) {
        functionWithParamNoResultHashMap.put(function.mFunctionName, function);
        return this;
    }

    /**
     * @param functionName
     * @param param        传递的参数
     * @param <Param>
     */
    public <Param> void invokeFunction(String functionName, Param param) {
        if (TextUtils.isEmpty(functionName) == true) {
            return;
        }
        if (functionWithParamNoResultHashMap != null) {
            FunctionWithParamNoResult f = functionWithParamNoResultHashMap.get(functionName);
            if (f != null) {
                f.function(param);
            } else {
                try {
                    throw new FunctionException("has no this Function " + functionName);
                } catch (FunctionException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 有参有返回值的回调
     */


    //添加
    public FunctionsManager addFunction(FunctionWithParamWithResult function) {
        functionWithParamWithResultHashMap.put(function.mFunctionName, function);
        return this;
    }

    //加载
    public <Result, Param> Result invokeFunction(String functionName, Param param, Class<Result> resultClass) {
        if (TextUtils.isEmpty(functionName) == true) {
            return null;
        }
        if (functionWithParamWithResultHashMap != null) {
            FunctionWithParamWithResult f = functionWithParamWithResultHashMap.get(functionName);
            if (f != null) {
                if (resultClass != null) {
                    return resultClass.cast(f.function(param));
                } else {
                    return null;
                }
            } else {
                try {
                    throw new FunctionException("has no this Function " + functionName);
                } catch (FunctionException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

}
