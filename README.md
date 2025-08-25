# RecensementJava
# 📊 Application de Recensement en Java (Swing + MySQL)

## 📌 Description
Cette application permet de gérer un **recensement de population** par zones géographiques.  
Elle offre une interface graphique en **Java Swing** et stocke les données dans une **base MySQL**.  

L’objectif est de recenser :
- Les **zones géographiques** (villages, quartiers…)
- Les **familles** rattachées à une zone
- Les **citoyens** appartenant à une famille
- Les campagnes de **recensement** incluant les statistiques démographiques et socio-économiques calculées automatiquement.

---

## ⚙️ Fonctionnalités

- ➕ Ajouter une **Zone géographique**
- ➕ Ajouter une **Famille**
- ➕ Ajouter un **Citoyen**
- 📊 Lancer un **recensement** pour une zone :
  - Population totale
  - Répartition par âge (enfants, adultes, personnes âgées)
  - Nombre d’actifs et de chômeurs
- 📑 Visualiser les données enregistrées
- 🎛️ Interface graphique intuitive avec **Java Swing**

---

## 🏗️ Architecture du projet

RecensementJava/
│── src/app/
│ ├── ConnexionBDD.java # Connexion MySQL
│ ├── ZoneGeographique.java # Modèle Zone
│ ├── Famille.java # Modèle Famille
│ ├── Citoyen.java # Modèle Citoyen
│ ├── Recensement.java # Modèle Recensement
│ ├── StatistiqueRecensement.java # Calculs automatiques
│ ├── ZoneForm.java # Interface d'ajout Zone
│ ├── FamilleFormMySQL.java # Interface d'ajout Famille
│ ├── CitoyenFormPanel.java # Interface d'ajout Citoyen
│ ├── RecensementForm.java # Interface pour lancer le recensement
│ ├── MenuPrincipal.java # Fenêtre principale (menu)
│── lib/
│ └── mysql-connector-java-8.3.0.jar
│── recensement.sql # Script SQL de la base
🚀 Installation et exécution
1️⃣ Prérequis

Java 17+

MySQL 8+

MySQL Connector/J (inclus dans lib/)

Swing (JDK standard)

2️⃣ Compiler le projet
cd RecensementJava
javac -cp "lib/mysql-connector-java-8.3.0.jar" -d out src/app/*.java
3️⃣ Lancer l’application
java -cp "out:lib/*" app.MenuPrincipal

🎨 Interface graphique

L’interface propose un menu principal avec plusieurs boutons :

Ajouter une Zone → ouvre ZoneForm

Ajouter une Famille → ouvre FamilleFormMySQL

Ajouter un Citoyen → ouvre CitoyenFormPanel

Calculer Recensement → ouvre RecensementForm

👨‍💻 Auteur

Nonguierma Romaric

Projet académique / personnel en Java & MySQL
