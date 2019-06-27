package mobile.s.todogoals.home.views.interfaces;

import mobile.s.todogoals.base.BaseView;
import mobile.s.todogoals.utils.widget.ToDoTextView;

public interface AddingToDoFragmentInterface extends BaseView {
    void setToDoDate(ToDoTextView toDoTextView);
    void navigateToHomeFragment();
}
