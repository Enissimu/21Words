package com.example.twentyonewords;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Locale;
import java.util.StringTokenizer;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExamplesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExamplesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "words";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View rootView;

    private LinearLayout exampleLayout;

    public ExamplesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExamplesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExamplesFragment newInstance(String param1, String param2) {
        ExamplesFragment fragment = new ExamplesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_words, container, false);

        exampleLayout= rootView.findViewById(R.id.wordsLayout);

        String[] words = getArguments().getStringArray("words");
        String[] examples = getArguments().getStringArray("examples");

        examplePage(words,examples,exampleLayout,container);

        Button button=(Button) rootView.findViewById(25);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int done=checkForAnswers(container,words);
                String muchmuch= done+"/"+"21";
                TextView much=new TextView(container.getContext());
                much.setTextColor(Color.GREEN);
                much.setText(muchmuch);
                LinearLayout horiz=rootView.findViewById(30);

                horiz.addView(much);



            }
        });


        return rootView;
    }




    @SuppressLint("ResourceType")
    protected void examplePage(String[] words, String[] examples, LinearLayout exampleLayout, ViewGroup container){

        //PUT LIKE THIS HORIZONTAL LAYOUTS IN VERTICAL  TEXT + MULTIEDITINPUT+ TEXT
        //IDK WHERE TO CHECK FOR RESULTS NOT SURE OR WHEN MAYBE WITH A BUTTON AND ARRAY  NEEDS TO BE CLEVER AND QUICK DESIGN ?
        for (int i = 0; i < 21; i++) {
            TextView textView = new TextView(container.getContext());





            AutoCompleteTextView answer=new AutoCompleteTextView(container.getContext());
            answer.setId(i);

            String exampleAndTwo=findBeforeAndAfterWord(examples[i],words[i]);


            ArrayAdapter<String> adapter = new ArrayAdapter<String>
                    (container.getContext(),android.R.layout.select_dialog_item,words);

            answer.setThreshold(1);
            answer.setAdapter(adapter);

            answer.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            answer.setTextColor(Color.parseColor("#f5f9dd"));



            textView.setText(exampleAndTwo);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            textView.setTextColor(Color.parseColor("#f5f9dd"));

            textView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            exampleLayout.addView(textView);
            exampleLayout.addView(answer);
        }
        LinearLayout horizon=new LinearLayout(container.getContext());
        horizon.setId(30);
        horizon.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        Button check=new Button(container.getContext());
        check.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        check.setText("CHECK");
        check.setId(25);

        horizon.addView(check);
        exampleLayout.addView(horizon);
    }


    public static String findBeforeAndAfterWord(String sentence, String word) {
        StringTokenizer token = new StringTokenizer(sentence.toLowerCase(Locale.ROOT));
        String emptySentence=" " ;
        String wordEx;
        int ct=0;
        while (token.hasMoreTokens()){
            wordEx=token.nextToken();
            if(wordEx.contains(word)){
                emptySentence+="______";
            }
            else{
                if(ct==0){
                    emptySentence+= wordEx.substring(0, 1).toUpperCase() + wordEx.substring(1)+" ";
                }
                else{
                    emptySentence+=wordEx+" ";

                }
            }
        ct++;
        }

            return  emptySentence;
        }

        public  static int checkForAnswers( ViewGroup container,String[] words){
            int ct=0;
            for(int i=0;i<21;i++){
                AutoCompleteTextView answer=container.findViewById(i);
                if (words[i].contentEquals(answer.getText())){
                    ct++;
                }
            }
        return ct;
        }
}