package gateway.router.Route;

import org.json.JSONException;
import org.json.JSONObject;

import attach.Attacher;
import session.Session;

public class Route {
    
    public static JSONObject begin(Session ses, String viewName, String transactionClass, boolean cacheOn, String cacheKey, String[] tranParameters) { 
        String cachedResponse = null;
        if(cacheOn && (cachedResponse = Attacher.checkAndAttachToCacheUILvl1(cacheKey)) != null) {
            try {
                JSONObject jObj = new JSONObject(cachedResponse);
                return ResponseFormatter.begin(ses, jObj);
            } catch (JSONException ex) {
                ses.addError("JSONException: " + ex);
                return ResponseFormatter.begin(ses);
            }
        } else {
            try {
                LoadClassTransactionAndProcess lctap = new LoadClassTransactionAndProcess();
                String response = lctap.begin(ses, viewName, transactionClass, tranParameters);
                System.out.println("response: " + response);
                if(cacheOn && !ses.hasError()) {Attacher.attachToCacheUILvl1(cacheKey, response);}
                JSONObject jObj = new JSONObject(response);
                return ResponseFormatter.begin(ses, jObj);
            } catch (JSONException ex) {
                ses.addError("JSONException: " + ex);
                return ResponseFormatter.begin(ses);
            }
        }
    }
    
    public static void main(String args[]) throws JSONException {
        String[] args1 = {"aqsdasdasd", "asdkhaksdh"};
        Session ses = new Session();
        JSONObject jo = Route.begin(new Session(), "Consumer", "ProductSearch", false, null, args1);
    }
}