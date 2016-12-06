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

public class PhrasesActivity extends AppCompatActivity {

    private TextToSpeech t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word("Where are you going?","minto wuksus"));
        words.add(new Word("What is your name?","tinna oyaase'na"));
        words.add(new Word("My name is...","oyaaset"));
        words.add(new Word("How are you feeling?","michaksas"));
        words.add(new Word("I'm feeling good.","kuchi achit"));
        words.add(new Word("Are you coming?","eenes'aa"));
        words.add(new Word("Yes, I'm coming","haa'eenam"));
        words.add(new Word("I'm coming","eenum"));
        words.add(new Word("Let's go","yoowutis"));
        words.add(new Word("Come here.","anni'nem"));

        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });

       final WordAdapter adapter = new WordAdapter(this,words,R.color.category_phrases);
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
