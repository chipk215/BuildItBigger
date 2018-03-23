package com.keyeswest.jokeviewer;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link JokeViewerMainActivityFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JokeViewerMainActivityFragment extends Fragment {
    private static final String TAG="JokeViewerMainActivityFragment";

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";


    private String mJoke;



    public JokeViewerMainActivityFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     *
     * @return A new instance of fragment JokeViewerMainActivityFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static JokeViewerMainActivityFragment newInstance(String param1) {
        JokeViewerMainActivityFragment fragment = new JokeViewerMainActivityFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mJoke = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_joke_viewer_main_activity, container, false);
        TextView jokeView = view.findViewById(R.id.joke_tv);
        jokeView.setText(mJoke);

        Button stopButton = view.findViewById(R.id.stop_btn);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResponseResult(false);
            }
        });


        Button moreButton = view.findViewById(R.id.more_btn);
        moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResponseResult(true);
            }
        });

        return view;
    }


    private void setResponseResult(boolean response){
        Intent intent = new Intent();
        intent.putExtra(JokeViewerMainActivity.RESPONSE_EXTRA, response);
        getActivity().setResult(Activity.RESULT_OK, intent);
        Log.d(TAG,"Setting result and ending activity");
        getActivity().finish();
    }

}
