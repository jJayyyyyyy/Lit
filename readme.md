#	Lit <img alt="ic_launcher" src="./assets/ic_launcher.png" width="32"/>

<div>
<a rel="cc-license" href="http://creativecommons.org/licenses/by-nc-sa/4.0/"><img alt="CC-BY--NC--SA" style="border-width:0" src="https://img.shields.io/badge/%20%20%20CC%20%20%20-BY--NC--SA-brightgreen.svg"></img></a>

<a rel="mit-license" href="http://opensource.org/licenses/mit-license.php"><img alt="MIT-License" style="border-width:0" src="https://img.shields.io/badge/License-MIT%20License-blue.svg"></img></a>

<img alt="Lollipop" style="border-width:0" src="https://img.shields.io/badge/Android-Lollipop%2B-orange.svg"></img>
</div>

**Lit** is a Lite Info Toolkit. It's like some kind of RSS reader with weather-and-dict query feature.

Basically this collection of tools were written in Python and used on PC. Since a handy is more handy so I made it to **Lit**. 

##	Installation

The installer is located in the **[Lit/installer/](https://github.com/jJayyyyyyy/Lit/tree/master/installer)** folder. 

Lit-installer is about the size of 1.4MB and needs access to network.

##	Development

*	Android Studio 2.2 for MAC
*  JDK embeded in Android Studio 2.2
*	API 21: Android 5.0 (Lollipop)

##	Layout

<table>
<tr>
<td><img src="./assets/main_activity.jpeg" width="240"/>
</td>
<td><img src="./assets/hackernews_activity.jpeg" width="240"/></td>
<tr>
</table>


##	Features

*	Weather

	Fetch the weather of today and tomorrow, plus Air Quality Index. Currently it is hard-coded and can only fetch info of Hangzhou from [中国天气网](http://m.weather.com.cn/). Try to change the city-code in `Lit/app/src/main/java/io/github/jjayyyyyyy/lit/MainActivity.java` to display the weather of your own city.

*	Dict

	Translate the CN to/from EN.

*	Hacker News

	Fetch the first page titles of [Hacker News](https://news.ycombinator.com/)

*	Solidot

	Fetch the first page titles of [Solidot](http://www.solidot.org/)
	
*	Newsflash

	Fetch the first page titles of [36kr Newsflash](http://36kr.com/newsflashes)

##	Mobile Traffic Consumption

Approximate mobile traffic consumption of each query is list below.

| query | traffic |
| :----- | :------- |
| Weather | 20 KB | 
| Dict | 1 KB | 
| Hacker News | 8 KB | 
| Solidot | 30 KB | 
| weather | 20 KB | 

##	Reference

*	Badge
	*	[Shields, make nice badge](http://shields.io/)

*	Basics
	*	[Udacity, Android Basics](https://www.udacity.com/courses/android)
	*	[Android Devloper Doc](https://developer.android.com/index.html)
	*	[Common_Android_Views_Cheat_Sheet](http://cn-static.udacity.com/nd801/Common_Android_Views_Cheat_Sheet.pdf)
	*	[Material Design](https://material.io/)

*	Json
	*	[TutorialsPoint, Android JSON Parser](https://www.tutorialspoint.com/android/android_json_parser.htm)
	*	[CuriousConcept, json formatter](https://jsonformatter.curiousconcept.com/)

*	Manifests
	*	[Stack Overflow, disable landscpe mode](http://stackoverflow.com/a/582585/5584850)

*	Regular Expression
	*	[Oracle Docs, Class Pattern](http://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html)
	*	[TutorialsPoint, Java Regex](https://www.tutorialspoint.com/java/java_regular_expressions.htm)
	*	[CSDN leesren, Java正则表达式转义](http://blog.csdn.net/csr0312/article/details/17016709)

*	Release
	*	[Stack Overflow, create a keystore](http://stackoverflow.com/questions/3997748/how-can-i-create-a-keystore)
	*	[Android Devloper Doc, publish/app-signing](https://developer.android.com/studio/publish/app-signing.html)

*	Spinner
	*	[泡网, Android Spinner控件详解](http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0105/2264.html)

*	TextView
	*	[Stack Overflow, setTextSize()](http://stackoverflow.com/questions/11590538/dpi-value-of-default-large-medium-and-small-text-views-android)
	*	[马会东, setTextSize()](http://www.cnblogs.com/duanweishi/p/4449588.html)
