package com.segeg.avin.service;

import com.segeg.avin.model.AppYaml;
import com.segeg.avin.parser.DMMSite;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
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
        String path = getUrl(avcode);
        return IOUtils.toByteArray(new URL("https://"+path));
    }

    public byte[] getDetailImage(String avcode) throws Exception {
        String url = getMoreUrl(avcode);
        Element element = dmmSite.getSampleList(Jsoup.parse(new URL(url), 5000).body());
        Elements elements = element.select("a img");
        for(Element e: elements){
            String u = e.attr("src");
            int i = u.lastIndexOf("-");
            return IOUtils.toByteArray(new URL(u.replace(u.substring(i), "jp"+u.substring(i))));
        }
        return null;
    }

    public @NotNull String getUrl(String avcode) throws Exception {
        Element element = dmmSite.getList(Jsoup.parse(new URL(String.format(app.getUrl(), URLEncoder.encode(avcode, "UTF-8"))), 5000).body());
        Elements elements = element.select("li div p a span img");
        for(Element e : elements){
            String path = e.attr("src");
//            if(path.contains())
            System.out.println(path.substring(2));
            return path.substring(2);
        }
        return null;
    }

    public @NotNull String getMoreUrl(String avcode) throws Exception {
        String pa = String.format(app.getUrl(), URLEncoder.encode(avcode, "UTF-8").replaceAll("\\+", "%20"));
        URL url = new URL(pa);
        Element element = dmmSite.getList(Jsoup.parse(url, 5000).body());
        Elements elements = element.select("li div p a");
        for(Element e: elements) {
            String path = e.attr("href");
            return path;
        }
        return null;
    }

    public byte[] getManga() throws Exception {
        System.out.println(app.getManga().getUrl());
        //https://pics.dmm.co.jp/digital/video/kavr00025/kavr00025-7.jpg
        //https://pics.dmm.co.jp/digital/video/kavr00025/kavr00025jp-7.jpg
        return new byte[2];
    }
}
