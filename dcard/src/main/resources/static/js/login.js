document.addEventListener('DOMContentLoaded', function() {
    // 登入按鈕點擊事件處理
    document.querySelector('#login-btn').addEventListener('click', function() {
        // 獲取用戶輸入的帳號和密碼
        var email = document.getElementById('logemail').value;
        var password = document.getElementById('logpass').value;
        var rememberMe = document.getElementById('remember-me').checked;

        // 在這裡進行登入操作，例如發送HTTP請求給服務器
        // 使用axios庫來發送POST請求
        axios.post('/login', {
            username: email,
            password: password,
            rememberMe: rememberMe
        })
        .then(function (response) {
            // 登入成功，處理回應數據
            console.log(response.data);
            alert('登入成功！');
            // 可以在這裡導航到登入後的頁面，例如跳轉到首頁
            window.location.href = '/welcome';
        })
        .catch(function (error) {
            // 登入失敗，處理錯誤
            console.error(error);
            alert('登入失敗！請檢查帳號和密碼。');
        });
    });

    // 註冊按鈕點擊事件處理
    document.querySelector('#signup-btn').addEventListener('click', function() {
        // 獲取用戶輸入的暱稱、帳號和密碼
        var name = document.getElementById('logname').value;
        var email = document.getElementById('logemail').value;
        var password = document.getElementById('logpass').value;

        // 在這裡進行註冊操作，例如發送HTTP請求給服務器
        // 使用axios庫來發送POST請求
        axios.post('/api/register', {
            nickname: name,
            username: email,
            password: password
        })
        .then(function (response) {
            // 註冊成功，處理回應數據
            console.log(response.data);
            alert('註冊成功！');
            // 可以在這裡導航到登入頁面，例如跳轉到首頁
            window.location.href = '/login';
        })
        .catch(function (error) {
            // 註冊失敗，處理錯誤
            console.error(error);
            alert('註冊失敗！請檢查輸入的信息。');
        });
    });
});
