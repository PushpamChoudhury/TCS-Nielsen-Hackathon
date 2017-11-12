/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package attach;

import java.util.concurrent.ConcurrentHashMap;
import session.Session;

/**
 *
 * @author acer
 */
public final class Attacher {
    static ConcurrentHashMap<String, Response> attachResponse = new ConcurrentHashMap<>();
    static ConcurrentHashMap<String, String> attachCacheAPILvl1 = new ConcurrentHashMap<>();
    static ConcurrentHashMap<String, String> attachCacheUILvl1 = new ConcurrentHashMap<>();
    
    public static String checkAndAttachToCacheAPILvl1(String cacheKey) {
        if(attachCacheAPILvl1.containsKey(cacheKey)) {
            return attachCacheAPILvl1.get(cacheKey);
        }
        return null;
    }
    
    public static String checkAndAttachToCacheUILvl1(String cacheKey) {
        if(attachCacheUILvl1.containsKey(cacheKey)) {
            return attachCacheUILvl1.get(cacheKey);
        }
        return null;
    }
    
    public static String getResponse(Session ses, String attacherId) throws InterruptedException {
        if(ses.hasError()) {return "";}
        long inTime = System.currentTimeMillis();
        Response response = new Response();
        while(true) {
            if(System.currentTimeMillis() - inTime > ValuesFinal.ATTACHER_RESPONSE_TIME_OUT) {
                ses.addError("Timed Out.");
                attachResponse.put(attacherId, response);
                return "";
            } else if ((attachResponse.containsKey(attacherId))) {
                if ((attachResponse.get(attacherId).getStatus().equalsIgnoreCase("attached"))) {
                    continue;
                }  else if ((attachResponse.get(attacherId).getStatus().equalsIgnoreCase("done"))) {
                    return attachResponse.get(attacherId).getData();
                } else {
                    return "";
                }
            }
        }
    }
    
    public static void attachToCacheAPILvl1(String cacheKey, String responseMessage) {
        attachCacheAPILvl1.put(cacheKey, responseMessage);
    }
    
    public static void attachToCacheUILvl1(String cacheKey, String responseMessage) {
        attachCacheUILvl1.put(cacheKey, responseMessage);
    }
    
    public static void attachRequest(Session ses, String attacherId) {
        if(ses.hasError()) {return;}
        Response res = new Response();
        if (attachResponse.containsKey(attacherId)) {
            ses.addError("Attacher ID: " + attacherId + " already present.");
        } else {
            attachResponse.put(attacherId, res);
            res.addStatus("attached");
        }
    }
        
    public static void attachResponse(Session ses, String attacherId, Response res) {
        if(ses.hasError()) {return;}
        res.addStatus("done");
        if (attachResponse.containsKey(attacherId)) {
            attachResponse.put(attacherId, res);
        } else {
            ses.addError("Attacher ID: " + attacherId + " not found.");
        }
    }
        
    public static boolean attachIdIsAvailable(String attacherId) {
        if (attachResponse.containsKey(attacherId)) {
            return false;
        }
        return true;
    }
}
