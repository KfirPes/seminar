package service;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArtistService {
    private final ArtistApiService apiService;

    public ArtistService() {
        apiService = RetrofitClient.getClient().create(ArtistApiService.class);
    }

    public void getAllArtists(Callback<List<String>> callback) {
        Call<List<String>> call = apiService.getArtists();
        call.enqueue(callback);
    }

    public void getArtistsByLetter(String letter, Callback<List<String>> callback) {
        Call<List<String>> call = apiService.getArtistsByLetter(letter);
        call.enqueue(callback);
    }

    public void getArtistsByType(String type, Callback<List<String>> callback) {
        Call<List<String>> call = apiService.getArtistsByType(type);
        call.enqueue(callback);
    }
}