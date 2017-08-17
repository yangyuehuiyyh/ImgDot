package biz.zhidu.zdsdk;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.util.Log;
import android.view.SurfaceView;

public class ZhiduEye {

    private Context _mainContext = null;

    public ZhiduEye(Context ctx) {
        _mainContext = ctx;
    }


    public VideoStreamsView createVideoView(Point dispSize) {
        return new VideoStreamsView(_mainContext, dispSize);
    }

    public ZhiduEyeAgent createAgent() {
        return new ZhiduEyeAgent();
    }
}
