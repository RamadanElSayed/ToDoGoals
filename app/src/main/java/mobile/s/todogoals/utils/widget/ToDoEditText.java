package mobile.s.todogoals.utils.widget;
import android.content.Context;
import android.util.AttributeSet;
import mobile.s.todogoals.utils.FontHelper;

public class ToDoEditText extends android.support.v7.widget.AppCompatEditText {

    public ToDoEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public ToDoEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ToDoEditText(Context context) {
        super(context);
        init();
    }

    private void init() {
        setTypeface(FontHelper.get(FontHelper.CAIRO_FONT, getContext()));
    }

}
