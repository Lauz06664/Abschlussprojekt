# Lernkartei - Version 3

## Was wir gemacht haben
- Den eigentlichen Lernmodus gebaut - das ist jetzt das Herzstück.
- Klasse `LernSession` mit einer `PriorityQueue`: die Karte, die am dringendsten dran ist
  (frühestes Datum), kommt zuerst.
- Die rekursive Berechnung vom Wiederholungs-Intervall gemacht:
  richtig -> Intervall verdoppelt sich (1, 2, 4, 8 Tage ...), falsch -> zurück auf 1 Tag.
- Neue Szene `lern.fxml` + `LernController`: Frage anzeigen, "Antwort zeigen", dann Richtig/Falsch.

## Neu/geändert gegenüber Version 2
- `Lernkarte` hat jetzt Setter für Streak und Datum, damit der Lernfortschritt verändert werden kann.
- `ViewManager` kann jetzt auch in den Lernmodus wechseln (`showLernmodus`).
- In der Hauptansicht gibt es den Button "Lernen starten".
- Bei falscher Antwort wird die Karte sofort wieder eingereiht und kommt nochmal dran.
