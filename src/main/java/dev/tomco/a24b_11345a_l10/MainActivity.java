package application.app.src.main.java.dev.tomco.a24b_11345a_l10;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;

import application.app.src.main.java.dev.tomco.a24b_11345a_l10.Models.Artist;
import application.app.src.main.java.dev.tomco.a24b_11345a_l10.Models.Composer;
import application.app.src.main.java.dev.tomco.a24b_11345a_l10.Models.Creation;
import application.app.src.main.java.dev.tomco.a24b_11345a_l10.Models.HallOfFame;
import application.app.src.main.java.dev.tomco.a24b_11345a_l10.Models.Player;
import application.app.src.main.java.dev.tomco.a24b_11345a_l10.Models.Poem;
import application.app.src.main.java.dev.tomco.a24b_11345a_l10.Models.Poet;
import application.app.src.main.java.dev.tomco.a24b_11345a_l10.Models.Singer;
import application.app.src.main.java.dev.tomco.a24b_11345a_l10.Models.Song;
import application.app.src.main.java.dev.tomco.a24b_11345a_l10.Models.Tune;
import service.ArtistService;

public class MainActivity extends AppCompatActivity {

    private ArtistService artistService;
    private HallOfFame hallOfFame=new HallOfFame();
    private TextInputEditText etArtistUniqueDetail, etCreationUniqueDetail;
    private Artist artist;
    private String poet;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etArtistUniqueDetail = findViewById(R.id.main_ET_artistUniqueDetail);
        etCreationUniqueDetail = findViewById(R.id.main_ET_creationUniqueDetail);
        setupTypeListeners();
        updateHallFromDB();
        findViewById(R.id.main_BTN_addArtist).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String artistType = ((TextInputEditText) findViewById(R.id.main_ET_artistType)).getText().toString();
                boolean identifiesWithJudaism = Boolean.parseBoolean(((TextInputEditText) findViewById(R.id.main_ET_identification)).getText().toString());
                boolean significantInfluence = Boolean.parseBoolean(((TextInputEditText) findViewById(R.id.main_ET_influence)).getText().toString());
                int yearsActive = Integer.parseInt(((TextInputEditText) findViewById(R.id.main_ET_yearsActive)).getText().toString());
                boolean commercialSuccess = Boolean.parseBoolean(((TextInputEditText) findViewById(R.id.main_ET_commercialSuccess)).getText().toString());
                boolean historicalSignificance = Boolean.parseBoolean(((TextInputEditText) findViewById(R.id.main_ET_historicalSignificance)).getText().toString());
                boolean culturalImpact = Boolean.parseBoolean(((TextInputEditText) findViewById(R.id.main_ET_culturalImpact)).getText().toString());
                // בדיקת תנאי הקבלה להיכל
                if (identifiesWithJudaism && significantInfluence && yearsActive >= 25 && commercialSuccess && historicalSignificance && culturalImpact) {
                    addArtist();
                    Toast.makeText(MainActivity.this, "Artist added successfully", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(MainActivity.this, "Artist does not meet the eligibility criteria", Toast.LENGTH_SHORT).show();
                }
            }
            artistService = new ArtistService();

            // דוגמה לקבלת כל האומנים
        artistService.getAllArtists(new Callback<List<String>>() {
                @Override
                public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        Log.d("Artists", "All Artists: " + response.body());
                    }
                }

