package api.neilsen.productreference.response.requestbydescription;

import java.util.List;
        
import org.codehaus.jackson.annotate.JsonProperty;
 
public class RequestByDescription {
	@JsonProperty("Header") public Header Header;
	@JsonProperty("Summary") public Summary Summary;
	@JsonProperty("ProductDetails") public List<ProductDetails> ProductDetails;
}