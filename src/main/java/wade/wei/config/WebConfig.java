package wade.wei.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import wade.wei.Interceptor.HttpInterceptor;
import wade.wei.Interceptor.AuthInterceptor;

/**
 * @author Administrator
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public AuthInterceptor authInterceptor() {
        return new AuthInterceptor();
    }

    @Bean
    public HttpInterceptor httpInterceptor() {
        return new HttpInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(httpInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/static/**");

        registry.addInterceptor(authInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/static/**");
    }
}
