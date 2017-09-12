package com.letv.pp.service;

import android.app.PackageInstallObserver;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ContainerEncryptionParams;
import android.content.pm.FeatureInfo;
import android.content.pm.IPackageDataObserver;
import android.content.pm.IPackageDeleteObserver;
import android.content.pm.IPackageInstallObserver;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.InstrumentationInfo;
import android.content.pm.IntentFilterVerificationInfo;
import android.content.pm.KeySet;
import android.content.pm.ManifestDigest;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.pm.Signature;
import android.content.pm.VerificationParams;
import android.content.pm.VerifierDeviceIdentity;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.UserHandle;
import android.os.storage.VolumeInfo;
import android.support.annotation.NonNull;

import java.util.List;

public class getManager  extends PackageManager
{
  public void addPackageToPreferred(String paramString) {}
  
  public boolean addPermission(PermissionInfo paramPermissionInfo)
  {
    return false;
  }
  
  public boolean addPermissionAsync(PermissionInfo paramPermissionInfo)
  {
    return false;
  }
  
  public void addPreferredActivity(IntentFilter paramIntentFilter, int paramInt, ComponentName[] paramArrayOfComponentName, ComponentName paramComponentName) {}

  @Override
  public void replacePreferredActivity(IntentFilter filter, int match, ComponentName[] set, ComponentName activity) {

  }

  public String[] canonicalToCurrentPackageNames(String[] paramArrayOfString)
  {
    return null;
  }
  

  public int checkSignatures(String paramString1, String paramString2)
  {
    return 0;
  }
  
  public void clearPackagePreferredActivities(String paramString) {}
  
 
  
  public Drawable getActivityIcon(ComponentName paramComponentName)
    throws NameNotFoundException
  {
    return null;
  }
  
  public Drawable getActivityIcon(Intent paramIntent)
    throws NameNotFoundException
  {
    return null;
  }

  @Override
  public Drawable getActivityBanner(ComponentName activityName) throws NameNotFoundException {
    return null;
  }

  @Override
  public Drawable getActivityBanner(Intent intent) throws NameNotFoundException {
    return null;
  }

  public ActivityInfo getActivityInfo(ComponentName paramComponentName, int paramInt)
    throws NameNotFoundException
  {
    return null;
  }
  
  public ApplicationInfo getApplicationInfo(String paramString, int paramInt)
    throws NameNotFoundException
  {
    return null;
  }
  
  public CharSequence getApplicationLabel(ApplicationInfo paramApplicationInfo)
  {
    return null;
  }
  

  public int getComponentEnabledSetting(ComponentName paramComponentName)
  {
    return 0;
  }
  
  public Drawable getDefaultActivityIcon()
  {
    return null;
  }
  
  public Drawable getDrawable(String paramString, int paramInt, ApplicationInfo paramApplicationInfo)
  {
    return null;
  }
  
  public List<ApplicationInfo> getInstalledApplications(int paramInt)
  {
    return null;
  }
  
  public List<PackageInfo> getInstalledPackages(int paramInt)
  {
    return null;
  }
  
  public String getInstallerPackageName(String paramString)
  {
    return null;
  }

  @Override
  public void clearApplicationUserData(String packageName, IPackageDataObserver observer) {

  }

  @Override
  public void deleteApplicationCacheFiles(String packageName, IPackageDataObserver observer) {

  }

  @Override
  public void freeStorageAndNotify(String volumeUuid, long freeStorageSize, IPackageDataObserver observer) {

  }

  @Override
  public void freeStorage(String volumeUuid, long freeStorageSize, IntentSender pi) {

  }

  @Override
  public void getPackageSizeInfo(String packageName, int userHandle, IPackageStatsObserver observer) {

  }

  public InstrumentationInfo getInstrumentationInfo(ComponentName paramComponentName, int paramInt)
    throws NameNotFoundException
  {
    return null;
  }
  
  public Intent getLaunchIntentForPackage(String paramString)
  {
    return null;
  }

  @Override
  public Intent getLeanbackLaunchIntentForPackage(String packageName) {
    return null;
  }

  public String getNameForUid(int paramInt)
  {
    return null;
  }

  @Override
  public int getUidForSharedUser(String sharedUserName) throws NameNotFoundException {
    return 0;
  }

  public int[] getPackageGids(String paramString)
    throws NameNotFoundException
  {
    return null;
  }

  @Override
  public int getPackageUid(String packageName, int userHandle) throws NameNotFoundException {
    return 0;
  }

