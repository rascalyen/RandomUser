# Random User

<img height="380" src="https://github.com/rascalyen/RandomUser/blob/master/screenshot/00.png" />
<img height="380" src="https://github.com/rascalyen/RandomUser/blob/master/screenshot/01.png" />
<img height="380" src="https://github.com/rascalyen/RandomUser/blob/master/screenshot/02.png" />
<img height="380" src="https://github.com/rascalyen/RandomUser/blob/master/screenshot/03.png" />
<img height="250" src="https://github.com/rascalyen/RandomUser/blob/master/screenshot/04.png" />
<br>

### Features
* See random user list (10 items in recyclerView) when you open
* Each item shows user name, city, state, and phone
* REFRESH button to initial state as 1st-time open the app
* gender option menus to show users only with specific gender
* Pagination with 10 items incremental
* Fragment state caching so it should survive screen orientation changes
* Detail page with information autolink.

### Technical Features
* Build android with MVP design pattern
* Use Dependency Injection ([Dagger2](http://google.github.io/dagger/)) to separate configuration (properties, imageClient, httpClient, etc.) and UI usage
* Use [Butterknife](https://github.com/JakeWharton/butterknife) for view injection in Activity/Fragment
* Use [Retrofit2](http://square.github.io/retrofit/) and [Jackson](https://github.com/FasterXML/jackson) to call RESTful API and parse returned JSON response
* [Picasso](http://square.github.io/picasso/) as image client
* Read properties from /res/raw resource
* How to save/restore Fragment's state
* Use RecyclerView and CardView


### Quality Assurance
* [Checkstyle](http://checkstyle.sourceforge.net/), [Findbugs](http://findbugs.sourceforge.net/), [PMD](https://pmd.github.io/) and [Lint](https://developer.android.com/studio/write/lint.html) for static code analysis

#### Run "check" task from Gradle
In this project, run check to ensure code quality in the following order: Checkstyle -> Findbugs -> PMD -> Lint.  You should find all configuration files under config/quality/..

```
./gradlew check
```


### TODO if this is a real world product

* Better user message, data validation, JavaDoc
* Unit test with [JUnit](http://junit.org/), [Mockito](http://mockito.org/) and [Robolectric](http://robolectric.org/)
* UI functional test with [Espresso] (https://google.github.io/android-testing-support-library/docs/espresso/)
* Define product flavor in Gradle script if there're dev and production environments
* ProGuard obfuscator setup for signed release build
* CI setup. I'd use Jenkins in house or cloud CI service (Travis / Circle) with outsourcing company. Plus AutoDelivery / CrashReport integration like Fabric

### Woops... Bug
Forgot to include scrollView for detail page therefore it only shows half page in landscape and it's not scrollable !!! Bad user experience users not happy :(    (But I wanna enjoy my weekend so I will fix it next week LOL)
