package mobile.s.todogoals.home.views.adapters;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mobile.s.todogoals.R;
import mobile.s.todogoals.database.ToDoModelDB;
import mobile.s.todogoals.home.views.activities.MainActivity;
public class AdapterFinishedToDo extends RecyclerView.Adapter<AdapterFinishedToDo.ItemViewHolder> {
    private MainActivity Context;
    private List<ToDoModelDB> toDoModelDBArrayList ;
    private FinishToDoActionsListener onItemClick;
    @Inject
    public AdapterFinishedToDo(MainActivity mContext) {
        this.Context = mContext;
        toDoModelDBArrayList = new ArrayList<>();
    }

    public void setOnItemClickListener( FinishToDoActionsListener onItemClick) {
        this.onItemClick = onItemClick;
    }

    public void updateChatList(List<ToDoModelDB> toDoModelDBS){

        this.toDoModelDBArrayList.clear();
        this.toDoModelDBArrayList.addAll(toDoModelDBS);
        this.notifyDataSetChanged();

    }
    @NonNull
    @Override
    public AdapterFinishedToDo.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(Context);
        View view = layoutInflater.inflate(R.layout.row_layout_todo_finished, parent, false);

        return new AdapterFinishedToDo.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterFinishedToDo.ItemViewHolder holder, int position) {

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
    public interface FinishToDoActionsListener {
        void onFinishToDoClicked(ToDoModelDB toDoModelDB);
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
        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.row_layout_todo)
        void onClick(){
            onItemClick.onFinishToDoClicked(toDoModelDBArrayList.get(getAdapterPosition()));
        }
    }
}