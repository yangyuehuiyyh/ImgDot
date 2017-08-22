package biz.zhidu.zdsdk;

public class FDInfo {
    public String FDID;
    public String DomainID;
    public String FDName;
    public String GBTCode;
    public short FactoryCode;
    public String FactoryName;
    public String Location /*设备安装位置*/;
    public String Desc /*设备描述*/;
    public int FDType /*设备类型*/;
    public int FDVersion/*设备版本号*/;
    public byte FDState /*'W'待安装、'A' 正常、'P' 停用、'C'销户*/;
    public byte VinCount /*视频输入通道数*/;
    public int Longitude /*经度*/;
    public int Latitude /*维度*/;
    public int LastLogin /*最后登录时间*/;
    public int LastLogout  /*最后登出时间*/;
    public int OnlineState /*BIT0, 是否在线*/;
}
