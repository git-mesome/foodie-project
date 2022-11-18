package io.wisoft.foodie.project.domain.chat.web.dto.res;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record FindChatMessageDetailResponse(Long chatRoomId,
                                            String senderProfileImagePath,
                                            String senderNickname,
                                            String message,
                                            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
                                            LocalDateTime createDate) {
}
