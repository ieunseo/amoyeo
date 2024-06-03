function changeIcon(element, activeSrc, inactiveSrc) {
    var img = element.querySelector('img');
    var isActive = img.src.includes(activeSrc);
    resetIcons();
    img.src = isActive ? inactiveSrc : activeSrc;
    if (!isActive) {
        element.classList.add('active');
    }
}
// 프로필 아이콘 활성화
function resetIcons() {
    var menuItems = document.querySelectorAll('.menu-item');
    menuItems.forEach(function(item) {
        var img = item.querySelector('img');
        if (img.src.includes('after-')) {
            img.src = img.src.replace('after-', 'before-');
        }
        item.classList.remove('active');
    });

    var profileItems = document.querySelectorAll('.menu-item.profile');
    profileItems.forEach(function(item) {
        item.classList.remove('active');
    });
}

function toggleProfileBorder(element) {
    resetIcons();
    element.classList.toggle('active');
}


// 홈버튼 눌렀을때만 메뉴 나오게
document.addEventListener("DOMContentLoaded", function() {
    const menuLinks = document.querySelectorAll(".menu-link");
    const headerMenu = document.getElementById("headerMenu");
    //활성화 시 동작

    function activateMenu(element) {
        menuLinks.forEach(link => link.classList.remove("active"));
        element.classList.add("active");
    }

    menuLinks.forEach(link => {
        link.addEventListener("click", function(event) {
            event.preventDefault();// 기본 활성화 돼있는거
            activateMenu(this);
        });
    });

    headerMenu.style.display = 'flex';
});

function handleHomeClick(element) {
    var headerMenu = document.getElementById("headerMenu");
    var img = element.querySelector('img');
    var isActive = img.src.includes('after-home.png');
    resetIcons();
    img.src = isActive ? 'before-home.png' : 'after-home.png';
    if (!isActive) {
        element.classList.add('active');
        headerMenu.style.display = 'flex';
    } else {
        headerMenu.style.display = 'none';
    }
}
// 사이드바 메뉴
function changeIconAndHideMenu(element, activeSrc) {
    var img = element.querySelector('img');
    var headerMenu = document.getElementById("headerMenu");
    resetIcons();
    img.src = activeSrc;
    element.classList.add('active');
    headerMenu.style.display = 'none';
}

function toggleProfileBorderAndHideMenu(element) {
    var headerMenu = document.getElementById("headerMenu");
    resetIcons();
    element.classList.toggle('active');
    headerMenu.style.display = 'none';
}

