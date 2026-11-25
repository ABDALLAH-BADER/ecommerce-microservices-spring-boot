# E-Commerce Microservices Backend

A scalable E-Commerce backend application built using Java, Spring Boot, and Microservices Architecture.

The project demonstrates how to design and implement distributed backend services with independent responsibilities, inter-service communication, centralized configuration, service discovery, API Gateway, authentication, and event-driven communication.

## Architecture Overview

The application is divided into independent microservices, each responsible for a specific business capability.

### Microservices

| Service | Responsibility | Database |
|---|---|---|
| Customer Service | Customer management | MongoDB |
| Product Service | Product catalog and inventory management | PostgreSQL |
| Order Service | Order creation and order management | PostgreSQL |
| Payment Service | Payment record management | PostgreSQL |
| Notification Service | Notifications and email delivery | MongoDB |

### Infrastructure Services

| Component | Responsibility |
|---|---|
| API Gateway | Centralized entry point and request routing |
| Eureka Discovery Server | Service registration and discovery |
| Config Server | Centralized configuration management |
| Keycloak | Identity and access management |
| Apache Kafka | Asynchronous event-driven communication |
| Zipkin | Distributed tracing |

## Technology Stack

- Java
- Spring Boot
- Spring Cloud
- Spring Cloud Config
- Netflix Eureka
- Spring Cloud Gateway
- Spring Security
- Keycloak
- OAuth2
- JWT
- Spring Data JPA
- Spring Data MongoDB
- PostgreSQL
- MongoDB
- Apache Kafka
- OpenFeign
- RestTemplate
- Docker & Docker Compose
- JavaMailSender
- Thymeleaf
- Maven

## Security and Authentication

The project integrates Keycloak with Spring Cloud Gateway to handle authentication and secure incoming API requests.

Keycloak is used as the Identity and Access Management (IAM) solution.

Security implementation includes:

- Centralized authentication using Keycloak.
- OAuth2-based authentication.
- JWT access tokens.
- Token validation at the API Gateway.
- Securing API access through Spring Security.

## Inter-Service Communication

The microservices communicate using two communication approaches:

### 1. Synchronous Communication

REST APIs are used for direct service-to-service communication.

Technologies:
- OpenFeign
- RestTemplate

Examples:
- Order Service communicates with Customer Service.
- Order Service communicates with Product Service.
- Order Service communicates with Payment Service.

### 2. Asynchronous Communication

Apache Kafka is used for event-driven communication.

Main Kafka topics:
- order-topic
- payment-topic

The Order and Payment services publish events, while the Notification Service consumes them.

This approach decouples notification processing from the main business operations.

## Main Business Workflow

The order creation workflow follows these steps:

1. The client sends an order request through the API Gateway.
2. The API Gateway handles authentication and routes the request to the Order Service.
3. The Order Service validates the customer through the Customer Service.
4. The Order Service requests product availability and stock updates from the Product Service.
5. The Order Service saves the order and its order lines.
6. The Order Service sends a payment request to the Payment Service.
7. The Payment Service stores the payment record and publishes a payment event to Kafka.
8. The Order Service publishes an order confirmation event.
9. The Notification Service consumes the events.
10. Notifications are stored and confirmation emails are sent to customers.

## Notification and Email Service

The Notification Service handles order and payment notifications.

Features:
- Kafka event consumption.
- Notification persistence using MongoDB.
- Email delivery using Spring JavaMailSender.
- HTML email templates using Thymeleaf.
- Asynchronous email processing.

MailDev is used for local email testing.

## Database Architecture

The project uses both relational and NoSQL databases.

**PostgreSQL**
- Product Service
- Order Service
- Payment Service

**MongoDB**
- Customer Service
- Notification Service

Each service owns and manages its own business data.

## Docker Infrastructure

Docker Compose is used to run the supporting infrastructure required by the microservices.

Infrastructure components include:

- PostgreSQL
- MongoDB
- Kafka
- ZooKeeper
- pgAdmin
- Mongo Express
- MailDev

