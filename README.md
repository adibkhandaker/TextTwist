# TextTwist - Enhanced Word Game

A modern JavaFX implementation of the classic TextTwist word puzzle game with enhanced UI/UX design and comprehensive user experience improvements.

## Project Overview

TextTwist is a word puzzle game where players rearrange six letters to form as many words as possible. Players must find words of varying lengths (3-6 letters) and discover the 6-letter word to advance to the next round. This implementation features a modern interface with improved accessibility and user-friendly design.

## Features

### Game Modes
- **Timed Mode**: 60-second rounds with countdown timer
- **Untimed Mode**: Play at your own pace without time pressure

### Enhanced User Interface
- Modern gradient backgrounds and visual effects
- Responsive button design with hover animations
- High-contrast text areas for improved readability
- Clean, professional aesthetic without distracting elements

### User Experience Improvements
- Comprehensive rules dialog displayed before each game
- Clear visual indicators for word placeholders using solid squares
- Improved typography with monospace fonts for consistency
- Enhanced keyboard and mouse input support

### Technical Features
- JavaFX-based modern UI architecture
- CSS-styled components with custom themes
- Modular code structure with separation of concerns
- Cross-platform compatibility

## Installation and Setup

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher

### Running the Application
1. Clone the repository:
   ```bash
   git clone https://github.com/adibkhandaker/TextTwist.git
   cd TextTwist
   ```

2. Compile and run using Maven:
   ```bash
   ./mvnw clean compile
   ./mvnw javafx:run
   ```

3. Alternatively, use regular Maven commands:
   ```bash
   mvn clean compile
   mvn javafx:run
   ```

## How to Play

### Objective
Rearrange the 6 letters to form as many words as possible. Words must be at least 3 letters long. Find the 6-letter word to advance to the next round.

### Controls
- **Mouse**: Click letters to spell words
- **Keyboard**: Type letters directly to spell words
- **Enter**: Submit the current word
- **Backspace**: Remove the last letter
- **Shuffle Button**: Rearrange the available letters
- **Clear Button**: Reset the current word

### Scoring System
- 3-letter words: 1,500 points
- 4-letter words: 2,000 points
- 5-letter words: 2,500 points
- 6-letter words: 3,000 points

## Technical Architecture

### Technologies Used
- **Java 17+**: Core programming language
- **JavaFX**: Modern UI framework for desktop applications
- **FXML**: Declarative markup for UI layouts
- **CSS**: Custom styling and theming
- **Maven**: Build automation and dependency management

### Project Structure
```
src/
├── main/
│   ├── java/
│   │   ├── com/example/texttwist/
│   │   │   ├── TextTwistApplication.java    # Main application entry point
│   │   │   ├── TextTwistController.java     # Main menu controller
│   │   │   ├── PlayscreenController.java    # Game logic controller
│   │   │   ├── ScorescreenController.java   # Score display controller
│   │   │   ├── Trie.java                    # Word dictionary data structure
│   │   │   └── TrieNode.java                # Trie node implementation
│   │   └── module-info.java                 # Java module configuration
│   └── resources/
│       └── com/example/texttwist/
│           ├── texttwist.fxml               # Main menu layout
│           ├── playscreen.fxml              # Game screen layout
│           ├── scorescreen.fxml             # Score screen layout
│           ├── styles.css                   # Custom styling
│           └── [image assets]               # Game graphics
├── ospd.txt                                 # Word dictionary file
└── pom.xml                                  # Maven configuration
```

### Key Components

#### Data Structures
- **Trie**: Efficient prefix tree for word validation and storage
- **TrieNode**: Individual nodes in the trie structure for fast lookups

#### Controllers
- **TextTwistController**: Manages main menu and game mode selection
- **PlayscreenController**: Handles game logic, user input, and scoring
- **ScorescreenController**: Displays final scores and statistics

#### UI Components
- **FXML Layouts**: Declarative UI structure with proper component binding
- **CSS Styling**: Modern theming with gradients, shadows, and animations
- **Event Handling**: Comprehensive keyboard and mouse input processing

## Development Features

### Code Quality
- Modular architecture with clear separation of concerns
- Comprehensive error handling and validation
- Consistent coding standards and documentation
- Type-safe implementation with proper generics usage

### Performance Optimizations
- Efficient word lookup using trie data structure
- Optimized UI rendering with CSS hardware acceleration
- Memory-efficient string processing and manipulation
- Responsive event handling without blocking UI thread

### Accessibility
- High-contrast color schemes for better visibility
- Keyboard navigation support for all game functions
- Clear visual feedback for user interactions
- Scalable fonts and UI elements

## Contributing

This project serves as a demonstration of modern JavaFX development practices and UI/UX design principles. The codebase showcases:

- Clean architecture patterns
- Modern Java features and best practices
- Professional UI/UX design implementation
- Cross-platform desktop application development

## Build Information

- **Build Tool**: Maven 3.6+
- **Java Version**: 17+
- **JavaFX Version**: 22.0.1
- **Target Platform**: Cross-platform desktop (Windows, macOS, Linux)

## License

This project is developed for educational and portfolio demonstration purposes, showcasing modern JavaFX application development and UI/UX design principles. 