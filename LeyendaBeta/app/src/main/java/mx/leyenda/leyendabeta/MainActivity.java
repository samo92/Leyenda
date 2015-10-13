package mx.leyenda.leyendabeta;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements MediaPlayer.OnPreparedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            setupMedia();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setupMedia() throws IOException {
        String url = "http://vignette3.wikia.nocookie.net/es.gta/images/5/54/GTA_VCS.ogg/revision/latest?cb=20100503204503"; // your URL here
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setDataSource(MainActivity.this, Uri.parse(url));
        mediaPlayer.prepareAsync(); // might take long! (for buffering, etc)
        mediaPlayer.setOnPreparedListener(this);


    }


    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }
}
