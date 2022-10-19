package io.wisoft.foodie.project.domain.image;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Slf4j
@Service
public class S3Util {

    private final AmazonS3Client amazonS3Client;

    @Autowired
    public S3Util(final AmazonS3Client amazonS3Client) {
        this.amazonS3Client = amazonS3Client;
    }

    @Value("${cloud.aws.s3.bucket}")
    public String bucket;

    public List<String> uploadFileList(final List<MultipartFile> multipartFiles,
                                       final String dirName) throws IOException {

        //Todo 파일 업로드 개수 지정

        List<String> imageUrlList = new ArrayList<>();

        for (MultipartFile file : multipartFiles) {
            imageUrlList.add(uploadFile(file, dirName));
        }

        return imageUrlList;

    }

    public String uploadFile(final MultipartFile file,
                             final String dirName) throws IOException {

        final String fileName = createFileName(StringUtils
                .cleanPath(Objects.requireNonNull(file.getOriginalFilename())), dirName);

        ObjectMetadata objectMetadata = new ObjectMetadata();

        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());

        try (InputStream inputStream = file.getInputStream()) {

            return putS3(inputStream, fileName, objectMetadata);

        } catch (IOException e) {
            throw new IllegalArgumentException("이미지 업로드 에러");
        }


    }


    private String putS3(final InputStream inputStream,
                         final String fileName,
                         final ObjectMetadata objectMetadata) {

        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
                .withCannedAcl(CannedAccessControlList.PublicRead));

        return amazonS3Client.getUrl(bucket, fileName).toString();

    }

    private String createFileName(final String fileName, final String dirName) {
        return dirName + "/" + UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }

    public void deleteFileList(List<String> fileNameList){

        for(String fileName : fileNameList){
            deleteFile(fileName);
        }

    }

    private void deleteFile(final String fileName) {
        try {
            final DeleteObjectRequest request = new DeleteObjectRequest(this.bucket, fileName);
            amazonS3Client.deleteObject(request);
        }catch (AmazonServiceException e){
            System.out.println(e.getMessage());
        }

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

    private String getFileExtension(final String fileName) {

        final ArrayList<String> fileValidate = new ArrayList<>();

        fileValidate.add(".jpg");
        fileValidate.add(".jpeg");
        fileValidate.add(".png");
        fileValidate.add(".JPG");
        fileValidate.add(".JPEG");
        fileValidate.add(".PNG");

        final String idxFileName = fileName.substring(fileName.lastIndexOf("."));

        if (!fileValidate.contains(idxFileName)) {
            throw new IllegalArgumentException("이미지 파일 형식이 아닙니다.");
        }

        return fileName.substring(fileName.lastIndexOf("."));
    }

}
