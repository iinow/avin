package com.segeg.avin.controller;

import com.segeg.avin.model.AppYaml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public AppYaml model;

    @GetMapping("/hello")
    public BufferedImage hello() throws IOException {
        URL url = new URL("https://pics.dmm.co.jp/digital/video/kavr00025/kavr00025jp-1.jpg");
        BufferedImage image = ImageIO.read(url);

        return image;
    }
}
