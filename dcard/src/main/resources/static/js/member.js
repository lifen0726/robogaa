function confirmLogout() {
    if (confirm('您確定要登出嗎？')) {
        window.location.href = '/logout';
    }
    return false;
}

function confirmResetPassword() {
    if (confirm('將進入重設密碼頁面')) {
        window.location.href = '/forgetpassword';
    }
    return false;
}

window.onload = async function () {
    const memberTag = document.getElementById('hello-h3');
    // 發送 GET 請求用戶數據
    const response = await axios.get('/getUserProfiles');

    memberid = response.data.memberid;
    const membername = response.data.nickname;
    console.log(memberid);
    console.log(membername);

// 顯示會員名稱
memberTag.textContent = `您好,${membername}！`;
}

async function changeNickname() {
    let nickname;
    let response;

    const { value } = await Swal.fire({
        title: "修改暱稱",
        input: "text",
        inputLabel: "請輸入您的新暱稱",
        showCancelButton: true,
        inputValidator: (value) => {
            if (!value) {
                return "您需要輸入暱稱!";
            }
        }
    });

    if (value) {
        nickname = value;

        // 發送 PUT 請求修改暱稱
        response = await axios.put(`/api/members/${memberid}`, { nickname });

        if (response.status >= 200 && response.status < 300) {
            Swal.fire("修改暱稱成功!")
            window.location.reload();
        } else {
            Swal.fire("修改暱稱失敗!");
        }
    }
}
