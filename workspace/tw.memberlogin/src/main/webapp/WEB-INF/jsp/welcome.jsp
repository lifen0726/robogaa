<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>成功登入網站</title>
  <!-- 引入SweetAlert2的CDN -->
  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>
你好

<script>
  // 在這裡使用SweetAlert2
  Swal.fire({
    title: "Good job!",
    text: "恭喜你成功登入此網站，可以開始購買商品了",
    icon: "success"
  }).then((result) => {
    // 當SweetAlert2彈出窗口關閉後，跳轉到首頁
    if (result.isConfirmed) {
      window.location.href = "/index.html"; // 直接指向首頁的相對路徑
    }
  });
</script>

</body>
</html>