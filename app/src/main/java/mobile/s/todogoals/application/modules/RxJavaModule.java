package mobile.s.todogoals.application.modules;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import mobile.s.todogoals.application.ApplicationScope;


@Module
public class RxJavaModule {

    @ApplicationScope
    @Provides
    CompositeDisposable boxForSubscriperRx(){
        return  new CompositeDisposable();
    }

}
