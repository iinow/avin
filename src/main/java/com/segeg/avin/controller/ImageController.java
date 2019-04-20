package com.segeg.avin.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

@Controller
@RequestMapping("/img")
public class ImageController {
    @GetMapping(value = "/hello", produces = MediaType.IMAGE_JPEG_VALUE)//, consumes="text/html"
    public @ResponseBody
    byte[] hello() throws IOException {
        URL url = new URL("https://pics.dmm.co.jp/digital/video/kavr00025/kavr00025jp-1.jpg");
//        BufferedImage image = ImageIO.read(url);

        byte[] b = IOUtils.toByteArray(url);
        return b;
    }
}
