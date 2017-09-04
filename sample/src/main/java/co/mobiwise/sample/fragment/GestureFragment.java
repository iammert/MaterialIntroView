package co.mobiwise.sample.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import co.mobiwise.materialintro.animation.MaterialIntroListener;
import co.mobiwise.materialintro.shape.CircleOnEdge;
import co.mobiwise.materialintro.shape.FocusGravity;
import co.mobiwise.materialintro.target.ViewTarget;
import co.mobiwise.materialintro.view.MaterialIntroView;
import co.mobiwise.sample.R;

/**
 * Created by mertsimsek on 31/01/16.
 */
public class GestureFragment extends Fragment implements MaterialIntroListener{

    private static final String INTRO_CARD_LEFT = "intro_gesture_left";
    private static final String INTRO_CARD_RIGHT = "intro_gesture_right";
    private static final String INTRO_CARD_TOP = "intro_gesture_top";
    private static final String INTRO_CARD_BOTTOM = "intro_gesture_bottom";

    private ViewPager pager;
    private PagerAdapter pagerAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gesture, container, false);

        this.pager = view.findViewById(R.id.pager);
        this.pagerAdapter = new ScreenSlidePagerAdapter();
        this.pager.setAdapter(this.pagerAdapter);

        showIntro(view, INTRO_CARD_RIGHT, "Hint that the view can be dragged from the RIGHT edge",
                FocusGravity.RIGHT, R.animator.drag_from_right);

        return view;
    }

    @Override
    public void onUserClicked(String materialIntroViewId) {
        if(materialIntroViewId == INTRO_CARD_RIGHT) {
            pager.setCurrentItem(1, true);
            showIntro(getView(), INTRO_CARD_LEFT, "Hint that the view can be dragged from the LEFT edge",
                    FocusGravity.LEFT, R.animator.drag_from_left);
        } else if (materialIntroViewId == INTRO_CARD_LEFT) {
            pager.setCurrentItem(0, true);
            showIntro(getView(), INTRO_CARD_TOP, "Hint that the view can be dragged from the TOP edge",
                    FocusGravity.TOP, R.animator.drag_from_top);
        } else if (materialIntroViewId == INTRO_CARD_TOP) {
            showIntro(getView(), INTRO_CARD_BOTTOM, "Hint that the view can be dragged from the BOTTOM edge",
                    FocusGravity.BOTTOM, R.animator.drag_from_bottom);
        }
    }

    public void showIntro(View view, String id, String text, FocusGravity focusGravity, int animatorResId){
        new MaterialIntroView.Builder(getActivity())
                .setGestureDrawableResId(R.drawable.ic_cursor_pointer)
                .setGestureAnimatorResId(animatorResId)
                .setCustomShape(new CircleOnEdge(new ViewTarget(view), focusGravity, 0))
                .setDelayMillis(200)
                .setIconDrawableResId(android.R.drawable.ic_dialog_info)
                .enableFadeAnimation(true)
                .setFadeAnimationDuration(200)
                .performClick(true)
                .setInfoText(text)
                .setTarget(view)
                .setListener(this)
                .setUsageId(id) //THIS SHOULD BE UNIQUE ID
                .show();
    }

    private class ScreenSlidePagerAdapter extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup collection, int position) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            TextView pageNumber = (TextView) inflater.inflate(R.layout.page, collection, false);
            pageNumber.setBackgroundColor(position == 0 ? 0xFF00897B : 0xFFFF9800);
            pageNumber.setText(String.valueOf(position));
            collection.addView(pageNumber);
            return pageNumber;
        }

        @Override
        public void destroyItem(ViewGroup collection, int position, Object view) {
            collection.removeView((View) view);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
