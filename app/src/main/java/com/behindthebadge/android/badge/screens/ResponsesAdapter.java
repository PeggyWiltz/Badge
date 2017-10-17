package com.behindthebadge.android.badge.screens;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.behindthebadge.android.badge.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by us50416 on 5/23/17.
 */

public class ResponsesAdapter extends ArrayAdapter<ResponseModel> {

    private Context context;

    ResponseModel[] responses;

    public ResponsesAdapter(@NonNull Context context) {
        super(context, R.layout.response_layout);
        this.context = context;

        List<ResponseModel> demoResponses = new ArrayList<>();

        demoResponses.add(new ResponseModel("Steve Dallas", "Bloom County Fire", ResponseModel.ResponseStatus.YES));
        demoResponses.add(new ResponseModel("Milo Bloom", "Bloom Beacon", ResponseModel.ResponseStatus.MAYBE));
        demoResponses.add(new ResponseModel("Opus", "GOP", ResponseModel.ResponseStatus.NO));
        demoResponses.add(new ResponseModel("Roscoe P. Coltrane", "Hazzard County Sherriff", ResponseModel.ResponseStatus.NOT_RESPONDED));

        responses = demoResponses.toArray(new ResponseModel[demoResponses.size()]);
    }

    @Override
    public int getCount() {
        return responses.length;
    }

    @Nullable
    @Override
    public ResponseModel getItem(int position) {
        return responses[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.response_layout, parent, false);

        ResponseModel model = responses[position];

        TextView responderNameView = (TextView) view.findViewById(R.id.responder_name);
        responderNameView.setText(model.getName());

        TextView organizationView = (TextView) view.findViewById(R.id.responder_org);
        organizationView.setText(model.getOrganization());

        ImageView statusView = (ImageView) view.findViewById(R.id.response_status_icon);
        switch (model.getStatus()) {
            case YES:
                statusView.setBackgroundResource(R.drawable.mini_available_circle_button);
                break;
            case NO:
                statusView.setBackgroundResource(R.drawable.mini_not_available_circle_button);
                break;
            case MAYBE:
                statusView.setBackgroundResource(R.drawable.mini_tentative_circle_button);
                break;
            case NOT_RESPONDED:
            default:
                statusView.setBackgroundResource(R.drawable.mini_no_response_circle_button);
                break;
        }

        return view;
    }
}
