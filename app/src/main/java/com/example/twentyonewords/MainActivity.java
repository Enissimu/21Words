package com.example.twentyonewords;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;





import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import androidx.fragment.app.Fragment;


import android.os.Bundle;
import android.os.StrictMode;

import android.view.MenuItem;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Fragment wordsPage =new WordsFragment();
        final Fragment examplePage =new ExamplesFragment();





        GetWords wordsGetter=new GetWords();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);




        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);


        try {
            wordsGetter.fetchWords();



            Bundle wordsArgs = new Bundle();
            Bundle exampleArgs = new Bundle();
            wordsArgs.putStringArray("words",wordsGetter.returnWords());
            wordsArgs.putStringArray("descs",wordsGetter.returnDesc());

            wordsGetter.randomizeLists();


            exampleArgs.putStringArray("words",wordsGetter.returnRandomWords());
            exampleArgs.putStringArray("examples",wordsGetter.returnExamp());


            wordsPage.setArguments(wordsArgs);
            examplePage.setArguments(exampleArgs);



            bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    if (item.getItemId()==R.id.action_words){
                        loadFragment(wordsPage);
                        return true;

                    } else if (item.getItemId()==R.id.action_examples) {
                        loadFragment(examplePage);
                        return true;

                    }
                    else{
                        return true;
                    }

                }
            });
            getSupportFragmentManager().beginTransaction().replace(R.id.layoutlinear,wordsPage).commit();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


    private void loadFragment(Fragment fragment) {
// create a FragmentManager

        FragmentManager fm = getSupportFragmentManager();


// create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
// replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.layoutlinear, fragment);
        fragmentTransaction.commit(); // save the changes
    }





}
