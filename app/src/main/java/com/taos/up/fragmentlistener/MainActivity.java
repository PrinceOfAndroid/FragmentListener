package com.taos.up.fragmentlistener;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.taos.up.fragmentlistener.base.BaseActivity;
import com.taos.up.fragmentlistener.base.BaseFragment;
import com.taos.up.fragmentlistener.struct.FunctionNoParamNoResult;
import com.taos.up.fragmentlistener.struct.FunctionNoParamWithResult;
import com.taos.up.fragmentlistener.struct.FunctionWithParamNoResult;
import com.taos.up.fragmentlistener.struct.FunctionWithParamWithResult;
import com.taos.up.fragmentlistener.struct.FunctionsManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> fragments;
    private String[] titles = {"1", "2", "3", "4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout) findViewById(R.id.tab_fm);
        viewPager = (ViewPager) findViewById(R.id.vp_fm);

        fragments = new ArrayList<>();
        fragments.add(new FragmentListenerTest1());
        fragments.add(new FragmentListenerTest2());
        fragments.add(new FragmentListenerTest3());
        fragments.add(new FragmentListenerTest4());

        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
    }

    /**
     * 获取functionsManager实例，添加监听
     */
    @Override
    public FunctionsManager setFunctionsManager() {

        FunctionsManager functionsManager = FunctionsManager.getInstance();

        /**
         * 无参无返回值的通讯
         * FragmentListenerTest1
         */
        functionsManager.addFunction(new FunctionNoParamNoResult(FragmentListenerTest1.INTERFACE) {
            @Override
            public void function() {
                Toast.makeText(MainActivity.this, "来自activity的toast", Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * 无参有返回值的通讯
         * FragmentListenerTest2
         */
        functionsManager.addFunction(new FunctionNoParamWithResult<String>(FragmentListenerTest2.INTERFACE) {
            @Override
            public String function() {
                return "来自MainActivity 传递的参数";
            }
        });

        /**
         * 有参无返回值的通讯
         * FragmentListenerTest3
         */
        functionsManager.addFunction(new FunctionWithParamNoResult<ParamBean>(FragmentListenerTest3.INTERFACE) {
            @Override
            public void function(ParamBean o) {
                Toast.makeText(MainActivity.this, "来自activity的Toast: 这是fragment传递的数据：" + o.getMsg(), Toast.LENGTH_SHORT).show();
            }
        });


        /**
         * 有参有返回值的通讯
         * FragmentListenerTest4
         * ParamBean  参数类型
         * ResultBean 返回值类型
         */
        functionsManager.addFunction(new FunctionWithParamWithResult<ParamBean, ResultBean>(FragmentListenerTest4.INTERFACE) {
            @Override
            public ResultBean function(ParamBean o) {
                ResultBean r = new ResultBean(o.getMsg(), 2);
                return r;
            }
        });


        return functionsManager;
    }


    public class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
