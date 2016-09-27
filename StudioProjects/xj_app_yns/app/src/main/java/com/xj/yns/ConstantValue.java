package com.xj.yns;

public interface ConstantValue {
    // class :public static final
    String ENCONDING = "UTF-8";
    /**
     * 服务器返回成功状态码
     */
    String SUCCESS = "0";

    //1,水情列表信息
    String WATER_URL = "/GetJsonResult/Pr_GetWaterInfo";


    /**************
     * 首页URL
     *******************/
    //首页Host
    String HOME_HOST = "http://192.168.1.45:8080/ynsServer";
    //首页轮播图的URL

    String HOME_BANNER_URL = "http://192.168.1.45:8080/ynsServer/api/home/getBanner";
    //首页显示企业信息的URL
    String HOME_GRIDVIEW_URL = "http://192.168.1.45:8080/ynsServer/api/home/getCompany";

    //首页信息集市的URL
    String HOME_MSGMARKET_URL="http://192.168.1.45:8080/ynsServer/api/message/getTopmessage";

    //信息集市的所有子条目的URL
    String MSG_URL0 = "http://192.168.1.45:8080/ynsServer/api/message/getYonggong";
    String MSG_URL1 = "http://192.168.1.45:8080/ynsServer/api/message/getQiuzhi ";
    String MSG_URL2 = "http://192.168.1.45:8080/ynsServer/api/message/getGongying ";
    String MSG_URL3 = "http://192.168.1.45:8080/ynsServer/api/message/getQiugou";
    String MSG_URL4 = "http://192.168.1.45:8080/ynsServer/api/message/getOther";

    //首页gridview所有条目点击事件共同的URL
    String HOME_GV_DETAIL_URL ="http://192.168.1.45:8080/ynsServer/api/getCommonDetail/";

    /***********益农社URL***************/
    //全部新闻的URL
    String YNS_ALLNEWS_URL="http://192.168.1.45:8080/ynsServer/api/news/getTopnews";
    //种植的URL
    String YNS_PLANT_URL = "http://192.168.1.45:8080/ynsServer/api/news/getZhongzhi";
    //三农的URL
    String YNS_SN_URL = "http://192.168.1.45:8080/ynsServer/api/news/getSannongrenwu";
    //农资的URL
    String YNS_NZ_URL = "http://192.168.1.45:8080/ynsServer/api/news/getNongzi";
    //养殖的URL
    String YNS_FEED_URL="http://192.168.1.45:8080/ynsServer/api/news/getYangzhi";
    //首页
    int VIEW_HOME = 2;

    //新闻
    int VIEW_NEWS = 4;
    //益农社
    int VIEW_YNS = 6;
    //我的
    int VIEW_MY = 10;

    //主页详情
    int VIEW_HOME_DETAIL = 12;

    //首页信息集市
    int VIEW_HOME_MSGMARKET = 14;

    //首页更多
    int VIEW_HOME_MORE = 16;
    //首页企业库
    int VIEW_HOME_COMPANYLIBS = 18;
    //首页三农风光
    int VIEW_HOME_SNAPPEARANCE = 20;
    //首页益农社
    int VIEW_HOME_YNS = 22;
    //益农社定位
    int VIEW_YNS_LOCATION = 24;
    int VIEW_HOME_SEARCH = 26;
/****************信息集市中的界面****************************/
    //信息集市中的用工信息界面
    int VIEW_MSGMARKET_EMPLOY = 28;
    //信息集市中的求职界面
    int VIEW_MSGMARKET_FIND_JOB = 29;
    //信息集市中的供应界面
    int VIEW_MSGMARKET_SUPPLY = 30;
    //信息集市中的求购信息界面
    int VIEW_MSGMARKET_SEEK_BUYER= 31;
    //信息集市中的其他信息界面
    int VIEW_MSGMARKET_OTHER = 32;

}
