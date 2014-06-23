
package com.android.splus.sdk.apiinterface;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

public class PayManager {
    private static final String TAG = "PayManager";

    private  IPayManager  mIPayManager=null;

    private static PayManager mPayManager=null;

    private InitBean mInitBean=null;

    private  Class<?> mIPayManagerClass=null;

    private boolean mLogDbug=false;

    public static final String LOCAL_SDK_VERSION = "1.0";

    public static final String SDKVERSION = "sdkVersion";

    public static final String GAMEVERSION = "gameVersion";

    public static final String UPDATEURL = "updateurl";

    public static final String UPDATECONTENT = "updatecontent";

    public static final String UPDATETYPE = "updatetype";

    /**
     * @Title: PayManager
     * @Description:( 将构造函数私有化)
     * @throws
     */
    private PayManager() {

    }

    /**
     * @Title: getInstance(获取实例)
     * @author xiaoming.yuan
     * @data 2014-2-26 下午2:30:02
     * @return PayManager 返回类型
     */
    public static PayManager getInstance() {

        if (mPayManager == null) {
            synchronized (PayManager.class) {
                if (mPayManager == null) {
                    mPayManager = new PayManager();
                }
            }
        }
        return mPayManager;
    }

    /**
     * Title: init Description:
     *
     * @param mBaseActivity
     * @param appkey
     * @param callBack
     * @param useupdate
     * @param screenType
     * @see com.android.splus.sdk.apiinterface.IPayManager#init(android.app.Activity,
     *      java.lang.String, com.android.splus.sdk.apiinterface.InitCallBack,
     *      boolean, int)
     */
    public void init(Activity activity, Integer gameid, String appkey, InitCallBack initCallBack, boolean useUpdate, Integer orientation) {
        Properties prop = readPropertites(activity, APIConstants.CONFIG_FILENAME);
        if(prop==null){
            initCallBack.initFaile("初始化失败--加载配置文件失败");
            Log.e(TAG, "初始化失败--加载配置文件失败");
            return;
        }
        mInitBean = InitBean.inflactBean(activity, prop, gameid, appkey, orientation);
        switch (mInitBean.getUsesdk()) {
            case APIConstants.SPLUS:
                try {
                    mIPayManagerClass =  Class.forName("com.android.splus.sdk.ui.SplusPayManager");
                    Method method = mIPayManagerClass.getMethod("getInstance", new Class[]{});
                    mIPayManager =(IPayManager)method.invoke(mIPayManagerClass, new Object[]{});
                } catch (IllegalArgumentException e) {
                    Log.e(TAG,e.getLocalizedMessage(),e);
                    initCallBack.initFaile(e.toString());
                    return;
                } catch (IllegalAccessException e) {
                    Log.e(TAG,e.getLocalizedMessage(),e);
                    initCallBack.initFaile(e.toString());
                    return;
                } catch (InvocationTargetException e) {
                    Log.e(TAG,e.getLocalizedMessage(),e);
                    initCallBack.initFaile(e.toString());
                    return;
                } catch (NoSuchMethodException e) {
                    Log.e(TAG,e.getLocalizedMessage(),e);
                    initCallBack.initFaile(e.toString());
                    return;
                } catch (ClassNotFoundException e) {
                    Log.e(TAG,e.getLocalizedMessage(),e);
                    initCallBack.initFaile(e.toString());
                    return;
                }

                break;
            case APIConstants.SPLUS_91:
                try {
                    mIPayManagerClass =  Class.forName("com.android.splus.sdk._91._91");
                    Method method = mIPayManagerClass.getMethod("getInstance", new Class[]{});
                    mIPayManager =(IPayManager)method.invoke(mIPayManagerClass, new Object[]{});
                } catch (IllegalArgumentException e) {
                    Log.e(TAG,e.getLocalizedMessage(),e);
                    initCallBack.initFaile(e.toString());
                    return;
                } catch (IllegalAccessException e) {
                    Log.e(TAG,e.getLocalizedMessage(),e);
                    initCallBack.initFaile(e.toString());
                    return;
                } catch (InvocationTargetException e) {
                    Log.e(TAG,e.getLocalizedMessage(),e);
                    initCallBack.initFaile(e.toString());
                    return;
                } catch (NoSuchMethodException e) {
                    Log.e(TAG,e.getLocalizedMessage(),e);
                    initCallBack.initFaile(e.toString());
                    return;
                } catch (ClassNotFoundException e) {
                    Log.e(TAG,e.getLocalizedMessage(),e);
                    initCallBack.initFaile(e.toString());
                    return;
                }
                break;
            case APIConstants.SPLUS_UC:
                try {
                    mIPayManagerClass =  Class.forName("com.android.splus.sdk._uc._UC");
                    Method method = mIPayManagerClass.getMethod("getInstance", new Class[]{});
                    mIPayManager =(IPayManager)method.invoke(mIPayManagerClass, new Object[]{});
                } catch (IllegalArgumentException e) {
                    Log.e(TAG,e.getLocalizedMessage(),e);
                    initCallBack.initFaile(e.toString());
                    return;
                } catch (IllegalAccessException e) {
                    Log.e(TAG,e.getLocalizedMessage(),e);
                    initCallBack.initFaile(e.toString());
                    return;
                } catch (InvocationTargetException e) {
                    Log.e(TAG,e.getLocalizedMessage(),e);
                    initCallBack.initFaile(e.toString());
                    return;
                } catch (NoSuchMethodException e) {
                    Log.e(TAG,e.getLocalizedMessage(),e);
                    initCallBack.initFaile(e.toString());
                    return;
                } catch (ClassNotFoundException e) {
                    Log.e(TAG,e.getLocalizedMessage(),e);
                    initCallBack.initFaile(e.toString());
                    return;
                }
                break;
            case APIConstants.SPLUS__360:
                try {
                    mIPayManagerClass =  Class.forName("com.android.splus.sdk._360._360");
                    Method method = mIPayManagerClass.getMethod("getInstance", new Class[]{});
                    mIPayManager =(IPayManager)method.invoke(mIPayManagerClass, new Object[]{});
                } catch (IllegalArgumentException e) {
                    Log.e(TAG,e.getLocalizedMessage(),e);
                    initCallBack.initFaile(e.toString());
                    return;
                } catch (IllegalAccessException e) {
                    Log.e(TAG,e.getLocalizedMessage(),e);
                    initCallBack.initFaile(e.toString());
                    return;
                } catch (InvocationTargetException e) {
                    Log.e(TAG,e.getLocalizedMessage(),e);
                    initCallBack.initFaile(e.toString());
                    return;
                } catch (NoSuchMethodException e) {
                    Log.e(TAG,e.getLocalizedMessage(),e);
                    initCallBack.initFaile(e.toString());
                    return;
                } catch (ClassNotFoundException e) {
                    Log.e(TAG,e.getLocalizedMessage(),e);
                    initCallBack.initFaile(e.toString());
                    return;
                }
                break;
            case APIConstants.SPLUS_XIAOMI:
                try {
                    mIPayManagerClass =  Class.forName("com.android.splus.sdk._xiaomi._XIAOMI");
                    Method method = mIPayManagerClass.getMethod("getInstance", new Class[]{});
                    mIPayManager =(IPayManager)method.invoke(mIPayManagerClass, new Object[]{});
                } catch (IllegalArgumentException e) {
                    Log.e(TAG,e.getLocalizedMessage(),e);
                    initCallBack.initFaile(e.toString());
                    return;
                } catch (IllegalAccessException e) {
                    Log.e(TAG,e.getLocalizedMessage(),e);
                    initCallBack.initFaile(e.toString());
                    return;
                } catch (InvocationTargetException e) {
                    Log.e(TAG,e.getLocalizedMessage(),e);
                    initCallBack.initFaile(e.toString());
                    return;
                } catch (NoSuchMethodException e) {
                    Log.e(TAG,e.getLocalizedMessage(),e);
                    initCallBack.initFaile(e.toString());
                    return;
                } catch (ClassNotFoundException e) {
                    Log.e(TAG,e.getLocalizedMessage(),e);
                    initCallBack.initFaile(e.toString());
                    return;
                }
                break;
            case APIConstants.SPLUS_DCN:
                try {
                    mIPayManagerClass =  Class.forName("com.android.splus.sdk._dcn._DCN");
                    Method method = mIPayManagerClass.getMethod("getInstance", new Class[]{});
                    mIPayManager =(IPayManager)method.invoke(mIPayManagerClass, new Object[]{});
                } catch (IllegalArgumentException e) {
                    Log.e(TAG,e.getLocalizedMessage(),e);
                    initCallBack.initFaile(e.toString());
                    return;
                } catch (IllegalAccessException e) {
                    Log.e(TAG,e.getLocalizedMessage(),e);
                    initCallBack.initFaile(e.toString());
                    return;
                } catch (InvocationTargetException e) {
                    Log.e(TAG,e.getLocalizedMessage(),e);
                    initCallBack.initFaile(e.toString());
                    return;
                } catch (NoSuchMethodException e) {
                    Log.e(TAG,e.getLocalizedMessage(),e);
                    initCallBack.initFaile(e.toString());
                    return;
                } catch (ClassNotFoundException e) {
                    Log.e(TAG,e.getLocalizedMessage(),e);
                    initCallBack.initFaile(e.toString());
                    return;
                }
                break;
            case APIConstants.SPLUS_DUOKU:
                try {
                    mIPayManagerClass =  Class.forName("com.android.splus.sdk._duoku._DUOKU");
                    Method method = mIPayManagerClass.getMethod("getInstance", new Class[]{});
                    mIPayManager =(IPayManager)method.invoke(mIPayManagerClass, new Object[]{});
                } catch (IllegalArgumentException e) {
                    Log.e(TAG,e.getLocalizedMessage(),e);
                    initCallBack.initFaile(e.toString());
                    return;
                } catch (IllegalAccessException e) {
                    Log.e(TAG,e.getLocalizedMessage(),e);
                    initCallBack.initFaile(e.toString());
                    return;
                } catch (InvocationTargetException e) {
                    Log.e(TAG,e.getLocalizedMessage(),e);
                    initCallBack.initFaile(e.toString());
                    return;
                } catch (NoSuchMethodException e) {
                    Log.e(TAG,e.getLocalizedMessage(),e);
                    initCallBack.initFaile(e.toString());
                    return;
                } catch (ClassNotFoundException e) {
                    Log.e(TAG,e.getLocalizedMessage(),e);
                    initCallBack.initFaile(e.toString());
                    return;
                }
                break;
            case APIConstants.SPLUS_GFAN:
                try {
                    mIPayManagerClass =  Class.forName("com.android.splus.sdk._gfan._GFAN");
                    Method method = mIPayManagerClass.getMethod("getInstance", new Class[]{});
                    mIPayManager =(IPayManager)method.invoke(mIPayManagerClass, new Object[]{});
                } catch (IllegalArgumentException e) {
                    Log.e(TAG,e.getLocalizedMessage(),e);
                    initCallBack.initFaile(e.toString());
                    return;
                } catch (IllegalAccessException e) {
                    Log.e(TAG,e.getLocalizedMessage(),e);
                    initCallBack.initFaile(e.toString());
                    return;
                } catch (InvocationTargetException e) {
                    Log.e(TAG,e.getLocalizedMessage(),e);
                    initCallBack.initFaile(e.toString());
                    return;
                } catch (NoSuchMethodException e) {
                    Log.e(TAG,e.getLocalizedMessage(),e);
                    initCallBack.initFaile(e.toString());
                    return;
                } catch (ClassNotFoundException e) {
                    Log.e(TAG,e.getLocalizedMessage(),e);
                    initCallBack.initFaile(e.toString());
                    return;
                }
                break;
            case APIConstants.SPLUS_OPPO:
                try {
                    mIPayManagerClass =  Class.forName("com.android.splus.sdk._oppo._OPPO");
                    Method method = mIPayManagerClass.getMethod("getInstance", new Class[]{});
                    mIPayManager =(IPayManager)method.invoke(mIPayManagerClass, new Object[]{});
                } catch (IllegalArgumentException e) {
                    Log.e(TAG,e.getLocalizedMessage(),e);
                    initCallBack.initFaile(e.toString());
                    return;
                } catch (IllegalAccessException e) {
                    Log.e(TAG,e.getLocalizedMessage(),e);
                    initCallBack.initFaile(e.toString());
                    return;
                } catch (InvocationTargetException e) {
                    Log.e(TAG,e.getLocalizedMessage(),e);
                    initCallBack.initFaile(e.toString());
                    return;
                } catch (NoSuchMethodException e) {
                    Log.e(TAG,e.getLocalizedMessage(),e);
                    initCallBack.initFaile(e.toString());
                    return;
                } catch (ClassNotFoundException e) {
                    Log.e(TAG,e.getLocalizedMessage(),e);
                    initCallBack.initFaile(e.toString());
                    return;
                }
                break;

        }

        //实例化对象
        if (mIPayManager != null) {
            mIPayManager.setDBUG(mLogDbug);
            mIPayManager.setInitBean(mInitBean);
            mIPayManager.init(activity, gameid, appkey, initCallBack, useUpdate, orientation);
        }else{
            initCallBack.initFaile("初始化实例失败");
            Log.e(TAG, "初始化实例失败");
        }
    }

