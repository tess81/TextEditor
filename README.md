# TextEditor

Kravspecifikation:
Laborationen går ut på att bygga en komplett texteditor med ett grafiskt användargränssnitt i Java användande Swing.

Utgå ifrån programmet anteckningar (notepad) följande features ska implementeras:
Kompletta textredigeringsmöjligheter: skriva in text, klippa ut, klistra in och kopiera (behöver ej implementeras i meny, det räcker om kortkommandona CTRL-X, CTRL-C, CTRL-V fungerar).
Lagring kan ske i valfritt format men ska lagras till fil.
File (Arkivmenyn) med;
Open
New
Save
Save As samt
Exit

Designkrav
Ska följa MVC-strukturen med definierade gränssnitt.
Lagring ska ske i vanliga textfiler (unicode).

VG grundande kriterier:
Automatisk koll på att fil är sparad vid Exit om inte ska programmet föreslå en save innan exit
Cut, Copy, Paste ska vara valbara i meny.
Automatisk koll på att fil är sparad vid Open (om inte ska programmet föreslå en save innan ny fil öppnas)
Minimal kommunikation mellan MVC delarna av appen. Endast en klass i varje del får kommunicera ut ur delen.
