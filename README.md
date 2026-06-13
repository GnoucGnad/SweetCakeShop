# 🍰 Sweet Cake Shop - Website Thương Mại Điện Tử

![Hero Image](my-app/src/main/resources/static/images/Slide1_transparent.png)

## 📌 Giới thiệu Dự án
**Sweet Cake Shop** là hệ thống website thương mại điện tử chuyên cung cấp các loại bánh ngọt, bánh mặn mang phong cách thiết kế hiện đại, lấy cảm hứng từ các thương hiệu lớn như Fresh Garden. Dự án được phát triển với trọng tâm vào trải nghiệm người dùng (UX/UI mượt mà) và hệ thống xử lý Backend chuẩn mực.

Đây là sản phẩm của **Đồ Án 1 (GR1)**, thực hiện bởi sinh viên **Đặng Đình Cường** (MSSV: 20225795) dưới sự hướng dẫn của **TS. Nguyễn Khanh Văn**.

## 🚀 Các Tính Năng Nổi Bật (Tính đến Giai đoạn 1)
- **Giao diện người dùng (UI) tuyệt đẹp**:
  - Tối ưu hóa toàn bộ hình ảnh với kích thước cực nhẹ, bóc tách phông nền bằng AI (U-2-Net).
  - Sử dụng CSS thuần với hệ thống Grid/Flexbox và hiệu ứng Hover mượt mà.
- **Trải nghiệm mua sắm mượt mà**:
  - Dễ dàng lướt, xem chi tiết và thêm sản phẩm vào giỏ hàng.
  - Quản lý giỏ hàng thông minh bằng LocalStorage.
  - Xác thực Form đặt hàng tức thì.
- **Backend API & Database mạnh mẽ**:
  - Kiến trúc RESTful chuẩn bằng **Spring Boot (Java 17)**.
  - CSDL **PostgreSQL 17** với các Trigger tự động hóa tính toán tồn kho.

## 🛠️ Công Nghệ Sử Dụng
- **Frontend**: HTML5, Vanilla CSS3, Vanilla JavaScript.
- **Backend**: Java 17, Spring Boot, Spring Data JPA.
- **Cơ sở dữ liệu**: PostgreSQL.
- **Công cụ hỗ trợ**: AI U-2-Net (rembg) và Pillow cho xử lý hình ảnh, Git.

## 📂 Cấu trúc Thư mục
- `/my-app`: Source code chính của dự án Spring Boot (chứa Backend và thư mục `static` của Frontend).
- `baocao_tiendo_gr1_v3.docx`: Báo cáo tiến độ hoàn thiện (kèm phần phân tích Kỹ năng Prompt Engineering).
- `database_cake.sql`: Script khởi tạo toàn bộ CSDL và Triggers.
- `generate_docx.py`: Script Python để tự động hóa sinh báo cáo.

## 📝 Hướng Dẫn Chạy Cài Đặt (Local Environment)
1. **Khởi tạo Database**:
   - Chạy PostgreSQL trên máy. Tạo database có tên `cakeshop`.
   - Thực thi file `database_cake.sql` để tạo bảng và nạp dữ liệu mẫu.
2. **Khởi động Backend**:
   - Mở thư mục `my-app` bằng IDE (Eclipse, IntelliJ, VS Code).
   - Chạy class `MyappApplication.java`.
   - Backend sẽ chạy tại cổng `8081`.
3. **Mở Frontend**:
   - Truy cập vào địa chỉ: `http://localhost:8081/index.html` để bắt đầu trải nghiệm.

## 🔮 Định Hướng Tương Lai (Giai đoạn 2)
1. **Tích hợp cổng thanh toán**: VNPay, MoMo.
2. **Xác thực bảo mật**: Đăng nhập, đăng ký bằng Spring Security + JWT.
3. **Trang Quản trị (Admin Dashboard)**: Quản lý hàng hóa, đơn hàng và thống kê.
4. **Deploy**: Đưa hệ thống lên Cloud để vận hành thực tế.

---
*Dự án được xây dựng với tâm huyết và sự kết hợp chặt chẽ giữa tư duy hệ thống của Kỹ sư phần mềm cùng sự hỗ trợ của Trí Tuệ Nhân Tạo (AI).*