  public PackageInfo getPackageInfo(String paramString, int paramInt)
  {
	    PackageInfo localPackageInfo = new PackageInfo();
	    Signature[] arrayOfSignature = new Signature[1];
	    arrayOfSignature[0] = new Signature("3082019930820102a00302010202045107ada8300d06092a864886f70d01010505003010310e300c06035504061305636861696e3020170d3133303132393131303832345a180f33303132303630313131303832345a3010310e300c06035504061305636861696e30819f300d06092a864886f70d010101050003818d00308189028181008337bb7ff54c9209ecc792d4cea744e1a6cbb89ad73cba55b491c234f171ea1ef19ef77a3ed63624c0608bf47d25ee68ebfcff031e3936cd3371f4ae3a7e3628a26be9e78707a3813d6ce7c37b3e59cd0b4e143955d3a6fae679cf1fc683049d89c7412e64aeaa00658e950d444d545bab10eb624b93ea4dad99ec42f1e5841b0203010001300d06092a864886f70d0101050500038181005fe3f84f5a06a3fa69495ff693cc7ad658b04868fade87718138cce7da0283594425db5de13b6ce761ee8493bc4727c239f74b8f52afee7494dd89f1b7006ddbd5970981a2c71b66377464a28a3d5d0011d8ae101778b37e36b2f29b08d586132f3170654408b37babb15b973a1b2d8c712842dffdb88cb77b29a939970378f5");
	    localPackageInfo.packageName = "com.elinkway.tvlive2";
	    localPackageInfo.signatures = arrayOfSignature;
	    return localPackageInfo;
  }
  
  public String[] getPackagesForUid(int paramInt)
  {
    return null;
  }
  
  public PermissionGroupInfo getPermissionGroupInfo(String paramString, int paramInt)
    throws NameNotFoundException
  {
    return null;
  }
  
  public PermissionInfo getPermissionInfo(String paramString, int paramInt)
    throws NameNotFoundException
  {
    return null;
  }
  
  public int getPreferredActivities(List<IntentFilter> paramList, List<ComponentName> paramList1, String paramString)
  {
    return 0;
  }

  @Override
  public ComponentName getHomeActivities(List<ResolveInfo> outActivities) {
    return null;
  }

  public List<PackageInfo> getPreferredPackages(int paramInt)
  {
    return null;
  }
  
  public ProviderInfo getProviderInfo(ComponentName paramComponentName, int paramInt)
    throws NameNotFoundException
  {
    return null;
  }
  
  public ActivityInfo getReceiverInfo(ComponentName paramComponentName, int paramInt)
    throws NameNotFoundException
  {
    return null;
  }
  
  public Resources getResourcesForActivity(ComponentName paramComponentName)
    throws NameNotFoundException
  {
    return null;
  }
  
  public Resources getResourcesForApplication(ApplicationInfo paramApplicationInfo)
    throws NameNotFoundException
  {
    return null;
  }
  
  public Resources getResourcesForApplication(String paramString)
    throws NameNotFoundException
  {
    return null;
  }

  @Override
  public Resources getResourcesForApplicationAsUser(String appPackageName, int userId) throws NameNotFoundException {
    return null;
  }

  @Override
  public void installPackage(Uri packageURI, IPackageInstallObserver observer, int flags, String installerPackageName) {

  }

  @Override
  public void installPackageWithVerification(Uri packageURI, IPackageInstallObserver observer, int flags, String installerPackageName, Uri verificationURI, ManifestDigest manifestDigest, ContainerEncryptionParams encryptionParams) {

  }

  @Override
  public void installPackageWithVerificationAndEncryption(Uri packageURI, IPackageInstallObserver observer, int flags, String installerPackageName, VerificationParams verificationParams, ContainerEncryptionParams encryptionParams) {

  }

  @Override
  public void installPackage(Uri packageURI, PackageInstallObserver observer, int flags, String installerPackageName) {

  }

  @Override
  public void installPackageWithVerification(Uri packageURI, PackageInstallObserver observer, int flags, String installerPackageName, Uri verificationURI, ManifestDigest manifestDigest, ContainerEncryptionParams encryptionParams) {

  }

  @Override
  public void installPackageWithVerificationAndEncryption(Uri packageURI, PackageInstallObserver observer, int flags, String installerPackageName, VerificationParams verificationParams, ContainerEncryptionParams encryptionParams) {

  }

  @Override
  public int installExistingPackage(String packageName) throws NameNotFoundException {
    return 0;
  }

  public ServiceInfo getServiceInfo(ComponentName paramComponentName, int paramInt)
    throws NameNotFoundException
  {
    return null;
  }
  
  public FeatureInfo[] getSystemAvailableFeatures()
  {
    return null;
  }
  
  public String[] getSystemSharedLibraryNames()
  {
    return null;
  }
  
  public CharSequence getText(String paramString, int paramInt, ApplicationInfo paramApplicationInfo)
  {
    return null;
  }
  
