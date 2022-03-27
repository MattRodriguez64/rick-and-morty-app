package uppa.fr.rodriguez_mobile.api;

public class Episode {

    private String id;
    private String characterId;
    private String name;
    private String air_date;
    private String episode;

    public Episode(String id, String characterId, String name, String air_date, String episode) {
        this.id = id;
        this.characterId = characterId;
        this.name = name;
        this.air_date = air_date;
        this.episode = episode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCharacterId() {
        return characterId;
    }

    public void setCharacterId(String characterId) {
        this.characterId = characterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAir_date() {
        return air_date;
    }

    public void setAir_date(String air_date) {
        this.air_date = air_date;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }
}
