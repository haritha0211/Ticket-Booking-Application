// API Handler for backend communication
class APIHandler {
    constructor() {
        this.baseURL = APP_CONFIG.API_BASE_URL;
    }

    // Generic fetch wrapper
    async request(endpoint, options = {}) {
        const url = `${this.baseURL}${endpoint}`;

        const defaultOptions = {
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        };

        const config = { ...defaultOptions, ...options };

        try {
            console.log(`API Request: ${config.method || 'GET'} ${url}`);
            const response = await fetch(url, config);

            if (!response.ok) {
                const errorText = await response.text();
                throw new Error(`HTTP ${response.status}: ${errorText}`);
            }

            const data = await response.json();
            console.log(`API Response:`, data);
            return data;
        } catch (error) {
            console.error('API Error:', error);
            throw error;
        }
    }

    // GET request
    async get(endpoint) {
        return this.request(endpoint, { method: 'GET' });
    }

    // POST request
    async post(endpoint, data) {
        return this.request(endpoint, {
            method: 'POST',
            body: JSON.stringify(data)
        });
    }

    // PUT request
    async put(endpoint, data) {
        return this.request(endpoint, {
            method: 'PUT',
            body: JSON.stringify(data)
        });
    }

    // DELETE request
    async delete(endpoint) {
        return this.request(endpoint, { method: 'DELETE' });
    }

    // Customer API Methods
    async customerSignIn(email, password) {
        return this.post(`${APP_CONFIG.ENDPOINTS.CUSTOMER_SIGNIN}?email=${encodeURIComponent(email)}&password=${encodeURIComponent(password)}`, {});
    }

    async customerSignUp(customerData) {
        return this.post(APP_CONFIG.ENDPOINTS.CUSTOMER_SIGNUP, customerData);
    }

    async getCustomer(customerId) {
        return this.get(`${APP_CONFIG.ENDPOINTS.CUSTOMERS}/${customerId}`);
    }

    // Admin API Methods
    async adminSignIn(email, password) {
        return this.post(`${APP_CONFIG.ENDPOINTS.ADMIN_SIGNIN}?email=${encodeURIComponent(email)}&password=${encodeURIComponent(password)}`, {});
    }

    // Movie API Methods
    async getMovies() {
        return this.get(APP_CONFIG.ENDPOINTS.MOVIES);
    }

    async getMovie(movieId) {
        return this.get(`${APP_CONFIG.ENDPOINTS.MOVIES}/${movieId}`);
    }

    async createMovie(movieData) {
        return this.post(APP_CONFIG.ENDPOINTS.MOVIES, movieData);
    }

    async updateMovie(movieId, movieData) {
        return this.put(`${APP_CONFIG.ENDPOINTS.MOVIES}/${movieId}`, movieData);
    }

    async deleteMovie(movieId) {
        return this.delete(`${APP_CONFIG.ENDPOINTS.MOVIES}/${movieId}`);
    }

    // Show API Methods
    async getShows() {
        return this.get(APP_CONFIG.ENDPOINTS.SHOWS);
    }

    async getShow(showId) {
        return this.get(`${APP_CONFIG.ENDPOINTS.SHOWS}/${showId}`);
    }

    async getShowsByMovie(movieId) {
        return this.get(`${APP_CONFIG.ENDPOINTS.SHOWS_BY_MOVIE}/${movieId}`);
    }

    async createShow(showData) {
        return this.post(APP_CONFIG.ENDPOINTS.SHOWS, showData);
    }

    // Seat API Methods
    async getSeatsByScreen(screenId) {
        return this.get(`${APP_CONFIG.ENDPOINTS.SEATS_BY_SCREEN}/${screenId}`);
    }

    async getAvailableSeats(showId) {
        return this.get(`${APP_CONFIG.ENDPOINTS.SEATS_AVAILABLE}/${showId}`);
    }

    async bookSeats(seatIds) {
        return this.post(APP_CONFIG.ENDPOINTS.SEATS_BOOK, seatIds);
    }

    // Booking API Methods
    async getBookings() {
        return this.get(APP_CONFIG.ENDPOINTS.BOOKINGS);
    }

    async getBooking(bookingId) {
        return this.get(`${APP_CONFIG.ENDPOINTS.BOOKINGS}/${bookingId}`);
    }

    async getBookingsByCustomer(customerId) {
        return this.get(`${APP_CONFIG.ENDPOINTS.BOOKINGS_BY_CUSTOMER}/${customerId}`);
    }

    async createBooking(bookingData) {
        return this.post(APP_CONFIG.ENDPOINTS.BOOKINGS, bookingData);
    }

    async deleteBooking(bookingId) {
        return this.delete(`${APP_CONFIG.ENDPOINTS.BOOKINGS}/${bookingId}`);
    }
}

// Global API instance
const api = new APIHandler();