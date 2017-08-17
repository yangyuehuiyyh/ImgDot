package biz.zhidu.zdsdk;

import android.graphics.Point;
import android.util.Log;
import android.view.SurfaceView;

import java.nio.ByteBuffer;

public class ZhiduEyeAgent {
    public static final byte CAMERA_CONTROL_ACTION_UP = 0x00000001     /* 向上转 */;
    public static final byte CAMERA_CONTROL_ACTION_LEFT = 0x00000002   /* 向左转 */;
    public static final byte CAMERA_CONTROL_ACTION_ROTATE = 0x00000003 /*旋转*/;
    public static final byte CAMERA_CONTROL_ACTION_RIGHT = 0x00000004  /*向右转*/;
    public static final byte CAMERA_CONTROL_ACTION_DOWN = 0x00000005   /*向下转*/;
    public static final byte CAMERA_CONTROL_ACTION_FAR = 0x00000006    /*镜头拉远*/;
    public static final byte CAMERA_CONTROL_ACTION_FOCUSNEAR = 0x00000007 /*聚焦*/;
    public static final byte CAMERA_CONTROL_ACTION_AUTO = 0x00000008 /*自动*/;
    public static final byte CAMERA_CONTROL_ACTION_FOCUSFAR = 0x00000009 /*散焦*/;
    public static final byte CAMERA_CONTROL_ACTION_NEAR = 0x0000000a /*镜头拉近*/;
    public static final byte CAMERA_CONTROL_ACTION_DIAPHRAGM_LARGE = 0x0000000b /*光圈增大（变亮）*/;
    public static final byte CAMERA_CONTROL_ACTION_DIAPHRAGM_SMALL = 0x0000000c /*光圈减小（变暗）*/;
    public static final byte CAMERA_CONTROL_ACTION_STOP = 0x0000000d /*停止动作*/;
    public static final byte CAMERA_CONTROL_ACTION_SPEAKER_ON = 0x0000000e /*打开喇叭*/;
    public static final byte CAMERA_CONTROL_ACTION_LIGHT_ON = 0x0000000f /*打开灯光*/;
    public static final byte CAMERA_CONTROL_ACTION_HOTDOG = 0x00000010 /*热狗扫描*/;
    public static final byte CAMERA_CONTROL_ACTION_SPEAKER_OFF = 0x00000011 /*关闭喇叭*/;
    public static final byte CAMERA_CONTROL_ACTION_LIGHT_OFF = 0x00000012 /*关闭灯光*/;
    public static final byte CAMERA_CONTROL_ACTION_PRESET_GOTO = 0x00000013 /*切换到预置点*/;
    public static final byte CAMERA_CONTROL_ACTION_PRESET_SET = 0x00000014 /*设置预置点*/;
    public static final byte CAMERA_CONTROL_ACTION_PRESET_DEL = 0x00000015 /*删除预置点*/;
    public static final byte CAMERA_CONTROL_ACTION_CAMERA_RESET = 0x00000016 /*摄像机复位*/;
    public static final byte CAMERA_CONTROL_ACTION_WIPER_ON = 0x00000017 /*打开雨刷*/;
    public static final byte CAMERA_CONTROL_ACTION_WIPER_OFF = 0x00000018 /*关闭雨刷*/;
    public static final byte CAMERA_CONTROL_ACTION_AUTOCRUISE = 0x0000001d /*自动巡航*/;
    public static final byte CAMERA_CONTROL_ACTION_PRESET_CLEAR = 0x0000001e /*清除所有预置点*/;
    public static final byte CAMERA_CONTROL_ACTION_STARTTRACKING = 0x0000001f /*启动跟踪*/;
    public static final byte CAMERA_CONTROL_ACTION_STOPTRACKING = 0x00000020 /*停止跟踪*/;
    public static final byte CAMERA_CONTROL_ACTION_LEFTUP = 0x00000021 /*左上转*/;
    public static final byte CAMERA_CONTROL_ACTION_RIGHTUP = 0x00000022 /*右上转*/;
    public static final byte CAMERA_CONTROL_ACTION_LEFTDOWN = 0x00000023 /*左下转*/;
    public static final byte CAMERA_CONTROL_ACTION_RIGHTDOWN = 0x00000024 /*右下转*/;
    public static final byte CAMERA_CONTROL_ACTION_CAMERAACTIVE = 0x00000028 /*摄像机激活*/;
    public static final byte CAMERA_CONTROL_ACTION_SETPANSPEED = 0x00000029 /*设置左右转动速度*/;
    public static final byte CAMERA_CONTROL_ACTION_SETTILTSPEED = 0x00000030 /*设置上下转动速度*/;
    public static final byte CAMERA_CONTROL_ACTION_SETZOOMSPEED = 0x00000031 /*设置光圈速度*/;
    public static final byte CAMERA_CONTROL_ACTION_SETFOCUSSPEED = 0x00000032 /*设置聚焦/散焦速度*/;
    public static final byte CAMERA_CONTROL_ACTION_SPEEDSETTINGGET = 0x00000033 /*获取摄像机速度设置*/;
    public static final byte CAMERA_CONTROL_ACTION_MATRIXSWITCH = 0x00000040 /*矩阵切换*/;
    public static final byte CAMERA_CONTROL_ACTION_BRIGHTNESS = 0x00000050 /*亮度*/;
    public static final byte CAMERA_CONTROL_ACTION_CONTRAST = 0x00000051 /*对比度*/;
    public static final byte CAMERA_CONTROL_ACTION_SATURATION = 0x00000052 /*饱和度*/;
    public static final byte CAMERA_CONTROL_ACTION_HUE = 0x00000053 /*色度*/;

