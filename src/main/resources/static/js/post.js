function showToast(e, message, redirectUrl) {
    e.preventDefault();
    const notificationContainer = document.getElementById('notification-container');
    const notification = document.createElement('div');
    notification.className = 'notification';
    notification.innerHTML = `
            <div class="notification_body">
                <img src="img/check-circle.svg" alt="Success" class="notification_icon">
                ${message}
            </div>
            <div class="notification_progress"></div>
        `;
    notificationContainer.appendChild(notification);

    setTimeout(() => {
        notification.style.opacity = '0';
        setTimeout(() => {
            notificationContainer.removeChild(notification);
            if (redirectUrl) {
                window.location.href = redirectUrl;
            }
        }, 1000); // Additional delay to allow fade-out transition
    }, 3000); // 3000 milliseconds = 3 seconds
}

function handleSubmit(event) {
    event.preventDefault(); // Prevent the default form submission
    const form = event.target;
    const formData = new FormData(form);

    fetch(form.action, {
        method: form.method,
        body: formData,
    })
        .then(response => {
            if (response.ok) {
                showToast(event, '신청이 완료되었습니다.', '/mypage'); // Replace '/new-page-url' with your desired redirect URL
            } else {
                alert('신청 중 오류가 발생했습니다. 다시 시도해주세요.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('신청 중 오류가 발생했습니다. 다시 시도해주세요.');
        });
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
    showToast(event, quantity + 'kg 신청 완료', '/mypage'); // Replace '/new-page-url' with your desired redirect URL
    togglePurchaseSection(button);
}