function updateLikes(element) {
    let likes = parseInt(element.getAttribute('data-likes'));
    let liked = element.getAttribute('data-liked') === 'true';

    if (liked) {
        likes -= 1;
        element.setAttribute('data-liked', 'false');
        element.src = '/img/heart-icon.png';
    } else {
        likes += 1;
        element.setAttribute('data-liked', 'true');
        element.src = '/img/heart-icon-red.png';
    }

    element.setAttribute('data-likes', likes);
    element.nextElementSibling.innerText = `${likes} 명이 구매했습니다.`;
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
