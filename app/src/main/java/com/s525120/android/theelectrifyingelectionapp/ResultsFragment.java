package com.s525120.android.theelectrifyingelectionapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.s525120.android.theelectrifyingelectionapp.dummy.DummyContent;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * A fragment representing a list of Items.
 * <p>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class ResultsFragment extends android.support.v4.app.Fragment implements AbsListView.OnItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener1;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    public ArrayAdapter mAdapter;

    public int vote_count = 0;
    public double ascending_arr[]=new double[8];


    // TODO: Rename and change types of parameters
    public static ResultsFragment newInstance(String param1, String param2) {
        ResultsFragment fragment = new ResultsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ResultsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Arrays.fill(ascending_arr,0);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // TODO: Change Adapter to display your content

    }
    /*public static void setInsets(Activity context, View view) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) return;
        SystemBarTintManager tintManager = new SystemBarTintManager(context);
        SystemBarTintManager.SystemBarConfig config = tintManager.getConfig();
        view.setPadding(0, config.getPixelInsetTop(true), config.getPixelInsetRight(), config.getPixelInsetBottom());
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item2, container, false);

        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);

        mAdapter = new ArrayAdapter<Candidate>(getActivity(),
                R.layout.custom_listview1, R.id.percent_tv, Candidate.candidates) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                double total = (double) (mListener1.ResultsMethod());
                View v = super.getView(position, convertView, parent);
                TextView mname_tv = (TextView) v.findViewById(R.id.name_tv);
                TextView mpercent_tv = (TextView) v.findViewById(R.id.percent_tv);
                mname_tv.setText(getItem(position).getName());


                String percentage = String.format("%.1f", (((double) Candidate.candidates[position].getNumber_votes()) / total) * 100);


                //String percentage=String.format("%.1f",(double)Candidate.candidates[position].getNumber_votes());
                double color_setter=(((double) Candidate.candidates[position].getNumber_votes()) / total) * 100;
                //Candidate.candidates[position].setPercentage(color_setter);
                //ascending_arr[position]=Candidate.candidates[position].getPercentage();
               //Candidate.candidates.equals(ascending_arr);
                //Log.d("eqauls","ascending_arr"+ascending_arr);
                //Arrays.sort(ascending_arr);

               /* if(color_setter!=null)
                 color_setter=(Integer.parseInt(percentage))+0;*/


                if (total == 0) {
                    mpercent_tv.setText("0 %");
                }
                else{
                    mpercent_tv.setText(percentage + "%");
                    //mpercent_tv.setText(ascending_arr[position]+"%");
                }
                if(color_setter>=90){
                   v.setBackgroundColor(Color.GRAY);
                }
                else if(color_setter>=80 && color_setter<90){
                    v.setBackgroundColor(Color.parseColor("#A52A2A"));
                }
                else if(color_setter>=70 && color_setter<80){
                    v.setBackgroundColor(Color.CYAN);
                }
                else if(color_setter>=60 && color_setter<70){
                    v.setBackgroundColor(Color.GREEN);
                }
                else if(color_setter>=50 && color_setter<60){
                    v.setBackgroundColor(Color.MAGENTA);
                }
                else if(color_setter>=40 && color_setter<50){
                    v.setBackgroundColor(Color.parseColor("#FF7F50"));
                }
                else if(color_setter>=30 && color_setter<40){
                    v.setBackgroundColor(Color.parseColor("#9932CC"));
                }
                else if(color_setter>=20 && color_setter<30){
                    v.setBackgroundColor(Color.parseColor("#FF4500"));
                }
                else if(color_setter>=10 && color_setter<20){
                    v.setBackgroundColor(Color.YELLOW);
                }
                else{
                    v.setBackgroundColor(Color.WHITE);
                }


                Log.d("tag","percentage "+percentage);
                return v;
            }
        };
        mListView.setAdapter(mAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);


        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener1 = (OnFragmentInteractionListener) activity;

        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener1 = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener1) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            // mListener1.ResultsMethod(DummyContent.ITEMS.get(position).id);
        }
    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public int ResultsMethod();
    }


    public void refreshData() {
        if (mAdapter != null)
            mAdapter.notifyDataSetChanged();

    }
   /* public void setTotalcount(int count){
        vote_count=count;
    }*/

}
