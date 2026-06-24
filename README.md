# Lernkartei - Version 7 (fertig)

## Was wir gemacht haben
- Die ganze App mit **CSS designt** (ein gemeinsames JavaFX-Stylesheet `styles.css`).
- `styles.css` liegt bei den FXMLs (`src/viewctrl/styles.css`) und wird in **jeder FXML**
  über `stylesheets="@styles.css"` eingebunden.
- Die alten Inline-Styles (`style="-fx-..."`) wurden raus und durch **CSS-Klassen**
  (`styleClass="..."`) ersetzt - so ist das Design an einer Stelle gebündelt.

## Das Design (modern, mit Pastellfarben)
- Heller, weicher Farbverlauf im Hintergrund: zartes Rosa -> Lavendel -> Himmelblau (`.root`).
- Buttons mit kräftiger Rundung, Pastell-Lila und sanftem Schatten + Hover-Effekt
  (`.button`, plus Varianten `.button-secondary`, `.button-richtig`, `.button-falsch`).
- Eingabefelder und Listen mit Rahmen/Rundung; ausgewählte Listeneinträge pastell hervorgehoben.
- "Karten"-Panel mit weisser Fläche, Rundung und leichtem Schatten (`.card`)
  - z.B. die Frage/Antwort im Lernmodus.
- Eigene Klassen für Überschriften (`.title`, `.subtitle`, `.section-label`),
  Fehlertexte (`.error-label`) und die Frage/Antwort (`.frage-label`, `.antwort-label`).

## Neu/geändert gegenüber Version 6
- Neue Datei: `src/viewctrl/styles.css`.
- Alle FXMLs (`main`, `lern`, `editor`, `statistik`) binden das Stylesheet ein
  und nutzen `styleClass` statt Inline-Styles.
- An der Logik (Java-Code) wurde nichts geändert - nur das Aussehen.

## Fazit
Damit ist die App optisch fertig: Decks und Karten anlegen, lernen mit Spaced
Repetition, Statistik mit Diagrammen - und alles in einem einheitlichen, modernen
Design mit schönen Pastellfarben.

## Klassendiagramm
Das Klassendiagramm (`Klassendiagramm.mdj`, StarUML) ist gleich wie in Version 6,
da CSS keine neuen Klassen einführt - es verändert nur die Darstellung.
