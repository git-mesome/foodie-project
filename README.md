# Foodi:e Web Application
![배너](https://user-images.githubusercontent.com/68283967/217172022-63089ca6-e7b5-4ffc-9122-0e44f8dc5eb9.png)

프로세스,설정 가이드 등 엔지니어링의 모든 것을 확인하세요!
- [\[Foodi:e 홈페이지 둘러보기\]](https://foodie.wisoft.io)
- [\[Foodi:e Wiki\]](https://www.notion.so/Foodi-e-ce8f763e98794ca9a78150d237134404)
- [\[Foodi:e-uiux Repository\]](https://github.com/Minseo-dev/foodie-uiux)

## 🛠️ Stacks
![SpringBoot](https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white)
![SpringBoot](https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=SpringBoot&logoColor=white)
![AmazonS3](https://img.shields.io/badge/Aws-232f3e?style=for-the-badge&logo=amazonAWS&logoColor=white)
![AmazonS3](https://img.shields.io/badge/Aws_Ec2-FF9900?style=for-the-badge&logo=AmazonEC2&logoColor=white)
![AmazonS3](https://img.shields.io/badge/Aws_RDS-527FFF?style=for-the-badge&logo=AmazonRDS&logoColor=white)
![AmazonS3](https://img.shields.io/badge/Aws_S3-232F3E?style=for-the-badge&logo=AmazonS3&logoColor=white)

> Main Page

![main](https://user-images.githubusercontent.com/68283967/217190720-8ac3ee03-9ccb-4841-aa79-d8f5ec767594.png)
> Login Page

![image02](https://user-images.githubusercontent.com/68283967/217191007-499db848-33af-4663-9ee0-7277f31275e7.png)

> Chat Page

![image](https://user-images.githubusercontent.com/68283967/217191309-0e459a26-f89c-4f1f-b3ba-013abb1bf541.png)
> Board Page

![image](https://user-images.githubusercontent.com/68283967/217191214-edbbaac2-261b-4b56-ae37-16f59b73c98c.png)
## User Service
![UserService](https://user-images.githubusercontent.com/68283967/217183897-70c018df-5ee1-45f7-9586-f4747fd592c6.png)

## Tech Flow
![TechFlow](https://user-images.githubusercontent.com/68283967/217184212-c5afe005-3d75-4a42-8f92-d7fc2a702668.png)
### Backend Server
- Spring Data JPA를 통해 데이터베이스에 저장된 데이터와 매핑
- JWT 인증 방식을 사용한 로그인
- Amazon EC2, RDS를 통해 비용의 효율성을 높이고 보안 및 관리 용이성 증대
- Amazon S3를 통한 이미지 저장
- 채팅 : Websocket, Stomp
  - 세션을 직접 관리하지 않아 간편한 메세지 처리 방식


### Frontend Server
- OAuth2를 이용한 소셜 로그인 구현
- 동일 상태 공유를 위한 Vuex Store
- vue-router 라이브러리를 사용하여 기능별 필요한 정보 렌더링
- 컴포넌트 요소를 외부 배경 요소와 내부 내용 요소로 분리하여 재사용성 극대화