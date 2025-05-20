# 💬 Help Chat Server (WebFlux 기반 실시간 채팅 백엔드)

Spring WebFlux를 이용한 논블로킹 실시간 채팅 서버입니다.  
Next.js 기반 프론트엔드와 연동되며, MongoDB를 통해 채팅 데이터를 저장합니다.


## 🧰 사용 기술 스택

- **Java 21**
- **Spring Boot 3.4.5 (WebFlux)**
- **Reactive MongoDB**
- **JWT 기반 인증**
- **Project Reactor**
- **SSE (Server-Sent Events)** for 실시간 메시징
- **DevContainer 기반 개발 환경 (Docker + IntelliJ ,VSCode)**

  📁 `.devcontainer/`  
┣ 📄 .env  
┣ 📄 docker-compose.yml  
┣ 📄 devcontainer.json  
┣ 📄 Dockerfile  

## ⚙️ 주요 기능

- ✅ 회원가입 / 로그인 (JWT 발급)
- ✅ 채팅방 생성 및 입장
- ✅ 실시간 채팅 (SSE)
- ✅ 채팅 로그 저장 (MongoDB)
- ✅ 인증된 유저만 접근 가능


## 🔐 API 테스트용 계정

* User : test@naver.com  | PWD :	test
* Admin : admin@naver.com | PWD : test

## 🌐 프론트엔드 연동
Next.js 기반 UI 👉 [help-chat-ui](https://github.com/Yseek/help-chat-ui)

## 🧑‍💻 개발 목적
"WebFlux의 논블로킹 특성을 활용한 실시간 채팅 서버를 직접 설계하고,
인증/저장소/스트리밍 구조까지 풀스택으로 구현하는 것이 목표였습니다."

## 📸 시연 화면

### 메인 페이지  
<img src="https://github.com/user-attachments/assets/340e40c3-d349-497f-98c8-a5436257c6f2" width="500"/>

### 로그인 페이지  
<img src="https://github.com/user-attachments/assets/6cad1684-b0d2-4e11-add1-0d34aed96238" width="500"/>

### 로그인 후 
#### USER
<div style="display: flex; gap: 10px; align-items: center;">
  <img src="https://github.com/user-attachments/assets/4d19103e-dde6-48f3-9f8a-981ce11243e9" height="350" style="object-fit: cover;"/>
  <img src="https://github.com/user-attachments/assets/7085f35f-23ec-463a-9405-9e0bbf295ca0" height="350" style="object-fit: cover;"/>
</div>

#### ADMIN
<div style="display: flex; gap: 10px; align-items: center;">
  <img src="https://github.com/user-attachments/assets/e6848aa0-36d4-412f-acd1-f952f627db1a" width="500" style="object-fit: cover;"/>
  <img src="https://github.com/user-attachments/assets/f16a8704-94f7-48be-847d-3900a48e5687" width="500" style="object-fit: cover;"/>
</div>

### 영상
![채팅녹화](https://github.com/user-attachments/assets/738d90d4-0fa9-4e52-b3fd-f59ab636f5dd)
