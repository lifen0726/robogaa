axios.get('/getUserProfiles')
    .then(response => {
        const member = response.data; // 獲取伺服器返回的會員物件
        const nickname = member.nickname; // 從會員物件中提取 nickname 屬性
        const memberid = member.memberid; // 從會員物件中提取 memberid 屬性
        console.log('獲取會員信息成功，會員id為:', memberid);
        // 顯示 SweetAlert 彈窗,並將 nickname 插入到 text 中
        Swal.fire({
            title: '登入成功!',
            text: `歡迎，${nickname}!`,
            icon: 'success',
            confirmButtonText: '繼續',
            allowOutsideClick: false
        }).then(result => {
            if (result.isConfirmed) {
                history.go(-2);
            }
        });
    })
    .catch(error => {
        console.error('獲取會員信息失敗:', error);
        // 處理錯誤情況
    });