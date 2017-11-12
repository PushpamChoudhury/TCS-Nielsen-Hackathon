package api.neilsen.productreference.response.requestforcharacteristics;

import org.codehaus.jackson.annotate.JsonProperty;
import java.util.List;

public class ReuestForCharacteristics {
	@JsonProperty("Header") public Header Header;
	@JsonProperty("Characteristics") public List<Characteristics> Characteristics;
}
