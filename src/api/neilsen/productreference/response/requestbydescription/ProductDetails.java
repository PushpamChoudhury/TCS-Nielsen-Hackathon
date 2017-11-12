package api.neilsen.productreference.response.requestbydescription;

import org.codehaus.jackson.annotate.JsonProperty;

public class ProductDetails {
	@JsonProperty("UPC") public Long UPC;
	@JsonProperty("Description") public String Description;
	@JsonProperty("Item_Code") public Integer Item_Code;
	@JsonProperty("Module") public String Module;
	@JsonProperty("Rank") public Integer Rank;
}
