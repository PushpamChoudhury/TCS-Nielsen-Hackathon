/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util.parse.json;

import com.sun.jersey.api.json.JSONJAXBContext;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import session.Session;
/**
 *
 * @author acer
 */
public class Marshall {
        
    public synchronized static String begin(Session ses, Object o) {
        if(ses.hasError()) {return "";}
        try {
            StringWriter writer = new StringWriter();
            JAXBContext context = JSONJAXBContext.newInstance(o.getClass());
            Marshaller m = context.createMarshaller();
            com.sun.jersey.api.json.JSONMarshaller marshaller = JSONJAXBContext.getJSONMarshaller(m);
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshallToJSON(o, writer);
            return writer.toString();
        } catch (JAXBException e) {
            ses.addError("JAXBException: " + e);
            return "";
        }
    }
}
