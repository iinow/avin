package com.segeg.avin.parser;

import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

@Component
public class DMMSite extends ProductSite {

    @Override
    public Element getList(Element body) {
        Element list = body.getElementById("list");
        return list;
    }

    public Element getSampleList(Element body){
        Element list = body.getElementById("sample-image-block");
        return list;
    }
}
