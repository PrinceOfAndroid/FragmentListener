package com.taos.up.fragmentlistener.base;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.taos.up.fragmentlistener.MainActivity;
import com.taos.up.fragmentlistener.struct.FunctionsManager;

/**
 * Created by PrinceOfAndroid on 2018/4/12 0012.
 */

public class BaseFragment extends Fragment {

    protected FunctionsManager functionsManager;
    private BaseActivity mBaseActivity;

    public void setFunctionsManager(FunctionsManager functionsManager) {
        this.functionsManager = functionsManager;
    }

    /**
     * 生命周期
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity){
            mBaseActivity= (BaseActivity) context;
            mBaseActivity.setFunctionsForFragment(getTag());
        }
    }
}
