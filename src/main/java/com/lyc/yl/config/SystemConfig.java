package com.lyc.yl.config;

import com.lyc.yl.util.StringUtils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统设置
 */
public class SystemConfig {
    // 小程序相关配置
    public final static String STORE_WX_INDEX_NEW = "store_wx_index_new";
    public final static String STORE_WX_INDEX_HOT = "store_wx_index_hot";
    public final static String STORE_WX_INDEX_BRAND = "store_wx_index_brand";
    public final static String STORE_WX_INDEX_TOPIC = "store_wx_index_topic";
    public final static String STORE_WX_INDEX_CATLOG_LIST = "store_wx_catlog_list";
    public final static String STORE_WX_INDEX_CATLOG_GOODS = "store_wx_catlog_goods";
    public final static String STORE_WX_SHARE = "store_wx_share";
    // 运费相关配置
    public final static String STORE_EXPRESS_FREIGHT_VALUE = "store_express_freight_value";
    public final static String STORE_EXPRESS_FREIGHT_MIN = "store_express_freight_min";
    public final static String STORE_CUSTOM_SERVICES_PRICE = "store_custom_services_price";
    // 订单相关配置
    public final static String STORE_ORDER_UNPAID = "store_order_unpaid";
    public final static String STORE_ORDER_UNCONFIRM = "store_order_unconfirm";
    public final static String STORE_ORDER_COMMENT = "store_order_comment";
    // 邮箱配置
    public final static String STORE_MAIL_SEND_FROM = "store_mail_send_from";
    public final static String STORE_MAIL_SEND_TO = "store_mail_send_to";
    public final static String STORE_MAIL_SUBJECT = "store_mail_subject";
    public final static String STORE_MAIL_CONTENT = "store_mail_content";
    public final static String STORE_MAIL_HOST = "store_mail_host";
    public final static String STORE_MAIL_USERNAME = "store_mail_username";
    public final static String STORE_MAIL_PASSWORD = "store_mail_password";
    public final static String STORE_MAIL_PROT = "store_mail_prot";
    // 商场相关配置
    public final static String STORE_MALL_NAME = "store_mall_name";
    public final static String STORE_MALL_ADDRESS = "store_mall_address";
    public final static String STORE_MALL_PHONE = "store_mall_phone";
    public final static String STORE_MALL_QQ = "store_mall_qq";
    public final static String STORE_MALL_LONGITUDE = "store_mall_longitude";
    public final static String STORE_MALL_Latitude = "store_mall_latitude";
    public final static String STORE_GOODS_PICTURE = "store_goods_picture";
    // 宝尊配置
 	public static final String BAOZUN_OMS_URL = "baozun_oms_url";
 	public static final String BAOZUN_APPKEY = "baozun_appkey";
 	public static final String BAOZUN_SECRET = "baozun_secret";
 	public static final String BAOZUN_UNEX_URL = "baozun_unex_url";
 	public static final String BAOZUN_UNEX_SAAS_TENANT_CODE = "baozun_unex_saas_tenant_code";
 	public static final String BAOZUN_UNEX_STORE_CODE = "baozun_unex_store_code";
 	public static final String BAOZUN_UNEX_STORE_NAME = "baozun_unex_store_name";
 	public static final String BAOZUN_UNEX_IP = "baozun_unex_ip";
    // avarto 配置
 	public static final String AVARTO_URL = "avarto_url";
	public static final String AVARTO_SHOP_ID = "avarto_shop_id";
	public static final String AVARTO_USERNAME = "avarto_username";
	public static final String AVARTO_PASSWORD = "avarto_password";
    // BANMA配置
	public final static String BANMA_FILE_URL = "banma_file_url";
	public final static String BANMA_FILE_ENDPOINT = "banma_file_endpoint";
	public final static String BANMA_FILE_ACCESS_KEY_ID = "banma_file_access_key_id";
	public final static String BANMA_FILE_ACCESS_KEY_SECRET = "banma_file_access_key_secret";
	public final static String BANMA_FILE_BACKET_NAME = "banma_file_backet_name";
	public final static String BANMA_CALLBACK_FILE_PATH = "banma_callback_file_path";
	public final static String BANMA_PICTURE_PATH = "banma_picture_path";
	public final static String BANMA_PICTURE_LOCAL_PATH = "banma_picture_local_path";
	public final static String BANMA_PICTURE_LOCAL_PATH_BACK = "banma_picture_local_path_back";
	public final static String BANMA_PICTURE_LOCAL = "banma_picture_local";

