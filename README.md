# FragmentListener
万能Fragment activity通讯接口

### 如何使用：
* 在BaseActivity和BaseFragment添加方法：
``` java
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
```

* 在activity中添加监听：
``` java
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
```
* 在Fragment定义唯一名称，并进行加载接口
``` java
  public static final String INTERFACE = FragmentListenerTest1.class.getName() + "NPNR"; //定义唯一的key
  
                /**
                 * 无参无返回值通讯
                 */
                functionsManager.invokeFunction(INTERFACE);
                
                /**
                 * 无参有返回值通讯
                 */
                String msg = functionsManager.invokeFunction(INTERFACE, String.class);
                Toast.makeText(getContext(), "FragmentListenerTest2 接收: " + msg, Toast.LENGTH_SHORT).show();

                /**
                 * 有参无返回值通讯
                 */
                ParamBean paramBean = new ParamBean("来自fragment的bean", 1);
                functionsManager.invokeFunction(INTERFACE, paramBean);
                
                /**
                 * 有参有返回值通讯
                 */
                ParamBean paramBean = new ParamBean("来自fragment的bean", 1);
                //activity 返回的参数
                ResultBean r = functionsManager.invokeFunction(INTERFACE, paramBean, ResultBean.class);
                Toast.makeText(getContext(), "activity返回对象" + r.toString(), Toast.LENGTH_SHORT).show(); 
```
