# Lernkartei - Version 4

## Was wir gemacht haben
- Speichern und Laden eingebaut, damit die Karten nicht mehr beim Schließen weg sind.
- Klasse `DeckFileHandler` mit `DataOutputStream` / `DataInputStream` geschrieben
  (ähnlich wie bei der Schülerverwaltung in Projekt 16).
- Pro Karte speichern wir auch einen Typ-Marker (0 = Text, 1 = Multiple Choice),
  damit beim Laden wieder die richtige Klasse erzeugt wird.
- Auch der Lernstand (Streak + nächstes Datum) wird mitgespeichert.

## Neu/geändert gegenüber Version 3
- `DeckVerwaltung` startet jetzt leer; Beispieldaten gibt es nur beim allerersten Start.
- Beim Programmstart wird aus der Datei `lernkartei.dat` geladen (wenn sie existiert).
- Beim Schließen des Fensters wird automatisch gespeichert, dazu gibt es noch einen "Speichern"-Button.
