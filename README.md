# Lernkartei - Version 5

## Was wir gemacht haben
- Eingaben mit Regex überprüft (so wie im Formular-Projekt, Projekt 13):
  - Frage im Editor (2-50 Zeichen, keine komischen Sonderzeichen).
  - Deck-Name (2-30 Zeichen).
- Klasse `LernHistorie` gemacht, die pro Tag mitzählt, wie viele Karten richtig
  und falsch beantwortet wurden (zwei `HashMap`s mit dem Datum als Schlüssel).
- `HistorieFileHandler`, der die Historie als einfache Textdatei speichert/lädt
  (mit `BufferedWriter` / `BufferedReader`).

## Neu/geändert gegenüber Version 4
- Im Editor und beim Deck-Anlegen wird die Eingabe jetzt validiert (Fehlertext, wenn was nicht passt).
- Im Lernmodus wird bei jeder Antwort die Historie hochgezählt.
- Beim Speichern wird jetzt auch die Datei `historie.txt` mitgespeichert und beim Start geladen.
- Das brauchen wir als Vorbereitung für die Statistik (Charts) in der nächsten Version.