    /**
     * 进入SDK登录
     */
    public void login(Activity activity, LoginCallBack loginCallBack) {
        if (mIPayManager != null) {
            mIPayManager.login(activity, loginCallBack);
        }
    }

    /**
     * 充值
     */
    public void recharge(Activity activity, Integer serverId, String serverName, Integer roleId, String roleName, String outOrderid, String pext, RechargeCallBack rechargeCallBack) {
        if (mIPayManager != null) {
            mIPayManager.recharge(activity, serverId, serverName, roleId, roleName, outOrderid, pext, rechargeCallBack);
        }
    }

    /**
     * 定额充值
     */
    public void rechargeByQuota(Activity activity, Integer serverId, String serverName, Integer roleId, String roleName, String outOrderid, String pext, Float money, RechargeCallBack rechargeCallBack) {
        if (mIPayManager != null) {
            mIPayManager.rechargeByQuota(activity, serverId, serverName, roleId, roleName, outOrderid, pext, money, rechargeCallBack);
        }
    }

    /**
     * 退出sdk
     */
    public void exitSDK() {
        if (mIPayManager != null) {
            mIPayManager.exitSDK();
        }
    }

    /**
     * 注销游戏接口
     *
     * @author xiaoming.yuan
     * @date 2013年10月12日 上午11:38:34
     */
    public void logout(Activity activity, LogoutCallBack logoutCallBack) {
        if (mIPayManager != null) {
            mIPayManager.logout(activity, logoutCallBack);
        }

    }

