# Clinical Triage - Backend Repository

Frontend Repository: [Frontend](https://github.com/weerayosong/clinical-triage-frontend)  
Live Preview: [Demo](https://clinical-triage-frontend.vercel.app/)

โปรเจกต์ต้นแบบแอปพลิเคชัน (Concept Application) สำหรับระบบจัดการคิวและคัดกรองผู้ป่วยเบื้องต้น ที่ได้รับการออกแบบโดยให้ความสำคัญกับความยืดหยุ่นในการแสดงผล (Responsive Design) และโครงสร้างระบบสถาปัตยกรรมระดับองค์กร (Enterprise Architecture)

![clinical-triage-img](https://github.com/weerayosong/weerayosong.github.io/blob/main/images/gif/proj5b.gif?raw=true)

## วัตถุประสงค์ของโปรเจกต์ (Project Objective)

แอปพลิเคชันนี้ถูกพัฒนาขึ้นเพื่อเป็นต้นแบบแนวคิด (Proof of Concept) ในการศึกษาและประยุกต์ใช้งานเทคโนโลยีระดับองค์กร โดยเฉพาะการใช้ Java Spring Bootโครงสร้างของระบบได้รับการพัฒนาผ่านแนวทางการเรียนรู้แบบปฏิบัติจริง (Top-down Approach) เพื่อทำความเข้าใจสถาปัตยกรรมฝั่งเซิร์ฟเวอร์ การทำงานของตรรกะทางธุรกิจ (Business Logic) การตรวจสอบความถูกต้องของข้อมูล (Validation) และการจัดการการเปลี่ยนสถานะของระบบ (State Transition) ให้มีประสิทธิภาพและรัดกุม

## การออกแบบ & สถาปัตยกรรมระบบ (Architecture & System Design)

[Architecture & System Design Documentation](https://admirable-malasada-3580eb.netlify.app)

## สถาปัตยกรรมระบบฝั่งเซิร์ฟเวอร์ (Backend Architecture)

ระบบปฏิบัติการบนพื้นฐานของ **Client-Server Architecture** โดยแอปพลิเคชันฝั่งหลังบ้าน (Server) ทำหน้าที่เป็นหัวใจหลักในการประมวลผลและเตรียม RESTful APIs ที่ปลอดภัย

การออกแบบซอฟต์แวร์จัดทำในรูปแบบ **N-Tier Architecture** (Separation of Concerns) เพื่อให้โค้ดดูแลรักษาง่ายและพร้อมรองรับการขยายตัวในอนาคต:

- **Controller Layer:** ด่านหน้าสำหรับรับ HTTP Requests, อ่านค่าพารามิเตอร์, และส่ง Response กลับพร้อม HTTP Status Code ที่เหมาะสม
- **Service Layer:** ชั้นที่ควบคุมตรรกะของแอปพลิเคชัน (Business Logic) ครอบคลุมถึงกฎและเงื่อนไขการย้ายสถานะคิวผู้ป่วย
- **Repository Layer (Data Access):** รับผิดชอบการสื่อสารกับฐานข้อมูล โดยอาศัย Spring Data JPA (Hibernate) เป็นกลไกในการทำ ORM (Object-Relational Mapping) เพื่อลดความซับซ้อนของการเขียนชุดคำสั่ง SQL
- **Entity Model:** คลาสโครงสร้างภาษา Java ที่เป็นตัวแทน (POJOs) ของตารางข้อมูลในฐานข้อมูล

---

## เครื่องมือและเทคโนโลยี (Technology Stack)

**Core System & Frameworks:**

- Java (JDK 21)
- Spring Boot 3.x
- Spring Web (RESTful APIs)

**Database & Persistence:**

- PostgreSQL
- PostgreSQL Driver
- Spring Data JPA

**Infrastructure & Version Control:**

- Maven (Dependency Management)

---

## การตั้งค่าและการติดตั้ง (Setup & Deployment)

แอปพลิเคชันฝั่งเซิร์ฟเวอร์นี้ได้รับการปรับแต่งโครงสร้างเพื่อรองรับการ Deployment บนคลาวด์แพลตฟอร์มอย่าง **Railway** และใช้ฐานข้อมูล **PostgreSQL จาก Aiven** โดยมีแนวทางปฏิบัติที่ต้องเตรียมการดังนี้:

### 1. การบริหารจัดการตัวแปรสภาพแวดล้อม (Environment Variables)

เพื่อปฏิบัติตามมาตรฐานความปลอดภัย ข้อมูลประจำตัวสำหรับการเชื่อมต่อฐานข้อมูลทั้งหมด กำหนดผ่านระบบ Environment Variables:

- ตั้งค่า `spring.datasource.url=${DB_URL}`
- ตั้งค่า `spring.datasource.username=${DB_USERNAME}`
- ตั้งค่า `spring.datasource.password=${DB_PASSWORD}`

### 2. การอนุญาต Cross-Origin Resource Sharing (CORS)

ระบบได้จัดเตรียมคลาส `CorsConfig` เพื่อดำเนินการกำหนดค่า Global CORS Configuration ซึ่งจะอนุญาตให้ Frontend Application (จาก Vercel หรือ Localhost) สามารถเรียกใช้งาน API Endpoints ได้อย่างสมบูรณ์แบบโดยไม่ถูกจำกัดสิทธิ์จากเบราว์เซอร์

### 3. การควบคุมกระบวนการ Build บน Railway (Nixpacks)

ในการนำโค้ดขึ้นสู่เซิร์ฟเวอร์ Railway จำเป็นต้องเพิ่มตัวแปรในส่วนของ Variables เพื่อควบคุมสภาวะแวดล้อม:

- `NIXPACKS_JDK_VERSION`: กำหนดค่าเป็น `21` เพื่อบังคับให้ระบบคลาวด์ใช้ Java เวอร์ชันที่สอดคล้องกับการตั้งค่าใน `pom.xml` ป้องกันข้อผิดพลาด Compilation Error
- `NIXPACKS_BUILD_CMD`: กำหนดค่าเป็น `./mvnw clean package -DskipTests` เพื่อข้ามกระบวนการ Unit Test ในระหว่างการ Build ลดปัญหาการเชื่อมต่อฐานข้อมูลขณะจัดเตรียม Image

---

## บันทึกการเปลี่ยนแปลง (Changelog)

**[v1.1.0] - 2026-06-07**

- **Added:** ผนวกคลาส `CorsConfig.java` ลงในระบบเพื่อจัดการนโยบาย CORS แบบเบ็ดเสร็จ รองรับการทำ End-to-End Integration กับ Web Application
- **Added:** ประยุกต์ใช้ `@NonNull` Annotation ในส่วนกำหนดค่า เพื่อให้สอดคล้องกับมาตรฐานข้อบังคับเรื่อง Null-Safety ของ Spring Boot เวอร์ชันปัจจุบัน
- **Security:** ปรับปรุงความปลอดภัยของไฟล์ `application.properties` โดยการแทนที่รหัสผ่านด้วยโครงสร้างตัวแปร
- **Fixed:** แก้ไขความขัดแย้งของข้อกำหนดเวอร์ชัน Java (Compilation Error) โดยปรับแก้ตัวแปร `<java.version>` ในไฟล์ `pom.xml` จากเวอร์ชัน 25 เป็นเวอร์ชัน 21 เพื่อให้ทำงานสอดคล้องกับเสถียรภาพของคลาวด์เซิร์ฟเวอร์
