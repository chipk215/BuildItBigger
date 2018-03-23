package com.udacity.gradle.builditbigger;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.keyeswest.jokeviewer.JokeViewerMainActivity;


public class MainActivity extends AppCompatActivity {
    private final static String TAG="APP MainActivity";

    private int mJokeCount = 1;

    private final static int REQUEST_DISPLAY_JOKE = 0;
    private boolean mMoreJokes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK){
            return;
        }
        if (requestCode == REQUEST_DISPLAY_JOKE){
            if (data == null){
                return;
            }
            mMoreJokes = JokeViewerMainActivity.moreJokes(data);
            Log.d(TAG, "More Jokes:" + Boolean.toString(mMoreJokes));
            if (mMoreJokes){
                mJokeCount++;
                sendDisplayRequest("Joke number: " + Integer.toString(mJokeCount));
            }
        }
    }





    public void tellJoke(View view) {
     //   Toast.makeText(this, "derp", Toast.LENGTH_SHORT).show();
        sendDisplayRequest("First Joke");

    }

    private void sendDisplayRequest(String joke){
        Intent intent = JokeViewerMainActivity.newIntent(this,joke);
        startActivityForResult(intent, REQUEST_DISPLAY_JOKE);
    }


}
