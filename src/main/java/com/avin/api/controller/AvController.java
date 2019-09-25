package com.avin.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avin.config.AppConfig;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

@RestController
@RequestMapping("/v1/av")
public class AvController {
    @Autowired
    public AppConfig model;

    @GetMapping(value = "/hello", produces = MediaType.IMAGE_JPEG_VALUE)
    public BufferedImage hello() throws IOException {
        URL url = new URL("https://pics.dmm.co.jp/digital/video/kavr00025/kavr00025jp-1.jpg");
        BufferedImage image = ImageIO.read(url);

        return image;
    }
}
