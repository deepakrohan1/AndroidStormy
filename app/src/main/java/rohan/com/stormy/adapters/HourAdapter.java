package rohan.com.stormy.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import rohan.com.stormy.R;
import rohan.com.stormy.model.Day;
import rohan.com.stormy.model.Hour;

/**
 * Created by rohan on 3/12/16.
 */
public class HourAdapter extends RecyclerView.Adapter<HourAdapter.HourViewHolder> {

    ArrayList<Hour> daysList;

    public HourAdapter(ArrayList<Hour> daysList) {
        this.daysList = daysList;
    }

    @Override
    public HourViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_hourly_view,parent,false);

        HourViewHolder viewHolder = new HourViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HourViewHolder holder, int position) {
        holder.bindHour(daysList.get(position));
    }


    @Override
    public int getItemCount() {
        return daysList.size();
    }

    public class HourViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewTime;
        public TextView textViewSummary;
        public TextView textViewTemperature;
        public ImageView imageViewIcon;

        public HourViewHolder(View itemView) {
            super(itemView);

            textViewSummary = (TextView) itemView.findViewById(R.id.textViewSummary);
            textViewTime = (TextView) itemView.findViewById(R.id.textViewTime);
            textViewTemperature = (TextView) itemView.findViewById(R.id.textViewTemp);
            imageViewIcon = (ImageView) itemView.findViewById(R.id.imageViewIcon);
        }

        public void bindHour(Hour hour){
            textViewSummary.setText(hour.getSummary());
            textViewTime.setText(hour.getFormattedTime());
            textViewTemperature.setText(hour.getTemperature()+"");
            imageViewIcon.setImageResource(hour.getIconId());

        }
    }
}
