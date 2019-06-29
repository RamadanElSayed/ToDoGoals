package mobile.s.todogoals.home.component;

import dagger.Component;
import mobile.s.todogoals.application.component.ApplicationComponent;
import mobile.s.todogoals.home.AddingActivityScope;
import mobile.s.todogoals.home.HomeActivityScope;
import mobile.s.todogoals.home.modules.AddingToDoModule;
import mobile.s.todogoals.home.views.activities.AddingFragmentActivity;
import mobile.s.todogoals.home.views.fragments.AddingNewToDoFragment;
@AddingActivityScope
@Component(modules = {AddingToDoModule.class}, dependencies = {ApplicationComponent.class})

public interface AddingToDoComponent {
    void injectAddingToDoActivity(AddingFragmentActivity activity);

//    void inject(AddingNewToDoFragment addingNewToDoFragment);
}
