package com.example.mibitelver2.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.mibitelver2.R;
import com.example.mibitelver2.adapter.HashTagAdapter;
import com.example.mibitelver2.databinding.ActivityVideoBinding;
import com.example.mibitelver2.model.videoInfo.VideoInfo;
import com.example.mibitelver2.modeltien.channel.Channel;
import com.example.mibitelver2.modeltien.hashtag.Datum;
import com.example.mibitelver2.modeltien.putpostmodel.Likeput;
import com.example.mibitelver2.modeltien.putpostmodel.PostFollower;
import com.example.mibitelver2.modeltien.putpostmodel.WhatLatePut;
import com.example.mibitelver2.modeltien.video.Data;
import com.example.mibitelver2.modeltien.video.VideoAcount;
import com.example.mibitelver2.util.Constants;
import com.example.mibitelver2.viewmodel.VideoUserViewModel;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class VideoActivity extends AppCompatActivity {

    PlayerView playerView;
    ProgressBar progressBar;
    SimpleExoPlayer simpleExoPlayer;
    int k = 1;
    private int g = 0, h = 0;
    private int g1 = 0, h1 = 0;
    private int g2 = 0, h2 = 0;
    private Handler handler;
    VideoUserViewModel videoUserViewModel;
    private String linkvideo;
    private  String timeWatch;
    ActivityVideoBinding activityVideoBinding;

    public static Intent newIntent(Context context, int videoId) {
        Intent i = new Intent(context, VideoActivity.class);
        i.putExtra(Constants.video_id, videoId);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        activityVideoBinding = DataBindingUtil.setContentView(this, R.layout.activity_video);

        activityVideoBinding.setVideoActivity(this);
        activityVideoBinding.setLifecycleOwner(this);
        int id = getIntent().getIntExtra("idVideo", 0);
        videoUserViewModel = new ViewModelProviders().of(this).get(VideoUserViewModel.class);
        /// chuyền id =1;
        videoUserViewModel.getVideoAcount(id + "", "3");
        videoUserViewModel.getChannelVideo(id + "", "3");
        videoUserViewModel.getHashTag(id + "");
        videoUserViewModel.getVideoInfor(id, 3);
        playerView = findViewById(R.id.play_video);
        progressBar = findViewById(R.id.progress_bar);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        LoadControl loadControl = new DefaultLoadControl();
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(VideoActivity.this, trackSelector, loadControl);


        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                changes();
                Uri videoUrl = Uri.parse(linkvideo);
                //Convert time watched to milliseconds
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
                TimeZone gmt = TimeZone.getTimeZone("GMT");
                sdf.setTimeZone(gmt);
                Date date = new Date();

                if (timeWatch == null)
                    timeWatch = "00:00:00";
                try {
                    date = sdf.parse(timeWatch);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                assert date != null;
                Log.e("Time watched", String.valueOf(date));
                long tw = date.getTime();
                Log.e("Time watched",
                        String.format(Locale.getDefault(), "%d", tw));

                MediaSource mediaSource = buildMediaSource(videoUrl);
                playerView.setPlayer(simpleExoPlayer);
                playerView.setKeepScreenOn(true);
                simpleExoPlayer.prepare(mediaSource);
                simpleExoPlayer.setPlayWhenReady(true);

                //lay thoi gian da xem video truoc khi thoat ra
                long currentPosition = simpleExoPlayer.getCurrentPosition();
              String currentTime = String.format(Locale.getDefault(), "%02d:%02d:%02d",
                        TimeUnit.MILLISECONDS.toHours(currentPosition),
                        TimeUnit.MILLISECONDS.toMinutes(currentPosition) -
                                TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(currentPosition)),
                        // The change is in this line
                        TimeUnit.MILLISECONDS.toSeconds(currentPosition) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(currentPosition)));

                //chay den doan video lan truoc da xem den
                simpleExoPlayer.seekTo(tw);


            }
        }, 450);
        simpleExoPlayer.addListener(new Player.EventListener() {
            @Override
            public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {

            }

            @Override
            public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

            }

            @Override
            public void onLoadingChanged(boolean isLoading) {

            }

            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                if (playbackState == Player.STATE_BUFFERING) {
                    progressBar.setVisibility(View.VISIBLE);
                } else if (playbackState == Player.STATE_READY) {
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onRepeatModeChanged(int repeatMode) {

            }

            @Override
            public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

            }

            @Override
            public void onPlayerError(ExoPlaybackException error) {

            }

            @Override
            public void onPositionDiscontinuity(int reason) {

            }

            @Override
            public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

            }

            @Override
            public void onSeekProcessed() {

            }

        });


    }

    private MediaSource buildMediaSource(Uri uri) {
        DataSource.Factory dataSourceFactory =
                new DefaultDataSourceFactory(this, "exoplayer-codelab");
        return new HlsMediaSource.Factory(dataSourceFactory)
                .createMediaSource(uri);
    }

    @Override
    protected void onPause() {
        super.onPause();
        simpleExoPlayer.setPlayWhenReady(false);
        simpleExoPlayer.getPlaybackState();
        k = 2;

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        simpleExoPlayer.setPlayWhenReady(true);
        simpleExoPlayer.getPlaybackState();

    }

    //setData

    private void changes() {

        videoUserViewModel.videoAcount12.observe(this, new Observer<VideoAcount>() {
            @Override
            public void onChanged(VideoAcount videoAcount) {
                setVideo(videoAcount.getData());
                if (videoAcount.getData().getLike()) {
                    setIsLike(1);
                } else setIsLike(0);


                /// chỗ này để lấy dữ liệu đã click xem sau chưa
                if (videoAcount.getData().getWatchLate()) {
                    setIsWhatLate(1);
                } else {
                    setIsWhatLate(0);
                }


            }
        });
        videoUserViewModel.channel.observe(this, new Observer<Channel>() {
            @Override
            public void onChanged(Channel channel) {
                setChannel(channel.getData());
                if (channel.getData().getCheckFollow()) {
                    setIsFollow(1);
                } else {
                    setIsFollow(0);
                }
            }
        });
        videoUserViewModel.videoInfor.observe(this, new Observer<VideoInfo>() {
            @Override
            public void onChanged(VideoInfo videoInfo) {
                timeWatch = videoInfo.getData().getTimeWatch();
            }
        });
        videoUserViewModel.hashTag.observe(this, hashTag -> setHashTagRecyclerview(hashTag.getData()));
    }

    public void setVideo(Data data) {
        linkvideo = data.getLinkVideo();
        activityVideoBinding.setVideoChannel(data);
    }

    public void setIsLike(int isLike) {
        activityVideoBinding.setIsLike(isLike);
    }

    public void setIsWhatLate(int isWhatLate) {
        activityVideoBinding.setIsWhatLate(isWhatLate);
    }

    public void setIsFollow(int isFollow) {
        activityVideoBinding.setIsFollow(isFollow);
    }

    public void setChannel(com.example.mibitelver2.modeltien.channel.Data channel) {
        activityVideoBinding.setChannel(channel);
    }

    public void setHashTagRecyclerview(List<Datum> hashtag) {
        activityVideoBinding.setAdapter(new HashTagAdapter(this, hashtag));
    }



    // setClick

    public void clickComment() {
        MyBottonSheetDialogFragment sheetDialogFragment = MyBottonSheetDialogFragment.newInstance();
        sheetDialogFragment.show(getSupportFragmentManager(), sheetDialogFragment.getTag());
        simpleExoPlayer.setPlayWhenReady(false);
        simpleExoPlayer.getPlaybackState();
    }

    public void clickBack() {
        onBackPressed();
    }

    public void clickLikevideo() {

        videoUserViewModel.videoAcount12.observe(this, new Observer<VideoAcount>() {
            @Override
            public void onChanged(VideoAcount videoAcount) {
                setVideo(videoAcount.getData());
                if (g < 1) {
                    if (videoAcount.getData().getLike()) {
                        h = 1;
                    } else h = 0;

                }
                if (h == 1) {
                    videoUserViewModel.putLike(new Likeput(0, videoAcount.getData().getIdVideo(), 3));
                    setIsLike(0);
                    h = 0;
                } else {
                    videoUserViewModel.putLike(new Likeput(1, videoAcount.getData().getIdVideo(), 3));
                    setIsLike(1);
                    h = 1;

                }


            }
        });
        g++;

    }

    public void clickSubChange() {
        videoUserViewModel.channel.observe(this, new Observer<Channel>() {
            @Override
            public void onChanged(Channel channel) {
                setChannel(channel.getData());
                if (g1 < 1) {
                    if (channel.getData().getCheckFollow()) {
                        h1 = 1;
                    } else h1 = 0;

                }
                if (h1 == 1) {
                    videoUserViewModel.deleteFollower(new PostFollower(3 + "", channel.getData().getIdUser().toString()));
                    setIsFollow(0);
                    h1 = 0;

                } else {
                    videoUserViewModel.postFollower(new PostFollower(3 + "", channel.getData().getIdUser().toString()));
                    setIsFollow(1);
                    h1 = 1;


                }
            }
        });

        g1++;

    }

    public void clickWhatLate() {

        videoUserViewModel.videoAcount12.observe(this, new Observer<VideoAcount>() {
            @Override
            public void onChanged(VideoAcount videoAcount) {
                setVideo(videoAcount.getData());
                if (g2 < 1) {

                    if (videoAcount.getData().getWatchLate()) {
                        h2 = 1;
                    } else h2 = 0;

                }
                if (h2 == 1) {
                    videoUserViewModel.putWhatLate(new WhatLatePut(false, videoAcount.getData().getIdVideo(), 3));
                    setIsWhatLate(0);
                    h2 = 0;
                } else {
                    videoUserViewModel.putWhatLate(new WhatLatePut(true, videoAcount.getData().getIdVideo(), 3));
                    setIsWhatLate(1);
                    h2 = 1;

                }


            }
        });
        g2++;

    }


}