    ////////////////////////////////////////////////////
    public static final byte MSG_TYPE_LOGIN = 0x00000001 /*登入*/;
    public static final byte MSG_TYPE_LOGOUT = 0x00000002 /*登出*/;
    public static final byte MSG_TYPE_START_MONITOR = 0x00000003 /*开始监控*/;
    public static final byte MSG_TYPE_STOP_MONITOR = 0x00000004 /*停止监控*/;
    public static final byte MSG_TYPE_PTZ_CONTROL = 0x00000005 /*PTZ控制*/;
    public static final byte MSG_TYPE_QUERY_RECORD = 0x00000006 /*查询录像*/;
    public static final byte MSG_TYPE_START_PLAYBACK = 0x00000007 /*开始播放录像*/;
    public static final byte MSG_TYPE_STOP_PLAYBACK = 0x00000008 /*停止播放录像*/;
    public static final byte MSG_TYPE_CTRL_PLAYBACK = 0x00000009 /*录像操作*/;
    public static final byte MSG_TYPE_DOWNLOAD_RECORD = 0x00000010 /*下载路线返回*/;
    public static final byte MSG_TYPE_START_AUDIO = 0x00000011 /*启动语音*/;
    public static final byte MSG_TYPE_STOP_AUDIO = 0x00000012 /*停止语音*/;
    public static final byte MSG_TYPE_CHANNEL_LIST = 0x00000013 /*获取通道列表*/;
    public static final byte MSG_TYPE_QUERY_GROUP = 0x00000014 /*查询分组*/;
    public static final byte MSG_TYPE_QUERY_PRESET = 0x00000015 /*查询预置点*/;
    public static final byte MSG_TYPE_PRESET_CONTROL = 0x00000016 /*预置点控制*/;
    public static final byte MSG_TYPE_PRESET_GOTO = 0x00000017 /*到达指定预置点*/;
    public static final byte MSG_TYPE_FD_LIST = 0x00000018 /*获取设备列表*/;
    public static final byte MSG_TYPE_FD_CHANNELLIST = 0x00000019 /*获取指定设备的通道列表*/;
    public static final byte MSG_TYPE_UA_RECORDSTART = 0x00000020 /*开始录像*/;
    public static final byte MSG_TYPE_UA_RECORDSTOP = 0x00000021 /*停止路线*/;
    public static final byte MSG_TYPE_CONFIG_RECORD_PLAN = 0x00000022 /*配置录像计划*/;
    public static final byte MSG_TYPE_QUERY_RECORD_PLAN = 0x00000023 /*查询录像计划*/;
    public static final byte MSG_TYPE_QUERY_PRESETSCHEDULE = 0x00000024 /*查询预置点计划*/;
    public static final byte MSG_TYPE_SET_PRESETSCHEDULE = 0x00000025 /*设置预置点计划*/;
    public static final byte MSG_TYPE_QUERY_FDSTATUS = 0x00000026 /*查询设备状态*/;
    public static final byte MSG_TYPE_MODIFY_PASSWORD = 0x00000027 /*修改密码*/;
    public static final byte MSG_TYPE_MODIFY_FDNAME = 0x00000028 /*修改设备昵称*/;
    public static final byte MSG_TYPE_MODIFY_CHANNELNAME = 0x00000029 /*修改通道昵称*/;
    ///////////////////////////////////////////////////

    private native long newSession();

    private native int startLogin(long handle, String szRemoteIP, short nRemotePort, String id, String passwd);

    private native FDInfo[] getFDInfoList(long handle);

    private native ChannelInfo[] getChannelInfoList(long handle);

    private native int startMonitorChannel(long handle, String uniqueid, short channelid, short nettype);

    private native int stopMonitorChannel(long handle, String uniqueid, short channelid);

    private native int startSnapShot(long handle, String uniqueid, String filePath);

    /*action: CAMERA_CONTROL_ACTION_XXX*/
    private native int controlCamera(long handle, String uniqueid, byte action, byte param);

    private native int enableAudio(long handle, String uniqueid, boolean enbale);

    private native int startQueryRecord(long handle, String uniqueid, boolean center, long start_time, long end_time);

    private native RecordInfo[] queryRecordList(long handle);

