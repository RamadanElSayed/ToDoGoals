package mobile.s.todogoals.application.component;

import android.content.SharedPreferences;

import dagger.Component;
import io.reactivex.disposables.CompositeDisposable;
import mobile.s.todogoals.application.ApplicationScope;
import mobile.s.todogoals.application.modules.LocalDatabaseModule;
import mobile.s.todogoals.application.modules.PreferenceModule;
import mobile.s.todogoals.application.modules.RxJavaModule;
import mobile.s.todogoals.database.ToDoDao;


@ApplicationScope
@Component(modules={ LocalDatabaseModule.class, PreferenceModule.class, RxJavaModule.class })
public interface ApplicationComponent {

    CompositeDisposable getParentRX();

    SharedPreferences getPrefernce();

    ToDoDao getToDoDao();

}
