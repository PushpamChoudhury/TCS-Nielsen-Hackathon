package api.neilsen.productreference.response.requestbyupc;

import org.codehaus.jackson.annotate.JsonProperty;

public class ProductDetails {
	@JsonProperty("Description") public String Description;
	@JsonProperty("Item_Code") public Integer Item_Code;
	@JsonProperty("Module") public String Module;
	@JsonProperty("UPC") public Long UPC;
}
