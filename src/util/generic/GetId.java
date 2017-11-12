/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util.generic;

import attach.Attacher.*;
/**
 *
 * @author acer
 */
public class GetId {
    
    public static synchronized String getAttacherId() {
        int attacherIdNum = 0;
        String attacherIdStr = String.valueOf(System.currentTimeMillis());
        while (true) {
            String attacherId = attacherIdStr + "-" + attacherIdNum++;
            if(attach.Attacher.attachIdIsAvailable(attacherIdStr + "-" + attacherIdNum++)) {
                return attacherId;
            }
        }
    }
}
