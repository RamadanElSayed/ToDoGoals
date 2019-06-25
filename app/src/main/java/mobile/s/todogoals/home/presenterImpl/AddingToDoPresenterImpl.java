package mobile.s.todogoals.home.presenterImpl;

import android.content.Context;

import javax.inject.Inject;

import mobile.s.todogoals.base.BaseView;
import mobile.s.todogoals.home.presenter.AddingToDoPresenter;
import mobile.s.todogoals.home.views.activities.MainActivity;

public class AddingToDoPresenterImpl implements AddingToDoPresenter {
    Context context;

    @Inject
    public AddingToDoPresenterImpl(MainActivity context) {
        this.context = context;
    }

    @Override
    public void setView(BaseView view) {

    }
}
