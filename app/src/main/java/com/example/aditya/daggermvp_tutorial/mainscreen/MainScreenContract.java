package com.example.aditya.daggermvp_tutorial.mainscreen;



import com.example.aditya.daggermvp_tutorial.data.Post;

import java.util.List;

/**
 * Created by Aditya on 11-May-16.
 */
public interface MainScreenContract {
    interface View {
        void showPosts(List<Post> posts);

        void showError(String message);

        void showComplete();
    }

    interface Presenter {
        void loadPost();
    }
}
