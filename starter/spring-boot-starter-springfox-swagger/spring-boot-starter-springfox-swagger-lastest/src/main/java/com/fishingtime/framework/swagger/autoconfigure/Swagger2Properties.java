package com.fishingtime.framework.swagger.autoconfigure;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description:
 *
 * @return
 * @author
 * @date 2019/2/25 16:52
 */
@ConditionalOnWebApplication
@ConfigurationProperties(prefix="swagger2")
@Getter
@Setter
public class Swagger2Properties {
    private Boolean enable =false;
}
