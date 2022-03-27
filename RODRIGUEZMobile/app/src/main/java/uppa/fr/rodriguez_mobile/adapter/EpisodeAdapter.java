package uppa.fr.rodriguez_mobile.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import uppa.fr.rodriguez_mobile.R;
import uppa.fr.rodriguez_mobile.api.Episode;
import uppa.fr.rodriguez_mobile.model.Character;

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder>{

    private Context context;
    private ArrayList<Episode> episodes;
    EpisodeAdapter.OnItemClickListener listener;

    public EpisodeAdapter(Context context, ArrayList<Episode> episodes) {
        this.context = context;
        this.episodes = episodes;
    }

    @NonNull
    @Override
    public EpisodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.episode_item, parent, false);
        return new EpisodeAdapter.EpisodeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeViewHolder holder, int position) {
        String formattedEpCounter = String.valueOf(position+1) + " -";
        holder.episodeCounter.setTypeface(null, Typeface.BOLD);
        holder.episodeCounter.setText(formattedEpCounter);

        String nameLabel = "Name";
        holder.episodeNameLabel.setTypeface(null, Typeface.BOLD);
        holder.episodeNameLabel.setText(nameLabel);

        holder.episodeName.setText(episodes.get(position).getName());

        String airdateLabel = "Air date";
        holder.episodeAirdateLabel.setTypeface(null, Typeface.BOLD);
        holder.episodeAirdateLabel.setText(airdateLabel);

        holder.episodeAirdate.setText(episodes.get(position).getAir_date());
    }

    @Override
    public int getItemCount() {
        return episodes.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(EpisodeAdapter.OnItemClickListener listener) {
        this.listener = listener;
        notifyDataSetChanged();
    }

    public class EpisodeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView episodeCounter;
        TextView episodeNameLabel;
        TextView episodeName;
        TextView episodeAirdateLabel;
        TextView episodeAirdate;
        private EpisodeAdapter.OnItemClickListener listener;

        public EpisodeViewHolder(@NonNull View itemView) {
            super(itemView);
            episodeCounter = (TextView) itemView.findViewById(R.id.episode_counter);
            episodeNameLabel = (TextView) itemView.findViewById(R.id.episode_name_label);
            episodeName = (TextView) itemView.findViewById(R.id.episode_name);
            episodeAirdateLabel = (TextView) itemView.findViewById(R.id.episode_airdate_label);
            episodeAirdate = (TextView) itemView.findViewById(R.id.episode_airdate);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(listener != null) {
                this.listener.onItemClick(getAdapterPosition());

            }
        }

        public void setOnItemClickListener(EpisodeAdapter.OnItemClickListener i) {
            this.listener = i;
        }
    }
}
