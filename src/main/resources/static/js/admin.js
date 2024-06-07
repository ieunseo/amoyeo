document.addEventListener("DOMContentLoaded", function() {
    const deleteButtons = document.querySelectorAll('.delete-button');

    deleteButtons.forEach(button => {
        button.addEventListener('click', function() {
            const row = this.closest('tr');
            const userId = row.querySelector('td:nth-child(2)').textContent;

            if (confirm(`Are you sure you want to delete user ${userId}?`)) {
                row.remove();
            }
        });
    });
});
