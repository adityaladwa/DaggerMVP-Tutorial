package com.example.aditya.daggermvp_tutorial.mainscreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.aditya.daggermvp_tutorial.App;
import com.example.aditya.daggermvp_tutorial.R;
import com.example.aditya.daggermvp_tutorial.data.Post;

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

        //Call the method in MainPresenter to make Network Request
        mainPresenter.loadPost();
    }

    @Override
    public void showPosts(List<Post> posts) {
        for (int i = 0; i < posts.size(); i++) {
            list.add(posts.get(i).getTitle());
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);

    }

    @Override
    public void showError(String message) {
        Toast.makeText(getApplicationContext(), "Error" + message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showComplete() {
        Toast.makeText(getApplicationContext(), "Complete", Toast.LENGTH_SHORT).show();
        listView.setAdapter(adapter);
    }
}
