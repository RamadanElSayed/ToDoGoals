package mobile.s.todogoals.home.modules;
import dagger.Module;
import dagger.Provides;
import mobile.s.todogoals.home.HomeActivityScope;
import mobile.s.todogoals.home.presenter.HomePresenterImpl;
import mobile.s.todogoals.home.views.activities.MainActivity;

@Module
public class HomeModule {
    MainActivity mainActivity;

    public HomeModule(MainActivity homeActivity) {
        this.mainActivity = homeActivity;
    }

    @Provides
    @HomeActivityScope
    MainActivity getMainActivity() {
        return mainActivity;
    }

    // m4 lazm t3ml provide ll presenterImpl
//    @Provides
//    @HomeActivityScope
//    HomePresenterImpl provideHomePresnter(MainActivity context) {
//        return new HomePresenterImpl(context);
//    }
}
