package mobile.s.todogoals.home.presenter;

import android.webkit.ValueCallback;

import java.util.List;

import mobile.s.todogoals.base.BasePresenter;
import mobile.s.todogoals.database.ToDoModelDB;

public interface AddingToDoPresenter extends BasePresenter {
    void saveToDoItem(ToDoModelDB toDoModelDB, ValueCallback<Boolean> callback);
    void onStopObserver();
}
