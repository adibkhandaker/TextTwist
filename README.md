# TextTwist

A JavaFX implementation of the TextTwist word puzzle game.

## Rules

TextTwist is a word puzzle game where players rearrange six letters to form as many words as possible. Players must find words of varying lengths (3-6 letters) and discover the 6-letter word to advance to the next round. 

## Features

### Game Modes
- **Timed Mode**: 60-second rounds with countdown timer
- **Untimed Mode**: Play at your own pace

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

## Installation

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher

1. Clone the repository:
```bash
git clone https://github.com/adibkhandaker/TextTwist.git
cd Run-The-Grid
```

2. Install dependencies:
```bash
mvn clean install
```

## Dependencies

- JavaFX 22.0.1 (Controls and FXML)

### System Requirements

- Operating System: Windows, macOS, or Linux
