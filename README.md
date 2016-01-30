# MaterialIntroView
Material Intro View is a showcase android library.

#Screen
<img src="https://raw.githubusercontent.com/iammert/MaterialIntroView/master/art/materialintroviewgif.gif"/>

#Usage
```java
new MaterialIntroView.Builder(this)
                .enableDotAnimation(true)
                .setFocusGravity(FocusGravity.CENTER)
                .setFocusType(Focus.MINIMUM)
                .setDelayMillis(500)
                .enableFadeAnimation(true)
                .performClick(true)
                .setInfoText("Hi There! Click this card and see what happens.")
                .setTarget(view)
                .setUsageId("intro_card") //THIS SHOULD BE UNIQUE ID
                .show();
```

# Builder Methods
```java
.setMaskColor(Color.Blue) 
```
```java
.setDelayMillis(3000) //starts after 3 seconds passed
```
```java
.enableFadeAnimation(true) //View will appear/disappear with fade in/out animation
```
```java
.setFocusType(Focus.MINIMUM)
.setFocusType(Focus.NORMAL)
.setFocusType(Focus.ALL)
```
```java
.setFocusGravity(FocusGravity.LEFT)
.setFocusType(FocusGravity.CENTER)
.setFocusType(FocusGravity.RIGHT)
```
```java
.setTarget(myButton) //Focus on myButton
```
```java
.setTargetPadding(30) //add 30px padding to focus circle
```
```java
.setInfoText("This is info text!") //Setting text will enable info dialog
```
```java
.setTextColor(Color.Black) //Info dialog's text color is set to black
```
```java
.setInfoTextSize(30) //Change text size
```
```java
.setUsageId("intro_fab_button") //Store intro view status whether it is learnt or not
```
```java
.enableDotAnimation(true) //Shows dot animation center of focus area
```
```java
.performClick(true) //Trigger click operation when user click focused area.
```
```java
//If you don't want to perform click automatically
//You can disable perform clik and handle it yourself
.setListener(new MaterialIntroListener() {
                    @Override
                    public void onUserClicked(String materialIntroViewId) {
                        
                    }
                })
```
```java
//Create global config instance to not write same config to builder
//again and again.
MaterialIntroConfiguration config = new MaterialIntroConfiguration();
config.setDelayMillis(1000);
config.setFadeAnimationEnabled(true);
...
.setConfiguration(config) //
```








