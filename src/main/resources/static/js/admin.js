document.addEventListener("DOMContentLoaded", () => {
    const deleteButtons = document.querySelectorAll(".delete-btn");

    deleteButtons.forEach(button => {
        button.addEventListener("click", () => {
            const row = button.closest("tr");
            row.remove();
        });
    });
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
});

