package pl.ice.fissst.demo.servicing.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;


@Data
public class AddServiceActionDTO {

    @Min(value = 1, message = "Id value must be greater than 1.")
    @NotNull(message = "Part id is missing.")
    @ApiModelProperty(notes = "Id of the part.")
    private long partId;

    @NotNull
    @NotEmpty(message = "Service name is missing.")
    @ApiModelProperty(notes = "Name of the servicing action.")
    private String name;

    @NotNull(message = "Service start date is missing.")
    @ApiModelProperty(notes = "Date when servicing started.")
    private Date servicedFrom;

    @ApiModelProperty(notes = "Date when servicing ended.")
    private Date servicedUntil;
}
