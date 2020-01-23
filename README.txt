=========================
Over all Requirements:
==========================

1. Write a mobile app that uses the Flickr image search API 
   - This process is correctly done with the given API_KEY
2. and shows the results in a 3-column scrollable view.   
    - This process is done for images



==============================================
Requirements and notes:
==============================================

1. The app should display the first page of results returned by the API. 
 - Done
2. The app should correctly handle orientation changes (without requiring android:configChanges in the manifest file) i.e. image search term and results should remain after screen rotation.
 -Done. 
 Note: The above condition according to my understanding which is based on the testing is being handled.
 Application is not failing. I have check the output for configuration changes in Samsung Galaxy 8 emulator. It was showing me the correct results.

3. Don’t worry about supporting old versions of Android, a minSdkVersion of 23 or later is fine. There are bonus points for the inclusion of Material Design elements. 
  - Done
4. Feel free to use the technologies you are most comfortable with. This includes any open-source third-party libraries such as Gson, Retrofit, OkHttp, Glide, RxJava, etc.
 - Done (Used OkHttp libraries)

Bonus tasks:
1. App written using Kotlin 
 - This part is not done, because of less time.
2. Support pagination – Extend your app to support endless scrolling i.e. automatically requesting and displaying more images when the user scrolls to the bottom of the list.  
 - Done. 

3. Add one or two unit tests to demonstrate your knowledge of automated testing.   
- Done (Added one Instrumented Unit Test case to check the Editext value on Button Click)


==================================================================
Thing That I would have done if there was more time:
==================================================================

1. If I would have little more time for this Android Test, my first thing to implement this in Kotlin only. 
I am planning to make same application on Kotlin, maybe after sometime I can send you the link.

2. To create more test cases, at this moment I have create a simple test case, I wanted to test the FlickerGallery class, in which the recycleview is being displayed, 
since I am using custom adapter -- the liberary (espresso for testing) has some limitations over the custom adapters.

3. I would have test the configuration changes more thoroughly, as I have tested the same on emulator. 
Which is giving me correct results but I am generally test my code more thoroughly until I am 100% sure.
