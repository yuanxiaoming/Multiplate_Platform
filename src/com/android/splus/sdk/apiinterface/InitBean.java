
package com.android.splus.sdk.apiinterface;

import com.android.splus.sdk.apiinterface.NetHttpUtil.DataCallback;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import java.util.Properties;

public class InitBean {

    private static final String TAG = "InitBean";

    public int mUseSDK = 1;

    public int mOrientation = 2; // 1是竖屏，2-横屏 ；

    private Properties mProperties;

    private String mAppkey;

    private Integer mGameid;

    private String mPartner;

    private String mReferer;

    private String mDeviceNo;

    private Activity mActivity;

    private int mHeight;

    private int mWidth;

    private InitCallBack mInitCallBack;

    private InitBeanSuccess mInitBeanSuccess;

    public static InitBean inflactBean(Activity activity, Properties prop, Integer gameid, String appkey, Integer orientation) {
        InitBean bean = new InitBean();
        if (prop != null && !TextUtils.isEmpty(appkey) && gameid != null) {
            bean.setActivity(activity);
            bean.setProperties(prop);
            bean.setOrientation(orientation);
            bean.setAppKey(appkey);
            bean.setGameid(gameid);
            bean.setPartner(prop.getProperty("partner").trim());
            bean.setReferer(prop.getProperty("referer").trim());
            bean.setUsesdk(Integer.parseInt(bean.getPartner()));
        }
        return bean;
    }

    public void initSplus(final Activity activity, final InitCallBack initCallBack, InitBeanSuccess initBeanSuccess) {
        setActivity(activity);
        this.mInitCallBack = initCallBack;
        this.mInitBeanSuccess = initBeanSuccess;
        init(activity, mGameid, mAppkey, initCallBack, mOrientation == 1 ? Configuration.ORIENTATION_PORTRAIT : Configuration.ORIENTATION_LANDSCAPE);

    }

