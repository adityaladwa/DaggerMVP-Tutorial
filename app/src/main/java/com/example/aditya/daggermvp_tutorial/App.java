package com.example.aditya.daggermvp_tutorial;

import android.app.Application;

import com.example.aditya.daggermvp_tutorial.data.component.DaggerNetComponent;
import com.example.aditya.daggermvp_tutorial.data.component.NetComponent;
import com.example.aditya.daggermvp_tutorial.data.module.AppModule;
import com.example.aditya.daggermvp_tutorial.data.module.NetModule;


/**
 * Created by Aditya on 11-May-16.
 */
public class App extends Application {
    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule("http://jsonplaceholder.typicode.com/"))
                .build();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }
}
