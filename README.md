# Overview

This project is build from scratch to finish based on microservices architecture using Spring Boot, Spring Cloud and deploy to Docker, Kubernetes .

## Diagrams

![microservices-architecture](https://user-images.githubusercontent.com/86077654/189718029-3025550c-0d5c-47a5-b648-ee51b37142b0.png)

![microservices-deploy-k8s](https://user-images.githubusercontent.com/86077654/189718060-2ac43d6e-403c-43fa-b8b4-2d0f7bfabfe4.png)

# Idea
* Client send a request to `customer` to perform `orders` then I check `product` customer want to orders exist or not. If product exist then I save `orders` then I push a message i.e notification to Message Queue which `Notification service` can pull message to send email to customer.

* Same with orders is that when client send a request to `customer` to perform `payment` then I check `orders` user want to payment exist or not. If orders exist then I save `payment` then I push a message i.e notification to Message Queue which `Notification service` can pull message to send email to customer.

## What are microservices ?
`Microservices` are an architectural and organizational approach to software development where software is composed of small independent services that communicate over well-defined APIs. These services are owned by small, self-contained teams.

Microservices architectures make applications easier to scale and faster to develop, enabling innovation and accelerating time-to-market for new features.

## Why use microservices ?
With monolithic architectures, all processes are tightly coupled and run as a single service. This means that when application grows up it's more difficult to manage and deploy application.

With a microservices architecture, an application is built as independent components that run each application process as a service. Because they are independently run, each service can be updated, deployed, and scaled to meet demand for specific functions of an application.

## How to build microservices ?
In this project, I will explain all the step that I build. Beside that, I have a lot of branches to describe step by step corresponding to each branch. So, you can check out the code of each branch to follow along.

## Steps:
- [1. Setup parent module](#1-setup-parent-module)
- [2. Create microservices instances](#2-create-microservices-instances)
- [3. Microservices communication using RestTemplate](#3-microservices-communication-using-resttemplate)
- [4. Service Discovery](#4-service-discovery)
- [5. Microservices communication using OpenFeign](#5-microservices-communication-using-openfeign)
- [6. Distributed Tracing](#6-distributed-tracing)
- [7. API Gateway](#7-api-gateway)
- [8. Message Queue](#8-message-queue)
- [9. Package, run microservices with jar file](#9-package-run-microservices-with-jar-file)
- [10. Containerize microserivces, build, push docker image to local and DockerHub using Jib](#10-containerize-microservice-build-docker-image-local-dockerhub-jib)
- [11. Monitor microservices using Prometheus & Grafana](#11-monitor-microservices-using-prometheus-grafana)
- [12. Deploy microservices to local Kubernetes](#12-deploy-microserivces-to-local-kubernetes)
- [13. Deploy microservices to AWS EKS (Elastic Kubernetes Service)](#13-deploy-microservice-to-aws-eks)
- [14. Monitor kubernetes cluster using Prometheus Operator](#14-monitor-k8s-cluster-prometheus-operator)
- [15. CI/CD microserivces using Github Actions](#15-ci-cd-microserivces)

## 1. Setup parent module
I use `dependencyManagement`, `pluginManagement` to define parent `pom.xml`and from that all sub-module i.e microserivces can pick one of list dependencies or plugin in there `pom.xml`

## 2. Create microservices instances
To create microservices instances you need to create new sub-module then add some `dependency` and `plugin` that you want to your `pom.xml` and build each Spring Boot application normally i.e `Controller, Service, Repository`, etc.

## 3. Microservices communication using RestTemplate
After create microservices instances then I want all microservices communication between them i.e send `HTTP request`. I need to use `RestTemplate` to perform a request.

The `RestTemplate` is the central Spring class for client-side HTTP access.

## 4. Service Discovery
`The mechanism` for application & microservices  to locate each other on  a network i.e `host:port` to communication between them.

Spring Cloud provided `Eureka Server` & `Eureka Client` to perform Service Discovery.

![eureka-service-discovery](https://user-images.githubusercontent.com/86077654/190224026-36ee1d45-344b-4464-97fc-344d84a67703.png)

* Step 1: Microservices register `Eureka Server` as a `Client` i.e `Eureka Client`.
* Step 2: When microservices need to talk to another microservices, then they will look up to `Eureka Server` to know location i.e `host:port`.
* Step 3: Microservices can connect with each other to perform HTTP request.

Look at the diagram below to understand how service discovery work which is provided by `Spring Cloud Netflix Eureka`.

![describe-services-discovery](https://user-images.githubusercontent.com/86077654/190223973-89e9b6fb-6170-43b3-b611-d31c60e6e093.png)

## 5. Microservices communication using OpenFeign
* `Spring Cloud OpenFeign` — a declarative REST client for Spring Boot app.
* Declarative REST Client: `Feign` is a declarative web service that creates a dynamic implementation of an `interface` decorated with JAX-RS or `Spring MVC annotations`.
* Spring Cloud integrates `Eureka`, `Spring Cloud CircuitBreaker`, as well as `Spring Cloud LoadBalancer` to provide a load-balanced http client when using `Feign`.

## 6. Distributed Tracing
In microserivces architecture, microserivces talk to another microserivces via HTTP request. We need to know exactly the entire flow of request go though microservices. To do so, we need to use `Sleuth` and `Zipkin`.
* `Spring Cloud Sleuth`: Spring Cloud Sleuth provides API for distributed tracing solution for Spring Cloud. Spring Cloud Sleuth is able to trace your requests and messages so that you can correlate that communication to corresponding log entries.
    * Spring Cloud Sleuth adds `TraceID` and `SpanID` to the Slf4J MDC, so you can extract all the logs from a given trace or span in a log aggregator when a request come in. The following image shows how Span and Trace look in a system.
      ![sleith-diagram](https://user-images.githubusercontent.com/86077654/190224518-462b19d4-2931-4b07-a438-1d3504a906e6.png)
* `Zipkin`: Zipkin is a distributed tracing system. It helps gather timing data needed to troubleshoot latency problems in service architectures. If you have a `trace ID` in a log file, you can jump directly to it.

If `spring-cloud-sleuth-zipkin` is available and add `spring.zipkin.baseUrl` in spring profile then the app will generate and report Zipkin-compatible traces via HTTP.

## 7. API Gateway
`Spring Cloud Gateway` as know `API Gateway` or `Load Balancer` is a service that allows you to route traffic requests to different microservices though the endpoint.

When a client send a request. The request go though the Load Balancer and the Load Blanacer will redirect the request to microserivces.

The following diagram shows how a request route based on the `path` from Load Balancer to microservices in a system.

![load-balancer-api-gateway](https://user-images.githubusercontent.com/86077654/190224032-afd8d032-0444-40b2-8edd-580d827f0f98.png)

## 8. Message Queue
`Message queue` is a form of asynchronous service-to-service communication used in serverless and microservices architectures.

In this project I used `RabbitMQ` to perform message queue.

`RabbitMQ` is the most widely deployed open source message broker. It support multiple messaging `protocols`.

`AMQP 0-9-1` (Advanced Message Queuing Protocol) is a messaging `protocol` that enables conforming client applications to communicate with conforming messaging middleware `broker`.

`Broker` receive messages from publishers (applications that publish them, also known as producers) and route them to consumers (applications that process them)

When a messages are published from `producers` to `exchange`. The `Exchange` will distribute message to `queues` when `binding` the `exchange` to `queue` with `routing-key`. Then the broker either deliver messages to `consumers` subscribed to `queue`, or `consumers` fetch/pull messages from `queue` on demand.

The following diagram shows how a message route from `producer` to `consumer` though message `broker` in a system.

![message-queue-rabbitmq-workflow](https://user-images.githubusercontent.com/86077654/190224049-f2a08139-7f5d-4447-8793-0d7c745f6fa8.png)

## 9. Package, run microservices with jar file
