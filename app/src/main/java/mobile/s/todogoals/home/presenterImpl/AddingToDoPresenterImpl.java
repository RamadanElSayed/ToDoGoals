package mobile.s.todogoals.home.presenterImpl;
import android.webkit.ValueCallback;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mobile.s.todogoals.base.BaseView;
import mobile.s.todogoals.database.ToDoDao;
import mobile.s.todogoals.database.ToDoModelDB;
import mobile.s.todogoals.home.presenter.AddingToDoPresenter;
import mobile.s.todogoals.home.views.activities.MainActivity;
import mobile.s.todogoals.home.views.interfaces.AddingToDoFragmentInterface;

public class AddingToDoPresenterImpl implements AddingToDoPresenter {

    private MainActivity context;
    private CompositeDisposable compositeDisposable;
    private ToDoDao todoDatabaseQueries;
    private AddingToDoFragmentInterface addingToDoFragmentInterface;
    @Inject
    public AddingToDoPresenterImpl(MainActivity context, ToDoDao toDoDao, CompositeDisposable compositeDisposable1) {
        this.context = context;
        this.todoDatabaseQueries=toDoDao;
        this.compositeDisposable = compositeDisposable1;
    }

    @Override
    public void setView(BaseView view) {
        addingToDoFragmentInterface = (AddingToDoFragmentInterface) view;
    }


    @Override
    public void saveToDoItem(ToDoModelDB toDoModelDB, ValueCallback<Boolean> callback) {


        Flowable<Boolean> observableSaveSession=Flowable.fromCallable(() -> {
            todoDatabaseQueries.insertAzkarData(toDoModelDB);
            return true;
        });

        Disposable observerSaveSession=observableSaveSession.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(aBoolean -> {
                    if(aBoolean!=null)
                    {
                        callback.onReceiveValue(aBoolean);
                    }

                });

        compositeDisposable.add(observerSaveSession);

    }

    @Override
    public void deleteToDoItem(int todoId, ValueCallback<Boolean> callback) {


        Flowable<Boolean>observableDeleteSession=Flowable.fromCallable(() -> {
            todoDatabaseQueries.deleteToDoById(todoId);
            return true;
        });


        Disposable observerDeleteSession=observableDeleteSession.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(aBoolean -> {
                    if(aBoolean!=null)
                    {
                        callback.onReceiveValue(aBoolean);
                    }

                });

        compositeDisposable.add(observerDeleteSession);
    }

    @Override
    public void getToDoList(ValueCallback<List<ToDoModelDB>> listValueCallback) {

        Flowable<List<ToDoModelDB>> observableGetSessions=todoDatabaseQueries.getAllToDoList();


        Disposable observerGetSessions=observableGetSessions.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(dbKeepingSessions -> {
                    if(dbKeepingSessions!=null)
                    {
                        listValueCallback.onReceiveValue(dbKeepingSessions);
                    }
                });
        compositeDisposable.add(observerGetSessions);
    }

    @Override
    public void updateToDoState(int id, Boolean isDone, ValueCallback<Boolean> callback) {

        Flowable<Boolean>observableUpdateSession=Flowable.fromCallable(() -> {
            todoDatabaseQueries.updateDutyState(id,isDone);
            return true;
        });


        Disposable observerUpdateSession=observableUpdateSession.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(aBoolean -> {
                    if(aBoolean!=null)
                    {
                        callback.onReceiveValue(aBoolean);
                    }

                });

        compositeDisposable.add(observerUpdateSession);
    }

    @Override
    public void getUnDoneToDoCount(ValueCallback<Integer> callback) {

        Flowable<Integer> unDoneToDoCountObservable = todoDatabaseQueries.getUnDoneToDoCount();
        Disposable observerUnDoneToDo = unDoneToDoCountObservable.
                subscribeOn(io.reactivex.schedulers.Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(callback::onReceiveValue);

        compositeDisposable.add(observerUnDoneToDo);
    }


    @Override
    public void onStopObserver() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
            compositeDisposable.clear();
        }
    }
}
