package com.example.contacts;

import android.app.Application;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;

import java.util.List;

public class ContactsApplication extends Application {
    public static final String APPLICATION_ID = ""; //Copy paste the APPLICATION_ID from Backendless
    public static final String API_KEY = "";       // Copy paste the API_KEY from Backendless
    public static final String SERVER_URL = "https://api.backendless.com";
    public static BackendlessUser user;
    public static List<Contact> contacts;

    @Override
    public void onCreate() {
        super.onCreate();
        Backendless.setUrl( SERVER_URL );
        Backendless.initApp( getApplicationContext(),
                APPLICATION_ID,
                API_KEY );

    }
}
