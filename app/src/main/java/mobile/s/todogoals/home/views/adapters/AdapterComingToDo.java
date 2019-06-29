package mobile.s.todogoals.home.views.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mobile.s.todogoals.R;
import mobile.s.todogoals.base.BaseView;
import mobile.s.todogoals.base.OnItemClickListener;
import mobile.s.todogoals.database.ToDoModelDB;
import mobile.s.todogoals.home.views.activities.MainActivity;
import mobile.s.todogoals.home.views.interfaces.ToDoListFragmentView;

public class AdapterComingToDo extends RecyclerView.Adapter<AdapterComingToDo.ItemViewHolder> {


    private MainActivity Context;
    private List<ToDoModelDB> toDoModelDBArrayList ;
    private OnItemClickListener<ToDoModelDB> onItemClick;
    private ToDoListFragmentView toDoListFragmentView;
    @Inject
    public AdapterComingToDo(MainActivity mContext) {
        this.Context = mContext;
        toDoModelDBArrayList = new ArrayList<>();
    }

    public void setOnItemClickListener( OnItemClickListener<ToDoModelDB>  onItemClick) {
        this.onItemClick = onItemClick;
    }
    public void setToDoListFragmentView(BaseView toDoListFragmentView)
    {
        this.toDoListFragmentView= (ToDoListFragmentView) toDoListFragmentView;
    }

    public void updateChatList(List<ToDoModelDB> toDoModelDBS){

        this.toDoModelDBArrayList.clear();
        this.toDoModelDBArrayList.addAll(toDoModelDBS);
        this.notifyDataSetChanged();

    }
    @NonNull
    @Override
    public AdapterComingToDo.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(Context);
        View view = layoutInflater.inflate(R.layout.row_layout_todo, parent, false);

        return new AdapterComingToDo.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        ToDoModelDB toDoModelDB=toDoModelDBArrayList.get(position);
        if(toDoModelDB!=null)
        {
            holder.titleTxtView.setText(toDoModelDB.getTodoTitle());
            holder.descTxtView.setText(toDoModelDB.getTodoDesc());
            holder.startDateTxtView.setText(toDoModelDB.getTodoStartDate());
            holder.endDateTxtView.setText(toDoModelDB.getTodoEndDate());

        }
    }


    @Override
    public int getItemCount() {
        return toDoModelDBArrayList == null ? 0 : toDoModelDBArrayList.size();

    }

    public  class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title_todo)
        TextView titleTxtView;
        @BindView(R.id.desc_todo)
        TextView descTxtView;
        @BindView(R.id.start_time_id)
        TextView startDateTxtView;
        @BindView(R.id.end_time_id)
        TextView endDateTxtView;

        @BindView(R.id.make_finish_todo_check)
        CheckBox toDoIsFinishBtn;
        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        @OnClick(R.id.make_finish_todo_check)
        void onCheckBoxClicked(){
            if (toDoIsFinishBtn.isChecked())
                toDoListFragmentView.onCheckBoxClicked( toDoModelDBArrayList.get(getAdapterPosition()));
        }
        @OnClick(R.id.row_layout_todo)
        void onClick(){
            onItemClick.onItemClick(toDoModelDBArrayList.get(getAdapterPosition()));
        }
    }
}
