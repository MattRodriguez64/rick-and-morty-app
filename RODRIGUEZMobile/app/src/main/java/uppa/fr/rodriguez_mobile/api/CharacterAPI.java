package uppa.fr.rodriguez_mobile.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import uppa.fr.rodriguez_mobile.model.Character;

public interface CharacterAPI {
    @GET("api/characters/")
    Call<List<Character>> getCharacters();

    @GET("api/characters/{id}")
    Call<Character> getCharacter(@Path("id") int id);

    @GET("api/characters/{id}/episodes")
    Call<List<Episode>> getEpisodes(@Path("id") int id);
}
