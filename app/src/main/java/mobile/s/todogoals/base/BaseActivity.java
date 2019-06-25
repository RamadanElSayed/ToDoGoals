package mobile.s.todogoals.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void  addFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(getContentViewId(), fragment);
        transaction.commit();
    }

    public void replaceCurrentFragment(Fragment targetFragment, boolean addToBackStack) {

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(getContentViewId(), targetFragment, targetFragment.getClass().getName());
        if (addToBackStack) {
            ft.addToBackStack(targetFragment.getClass().getName());
        }
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
            super.onBackPressed();
        }
    }

    protected View getRootView() {
        return this.findViewById(android.R.id.content);
    }

    protected int getContentViewId() {
        return getRootView().getId();
    }

}