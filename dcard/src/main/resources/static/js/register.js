axios.get('/getUserProfiles')
    .then(response => {
        // 顯示 SweetAlert 彈窗
        Swal.fire({
            title: '註冊成功!',
            text: `帳號已啟動，請登入。`,
            icon: 'success',
            confirmButtonText: '馬上登入'
        }).then(result => {
            if (result.isConfirmed) {
                window.location.href = '/login';
            }
        });
    })
    .catch(error => {
        console.error('獲取會員信息失敗:', error);
        // 處理錯誤情況
    });