                @Override
                public void onFailure(Call<List<String>> call, Throwable t) {
                    Log.e("Artists", "Error fetching artists", t);
                }
            });


        findViewById(R.id.main_BTN_addCreation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCreationToArtist();
            }
        });
    }

                artistService.getArtistsByLetter("B", new Callback<List<String>>() {
                    @Override
                    public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            Log.d("Artists", "Artists starting with B: " + response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<String>> call, Throwable t) {
                        Log.e("Artists", "Error fetching artists", t);
                    }
                });
        artistService.getArtistsByType("Singer", new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("Artists", "Singers: " + response.body());
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Log.e("Artists", "Error fetching artists", t);
            }
        });
    }
    private void setupTypeListeners() {
        TextInputEditText etArtistType = findViewById(R.id.main_ET_artistType);
        TextInputEditText etCreationType = findViewById(R.id.main_ET_creationType);

        etArtistType.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateArtistUniqueDetail(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        etCreationType.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateCreationUniqueDetail(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
    }

    private void updateArtistUniqueDetail(String artistType) {
        switch (artistType.toLowerCase()) {
            case "poet":
                etArtistUniqueDetail.setHint("Enter Number of Poems");
                etArtistUniqueDetail.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
                etArtistUniqueDetail.setVisibility(View.VISIBLE);
                break;
            case "singer":
                etArtistUniqueDetail.setHint("Enter Number of Albums");
                etArtistUniqueDetail.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
                etArtistUniqueDetail.setVisibility(View.VISIBLE);
                break;
            case "player":
                etArtistUniqueDetail.setHint("Enter Instrument");
                etArtistUniqueDetail.setInputType(android.text.InputType.TYPE_CLASS_TEXT);
                etArtistUniqueDetail.setVisibility(View.VISIBLE);
                break;
            case "composer":
                etArtistUniqueDetail.setHint("Enter Music Type");
                etArtistUniqueDetail.setInputType(android.text.InputType.TYPE_CLASS_TEXT);
                etArtistUniqueDetail.setVisibility(View.VISIBLE);
                break;
            default:
                etArtistUniqueDetail.setVisibility(View.GONE);
                break;
        }
    }

    private void updateCreationUniqueDetail(String creationType) {
        switch (creationType.toLowerCase()) {
            case "poem":
                etCreationUniqueDetail.setHint("Enter Length in Words");
                etCreationUniqueDetail.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
                etCreationUniqueDetail.setVisibility(View.VISIBLE);
                break;
            case "tune":
                etCreationUniqueDetail.setHint("Enter Length in Seconds");
                etCreationUniqueDetail.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
                etCreationUniqueDetail.setVisibility(View.VISIBLE);
                break;
            case "song":
                etCreationUniqueDetail.setHint("Enter Length in Seconds");
                etCreationUniqueDetail.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
                etCreationUniqueDetail.setVisibility(View.VISIBLE);
                break;
            default:
                etCreationUniqueDetail.setVisibility(View.GONE);
                break;
        }
    }

    private void addArtist() {
        int add=0;
        String artistName = ((TextInputEditText) findViewById(R.id.main_ET_artistName)).getText().toString();
        String artistType = ((TextInputEditText) findViewById(R.id.main_ET_artistType)).getText().toString();
        String artistUniqueDetail = etArtistUniqueDetail.getText().toString();
        switch (artistType.toLowerCase()) {
            case "poet":
                artist = new Poet(artistName, Integer.parseInt(artistUniqueDetail));
                break;
            case "singer":
                artist = new Singer(artistName, Integer.parseInt(artistUniqueDetail));
                break;
            case "player":
                artist = new Player(artistName, artistUniqueDetail);
                break;
            case "composer":
                artist = new Composer(artistName, artistUniqueDetail);
                break;
        }

        if (artist != null) {
            hallOfFame.addArtist(artist);
            savedHallToDB();
            //updateHallFromDB();
            Toast.makeText(MainActivity.this, hallOfFame.getArtist(artist.getName()).getType(), Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(MainActivity.this, "Failed to Add Artist", Toast.LENGTH_SHORT).show();
        }
    }

    private void addCreationToArtist() {
        Artist artist = hallOfFame.getArtist(((TextInputEditText) findViewById(R.id.main_ET_artistOfCreationName)).getText().toString());
        String creationName = ((TextInputEditText) findViewById(R.id.main_ET_creationName)).getText().toString();
        String creationType = ((TextInputEditText) findViewById(R.id.main_ET_creationType)).getText().toString();
        String creationUniqueDetail = etCreationUniqueDetail.getText().toString();

        Creation creation = null;
        switch (creationType.toLowerCase()) {
            case "poem":
                creation = new Poem(artist,creationName, Integer.parseInt(creationUniqueDetail));
                break;
            case "tune":
                creation = new Tune(artist,creationName, Double.parseDouble(creationUniqueDetail));
                break;
            case "song":
                creation = new Song(artist,creationName, Double.parseDouble(creationUniqueDetail));
                break;
        }

        if (creation != null) {

            if (artist != null) {
                artist.addCreation(creation);
                hallOfFame.addCreation(creation);
                savedHallToDB();
                Toast.makeText(MainActivity.this, "Creation Added to Artist", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Artist Not Found", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(MainActivity.this, "Failed to Add Creation", Toast.LENGTH_SHORT).show();
        }
    }
    public void savedHallToDB() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = db.getReference("hall");

        dbRef.setValue(hallOfFame);
    }
    private void updateHallFromDB() {
        DatabaseReference titleRef = FirebaseDatabase.getInstance().getReference("hall");
        titleRef.addListenerForSingleValueEvent( // For one time data fetching from DB.
        //titleRef.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        DataSnapshot artists = snapshot.child("artists");
                        DataSnapshot creations=snapshot.child("creations");
                        for (DataSnapshot artistSnapshot : artists.getChildren()) {
                            String name=artistSnapshot.child("name").getValue(String.class);
                            String type = artistSnapshot.child("type").getValue(String.class);

                            Artist artist = null;

                            switch (type) {
                                case "Poet":
                                    int numPoems=artistSnapshot.child("numPoems").getValue(Integer.class);
                                    artist = new Poet(name,numPoems);
                                    break;
                                case "Singer":
                                    int numAlbums=artistSnapshot.child("numAlbums").getValue(Integer.class);
                                    artist = new Singer(name,numAlbums);
                                    break;
                                case "Player":
                                    String instrument=artistSnapshot.child("instrument").getValue(String.class);
                                    artist = new Player(name,instrument);
                                    break;
                                case "Composer":
                                    String musicType=artistSnapshot.child("musicType").getValue(String.class);
                                    artist = new Composer(name,musicType);
                                    break;
                                // טיפולים נוספים לסוגי אומנים אחרים
                            }

                            if (artist != null) {
                                hallOfFame.addArtist(artist);
                            }
                        }
                        for (DataSnapshot creationsSnapshot : creations.getChildren()) {
                            String name=creationsSnapshot.child("name").getValue(String.class);
                            String type = creationsSnapshot.child("type").getValue(String.class);
                            DataSnapshot artist=creationsSnapshot.child("artist");
                            String artistName=artist.child("name").getValue(String.class);
                            String artistType=artist.child("type").getValue(String.class);
                            Artist artistInCreation=null;
                            switch (artistType){
                                case "Poet":
                                    int numPoems=artist.child("numPoems").getValue(Integer.class);
                                    artistInCreation = new Poet(artistName,numPoems);
                                    break;
                                case "Singer":
                                    int numAlbums=artist.child("numAlbums").getValue(Integer.class);
                                    artistInCreation = new Singer(artistName,numAlbums);
                                    break;
                                case "Player":
                                    String instrument=artist.child("instrument").getValue(String.class);
                                    artistInCreation = new Player(artistName,instrument);
                                    break;
                                case "Composer":
                                    String musicType=artist.child("musicType").getValue(String.class);
                                    artistInCreation = new Composer(artistName,musicType);
                                    break;
                            }
                            Creation creation = null;

                            switch (type) {
                                case "Poem":
                                    int lengthPoem=creationsSnapshot.child("length").getValue(Integer.class);
                                    creation = new Poem(artistInCreation,name,lengthPoem);
                                    break;
                                case "Song":
                                    double lengthSong=creationsSnapshot.child("length").getValue(Double.class);
                                    creation = new Song(artistInCreation,name,lengthSong);
                                    break;
                                case "Tune":
                                    double lengthTune=creationsSnapshot.child("length").getValue(Double.class);;
                                    creation = new Tune(artistInCreation,name,lengthTune);
                                    break;
                                // טיפולים נוספים לסוגי אומנים אחרים
                            }

                            if (creation != null) {
                                hallOfFame.addCreation(creation);
                            }
                        }
                        //Toast.makeText(MainActivity.this, hallOfFame.getArtists().size(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.w("Firebase", "loadHallOfFame:onCancelled", error.toException());
                    }
                }
        );
    }
}
