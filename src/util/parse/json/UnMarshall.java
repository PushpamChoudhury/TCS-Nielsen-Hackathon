/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util.parse.json;

import com.sun.jersey.api.json.JSONJAXBContext;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import session.Session;

/**
 *
 * @author acer
 */
public class UnMarshall {
    
    public synchronized static <T> Object begin(Session ses, Class<T> cr, String s) {
        if(ses.hasError()) {return "";}
        try {
            JAXBContext context = JSONJAXBContext.newInstance( cr );
            Unmarshaller um = context.createUnmarshaller();
            com.sun.jersey.api.json.JSONUnmarshaller unmarshaller = JSONJAXBContext.getJSONUnmarshaller(um);
            InputStream is = new ByteArrayInputStream(s.getBytes("UTF-8"));
            return unmarshaller.unmarshalFromJSON(is, cr);
        } catch (JAXBException | UnsupportedEncodingException e) {
            ses.addError("JAXBException / UnsupportedEncodingException: " + e);
            return "";
        }
    }
}
