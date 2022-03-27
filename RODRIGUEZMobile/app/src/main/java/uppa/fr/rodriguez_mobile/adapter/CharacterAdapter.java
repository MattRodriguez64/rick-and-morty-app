package uppa.fr.rodriguez_mobile.adapter;


import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import uppa.fr.rodriguez_mobile.R;
import uppa.fr.rodriguez_mobile.model.Character;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {

    private Context context;
    private ArrayList<Character> characters;
    OnItemClickListener listener;


    public CharacterAdapter(Context ct, ArrayList<Character> characters){
        this.context = ct;
        this.characters = characters;
    }


    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.character_item, parent, false);
        return new CharacterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        holder.characterName.setTypeface(null, Typeface.BOLD);
        holder.characterName.setText(characters.get(position).getName());
        holder.characterSpecie.setText(characters.get(position).getSpecies());
        holder.characterStatus.setText(characters.get(position).getStatus());
        Picasso.get().load(characters.get(position).getImage()).into(holder.characterImage);
        holder.setOnItemClickListener(this.listener);
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
        notifyDataSetChanged();
    }

    public class CharacterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView characterName;
        TextView characterSpecie;
        TextView characterStatus;
        ImageView characterImage;
        private CharacterAdapter.OnItemClickListener listener;

        public CharacterViewHolder(@NonNull View itemView) {
            super(itemView);
            characterName = itemView.findViewById(R.id.character_name);
            characterSpecie = itemView.findViewById(R.id.character_spiece);
            characterStatus = itemView.findViewById(R.id.character_status);
            characterImage = itemView.findViewById(R.id.character_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(listener != null) {
                this.listener.onItemClick(getAdapterPosition());

            }
        }

        public void setOnItemClickListener(CharacterAdapter.OnItemClickListener i) {
            this.listener = i;
        }
    }
}