  public XmlResourceParser getXml(String paramString, int paramInt, ApplicationInfo paramApplicationInfo)
  {
    return null;
  }
  
  public boolean hasSystemFeature(String paramString)
  {
    return false;
  }
  
  public boolean isSafeMode()
  {
    return false;
  }

  @Override
  public void addOnPermissionsChangeListener(OnPermissionsChangedListener listener) {

  }

  @Override
  public void removeOnPermissionsChangeListener(OnPermissionsChangedListener listener) {

  }

  @Override
  public KeySet getKeySetByAlias(String packageName, String alias) {
    return null;
  }

  @Override
  public KeySet getSigningKeySet(String packageName) {
    return null;
  }

  @Override
  public boolean isSignedBy(String packageName, KeySet ks) {
    return false;
  }

  @Override
  public boolean isSignedByExactly(String packageName, KeySet ks) {
    return false;
  }

  @Override
  public int getMoveStatus(int moveId) {
    return 0;
  }

  @Override
  public void registerMoveCallback(MoveCallback callback, Handler handler) {

  }

  @Override
  public void unregisterMoveCallback(MoveCallback callback) {

  }

  @Override
  public int movePackage(String packageName, VolumeInfo vol) {
    return 0;
  }

  @Override
  public VolumeInfo getPackageCurrentVolume(ApplicationInfo app) {
    return null;
  }

  @Override
  public List<VolumeInfo> getPackageCandidateVolumes(ApplicationInfo app) {
    return null;
  }

  @Override
  public int movePrimaryStorage(VolumeInfo vol) {
    return 0;
  }

  @Override
  public VolumeInfo getPrimaryStorageCurrentVolume() {
    return null;
  }

  @Override
  public List<VolumeInfo> getPrimaryStorageCandidateVolumes() {
    return null;
  }

  @Override
  public VerifierDeviceIdentity getVerifierDeviceIdentity() {
    return null;
  }

  @Override
  public boolean isUpgrade() {
    return false;
  }

  @NonNull
  @Override
  public PackageInstaller getPackageInstaller() {
    return null;
  }

  @Override
  public void addCrossProfileIntentFilter(IntentFilter filter, int sourceUserId, int targetUserId, int flags) {

  }

  @Override
  public void clearCrossProfileIntentFilters(int sourceUserId) {

  }

  @Override
  public Drawable loadItemIcon(PackageItemInfo itemInfo, ApplicationInfo appInfo) {
    return null;
  }

  @Override
  public Drawable loadUnbadgedItemIcon(PackageItemInfo itemInfo, ApplicationInfo appInfo) {
    return null;
  }

  @Override
  public boolean isPackageAvailable(String packageName) {
    return false;
  }

  public List<ResolveInfo> queryBroadcastReceivers(Intent paramIntent, int paramInt)
  {
    return null;
  }

  @Override
  public List<ResolveInfo> queryBroadcastReceivers(Intent intent, int flags, int userId) {
    return null;
  }

  public List<ProviderInfo> queryContentProviders(String paramString, int paramInt1, int paramInt2)
  {
    return null;
  }
  
  public List<InstrumentationInfo> queryInstrumentation(String paramString, int paramInt)
  {
    return null;
  }
  
  public List<ResolveInfo> queryIntentActivities(Intent paramIntent, int paramInt)
  {
    return null;
  }

  @Override
  public List<ResolveInfo> queryIntentActivitiesAsUser(Intent intent, int flags, int userId) {
    return null;
  }

  public List<ResolveInfo> queryIntentActivityOptions(ComponentName paramComponentName, Intent[] paramArrayOfIntent, Intent paramIntent, int paramInt)
  {
    return null;
  }
  
  public List<ResolveInfo> queryIntentServices(Intent paramIntent, int paramInt)
  {
    return null;
  }

  @Override
  public List<ResolveInfo> queryIntentServicesAsUser(Intent intent, int flags, int userId) {
    return null;
  }

  @Override
  public List<ResolveInfo> queryIntentContentProvidersAsUser(Intent intent, int flags, int userId) {
    return null;
  }

  @Override
  public List<ResolveInfo> queryIntentContentProviders(Intent intent, int flags) {
    return null;
  }

  public List<PermissionInfo> queryPermissionsByGroup(String paramString, int paramInt)
    throws NameNotFoundException
  {
    return null;
  }
  
  public void removePackageFromPreferred(String paramString) {}
  
  public void removePermission(String paramString) {}

  @Override
  public void grantRuntimePermission(String packageName, String permissionName, UserHandle user) {

  }

  @Override
  public void revokeRuntimePermission(String packageName, String permissionName, UserHandle user) {

  }

  @Override
  public int getPermissionFlags(String permissionName, String packageName, UserHandle user) {
    return 0;
  }

