package mobile.s.todogoals.home.views.activities;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import butterknife.BindView;
import butterknife.ButterKnife;
import mobile.s.todogoals.R;
import mobile.s.todogoals.application.BaseApplication;
import mobile.s.todogoals.base.BaseActivity;
import mobile.s.todogoals.home.component.DaggerHomeComponent;
import mobile.s.todogoals.home.component.HomeComponent;
import mobile.s.todogoals.home.modules.HomeModule;
import mobile.s.todogoals.home.views.fragments.AddingNewToDoFragment;
import mobile.s.todogoals.home.views.fragments.ToDoListFragment;
import mobile.s.todogoals.home.views.interfaces.ToDoActivityView;
import mobile.s.todogoals.utils.Messenger;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, ToDoActivityView {
    @BindView(R.id.toolbar)
     Toolbar toolbar;
    HomeComponent homeComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        homeComponent = DaggerHomeComponent.builder().homeModule(new HomeModule(this)).
        applicationComponent(((BaseApplication) getApplicationContext()).getComponent()).build();
        homeComponent.injectHomeActivity(this);
        initialUI();
    }

    public HomeComponent getHomeComponent() {
        return homeComponent;
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void initialUI() {

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            AddingNewToDoFragment addingNewToDoFragment = AddingNewToDoFragment.getInstance();
            replaceCurrentFragment(addingNewToDoFragment,true);
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void initialFragment() {
        ToDoListFragment addFamilyProfileFragment = new ToDoListFragment();
        replaceCurrentFragment(addFamilyProfileFragment,true);
    }

    @Override
    public void showMessage(String message) {
        Messenger.showErrorMsg(message,MainActivity.this);

    }

    @Override
    public void showErrMsg(String msg) {
        Messenger.showErrorMsg(msg,MainActivity.this);

    }
}
