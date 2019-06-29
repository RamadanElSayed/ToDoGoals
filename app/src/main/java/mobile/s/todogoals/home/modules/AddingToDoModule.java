package mobile.s.todogoals.home.modules;

import dagger.Module;
import dagger.Provides;
import mobile.s.todogoals.home.AddingActivityScope;
import mobile.s.todogoals.home.HomeActivityScope;
import mobile.s.todogoals.home.views.activities.AddingFragmentActivity;

@Module
public class AddingToDoModule {

    AddingFragmentActivity addingFragmentActivity;

    public AddingToDoModule(AddingFragmentActivity addingFragmentActivity1) {
        this.addingFragmentActivity = addingFragmentActivity1;
    }

    @Provides
    @AddingActivityScope
    AddingFragmentActivity getAddingToDoActivity() {
        return addingFragmentActivity;
    }

}
