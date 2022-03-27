package uppa.fr.rodriguez_mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uppa.fr.rodriguez_mobile.adapter.CharacterAdapter;
import uppa.fr.rodriguez_mobile.api.CharacterAPI;
import uppa.fr.rodriguez_mobile.model.Character;

public class ListActivity extends AppCompatActivity {

    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyclerView = findViewById(R.id.collection_recycler_list);

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://623b4baf2e056d1037f07a74.mockapi.io/")
                .build();
        CharacterAPI api = retrofit.create(CharacterAPI.class);
        Call<List<Character>> charactersCallApi = api.getCharacters();

        charactersCallApi.enqueue(new Callback<List<Character>>() {

            @Override
            public void onResponse(Call<List<Character>> call, Response<List<Character>> response) {
                ArrayList<Character> characters = new ArrayList<Character>();
                Log.d("Log", String.valueOf(response.body().size()));
                characters = new ArrayList<Character>(response.body());
                CharacterAdapter characterAdapter = new CharacterAdapter(getApplicationContext(), characters);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(characterAdapter);

                ArrayList<Character> finalCharacters = characters;
                characterAdapter.setOnItemClickListener(position -> {
                    String characterIdClicked = finalCharacters.get(position).getId();
                    Intent intent = new Intent(getApplicationContext(), PageDetailsActivity.class);
                    Log.d("Log", "Character " + finalCharacters.get(position).getName() + " clicked");
                    intent.putExtra("characterIdClicked", characterIdClicked);
                    startActivity(intent);
                });

            }

            @Override
            public void onFailure(Call<List<Character>> call, Throwable t) {
                Log.d("Yo", "Errror!");
            }

        });

    }
}