package mobile.s.todogoals.base;


public interface BasePresenter<V extends BaseView> {
    void setView(V view);
}
