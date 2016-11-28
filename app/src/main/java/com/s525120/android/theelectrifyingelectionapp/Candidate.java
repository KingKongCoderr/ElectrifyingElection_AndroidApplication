package com.s525120.android.theelectrifyingelectionapp;

import java.util.ArrayList;

/**
 * Created by s525120 on 2/24/2016.
 */
public class Candidate {
    private String name;
    private int imageresourceid;
    private int number_votes;
    private String rating;
    private int id;
    private double percentage;
    /*public Candidate(){
        name="";
        imageresourceid=0;
        rating="";



    }*/
public Candidate(){

}
    public Candidate(String name, int imageresourceid, int number_votes, String rating,int id,double percentage) {
        this.name = name;
        this.imageresourceid = imageresourceid;
        this.number_votes = number_votes;
        this.rating = rating;
        this.id=id;
        this.percentage=percentage;

    }

    public static Candidate[] candidates={new Candidate("Hilary Clinton",R.drawable.hilary,0,"3 4 4",0,0),
    new Candidate("Bernie Sanders",R.drawable.bernie,0,"4 4 4",1,0),new Candidate("Jeb Bush",R.drawable.jeb,0,"3 3 3",2,0),
    new Candidate("Ben Carson",R.drawable.ben,0,"4 4 4",3,0),new Candidate("Ted Cruz",R.drawable.ted,0,"3 3 3",4,0),
    new Candidate("John Kasich",R.drawable.john,0,"2 3 3",5,0),new Candidate("Marco Rubio",R.drawable.marco,0,"3 4 3",6,0),
    new Candidate("Donald Trump",R.drawable.trump,0,"4 4 4",7,0)};

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageresourceid() {
        return imageresourceid;
    }

    public void setImageresourceid(int imageresourceid) {
        this.imageresourceid = imageresourceid;
    }

    public int getNumber_votes() {
        return number_votes;
    }

    public void setNumber_votes(int number_votes) {
        this.number_votes = number_votes;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Candidate candidate = (Candidate) o;

        return percentage == candidate.percentage;

    }

    @Override
    public int hashCode() {
        return number_votes;
    }

    public void resetvotes(){
        for(int i=0;i<candidates.length;i++)
            candidates[i].setNumber_votes(0);
    }

    @Override
    public String toString() {
        return name;
    }
}
