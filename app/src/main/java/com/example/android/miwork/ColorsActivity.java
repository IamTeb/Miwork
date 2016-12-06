package com.example.android.miwork;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Locale;

public class ColorsActivity extends AppCompatActivity {

    private TextToSpeech t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word("red","wetetti",R.drawable.color_red));
        words.add(new Word("mustard yellow","chiwiita",R.drawable.color_mustard_yellow));
        words.add(new Word("dusty yellow","topiisa",R.drawable.color_dusty_yellow));
        words.add(new Word("green","chokokki",R.drawable.color_green));
        words.add(new Word("brown","takaakki",R.drawable.color_brown));
        words.add(new Word("gray","topoppi",R.drawable.color_gray));
        words.add(new Word("black","kululli",R.drawable.color_black));
        words.add(new Word("white","kelelli",R.drawable.color_white));

        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });

       final WordAdapter adapter = new WordAdapter(this,words,R.color.category_colors);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Word value = (Word) parent.getItemAtPosition(position);
                t1.speak(value.getmDefaultTranslation(), TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        listView.setAdapter(adapter);

    }
    public void onPause()
    {
        if (t1 != null) {
            t1.stop();
            t1.shutdown();
        }
        super.onPause();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.main_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }
}
