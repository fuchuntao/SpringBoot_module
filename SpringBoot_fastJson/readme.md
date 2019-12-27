### SpringBoot Jackson NULL的处理

https://www.jianshu.com/p/ebe9087b9078


```
spring:
  jackson:
    date-format: yyyy-MM-dd HH:mm:ss
    //可以忽略为NULL的属性
    default-property-inclusion: non_null
```

- Include.Include.ALWAYS 默认
- Include.NON_DEFAULT 属性为默认值不序列化
- Include.NON_EMPTY 属性为 空（""） 或者为 NULL 都不序列化，则返回的json是没有这个字段的。这样对移动端会更省流量
- Include.NON_NULL 属性为NULL 不序列化,就是为null的字段不参加序列化

或者

```
/**
 * Jackson 中对 null 的处理
 */
@Configuration
public class JacksonConfig {
    @Bean
    @Primary
    @ConditionalOnMissingBean(ObjectMapper.class)
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        
        //objectMapper.setSerializationInclusion(Include.NON_EMPTY);
 
        // 字段保留，将null值转为""
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
            @Override
            public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
                jsonGenerator.writeString("");
            }
        });
        return objectMapper;
    }
}
```

亦或者

```
@JsonInclude(JsonInclude.Include.NON_EMPTY)
```


### 使用fastJson替换默认的jackson

[SpringBoot使用fastJson替换默认的jackson](https://www.jianshu.com/p/24f3c84b79d3)
[SpringBoot中用Fastjson替换默认的Jackson](https://www.cnblogs.com/silentdoer/p/9007580.html)
[Spring Boot使用FastJson替换Jackson进行JSON解析](https://my.oschina.net/u/3826645/blog/1821775)

FastJson 对NULL的处理

```
/**
 * fastJson 中对 null 的处理
 */
@Configuration
public class fastJsonConfig extends WebMvcConfigurationSupport {
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(

                SerializerFeature.WriteMapNullValue,// 保留map空的字段

                SerializerFeature.WriteNullStringAsEmpty, // 将String类型的null转成""

                SerializerFeature.WriteNullNumberAsZero,// 将Number类型的null转成0

                SerializerFeature.WriteNullListAsEmpty,// 将List类型的null转成[]

                SerializerFeature.WriteNullBooleanAsFalse, // 将Boolean类型的null转成false

                SerializerFeature.DisableCircularReferenceDetect);// 避免循环引用

        converter.setFastJsonConfig(config);
        converter.setDefaultCharset(Charset.forName("UTF-8"));
        List<MediaType> mediaTypeList = new ArrayList<>();
        // 解决中文乱码问题，相当于在Controller上的@RequestMapping中加了个属性produces = "application/json"
        mediaTypeList.add(MediaType.APPLICATION_JSON);
        converter.setSupportedMediaTypes(mediaTypeList);
        converters.add(converter);
    }
}
```