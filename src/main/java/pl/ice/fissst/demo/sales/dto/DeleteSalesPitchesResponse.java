package pl.ice.fissst.demo.sales.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DeleteSalesPitchesResponse {

    @ApiModelProperty(notes = "Id of the part with cleared sales pitch.")
    private long partId;

    @ApiModelProperty(notes = "Result of the operation.")
    private boolean success;

    public DeleteSalesPitchesResponse(){}

    public DeleteSalesPitchesResponse(long partId, boolean success){
        this.partId = partId;
        this.success = success;
    }
}
