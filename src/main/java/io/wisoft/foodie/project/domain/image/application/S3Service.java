package io.wisoft.foodie.project.domain.image.application;

import io.wisoft.foodie.project.domain.auth.exception.ImageException;
import io.wisoft.foodie.project.domain.auth.web.ErrorCode;
import io.wisoft.foodie.project.domain.image.S3Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class S3Service {

    private final S3Util s3Util;

    @Autowired
    public S3Service(final S3Util s3Util) {
        this.s3Util = s3Util;
    }

    public List<String> uploadFileList(final Optional<List<MultipartFile>> multipartFiles) throws IOException {
        return s3Util.uploadFileList(multipartFiles.orElse(Collections.emptyList()), "post");
    }

    public void deleteFileList(final List<String> fileNameList){
        s3Util.deleteFileList(fileNameList.stream()
                .map((item) -> {
                    try {
                        return new URL(item).getFile().substring(1);
                    } catch (MalformedURLException e) {
                        throw new ImageException(ErrorCode.INVALID_URL_FORMAT);
                    }
                }).toList());
    }


}
