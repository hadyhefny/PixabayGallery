#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring
Java_com_hefny_hady_pixabaygallery_core_keys_PixabayKeys_getBaseUrl(JNIEnv *env, jobject thiz) {
    return env->NewStringUTF("https://pixabay.com/");
}

extern "C" JNIEXPORT jstring
Java_com_hefny_hady_pixabaygallery_core_keys_PixabayKeys_getApiKey(JNIEnv *env, jobject thiz) {
    return env->NewStringUTF("29526896-6e9ed3090a976b0b34ce32434");
}
