LOCAL_PATH := $(call my-dir)

cmd-strip = $(TOOLCHAIN_PREFIX)strip --strip-debug -x $1

include $(CLEAR_VARS)

LOCAL_MODULE    := libgalaxy

LOCAL_CFLAGS := -DANDROID_NDK \
	-DDISABLE_IMPORTGL \
	-fvisibility=hidden

LOCAL_SHARED_LIBRARIES := libcutils

LOCAL_SRC_FILES := galaxy.c core.c

LOCAL_LDLIBS := -llog -ldl

include $(BUILD_SHARED_LIBRARY)