package com.avin.api.controller;

import com.avin.api.service.ImageService;
import com.avin.config.AppConfig;
import com.github.benmanes.caffeine.cache.Cache;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.DeprecatedConfigurationProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;


@Controller
@RequestMapping("/img")
public class ImageController {
    @Autowired
    private AppConfig yaml;

    @Autowired
    private ImageService imageService;

    @GetMapping(value = "/hello", produces = MediaType.IMAGE_JPEG_VALUE)//, consumes="text/html"
    public @ResponseBody byte[] hello() throws IOException {
        URL url = new URL("https://pics.dmm.co.jp/digital/video/kavr00025/kavr00025jp-1.jpg");
        byte[] b = IOUtils.toByteArray(url);
        return b;
    }

    /**
     * 변경 예정
     * */
    @GetMapping(value = "/", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] get_Image(
            @RequestParam(value = "avcode", required = false) String avcode,
            HttpServletRequest request
    ) throws Exception {
        return imageService.getPreviewImage(avcode, request.getRequestURL());
    }

    @GetMapping(value = "/detail", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] get_ImageDetail(
            @RequestParam(value = "avcode", required = false) String avcode,
            HttpServletRequest request
    ) throws Exception {
        return imageService.getDetailImage(avcode);
    }

    @GetMapping(value = "/manga", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] get_Manga(
            @RequestParam(value = "avcode", required = false) String avcode,
            HttpServletRequest request
    ) throws Exception {
        return imageService.getManga();
    }
}
