## 📌 Beschrijving van het project
Dit project is ontwikkeld als onderdeel van mijn opleiding **Enterprise Java Developer**. Het doel was om een **volledig functionele blogbeheerapplicatie** te bouwen met een duidelijke architectuur en schaalbaarheid.

De applicatie biedt een **console-gebaseerde interface** waarmee beheerders gebruikers, posts, topics, likes en koppelingen kunnen beheren. Het project is ontworpen met **MVC-principes** om een duidelijke scheiding van verantwoordelijkheden te realiseren:

- **Model**: Data-objecten die de database representeren.
- **Repository**: Communicatie met MySQL via JDBC.
- **Service**: Businesslogica en foutafhandeling.
- **Controller**: Logica voor CRUD-operaties, herbruikbaar voor toekomstige web- of GUI-interfaces.
- **Menu (View)**: Console-interface voor gebruikersinteractie.
- **Utils**: Hulpmethodes voor invoer, weergave en menu's.

### 🔍 Waarom deze structuur?
Door controllers toe te voegen, is de applicatie voorbereid op uitbreiding naar:
- **Spring Boot REST API** (controllers worden webcontrollers)
- **JavaFX GUI** (controllers koppelen aan UI-acties)

### ✅ Belangrijkste kenmerken
- Volledige CRUD-functionaliteit voor alle entiteiten.
- Waarschuwingen bij verwijderen van gekoppelde data (Cascade).
- Herbruikbare controllers en services.
- Duidelijke scheiding van lagen (MVC).

### 🧠 Wat heb ik geleerd?
- Werken met JDBC en MySQL.
- Ontwerpen van een schaalbare applicatie.
- Toepassen van MVC in een console-app.
- Voorbereiden van code voor toekomstige uitbreidingen.

### 🔮 Toekomstige uitbreidingen
- Spring Boot REST API met endpoints voor alle entiteiten.
- JavaFX GUI voor een visuele interface.
- Wachtwoord hashing (BCrypt) voor beveiliging.
- Unit tests met JUnit.
- Exportfunctie naar CSV/JSON.