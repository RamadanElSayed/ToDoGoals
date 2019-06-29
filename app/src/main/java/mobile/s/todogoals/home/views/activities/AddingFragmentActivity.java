package mobile.s.todogoals.home.views.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import mobile.s.todogoals.R;
import mobile.s.todogoals.application.BaseApplication;
import mobile.s.todogoals.base.BaseActivity;
import mobile.s.todogoals.home.component.AddingToDoComponent;
import mobile.s.todogoals.home.component.DaggerAddingToDoComponent;
import mobile.s.todogoals.home.modules.AddingToDoModule;
import mobile.s.todogoals.home.views.fragments.AddingNewToDoFragment;

public class AddingFragmentActivity extends BaseActivity {
    AddingToDoComponent addingToDoComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_fragment);
        addingToDoComponent = DaggerAddingToDoComponent.builder().addingToDoModule(new AddingToDoModule(this)).
                applicationComponent(((BaseApplication) getApplicationContext()).getComponent()).build();
        addingToDoComponent.injectAddingToDoActivity(this);
        AddingNewToDoFragment addingNewToDoFragment = AddingNewToDoFragment.getInstance();
        addFragment(addingNewToDoFragment);
    }

    public AddingToDoComponent getAddingToDoComponent() {
        return addingToDoComponent;
    }

    public static Intent getLaunchIntent(Context context) {
        return new Intent(context, AddingFragmentActivity.class);
    }
}
