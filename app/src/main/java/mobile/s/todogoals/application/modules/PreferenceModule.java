package mobile.s.todogoals.application.modules;

import android.content.Context;
import android.content.SharedPreferences;

import dagger.Module;
import dagger.Provides;
import mobile.s.todogoals.application.ApplicationScope;

@Module(includes = {ContextModule.class})
public class PreferenceModule {

    private static final String PREF_FILE_NAME = "todo_list";


    @Provides
    @ApplicationScope
    SharedPreferences getSharedPref(Context context)
    {
        return context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }
}
