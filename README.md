# Lernkartei - Version 6 (fertig)

## Was wir gemacht haben
- Die Statistik-Seite mit Diagrammen gebaut (so wie in Übungsstunde 4 mit den Charts).
- `statistik.fxml` + `StatistikController`:
  - `LineChart`: zeigt den Lernfortschritt der letzten 7 Tage (richtig und falsch).
  - `BarChart`: zeigt, wie viele Karten in jedem Deck sind.
- Daten für die Charts kommen aus der `LernHistorie` und der `DeckVerwaltung`.

## Neu/geändert gegenüber Version 5
- `ViewManager` kann jetzt auch zur Statistik wechseln (`showStatistik`).
- In der Hauptansicht gibt es den Button "Statistik".

## Fazit
Damit ist die App komplett: Decks und Karten anlegen, lernen mit Spaced Repetition,
alles wird gespeichert und man sieht seinen Fortschritt in den Diagrammen.
