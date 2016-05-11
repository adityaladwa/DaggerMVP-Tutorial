package com.example.aditya.daggermvp_tutorial.mainscreen;


import com.example.aditya.daggermvp_tutorial.data.component.NetComponent;
import com.example.aditya.daggermvp_tutorial.util.CustomScope;

import dagger.Component;

/**
 * Created by Aditya on 11-May-16.
 */
@CustomScope
@Component(dependencies = NetComponent.class, modules = MainScreenModule.class)
public interface MainScreenComponent {
    void inject(MainActivity activity);
}
