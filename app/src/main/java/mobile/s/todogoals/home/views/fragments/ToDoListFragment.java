package mobile.s.todogoals.home.views.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.webkit.ValueCallback;
import android.widget.Button;

import com.daimajia.swipe.util.Attributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import mobile.s.todogoals.R;
import mobile.s.todogoals.base.BaseFragment;
import mobile.s.todogoals.base.OnItemClickListener;
import mobile.s.todogoals.database.ToDoModelDB;
import mobile.s.todogoals.home.presenterImpl.AddingToDoPresenterImpl;
import mobile.s.todogoals.home.presenterImpl.HomePresenterImpl;
import mobile.s.todogoals.home.views.activities.MainActivity;
import mobile.s.todogoals.home.views.adapters.AdapterComingToDo;
import mobile.s.todogoals.home.views.adapters.AdapterFinishedToDo;
import mobile.s.todogoals.home.views.interfaces.ToDoListFragmentView;
import mobile.s.todogoals.utils.Messenger;

public class ToDoListFragment extends BaseFragment implements ToDoListFragmentView, OnItemClickListener<ToDoModelDB>, AdapterFinishedToDo.FinishToDoActionsListener {

    @BindView(R.id.list_coming_todo_rv)
    RecyclerView comingToDoRv;

    @BindView(R.id.list_finish_todo_rv)
    RecyclerView finishedToDoRv;

    @Inject
    public HomePresenterImpl homePresenter;
    @Inject
    public ToDoModelDB toDoModelDB;
    @Inject
    public AdapterFinishedToDo adapterFinishedToDo;
    @Inject
    public AdapterComingToDo adapterComingToDo;
    private List<ToDoModelDB> toDoComingModelDBList=new ArrayList<>();
    private List<ToDoModelDB> toDoFinishedModelDBArrayList=new ArrayList<>();
    private SweetAlertDialog removeToDoItemSweetAlert;
    int[] animationList = {R.anim.layout_animation_up_to_down, R.anim.layout_animation_right_to_left, R.anim.layout_animation_down_to_up, R.anim.layout_animation_left_to_right};

    public static ToDoListFragment getInstance() {
        return new ToDoListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity) Objects.requireNonNull(getActivity())).getHomeComponent().inject(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_to_do_list, container, false);
        ButterKnife.bind(this, view);
        homePresenter.getComingToDoList(this::prepareComingRecycler);
        homePresenter.getFinishedToDoList(this::prepareFinishedRecycler);
        return view;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initComponents() {
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onCheckBoxClicked(ToDoModelDB toDoModelDB) {

        homePresenter.updateToDoState(toDoModelDB.getTodoId(), true, value -> {
            toDoComingModelDBList.remove(toDoModelDB);
            toDoFinishedModelDBArrayList.add(toDoModelDB);
            adapterComingToDo.updateChatList(toDoComingModelDBList);
            adapterFinishedToDo.updateChatList(toDoFinishedModelDBArrayList);
        });
    }

    @Override
    public void prepareComingRecycler(List<ToDoModelDB> value) {
        if(value.size()>0)
        {
        this.toDoComingModelDBList = value;
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        comingToDoRv.setLayoutManager(mLayoutManager);
        comingToDoRv.setItemAnimator(new DefaultItemAnimator());
        comingToDoRv.setAdapter(adapterComingToDo);
        adapterComingToDo.setToDoListFragmentView(this);
        adapterComingToDo.notifyDataSetChanged();
        Random rand = new Random();
        int randomNum = rand.nextInt((3 - 1) + 1) + 1;
        LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(getContext(), animationList[randomNum]);
        comingToDoRv.setLayoutAnimation(controller);
        comingToDoRv.scheduleLayoutAnimation();
        adapterComingToDo.setOnItemClickListener(this);
        adapterComingToDo.updateChatList(value);
        adapterComingToDo.notifyDataSetChanged();}
    }

    @Override
    public void prepareFinishedRecycler(List<ToDoModelDB> value) {
        if(value.size()>0){
         this.toDoFinishedModelDBArrayList = value;
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        finishedToDoRv.setLayoutManager(mLayoutManager);
        finishedToDoRv.setItemAnimator(new DefaultItemAnimator());
        finishedToDoRv.setAdapter(adapterFinishedToDo);
        Random rand = new Random();
        int randomNum = rand.nextInt((3 - 1) + 1) + 1;
        LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(getContext(), animationList[randomNum]);
        finishedToDoRv.setLayoutAnimation(controller);
        finishedToDoRv.scheduleLayoutAnimation();
        adapterFinishedToDo.setOnItemClickListener(this);
        adapterFinishedToDo.updateChatList(value);
        adapterFinishedToDo.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(ToDoModelDB toDoModelDB) {
        this.toDoModelDB = toDoModelDB;
        initialDialogData(this.toDoModelDB, true);
        showRemovingToDoDialog();
    }

    @Override
    public void showRemovingToDoDialog() {
        if (removeToDoItemSweetAlert != null) {
            removeToDoItemSweetAlert.show();
            Button keep = removeToDoItemSweetAlert.findViewById(R.id.confirm_button);
            Button end = removeToDoItemSweetAlert.findViewById(R.id.cancel_button);
            keep.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.white));
            keep.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.round_red_bg));
            end.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.white));
            end.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.round_red_bg));
        }
    }

    @Override
    public void dismissRemovingToDoDialog() {
        if (removeToDoItemSweetAlert != null) {
            removeToDoItemSweetAlert.dismissWithAnimation();
        }
    }

    @Override
    public void initialDialogData(ToDoModelDB toDoModelDB, boolean isComeToDo) {
        String title = getString(R.string.remove_todo_title);
        String messageContent = getString(R.string.remove_todo_content);
        String acceptBtnText = getString(R.string.remove);

        String endBtnText = getString(R.string.cancel);

        removeToDoItemSweetAlert = Messenger.removeAzkarSweetDialog(getContext(), title, messageContent,
                acceptBtnText, endBtnText, true, sweetAlertDialog -> homePresenter.deleteToDoItem(toDoModelDB.getTodoId(), value -> {
                    if (value) {
                        if (isComeToDo) {
                            toDoComingModelDBList.remove(toDoModelDB);
                            dismissRemovingToDoDialog();
                            adapterComingToDo.updateChatList(toDoComingModelDBList);
                        } else {
                            toDoFinishedModelDBArrayList.remove(toDoModelDB);
                            dismissRemovingToDoDialog();
                            adapterFinishedToDo.updateChatList(toDoFinishedModelDBArrayList);
                        }
                    }
                }), sweetAlertDialog -> dismissRemovingToDoDialog());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        homePresenter.onStopObserver();
    }

    @Override
    public void onStop() {
        super.onStop();
        homePresenter.onStopObserver();
    }

    @Override
    public void onFinishToDoClicked(ToDoModelDB toDoModelDB) {
        this.toDoModelDB = toDoModelDB;
        initialDialogData(this.toDoModelDB, false);
        showRemovingToDoDialog();
    }
}
