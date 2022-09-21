package io.wisoft.foodie.project.global.web.validator.image;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ImageContentTypeValidator implements ConstraintValidator<Image, MultipartFile> {

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext contest) {
        return isImageFile(value);
    }

    private boolean isImageFile(final MultipartFile multipartFile) {

        final String contentType = multipartFile.getContentType();

        if (contentType == null) {
            throw new IllegalArgumentException("파일 형식이 맞지 않습니다.");
        }

        return contentType.contains("image");

    }

}
