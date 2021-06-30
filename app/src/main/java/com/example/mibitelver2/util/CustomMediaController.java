package com.example.mibitelver2.util;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.mibitelver2.R;

import java.lang.ref.WeakReference;
import java.util.Formatter;
import java.util.Locale;

import static com.example.mibitelver2.util.Constants.FADE_OUT;
import static com.example.mibitelver2.util.Constants.SHOW_PROGRESS;
import static com.example.mibitelver2.util.Constants.sDefaultTimeout;

public class CustomMediaController extends MediaController {

    private MediaPlayerControl mPlayer;
    private View mRoot;
    private ProgressBar mProgressBar;
    private ImageButton mPauseButton;
    private ImageButton mFfwdButton;
    private ImageButton mRewButton;
//    private ImageButton mNextButton;
//    private ImageButton mPrevButton;
    private ImageButton mFullscreenButton;
    private boolean mUseFastForward;
    private boolean mFromXml;
    private boolean mShowing;
    private ViewGroup mAnchor;
    private TextView mEndTime;
    private TextView mCurrentTime;
    StringBuilder mFormatBuilder;
    Formatter mFormatter;
    Context mContext;
    private boolean mDragging;
    private Handler mHandler = new MessageHandler(this);

    public CustomMediaController(@NonNull Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mRoot = null;
        mContext = context;
        mUseFastForward = true;
        mFromXml = true;
    }

    public CustomMediaController(@NonNull Context context, boolean useFastForward) {
        super(context);
        mContext = context;
        mUseFastForward = useFastForward;
    }

    public CustomMediaController(Context context) {
        this(context, true);
    }

    public void setMediaPlayer(MediaPlayerControl player) {
        mPlayer = player;
        updatePausePlay();
        updateFullScreen();
    }

//    private final OnTouchListener mTouchListener = new OnTouchListener() {
//        @Override
//        public boolean onTouch(View v, MotionEvent event) {
//            if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                if (mShowing) {
//                    hide();
//                }
//            }
//            return false;
//        }
//    };

    //switch fullScreen button
    public void updateFullScreen() {
        if (mRoot == null || mFullscreenButton == null || mPlayer == null) {
            return;
        }
        if (mPlayer.isFullScreen()) {
            mFullscreenButton.setImageResource(R.drawable.ic_media_fullscreen_shrink);
        } else {
            mFullscreenButton.setImageResource(R.drawable.ic_media_fullscreen_stretch);
        }
    }

    //Switch icon play/pause
    public void updatePausePlay() {
        if (mRoot == null || mPauseButton == null || mPlayer == null) {
            return;
        }
        if (mPlayer.isPlaying()) {
            mPauseButton.setImageResource(android.R.drawable.ic_media_pause);
        } else {
            mPauseButton.setImageResource(android.R.drawable.ic_media_play);
        }
    }

    public void setAnchorView(ViewGroup view) {
        mAnchor = view;
        FrameLayout.LayoutParams frameParams = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );
        removeAllViews();
        View v = makeControllerView();
        addView(v, frameParams);
    }

    protected View makeControllerView() {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        mRoot = inflater.inflate(R.layout.custom_media_controller, null);
        initControllerView(mRoot);
        return mRoot;
    }

    private void initControllerView(View v) {
        mPauseButton = v.findViewById(R.id.pause);
        if (mPauseButton != null) {
            mPauseButton.requestFocus();
            mPauseButton.setOnClickListener(listener -> {
                doPauseResume();
                show(sDefaultTimeout);
            });
        }

        mFullscreenButton = v.findViewById(R.id.fullscreen);
        if (mFullscreenButton != null) {
            mFullscreenButton.requestFocus();
            mFullscreenButton.setOnClickListener(listener -> {
                doToggleFullScreen();
                show(sDefaultTimeout);
            });
        }

        mFfwdButton = v.findViewById(R.id.ffwd);
        if (mFfwdButton != null) {
            mFfwdButton.setOnClickListener(listener -> {
                if (mPlayer == null)
                    return;
                int pos = mPlayer.getCurrentPosition();
                pos += 10000;
                mPlayer.seekTo(pos);
                setProgress();
                show(sDefaultTimeout);
            });
            if (!mFromXml) {
                mFfwdButton.setVisibility(mUseFastForward ? VISIBLE : GONE);
            }
        }

        mRewButton = v.findViewById(R.id.rew);
        if (mRewButton != null) {
            mRewButton.setOnClickListener(listener -> {
                if (mPlayer == null)
                    return;
                int pos = mPlayer.getCurrentPosition();
                pos -= 10000;
                mPlayer.seekTo(pos);
                setProgress();
                show(sDefaultTimeout);
            });
            if (!mFromXml) {
                mRewButton.setVisibility(mUseFastForward ? VISIBLE : GONE);
            }
        }

        mProgressBar = v.findViewById(R.id.mediacontroller_progress);
        if (mProgressBar != null) {
            if (mProgressBar instanceof SeekBar) {
                SeekBar seekBar = (SeekBar) mProgressBar;
                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if (mPlayer == null) {
                            return;
                        }
                        if (!fromUser) {
                            return;
                        }
                        long duration = mPlayer.getDuration();
                        long newPos = (duration * progress) / 1000L;
                        mPlayer.seekTo((int) newPos);
                        if (mCurrentTime != null)
                            mCurrentTime.setText(stringForTime((int) newPos));
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        show(5000000);
                        mDragging = true;
                        mHandler.removeMessages(SHOW_PROGRESS);
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        mDragging = false;
                        setProgress();
                        updatePausePlay();
                        show(sDefaultTimeout);
                        mHandler.sendEmptyMessage(SHOW_PROGRESS);
                    }
                });
            }
            mProgressBar.setMax(1000);
        }

