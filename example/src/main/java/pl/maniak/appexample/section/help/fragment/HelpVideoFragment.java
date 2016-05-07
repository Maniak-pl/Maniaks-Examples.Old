package pl.maniak.appexample.section.help.fragment;


import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.maniak.appexample.App;
import pl.maniak.appexample.R;
import pl.maniak.appexample.common.log.L;
import pl.maniak.appexample.section.help.view.VideoPlayer;

/**
 * Created by Maniak on 2015-09-29.
 */
public class HelpVideoFragment extends Fragment {

    MediaController mediaController;
    @Bind(R.id.videoView)
    VideoPlayer mVideoView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        L.i("HelpVideoFragment.onCreateView() ");
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_help_video, null);
        ButterKnife.bind(this, root);

        initVideo();

        App.getAnalytics().sendScreenView(getClass().getSimpleName());

        return root;
    }

    private void initVideo() {
        mediaController = new MediaController(getContext());

        Uri video = Uri.parse("android.resource://" + getActivity().getPackageName() + "/"
                + R.raw.video_world_of_warcraft);

        mVideoView.setMediaController(mediaController);
        mVideoView.setVideoURI(video);
        mVideoView.requestFocus();
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mVideoView.seekTo(1);
                Toast.makeText(getActivity(), "This is the end", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        mVideoView.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @OnClick({R.id.videoPlay, R.id.videoPause, R.id.videoSoundOn, R.id.videoSoundOff})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.videoPlay:
                mVideoView.start();
                break;
            case R.id.videoPause:
                mVideoView.pause();
                break;
            case R.id.videoSoundOn:
                mVideoView.unmute();
                break;
            case R.id.videoSoundOff:
                mVideoView.mute();
                break;
        }
    }


}
