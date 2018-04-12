package com.taos.up.fragmentlistener.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import com.taos.up.fragmentlistener.struct.FunctionsManager;

/**
 * Created by PrinceOfAndroid on 2018/4/12 0012.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private FunctionsManager functionsManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.functionsManager = setFunctionsManager();
    }

    public abstract FunctionsManager setFunctionsManager();


    /**
     * 为fragment设置回调管理者
     * 在此之前往functionsManager中添加function
     * @param tag fragment的唯一标识
     */
    public void setFunctionsForFragment(String tag) {
        FragmentManager fm = getSupportFragmentManager();
        BaseFragment fragment = (BaseFragment) fm.findFragmentByTag(tag);
        if (functionsManager != null) {
            fragment.setFunctionsManager(functionsManager);
        }
    }
}
