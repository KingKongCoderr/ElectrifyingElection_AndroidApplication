package com.s525120.android.theelectrifyingelectionapp;

import android.app.Activity;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link com.s525120.android.theelectrifyingelectionapp.IssuesFragment.OnIssuesInteraction} interface
 * to handle interaction events.
 * Use the {@link IssuesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IssuesFragment extends android.support.v4.app.Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

private AbsListView mListView;
    private ArrayAdapter<String> mAdapter;
    private OnIssuesInteraction mListener;
    public String issue_ratings[]={"0","0","0"};
    public String issue[]={"EDUCATION","TRANSPORT","NASA"};

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment IssuesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static IssuesFragment newInstance(String param1, String param2) {
        IssuesFragment fragment = new IssuesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    public IssuesFragment() {
        // Required empty public constructor
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
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // TODO: Change Adapter to display your content

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_issues, container, false);
mListView =(AbsListView) v.findViewById(android.R.id.list);
        mAdapter = new ArrayAdapter<String>(getActivity(),
                R.layout.custome_listview2, R.id.issue_tv,issue_ratings){

            @Override
            public View getView(int position, View convertView, ViewGroup parent){

                View view = super.getView(position, convertView, parent);
        TextView missue_tv=(TextView)view.findViewById(R.id.issue_tv);
                TextView mrating_tv=(TextView)view.findViewById(R.id.rating_tv);
       missue_tv.setText(issue[position]);
                if(issue_ratings[position]=="0"){
                    mrating_tv.setText("Select a candidate by a long press");
                }
                else
                mrating_tv.setText(issue_ratings[position]);

      /*  TextView trans_tv=(TextView)v.findViewById(R.id.transportation_tv);
        TextView nasa_tv=(TextView)v.findViewById(R.id.nasa_tv);*/
       /* edu_tv.setText("Education :"+issue_ratings[0]);
        trans_tv.setText("Transportation :"+issue_ratings[1]);
        nasa_tv.setText("Nasa :" + issue_ratings[2]);*/
        // Inflate the layout for this fragment

        return view;
    }};
        mListView.setAdapter(mAdapter);

    /*// TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.OnCandidateSelect(uri);

        }*/
        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnIssuesInteraction) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    public void setDetails(int id){
        //RelativeLayout lay=(RelativeLayout) getLayoutInflater().inflate(R.layout.fragment_issues,getView());
        if(mAdapter!=null){
        issue_ratings=Candidate.candidates[id].getRating().split(" ");

        mAdapter.notifyDataSetChanged();}


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
    public interface OnIssuesInteraction {
        // TODO: Update argument type and name
        public void onCandidateSelect();
    }
}


