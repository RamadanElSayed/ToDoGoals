package mobile.s.todogoals.home.presenterImpl;

import android.content.Context;

import javax.inject.Inject;

import mobile.s.todogoals.base.BaseView;
import mobile.s.todogoals.home.presenter.HomePresenter;
import mobile.s.todogoals.home.views.activities.MainActivity;

public class HomePresenterImpl implements HomePresenter {
    Context context;

    @Override
    public void setView(BaseView view) {

    }

    @Inject
    public HomePresenterImpl(MainActivity context) {
        this.context = context;
    }
}
