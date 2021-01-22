package au.com.woolworths.model.scango.login;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class DeviceInfo {
    private String os;
    private String appversion;
    private String osversion;
    private String deviceid;
    private String devicemake;
}
