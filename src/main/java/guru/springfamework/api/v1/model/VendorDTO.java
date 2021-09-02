package guru.springfamework.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorDTO {
    @ApiModelProperty(value = "This is a name of vendor")
    private String name;

    @ApiModelProperty(value = "Path url for vendor")
    private String vendorUrl;
}
