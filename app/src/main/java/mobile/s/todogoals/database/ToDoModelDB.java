package mobile.s.todogoals.database;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

@Entity
public class ToDoModelDB implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int todoId;
    @SerializedName("todoTitle")
    @ColumnInfo(name = "todoTitle")
    private String todoTitle;
    private String todoDate;
    private String todoDesc;
    private Boolean todoDone;

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

    public String getTodoDate() {
        return todoDate;
    }

    public void setTodoDate(String todoDate) {
        this.todoDate = todoDate;
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
