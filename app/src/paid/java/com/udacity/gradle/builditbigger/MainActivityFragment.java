package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.gradle.builditbigger.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import nl.erikduisters.javajokes.JavaJoker;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private final JavaJoker javaJoker;

    public MainActivityFragment() {
        javaJoker = new JavaJoker();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        ButterKnife.bind(this, root);

        return root;
    }

    @OnClick(R.id.button)
    public void tellJoke() {
        //Toast.makeText(getContext(), javaJoker.getJoke(), Toast.LENGTH_SHORT).show();

        /*
        Intent intent = new Intent(getContext(), JokeDisplayActivity.class);
        intent.putExtra(JokeDisplayActivity.KEY_JOKE, javaJoker.getJoke());

        startActivity(intent);
        */

        EndpointsAsyncTask task = new EndpointsAsyncTask();
        task.execute(getContext());
    }
}
