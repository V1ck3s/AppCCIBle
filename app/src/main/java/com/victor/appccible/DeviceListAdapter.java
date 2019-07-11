package com.victor.appccible;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DeviceListAdapter extends RecyclerView.Adapter<DeviceListAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<BluetoothDevice> bdList = new ArrayList<BluetoothDevice>();
    public DeviceListAdapter(Context context) {
        this.context = context;
    }

    public void addBluetoothDevice(BluetoothDevice bd){
        boolean exist = false;
        for(BluetoothDevice b : bdList){
            if(bd.getAddress().equals(b.getAddress())){
                exist = true;
            }
        }
        if(!exist){
            bdList.add(bd);
        }

    }

    @NonNull
    @Override
    public DeviceListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_cell, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeviceListAdapter.MyViewHolder holder, int position) {
        holder.display(bdList.get(position));
    }

    @Override
    public int getItemCount() {
        return bdList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView date;
        private final TextView parcelName;


        /**
         * Instantiates a new My view holder.
         *
         * @param itemView the item view
         */
        public MyViewHolder(final View itemView) {
            super(itemView);

            date = ((TextView) itemView.findViewById(R.id.textView_treatmentsList_date));
            parcelName = ((TextView) itemView.findViewById(R.id.textView_treatmentsList_description));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //  new AlertDialog.Builder(itemView.getContext())
                    //        .setTitle(currentPair.first)
                    //      .setMessage(currentPair.second)
                    //    .show();
                    //view.getContext().startActivity(new Intent(view.getContext(), DetailsTreatmentActivity.class));
                  //  Intent intent;
                    //intent = new Intent(context , DetailsTreatmentActivity.class);
                    //intent.putExtra("TREATMENT_DETAILS", (Serializable) treatments.get(getAdapterPosition()));
                    //context.startActivity(intent);
                }
            });
        }

        /**
         * Display.
         *
         */
        public void display(BluetoothDevice bDevice) {
            date.setText(bDevice.getName());
            parcelName.setText(bDevice.getAddress());


        }
    }
}