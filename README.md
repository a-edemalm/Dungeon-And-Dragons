# Dungeon & Dragons (Terminal Adventure)

A highly atmospheric, text-based horror adventure game built entirely in Java. As my first major Java project, it was designed to consolidate my understanding of Object-Oriented Programming (OOP), concurrency, and data structures.

Despite being a terminal application, the game features a bespoke rendering engine and a dedicated, multi-threaded sound system to create a genuinely immersive horror experience.

## 🎨 Creative & Technical Disclaimer
Every element of this project—including the core design, ASCII graphics, picture rendering logic, and UI architecture—is **entirely self-made**.

* **Design & Graphics:** All visual representations and rendering algorithms were developed from scratch specifically for this project.
* **Audio Production:** While the raw soundtracks and music were sourced externally, they have been **heavily modified and remixed**. I applied custom filters, equalisation, ambient layering, and seamless looping to ensure every sound fits the specific, tense atmosphere of the game.

## Showcase

<div align="center">
  <p><i>Custom ASCII screens rendered via the internal VisualEngine.</i></p>

  <details>
  <summary><b>View Startup Screen</b></summary>
  <pre>
:::::::::::::: 
:::::       :: 
:::: ::::    : 
::: ::::::  :: 
::: ::::::  :: 
:::: ::::  ::: 
:::::  :   ::: 
:::::::::::::: :::::::  ::::: :::::::::::::: 
::        :::: :::::  ::  ::: ::          :: 
::   :::   ::: :::   ::::: :: ::::::  :::::: 
::   ::::   :: ::  ::::: :: : ::::::  :::::: 
::   :::   ::: :   ::::  :::: ::::::  :::::: 
::        :::: ::       ::::: ::::::  :::::: 
:::::::::::::: :::::::::::::: :::::::::::::: 
  </pre>
  </details>

  <details>
  <summary><b>View Loading Sequence</b></summary>
  <pre>
:::::::::::::: :::::::::::::: :::::::::::::: :::::::::::::: :::::::::::::: 
:::   :::::::: :::::    ::::: :::::    ::::: ::        :::: :::::::::::::: 
:::   :::::::: ::   ::::   :: ::::  ::  :::: ::   :::   ::: :::::::::::::: 
:::   :::::::: :   ::::::   : :::        ::: ::   ::::   :: :::::::::::::: 
:::   :::::::: ::   ::::   :: ::   ::::   :: ::   :::   ::: :   :   :   :: 
:::        ::: :::::    ::::: :   ::::::   : ::        :::: :: ::: ::: ::: 
:::::::::::::: :::::::::::::: :::::::::::::: :::::::::::::: :::::::::::::: 
  </pre>
  </details>
</div>

## ✨ Technical Highlights

* **Custom Visual Engine:** A bespoke rendering system handling dynamic text wrapping, typing delays, and ANSI colour customisation without external UI libraries.
* **Multi-threaded Sound Engine:** Utilises a secondary thread with `ReentrantLock` and `Condition` for concurrent, non-blocking audio playback.
* **Advanced Data Structures:** A dynamic inventory system built using an `EnumMap` to efficiently categorise items (Weapons, Potions, Keys, Coins).
* **Polymorphism & Abstraction:** Items and Monsters are built on abstract base classes to handle complex interactions dynamically.

## ⚙️ Installation & Execution

### Prerequisites
* **JDK 17+**
* An **ANSI-compatible terminal** (to display colours correctly).
* Ensure the `res/` directory (containing the audio files) is included in the project root.

### Running the Game

```bash
# 1. Clone the repository
git clone [https://github.com/yourusername/dungeon-and-treasures.git](https://github.com/yourusername/dungeon-and-treasures.git)
cd dungeon-and-treasures

# 2. Compile the source code
javac -d bin src/**/*.java

# 3. Run the application
java -cp bin main.Menu
