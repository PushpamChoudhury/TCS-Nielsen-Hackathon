/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gateway.client;

import attach.Attacher;
import java.util.ArrayList;

import session.Session;
import attach.Response;

public class GetHttpHelper implements Runnable {
    
    Session ses;
    String httpScheme;
    String urlHome;
    String urlCat;
    ArrayList urlQueryList;
    
    String attacherId;
    
    GetHttpHelper(Session ses, String attacherId, String httpScheme, String urlHome, String urlCat, ArrayList urlQueryList) {
        this.ses = ses;
        this.httpScheme = (httpScheme.equalsIgnoreCase("def")) ? ValuesFinal.DEF_CLIENT_HTTP_SCHEME : httpScheme;
        this.urlHome = (urlHome.equalsIgnoreCase("def")) ? ValuesFinal.DEF_CLIENT_HTTP_CLIENT : urlHome;
        this.urlCat = urlCat;
        this.urlQueryList = urlQueryList;
        this.attacherId = attacherId;
    }

    @Override
    public void run() {
        try {
            Response res = new Response();
            String requestUrl = util.url.QueryBuilder.buildGet(this.ses, this.httpScheme, this.urlHome, this.urlCat, this.urlQueryList).toString();
            String cachedResponse = null;
            String cacheKey = requestUrl.substring(0, requestUrl.lastIndexOf("apikey"));
            if (ValuesFinal.CacheAPILvl1) {
                if((cachedResponse = Attacher.checkAndAttachToCacheAPILvl1(cacheKey)) != null) {
                    System.out.println("byyyyyyy cache");
                    res.addData(cachedResponse);
                    Attacher.attachResponse(ses, this.attacherId, res);
                } else {
                   String responseMessage = HttpUtil.getHttpUtil().doGet(ses, requestUrl);
                   res.addData(responseMessage);
                   Attacher.attachResponse(ses, this.attacherId, res);
                   Attacher.attachToCacheAPILvl1(cacheKey, responseMessage);
                }
            } else {
                String responseMessage = HttpUtil.getHttpUtil().doGet(ses, requestUrl);
                res.addData(responseMessage);
                Attacher.attachResponse(ses, this.attacherId, res);
            }
        } catch (Exception e) {
            Response res = new Response();
            res.addStatus(e.getMessage());
            Attacher.attachResponse(ses, this.attacherId, res);
        }
    } 
} 
