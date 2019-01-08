# SKStructure
基于MVP的Android基础架构

一、基本写法  
  1、定义view层  
    setp1、定义IView。  
    setp2、定义View。继承SKActivity<P extends SKIPre>，通过指定泛型注入Presenter。  
    interface IJokeListActivity {  
      void showJokeList(List<JokeData.JokeBean> jokeList);  
    }  
    public class JokeListActivity extends SKActivity<IJokeListPre> implements IJokeListActivity{}
    同样，Fragment也是一样。
  2、定义Presenter层
    step1、定义IPresenter。
    step2、定义Presenter。继承SKPre<U>，通过泛型注入IView。
    step3、给IPresenter添加注解Impl指定IPresenter的实现类Presenter，从而建立关联。
    @Impl(JokeListPre.class)
    public interface IJokeListPre extends SKIPre {
        @Background(BackgroundType.HTTP)
        void loadJokeList();
    }
    class JokeListPre extends SKPre<IJokeListActivity> implements IJokeListPre {}
  3、view调用presenter
    在Activity中，直接使用方法pre()来调用方法。
    pre().loadJokeList();
  4、presenter调用view
    在Presenter中，直接调用方法ui()来调用方法。
    ui().showJokeList(jokeDataResponse.getData());
