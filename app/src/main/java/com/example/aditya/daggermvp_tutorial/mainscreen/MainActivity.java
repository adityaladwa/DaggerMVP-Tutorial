package com.example.aditya.daggermvp_tutorial.mainscreen;

import android.os.Bundle;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.core.deps.guava.annotations.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.aditya.daggermvp_tutorial.App;
import com.example.aditya.daggermvp_tutorial.R;
import com.example.aditya.daggermvp_tutorial.data.Post;
import com.example.aditya.daggermvp_tutorial.util.EspressoIdlingResource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainScreenContract.View {
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;

    @Inject
    MainScreenPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.my_list);
        list = new ArrayList<>();

        DaggerMainScreenComponent.builder()
                .netComponent(((App) getApplicationContext()).getNetComponent())
                .mainScreenModule(new MainScreenModule(this))
                .build().inject(this);


        //Increment the counter before making a network request
        EspressoIdlingResource.increment();

        //Call the method in MainPresenter to make Network Request
        mainPresenter.loadPost();
    }

    @Override
    public void showPosts(List<Post> posts) {
        //Loop through the posts and get the title of the post and add it to our list object
        for (int i = 0; i < posts.size(); i++) {
            list.add(posts.get(i).getTitle());
        }
        //Create the array adapter and set it to list view
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        //Decrement after loading the posts
        EspressoIdlingResource.decrement();
    }

    @Override
    public void showError(String message) {
        //Show error message Toast
        Toast.makeText(getApplicationContext(), "Error" + message, Toast.LENGTH_SHORT).show();

        // If there is no network connection we get an error and decrement the counter because the call has finished
        EspressoIdlingResource.decrement();
    }

    @Override
    public void showComplete() {
        //Show completed message Toast
        Toast.makeText(getApplicationContext(), "Complete", Toast.LENGTH_SHORT).show();

    }

    @VisibleForTesting
    public IdlingResource getCountingIdlingResource() {
        return EspressoIdlingResource.getIdlingResource();
    }
}
