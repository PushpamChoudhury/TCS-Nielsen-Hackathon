package api.neilsen.productreference;

import attach.Attacher;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.xml.bind.JAXBException;
import session.Session;

public class ProductReference {
    
    public static class Request {
    
        public static class ByDescription {
            
            Session ses;
            private String attacherId = null;
            private final ArrayList<HashMap<String, String>> queryList = new ArrayList<>(4);
                        
            private final String pageSizeId = "pgSize";
            private final int pageSizeIndex = 1;
            
            private final String pageNoId = "pgNo";
            private final int pageNoIndex = 2;
            
            private final String productDescriptionId = "search";
            private final int productDescriptionIndex = 0;
            
            private final String APIKeyId = "apikey";
            private final int APIKeyIndex = 3;

            public ByDescription(Session ses) {
                this.ses = ses;
                for(int i = 0; i < 4; i++) {
                    queryList.add(new HashMap<String, String>());
                }
                setAPIKey(ses.getApiKey());
            }
                        
            public void setProductDescription(String productDescription) {
                this.queryList.get(productDescriptionIndex).put(productDescriptionId, productDescription.toUpperCase());
            }
            
            public void setPageSize(String pageSize) {
                this.queryList.get(pageSizeIndex).put(pageSizeId, pageSize);
            }
            
            public void setPageNo(String pageNo) {
                this.queryList.get(pageNoIndex).put(pageNoId, pageNo);
            }
            
            private void setAPIKey(String APIKey) {
                this.queryList.get(APIKeyIndex).put(APIKeyId, APIKey);
            }

            public void fetchConcurrent() {
                            
                if(this.queryList.get(productDescriptionIndex).isEmpty()) {
                    ses.addError("productDescription is mandatory and cannot be null");
                    return;
                }
                
                if(this.queryList.get(APIKeyIndex).isEmpty()) {
                    ses.addError("APIKey is mandatory and cannot be null");
                    return;
                }

                this.attacherId = util.generic.GetId.getAttacherId();
                Attacher.attachRequest(ses, this.attacherId);
                gateway.client.Client.Http.get(ses, this.attacherId, "def", "def", "/Products/v1/", this.queryList);
            }
            public api.neilsen.productreference.response.requestbydescription.RequestByDescription getResponse() {
                api.neilsen.productreference.response.requestbydescription.RequestByDescription rbd = 
                        new api.neilsen.productreference.response.requestbydescription.RequestByDescription();
                String res = null;
                try {
                    res = attach.Attacher.getResponse(ses, this.attacherId);
                } catch (InterruptedException e) {
                    ses.addError("InterruptedException: " + e);
                }
                try {
                    rbd = (api.neilsen.productreference.response.requestbydescription.RequestByDescription) 
                        util.parse.json.UnMarshall.begin(
                        ses,
                        rbd.getClass(), 
                        res);
                    return rbd;
                } catch (Exception e) {
                    ses.addError("Exception: " + e);
                    return rbd;
                }
            }
        }

    
    public static void main(String args[]) throws FileNotFoundException, IOException, JAXBException, InterruptedException {
        Session ses = new Session();
        ProductReference.Request.ByDescription prb = new ProductReference.Request.ByDescription(ses);
        
        prb.setProductDescription("FREEMAN ULTRA HAND");
        prb.fetchConcurrent();
        ProductReference.Request.ByDescription prb1 = new ProductReference.Request.ByDescription(ses);
        prb1.setProductDescription("FREEMAN ULTRA HAND");
        //prb1.fetchConcurrent();
        
        api.neilsen.productreference.response.requestbydescription.RequestByDescription rbd;
        try {
           rbd = prb.getResponse();
           System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
           System.out.println(rbd.Summary.PageNo);
           System.out.println(rbd.Summary.PageSize);
           System.out.println(rbd.Summary.TotalPages);
           System.out.println(rbd.Summary.TotalRecords);
           System.out.println(rbd.ProductDetails.iterator().next().Description);
           System.out.println(rbd.ProductDetails.iterator().next().Item_Code);
           System.out.println(rbd.ProductDetails.iterator().next().Rank);
        } catch (Exception e) {  
            ses.addError("Exxception: " + e);
            if(ses.hasError()) {
                System.out.println("sys in error");
            }
        }
        
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        api.neilsen.productreference.response.requestbydescription.RequestByDescription rbd1;
        try {
           rbd1 = prb1.getResponse();
           System.out.println(rbd1.Summary.PageNo);
           System.out.println(rbd1.Summary.PageSize);
           System.out.println(rbd1.Summary.TotalPages);
           System.out.println(rbd1.Summary.TotalRecords);
           System.out.println(rbd1.ProductDetails.iterator().next().Description);
           System.out.println(rbd1.ProductDetails.iterator().next().Item_Code);
           System.out.println(rbd1.ProductDetails.iterator().next().Rank);
           System.out.println(rbd1.ProductDetails.iterator().next().Description);
        } catch (Exception e) {  
            ses.addError("Exxception: " + e);
            System.out.println("sys in error");
        }
    }    
}
}