package mobile.s.todogoals.home.presenter;

import android.webkit.ValueCallback;

import java.util.List;

import mobile.s.todogoals.base.BasePresenter;
import mobile.s.todogoals.database.ToDoModelDB;

public interface HomePresenter extends BasePresenter {

    void deleteToDoItem(int todoId, ValueCallback<Boolean> callback);

    void getComingToDoList(ValueCallback<List<ToDoModelDB>> listValueCallback);

    void getFinishedToDoList(ValueCallback<List<ToDoModelDB>> listValueCallback);

    void updateToDoState(int id, Boolean isDone, ValueCallback<Boolean> callback);

    void getUnDoneToDoCount(ValueCallback<Integer> callback);

    void onStopObserver();

}