//        mNextButton = v.findViewById(R.id.next);
//        mPrevButton = v.findViewById(R.id.prev);
        mEndTime = v.findViewById(R.id.time_end);
        mCurrentTime = v.findViewById(R.id.time_current);
        mFormatBuilder = new StringBuilder();
        mFormatter = new Formatter(mFormatBuilder, Locale.getDefault());
    }

    public void show() {
        show(sDefaultTimeout);
    }

    private void disableUnsupportedButtons() {
        if (mPlayer == null) return;
        try {
            if (mPauseButton != null && !mPlayer.canPause()) {
                mPauseButton.setEnabled(false);
            }
            if (mRewButton != null && !mPlayer.canSeekBackward()) {
                mRewButton.setEnabled(false);
            }
            if (mFfwdButton != null && !mPlayer.canSeekForward()) {
                mFfwdButton.setEnabled(false);
            }
        } catch (IncompatibleClassChangeError ex) {
            ex.printStackTrace();
        }
    }

    public void show(int timeout) {
        if(!mShowing && mAnchor != null) {
            setProgress();
            if(mPauseButton != null) {
                mPauseButton.requestFocus();
            }
            disableUnsupportedButtons();
            FrameLayout.LayoutParams tlp = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    Gravity.BOTTOM
            );
            mAnchor.addView(this, tlp);
            mShowing = true;
        }
        updatePausePlay();
        updateFullScreen();

        mHandler.sendEmptyMessage(SHOW_PROGRESS);
        Message msg = mHandler.obtainMessage(FADE_OUT);
        if(timeout != 0) {
            mHandler.removeMessages(FADE_OUT);
            mHandler.sendMessageDelayed(msg, timeout);
        }
    }

    public void hide() {
        if (mAnchor == null) {
            return;
        }
        try {
            mAnchor.removeView(this);
            mHandler.removeMessages(SHOW_PROGRESS);
        } catch (IllegalArgumentException ex) {
            Log.w("MediaController", "already removed");
        }
        mShowing = false;
    }

    //Resume or pause video
    private void doPauseResume() {
        if (mPlayer == null) {
            return;
        }
        if (mPlayer.isPlaying()) {
            mPlayer.pause();
        } else {
            mPlayer.start();
        }
        updatePausePlay();
    }

    //Fullscreen
    private void doToggleFullScreen() {
        if (mPlayer == null) {
            return;
        }
        mPlayer.toggleFullScreen();
    }

    //set tien do cho video
    private int setProgress() {
        if (mPlayer == null||mDragging) {
            return 0;
        }
        int pos = mPlayer.getCurrentPosition();
        int duration = mPlayer.getDuration();
        if (mProgressBar != null) {
            if (duration > 0) {
                long position = 1000L * pos / duration;
                mProgressBar.setProgress((int) position);
            }
            int percent = mPlayer.getBufferPercentage();
            mProgressBar.setSecondaryProgress(percent * 10);
        }
        if (mEndTime != null)
            mEndTime.setText(stringForTime(duration));
        if (mCurrentTime != null)
            mCurrentTime.setText(stringForTime(pos));

        return pos;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                show(0); // show until hide is called
                break;
            case MotionEvent.ACTION_UP:
                show(sDefaultTimeout); // start timeout
                break;
            case MotionEvent.ACTION_CANCEL:
                hide();
                break;
            default:
                break;
        }
        return true;
    }

    //String dang time
    private String stringForTime(int timeMillis) {
        int totalSecs = timeMillis / 1000;
        int secs = totalSecs % 60;
        int mins = (totalSecs / 60) % 60;
        int hours = totalSecs / 3600;

        mFormatBuilder.setLength(0);
        if (hours > 0) {
            return mFormatter.format("%d:%02d:%02d", hours, mins, secs).toString();
        } else {
            return mFormatter.format("%02d:%02d", mins, secs).toString();
        }
    }

    private static class MessageHandler extends Handler {
        private final WeakReference<CustomMediaController> mController;

        MessageHandler(CustomMediaController controller) {
            mController = new WeakReference<CustomMediaController>(controller);
        }

        @Override
        public void handleMessage(Message msg) {
            CustomMediaController controller = mController.get();
            if (controller == null || controller.mPlayer == null) {
                return;
            }

            int pos;
            switch (msg.what) {
                case FADE_OUT:
                    controller.hide();
                    break;
                case SHOW_PROGRESS:
                    pos = controller.setProgress();
                    if (!controller.mDragging && controller.mShowing && controller.mPlayer.isPlaying()) {
                        msg = obtainMessage(SHOW_PROGRESS);
                        sendMessageDelayed(msg, 1000 - (pos % 1000));
                    }
                    break;
            }
        }
    }

    public interface MediaPlayerControl {
        void    start();
        void    pause();
        int     getDuration();
        int     getCurrentPosition();
        void    seekTo(int pos);
        boolean isPlaying();
        int     getBufferPercentage();
        boolean canPause();
        boolean canSeekBackward();
        boolean canSeekForward();
        boolean isFullScreen();
        void    toggleFullScreen();
    }
}
