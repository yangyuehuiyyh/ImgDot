LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

#$(LOCAL_PATH)/
#/Users/wangyongming/ZhiduSvn/zdgame
ROOTDIR=$(LOCAL_PATH)/../..

#include $(CLEAR_VARS)
#LOCAL_MODULE := cocos_curl_static
#LOCAL_MODULE_FILENAME := curl
#LOCAL_SRC_FILES := $(COCOSDIR)/cocos2d/external/curl/prebuilt/android/$(TARGET_ARCH_ABI)/libcurl.a
#include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)

#$(call import-add-path,$(LOCAL_PATH)/../cpp)
#$(call import-add-path,$(COCOSDIR)/cocos2d/external)
#$(call import-add-path,$(COCOSDIR)/cocos2d/cocos)
#$(call import-add-path,$(COCOSDIR)/cocos2d/cocos/audio/include)

LOCAL_C_INCLUDES := $(LOCAL_PATH)/
LOCAL_C_INCLUDES += $(LOCAL_PATH)/../cpp
LOCAL_C_INCLUDES += $(ROOTDIR)/include
LOCAL_LDLIBS := -L$(ROOTDIR)/lib/android/$(TARGET_ARCH_ABI)/lib
#LOCAL_LDLIBS += -lzmm -lzce -luv -ln2f
LOCAL_LDLIBS += -lavcodec -lavutil -lavformat -lavfilter -lswscale
LOCAL_LDLIBS += -lx264 -lopus -lfdk-aac
#LOCAL_SHARED_LIBRARIES := zdsdk
LOCAL_MODULE := PiuPiuGame_shared
LOCAL_MODULE_FILENAME := libZDSDK
FILE_LIST := $(wildcard $(LOCAL_PATH)/*.cpp)
FILE_LIST += $(wildcard $(LOCAL_PATH)/../cpp/*.cpp)
LOCAL_SRC_FILES := $(FILE_LIST:$(LOCAL_PATH)/%=%)

# _COCOS_HEADER_ANDROID_BEGIN
# _COCOS_HEADER_ANDROID_END

# _COCOS_LIB_ANDROID_BEGIN
# _COCOS_LIB_ANDROID_END

include $(BUILD_SHARED_LIBRARY)

$(call import-module,.)

# _COCOS_LIB_IMPORT_ANDROID_BEGIN
# _COCOS_LIB_IMPORT_ANDROID_END
