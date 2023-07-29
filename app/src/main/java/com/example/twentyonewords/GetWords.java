package com.example.twentyonewords;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public  class GetWords {





    public  String[] randomizedWordList=new String[21];
    public  String[] wordList=new String[21];
    public  String[] descList=new String[21];
    public  String[] exampList=new String[21];

    public  String[] randomizedExampList=new String[21];

    public void fetchWords() throws IOException {
    Document doc = null;

    doc = Jsoup.connect("https://www.vocabulary.com/lists/52473").userAgent("Mozilla").get();

    Elements tempWords=doc.select("li.entry");
        int ct=0;
        Random rd = new Random(); // creating Random object


        List<Integer> list=new ArrayList<Integer>();

        int randomInt=rd.nextInt(1000);


        while (list.size()< 30){
            randomInt=rd.nextInt(1000);
            if (list.contains(randomInt)){
                continue;
            }
            list.add(rd.nextInt(1000));
        }

        int ct2=0;
        for (Element  word: tempWords){
            if (ct==21){
                break;
            }
            if (list.contains(ct2) && !word.attr("word").equals("null")){
                descList[ct]=word.select("div.definition").text();
                wordList[ct]=word.attr("word");
                word.select("div.example").select("a.source").remove();
                exampList[ct]=word.select("div.example").text();
                ct++;
            }
            ct2++;
        }
}

    public String[] returnWords(){
    return wordList;
    }

    public String[] returnDesc(){

        return descList;
    }


    public String[] returnExamp(){
        return randomizedExampList;
    }

    public String[] returnRandomWords(){
        return randomizedWordList;
    }

    public void randomizeLists(){
        Random rand = new Random();
        int ct =0;
        List<Integer> list=new ArrayList<Integer>();
        while ( ct<21 ){
            int randomIndexToSwap = rand.nextInt(21);

            if(list.contains(randomIndexToSwap)){
                continue;
            }
            System.out.println(randomIndexToSwap);
            list.add(randomIndexToSwap);

            String temp = exampList[ct];

            randomizedExampList[randomIndexToSwap]=temp;



            String temp2=wordList[ct];

            randomizedWordList[randomIndexToSwap]=temp2;
            ct++;

        }

    }





}

