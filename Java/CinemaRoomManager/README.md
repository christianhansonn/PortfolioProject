# Cinema Room Manager

[![Java](https://img.shields.io/badge/Java-OOP-red.svg)](https://www.java.com/)
[![Hyperskill](https://img.shields.io/badge/Hyperskill-Project-green.svg)](https://hyperskill.org/)
[![Project Type](https://img.shields.io/badge/Project-Learning-blue.svg)](https://github.com/christianhansonn/Hyperskill)

## Overview

This project is a cinema ticket management system developed as part of JetBrains Academy's Java learning track. The application simulates a movie theater's seat reservation system, implementing dynamic pricing based on theater size and seat location, comprehensive statistics tracking, and error-prone input validation.

## Features

- ✅ Dynamic seating grid generation based on user-defined theater dimensions
- ✅ Interactive seat reservation system with visual feedback
- ✅ Intelligent pricing algorithm (front/back row differentiation for larger theaters)
- ✅ Real-time statistics tracking (tickets sold, revenue, occupancy percentage)
- ✅ Input validation with error handling for invalid seat selections
- ✅ Prevention of double-booking seats
- ✅ Menu-driven command-line interface
- ✅ Total income calculation based on theater configuration

## Technical Implementation

### Core Classes

- **CinemaTheater** - Manages the theater's seating grid and reservation logic:
    - Generates dynamic 2D seating arrangement with row/column labels
    - Validates seat availability and user input bounds
    - Tracks purchased tickets and current revenue
    - Calculates occupancy percentage with precise formatting
    - Integrates with PricingService for ticket costs

- **Menu** - Controls the application flow and user interaction:
    - Presents menu options (show seats, buy ticket, view statistics, exit)
    - Routes user selections to appropriate theater methods
    - Displays formatted statistics with proper decimal precision
    - Manages the main application loop

- **PricingService** - Implements dynamic pricing logic:
    - Flat rate ($10) for small theaters (≤60 seats)
    - Tiered pricing for large theaters (front half: \$10, back half: \$8)
    - Calculates total potential income based on theater configuration

- **InputHandler** - Centralizes user input validation:
    - Validates numeric input for menu options and seat selection
    - Handles invalid input gracefully with error messages
    - Ensures type safety for all user interactions

- **Cinema** - Application entry point:
    - Initializes the menu system and starts the application

```java
// Example of the pricing logic
public static int calculateTicketPrice(int row, int totalRows, int totalSeats) {
    if (totalSeats <= 60) {
        return 10;
    } else {
        int firstHalfRows = totalRows / 2;
        return (row <= firstHalfRows) ? 10 : 8;
    }
}
```

### Design Patterns & OOP Concepts

- **Single Responsibility Principle** - Each class has one clear purpose (theater management, menu control, pricing, input handling)
- **Separation of Concerns** - Business logic separated from UI and input validation
- **Encapsulation** - Private fields with public accessor methods for controlled data access
- **Service Pattern** - PricingService and InputHandler provide specialized functionality
- **2D Grid Representation** - List of Lists structure for flexible seating arrangement

## Learning Journey

This project deepened my understanding of Java fundamentals and software design principles:

1. **Data Structure Design**: Creating a flexible 2D grid structure that could represent both the seating layout and maintain state (available vs. booked seats) required careful planning.

2. **Business Logic Implementation**: Translating real-world theater pricing rules into conditional logic helped me understand how to model domain-specific requirements in code.

3. **User Experience Considerations**: Adding input validation, clear error messages, and visual feedback taught me the importance of defensive programming and user-friendly design.

4. **Precision Formatting**: Learning to handle percentage display with exact decimal precision (e.g., `0.00%` instead of `0.0%`) highlighted the importance of attention to detail in production code.

5. **State Management**: Tracking multiple pieces of state (grid configuration, ticket sales, revenue) across user interactions improved my understanding of mutable object design.

6. **Test-Driven Requirements**: Working with specific test case expectations taught me how to meet precise specifications and handle edge cases.

## Running the Application

To run the application:

1. Clone the repository
2. Navigate to the Cinema Room Manager directory
3. Compile the Java files: `javac Cinema\ Room\ Manager/task/src/cinema/*.java`
4. Run the application: `java -cp Cinema\ Room\ Manager/task/src cinema.Cinema`

## Application Flow

1. User inputs theater dimensions (rows and seats per row)
2. Main menu presents four options:
    - **Show the seats**: Displays current seating grid (S = available, B = booked)
    - **Buy a ticket**: Prompts for seat selection, validates availability, calculates price, updates grid
    - **Statistics**: Shows tickets sold, occupancy percentage, current revenue, and total possible income
    - **Exit**: Terminates the application
3. After each action, menu re-displays for next operation
4. Purchased seats are marked as 'B' and cannot be re-booked
5. Invalid selections prompt error messages and retry

## Pricing Rules

- **Small theaters** (≤60 total seats): All tickets $10
- **Large theaters** (>60 total seats):
    - Front half rows: $10 per ticket
    - Back half rows: $8 per ticket

## Technical Reflection

Building this cinema management system reinforced several key Java concepts:

- The power of breaking complex problems into single-purpose classes
- How to model real-world business rules in code
- The importance of input validation for robust applications
- Formatting precision for professional output display
- Managing application state across multiple user interactions

This project demonstrates practical application of object-oriented design principles to solve a real-world business problem - managing a theater's ticket sales and tracking its financial performance.

