package com.segeg.avin.model;

import org.hibernate.validator.constraints.Length;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "app")
public class AppYaml extends BaseModel {

    /*@NotBlank
    private String hostName;
    @Length(max = 4, min = 1)
    private String authMethod;
    @Min(1025)
    @Max(65536)
    private int port;
    @Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$")
    private String from;*/

    private String url;
    private Manga manga;

    public AppYaml() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Manga getManga(){
        return manga;
    }

    public void setManga(Manga manga){
        this.manga = manga;
    }

    public static class Manga extends BaseModel {
        private String url;

        public String getUrl(){
            return url;
        }

        public void setUrl(String url){
            this.url = url;
        }
    }
}
