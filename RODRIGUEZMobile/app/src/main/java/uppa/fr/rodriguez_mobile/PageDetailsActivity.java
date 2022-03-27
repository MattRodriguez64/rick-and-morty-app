package uppa.fr.rodriguez_mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uppa.fr.rodriguez_mobile.adapter.EpisodeAdapter;
import uppa.fr.rodriguez_mobile.api.CharacterAPI;
import uppa.fr.rodriguez_mobile.model.Episode;
import uppa.fr.rodriguez_mobile.model.Character;

public class PageDetailsActivity extends AppCompatActivity {

    final private String alive = "#27ae60";
    final private String unknown = "#f1c40f";
    final private String dead = "#c0392b";
    final private String defaultColor = "#FFFFFF";

    RecyclerView recyclerView;
    ImageView imageView;
    TextView name;
    TextView status;
    TextView specie;
    TextView gender;
    TextView episodeNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_details);

        imageView = (ImageView) findViewById(R.id.character_image_back);
        name = (TextView) findViewById(R.id.character_name_detail);
        status = (TextView) findViewById(R.id.character_status_detail);
        specie = (TextView) findViewById(R.id.character_spiece_detail);
        gender = (TextView) findViewById(R.id.character_gender_detail);
        recyclerView = (RecyclerView) findViewById(R.id.episode_recycler_list_detail);
        recyclerView.setNestedScrollingEnabled(false);
        episodeNumber = (TextView) findViewById(R.id.episode_number);

        String tempCharacterIdClicked = getIntent().getStringExtra("characterIdClicked");

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://623b4baf2e056d1037f07a74.mockapi.io/")
                .build();
        CharacterAPI api = retrofit.create(CharacterAPI.class);
        int id = Integer.valueOf(tempCharacterIdClicked);
        Call<Character> charactersCallApi = api.getCharacter(id);

        Call<List<Episode>> episodesCallApi = api.getEpisodes(id);
        episodesCallApi.enqueue(new Callback<List<Episode>>() {
            @Override
            public void onResponse(Call<List<Episode>> call, Response<List<Episode>> response) {
                ArrayList<Episode> episodes = new ArrayList<Episode>();
                episodes = new ArrayList<Episode>(response.body());

                EpisodeAdapter episodeAdapter = new EpisodeAdapter(getApplicationContext(), episodes);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(episodeAdapter);

                Log.d("SIZE", String.valueOf(response.body().size()));
                String formattedEpisodeNumber = "Episodes (" + response.body().size() + ")";
                episodeNumber.setTypeface(null, Typeface.BOLD);
                episodeNumber.setText(formattedEpisodeNumber);
            }

            @Override
            public void onFailure(Call<List<Episode>> call, Throwable t) {

            }
        });

        charactersCallApi.enqueue(new Callback<Character>() {

            @Override
            public void onResponse(Call<Character> call, Response<Character> response) {
                name.setTypeface(null, Typeface.BOLD);
                name.setText(response.body().getName());
                switch(response.body().getStatus()){
                    case "Alive":
                        status.setTextColor(Color.parseColor(alive));
                        break;
                    case "unknown":
                        status.setTextColor(Color.parseColor(unknown));
                        break;
                    case "Dead":
                        status.setTextColor(Color.parseColor(dead));
                        break;
                    default:
                        status.setTextColor(Color.parseColor(defaultColor));
                }

                String formattedStatus = "(" + response.body().getStatus() + ")";
                status.setText(formattedStatus);

                specie.setTextColor(Color.parseColor(unknown));
                String formattedSpecie = "( " + response.body().getSpecies() + " - ";
                specie.setText(formattedSpecie);

                gender.setTextColor(Color.parseColor(unknown));
                String formattedGender = response.body().getGender() + " )";
                gender.setText(formattedGender);

                Picasso.get().load(response.body().getImage()).into(imageView);
                Log.d("Log", response.body().getName());


            }

            @Override
            public void onFailure(Call<Character> call, Throwable t) {
                Log.d("Yo", "Errror!");
            }

        });




    }
}