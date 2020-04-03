package com.joindoo.jdwechat.utils;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

/**
 * Created by zhuqiang1 on 2016/5/30.
 */
public class HttpConnectionManager {
    // private static HttpParams httpParams;
    private static PoolingHttpClientConnectionManager cm;

    /**
     * 最大连接数
     */
    public final static int MAX_TOTAL_CONNECTIONS = 200;
    /**
     * 获取连接的最大等待时间
     */
    public final static int WAIT_TIMEOUT = 90000;
    /**
     * 每个路由最大连接数
     */
    public final static int MAX_ROUTE_CONNECTIONS = 50;
    /**
     * 连接超时时间
     */
    public final static int CONNECT_TIMEOUT = 90000;
    /**
     * 读取超时时间
     */
    public final static int READ_TIMEOUT = 60000;
    public final static String useragent = "Mozilla/4.0 (compatible; MSIE 5.5; Windows 98)";

    // static {
    // try {
    // 需要通过以下代码声明对https连接支持
    // SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(null,
    // new TrustSelfSignedStrategy())
    // .build();
    // HostnameVerifier hostnameVerifier =
    // SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
    // SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
    // sslcontext,hostnameVerifier);
    // Registry<ConnectionSocketFactory> socketFactoryRegistry =
    // RegistryBuilder.<ConnectionSocketFactory>create()
    // .register("http", PlainConnectionSocketFactory.getSocketFactory())
    // .register("https", sslsf)
    // .build();
    // 初始化连接管理器
    // cm = new PoolingHttpClientConnectionManager();
    // Increase max total connection to 200
    // cm.setMaxTotal(MAX_TOTAL_CONNECTIONS);

    // Increase default max connection per route to 20
    // cm.setDefaultMaxPerRoute(MAX_ROUTE_CONNECTIONS);

    // } //catch (KeyManagementException e) {
    // TODO Auto-generated catch block
    // e.printStackTrace();
    // } catch (NoSuchAlgorithmException e) {
    // TODO Auto-generated catch block
    // e.printStackTrace();
    // } catch (KeyStoreException e) {
    // TODO Auto-generated catch block
    // e.printStackTrace();
    // }

    // }

    public static CloseableHttpClient getHttpClient() {
        cm = new PoolingHttpClientConnectionManager();
        // Increase max total connection to 200
        cm.setMaxTotal(MAX_TOTAL_CONNECTIONS);
        // Increase default max connection per route to 20
        cm.setDefaultMaxPerRoute(MAX_ROUTE_CONNECTIONS);
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(60000).setSocketTimeout(90000).build();
        LaxRedirectStrategy redirectStrategy = new LaxRedirectStrategy();
        // DefaultHttpRequestRetryHandler handler = new
        // DefaultHttpRequestRetryHandler(3,false);
        CloseableHttpClient client = HttpClients.custom().setConnectionManager(cm).setDefaultRequestConfig(requestConfig).disableAutomaticRetries().setRedirectStrategy(redirectStrategy).setUserAgent(useragent).build();
       // client.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);//cookie策略 对这个请求覆盖默认策略
        return client;
    }
}
