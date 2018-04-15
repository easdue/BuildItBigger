package nl.erikduisters.androidjokes;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class JokeDisplayActivity extends AppCompatActivity {
    public static final String KEY_JOKE = "KeyJoke";

    TextView joke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        joke = findViewById(R.id.joke);

        Intent intent = getIntent();

        if (intent.hasExtra(KEY_JOKE)) {
            joke.setText(intent.getStringExtra(KEY_JOKE));
        } else {
            joke.setText(getString(R.string.no_joke_passed, KEY_JOKE));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
