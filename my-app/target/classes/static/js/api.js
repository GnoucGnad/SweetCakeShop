/* ============================================================
   API Module - Gọi REST API từ Spring Boot Backend
   ============================================================ */

const API_BASE = '/api';

const Api = {
    // --- Products ---
    async getProducts() {
        const res = await fetch(`${API_BASE}/products`);
        if (!res.ok) throw new Error('Không thể tải danh sách sản phẩm');
        return res.json();
    },

    async getProductById(id) {
        const res = await fetch(`${API_BASE}/products/${id}`);
        if (!res.ok) throw new Error('Không tìm thấy sản phẩm');
        return res.json();
    },

    async getProductsByCategory(categoryId) {
        const res = await fetch(`${API_BASE}/products/category/${categoryId}`);
        if (!res.ok) throw new Error('Không thể tải sản phẩm theo danh mục');
        return res.json();
    },

    async searchProducts(keyword) {
        const res = await fetch(`${API_BASE}/products/search?keyword=${encodeURIComponent(keyword)}`);
        if (!res.ok) throw new Error('Lỗi tìm kiếm');
        return res.json();
    },

    // --- Categories ---
    async getCategories() {
        const res = await fetch(`${API_BASE}/categories`);
        if (!res.ok) throw new Error('Không thể tải danh mục');
        return res.json();
    },

    // --- Orders ---
    async createOrder(orderData) {
        const res = await fetch(`${API_BASE}/orders`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(orderData)
        });
        if (!res.ok) {
            const err = await res.json();
            throw new Error(err.error || 'Đặt hàng thất bại');
        }
        return res.json();
    },

    // --- Reviews ---
    async getReviewsByProduct(cakeId) {
        const res = await fetch(`${API_BASE}/reviews/product/${cakeId}`);
        if (!res.ok) return [];
        return res.json();
    }
};
