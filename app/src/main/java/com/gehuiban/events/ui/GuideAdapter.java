package com.gehuiban.events.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.gehuiban.events.R;
import com.gehuiban.events.api.Guide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class GuideAdapter extends RecyclerView.Adapter<GuideAdapter.ViewHolder> {
    private List<Guide> guideItems = new ArrayList<>();

    public void addGuides(List<Guide> newDays) {
        guideItems.addAll(newDays);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return guideItems.size();
    }

    public GuideAdapter() {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Guide guide = guideItems.get(position);
        if (guide == null) {
            return;
        }
        if (guide.getName() != null) {
            holder.nameText.setText(guide.getName());
        }
        if (guide.getLocation() != null) {
            holder.dateText.setText(guide.getLocation());
        }
        if (guide.getEndDate() != null) {
            String endDate = guide.getEndDate();
            holder.locationText.setText(endDate.substring(0,10));
        }
        Picasso.get().load(guide.getIcon()).placeholder(R.drawable.ic_event).into(holder.icon);
    }

    public void clearData() {
        guideItems.clear();
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameText;
        TextView locationText;
        TextView dateText;
        ImageView icon;

        private ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.card_item, parent, false));
            nameText = itemView.findViewById(R.id.name_text);
            locationText = itemView.findViewById(R.id.location_text);
            dateText = itemView.findViewById(R.id.date_text);
            icon = itemView.findViewById(R.id.icon);
        }
    }
}
