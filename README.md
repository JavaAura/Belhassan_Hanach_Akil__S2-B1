# Gestion-Projet-Collaboratif

## Description du projet
Gestion-Projet-Collaboratif est une application web Java EE conçue pour faciliter la gestion de projets en équipe. Elle permet aux utilisateurs de collaborer efficacement, de suivre l'avancement des tâches et de gérer les ressources du projet.

## Objectif général de l'application
L'objectif principal est d'offrir une plateforme centralisée pour la gestion de projets, améliorant la communication entre les membres de l'équipe et optimisant le suivi des tâches et des délais.

## Technologies utilisées
- **Backend**: Java EE
- **Frontend**: HTML, CSS, JavaScript, JSP
- **Base de données**: MySQL
- **Serveur d'application**: Apache Tomcat
- **Frameworks**: Bootstrap 4.5.2
- **Autres**: JSTL, Font Awesome

## Structure du projet
Le projet est organisé selon une architecture MVC (Modèle-Vue-Contrôleur) :
+ src/main/java: Contient les classes Java (modèles, servlets, services)
+ src/main/webapp: Contient les fichiers web (JSP, CSS, JavaScript)
## Fonctionnalités principales
- Gestion des projets (création, modification, suppression, visualisation)
- Gestion des tâches (ajout, édition, liste par projet)
- Gestion des équipes
- Statistiques des projets

## Démarrage rapide

### Prérequis
- JDK 8 ou supérieur
- Apache Tomcat 9.0 ou supérieur
- MySQL 8.0 ou supérieur
- Maven (pour la gestion des dépendances)

### Configuration de la base de données
1. Créez une nouvelle base de données MySQL nommée `projectmanagement`.
2. Exécutez le script SQL fourni dans `database/init.sql` pour créer les tables nécessaires.

### Lancement de l'application
#### 1. Clonez le dépôt :
   ```bash
   git clone https://github.com/JavaAura/Belhassan_Hanach_Akil__S2-B1.git
   ```

#### 2. Naviguez vers le répertoire du projet :
```bash
cd Belhassan_Hanach_Akil__S2-B1
   ```
#### 3. Compilez le projet :
```bash
mvn clean install
```
Déployez le fichier WAR généré sur votre serveur Tomcat.
##### Accédez à l'application via 
http://localhost:8080/Belhassan_Hanach_Akil__S2-B1


### Guide d'utilisation rapide
- Page d'accueil : Choisissez entre la gestion des équipes, des projets ou des tâches.
- Gestion des projets : Créez, modifiez, supprimez et visualisez les projets.
- Gestion des tâches : Ajoutez et modifiez des tâches pour chaque projet.
- Gestion des équipes : Créez, modifiez, supprimez et visualisez les équipes et les membres de l'équipe.

## Capture d'écrans 
#### PAGE PROJET
###### Liste des projets
<img width="959" alt="addPage" src="https://github.com/user-attachments/assets/afbb8b6e-134d-4d85-ad7d-a7bf21779fcb">

###### Ajouter projet
![aaa](https://github.com/user-attachments/assets/92add13c-2cf7-4427-8c5f-b0d04c04b0c1)

#### PAGE EQUIPE
###### Liste des equipes
![Screenshot from 2024-10-06 00-27-13](https://github.com/user-attachments/assets/b920230b-27e5-4ee5-af94-df5ee7242240)

###### Ajouter equipe
![Screenshot from 2024-10-06 00-55-34](https://github.com/user-attachments/assets/d30b6cdd-d88d-4173-82a3-3b96f64c8071)


###### Listes des membres dans l'equipe 
![Screenshot from 2024-10-06 00-27-33](https://github.com/user-attachments/assets/1c9f1881-27de-4737-800b-9001a3b84303)


#### PAGE MEMBRE
###### Liste des membres
![Screenshot from 2024-10-06 00-26-41](https://github.com/user-attachments/assets/98088d89-d7d2-42c0-9a06-5441475f0462)
###### Ajouter membre
![Screenshot from 2024-10-06 00-27-00](https://github.com/user-attachments/assets/bb9bfc16-b0b3-4fa2-aaf9-eb922b3efe44)


## Configuration de la base de données

Pour configurer la base de données pour l'application Gestion-Projet-Collaboratif, exécutez le script SQL suivant :

```sql
CREATE DATABASE GestionDeProjet;
USE GestionDeProjet;

CREATE TABLE projet (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(30),
    description VARCHAR(255),
    dateDebut DATE,
    dateFin DATE,
    etatProjet ENUM('en_preparation','en_cours','en_pause','termine','annule')
);

CREATE TABLE equipe (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(30)
);

CREATE TABLE membre (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(40),
    prenom VARCHAR(40),
    email VARCHAR(50) UNIQUE NOT NULL,
    role ENUM('ChefDeProjet','Developpeur','Designer'),
    equipeId INT,
    CONSTRAINT fk_equipe FOREIGN KEY (equipeId) REFERENCES equipe(id)
);

CREATE TABLE tache (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titre VARCHAR(30),
    description VARCHAR(255),
    dateCreation DATE,
    dateEcheance DATE,
    priorite ENUM('Basse','Moyenne','Haute'),
    statut ENUM('A_faire','En_cours','Termine'),
    membreId INT,
    projetId INT,
    CONSTRAINT fk_membre FOREIGN KEY (membreId) REFERENCES membre(id),
    CONSTRAINT fk_projet FOREIGN KEY (projetId) REFERENCES projet(id)
);

CREATE INDEX idx_nom_projet ON projet(nom);
CREATE INDEX idx_nom_equipe ON equipe(nom);
CREATE INDEX idx_email_membre ON membre(email);
CREATE INDEX idx_statut_tache ON tache(titre);

CREATE USER 'Akil'@'localhost' IDENTIFIED BY 'password';
```

### Auteurs et contact
Ce projet a été développé par une équipe d'étudiants :

- [Anouar](https://github.com/BelAnouar)
- [Salah Eddine](https://github.com/AkilSalah)
- [Yassine](https://github.com/Yassinean)
