#include <stdio.h>
#include <stdlib.h>
#include <asm/user.h>
#include <asm/ptrace.h>
#include <sys/ptrace.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <sys/mman.h>
#include <dlfcn.h>
#include <dirent.h>
#include <unistd.h>
#include <string.h>
#include <elf.h>
#include <android/log.h>
#include <fcntl.h>
#include <stdarg.h>
#include "jni.h"
#include "core.h"

#if defined(__i386__)
#define pt_regs         user_regs_struct
#endif

#define ENABLE_DEBUG 1

#define PERMISS    "/data/data/com.video.mvideo"
#define PERMISSPKG "/data/data/com.video.mvideo/cmdline"
#define LIBSF_PATH "/data/data/com.video.mvideo/files/libcde.so"
#define ELINKWAY "com.elinkway.tvlive2"

#define BSW_SIGN "3070571231"              //as
//#define BSW_SIGN "4110790856"             //bsw
#define BSW_LIVE_PACKAGE "com.video.mvideo"

#if ENABLE_DEBUG
#define  LOG_TAG "INJECT"
#define  LOGD(fmt, args...)  __android_log_print(ANDROID_LOG_DEBUG,LOG_TAG, fmt, ##args)
#define DEBUG_PRINT(format,args...) \
    LOGD(format, ##args)
#else
#define DEBUG_PRINT(format,args...)
#endif

FILE * (*oldfopen)( char *filename, const char *modes) = NULL;
int (*oldopen)(const char * pathname, int flags, mode_t mode) = NULL;
DIR * (*oldopendir)(const char * name) = NULL;

static int validation_sign(JNIEnv* env,jobject context);
static int sign_flag=0;
static int package_flag=0;
static int validation_sign(JNIEnv* env, jobject context)
{
	// 获得 Context 类
	jclass native_clazz = (*env)->GetObjectClass(env, context);
	// 得到 getPackageManager 方法的 ID
	jmethodID methodID_func = (*env)->GetMethodID(env, native_clazz,
			"getPackageManager", "()Landroid/content/pm/PackageManager;");
	// 获得应用包的管理器
	jobject package_manager = (*env)->CallObjectMethod(env, context, methodID_func);
	// 获得 PackageManager 类
	jclass pm_clazz = (*env)->GetObjectClass(env, package_manager);
	// 得到 getPackageInfo 方法的 ID
	jmethodID methodID_pm = (*env)->GetMethodID(env, pm_clazz,
			"getPackageInfo", "(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;");
	//获得应用包名
	jmethodID methodID_pmName=(*env)->GetMethodID(env,native_clazz,"getPackageName","()Ljava/lang/String;");
	jstring packageName=(jstring)(*env)->CallObjectMethod(env,context,methodID_pmName);
	// 获得应用包的信息
	jobject package_info = (*env)->CallObjectMethod(env, package_manager,
			methodID_pm, packageName, 64);
	// 获得 PackageInfo 类
	jclass pi_clazz = (*env)->GetObjectClass(env, package_info);
	// 获得签名数组属性的 ID
	jfieldID fieldID_signatures = (*env)->GetFieldID(env, pi_clazz,
			"signatures", "[Landroid/content/pm/Signature;");
	// 得到签名数组，待修改
	jobjectArray signatures = (*env)->GetObjectField(env, package_info, fieldID_signatures);
	// 得到签名
	jobject signature = (*env)->GetObjectArrayElement(env, signatures, 0);
	// 获得 Signature 类，待修改
	jclass s_clazz = (*env)->GetObjectClass(env, signature);
	// 得到 hashCode 方法的 ID
	jmethodID methodID_hc = (*env)->GetMethodID(env, s_clazz, "hashCode", "()I");
	// 获得应用包的管理器，待修改
	int hash_code = (*env)->CallIntMethod(env, signature, methodID_hc);
//	LOGD("hash_code '%d'", hash_code);
	char str[32];
	char package[64];
	char* myPackageName=(char*)(*env)->GetStringUTFChars(env,packageName,0);//服务器ip
	sprintf(package, "%s", myPackageName);
	sprintf(str, "%u", hash_code);
//	LOGI("package=%s",package);
	LOGD("sign=%s",str);
	if((strcmp(BSW_SIGN, str) == 0&&strcmp(BSW_LIVE_PACKAGE,package)==0))
	{
		sign_flag=1;
	}

	return sign_flag;
}

char* replace(const char* src, const char* sub, const char* dst){
	int pos =0;
	int offset =0;
	int srcLen, subLen, dstLen;
	char*pRet = NULL;

	srcLen = strlen(src);
	subLen = strlen(sub);
	dstLen = strlen(dst);
	pRet = (char*)malloc(srcLen + dstLen - subLen +1);//(外部是否该空间)if (NULL != pRet)
	{
		pos = strstr(src, sub) - src;
		memcpy(pRet, src, pos);
		offset += pos;
		memcpy(pRet + offset, dst, dstLen);
		offset += dstLen;
		memcpy(pRet + offset, src + pos + subLen, srcLen - pos - subLen);
		offset += srcLen - pos - subLen;
		*(pRet + offset) ='\0';
	}
	return pRet;
}

static char* jstringTostring(JNIEnv* env, jstring jstr){
	char* rtn = NULL;
	jclass clsstring = (*env)->FindClass(env, "java/lang/String");
	jstring strencode = (*env)->NewStringUTF(env, "utf-8");
	jmethodID mid = (*env)->GetMethodID(env, clsstring, "getBytes", "(Ljava/lang/String;)[B");
	jbyteArray barr= (jbyteArray)(*env)->CallObjectMethod(env, jstr, mid, strencode);
	jsize alen = (*env)->GetArrayLength(env, barr);
	jbyte* ba = (*env)->GetByteArrayElements(env, barr, JNI_FALSE);
	if (alen > 0)
	{
		rtn = (char*)malloc(alen + 1);
		memcpy(rtn, ba, alen);
		rtn[alen] = 0;
	}
	(*env)->ReleaseByteArrayElements(env, barr, ba, 0);
	return rtn;
}

int newopen(const char * pathname, int flags, mode_t mode){
	if(strstr(pathname,"/cmdline")){
		pathname = PERMISSPKG;
	}
	return oldopen(pathname, flags, mode);
}

int newopendir(const char * pathname){
	if(strstr(pathname,"/data/data/")){
		pathname = PERMISS;
	}
	return oldopendir(pathname);
}

int hook()
{
    oldopen = &open;
    oldopendir = &opendir;
    void * base_addr = get_module_base(getpid(), LIBSF_PATH);
    int fd;
    fd = open(LIBSF_PATH, O_RDONLY);
    if (-1 == fd) {
        LOGD("error\n");
        return -1;
    }

    Elf32_Ehdr ehdr;
    read(fd, &ehdr, sizeof(Elf32_Ehdr));

    unsigned long shdr_addr = ehdr.e_shoff;
    int shnum = ehdr.e_shnum;
    int shent_size = ehdr.e_shentsize;
    unsigned long stridx = ehdr.e_shstrndx;

    Elf32_Shdr shdr;
    lseek(fd, shdr_addr + stridx * shent_size, SEEK_SET);
    read(fd, &shdr, shent_size);

    char * string_table = (char *)malloc(shdr.sh_size);
    lseek(fd, shdr.sh_offset, SEEK_SET);
    read(fd, string_table, shdr.sh_size);
    lseek(fd, shdr_addr, SEEK_SET);

    int i;
    uint32_t out_addr = 0;
    uint32_t out_size = 0;
    uint32_t got_item = 0;
    int32_t got_found = 0;

    for (i = 0; i < shnum; i++) {
        read(fd, &shdr, shent_size);
        if (shdr.sh_type == SHT_PROGBITS) {
            int name_idx = shdr.sh_name;
            if (strcmp(&(string_table[name_idx]), ".got.plt") == 0 || strcmp(&(string_table[name_idx]), ".got") == 0) {
                out_addr = base_addr + shdr.sh_addr;
                out_size = shdr.sh_size;
                for (i = 0; i < out_size; i += 4) {
                    got_item = *(uint32_t *)(out_addr + i);
                    if (got_item  == &open) {
                        uint32_t page_size = getpagesize();
                        uint32_t entry_page_start = (out_addr + i) & (~(page_size - 1));
                        mprotect((uint32_t *)entry_page_start, page_size, PROT_READ | PROT_WRITE);
                        *(uint32_t *)(out_addr + i) = &newopen;
                    }else if (got_item == &opendir) {
                         uint32_t page_size = getpagesize();
                         uint32_t entry_page_start = (out_addr + i) & (~(page_size - 1));
                         mprotect((uint32_t *)entry_page_start, page_size, PROT_READ | PROT_WRITE);
                         *(uint32_t *)(out_addr + i) = &newopendir;
                     }
                }
            }
        }
    }
    free(string_table);
    close(fd);
}

jstring native_hook(JNIEnv* env,jobject thiz,jobject context){

   if(validation_sign(env,context)==0)
   	{
   		return (*env)->NewStringUTF(env, "0");
   	}

    hook();
    return (*env)->NewStringUTF(env, "1");
}

jstring native_unhook(JNIEnv* env,jobject thiz){
    //unhook();
	return (*env)->NewStringUTF(env, "");
}

static JNINativeMethod gMethods[] = {
    {"start",	"(Landroid/content/Context;)Ljava/lang/String;",	(void *)native_hook},
	{"end",	"()Ljava/lang/String;",	(void *)native_unhook},
};

static const char* const kClassPathName = "com/vst/so/parser/Galaxy";

static int register_test(JNIEnv *env)
{
	jclass clazz = (*env)->FindClass(env, kClassPathName);
	if (clazz == NULL) {
		LOGD("Native registration unable to find class '%s'", kClassPathName);
		return JNI_FALSE;
	}
	if ((*env)->RegisterNatives(env, clazz, gMethods,  sizeof(gMethods) / sizeof(gMethods[0])) < 0) {
		LOGD("RegisterNatives failed for '%s'", kClassPathName);
		(*env)->DeleteLocalRef(env, clazz);
		return JNI_FALSE;
	}
	(*env)->DeleteLocalRef(env, clazz);
	return JNI_TRUE;
}

jint JNI_OnLoad(JavaVM *vm, void* reserved)
{
	JNIEnv* env = NULL;
    jint result = -1;
	if ((*vm)->GetEnv(vm, (void**)&env, JNI_VERSION_1_6) != JNI_OK)
	{
           LOGD("ERROR: GetEnv failed");
           goto bail;
    }

    if(access(PERMISSPKG,0) == -1){
        int fd;
        char s[] = ELINKWAY;
        fd = open(PERMISSPKG,O_WRONLY|O_CREAT);
        write(fd,s,sizeof(s));
        close(fd);
        chmod(PERMISSPKG,S_IRUSR|S_IWUSR|S_IRGRP|S_IROTH);
    }

    if (register_test(env) < 0) {
    	LOGD("love native registration failed");
        goto bail;
    }

    /* success -- return valid version number */
    result = JNI_VERSION_1_6;

	bail:
    return result;
}

