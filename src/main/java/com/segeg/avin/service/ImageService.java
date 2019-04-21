package com.segeg.avin.service;

import com.segeg.avin.model.AppYaml;
import com.segeg.avin.parser.DMMSite;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.net.URLEncoder;

@Component
public class ImageService {

    @Autowired
    private DMMSite dmmSite;

    @Autowired
    private AppYaml app;

    public String getAvcode(String avcode){
        //- 특수문자가 있을경우 잘라준다 공백으로 대체
        if(avcode.contains("-")){
            return avcode.replaceAll("-", " ");
        }else{
            //EDD-202
            //EDD202
            //EDD 202

            return avcode;
        }
    }

    public String getHtml(String url) throws Exception {
        Element body = Jsoup.parse(new URL(url), 5000).body();
        Element list = body.getElementById("list");
        return list.toString();
    }

    public byte[] getImage(String avcode) throws Exception {
        Element element = dmmSite.getList(Jsoup.parse(new URL(String.format(app.getUrl(), URLEncoder.encode(avcode, "UTF-8"))), 5000).body());
        Elements elements = element.select("li div p a span img");
        for(Element e : elements){
            String path = e.attr("src");
//            if(path.contains())
            System.out.println(path.substring(2));
            return IOUtils.toByteArray(new URL("https://"+path.substring(2)));
        }
//        return IOUtils.toByteArray(u);
        return null;
    }
}
