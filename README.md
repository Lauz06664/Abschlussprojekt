# Lernkartei - Version 2

## Was wir gemacht haben
- Zweite Kartenart `MultipleChoiceKarte` eingebaut (Frage + 3 Antworten + welche richtig ist).
- `DeckVerwaltung` geschrieben, die alle Decks verwaltet:
  - `HashMap`, um ein Deck schnell über den Namen zu finden.
  - `TreeSet`, damit die Decks immer alphabetisch sortiert sind.
- `ViewManager` gemacht, damit wir mehrere Fenster/Szenen wechseln können (wie in Projekt 10).
- Einen modalen Editor (`editor.fxml` + `EditorController`) gebaut, um neue Karten anzulegen.

## Neu/geändert gegenüber Version 1
- `Deck` ist jetzt `Comparable` (Sortierung nach Name).
- Decks kommen nicht mehr direkt aus dem Controller, sondern aus der `DeckVerwaltung`.
- In der Hauptansicht kann man jetzt selbst neue Decks anlegen und über den Editor Karten hinzufügen.
- `Main` startet das Programm jetzt über den `ViewManager`.
