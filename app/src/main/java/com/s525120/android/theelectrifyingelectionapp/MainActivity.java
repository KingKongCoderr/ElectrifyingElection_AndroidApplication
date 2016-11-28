package com.s525120.android.theelectrifyingelectionapp;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
//import android.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements CandidatesFragment.OnFragmentInteractionListener,ResultsFragment.OnFragmentInteractionListener,IssueFragment.OnIssueInteraction {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

   private int count_hil=0,count_bern=0,count_jeb=0,count_ben=0,count_ted=0,count_john=0,count_marco=0,count_trump=0;
    public int count[]=new int[8];

    public int count_total=0;
    public boolean isResetClicked=false;
    public boolean Candidate_selected=false;
    public int candidate_issue=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
Arrays.fill(count,0);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        if(savedInstanceState!=null){
            count=savedInstanceState.getIntArray("individual votes");
            count_total=savedInstanceState.getInt("total vote count", 0);
            isResetClicked=savedInstanceState.getBoolean("Reset clicked");
            Candidate_selected=savedInstanceState.getBoolean("Rating saved");
            candidate_issue=savedInstanceState.getInt("candidate issue",0);
        }

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
        switch(id){
            case R.id.reset_bt:reset();return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void reset() {
        Log.d("tag","on reset clicked-------");
        count_hil=0;count_bern=0;count_jeb=0;count_ben=0;count_ted=0;count_john=0;count_marco=0;count_trump=0;
        Arrays.fill(count,0);
        count_total=0;

        Candidate can=new Candidate();
        can.resetvotes();
        ResultsMethod();
        //isResetClicked=true;
//Candidate_selected=false;

        //getResetstatus();
        onCandidateSelect();



    }

    @Override
    public void onFragmentInteraction(int id) {
        Log.d("tag","Click RECEIVED ------------");
        count[id]++;
        count_total++;
        Candidate.candidates[id].setNumber_votes(Candidate.candidates[id].getNumber_votes() + 1);
      /*switch(id){
            case 0:count_hil++;Candidate.candidates[id].setNumber_votes(Candidate.candidates[id].getNumber_votes() + 1);break;
          case 1:count_bern++;Candidate.candidates[id].setNumber_votes(Candidate.candidates[id].getNumber_votes() + 1);break;
          case 2:count_jeb++;Candidate.candidates[id].setNumber_votes(Candidate.candidates[id].getNumber_votes() + 1);break;
          case 3:count_ben++;Candidate.candidates[id].setNumber_votes(Candidate.candidates[id].getNumber_votes() + 1);break;
          case 4:count_ted++;Candidate.candidates[id].setNumber_votes(Candidate.candidates[id].getNumber_votes() + 1);break;
          case 5:count_john++;Candidate.candidates[id].setNumber_votes(Candidate.candidates[id].getNumber_votes() + 1);break;
          case 6:count_marco++;Candidate.candidates[id].setNumber_votes(Candidate.candidates[id].getNumber_votes() + 1);break;
          case 7:count_trump++;Candidate.candidates[id].setNumber_votes(Candidate.candidates[id].getNumber_votes() + 1);break;
            default: return;
        }*/
        /*if (id == 0) {

            count_hil++;

            Candidate.candidates[0].setNumber_votes(Candidate.candidates[0].getNumber_votes() + 1);

            Log.d("tag", "HIL ------------"+Candidate.candidates[0].getNumber_votes());
            //String str=Candidate.candidates[0].getNumber_votes()+" hilary votes";
            //Log.d("tag",str);
        }else if(id==1){
            count_bern++;
            Candidate.candidates[1].setNumber_votes(Candidate.candidates[1].getNumber_votes() + 1);
        }else if(id==2){
            count_jeb++;
            Candidate.candidates[2].setNumber_votes(Candidate.candidates[2].getNumber_votes() + 1);
        }else if(id==3){
            count_ben++;
            Candidate.candidates[3].setNumber_votes(Candidate.candidates[3].getNumber_votes() + 1);
        }else if(id==4){
            count_ted++;
            Candidate.candidates[4].setNumber_votes(Candidate.candidates[4].getNumber_votes() + 1);
        }else if(id==5){
            count_john++;
            Candidate.candidates[5].setNumber_votes(Candidate.candidates[5].getNumber_votes() + 1);
        }else if(id==6){
            count_marco++;
            Candidate.candidates[6].setNumber_votes(Candidate.candidates[6].getNumber_votes() + 1);
        }else if(id==7){
            count_trump++;
            Candidate.candidates[7].setNumber_votes(Candidate.candidates[7].getNumber_votes() + 1);
        }
*/
        //count_total=count_hil+count_bern+count_jeb+count_ben+count_ted+count_john+count_marco+count_trump;
       /* for(Candidate str:Candidate.candidates){
            count_total+=str.getNumber_votes();
        }*/
        /*for(int i=0;i<8;i++){
            count_total+=Candidate.candidates[i].getNumber_votes();
        }*/
Log.d("tag", "count in click event" + count_total);
        //Log.d("tag", "vote count"+count_total);
        ResultsMethod();

/*ResultsFragment frag=new ResultsFragment();
        *//*Bundle b=new Bundle();
        frag.onCreate(b);*//*
        mSectionsPagerAdapter.notifyDataSetChanged();*/


        //count_total=count_hil+count_ben+count_jeb+count_ben+count_ted+count_john+count_marco+count_trump;


        //ResultsMethod();
        //ResultsFragment frag=new ResultsFragment();

// frag.setTotalcount(count_total);







    }
    public void onLongclick(int id){

        isResetClicked=false;
        if(id>=0 && id<=7)
Candidate_selected=true;
        candidate_issue=id;
        ((IssueFragment)findFragmentByPosition(2)).setDetails(candidate_issue);

        onCandidateSelect();

    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("total vote count", count_total);
        savedInstanceState.putIntArray("individual votes", count);
        savedInstanceState.putBoolean("Reset clicked", isResetClicked);
        savedInstanceState.putBoolean("Rating saved", Candidate_selected);
        savedInstanceState.putInt("candidate issue",candidate_issue);

    }

    /*@Override
    public void onFragmentInteraction(Uri uri) {

    }*/


   /* @Override
    public int[] ResultMethod() {
        Log.d("tag","IN RESULT ------------");
        int countlist[]=new int[9];
        *//*for(int i=0;i<8;i++)
            count_total+=Candidate.candidates[i].getNumber_votes();*//*
        countlist[0]=count_hil;
        countlist[1]=count_bern;
        countlist[2]=count_jeb;
        countlist[3]=count_ben;
        countlist[4]=count_ted;
        countlist[5]=count_john;
        countlist[6]=count_marco;
        countlist[7]=count_trump;
        countlist[8]=count_total;


        return countlist;

    }*/




    @Override
    public int ResultsMethod() {
        Log.d("tag","count in resultmethod"+count_total);




        ((ResultsFragment)findFragmentByPosition(1)).refreshData();
        return count_total;
    }
    @Override
    public void onCandidateSelect() {

        //(IssuesFragment)findFragmentByPosition(2).refreshData();
        //((IssuesFragment)findFragmentByPosition(2)).setDetails(id);
        ((IssueFragment)findFragmentByPosition(2)).refreshData();


    }

    @Override
    public boolean getResetstatus() {

        ((IssueFragment)findFragmentByPosition(2)).refreshData();
        return isResetClicked;
    }

    @Override
    public boolean getSelected() {
        ((IssueFragment)findFragmentByPosition(2)).refreshData();
        return Candidate_selected;
    }

    @Override
    public int getIssuestatus() {
        ((IssueFragment)findFragmentByPosition(2)).refreshData();
        return candidate_issue;
    }

    public Fragment findFragmentByPosition(int position) {
        FragmentPagerAdapter fragmentPagerAdapter = mSectionsPagerAdapter;
        return getSupportFragmentManager().findFragmentByTag(
                "android:switcher:" + mViewPager.getId() + ":"
                        + fragmentPagerAdapter.getItemId(position));
    }





    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            //return PlaceholderFragment.newInstance(position + 1);

           /* switch(position){
                case 0:return new CandidatesFragment();
                case 1:return new ResultFragment();
                default: return null;
            }*/
            if(position==0)
            {
                return new CandidatesFragment();

            }else if(position==1 )
            {

                return new ResultsFragment();
            }
            else if(position == 2)
                return new IssueFragment();
            else
                return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }



        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Candidates";
                case 1:
                    return "Results";
                case 2:
                    return "Issues";
            }
            return null;
        }
    }


    /**
     * A placeholder fragment containing a simple view.
    public static class PlaceholderFragment extends Fragment {
        *
         * The fragment argument representing the section number for this
         * fragment.
        private static final String ARG_SECTION_NUMBER = "section_number";

        *
         * Returns a new instance of this fragment for the given section
         * number.
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }*/

}
