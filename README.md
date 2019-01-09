# SKStructure
### 一、基本写法
  #### 1、定义Application  
    自定义Application继承SKApplication  
  #### 2、定义view层  
    setp1、定义IView。  
    setp2、定义View。继承SKActivity<P extends SKIPre>，通过指定泛型注入Presenter。  
    
    interface IJokeListActivity {  
        void showJokeList(List<JokeData.JokeBean> jokeList);  
    }  
    public class JokeListActivity extends SKActivity<IJokeListPre> implements IJokeListActivity{}  
    
    同样，Fragment也是一样。  
  #### 3、定义Presenter层  
    step1、定义IPresenter。  
    step2、定义Presenter。继承SKPre<U>，通过泛型注入IView。  
    step3、给IPresenter添加注解@Impl指定IPresenter的实现类Presenter，从而建立关联。  
    
    @Impl(JokeListPre.class)  
    public interface IJokeListPre extends SKIPre {  
        @Background(BackgroundType.HTTP)  
        void loadJokeList();  
    }  
    class JokeListPre extends SKPre<IJokeListActivity> implements IJokeListPre {}  
  #### 4、view调用presenter  
    在Activity中，直接使用方法pre()来调用方法。  
    pre().loadJokeList();  
  #### 5、presenter调用view  
    在Presenter中，直接调用方法ui()来调用方法。  
    ui().showJokeList(jokeDataResponse.getData());  
### 二、presenter的同步和异步操作  
    在IPresenter申明方法的时候，添加注解@Background。  
    取值为Background下的SINGLEWORK：单线程同步操作  
                    HTTP：网络线程  
                    WORK：工作线程  
    例如demo中的     @Background(BackgroundType.HTTP)  
                    void loadJokeList();  
### 三、presenter中的防止连续调用  
    同样，在IPresenter申明方法的时候，添加注解@Repeat，取值为boolean型，默认为false。  
    这样，如果@Repeat(false)这个方法的方法体还没有执行完成是不会再次执行的。  
### 四、presenter中的拦截器  
    step1、自定义自己的startInterceptor继承SKPreMethodStartInterceptor和endInterceptor继承SKPreMethodEndInterceptor。  
    step2、在Application中，重写createMethodInterceptor，通过builder模式来创建SKMethodProxy。  
    step3、在IPresenter的方法中，添加@Interceptor注解，传入value。  
    那么，在这个方法执行的时候，如果interceptor不为空，value!=-1，就会回调interceptor的interceptor方法  
### 五、网络
    在presenter中请求网络数据，调用http方法，直接返回实体。例如在demo中    
    JokeData jokeDataResponse = http(ApiServices.class).loadJokeList(2, 1);
    解析：这里使用了Retrofit，因为有@Background来处理异步操作，因此去掉CallAdapter。通过修改Retrofit的源码，在create方法里边已经处理好了converter。  
    使用：step1、在自定义的Application中，重写createRetrofit方法，创建Retrofit，初始化参数。
         step2、调用http方法，传入ApiServices.class，调用方法。
### 六、跨作用域调用
    用于RecyclerView.holder的点击等业务处理、跨Activity通信、Fragment和Activity通信、甚至于任何地方来操作ui。
    用法：给pre()方法传入IPresenter.class。未提供默认pre(xxx.class)调用的地方，可使用SKHelper.pre(xxx.class,index)。
    例如demo中：JokeListHolder中
            R.id.forward -> {  
                Log.i("tag", "click forward")  
                SKHelper.pre(IJokeListPre::class.java, 0).forward(dataBean.soureid)  
            }  
            R.id.up -> {  
                SKHelper.pre(IJokeListPre::class.java, 0).up(dataBean.soureid)  
            }  
            R.id.down -> {  
                SKHelper.pre(IJokeListPre::class.java, 0).down(dataBean.soureid)  
            }  
### 七、Display层
    和显示、跳转相关的操作。比如dialog的显示，popWindow的显示，activity跳转、Fragment添加都交由Display层操作。
