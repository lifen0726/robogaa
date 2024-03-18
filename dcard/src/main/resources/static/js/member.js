function confirmLogout() {
    if (confirm('您確定要登出嗎？')) {
        window.location.href = '/logout';
    }
    return false;
}