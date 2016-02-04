package com.example.karan.myapplication;

/**
 * Created by Karan on 10/8/15.
 */
import com.parse.Parse;
        import com.parse.ParseACL;

        import com.parse.ParseUser;

        import android.app.Application;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "8UWzukCeKOYEJbej0UuOVOmx2OJO97IL8uaKpSjc", "0Kpv0iSTE6OXYaLoh9XenthQTfLfy7ua2RBvgxqp");

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);
    }

}