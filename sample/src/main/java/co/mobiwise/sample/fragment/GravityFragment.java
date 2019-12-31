package co.mobiwise.sample.fragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.cardview.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.mobiwise.materialintro.animation.MaterialIntroListener;
import co.mobiwise.materialintro.shape.Focus;
import co.mobiwise.materialintro.shape.FocusGravity;
import co.mobiwise.materialintro.view.MaterialIntroView;
import co.mobiwise.sample.R;

/**
 * Created by mertsimsek on 31/01/16.
 */
public class GravityFragment extends Fragment implements MaterialIntroListener{

    private static final String INTRO_CARD1 = "intro_card_1";
    private static final String INTRO_CARD2 = "intro_card_2";
    private static final String INTRO_CARD3 = "intro_card_3";

    CardView cardView1;
    CardView cardView2;
    CardView cardView3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gravity, container, false);

        cardView1 = (CardView) view.findViewById(R.id.my_card);
        cardView2 = (CardView) view.findViewById(R.id.my_card2);
        cardView3 = (CardView) view.findViewById(R.id.my_card3);

        showIntro(cardView1, INTRO_CARD1, "This intro focuses on RIGHT", FocusGravity.RIGHT);

        return view;
    }

    @Override
    public void onUserClicked(String materialIntroViewId) {
        if(materialIntroViewId == INTRO_CARD1)
            showIntro(cardView2, INTRO_CARD2, "This intro focuses on CENTER.", FocusGravity.CENTER);
        if(materialIntroViewId == INTRO_CARD2)
            showIntro(cardView3, INTRO_CARD3, "This intro focuses on LEFT.", FocusGravity.LEFT);
    }

    public void showIntro(View view, String id, String text, FocusGravity focusGravity){
        new MaterialIntroView.Builder(getActivity())
                .enableDotAnimation(true)
                .setFocusGravity(focusGravity)
                .setFocusType(Focus.MINIMUM)
                .setDelayMillis(200)
                .enableFadeAnimation(true)
                .performClick(true)
                .setInfoText(text)
                .setTarget(view)
                .setListener(this)
                .setUsageId(id) //THIS SHOULD BE UNIQUE ID
                .show();
    }
}
