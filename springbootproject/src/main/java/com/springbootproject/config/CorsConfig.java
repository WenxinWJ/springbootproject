package com.springbootproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 跨域配置类
 */
@Configuration
public class CorsConfig {
  private static String[] originsVal = new String[]{
      "127.0.0.1:8080",     // 要跨域的ip
      "localhost:8080",
      //"127.0.0.1:8086",
      //"localhost:8086",
      "google.com",
      "gmail.google.com",
      "photo.google.com",
      "keep.google.com"
  };

  @Bean
  public CorsFilter corsFilter() {  // 跨域的过滤器
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration corsConfiguration = new CorsConfiguration();
    this.addAllowedOrigins(corsConfiguration);
    corsConfiguration.addAllowedHeader("*");    // 允所许有的头
    corsConfiguration.addAllowedMethod("*");    // 允所许有的方法
    corsConfiguration.addAllowedOrigin("*");    // 允许所有的源
    source.registerCorsConfiguration("/**", corsConfiguration); // 所有的路径
    return new CorsFilter(source);
  }

  private void addAllowedOrigins(CorsConfiguration corsConfiguration) {
    for (String origin : originsVal) {  // 配置信息
      corsConfiguration.addAllowedOrigin("http://" + origin);
      corsConfiguration.addAllowedOrigin("https://" + origin);
    }
  }
}
