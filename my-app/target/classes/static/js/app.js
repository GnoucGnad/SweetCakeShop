/* ============================================================
   App Module - Logic chung cho toàn bộ website
   ============================================================ */

const App = {
    init() {
        this.initNavbar();
        this.initScrollTop();
        Cart.updateBadge();
    },

    // --- Navbar ---
    initNavbar() {
        const navbar = document.querySelector('.navbar');
        const toggle = document.querySelector('.navbar-toggle');
        const nav = document.querySelector('.navbar-nav');

        // Scroll effect
        window.addEventListener('scroll', () => {
            if (window.scrollY > 50) {
                navbar?.classList.add('scrolled');
            } else {
                navbar?.classList.remove('scrolled');
            }
        });

        // Mobile toggle
        toggle?.addEventListener('click', () => {
            nav?.classList.toggle('open');
        });

        // Close mobile menu on link click
        nav?.querySelectorAll('a').forEach(link => {
            link.addEventListener('click', () => {
                nav.classList.remove('open');
            });
        });

        // Active link
        const currentPage = window.location.pathname.split('/').pop() || 'index.html';
        nav?.querySelectorAll('a').forEach(link => {
            const href = link.getAttribute('href');
            if (href === currentPage || (currentPage === '' && href === 'index.html')) {
                link.classList.add('active');
            }
        });
    },

    // --- Scroll to top ---
    initScrollTop() {
        const btn = document.querySelector('.scroll-top');
        if (!btn) return;

        window.addEventListener('scroll', () => {
            if (window.scrollY > 400) {
                btn.classList.add('visible');
            } else {
                btn.classList.remove('visible');
            }
        });

        btn.addEventListener('click', () => {
            window.scrollTo({ top: 0, behavior: 'smooth' });
        });
    },

    // --- Hero Slider ---
    initHeroSlider() {
        const slides = document.querySelectorAll('.hero-slide');
        const dots = document.querySelectorAll('.hero-dot');
        if (slides.length === 0) return;

        let current = 0;
        const total = slides.length;

        function showSlide(index) {
            slides.forEach(s => s.classList.remove('active'));
            dots.forEach(d => d.classList.remove('active'));
            slides[index]?.classList.add('active');
            dots[index]?.classList.add('active');
        }

        // Auto-play
        setInterval(() => {
            current = (current + 1) % total;
            showSlide(current);
        }, 5000);

        // Dot click
        dots.forEach((dot, i) => {
            dot.addEventListener('click', () => {
                current = i;
                showSlide(current);
            });
        });

        showSlide(0);
    },

    // --- Toast Notification ---
    showToast(message, type = 'success') {
        let container = document.querySelector('.toast-container');
        if (!container) {
            container = document.createElement('div');
            container.className = 'toast-container';
            document.body.appendChild(container);
        }

        const icons = {
            success: '✓',
            error: '✕',
            warning: '⚠'
        };

        const toast = document.createElement('div');
        toast.className = `toast ${type}`;
        toast.innerHTML = `
            <span class="toast-icon">${icons[type] || '✓'}</span>
            <span class="toast-message">${message}</span>
            <button class="toast-close" onclick="this.parentElement.remove()">×</button>
        `;

        container.appendChild(toast);

        // Auto remove after 3s
        setTimeout(() => {
            toast.style.animation = 'slideOut 0.3s ease forwards';
            setTimeout(() => toast.remove(), 300);
        }, 3000);
    },

    // --- Format Price ---
    formatPrice(price) {
        return '$' + Number(price).toFixed(2);
    },

    // --- Generate product image URL (placeholder with nice colors) ---
    getProductImage(product) {
        const imageMap = {
            'Chocolate Cake': 'chocolate_cake.jpg',
            'Vanilla Cake': 'Vanilla Cake.jpeg',
            'Strawberry Cake': 'Strawberry_cake.jpg',
            'Caramel Cake': 'Caramel_cake.jpg',
            'Almond Cake': 'banh-hanh-nhan.jpeg',
            'Blueberry Cake': 'Blueberry_cake.jpg',
            'Coconut Cake': 'Coconut_cake.jpg',
            'Honey Cake': 'Honey Cake.jpg',
            'Tiramisu': 'Tiramisu.jpg',
            'Cheese Bread': 'Cheese Bread.jpg',
            'Garlic Bread': 'Garlic Bread.jpg',
            'Sausage Roll': 'Sausage Roll.jpeg',
            'Ham Croissant': 'Ham Croissant.jpg',
            'Herb Crackers': 'Herb Crackers.jpeg',
            'Cheddar Biscuit': 'Cheddar Biscuit.jpeg',
            'Tomato Focaccia': 'Tomato Focaccia.jpeg',
            'Pepper Bread': 'Pepper Bread.jpg',
            'Parmesan Stick': 'Parmesan Stick.jpg'
        };
        const name = product.cakeName || 'Cake';
        const fileName = imageMap[name];
        
        if (fileName) {
            return `images/${fileName}`;
        }
        
        // Fallback if image not found
        return `https://placehold.co/400x400/E8A87C/FFFFFF?text=${encodeURIComponent(name.replace(/ /g, '+'))}`;
    },

    // --- Create Product Card HTML ---
    createProductCard(product) {
        const category = product.optionCake ? product.optionCake.categoryName : '';
        const inStock = product.quantity > 0;
        const stockClass = inStock ? '' : 'out-of-stock';
        const stockText = inStock ? `Còn ${product.quantity} sản phẩm` : 'Hết hàng';

        return `
            <div class="product-card" data-id="${product.cakeId}">
                <div class="product-card-image">
                    <img src="${this.getProductImage(product)}" alt="${product.cakeName}" loading="lazy">
                    ${category === 'Sweet' ? '<span class="product-card-badge">Bánh ngọt</span>' : ''}
                    ${category === 'Salty' ? '<span class="product-card-badge" style="background:var(--secondary)">Bánh mặn</span>' : ''}
                    <div class="product-card-actions">
                        <button class="btn btn-primary btn-sm" onclick="event.stopPropagation(); App.quickAddToCart(${product.cakeId})" ${!inStock ? 'disabled' : ''}>
                            🛒 Thêm vào giỏ
                        </button>
                    </div>
                </div>
                <div class="product-card-body">
                    <div class="product-card-category">${category === 'Sweet' ? 'Bánh ngọt' : 'Bánh mặn'}</div>
                    <h3 class="product-card-title">
                        <a href="product-detail.html?id=${product.cakeId}">${product.cakeName}</a>
                    </h3>
                    <div class="product-card-price">
                        <span class="price">${this.formatPrice(product.price)}</span>
                    </div>
                    <div class="product-card-stock ${stockClass}">${stockText}</div>
                </div>
            </div>
        `;
    },

    // Quick add to cart from card
    async quickAddToCart(cakeId) {
        try {
            const product = await Api.getProductById(cakeId);
            Cart.addItem(product, 1);
        } catch (e) {
            this.showToast(e.message, 'error');
        }
    }
};

// Init on DOM ready
document.addEventListener('DOMContentLoaded', () => App.init());
