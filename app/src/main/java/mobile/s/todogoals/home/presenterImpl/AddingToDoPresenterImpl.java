package mobile.s.todogoals.home.presenterImpl;
import android.webkit.ValueCallback;
import java.util.List;
import javax.inject.Inject;

import io.reactivex.Completable;
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

    @Inject
    public AddingToDoPresenterImpl(MainActivity context, ToDoDao toDoDao, CompositeDisposable compositeDisposable1) {
        this.context = context;
        this.todoDatabaseQueries=toDoDao;
        this.compositeDisposable = compositeDisposable1;
    }

    @Override
    public void setView(BaseView view) {
        AddingToDoFragmentInterface addingToDoFragmentInterface = (AddingToDoFragmentInterface) view;
    }


    @Override
    public void saveToDoItem(ToDoModelDB toDoModelDB, ValueCallback<Boolean> callback) {


//        Flowable<Boolean> observableSaveSession=Flowable.fromCallable(() -> {
//            todoDatabaseQueries.insertToDoData(toDoModelDB);
//            return true;
//        });
//
//        Disposable observerSaveSession=observableSaveSession.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
//                subscribe(aBoolean -> {
//                    if(aBoolean!=null)
//                    {
//                        callback.onReceiveValue(aBoolean);
//                    }
//
//                });
//
//        compositeDisposable.add(observerSaveSession);

        Completable completable = Completable.fromAction(() -> todoDatabaseQueries.insertToDoData(toDoModelDB));

        Disposable disposable = completable.subscribeOn(io.reactivex.schedulers.Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(() -> {
                    callback.onReceiveValue(true);
                });

        compositeDisposable.add(disposable);

    }



    @Override
    public void onStopObserver() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
            compositeDisposable.clear();
        }
    }
}
