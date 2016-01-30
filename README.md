# MaterialIntroView [Beta]
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
//ie. If your button's width has MATCH_PARENT.
//Focus.ALL is not a good option. You can use
//Focus.MINIMUM or Focus.NORMAL. See demos below.
.setFocusType(Focus.MINIMUM)
.setFocusType(Focus.NORMAL)
.setFocusType(Focus.ALL)
```
```java
//ie. You can focus on left of RecyclerView list item.
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
# Configuration Method
```java
//Create global config instance to not write same config to builder
//again and again.
MaterialIntroConfiguration config = new MaterialIntroConfiguration();
config.setDelayMillis(1000);
config.setFadeAnimationEnabled(true);
...
.setConfiguration(config) //
```

# Demos

<img src="https://raw.githubusercontent.com/iammert/MaterialIntroView/master/art/art_drawer.png"/> <img src="https://raw.githubusercontent.com/iammert/MaterialIntroView/master/art/art_focus_all.png"/>

<img src="https://raw.githubusercontent.com/iammert/MaterialIntroView/master/art/art_focus_normal.png"/> <img src="https://raw.githubusercontent.com/iammert/MaterialIntroView/master/art/art_gravity_left.png"/>

# TODO

* [ ] Sample app will be more detailed about using library.
* [ ] Sequence for MaterialIntroViews

#Authors

[Mert SIMSEK](https://github.com/iammert)

[Murat Can BUR](https://github.com/muratcanbur)


License
--------


    Copyright 2015 Mert Şimşek.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.







