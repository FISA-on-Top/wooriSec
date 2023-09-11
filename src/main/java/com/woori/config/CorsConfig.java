package com.woori.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class  CorsConfig implements WebMvcConfigurer {

	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .maxAge(3600);
    }
}
	
	
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")	// 어떤 URL 패턴에 CORS 설정을 적용할 것인지 지정
//                .allowedOrigins("http://localhost:8080")	// 허용할 도메인
//                .allowedMethods(
//                    	HttpMethod.GET.name(),
//                    	HttpMethod.HEAD.name(),
//                    	HttpMethod.POST.name(),
//                    	HttpMethod.PUT.name(),
//                    	HttpMethod.DELETE.name())	//allowedMethods() 메소드를 통해 GET, POST와 같은 HTTP 메소드의 종류도 제한 가능
//        		.allowCredentials(true); // 쿠키를 사용할 수 있도록 설정
//    }
//}

