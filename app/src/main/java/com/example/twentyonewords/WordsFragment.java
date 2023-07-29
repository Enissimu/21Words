package com.example.twentyonewords;


import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WordsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WordsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "words";
    private static final String ARG_PARAM2 = "descs";

    // TODO: Rename and change types of parameters
    private String mParam1;

    private View rootView;

    private  LinearLayout wordsLayout;
    private String mParam2;

    public WordsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WordsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WordsFragment newInstance(String param1, String param2) {
        WordsFragment fragment = new WordsFragment();
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
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_words, container, false);

        wordsLayout= rootView.findViewById(R.id.wordsLayout);

        String[] words = getArguments().getStringArray("words");
        String[] descs = getArguments().getStringArray("descs");





        wordsPage(words,descs,wordsLayout,container);


        return rootView;

    }



    protected  void wordsPage(String[] words,String[] descs, LinearLayout wordsLayout, ViewGroup container) {



        for (int i = 0; i < 21; i++) {
            TextView textView = new TextView(container.getContext());

            TextView textView2 = new TextView(container.getContext());




            textView2.setText(descs[i]);
            textView2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            textView2.setTextColor(Color.parseColor("#ab3f4a"));
            textView2.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.FILL_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));


            textView.setText(words[i]);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
            textView.setTextColor(Color.parseColor("#86bd50"));
            textView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.FILL_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));

            wordsLayout.addView(textView);
            wordsLayout.addView(textView2);
        }
    }



}