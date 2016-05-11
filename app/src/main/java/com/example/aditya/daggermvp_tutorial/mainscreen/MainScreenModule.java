package com.example.aditya.daggermvp_tutorial.mainscreen;



import com.example.aditya.daggermvp_tutorial.util.CustomeScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Aditya on 11-May-16.
 */
@Module
public class MainScreenModule {
    private final MainScreenContract.View mView;


    public MainScreenModule(MainScreenContract.View mView) {
        this.mView = mView;
    }

    @Provides
    @CustomeScope
    MainScreenContract.View providesMainScreenContractView() {
        return mView;
    }
}
