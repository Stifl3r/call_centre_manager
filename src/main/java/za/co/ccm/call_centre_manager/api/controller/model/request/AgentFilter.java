package za.co.ccm.call_centre_manager.api.controller.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("AgentFilter")
@Getter
@Setter
public class AgentFilter {

    @ApiModelProperty(value = "Page index (starting point)", required = true , example = "0")
    private Integer pageIndex;
    @ApiModelProperty(value = "Page size(elements in the results)", required = true, example = "10")
    private Integer pageSize;
}
