# Projet Microservices Architecture

## 📄 Project Overview

L'application est basée sur l’architecture microservices de 2 srevices. Elle repose sur Spring Boot, Spring Cloud, Kafka et Docker et met l’accent sur la scalabilité, la communication asynchrone, la configuration centralisée, la découverte de services et la sécurité.


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
| 5. Keycloak                        | API Gateway : `8081`                |
| 6. API Gateway                     | Event Microservice : `8080`         |
| 7. Event Microservice              | Prospect Microservice : `9090`      |
| 8. Prospect Microservice           | Keycloak : `8180`                   |
| 9. Zipkin                          | Zipkin : `9411`                     |


---

## 🌐 Schéma d’Architecture du Projet

```
+-------------------+         +-----------------------+
| Config Server     |<------->| Git Repository        |
| Port: 8889        |         | Application YML files |
+-------------------+         +-----------------------+
        |
        v
+-------------------+         +--------------------+
| Eureka Server     |<------->| API Gateway        |
| Port: 8761        |         | Port: 8081         |
+-------------------+         +--------------------+
        |                                |
        v                                v
+-------------------+         +--------------------+
| Event Service     |<------->| Prospect Service   |
| Port: 8080        |         | Port: 9090         |
+-------------------+         +--------------------+
        |                                |
        v                                v
+-------------------+         +--------------------+
| Kafka             |<------->| Keycloak           |
| Ports: 9092,...   |         | Port: 8180         |
+-------------------+         +--------------------+
        |
        v
+-------------------+
| Zipkin            |
| Port: 9411        |
+-------------------+
```


## 🔗 Vue d’ensemble des Microservices

| Service           | Description                          | Port   | Dépendances           |
|-------------------|--------------------------------------|--------|-----------------------|
| Config Server     | Gestion centralisée de la config     | 8889   | Git repo              |
| Eureka Server     | Découverte de service                | 8761   | -                     |
| API Gateway       | Point d’entrée unique                | 8081   | Eureka, Config        |
| Event Service     | Gestion des événements (MySQL)       | 8080   | Eureka, Kafka         |
| Prospect Service  | Gestion des prospects (MongoDB)      | 9090   | Eureka, Kafka         |
| Kafka            | Broker de messages asynchrones        | 9092   | Zookeeper             |
| Zookeeper         | Dépendance Kafka                     | 2181   | -                     |
| Keycloak          | Gestion des identités et accès       | 8180   | -                     |
| Zipkin            | Tracing distribué                    | 9411   | -                     |


---

## ⚙️ Technologies Utilisées

- **Spring Cloud** : Eureka (découverte de services), Config Server (config centralisée), API Gateway (reverse proxy)
- **Kafka** : Messaging asynchrone entre microservices
- **MongoDB** : Stockage des prospects
- **MySQL** : Stockage des événements
- **Keycloak** : Authentification et autorisation
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