    /**
     * 控制日志DUBG
     */
    public void setDBUG(boolean logDbug) {
        this.mLogDbug = logDbug;
        if (mIPayManager != null) {
            mIPayManager.setDBUG(mLogDbug);
        }
    }

    /**
     * 进入个人中心
     *
     * @author xiaoming.yuan
     * @date 2013年10月14日 上午10:27:05
     */
    public void enterUserCenter(Activity activity, LogoutCallBack logoutCallBack) {
        if (mIPayManager != null) {
            mIPayManager.enterUserCenter(activity, logoutCallBack);
        }
    }

    /**
     * 統計区服角色等级接口
     */
    public void sendGameStatics(Activity activity, Integer serverId, String serverName, Integer roleId, String roleName, String level) {
        if (mIPayManager != null) {
            mIPayManager.sendGameStatics(activity, serverId, serverName, roleId, roleName, level);
        }
    }

    /**
     * 论坛
     */
    public void enterBBS(Activity activity) {
        if (mIPayManager != null) {
            mIPayManager.enterBBS(activity);
        }
    }

    /**
     * 悬浮按钮
     */

    public void creatFloatButton(Activity activity, boolean showlasttime, int align, float position) {
        if (mIPayManager != null) {
            mIPayManager.creatFloatButton(activity, showlasttime, align, position);
        }

    }

    /**
     * 在线时长统计开始
     */

    public void onResume(Activity activity) {
        if (mIPayManager != null) {
            mIPayManager.onResume(activity);
        }
    }

    /**
     * 在线时长统计结束
     */
    public void onPause(Activity activity) {
        if (mIPayManager != null) {
            mIPayManager.onPause(activity);
        }
    }

    public void onStop(Activity activity) {
        if (mIPayManager != null) {
            mIPayManager.onStop(activity);
        }

    }

    public void onDestroy(Activity activity) {

        if (mIPayManager != null) {
            mIPayManager.onDestroy(activity);
        }
    }

    /**
     * 取assets下的配置参数
     *
     * @param context
     * @param file
     * @return
     */
    private Properties readPropertites(Context context, String file) {
        Properties p = null;
        try {
            InputStream in = context.getResources().getAssets().open(file);
            p = new Properties();
            p.load(in);
        } catch (IOException e) {
            p = null;
        }
        return p;
    }

}
