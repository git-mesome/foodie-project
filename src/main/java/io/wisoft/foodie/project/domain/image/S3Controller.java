package io.wisoft.foodie.project.domain.image;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("/images")
@RequiredArgsConstructor
@RestController
public class S3Controller {

    private final S3Util s3Util;

    @PostMapping("/upload/post")
    public ResponseEntity<PostUploadResponse> uploadPostImage(@RequestParam("image") MultipartFile multipartFile) throws IOException {

        try {
            s3Util.uploadFile(multipartFile, "post");
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }

    @PostMapping("/upload/posts")
    public ResponseEntity<PostUploadResponse> uploadPostImageList(@RequestParam("image")List<MultipartFile> multipartFiles) throws IOException{

        List<String> imgPaths = s3Util.uploadFileList(multipartFiles,"post");
        System.out.println("IMG 경로들 : " + imgPaths);
//        postService.uploadPost(postRequestDto, imgPaths);

        return new ResponseEntity<>(HttpStatus.OK);

    }

}
