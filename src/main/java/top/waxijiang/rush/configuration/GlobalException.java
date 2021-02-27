package top.waxijiang.rush.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.Properties;

/**
 * @author waxijiang
 */
@Configuration
public class GlobalException {
    @Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver(){
        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();

        Properties mappings = new Properties();

        mappings.put("org.apache.shiro.authz.UnauthorizedException", "refuse");

        resolver.setExceptionMappings(mappings);
        return resolver;
    }

}
