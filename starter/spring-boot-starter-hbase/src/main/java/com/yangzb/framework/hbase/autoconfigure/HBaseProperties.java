package com.yangzb.framework.hbase.autoconfigure;

import com.yangzb.framework.hbase.configure.HbaseConfiguration;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
/**
 * @Description:
 *
 * @return
 * @author yzb
 * @date 2019/2/25 16:52
 */
@ConfigurationProperties(prefix="spring.data.hbase")
@Getter
@Setter
public class HBaseProperties extends HbaseConfiguration {
}
