package co.mobiwise.sample.fragment;

import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import co.mobiwise.materialintro.animation.MaterialIntroListener;
import co.mobiwise.materialintro.shape.Focus;
import co.mobiwise.materialintro.shape.FocusGravity;
import co.mobiwise.materialintro.view.MaterialIntroView;
import co.mobiwise.sample.R;
import co.mobiwise.sample.adapter.RecyclerViewAdapter;
import co.mobiwise.sample.model.Song;

public class RecyclerviewFragment extends Fragment implements MaterialIntroListener {

    private static final String INTRO_CARD = "recyclerView_material_intro";
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        initializeRecyclerview();
        loadData();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showMaterialIntro();
            }
        }, 2000);
        return view;
    }

    private void showMaterialIntro() {
        new MaterialIntroView.Builder(getActivity())
                .enableDotAnimation(true)
                .setFocusGravity(FocusGravity.CENTER)
                .setFocusType(Focus.MINIMUM)
                .setDelayMillis(200)
                .enableFadeAnimation(true)
                .setListener(this)
                .performClick(true)
                .setInfoText("This intro focuses on Recyclerview item")
                .setTarget(recyclerView.getChildAt(2))
                .setUsageId(INTRO_CARD) //THIS SHOULD BE UNIQUE ID
                .show();
    }

    private void loadData() {

        Song song = new Song("Diamond", R.drawable.diamond, "Rihanna");
        List<Song> songList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            songList.add(song);
        }
        adapter.setSongList(songList);
    }


    private void initializeRecyclerview() {

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new RecyclerViewAdapter(getActivity().getApplicationContext());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onUserClicked(String materialIntroViewId) {
        if (materialIntroViewId.equals(INTRO_CARD)) {
            Toast.makeText(getActivity(), "User Clicked", Toast.LENGTH_SHORT).show();
        }
    }
}
