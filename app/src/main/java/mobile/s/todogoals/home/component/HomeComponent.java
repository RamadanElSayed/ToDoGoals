package mobile.s.todogoals.home.component;

import dagger.Component;
import mobile.s.todogoals.application.component.ApplicationComponent;
import mobile.s.todogoals.home.HomeActivityScope;
import mobile.s.todogoals.home.modules.HomeModule;
import mobile.s.todogoals.home.views.activities.MainActivity;
import mobile.s.todogoals.home.views.fragments.AddingNewToDoFragment;
import mobile.s.todogoals.home.views.fragments.ToDoListFragment;

@HomeActivityScope
@Component(modules = {HomeModule.class}, dependencies = {ApplicationComponent.class})
public interface HomeComponent {

    void injectHomeActivity(MainActivity activity);

    void inject(ToDoListFragment toDoListFragment);

    void inject(AddingNewToDoFragment addingNewToDoFragment);

}
