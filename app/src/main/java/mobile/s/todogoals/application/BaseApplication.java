package mobile.s.todogoals.application;
import android.app.Application;
import android.content.Context;
import mobile.s.todogoals.application.component.ApplicationComponent;
import mobile.s.todogoals.application.component.DaggerApplicationComponent;
import mobile.s.todogoals.application.modules.ContextModule;
import mobile.s.todogoals.database.ToDoDao;
import mobile.s.todogoals.database.ToDoDataBase;

public class BaseApplication extends Application {

    private static ToDoDao azkarDao;
    private static ToDoDataBase toDoDataBase;
    private static BaseApplication mInstance;
    private ApplicationComponent component;
    @Override
    public void onCreate() {
        super.onCreate();
       // setRoamingData();
        mInstance = this;
        component = DaggerApplicationComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }

    public ApplicationComponent getComponent() {
        return component;
    }


    public static synchronized BaseApplication getInstance() {
        BaseApplication baseApplication;
        synchronized (BaseApplication.class) {
            baseApplication = mInstance;
        }
        return baseApplication;
    }

    public static Context getAppContext() {
        return mInstance.getApplicationContext();
    }



//    private void setRoamingData() {
//        toDoDataBase = Room.databaseBuilder(getApplicationContext(),
//                ToDoDataBase.class, "todolist_db").build();
//
//        azkarDao = toDoDataBase.toDoDao();
//    }
//
//    public static ToDoDao getToDoDoa() {
//        if (azkarDao != null)
//            return azkarDao;
//        else {
//            azkarDao = toDoDataBase.toDoDao();
//            return azkarDao;
//        }
//    }


}
