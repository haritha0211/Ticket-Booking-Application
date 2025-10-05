// Utility Functions
class Utils {
    // Format currency
    static formatCurrency(amount) {
        return `₹${parseFloat(amount).toFixed(2)}`;
    }

    // Format date
    static formatDate(dateString) {
        const date = new Date(dateString);
        return date.toLocaleDateString('en-IN', {
            year: 'numeric',
            month: 'short',
            day: 'numeric'
        });
    }

    // Format time
    static formatTime(dateString) {
        const date = new Date(dateString);
        return date.toLocaleTimeString('en-IN', {
            hour: '2-digit',
            minute: '2-digit'
        });
    }

    // Format date and time
    static formatDateTime(dateString) {
        return `${this.formatDate(dateString)} ${this.formatTime(dateString)}`;
    }

    // Show loading spinner
    static showLoading(element) {
        if (element) {
            element.innerHTML = '<div class="loading-spinner">Loading...</div>';
        }
    }

    // Hide loading spinner
    static hideLoading(element) {
        if (element) {
            const spinner = element.querySelector('.loading-spinner');
            if (spinner) {
                spinner.remove();
            }
        }
    }

    // Show error message
    static showError(message, container) {
        const errorDiv = document.createElement('div');
        errorDiv.className = 'error-message';
        errorDiv.textContent = message;

        if (container) {
            container.prepend(errorDiv);
            setTimeout(() => errorDiv.remove(), 5000);
        } else {
            alert(message);
        }
    }

    // Show success message
    static showSuccess(message, container) {
        const successDiv = document.createElement('div');
        successDiv.className = 'success-message';
        successDiv.textContent = message;

        if (container) {
            container.prepend(successDiv);
            setTimeout(() => successDiv.remove(), 5000);
        } else {
            alert(message);
        }
    }

    // Validate email
    static validateEmail(email) {
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return emailRegex.test(email);
    }

    // Validate phone number
    static validatePhone(phone) {
        const phoneRegex = /^[6-9]\d{9}$/;
        return phoneRegex.test(phone);
    }

    // Generate booking reference
    static generateBookingRef() {
        const timestamp = Date.now().toString(36);
        const random = Math.random().toString(36).substr(2, 5);
        return `BKG${timestamp}${random}`.toUpperCase();
    }

    // Get URL parameters
    static getUrlParameter(name) {
        const urlParams = new URLSearchParams(window.location.search);
        return urlParams.get(name);
    }

    // Set URL parameter
    static setUrlParameter(name, value) {
        const url = new URL(window.location);
        url.searchParams.set(name, value);
        window.history.pushState({}, '', url);
    }

    // Debounce function
    static debounce(func, wait) {
        let timeout;
        return function executedFunction(...args) {
            const later = () => {
                clearTimeout(timeout);
                func(...args);
            };
            clearTimeout(timeout);
            timeout = setTimeout(later, wait);
        };
    }

    // Group array by key
    static groupBy(array, key) {
        return array.reduce((result, currentValue) => {
            (result[currentValue[key]] = result[currentValue[key]] || []).push(currentValue);
            return result;
        }, {});
    }

    // Sort array by date
    static sortByDate(array, dateKey, ascending = true) {
        return array.sort((a, b) => {
            const dateA = new Date(a[dateKey]);
            const dateB = new Date(b[dateKey]);
            return ascending ? dateA - dateB : dateB - dateA;
        });
    }
}