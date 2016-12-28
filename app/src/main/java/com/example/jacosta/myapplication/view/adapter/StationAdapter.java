package com.example.jacosta.myapplication.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jacosta.myapplication.R;
import com.example.jacosta.myapplication.databinding.StationCardBinding;
import com.example.jacosta.myapplication.model.SubwayStations;
import com.example.jacosta.myapplication.viewmodel.StationCardViewModel;

import java.util.List;

/**
 * Created by jacosta on 12/28/16.
 */

public class StationAdapter extends RecyclerView.Adapter<StationAdapter.StationViewHolder> {

    private List<SubwayStations.Station> mData;

    public StationAdapter(List<SubwayStations.Station> mData) {
        this.mData = mData;
    }

    @Override
    public StationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        StationCardBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.page_home, parent, false);
        return new StationViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(StationViewHolder holder, int position) {
        holder.bindStation(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class StationViewHolder extends RecyclerView.ViewHolder {

        StationCardBinding binding;
        StationCardViewModel viewModel;

        public StationViewHolder(StationCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.viewModel = new StationCardViewModel();
            binding.setViewModel(viewModel);
        }

        public void bindStation(SubwayStations.Station station){
            viewModel.setStation(station);
            binding.executePendingBindings();
        }
    }
}
