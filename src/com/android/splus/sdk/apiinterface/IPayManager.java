
package com.android.splus.sdk.apiinterface;


import android.app.Activity;

/**
 * 游戏充值管理接口（对外api接口）
 *
 * @author xiaoming.yuan
 * @ClassName: IPayManager
 * @date 2013-8-9 下午1:03:54
 */
public interface IPayManager {

    /**
     * @Title: setInitBean(初始化bean)
     * @author xiaoming.yuan
     * @data 2014-1-15 上午9:47:20
     * @param bean void 返回类型
     */
    public void setInitBean(InitBean bean);

    /**
     * @param context
     * @param appkey
     * @param callBack 初始化回调
     * @param useupdate 是否使用SDK平台的更新机制
     */

    public void init(Activity activity, Integer gameid, String appkey, InitCallBack initCallBack, boolean useUpdate, Integer orientation);

    /**
     * 进入SDK登录
     */
    public void login(Activity activity, LoginCallBack loginCallBack);

    /**
     * 充值
     */
    public void recharge(Activity activity, Integer serverId, String serverName, Integer roleId, String roleName, String outOrderid, String pext, RechargeCallBack rechargeCallBack);

    /**
     * 定额充值
     */
    public void rechargeByQuota(Activity activity, Integer serverId, String serverName, Integer roleId, String roleName, String outOrderid, String pext, Float money, RechargeCallBack rechargeCallBack);

    /**
     * 退出sdk
     */
    public void exitSDK();


    /**
     * 注销游戏接口
     *
     * @author xiaoming.yuan
     * @date 2013年10月12日 上午11:38:34
     */
    public void logout(Activity activity, LogoutCallBack logoutCallBack);

    /**
     * 控制日志DUBG
     */
    public void setDBUG(boolean logDbug);

    /**
     * 进入个人中心
     *
     * @author xiaoming.yuan
     * @date 2013年10月14日 上午10:27:05
     */
    public void enterUserCenter(Activity activity, LogoutCallBack logoutCallBack);

    /**
     * 統計区服角色等级接口
     */
    public void sendGameStatics(Activity activity, Integer serverId, String serverName, Integer roleId, String roleName, String level);

    /**
     * 论坛
     */
    public void enterBBS(Activity activity);

    /**
     * 悬浮按钮
     */

    public void creatFloatButton(Activity activity, boolean showlasttime, int align, float position);

    /**
     * 在线时长统计开始
     */

    public void onResume(Activity activity);

    /**
     * 在线时长统计结束
     */
    public void onPause(Activity activity);

    /**
     * @Title: onStop(activity不可见时)
     * @author xiaoming.yuan
     * @data 2014-4-29 下午12:33:01
     * @param activity void 返回类型
     */
    public void onStop(Activity activity);

    /**
     * @Title: onDestroy(activity 销毁时)
     * @author xiaoming.yuan
     * @data 2014-4-29 下午12:34:09
     * @param activity void 返回类型
     */
    public void onDestroy(Activity activity);

}
