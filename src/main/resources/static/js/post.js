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
function showToast(message) {
    const toast = document.createElement('div');
    toast.className = 'toast';
    toast.innerText = message;
    document.body.appendChild(toast);
    toast.classList.add('show');
    setTimeout(() => {
        toast.classList.remove('show');
        document.body.removeChild(toast);
    }, 3000);
}

function submitPurchase(event) {
    event.preventDefault();
    const button = event.target;
    const quantityInput = button.closest('form').querySelector('.quantity');
    const quantity = quantityInput.value;
    showToast(quantity + 'kg 신청 완료');
    togglePurchaseSection(button);
}

document.addEventListener('DOMContentLoaded', function() {
    document.querySelectorAll('.purchase-section form').forEach(form => {
        form.addEventListener('submit', submitPurchase);
    });
});
