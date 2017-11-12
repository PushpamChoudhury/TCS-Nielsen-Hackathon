package api.neilsen.productreference.response.requestbyupc;

import org.codehaus.jackson.annotate.JsonProperty;
import java.util.List;

public class ReuestByUPC {
	@JsonProperty("Header") public Header Header;
	@JsonProperty("Summary") public Summary Summary;
	@JsonProperty("ProductDetails") public List<ProductDetails> ProductDetails;
}
