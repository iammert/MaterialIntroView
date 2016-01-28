package co.mobiwise.materialintrosample;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.mobiwise.materialintro.MaterialIntroView;
import co.mobiwise.materialintro.shape.Focus;
import co.mobiwise.materialintro.shape.FocusGravity;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    @Bind(R.id.fab)
    FloatingActionButton floatingActionButton;

    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initializeViewsAdapter();
        loadData();

        new MaterialIntroView.Builder(MainActivity.this)
                .setDelayMillis(2000)
                .enableFadeAnimation(true)
                .setTarget(floatingActionButton)
                .setFocusType(Focus.MINIMUM)
                .setTargetPadding(70)
                .dismissOnTouch(false)
                .setFocusGravity(FocusGravity.CENTER)
                .show();


    }

    /**
     * Initializes views and adapter
     */
    private void initializeViewsAdapter() {

        setSupportActionBar(toolbar);
        collapsingToolbarLayout.setTitle("My RadioList");

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new RecyclerViewAdapter(getApplicationContext());
        recyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.fab)
    public void onClick() {
        Log.v("TEST", "Clicked");
    }

    /**
     * load mock data to adapter
     */
    private void loadData() {
        Radio radio = new Radio("Joy Radio FM", R.drawable.temp, "102.5");
        List<Radio> radioList = new ArrayList<>();

        for (int i = 0; i < 20; i++)
            radioList.add(radio);

        adapter.setRadioList(radioList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
