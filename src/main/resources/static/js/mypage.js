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

    // // 현재 URL 경로를 기반으로 활성화할 링크를 찾는 함수
    // function activateCurrentMenu() {
    //     const currentPath = window.location.pathname;
    //
    //     menuLinks.forEach(link => {
    //         const linkPath = new URL(link.href).pathname;
    //         if (linkPath === currentPath) {
    //             link.classList.add("active");
    //         } else {
    //             link.classList.remove("active");
    //         }
    //     });
    // }

    // // 각 헤더 메뉴 링크에 클릭 이벤트 핸들러를 추가
    // menuLinks.forEach(link => {
    //     link.addEventListener("click", function(event) {
    //         // 페이지 이동을 위한 기본 동작 허용
    //         link.classList.add("active");
    //     });
    // });

    // // 페이지 로드 시 현재 URL 경로를 기반으로 메뉴 활성화
    // activateCurrentMenu();

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
});
