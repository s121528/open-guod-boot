package cn.hacz.edu.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author guodd
 * @since 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "elasticsearch")
public class EsProperties {
    private String esHost;

    private int esPort;

    private String esName;

    private String schema;
}
