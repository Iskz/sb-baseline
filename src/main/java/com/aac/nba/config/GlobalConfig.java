package com.aac.nba.config;

import com.aac.nba.Constants;
import com.aac.nba.converter.LocalDateConverter;
import com.aac.nba.converter.LocalDateTimeConverter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.core.env.Environment;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Configuration
@EnableTransactionManagement
public class GlobalConfig {

  @Autowired
  Environment env;

  /**
   * Primary Datasource configuration
   * @return the datasource
   */
  @Bean(name = "dataSource")
  @Primary
  public DataSource dataSource() {
    final DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
    dataSource.setUrl(env.getProperty("spring.datasource.url"));
    dataSource.setUsername(env.getProperty("spring.datasource.username"));
    dataSource.setPassword(env.getProperty("spring.datasource.password"));
    dataSource.setSchema(env.getProperty("spring.datasource.db-schema"));

    return dataSource;
  }

  /**
   * Global password encoder - BCrypt. Since the NoOp Encoder is deprecated
   * @return the passwordEncoder
   */
  @Bean(name = "passwordEncoder")
  public PasswordEncoder passwordEncoder() {
    Map encoders = new HashMap<>();
    // TODO: uncomment this to enable BCrypt Password Encoder.
    // encoders.put("bcrypt", new BCryptPasswordEncoder());
    // return new DelegatingPasswordEncoder("noop", encoders);

    encoders.put("noop", NoOpPasswordEncoder.getInstance());
    return NoOpPasswordEncoder.getInstance();
  }

  /**
   * Global object mapper for instantiating jackson binding
   * @return - custom object mapper
   */
  @Bean(name = "objectMapper")
  public ObjectMapper objectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    return objectMapper;
  }

  @Bean(name = "gson")
  public Gson gson() {
    return new GsonBuilder()
            .setDateFormat(Constants.DATE_FORMAT)
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDate.class, new LocalDateConverter())
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeConverter())
            .create();
  }

  @Bean(name = "restTemplate")
  public RestTemplate restTemplate() {
    return new RestTemplateBuilder()
        .requestFactory(() ->
            new BufferingClientHttpRequestFactory(
                new SimpleClientHttpRequestFactory())
            )
//        .interceptors(new ClientRequestLoggingInterceptor(new LogService()))
        .messageConverters(getMessageConverters())
        .build();
  }

  /**
   * Override existing message converters
   * @return - List of message converters
   */
  private List<HttpMessageConverter<?>> getMessageConverters() {
    List<HttpMessageConverter<?>> messageConverterList = new ArrayList<>();
    StringHttpMessageConverter stringHttpMessageConverter =
        new StringHttpMessageConverter(Charset.forName(StandardCharsets.UTF_8.name()));
    stringHttpMessageConverter.setWriteAcceptCharset(false);
    messageConverterList.add(stringHttpMessageConverter);
    messageConverterList.add(new ByteArrayHttpMessageConverter());
    messageConverterList.add(new ResourceHttpMessageConverter(false));
    messageConverterList.add(new SourceHttpMessageConverter());
    messageConverterList.add(new AllEncompassingFormHttpMessageConverter());
    messageConverterList.add(new Jaxb2RootElementHttpMessageConverter());
    messageConverterList.add(new GsonHttpMessageConverter());
    // Replaced with Gson Message Converter
    // messageConverterList.add(new MappingJackson2HttpMessageConverter());
    return messageConverterList;
  }

  @Bean
  public GenericConversionService lobConversionService() {
    GenericConversionService converter = new GenericConversionService();
    converter.addConverter(Object.class,
                           byte[].class,
                           new SerializingConverter());
    converter.addConverter(byte[].class,
                           Object.class,
                           new DeserializingConverter());
    return converter;
  }
}
