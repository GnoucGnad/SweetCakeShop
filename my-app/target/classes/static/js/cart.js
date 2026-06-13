/* ============================================================
   Cart Module - Quản lý giỏ hàng bằng localStorage
   ============================================================ */

const Cart = {
    STORAGE_KEY: 'cakeshop_cart',

    // Lấy giỏ hàng từ localStorage
    getItems() {
        const data = localStorage.getItem(this.STORAGE_KEY);
        return data ? JSON.parse(data) : [];
    },

    // Lưu giỏ hàng vào localStorage
    saveItems(items) {
        localStorage.setItem(this.STORAGE_KEY, JSON.stringify(items));
        this.updateBadge();
    },

    // Thêm sản phẩm vào giỏ
    addItem(product, quantity = 1) {
        const items = this.getItems();
        const existingIndex = items.findIndex(item => item.cakeId === product.cakeId);

        if (existingIndex > -1) {
            items[existingIndex].quantity += quantity;
        } else {
            items.push({
                cakeId: product.cakeId,
                cakeName: product.cakeName,
                price: product.price,
                quantity: quantity,
                categoryName: product.optionCake ? product.optionCake.categoryName : '',
                description: product.description
            });
        }

        this.saveItems(items);
        App.showToast(`Đã thêm "${product.cakeName}" vào giỏ hàng!`, 'success');
    },

    // Cập nhật số lượng
    updateQuantity(cakeId, quantity) {
        const items = this.getItems();
        const index = items.findIndex(item => item.cakeId === cakeId);
        if (index > -1) {
            if (quantity <= 0) {
                items.splice(index, 1);
            } else {
                items[index].quantity = quantity;
            }
            this.saveItems(items);
        }
    },

    // Xóa sản phẩm khỏi giỏ
    removeItem(cakeId) {
        const items = this.getItems().filter(item => item.cakeId !== cakeId);
        this.saveItems(items);
    },

    // Xóa toàn bộ giỏ hàng
    clear() {
        localStorage.removeItem(this.STORAGE_KEY);
        this.updateBadge();
    },

    // Tính tổng số lượng sản phẩm
    getTotalItems() {
        return this.getItems().reduce((sum, item) => sum + item.quantity, 0);
    },

    // Tính tổng tiền
    getTotalPrice() {
        return this.getItems().reduce((sum, item) => sum + (item.price * item.quantity), 0);
    },

    // Cập nhật badge trên navbar
    updateBadge() {
        const badges = document.querySelectorAll('.cart-badge');
        const total = this.getTotalItems();
        badges.forEach(badge => {
            badge.textContent = total > 0 ? total : '';
        });
    }
};
