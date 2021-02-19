# APIdesign2021

NOTAT TIL GRUPPEN:
Siden vi ikke skal ha en executable fil så må vi ekportere en .jar fil. Denne kan vi bare importere i et annet prosjekt og bruke uten masse kode bak som klienten må styre med. 

## Slikt bygger du en .jar fil med IntelliJ
1. File --> Project Structure
2. Gå til "Artifacts" --> klikk på "+" knappen --> JAR --> From modules with dependencies
3. Velg modulen (i dette tilfellet rammeverk)
4. Ikke velg noen "Main class", la den være tom.
5. Navngi jar filen under test, vi finner et nytt navn etterhvert som en final. Følg også nøye med på hvor outputten er etter at builden er ferdig.
6. Når du har navngitt jar filen, gå til "Build" --> Build Artifacts
7. Klikk på jar filen du nettop laget
8. Klikk på Build og nå har vi en .jar fil.

