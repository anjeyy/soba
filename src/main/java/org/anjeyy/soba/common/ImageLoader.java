package org.anjeyy.soba.common;

import java.io.InputStream;
import java.util.Objects;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageLoader {

    private ImageLoader() {
        throw new UnsupportedOperationException("No instance allowed.");
    }

    public static ImageView asImageView(String resourcePath) {
        Image image = asImage(resourcePath);
        return new ImageView(image);
    }

    public static Image asImage(String resourcePath) {
        InputStream inputStream = asInputStream(resourcePath);
        return new Image(inputStream);
    }

    public static InputStream asInputStream(String resourcePath) {
        return Objects.requireNonNull(
            ImageLoader.class.getResourceAsStream(resourcePath),
            "Could not load image."
        );
    }
}