	/** redis */
	// Redis 是否可以使用
    public final static String HUB_IS_USE_REDIS = "hub_use_redis";
    // CRM接口Token的有效期
    public static final String AVARTO_TOKEN_EXPIRE_TIME = "avarto_token_expire_time";
    // 渠道的验证的开关
    public static final String CHANNEL_CHECK_SWITCH = "channel_check_switch";
    // 接口的验证开关
    public static final String INTERFACE_CHECK_SWITCH = "interface_check_switch";
    // 幂等的开关
    public static final String IDEMPOTENT_CHECK_SWITCH = "idempotent_check_switch";
    // 幂等的 过期时间
    public static final String IDEMPOTENT_TOKEN_EXPIRE_TIME = "idempotent_token_expire_time";

    //所有的配置均保存在该 HashMap 中
    private static Map<String, String> SYSTEM_CONFIGS = new HashMap<>();

    private static String getConfig(String keyName) {
        return SYSTEM_CONFIGS.get(keyName);
    }

    private static Integer getConfigInt(String keyName) {
    	String result = SYSTEM_CONFIGS.get(keyName);
        return StringUtils.isEmpty(result) ? 0 : Integer.parseInt(SYSTEM_CONFIGS.get(keyName));
    }

    private static Boolean getConfigBoolean(String keyName) {
        return Boolean.valueOf(SYSTEM_CONFIGS.get(keyName));
    }

    private static BigDecimal getConfigBigDec(String keyName) {
        return new BigDecimal(SYSTEM_CONFIGS.get(keyName));
    }

    public static Integer getNewLimit() {
        return getConfigInt(STORE_WX_INDEX_NEW);
    }

    public static Integer getHotLimit() {
        return getConfigInt(STORE_WX_INDEX_HOT);
    }

    public static Integer getBrandLimit() {
        return getConfigInt(STORE_WX_INDEX_BRAND);
    }

    public static Integer getTopicLimit() {
        return getConfigInt(STORE_WX_INDEX_TOPIC);
    }

    public static Integer getCatlogListLimit() {
        return getConfigInt(STORE_WX_INDEX_CATLOG_LIST);
    }

    public static Integer getCatlogMoreLimit() {
        return getConfigInt(STORE_WX_INDEX_CATLOG_GOODS);
    }

    public static boolean isAutoCreateShareImage() {
        return getConfigBoolean(STORE_WX_SHARE);
    }

    public static BigDecimal getFreight() {
        return getConfigBigDec(STORE_EXPRESS_FREIGHT_VALUE);
    }

    public static BigDecimal getFreightLimit() {
        return getConfigBigDec(STORE_EXPRESS_FREIGHT_MIN);
    }
    public static BigDecimal getCustomServicesPrice() {
        return getConfigBigDec(STORE_CUSTOM_SERVICES_PRICE);
    }

    public static Integer getOrderUnpaid() {
        return getConfigInt(STORE_ORDER_UNPAID);
    }

    public static Integer getOrderUnconfirm() {
        return getConfigInt(STORE_ORDER_UNCONFIRM);
    }

    public static Integer getOrderComment() {
        return getConfigInt(STORE_ORDER_COMMENT);
    }

    public static String getMailSendFrom() {
        return getConfig(STORE_MAIL_SEND_FROM);
    }

    public static String[] getMailSendTo() {
        return getConfig(STORE_MAIL_SEND_TO).split(";");
    }

    public static String getMailContent() {
        return getConfig(STORE_MAIL_CONTENT);
    }

    public static String getMailHost() {
        return getConfig(STORE_MAIL_HOST);
    }

    public static String getMailPassword() {
        return getConfig(STORE_MAIL_PASSWORD);
    }

    public static Integer getMailProt() {
        return getConfigInt(STORE_MAIL_PROT);
    }

    public static String getMailSubject() {
        return getConfig(STORE_MAIL_SUBJECT);
    }

    public static String getMailUsername() {
        return getConfig(STORE_MAIL_USERNAME);
    }

    public static String getMallName() {
        return getConfig(STORE_MALL_NAME);
    }

    public static String getMallAddress() {
        return getConfig(STORE_MALL_ADDRESS);
    }

