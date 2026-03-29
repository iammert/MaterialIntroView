package co.mobiwise.materialintro.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import co.mobiwise.materialintro.animation.MaterialIntroListener;

/**
 * Shows several intros one after another. The next intro is shown when
 * the previous one is dismissed. Intros whose usage id was already
 * displayed are skipped.
 *
 * <pre>
 * new MaterialIntroSequence()
 *         .add(new MaterialIntroView.Builder(activity).setTarget(search).setUsageId("search"))
 *         .add(new MaterialIntroView.Builder(activity).setTarget(share).setUsageId("share"))
 *         .setOnCompleteListener(() -> ...)
 *         .start();
 * </pre>
 */
public class MaterialIntroSequence {

    public interface OnCompleteListener {
        void onSequenceComplete();
    }

    private final List<MaterialIntroView.Builder> steps = new ArrayList<>();

    @Nullable
    private OnCompleteListener onCompleteListener;

    private int nextIndex;

    private boolean started;

    public MaterialIntroSequence add(@NonNull MaterialIntroView.Builder builder) {
        if (started) throw new IllegalStateException("Cannot add steps after start()");
        steps.add(builder);
        return this;
    }

    public MaterialIntroSequence setOnCompleteListener(@Nullable OnCompleteListener listener) {
        this.onCompleteListener = listener;
        return this;
    }

    public void start() {
        if (started) return;
        started = true;
        showNext();
    }

    private void showNext() {
        while (nextIndex < steps.size()) {
            MaterialIntroView.Builder step = steps.get(nextIndex++);
            final MaterialIntroListener stepListener = step.getListener();
            step.setListener(new MaterialIntroListener() {
                @Override
                public void onUserClicked(String materialIntroViewId) {
                    if (stepListener != null) stepListener.onUserClicked(materialIntroViewId);
                    showNext();
                }
            });

            MaterialIntroView view = step.show();
            if (view.getParent() != null) {
                // Shown, the listener continues the sequence on dismiss.
                return;
            }
        }

        if (onCompleteListener != null) {
            OnCompleteListener listener = onCompleteListener;
            onCompleteListener = null;
            listener.onSequenceComplete();
        }
    }
}
