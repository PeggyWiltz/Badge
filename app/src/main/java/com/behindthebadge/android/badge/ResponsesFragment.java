package com.behindthebadge.android.badge;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.behindthebadge.android.badge.R;
import com.behindthebadge.android.badge.screens.ResponsesAdapter;

/**
 * Created by us53216 on 4/26/17.
 */

public class ResponsesFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_responses, container, false);
        ListView list = (ListView) view.findViewById(R.id.responses_list);
        list.setAdapter(new ResponsesAdapter(getActivity()));

        return view;
    }

}
