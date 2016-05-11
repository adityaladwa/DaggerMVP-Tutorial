package com.example.aditya.daggermvp_tutorial.data.component;



import com.example.aditya.daggermvp_tutorial.data.module.AppModule;
import com.example.aditya.daggermvp_tutorial.data.module.NetModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by Aditya on 21-Apr-16.
 */
@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    // downstream components need these exposed with the return type
    // method name does not really matter
    Retrofit retrofit();
}
