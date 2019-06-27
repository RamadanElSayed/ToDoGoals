package mobile.s.todogoals.home.views.fragments;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import java.util.Calendar;
import java.util.Objects;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mobile.s.todogoals.R;
import mobile.s.todogoals.base.BaseFragment;
import mobile.s.todogoals.database.ToDoModelDB;
import mobile.s.todogoals.home.presenterImpl.AddingToDoPresenterImpl;
import mobile.s.todogoals.home.views.activities.MainActivity;
import mobile.s.todogoals.home.views.interfaces.AddingToDoFragmentInterface;
import mobile.s.todogoals.utils.KeyboardUtil;
import mobile.s.todogoals.utils.Messenger;
import mobile.s.todogoals.utils.widget.ToDoTextView;


public class AddingNewToDoFragment extends BaseFragment implements AddingToDoFragmentInterface {

    @BindView(R.id.duty_title_edit_txt)
    EditText todoTitle;

    @BindView(R.id.duty_desc_edit_txt)
    EditText todoDescription;

    @BindView(R.id.duty_date_strat_txt)
    ToDoTextView todoStartDate;

    @BindView(R.id.duty_date_end_txt)
    ToDoTextView todoEndDate;
    @Inject
    AddingToDoPresenterImpl addingToDoPresenter;
    @Inject
    ToDoModelDB toDoModelDB;
    private Calendar calendar = Calendar.getInstance();
    private String datTime;
    private String am_pm = "";

    public static AddingNewToDoFragment getInstance() {
        return new AddingNewToDoFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity) Objects.requireNonNull(getActivity())).getHomeComponent().inject(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_adding_new_to_do, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initComponents() {

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @OnClick(R.id.duty_date_strat_txt)
    void onStartDateClicked() {
        setToDoDate(todoStartDate);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @OnClick(R.id.duty_date_end_txt)
    void onEndDateClicked() {
        setToDoDate(todoEndDate);
    }


    @OnClick(R.id.save_btn)
    void onSaveBtnClicked() {
        KeyboardUtil.dismissKeyboard(Objects.requireNonNull(getActivity()));

        String title = todoTitle.getText().toString();
        String description = todoDescription.getText().toString();
        String startDate = todoStartDate.getText().toString();
        String endDate = todoEndDate.getText().toString();

        if (TextUtils.isEmpty(title))
            Messenger.showErrorMsg(getString(R.string.title_todo), getActivity());
        else {
            //start adding to the room DataBase ...
            toDoModelDB.setTodoStartDate(startDate);
            toDoModelDB.setTodoEndDate(endDate);
            toDoModelDB.setTodoDesc(description);
            toDoModelDB.setTodoTitle(title);
            toDoModelDB.setTodoDone(false);
            addingToDoPresenter.saveToDoItem(toDoModelDB, value ->{
                Messenger.showSuccessMsg("done", getActivity());
                navigateToHomeFragment();
            }
            );

        }

    }

    @OnClick(R.id.cancel_btn)
    void onCancelBtnClicked() {
        navigateToHomeFragment();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void setToDoDate(final ToDoTextView toDoTextView) {
        DatePickerDialog.OnDateSetListener date = (datePicker, i, i1, i2) -> {
            calendar.set(Calendar.YEAR, i);
            calendar.set(Calendar.MONTH, i1);
            calendar.set(Calendar.DAY_OF_MONTH, i2);
            datTime = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH);

            TimePickerDialog.OnTimeSetListener timeSetListener = (view, hourOfDay, minute) -> {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                if (calendar.get(Calendar.AM_PM) == Calendar.AM)
                    am_pm = "AM";
                else if (calendar.get(Calendar.AM_PM) == Calendar.PM)
                    am_pm = "PM";
                datTime += " " + ":" + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + " " + am_pm;
                toDoTextView.setText(datTime);

            };
            new TimePickerDialog(getContext(), timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
        };
        new DatePickerDialog(Objects.requireNonNull(getContext()), date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();

    }

    @Override
    public void navigateToHomeFragment() {
        ToDoListFragment toDoListFragment = ToDoListFragment.getInstance();
        ((MainActivity) Objects.requireNonNull(getActivity())).replaceCurrentFragment(toDoListFragment,true);
    }


}
