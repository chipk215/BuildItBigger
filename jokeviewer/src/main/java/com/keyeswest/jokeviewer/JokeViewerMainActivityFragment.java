package com.keyeswest.jokeviewer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class JokeViewerMainActivityFragment extends Fragment {
    private static final String TAG="JokeViewerMainActivityFragment";

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    private String mJoke;

    public JokeViewerMainActivityFragment() {
        // Required empty public constructor
    }


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
        View view =inflater.inflate(R.layout.fragment_joke_viewer_main_activity,
                container, false);
        TextView jokeView = view.findViewById(R.id.joke_tv);
        jokeView.setText(mJoke);


        return view;
    }


}