    private synchronized void init(Activity activity, Integer gameid, String appkey, InitCallBack initCallBack, int orientation) {
        if (initCallBack == null) {
            Log.d(TAG, "InitCallBack参数不能为空");
            return;
        }
        if (activity == null) {
            Log.d(TAG, "Activity参数不能为空");
            initCallBack.initFaile("Activity参数不能为空");
            return;
        } else {
            if (!(activity instanceof Activity)) {
                Log.d(TAG, "参数Activity不是一个Activity的实例");
                initCallBack.initFaile("参数Activity不是一个Activity的实例");
                return;
            }
        }
        if (TextUtils.isEmpty(appkey)) {
            Log.d(TAG, "appkey参数不能为空");
            initCallBack.initFaile("appkey参数不能为空");
            return;
        }
        if (gameid == null) {
            Log.i(TAG, "gameid参数不能为空");
            initCallBack.initFaile("gameid参数不能为空");
            return;
        }
        // 初始化获取屏幕高度和宽度
        mHeight = Phoneuitl.getHpixels(activity);
        mWidth = Phoneuitl.getWpixels(activity);
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // 横屏方向，高<宽
            if (mHeight > mWidth) {
                int temp = mWidth;
                mWidth = mHeight;
                mHeight = temp;
            }
        } else {
            // 竖屏方向 高>宽
            if (mHeight < mWidth) {
                int temp = mWidth;
                mWidth = mHeight;
                mHeight = temp;
            }
        }
        repeatInit(activity);

    }

    private synchronized void requestInit(Activity activity) {
        long time = DateUtil.getUnixTime();
        String mac = Phoneuitl.getLocalMacAddress(activity);
        String imei = Phoneuitl.getIMEI(activity);
        String keyString = mGameid + mReferer + mPartner + mac + imei + time;
        String sign = MD5Util.getMd5toLowerCase(keyString + mAppkey);
        ActiveModel mActiveMode = new ActiveModel(mGameid, mPartner, mReferer, mac, imei, mWidth, mHeight, Phoneuitl.MODE, Phoneuitl.OS, Phoneuitl.OSVER, time, sign);
        Log.d(TAG,NetHttpUtil.hashMapTOgetParams(mActiveMode, APIConstants.ACTIVE_URL));
        NetHttpUtil.getDataFromServerPOST(activity, new RequestModel(APIConstants.ACTIVE_URL, mActiveMode, new LoginParser()), onActiveCallBack);

    }

    /**
     * @Title: repeatInit(无网络时提示)
     * @author xiaoming.yuan
     * @data 2013-12-16 上午10:45:44 void 返回类型
     */
    private synchronized void repeatInit(final Activity activity) {

        if (!NetWorkUtil.isNetworkAvailable(activity)) {

            ProgressDialogUtil.showInfoDialog(activity, "提示", "当前网络不稳定,请检查您的网络设置！", 0, new OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    repeatInit(activity);

                }
            }, "确定", null, null, false);

        } else {
            requestInit(activity);
        }

    }

    /**
     * 初始化回调
     */
    protected DataCallback<JSONObject> onActiveCallBack = new DataCallback<JSONObject>() {
        @Override
        public void callbackSuccess(JSONObject paramObject) {
            try {
                if (paramObject != null && paramObject.optInt("code") == 1) {
                    JSONObject dataObject = paramObject.optJSONObject("data");
                    if (dataObject != null) {
                        setDeviceNo(dataObject.optString("deviceno"));
                        mInitBeanSuccess.initBeaned(true);
                    } else {
                        Toast.makeText(mActivity, "初始化失败", Toast.LENGTH_SHORT).show();
                        mInitCallBack.initFaile("初始化失败");
                    }
                } else {
                    String msg = paramObject.getString("msg");
                    Log.d(TAG, msg);
                    Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
                    mInitCallBack.initFaile(msg);
                }
            } catch (JSONException e) {
                Log.d(TAG, e.getLocalizedMessage());
                Toast.makeText(mActivity, "数据异常", Toast.LENGTH_SHORT).show();
                mInitCallBack.initFaile("初始化失败---数据异常---"+e.getLocalizedMessage());
            }
        }

        @Override
        public void callbackError(String error) {
            Log.d(TAG, error);
            Toast.makeText(mActivity, error, Toast.LENGTH_SHORT).show();
            mInitCallBack.initFaile(error);
        }
    };

    public Activity getActivity() {

        return this.mActivity;
    }

    public void setActivity(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public String getAppKey() {
        return this.mAppkey;
    }

    public void setAppKey(String appKey) {
        this.mAppkey = appKey;
    }

    public Integer getGameid() {
        return this.mGameid;
    }

    public void setGameid(int gameId) {
        this.mGameid = gameId;
    }

    public String getReferer() {
        return this.mReferer;
    }

    public void setReferer(String referer) {
        this.mReferer = referer;
    }

    public String getPartner() {
        return this.mPartner;
    }

    public void setPartner(String partner) {
        this.mPartner = partner;
    }

    public String getDeviceNo() {
        try {
            if (TextUtils.isEmpty(this.mDeviceNo)) {
                this.mDeviceNo = getdevicenoPreferences(mActivity.getApplicationContext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.mDeviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.mDeviceNo = deviceNo;
        try {
            setDevicenoPreferences(mActivity.getApplicationContext(), this.mDeviceNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setProperties(Properties prop) {
        this.mProperties = prop;
    }

    public Properties getProperties() {
        return this.mProperties;
    }

    public int getUsesdk() {
        return mUseSDK;
    }

    public void setUsesdk(int usesdk) {
        this.mUseSDK = usesdk;
    }

    public int getOrientation() {
        return mOrientation;
    }

    public void setOrientation(int orientation) {
        this.mOrientation = orientation;
    }

    public interface InitBeanSuccess {
        public void initBeaned(boolean initBeanSuccess);

    }

    /**
     * @Title: setDevicenoPreferences(保存服务器生成的设备唯一值)
     * @author xiaoming.yuan
     * @data 2013-12-16 下午4:45:25
     * @param context
     * @param value
     * @return boolean 返回类型
     */
    @SuppressWarnings("deprecation")
    private boolean setDevicenoPreferences(Context context, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("deviceno_file", Context.MODE_WORLD_READABLE | Context.MODE_WORLD_WRITEABLE);
        Editor edit = sharedPreferences.edit();
        edit.putString("deviceno", value);
        boolean commit = edit.commit();
        return commit;
    }

    /**
     * @Title: getdevicenoPreferences(获取保存服务器生成的设备唯一值)
     * @author xiaoming.yuan
     * @data 2013-12-16 下午4:45:32
     * @param context
     * @return String 返回类型
     */
    @SuppressWarnings("deprecation")
    private String getdevicenoPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("deviceno_file", Context.MODE_WORLD_READABLE | Context.MODE_WORLD_WRITEABLE);
        return sharedPreferences.getString("deviceno", "");

    }

}
