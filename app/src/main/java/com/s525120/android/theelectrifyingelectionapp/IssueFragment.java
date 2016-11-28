package com.s525120.android.theelectrifyingelectionapp;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.s525120.android.theelectrifyingelectionapp.dummy.DummyContent;

/**
 * A fragment representing a list of Items.
 * <p>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p>
 * Activities containing this fragment MUST implement the {@link com.s525120.android.theelectrifyingelectionapp.IssueFragment.OnIssueInteraction}
 * interface.
 */
public class IssueFragment extends android.support.v4.app.Fragment implements AbsListView.OnItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public String issue_ratings[]={"0","0","0"};
    public String issue[]={"EDUCATION","TRANSPORT","NASA"};
    public String name="";

    private OnIssueInteraction mListener;
    public boolean show_status=true;
    public int selected=0;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    public  ArrayAdapter mAdapter;

    // TODO: Rename and change types of parameters
    public static IssueFragment newInstance(String param1, String param2) {
        IssueFragment fragment = new IssueFragment();
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
    public IssueFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        // TODO: Change Adapter to display your content
        mAdapter = new ArrayAdapter<DummyContent.DummyItem>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, DummyContent.ITEMS);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // TODO: Change Adapter to display your content

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item5, container, false);

        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);

        mAdapter = new ArrayAdapter<String>(getActivity(),
                R.layout.custome_listview2, R.id.issue_tv,issue_ratings){

            @Override
            public View getView(int position, View convertView, ViewGroup parent){


                View view = super.getView(position, convertView, parent);
                boolean isResetclicked=mListener.getResetstatus();
                show_status=mListener.getSelected();
                selected=mListener.getIssuestatus();


                TextView missue_tv=(TextView)view.findViewById(R.id.issue_tv);
                TextView mrating_tv=(TextView)view.findViewById(R.id.rating_tv);
                RatingBar mrating_bar=(RatingBar)view.findViewById(R.id.ratingBar);
                missue_tv.setText(issue[position]);
                if(show_status==false){
                    mrating_tv.setText("Select a candidate by a long pressing the person in Candidates tab");
                    mrating_bar.setNumStars(0);
                    mrating_bar.setVisibility(View.INVISIBLE);


                }
                else if(isResetclicked==true){
                    mrating_tv.setText("Select a candidate by a long pressing the person in Candidates tab");
                    mrating_bar.setNumStars(0);
                    mrating_bar.setVisibility(View.INVISIBLE);
                }
                else{
                    mrating_tv.setText(issue_ratings[position]);
               // mrating_tv.setText(issue_ratings[position]);
                mrating_bar.setNumStars(Integer.parseInt(issue_ratings[position]));
                mrating_bar.setRating(Integer.parseInt(issue_ratings[position]));
                mrating_bar.setVisibility(View.VISIBLE);}

      /*  TextView trans_tv=(TextView)v.findViewById(R.id.transportation_tv);
        TextView nasa_tv=(TextView)v.findViewById(R.id.nasa_tv);*/
       /* edu_tv.setText("Education :"+issue_ratings[0]);
        trans_tv.setText("Transportation :"+issue_ratings[1]);
        nasa_tv.setText("Nasa :" + issue_ratings[2]);*/
                // Inflate the layout for this fragment

                return view;
            }};
        mListView.setAdapter(mAdapter);

      //  ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnIssueInteraction) activity;
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
           // mListener.onFragmentInteraction(DummyContent.ITEMS.get(position).id);
        }
    }
    public void setDetails(int id){
        //RelativeLayout lay=(RelativeLayout) getLayoutInflater().inflate(R.layout.fragment_issues,getView());
        if(id>=0 && id<=7)
            show_status=true;
selected=id;
            issue_ratings=Candidate.candidates[selected].getRating().split(" ");
       // name=Candidate.candidates[id].getName();




           if(mAdapter!=null){ mAdapter.notifyDataSetChanged();}

          }
    public void refreshData(){
        if(mAdapter!=null){
            issue_ratings=Candidate.candidates[selected].getRating().split(" ");

        mAdapter.notifyDataSetChanged();}

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
    public interface OnIssueInteraction {
        // TODO: Update argument type and name
      public void onCandidateSelect();
        public boolean getResetstatus();
        public boolean getSelected();
        public int getIssuestatus();
    }

}
