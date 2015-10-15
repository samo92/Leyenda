package mx.leyenda.leyendabeta.ui.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import java.io.IOException;

import mx.leyenda.leyendabeta.R;

/**
 * Created by LEONARDO on 12/10/2015.
 */
public class PlayDialogFragment extends DialogFragment implements MediaPlayer.OnPreparedListener {

    String url;
    MediaPlayer mediaPlayer;

    public PlayDialogFragment() {

    }

    public static PlayDialogFragment newInstance(String title, String url) {
        PlayDialogFragment playDialogFragment = new PlayDialogFragment();
        Bundle args = new Bundle();
        args.putString("Reproducir", title);
        args.putString("Url", url);
        playDialogFragment.setArguments(args);
        return playDialogFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_fragment_play, null);
        obtainArgs();
        alertDialog.setView(view);
        alertDialog.setPositiveButton("Escuchar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    setupMedia();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        return alertDialog.create();
    }

    private void obtainArgs() {
        Bundle args = getArguments();
        url = args.getString("Url");
    }

    public void setupMedia() throws IOException {

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setDataSource(getActivity(), Uri.parse(url));
        mediaPlayer.prepareAsync(); // might take long! (for buffering, etc)
        mediaPlayer.setOnPreparedListener((MediaPlayer.OnPreparedListener) this);

    }


    @Override
    public void onPrepared(MediaPlayer mp) {

        mp.start();
    }
}
