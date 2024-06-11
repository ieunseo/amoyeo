function resetIcons() {
    var menuItems = document.querySelectorAll('.menu-item');
    menuItems.forEach(function(item) {
        var img = item.querySelector('img');
        if (img && img.src.includes('after-')) {
            img.src = img.src.replace('after-', 'before-');
        }
        item.classList.remove('active');
    });

    var profileItems = document.querySelectorAll('.menu-item.profile');
    profileItems.forEach(function(item) {
        item.classList.remove('active');
    });
}

document.addEventListener("DOMContentLoaded", function() {
    const menuLinks = document.querySelectorAll(".contentmenu .menu-link");
    const profileMenuItem = document.querySelector(".menu-item.profile");
    const headerMenu = document.getElementById("headerMenu");

    // 프로필 메뉴를 활성화하는 함수
    function activateProfileMenuItem() {
        resetIcons();
        profileMenuItem.classList.add("active");
        const img = profileMenuItem.querySelector('img');
        if (img) {
            img.src = img.getAttribute('data-active-src');
        }
    }

    profileMenuItem.addEventListener("click", function(event) {
        event.preventDefault();
        activateProfileMenuItem();
    });

    activateProfileMenuItem();

    // 전화번호 폼 검증 함수
    function validateForm() {
        const phone = document.querySelector('input[name="userPhone"]').value;
        const phonePattern = /^010-\d{4}-\d{4}$/;

        if (!phonePattern.test(phone)) {
            alert('올바른 전화번호 형식이 아닙니다. 010-1234-5678 형식으로 입력해주세요.');
            return false;
        }

        return true;
    }

    // 폼 검증 추가
    const form = document.querySelector('form');
    if (form) {
        form.onsubmit = validateForm;
    }

    headerMenu.style.display = 'flex';
    function checkWindowSize() {
        const sidebar = document.querySelector('.sidebar');
        if (window.innerWidth <= 767) {
            sidebar.style.display = 'none';
        } else {
            sidebar.style.display = 'flex';
        }
    }

    window.addEventListener('resize', checkWindowSize);
    checkWindowSize();

    // 회원탈퇴 버튼 클릭 이벤트 추가
    const deleteAccountButton = document.querySelector('.delete_account_button');
    if (deleteAccountButton) {
        deleteAccountButton.addEventListener('click', function() {
            if (confirm('정말로 탈퇴하시겠습니까? 탈퇴하면 모든 정보가 삭제됩니다.')) {
                fetch('/mypage/delete', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({})
                }).then(response => {
                    if (response.ok) {
                        alert('회원 탈퇴가 완료되었습니다.');
                        window.location.href = '/';
                    } else {
                        alert('회원 탈퇴 중 오류가 발생했습니다.');
                    }
                }).catch(error => {
                    console.error('Error:', error);
                    alert('회원 탈퇴 중 오류가 발생했습니다.');
                });
            }
        });
    }
});
