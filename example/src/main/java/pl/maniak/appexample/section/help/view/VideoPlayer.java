package pl.maniak.appexample.section.help.view;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * Created by maniak on 07.05.16.
 */
public class VideoPlayer extends VideoView implements MediaPlayer.OnPreparedListener {
    private MediaPlayer mediaPlayer;

    public VideoPlayer(Context context, AttributeSet attributes) {
        super(context, attributes);

        this.setOnPreparedListener(this);

    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    public void mute() {
        this.setVolume(0);
    }

    public void unmute() {
        this.setVolume(100);
    }

    private void setVolume(int amount) {
        final int max = 100;
        final double numerator = max - amount > 0 ? Math.log(max - amount) : 0;
        final float volume = (float) (1 - (numerator / Math.log(max)));

        this.mediaPlayer.setVolume(volume, volume);
    }
}