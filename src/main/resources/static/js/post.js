//게시글 하트같은거 임시
function updateLikes(element) {
    let likes = parseInt(element.getAttribute('data-likes'));
    let kg = parseInt(element.getAttribute('data-kg'));
    const liked = element.getAttribute('data-liked') === 'true';

    if (liked) {
        likes -= 1;
        kg -= 1;
        element.setAttribute('data-liked', 'false');
        element.src = '/img/heart-icon.png';
    } else {
        likes += 1;
        kg += 1;
        element.setAttribute('data-liked', 'true');
        element.src =  '/img/heart-icon-red.png';
    }

    element.setAttribute('data-likes', likes);
    element.setAttribute('data-kg', kg);
    document.querySelector('.like-text').innerText = `${kg} kg 구매했습니다.`;
    document.querySelector('.purchase-text').innerText = `${likes} 명이 구매했습니다.`;
}
