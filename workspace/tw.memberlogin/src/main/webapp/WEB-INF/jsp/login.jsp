<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>登入頁面</title>
<link rel="stylesheet" href="style.css">
<style type="text/css">
body {
  font-family: Arial, sans-serif;
  background-color: #f0f0f0;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  margin: 0;
}

.login-container {
  background-color: white;
  padding: 20px;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.login-form {
  display: flex;
  flex-direction: column;
  margin-right: 30px;
}

.input-group {
  margin-bottom: 15px;
}

.input-group label {
  margin-bottom: 5px;
  display: block;
}

.input-group input {
  width: 100%;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 4px;
}

button {
  background-color: #007bff;
  color: white;
  padding: 10px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}

a {
  display: block;
  margin-top: 10px;
  text-align: right;
  text-decoration: none;
  color: #007bff;
}

a:hover {
  text-decoration: underline;
}
</style>
</head>
<body>

<div class="login-container">
  <form class="login-form" action="/login/page" method="post">
    <h2>登入</h2>
    <div class="input-group">
      <label for="username">使用者名稱</label>
      <input type="text" id="username" name="username" required>
    </div>
    <div class="input-group">
      <label for="password">密碼</label>
      <input type="password" id="password" name="password" required>
    </div>
    <button type="submit">登入</button>
    <a href="#">忘記密碼?</a>
  </form>
</div>
<script type="text/javascript">
function login() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    var formData = new FormData();
    formData.append("username", username);
    formData.append("password", password);

    fetch('/members/login', {
        method: 'POST',
        body: formData,
    })
    .then(response => {
        if (response.ok) {
            return response.text();
        }
        throw new Error('登入失敗！');
    })
    .then(text => {
        alert('登入成功！');
        // 可以在这里根据需要处理跳转，例如：
        // window.location.href = '/home';
        console.log('Login response:', text);
    })
    .catch(error => {
        alert(error.message);
    });
}

</script>
</body>
</html>