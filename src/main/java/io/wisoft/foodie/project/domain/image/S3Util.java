package io.wisoft.foodie.project.domain.image;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
@Service
public class S3Util {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    public String bucket;

    public List<String> uploadFileList(List<MultipartFile> multipartFiles, String dirName) throws IOException {

        //Todo 파일 업로드 개수 지정

        List<String> imageUrlList = new ArrayList<>();

        for (MultipartFile file : multipartFiles) {
            imageUrlList.add(uploadFile(file, dirName));
        }

        return imageUrlList;

    }

    public String uploadFile(MultipartFile file, String dirName) throws IOException {


        String fileName = createFileName(StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename())), dirName);

        ObjectMetadata objectMetadata = new ObjectMetadata();

        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());

        try (InputStream inputStream = file.getInputStream()) {

            return putS3(inputStream, fileName, objectMetadata);

        } catch (IOException e) {
            throw new IllegalArgumentException("이미지 업로드 에러");
        }


    }


    private String putS3(InputStream inputStream, String fileName, ObjectMetadata objectMetadata) {

        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
                .withCannedAcl(CannedAccessControlList.PublicRead));

        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    private String createFileName(String fileName, String dirName) {
        return dirName + "/" + UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }





    public void deleteFile(String fileName) {
        DeleteObjectRequest request = new DeleteObjectRequest(bucket, fileName);
        amazonS3Client.deleteObject(request);
    }


//    private void removeNewFile(File targetFile) {
//
//        if (targetFile.delete()) {
//            log.info("File delete success");
//            return;
//        }
//        log.info("File delete fail");
//
//    }

    private String getFileExtension(String fileName) {

        ArrayList<String> fileValidate = new ArrayList<>();

        fileValidate.add(".jpg");
        fileValidate.add(".jpeg");
        fileValidate.add(".png");
        fileValidate.add(".JPG");
        fileValidate.add(".JPEG");
        fileValidate.add(".PNG");

        String idxFileName = fileName.substring(fileName.lastIndexOf("."));

        if (!fileValidate.contains(idxFileName)) {
            throw new IllegalArgumentException("이미지 파일 형식이 아닙니다.");
        }

        return fileName.substring(fileName.lastIndexOf("."));
    }

}
