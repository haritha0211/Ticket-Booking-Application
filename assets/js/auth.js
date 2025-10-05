// Authentication Management
class AuthManager {
    constructor() {
        this.currentCustomer = null;
        this.currentAdmin = null;
        this.loadFromStorage();
    }

    // Load user data from localStorage
    loadFromStorage() {
        try {
            const customerData = localStorage.getItem(APP_CONFIG.STORAGE_KEYS.CUSTOMER_DATA);
            if (customerData) {
                this.currentCustomer = JSON.parse(customerData);
            }

            const adminData = localStorage.getItem(APP_CONFIG.STORAGE_KEYS.ADMIN_DATA);
            if (adminData) {
                this.currentAdmin = JSON.parse(adminData);
            }
        } catch (error) {
            console.error('Error loading auth data:', error);
        }
    }

    // Save customer data
    saveCustomer(customerData) {
        this.currentCustomer = customerData;
        localStorage.setItem(APP_CONFIG.STORAGE_KEYS.CUSTOMER_DATA, JSON.stringify(customerData));
        this.updateNavigation();
    }

    // Save admin data
    saveAdmin(adminData) {
        this.currentAdmin = adminData;
        localStorage.setItem(APP_CONFIG.STORAGE_KEYS.ADMIN_DATA, JSON.stringify(adminData));
        this.updateNavigation();
    }

    // Customer login
    async customerLogin(email, password) {
        try {
            const customer = await api.customerSignIn(email, password);
            this.saveCustomer(customer);
            return customer;
        } catch (error) {
            throw new Error('Invalid email or password');
        }
    }

    // Customer registration
    async customerRegister(customerData) {
        try {
            const customer = await api.customerSignUp(customerData);
            return customer;
        } catch (error) {
            throw new Error('Registration failed. Email may already exist.');
        }
    }

    // Admin login
    async adminLogin(email, password) {
        try {
            const admin = await api.adminSignIn(email, password);
            this.saveAdmin(admin);
            return admin;
        } catch (error) {
            throw new Error('Invalid admin credentials');
        }
    }

    // Logout customer
    customerLogout() {
        this.currentCustomer = null;
        localStorage.removeItem(APP_CONFIG.STORAGE_KEYS.CUSTOMER_DATA);
        this.updateNavigation();
        window.location.href = '/index.html';
    }

    // Logout admin
    adminLogout() {
        this.currentAdmin = null;
        localStorage.removeItem(APP_CONFIG.STORAGE_KEYS.ADMIN_DATA);
        this.updateNavigation();
        window.location.href = '/index.html';
    }

    // Check if customer is logged in
    isCustomerLoggedIn() {
        return this.currentCustomer !== null;
    }

    // Check if admin is logged in
    isAdminLoggedIn() {
        return this.currentAdmin !== null;
    }

    // Get current customer
    getCurrentCustomer() {
        return this.currentCustomer;
    }

    // Get current admin
    getCurrentAdmin() {
        return this.currentAdmin;
    }

    // Update navigation based on auth state
    updateNavigation() {
        const loginLinks = document.querySelectorAll('.nav-login');
        const logoutLinks = document.querySelectorAll('.nav-logout');
        const userNames = document.querySelectorAll('.user-name');

        if (this.isCustomerLoggedIn()) {
            loginLinks.forEach(link => link.style.display = 'none');
            logoutLinks.forEach(link => link.style.display = 'block');
            userNames.forEach(span => span.textContent = this.currentCustomer.customerName);
        } else if (this.isAdminLoggedIn()) {
            loginLinks.forEach(link => link.style.display = 'none');
            logoutLinks.forEach(link => link.style.display = 'block');
            userNames.forEach(span => span.textContent = this.currentAdmin.adminName);
        } else {
            loginLinks.forEach(link => link.style.display = 'block');
            logoutLinks.forEach(link => link.style.display = 'none');
            userNames.forEach(span => span.textContent = '');
        }
    }

    // Require customer login
    requireCustomerLogin() {
        if (!this.isCustomerLoggedIn()) {
            window.location.href = '/pages/auth/login.html';
            return false;
        }
        return true;
    }

    // Require admin login
    requireAdminLogin() {
        if (!this.isAdminLoggedIn()) {
            window.location.href = '/pages/auth/admin-login.html';
            return false;
        }
        return true;
    }
}

// Global auth manager instance
const auth = new AuthManager();