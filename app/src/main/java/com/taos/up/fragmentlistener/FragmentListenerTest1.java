package com.taos.up.fragmentlistener;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.taos.up.fragmentlistener.base.BaseFragment;

/**
 * Created by PrinceOfAndroid on 2018/4/12 0012.
 */

public class FragmentListenerTest1 extends BaseFragment {

    public static final String INTERFACE = FragmentListenerTest1.class.getName() + "NPNR"; //定义唯一的key
    private Button btnListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listener_test, null);
        initView(view);

        return view;
    }

    private void initView(View view) {
        btnListener = view.findViewById(R.id.btn_listener);
        btnListener.setText(INTERFACE);
        btnListener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 无参无返回值通讯
                 */
                functionsManager.invokeFunction(INTERFACE);
            }
        });

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
