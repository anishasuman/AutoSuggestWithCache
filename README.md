## 🌻 SmartSearchApp

Hey! This is **SmartSearchApp**, a **Java Swing GUI project** to help you learn and build confidence in:

✅ Making **smart search suggestions**
✅ Keeping **recent search history**
✅ Clearing history with a click
✅ Opening Google searches directly
✅ Using clean, modern Java UI

---

## 💡 What this app does

✨ As you **type**, it suggests searches (like “app,” “review,” “latest”).
✨ You can **click suggestions** to search on Google instantly.
✨ Keeps track of **recent searches** in a clean panel.
✨ **“🗑️ Clear History” button** wipes your history when you want a fresh start.

---

## 🚀 How to run it (super easy)

### 🛠️ What you need:

✅ **JDK 24** installed on your laptop.
✅ Project built in **IntelliJ** (or your IDE).

---

### ✅ Steps:

1️⃣ **Build the .jar:**

* In IntelliJ:

  ```
  Build > Build Artifacts > SmartSearchApp:jar > Build
  ```

  It will generate:

  ```
  C:\Users\Anisha\IdeaProjects\SmartSearchApp\out\artifacts\SmartSearchApp_jar\SmartSearchApp.jar
  ```

2️⃣ **Open Command Prompt.**

3️⃣ Type this to go to your project jar:

```cmd
cd "C:\Users\Anisha\IdeaProjects\SmartSearchApp\out\artifacts\SmartSearchApp_jar"
```

4️⃣ Now launch your app:

```cmd
"C:\Program Files\Java\jdk-24\bin\java.exe" -jar SmartSearchApp.jar
```

---

✨ **Your Smart Search GUI will open!**
Start typing and enjoy testing your app.

---

## 🗂️ Project structure (for clarity)

```
SmartSearchApp/
├── src/
│   ├── ui/                # All UI-related classes
│   │   ├── SearchGUI.java
│   │   └── RoundedBorder.java
│   ├── util/              # Core logic and search engine
│   │   └── SearchEngine.java
│   └── autocomplete/      # Optional: Trie for advanced autocomplete
│       └── Trie.java
└── out/artifacts/SmartSearchApp_jar/SmartSearchApp.jar
```

---

## 🙋🏻‍♀️ Why I made this

I’m learning **Java GUI building** and wanted a **real app** to practice:

✅ Java Swing layout and design
✅ Event handling with buttons and fields
✅ Managing search logic cleanly
✅ Packaging a `.jar` I can run on any machine

---

## 🚩 Notes

⚡ If the clear history button isn’t working:

* Ensure your `SearchEngine.java` has:

  ```java
  public void clearHistory() {
      history.clear();
  }
  ```

  and you have a `history` list managing your past searches.

⚡ You can connect your `Trie` class for **better autocomplete** later if you want.

⚡ If `java` is not recognized, add JDK 24’s `bin` to your PATH, or use the full command above.

---

## 🫶 Let’s build more!

This is just the start 🌱. Next, you can:
✅ Add dark mode
✅ Add local file-based search persistence
✅ Use your `Trie` for fast autocomplete
✅ Deploy your `.jar` for friends to try

