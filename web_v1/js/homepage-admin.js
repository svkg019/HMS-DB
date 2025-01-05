// Add active class to current navigation item
document.querySelectorAll('.sidebar a').forEach(link => {
    link.addEventListener('click', (e) => {
        e.preventDefault();
        document.querySelector('.sidebar a.active')?.classList.remove('active');
        link.classList.add('active');
    });
});

// Simple animation for the hero section

// document.addEventListener('DOMContentLoaded', () => {
//     const hero = document.querySelector('.hero');
//     hero.style.opacity = '0';
//     hero.style.transform = 'translateY(20px)';
//     hero.style.transition = 'all 0.5s ease-out';
    
//     setTimeout(() => {
//         hero.style.opacity = '1';
//         hero.style.transform = 'translateY(0)';
//     }, 100);
// });