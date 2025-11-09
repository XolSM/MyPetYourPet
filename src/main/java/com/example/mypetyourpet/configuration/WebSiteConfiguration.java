package com.example.mypetyourpet.configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebSiteConfiguration implements WebMvcConfigurer {
//    @Value("${app.upload.dir}")
//    private String uploadDir;
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/uploads/**")
//                .addResourceLocations("file:"  + uploadDir +"/");
//    }

    @Value("${cloudinary.cloud-name}")
    private String cloudName;

    @Value("${cloudinary.api-key}")
    private String apiKey;

    @Value("${cloudinary.api-secret}")
    private String apiSecret;

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret));

    }





//    @Configuration
//    public class CloudinaryConfig {
//
//        @Value("${cloudinary.cloud-name}")
//        private String cloudName;
//
//        @Value("${cloudinary.api-key}")
//        private String apiKey;
//
//        @Value("${cloudinary.api-secret}")
//        private String apiSecret;
//
//        @Bean
//        public Cloudinary cloudinary() {
//            return new Cloudinary(ObjectUtils.asMap(
//                    "cloud_name", cloudName,
//                    "api_key", apiKey,
//                    "api_secret", apiSecret));
//
//        }
//
//
//    }

}
