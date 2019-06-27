package mobile.s.todogoals.database;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

import javax.inject.Inject;

@Entity
public class ToDoModelDB implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int todoId;
    @SerializedName("todoTitle")
    @ColumnInfo(name = "todoTitle")
    private String todoTitle;
    private String todoStartDate;
    private String todoEndDate;
    private String todoDesc;
    private Boolean todoDone;

    @Inject
    public ToDoModelDB() {
    }
    public String getTodoStartDate() {
        return todoStartDate;
    }

    public void setTodoStartDate(String todoStartDate) {
        this.todoStartDate = todoStartDate;
    }

    public String getTodoEndDate() {
        return todoEndDate;
    }

    public void setTodoEndDate(String todoEndDate) {
        this.todoEndDate = todoEndDate;
    }


    public int getTodoId() {
        return todoId;
    }

    public void setTodoId(int todoId) {
        this.todoId = todoId;
    }

    public String getTodoTitle() {
        return todoTitle;
    }

    public void setTodoTitle(String todoTitle) {
        this.todoTitle = todoTitle;
    }

    public String getTodoDesc() {
        return todoDesc;
    }

    public void setTodoDesc(String todoDesc) {
        this.todoDesc = todoDesc;
    }

    public Boolean getTodoDone() {
        return todoDone;
    }

    public void setTodoDone(Boolean todoDone) {
        this.todoDone = todoDone;
    }
}
