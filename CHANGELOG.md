# Changelog

## 2.0.0

### Breaking changes
- Migrated from the support library to AndroidX.
- minSdk is now 21.
- `Builder.setDelayMillis()` takes a `long`.
- Constants in `Constants` are now `final`.
- Intros without a usage id are no longer persisted and are shown every time.

### New
- `MaterialIntroSequence` for showing intros one after another.
- `MaterialIntroListener.onIntroShown()` callback (default method).
- Back press dismisses the intro, including predictive back on Android 13+.
  Disable with `Builder.dismissOnBackPress(false)`.
- `Builder.setInfoText(@StringRes int)`.
- `Builder.setIcon()`, `setIconTint()` and `setInfoCardBackgroundColor()`.
- `Builder.setRectCornerRadius()` for rectangle focus.
- `FocusGravity.START` and `FocusGravity.END`, and RTL support in the info card.
- Accessibility: the info text is exposed to screen readers and the overlay can be dismissed with TalkBack.

### Fixes
- Crash when a layout pass happened before the shape was set.
- `onUserClicked()` was called twice when tapping during the fade out animation.
- Fade out animation ran even when fade was disabled.
- Dot animation, pending callbacks and the mask bitmap leaked after dismiss.
- Mask was not redrawn correctly after the window was resized.
- Target view stayed pressed when the touch was cancelled.
- Rectangle focus did not follow targets that moved.
- `setConfiguration()` ignored padding, icon visibility and text color.

### Build
- Android Gradle Plugin 9.4.0, Gradle 9.6.1, compile/target SDK 36.
- Published with `maven-publish`; JitPack builds with JDK 17.
- Unit tests with Robolectric and GitHub Actions CI.

## 1.6.0
- Last release based on the support library.
