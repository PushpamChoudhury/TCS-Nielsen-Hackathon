/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gateway.router.Route;

import session.Session;

/**
 *
 * @author 510720
 */
public class LoadClassTransactionAndProcess {

    LoadClassTransactionAndProcess() {}

    public String begin(Session ses, String viewName, String transactionClass, String[] tranParameters) {
        String tranId = viewName.trim() + ":" + transactionClass.trim();
        switch(tranId.toLowerCase()) {
            case "consumer:productsearch" : 
                return (view.consumer.Consumer.getProductSearchResponse(ses, tranParameters));
        }
        return null;
    }
}
