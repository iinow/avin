package com.avin.common.parser;

import org.jsoup.nodes.Element;

public abstract class ProductSite {
    abstract Element getList(Element body);
}
