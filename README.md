# Projet Microservices Architecture

## 📄 Project Overview

L'application est basée sur l’architecture microservices de 2 services. Elle repose sur Spring Boot, Spring Cloud, Kafka, Keycloak et Docker, et met l’accent sur la scalabilité, la sécurité, et la communication asynchrone entre les microservices.

---

## 🛠️ Lancement du Projet

### ✅ Prérequis

- Docker & Docker Compose installés
- Token GitHub (pour les repos de config privés si besoin)

### 📂 Cloner le Repository

```bash
git clone https://github.com/omarChouikhEspritAL/Event_Prospect.git
cd Event_Prospect
```

### ⚡️ Démarrer les Services

```bash
docker-compose build
docker-compose up
```
Pour lancer en arrière-plan :

```bash
docker-compose up -d
```

### ❌ Arrêter les Services

```bash
docker-compose down
```

---

## ⏳ Ordre de Lancement        ___       🔍 Récapitulatif des Ports

| ** Ordre de Lancement Conseillé** | ** Récapitulatif des Ports**      |
|:-----------------------------------:|:------------------------------------|
| 1. Zookeeper                       | Zookeeper : `2181`                  |
| 2. Kafka                           | Kafka : `9092`, `29092`, `9999`     |
| 3. Eureka Server                   | Eureka Server : `8761`              |
| 4. Config Server                   | Config Server : `8889`              |
| 5. Keycloak                        | Keycloak : `8180`                   |
| 6. API Gateway                     | API Gateway : `8081`                |
| 7. Event Microservice              | Event Microservice : `8080`         |
| 8. Prospect Microservice           | Prospect Microservice : `9090`      |
| 9. Zipkin                          | Zipkin : `9411`                     |

---

## 🌐 Schéma d’Architecture du Projet (corrigé et conforme)

```
+-------------------+         +-----------------------+
| Config Server     |<------->| Git Repository        |
| Port: 8889        |         | Application YML files |
+-------------------+         +-----------------------+
        |
        v
+-------------------+
| Eureka Server     |
| Port: 8761        |
+-------------------+
        |
        v
+-------------------+          +-------------------+
|   API Gateway     |<-------->|    Keycloak       |
|    Port: 8081     |   OAuth2 |    Port: 8180     |
+-------------------+          +-------------------+
        |
        v
+-------------------+         +--------------------+
| Event Service     |<------->| Prospect Service   |
| Port: 8080        |         | Port: 9090         |
+-------------------+         +--------------------+
        \                         /
         \                       /
          +------>   Kafka   <---+
                 (9092,…)
        |
        v
+-------------------+
| Zipkin            |
| Port: 9411        |
+-------------------+
```
**Remarques :**
- Les utilisateurs ou applications client s’authentifient via Keycloak (génèrent un token).
- Toutes les requêtes passent par l’API Gateway, qui valide le token avec Keycloak.
- Les microservices communiquent entre eux via Kafka, mais l’accès principal passe par l’API Gateway.
- Zipkin reçoit des traces de tous les services.


---

## 🔑 Authentification & Sécurité avec Keycloak

- **Keycloak** est utilisé comme serveur d’authentification et d’autorisation centralisé.
- **API Gateway** est protégé par OAuth2, déléguant l’authentification à Keycloak.
- Chaque microservice valide le token JWT fourni par Keycloak via l’API Gateway.
- Pour accéder aux endpoints sécurisés, connectez-vous via l’interface Keycloak (`http://localhost:8180` par défaut).
- Vous pouvez configurer des utilisateurs, rôles et clients directement via l’admin Keycloak.
- Les configurations de sécurité des microservices sont centralisées dans leurs fichiers `application.yml` (voir dossiers `Ms_Event` et `Ms_Prospect`).

---

## 🔗 Vue d’ensemble des Microservices

| Service           | Description                          | Port   | Dépendances           |
|-------------------|--------------------------------------|--------|-----------------------|
| Config Server     | Gestion centralisée de la config     | 8889   | Git repo              |
| Eureka Server     | Découverte de service                | 8761   | -                     |
| API Gateway       | Point d’entrée unique                | 8081   | Eureka, Config, Keycloak |
| Event Service     | Gestion des événements (MySQL)       | 8080   | Eureka, Kafka, Keycloak  |
| Prospect Service  | Gestion des prospects (MongoDB)      | 9090   | Eureka, Kafka, Keycloak  |
| Kafka             | Broker de messages asynchrones       | 9092   | Zookeeper             |
| Zookeeper         | Dépendance Kafka                     | 2181   | -                     |
| Keycloak          | Gestion des identités et accès       | 8180   | -                     |
| Zipkin            | Tracing distribué                    | 9411   | -                     |

---

## ⚙️ Technologies Utilisées

- **Spring Cloud** : Eureka (découverte de services), Config Server (config centralisée), API Gateway (reverse proxy et sécurité)
- **Kafka** : Messaging asynchrone entre microservices
- **MongoDB** : Stockage des prospects
- **MySQL** : Stockage des événements
- **Keycloak** : Authentification et autorisation centralisées (OAuth2, OpenID Connect)
- **Zipkin** : Tracing distribué
- **Docker & Docker Compose** : Conteneurisation et orchestration

---

## ⚡️ Communication Asynchrone

- **Kafka topic** : `prospect-events`
- **Ms_Prospect** produit des événements lors de la création/mise à jour d’un prospect.
- **Ms_Event** consomme ces événements et crée les événements correspondants.

---

## 📅 Auteur

Développé par **Omar Chouikh**  
[GitHub Profile](https://github.com/omarChouikhEspritAL)
