package com.freedev.hmiyh.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.freedev.hmiyh.R;
import com.freedev.hmiyh.datas.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterReitin extends ArrayAdapter<User> {
    public AdapterReitin(Context context, ArrayList<User> reiting, String sellektOfSort) {
        super(context, 0, reiting);
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        User user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_reiting, parent, false);
        }
        // Lookup view for data population

        CircleImageView img = convertView.findViewById(R.id.avatar);
        TextView tvNick = (TextView) convertView.findViewById(R.id.nick);
        TextView tvMoney = (TextView) convertView.findViewById(R.id.money);
        TextView tvData = (TextView) convertView.findViewById(R.id.work_name);
        // Populate the data into the template view using the data object
        Picasso.with(getContext())
                .load(user.personPhoto)
                .placeholder(R.drawable.avatar)
                .error(R.drawable.avatar)
                .into(img);
        tvNick.setText(user.personName);
        tvMoney.setText(String.format("%.1f",Double.parseDouble(user.personHourCost))+" $");
        tvData.setText(user.personWork);
        // Return the completed view to render on screen
        return convertView;
    }

    public void paramsOfSort() {

    }
}