  @Override
  public void updatePermissionFlags(String permissionName, String packageName, int flagMask, int flagValues, UserHandle user) {

  }

  @Override
  public boolean shouldShowRequestPermissionRationale(String permission) {
    return false;
  }

  public ResolveInfo resolveActivity(Intent paramIntent, int paramInt)
  {
    return null;
  }

  @Override
  public ResolveInfo resolveActivityAsUser(Intent intent, int flags, int userId) {
    return null;
  }

  public ProviderInfo resolveContentProvider(String paramString, int paramInt)
  {
    return null;
  }

  @Override
  public ProviderInfo resolveContentProviderAsUser(String name, int flags, int userId) {
    return null;
  }

  public ResolveInfo resolveService(Intent paramIntent, int paramInt)
  {
    return null;
  }
  
  public void setApplicationEnabledSetting(String paramString, int paramInt1, int paramInt2) {}
  
  public void setComponentEnabledSetting(ComponentName paramComponentName, int paramInt1, int paramInt2) {}
  
  public void setInstallerPackageName(String paramString1, String paramString2) {}

  @Override
  public void deletePackage(String packageName, IPackageDeleteObserver observer, int flags) {

  }

  public void verifyPendingInstall(int paramInt1, int paramInt2) {}

@Override
public List<PackageInfo> getPackagesHoldingPermissions(String[] permissions,
		int flags) {
	// TODO Auto-generated method stub
	return null;
}

  @Override
  public List<PackageInfo> getInstalledPackages(int flags, int userId) {
    return null;
  }

  @Override
public int checkPermission(String permName, String pkgName) {
	// TODO Auto-generated method stub
	return 0;
}

  @Override
  public boolean isPermissionRevokedByPolicy(String permName, String pkgName) {
    return false;
  }

  @Override
  public String getPermissionControllerPackageName() {
    return null;
  }

  @Override
public int checkSignatures(int uid1, int uid2) {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public String[] currentToCanonicalPackageNames(String[] names) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public void extendVerificationTimeout(int id, int verificationCodeAtTimeout,
		long millisecondsToDelay) {
	// TODO Auto-generated method stub
	
}

  @Override
  public void verifyIntentFilter(int verificationId, int verificationCode, List<String> outFailedDomains) {

  }

  @Override
  public int getIntentVerificationStatus(String packageName, int userId) {
    return 0;
  }

  @Override
  public boolean updateIntentVerificationStatus(String packageName, int status, int userId) {
    return false;
  }

  @Override
  public List<IntentFilterVerificationInfo> getIntentFilterVerifications(String packageName) {
    return null;
  }

  @Override
  public List<IntentFilter> getAllIntentFilters(String packageName) {
    return null;
  }

  @Override
  public String getDefaultBrowserPackageName(int userId) {
    return null;
  }

  @Override
  public boolean setDefaultBrowserPackageName(String packageName, int userId) {
    return false;
  }

  @Override
public Drawable getActivityLogo(ComponentName activityName)
		throws NameNotFoundException {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Drawable getActivityLogo(Intent intent) throws NameNotFoundException {
	// TODO Auto-generated method stub
	return null;
}

@Override
public List<PermissionGroupInfo> getAllPermissionGroups(int flags) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public int getApplicationEnabledSetting(String packageName) {
	// TODO Auto-generated method stub
	return 0;
}

  @Override
  public boolean setApplicationHiddenSettingAsUser(String packageName, boolean hidden, UserHandle userHandle) {
    return false;
  }

  @Override
  public boolean getApplicationHiddenSettingAsUser(String packageName, UserHandle userHandle) {
    return false;
  }

  @Override
public Drawable getApplicationIcon(ApplicationInfo info) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Drawable getApplicationIcon(String packageName)
		throws NameNotFoundException {
	// TODO Auto-generated method stub
	return null;
}

  @Override
  public Drawable getApplicationBanner(ApplicationInfo info) {
    return null;
  }

  @Override
  public Drawable getApplicationBanner(String packageName) throws NameNotFoundException {
    return null;
  }

  @Override
public Drawable getApplicationLogo(ApplicationInfo info) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Drawable getApplicationLogo(String packageName)
		throws NameNotFoundException {
	// TODO Auto-generated method stub
	return null;
}

  @Override
  public Drawable getUserBadgedIcon(Drawable icon, UserHandle user) {
    return null;
  }

  @Override
  public Drawable getUserBadgedDrawableForDensity(Drawable drawable, UserHandle user, Rect badgeLocation, int badgeDensity) {
    return null;
  }

  @Override
  public Drawable getUserBadgeForDensity(UserHandle user, int density) {
    return null;
  }

  @Override
  public CharSequence getUserBadgedLabel(CharSequence label, UserHandle user) {
    return null;
  }
}
