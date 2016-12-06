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

public class FamilyActivity extends AppCompatActivity {

    private TextToSpeech t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word("father","apa",R.drawable.family_father));
        words.add(new Word("mother","ata",R.drawable.family_mother));
        words.add(new Word("son","angsi",R.drawable.family_son));
        words.add(new Word("daughter","tune",R.drawable.family_daughter));
        words.add(new Word("older brother","taachi",R.drawable.family_older_brother));
        words.add(new Word("younger brother","chalitti",R.drawable.family_younger_brother));
        words.add(new Word("older sister","tete",R.drawable.family_older_sister));
        words.add(new Word("younger sister","kolliti",R.drawable.family_younger_sister));
        words.add(new Word("grandmother","ama",R.drawable.family_grandmother));
        words.add(new Word("grandfather","paapa",R.drawable.family_grandfather));

        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });

        final WordAdapter adapter = new WordAdapter(this,words,R.color.category_family);
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
