package co.mobiwise.sample.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import co.mobiwise.materialintro.animation.MaterialIntroListener;
import co.mobiwise.materialintro.shape.Focus;
import co.mobiwise.materialintro.shape.FocusGravity;
import co.mobiwise.materialintro.view.MaterialIntroView;
import co.mobiwise.sample.R;

/**
 * Created by mertsimsek on 31/01/16.
 */
public class FocusFragment extends Fragment implements MaterialIntroListener{

    private static final String INTRO_FOCUS_1 = "intro_focus_1";
    private static final String INTRO_FOCUS_2 = "intro_focus_2";
    private static final String INTRO_FOCUS_3 = "intro_focus_3";

    Button button1;
    Button button2;
    Button button3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_focus, container, false);

        button1 = (Button) view.findViewById(R.id.button_focus_1);
        button2 = (Button) view.findViewById(R.id.button_focus_2);
        button3 = (Button) view.findViewById(R.id.button_focus_3);

        showIntro(button1,INTRO_FOCUS_1,"This intro view focus on all target.", Focus.ALL);

        return view;
    }

    public void showIntro(View view, String id, String text, Focus focusType){
        new MaterialIntroView.Builder(getActivity())
                .enableDotAnimation(false)
                .setFocusGravity(FocusGravity.CENTER)
                .setFocusType(focusType)
                .setDelayMillis(200)
                .enableFadeAnimation(true)
                .setListener(this)
                .performClick(true)
                .setInfoText(text)
                .setTarget(view)
                .setUsageId(id) //THIS SHOULD BE UNIQUE ID
                .show();
    }

    @Override
    public void onUserClicked(String materialIntroViewId) {
        if(materialIntroViewId == INTRO_FOCUS_1)
            showIntro(button2,INTRO_FOCUS_2,"This intro view focus on minimum size", Focus.MINIMUM);
        else if(materialIntroViewId == INTRO_FOCUS_2)
            showIntro(button3,INTRO_FOCUS_3,"This intro view focus on normal size (avarage of MIN and ALL)", Focus.NORMAL);
    }
}
