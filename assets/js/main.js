// Main Application JavaScript
document.addEventListener('DOMContentLoaded', function() {
    initializeApp();
});

async function initializeApp() {
    console.log('Initializing MovieMax Application...');

    // Update navigation based on auth state
    auth.updateNavigation();

    // Load featured movies if we're on the home page
    if (window.location.pathname === '/' || window.location.pathname.includes('index.html')) {
        await loadFeaturedMovies();
    }
}

async function loadFeaturedMovies() {
    const grid = document.getElementById('featuredMoviesGrid');
    if (!grid) return;

    try {
        Utils.showLoading(grid);

        const movies = await api.getMovies();

        // Show only first 6 movies as featured
        const featuredMovies = movies.slice(0, 6);

        if (featuredMovies.length === 0) {
            grid.innerHTML = '<div class="empty-state"><div class="empty-icon">🎬</div><h3>No Movies Available</h3><p>Check back later for exciting new releases!</p></div>';
            return;
        }

        grid.innerHTML = featuredMovies.map(movie => createMovieCard(movie)).join('');

    } catch (error) {
        console.error('Error loading featured movies:', error);
        grid.innerHTML = '<div class="empty-state"><div class="empty-icon">❌</div><h3>Unable to Load Movies</h3><p>Please check your connection and try again.</p></div>';
    }
}

function createMovieCard(movie) {
    const posterUrl = movie.posterUrl || `https://via.placeholder.com/300x450/667eea/white?text=${encodeURIComponent(movie.movieName)}`;

    return `
        <div class="movie-card" onclick="viewMovieDetails(${movie.movieId})">
            <div class="movie-poster">
                <img src="${posterUrl}" alt="${movie.movieName}" onerror="this.src='data:image/svg+xml,<svg xmlns=%22http://www.w3.org/2000/svg%22 viewBox=%220 0 300 450%22><rect fill=%22%23667eea%22 width=%22300%22 height=%22450%22/><text x=%22150%22 y=%22225%22 text-anchor=%22middle%22 fill=%22white%22 font-size=%2220%22>${encodeURIComponent(movie.movieName)}</text></svg>'">
                <div class="movie-rating">8.5</div>
            </div>
            <div class="movie-info">
                <h3 class="movie-title">${movie.movieName}</h3>
                <div class="movie-details">
                    <span class="movie-detail genre">${movie.movieGenre || 'Drama'}</span>
                    <span class="movie-detail language">${movie.language || 'English'}</span>
                    <span class="movie-detail duration">${movie.movieHours || 2}h</span>
                </div>
                <p class="movie-description">${movie.description || 'An exciting cinematic experience awaits you.'}</p>
                <div class="movie-actions">
                    <button class="btn-book" onclick="event.stopPropagation(); bookMovie(${movie.movieId})">Book Now</button>
                </div>
            </div>
        </div>
    `;
}

function viewMovieDetails(movieId) {
    window.location.href = `pages/customer/shows.html?movieId=${movieId}`;
}

function bookMovie(movieId) {
    if (!auth.isCustomerLoggedIn()) {
        alert('Please login to book tickets');
        window.location.href = 'pages/auth/login.html';
        return;
    }

    window.location.href = `pages/customer/shows.html?movieId=${movieId}`;
}

// Global utility functions
window.showLoading = Utils.showLoading;
window.hideLoading = Utils.hideLoading;
window.showError = Utils.showError;
window.showSuccess = Utils.showSuccess;