    /*return playbackhandle*/
    private native long startPlayback(long handle, RecordInfo record, int net_type, boolean is_center);

    private native int stopPlayback(long handle, long playbackhandle);

    private native int controlPlayback(long handle, long playbackhandle, byte action, byte param);

    private native int enablePlaybackAudio(long handle, long playbackhandle, boolean enbale);

    private native int startPlaybackSnapShot(long handle, long playbackhandle, String filePath);

    private long _session = 0;

    private Point frameSize = new Point(0, 0);

    private VideoStreamsView _view;

    private VideoRenderer.I420Frame blackImage = null;

    public interface MessageCallBack
    {
        public void OnRecvieMessage(int msgtype, int errorcode);
    }

    private  MessageCallBack messagecb;

    static {
        System.loadLibrary("zdsdk");
    }

    public ZhiduEyeAgent() {
        _session = newSession();
    }

    public void OnNotify(int evt, int param) {

    }

    public void SetMessageCallBack(MessageCallBack obj)
    {
        messagecb = obj;
    }

    public int startLogin(String ip, short port, String id, String passwd) {
        return startLogin(_session, ip, port, id, passwd);
    }

    public FDInfo[] getFDInfoList() {
        return getFDInfoList(_session);
    }

    public ChannelInfo[] getChannelInfoList() {
        return getChannelInfoList(_session);
    }

    public int startMonitorChannel(VideoStreamsView view, String uniqueid, short nettype) {
        _view = view;
        return startMonitorChannel(_session, uniqueid, (short)1, nettype);
    }

    public int stopMonitorChannel(String uniqueid) {
        int ret = stopMonitorChannel(_session, uniqueid, (short)1);
        _view.queueFrame(VideoStreamsView.Endpoint.REMOTE, VideoRenderer.I420Frame.createBlackImage(1920, 1080));
        return ret;
    }

    public int controlCamera(String uniqueid, byte action, byte param) {
        return controlCamera(_session, uniqueid, action, param);
    };

    public int enableAudio(String uniqueid, boolean enbale) {
        return enableAudio(_session, uniqueid, enbale);
    };

    public int startQueryRecord(String uniqueid, boolean center, long start_time, long end_time) {
        return startQueryRecord(_session, uniqueid, center, start_time, end_time);
    };

    public RecordInfo[] queryRecordList() {
        return queryRecordList(_session);
    };

    /*return playbackhandle*/
    public long startPlayback(VideoStreamsView view, RecordInfo record, int net_type, boolean is_center) {
        _view = view;
        return startPlayback(_session, record, net_type, is_center);
    };

    public int stopPlayback(long playbackhandle) {
        return stopPlayback(_session, playbackhandle);
    };

    public int controlPlayback(long playbackhandle, byte action, byte param) {
        return controlPlayback(_session, playbackhandle, action, param);
    };

    public int enablePlaybackAudio(long playbackhandle, boolean enable) {
        return enablePlaybackAudio(_session, playbackhandle, enable);
    };

    public int startPlaybackSnapShot(long playbackhandle, String filePath) {
        return startPlaybackSnapShot(_session, playbackhandle, filePath);
    };

    private void setSourceVideoSize(final int index, int w, int h) {
        if(w != frameSize.x || h != frameSize.y) {
            frameSize.x = w;
            frameSize.y = h;
            blackImage = VideoRenderer.I420Frame.createBlackImage(w, h);
            _view.queueEvent(new Runnable() {
                public void run() {
                    _view.setSize(VideoStreamsView.Endpoint.REMOTE, frameSize.x, frameSize.y);
                }
            });
        }
    }

    public void onReceiveVideoFrame(int index, byte[] pY, byte[] pCb, byte[] pCr, int w, int h) {
        ByteBuffer[] yuvPlanes = new ByteBuffer[3];
        yuvPlanes[0] = ByteBuffer.wrap(pY);
        yuvPlanes[1] = ByteBuffer.wrap(pCb);
        yuvPlanes[2] = ByteBuffer.wrap(pCr);
        int[] yuvStrides = new int[3];
        yuvStrides[0] = w;
        yuvStrides[1] = yuvStrides[2] = w / 2;

        if(_view != null) {
            Log.i("Decoder", "onReceiveVideoFrame ." + Integer.toString(index));
            setSourceVideoSize(index, w, h);
            _view.queueFrame(VideoStreamsView.Endpoint.REMOTE,
                    new VideoRenderer.I420Frame(w, h, yuvStrides, yuvPlanes));
        }
    }

    public void onReceiveAudioFrame(int samplerate, int channelcnt, byte[] audiodata) {

    }

    public void onReceiveMessage(int msgtype, int errorcode){
        Log.i("Decoder", "onReceiveMessage msgtype: " + msgtype + "  errorcode:" + errorcode);
        if(messagecb != null)
        {
            messagecb.OnRecvieMessage(msgtype,errorcode);
        }
    }
}
