const signupForm = document.getElementById('signup-form');

signupForm.addEventListener('submit', (event) => {
    event.preventDefault(); // 阻止默认行为 (表单提交刷新页面)
    Swal.fire({
        title: '傳送中...請稍後',
        icon: 'info',
        allowOutsideClick: false, // 防止用戶點擊外部關閉彈窗
        showConfirmButton: false, // 不顯示確認按鈕
        timerProgressBar: true, // 顯示定時器進度條
    });
    const formData = new FormData(signupForm);
    const nickname = formData.get('nickname');
    const username = formData.get('username');
    const password = formData.get('password');

    axios.post('/api/members/register', {
        nickname,
        username,
        password
    })
        .then((response) => {
            // 注册成功，处理回应数据
            console.log(response.data);
            // alert('注册成功！');
            // 可以导航到登录页面或其他操作
            Swal.fire({
                title: '註冊還差一步..',
                text: '確認信件已發送，請查收郵件並進行確認。',
                icon: 'success',
                confirmButtonText: 'OK'
            }).then((result) => {
                if (result.isConfirmed) {
                    window.location.href = '/login';
                }
            });
        })
        .catch((error) => {
            // 注册失败，处理错误
            console.error(error);
            // alert('注册失败！请检查输入的信息。');
            Swal.fire({
                title: 'Error!',
                text: '發生錯誤，請檢查是否重複提交',
                icon: 'error',
                confirmButtonText: 'Okay'
            })
        });

});