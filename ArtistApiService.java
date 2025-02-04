import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ArtistApiService {
    @GET("artists")
    Call<List<String>> getArtists();

    @GET("artists")
    Call<List<String>> getArtistsByLetter(@Query("letter") String letter);

    @GET("artists")
    Call<List<String>> getArtistsByType(@Query("type") String type);
}