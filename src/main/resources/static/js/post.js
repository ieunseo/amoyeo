document.addEventListener('DOMContentLoaded', function() {
    const likeForms = document.querySelectorAll('.like-form');
    likeForms.forEach(form => {
        const userNo = form.querySelector('input[name="userNo"]').value;
        const requireNo = form.querySelector('input[name="requireNo"]').value;
        checkLikeStatus(userNo, requireNo, form);
    });
});

function checkLikeStatus(userNo, requireNo, form) {
    fetch(`/checkLikeStatus?userNo=${userNo}&requireNo=${requireNo}`)
        .then(response => response.json())
        .then(data => {
            const img = form.querySelector('.like-icon');
            if (data.liked) {
                img.src = '/img/heart-icon-red.png';
                img.alt = 'liked';
            } else {
                img.src = '/img/heart-icon.png';
                img.alt = 'unliked';
            }
        });
}

function toggleLike(button, requireNo, userNo) {
    const form = button.closest('form');
    const img = button.querySelector('.like-icon');
    const isLiked = img.getAttribute('alt') === 'liked';

    // AJAX 요청으로 좋아요 상태 변경
    const xhr = new XMLHttpRequest();
    xhr.open("POST", form.getAttribute('action'), true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // 요청이 성공하면 아이콘 이미지를 변경
            if (isLiked) {
                img.src = '/img/heart-icon.png';
                img.alt = 'unliked';
            } else {
                img.src = '/img/heart-icon-red.png';
                img.alt = 'liked';
            }
        }
    };

    const formData = new FormData(form);
    xhr.send(new URLSearchParams(formData).toString());
}
document.addEventListener('DOMContentLoaded', function() {
    const likeForms = document.querySelectorAll('.like-form');
    likeForms.forEach(form => {
        const userNo = form.querySelector('input[name="userNo"]').value;
        const requireNo = form.querySelector('input[name="requireNo"]').value;
        checkLikeStatus(userNo, requireNo, form);
    });
});

function checkLikeStatus(userNo, requireNo, form) {
    fetch(`/checkLikeStatus?userNo=${userNo}&requireNo=${requireNo}`)
        .then(response => response.json())
        .then(data => {
            const img = form.querySelector('.like-icon');
            if (data.liked) {
                img.src = '/img/heart-icon-red.png';
                img.alt = 'liked';
            } else {
                img.src = '/img/heart-icon.png';
                img.alt = 'unliked';
            }
        });
}

function toggleLike(button, requireNo, userNo) {
    const form = button.closest('form');
    const img = button.querySelector('.like-icon');
    const isLiked = img.getAttribute('alt') === 'liked';

    // AJAX 요청으로 좋아요 상태 변경
    const xhr = new XMLHttpRequest();
    xhr.open("POST", form.getAttribute('action'), true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // 요청이 성공하면 아이콘 이미지를 변경
            if (isLiked) {
                img.src = '/img/heart-icon.png';
                img.alt = 'unliked';
            } else {
                img.src = '/img/heart-icon-red.png';
                img.alt = 'liked';
            }
        }
    };

    const formData = new FormData(form);
    xhr.send(new URLSearchParams(formData).toString());
}



function togglePurchaseSection(button) {
    var purchaseSection = button.parentElement.nextElementSibling;
    if (purchaseSection.style.display === 'none' || purchaseSection.style.display === '') {
        purchaseSection.style.display = 'block';
    } else {
        purchaseSection.style.display = 'none';
    }
}

function decreaseQuantity(button) {
    var quantityInput = button.nextElementSibling;
    if (quantityInput.value > 1) {
        quantityInput.value = parseInt(quantityInput.value) - 1;
    }
}

function increaseQuantity(button) {
    var quantityInput = button.previousElementSibling;
    quantityInput.value = parseInt(quantityInput.value) + 1;
}

function submitPurchase(button) {
    var quantityInput = button.previousElementSibling.querySelector('.quantity');
    var quantity = quantityInput.value;
    alert(quantity + 'kg가 신청되었습니다. 자세한 사항은 마이페이지에서 확인하세요.');
    togglePurchaseSection(button);
}