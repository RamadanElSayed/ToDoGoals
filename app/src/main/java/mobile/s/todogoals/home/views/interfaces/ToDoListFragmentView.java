package mobile.s.todogoals.home.views.interfaces;

import java.util.List;

import mobile.s.todogoals.base.BaseView;
import mobile.s.todogoals.database.ToDoModelDB;

public interface ToDoListFragmentView extends BaseView {
    void onCheckBoxClicked(ToDoModelDB toDoModelDB);
    void prepareComingRecycler(List<ToDoModelDB> value);
    void prepareFinishedRecycler(List<ToDoModelDB> value);
    void showRemovingToDoDialog();
    void dismissRemovingToDoDialog();
    void initialDialogData(ToDoModelDB toDoModelDB,boolean isComeToDo);

}
