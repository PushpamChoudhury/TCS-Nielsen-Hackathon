package gateway.router.Route;

import org.json.JSONException;
import org.json.JSONObject;
import session.Session;

public class ResponseFormatter {

    static JSONObject begin(Session ses, JSONObject jObj) {
        return jObj;
    }

    static JSONObject begin(Session ses) {
        JSONObject jObj = new JSONObject();
        if(ses.hasError()) {
            try {
                JSONObject jObjError = jObj.optJSONObject("error");
                jObjError.put("error_flag", true);
                jObjError.put("error_list", ses.getErrorList());
                if(ses.hasAppError()) {
                    jObjError.put("app_error_flag", true);
                    jObjError.put("app_error_list", ses.getAppErrorList());
                }
            } catch (JSONException ex) {
                ses.addError("JSONException: " + ex);
            }
        }
        return null;
    }
}
