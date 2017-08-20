package com.group6.TakeOff;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by STzavelas on 08.08.17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Trip trip;
    Context c;
    int id;
    //DatabaseHelper myDb;


    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView Project;
        public TextView Price;
        public TextView DATE_FROM;
        public TextView DATE_TO;

        public ViewHolder(final View itemView){
            super (itemView);
            Project = (TextView) itemView.findViewById(R.id.textProject);
            Price = (TextView) itemView.findViewById(R.id.textName);
            DATE_FROM =  (TextView) itemView.findViewById(R.id.dateFrom);
            DATE_TO =  (TextView) itemView.findViewById(R.id.dateTo);

        }
    }

    private List<Trip> mTrips;

    public RecyclerViewAdapter(Context c, List<Trip> trips) {
        this.c = c;
        mTrips = trips;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.recycler_blueprint, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {

        Trip trip = mTrips.get(position);

        TextView mProject = holder.Project;
        mProject.setText(trip.getProject());

        TextView mPrice = holder.Price;
        String stringdouble= Double.toString(trip.getPrice());
        mPrice.setText(stringdouble);

        TextView mDateFrom = holder.DATE_FROM;
        mDateFrom.setText(trip.getDateFrom());

        TextView mDateTo = holder.DATE_TO;
        mDateTo.setText(trip.getDateTo());

    }

    @Override
    public int getItemCount() {
        return mTrips.size();
    }


    public void dismissProject(int pos){

        mTrips.remove(pos);
        this.notifyItemRemoved(pos);

        /*pos = pos+1;
        id = pos;
        DatabaseHelper myDb = new DatabaseHelper(c);
        if(myDb.deleteProject(pos)){

        }else{

        }*/


    }

}