    public static String getMallPhone() {
        return getConfig(STORE_MALL_PHONE);
    }

    public static String getMallQQ() {
        return getConfig(STORE_MALL_QQ);
    }

    public static String getMallLongitude() {
        return getConfig(STORE_MALL_LONGITUDE);
    }

    public static String getMallLatitude() {
        return getConfig(STORE_MALL_Latitude);
    }

    public static String getGoodsPicture() {
        return getConfig(STORE_GOODS_PICTURE);
    }

    public static String getBaozunOmsUrl() {
        return getConfig(BAOZUN_OMS_URL);
    }
    public static String getBaozunUnexUrl() {
        return getConfig(BAOZUN_UNEX_URL);
    }
    public static String getBaozunAppKey() {
        return getConfig(BAOZUN_APPKEY);
    }
    public static String getBaozunSecret() {
        return getConfig(BAOZUN_SECRET);
    }
    public static String getBaozunUnexSaasTenantCode() {
        return getConfig(BAOZUN_UNEX_SAAS_TENANT_CODE);
    }
    public static String getBaozunUnexStoreCode() {
        return getConfig(BAOZUN_UNEX_STORE_CODE);
    }
    public static String getBaozunUnexStoreName() {
        return getConfig(BAOZUN_UNEX_STORE_NAME);
    }
    public static List<String> getBaozunUnexIp() {
    	String ipList = getConfig(BAOZUN_UNEX_IP);
        return Arrays.asList(ipList.split(","));
    }
    public static String getAvartoUrl() {
        return getConfig(AVARTO_URL);
    }
    public static String getAvartoShopId() {
        return getConfig(AVARTO_SHOP_ID);
    }
    public static String getAvartoUserName() {
        return getConfig(AVARTO_USERNAME);
    }
    public static String getAvartoPassword() {
        return getConfig(AVARTO_PASSWORD);
    }
    public static String getBanmaFileUrl() {
        return getConfig(BANMA_FILE_URL);
    }
    public static String getBanmaFileEndpoint() {
        return getConfig(BANMA_FILE_ENDPOINT);
    }
    public static String getBanmaFileAccessKeyId() {
        return getConfig(BANMA_FILE_ACCESS_KEY_ID);
    }
    public static String getBanmaFileAccessKeySecret() {
        return getConfig(BANMA_FILE_ACCESS_KEY_SECRET);
    }
    public static String getBanmaFileBacketName() {
        return getConfig(BANMA_FILE_BACKET_NAME);
    }
    public static String getBanmaCallbackFilePath() {
        return getConfig(BANMA_CALLBACK_FILE_PATH);
    }
    public static String getBanmaPicturePath() {
        return getConfig(BANMA_PICTURE_PATH);
    }
    public static String getBanmaPictureLocalPath() {
        return getConfig(BANMA_PICTURE_LOCAL_PATH);
    }
    public static String getBanmaPictureLocalPathBack() {
        return getConfig(BANMA_PICTURE_LOCAL_PATH_BACK);
    }
    public static boolean getBanmaPictureLocal() {
        return getConfigBoolean(BANMA_PICTURE_LOCAL);
    }
    public static void setConfigs(Map<String, String> configs) {
        SYSTEM_CONFIGS = configs;
    }

    public static void updateConfigs(Map<String, String> data) {
        for (Map.Entry<String, String> entry : data.entrySet()) {
            SYSTEM_CONFIGS.put(entry.getKey(), entry.getValue());
        }
    }

    public static Integer getAvartoTokenExpireTime() {
        return getConfigInt(AVARTO_TOKEN_EXPIRE_TIME);
    }

    public static boolean getHubIsUseRedis() {
        return getConfigBoolean(HUB_IS_USE_REDIS);
    }

    public static boolean getChannelCheckSwitch() {
        return getConfigBoolean(CHANNEL_CHECK_SWITCH);
    }

    public static boolean getInterfaceCheckSwitch() {
        return getConfigBoolean(INTERFACE_CHECK_SWITCH);
    }

    public static boolean getIdempotentCheckSwitch() {
        return getConfigBoolean(IDEMPOTENT_CHECK_SWITCH);
    }

    public static Integer getIdempotentTokenExpireTime() {
        return getConfigInt(IDEMPOTENT_TOKEN_EXPIRE_TIME);
    }
}
