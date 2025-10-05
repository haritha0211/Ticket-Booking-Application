// Application Configuration
const APP_CONFIG = {
    API_BASE_URL: 'http://localhost:8080/api',
    APP_NAME: 'MovieMax - Book Your Show',
    VERSION: '1.0.0',

    // Local Storage Keys
    STORAGE_KEYS: {
        CUSTOMER_DATA: 'moviemax_customer',
        ADMIN_DATA: 'moviemax_admin',
        BOOKING_TEMP: 'moviemax_temp_booking'
    },

    // API Endpoints
    ENDPOINTS: {
        // Customer endpoints
        CUSTOMER_SIGNIN: '/customers/signin',
        CUSTOMER_SIGNUP: '/customers',
        CUSTOMERS: '/customers',

        // Admin endpoints
        ADMIN_SIGNIN: '/admin/signin',
        ADMIN: '/admin',

        // Movie endpoints
        MOVIES: '/movies',

        // Show endpoints
        SHOWS: '/shows',
        SHOWS_BY_MOVIE: '/shows/movie',

        // Seat endpoints
        SEATS: '/seats',
        SEATS_BY_SCREEN: '/seats/screen',
        SEATS_AVAILABLE: '/seats/available',
        SEATS_BOOK: '/seats/book',

        // Booking endpoints
        BOOKINGS: '/bookings',
        BOOKINGS_BY_CUSTOMER: '/bookings/customer'
    },

    // UI Constants
    SEAT_TYPES: {
        REGULAR: { price: 200, color: '#4CAF50' },
        PREMIUM: { price: 300, color: '#FF9800' },
        VIP: { price: 500, color: '#E91E63' },
        IMAX: { price: 600, color: '#9C27B0' },
        'IMAX Premium': { price: 750, color: '#673AB7' },
        RECLINER: { price: 500, color: '#F44336' }
    }
};