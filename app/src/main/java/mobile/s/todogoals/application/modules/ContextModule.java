package mobile.s.todogoals.application.modules;

/**
 * Created by Mohamed on 11/16/2017.
 */

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    private final Context context;

    public ContextModule(Context context) {
        this.context = context.getApplicationContext();
    }

    @Provides
    public Context context() {
        return context;
    }

}
