<<<<<<< HEAD
# Ticket-Booking-Application
=======
# MovieMax - Complete Movie Booking Frontend

A modern, responsive HTML/CSS/JavaScript frontend application for movie ticket booking that integrates seamlessly with the MovieMax backend API.

## 🚀 Features

### Customer Features
- **User Registration & Authentication** - Create account and secure login
- **Movie Browsing** - Search and filter movies by genre, language
- **Show Selection** - Choose from available show times and theatres  
- **Interactive Seat Selection** - Visual seat map with pricing tiers
- **Booking Management** - Complete booking workflow with payment options
- **Profile Management** - Update personal information and view booking history

### Admin Features  
- **Admin Dashboard** - Overview of system statistics
- **Movie Management** - Add, edit, delete movies
- **Show Management** - Create and manage show schedules
- **Booking Monitoring** - View all customer bookings
- **Revenue Tracking** - Monitor total earnings

## 📁 Project Structure

```
moviemax-frontend/
├── index.html                    # Landing page
├── assets/
│   ├── css/
│   │   ├── styles.css           # Main application styles
│   │   ├── components.css       # Reusable component styles
│   │   ├── auth.css            # Authentication pages
│   │   ├── movies.css          # Movie browsing
│   │   ├── booking.css         # Booking workflow
│   │   └── admin.css           # Admin dashboard
│   └── js/
│       ├── api.js              # Backend API integration
│       ├── auth.js             # Authentication management
│       ├── utils.js            # Utility functions
│       └── main.js             # Core application logic
├── pages/
│   ├── auth/
│   │   ├── login.html          # Customer login
│   │   ├── register.html       # Customer registration
│   │   └── admin-login.html    # Admin login
│   ├── customer/
│   │   ├── movies.html         # Browse movies
│   │   ├── shows.html          # Select show times
│   │   ├── seats.html          # Choose seats
│   │   ├── booking-confirmation.html # Complete booking
│   │   ├── my-bookings.html    # Booking history
│   │   └── profile.html        # Account settings
│   └── admin/
│       ├── dashboard.html      # Admin overview
│       ├── manage-movies.html  # Movie management
│       └── [other admin pages] # Additional admin features
└── config/
    └── app-config.js           # Application configuration
```

## 🔧 Setup Instructions

### Prerequisites
- Web server (Apache, Nginx, or simple HTTP server)
- MovieMax Backend API running on `http://localhost:8080`

### Quick Start
1. **Clone/Download** the frontend files
2. **Configure API URL** in `config/app-config.js`:
   ```javascript
   const APP_CONFIG = {
       API_BASE_URL: 'http://localhost:8080/api',
       // ... other config
   };
   ```
3. **Serve the files** using any web server:
   ```bash
   # Using Python
   python -m http.server 3000

   # Using Node.js
   npx http-server -p 3000

   # Using Live Server (VS Code)
   # Right-click index.html → Open with Live Server
   ```
4. **Access the application** at `http://localhost:3000`

## 🎭 Demo Accounts

### Customer Login
- **Email:** `rajesh@email.com`
- **Password:** `password123`

### Admin Login  
- **Email:** `ravi@admin.com`
- **Password:** `admin123`

## 🌟 Key Features Explained

### Responsive Design
- Mobile-first approach with breakpoints
- Touch-friendly interface for mobile devices
- Fluid grid system and flexible layouts

### API Integration
- RESTful API communication using Fetch API
- Comprehensive error handling and loading states
- Automatic token management for sessions

### User Experience
- Progressive booking workflow with clear steps
- Real-time seat availability and pricing
- Intuitive navigation and visual feedback
- Form validation and user-friendly error messages

### Security Features
- Client-side authentication state management
- Protected routes requiring login
- Input validation and sanitization
- CORS-enabled API communication

## 🎨 Styling & Theme

### Color Scheme
- **Primary:** Gradient from `#667eea` to `#764ba2`
- **Success:** `#28a745` 
- **Warning:** `#ffc107`
- **Danger:** `#dc3545`
- **Background:** `#f8f9fa`

### Typography
- **Font Family:** Arial, sans-serif
- **Headings:** Bold weights with proper hierarchy
- **Body:** 16px base size with 1.6 line height

### Components
- **Cards:** Rounded corners with subtle shadows
- **Buttons:** Smooth transitions and hover effects
- **Forms:** Clean inputs with focus states
- **Navigation:** Sticky header with smooth scrolling

## 🔄 User Workflows

### Customer Booking Flow
1. Browse movies on homepage or movies page
2. Select a movie and view available shows
3. Choose preferred show time and theatre
4. Select seats from interactive seat map
5. Review booking details and choose payment method
6. Complete booking and receive confirmation

### Admin Management Flow
1. Login to admin dashboard
2. View system statistics and overview
3. Manage movies (add, edit, delete)
4. Create and schedule shows
5. Monitor bookings and customer activity
6. Track revenue and system performance

## 🛠️ Customization

### API Configuration
Update `config/app-config.js` to modify:
- Backend API URL
- Storage keys
- Application constants
- Seat pricing tiers

### Styling
Modify CSS files to customize:
- Color scheme and themes
- Layout and spacing
- Typography and fonts  
- Component appearances

### Features
Extend functionality by:
- Adding new API endpoints
- Creating additional customer features
- Implementing advanced admin tools
- Adding payment gateway integration

## 📱 Browser Support

- **Chrome** 70+ ✅
- **Firefox** 65+ ✅
- **Safari** 12+ ✅
- **Edge** 79+ ✅
- **Mobile Safari** iOS 12+ ✅
- **Chrome Mobile** Android 70+ ✅

## 🚀 Performance Features

- **Lazy Loading** - Images and content loaded as needed
- **Caching** - API responses cached in localStorage
- **Optimization** - Minified CSS and compressed images
- **Progressive Enhancement** - Works without JavaScript for basic features

## 🔐 Security Considerations

- **Input Validation** - All forms validated client and server-side
- **HTTPS Ready** - Secure communication protocols
- **XSS Prevention** - Proper data escaping and sanitization
- **Session Management** - Secure authentication handling

## 📞 Support

For issues or questions:
- Check browser console for error messages
- Verify backend API is running and accessible
- Ensure CORS is properly configured on backend
- Check network connectivity and firewall settings

---

**MovieMax Frontend** - Built with ❤️ for movie lovers everywhere!
>>>>>>> 7bf94da (frontend files)
