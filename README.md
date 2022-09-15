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
- [10. Containerize microservices, build, push docker image to local and DockerHub using Jib](#10-containerize-microservices-build-push-docker-image-to-local-and-dockerhub-using-jib)
- [11. Monitor microservices using Prometheus and Grafana](#11-monitor-microservices-using-prometheus-and-grafana)
- [12. Deploy microservices to local Kubernetes](#12-deploy-microservices-to-local-kubernetes)
- [13. Deploy microservices to AWS EKS (Elastic Kubernetes Service)](#13-deploy-microservice-to-aws-eks)
- [14. Monitor kubernetes cluster using Prometheus Operator](#14-monitor-k8s-cluster-prometheus-operator)
- [15. CI/CD microservices using Github Actions](#15-ci-cd-microservices)

## 1. Setup parent module
To set up parent module i.e  `pom.xml`, we need to add `dependencyManagement`, `pluginManagement` and from that all sub-module i.e microserivces can pick one of list dependencies or plugin in there `pom.xml`

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
* Step 2: When microservices need to talk to another microservices, then they will look up to `Eureka Server` to know the location i.e `host:port`.
* Step 3: Microservices can connect with each other just use service name to perform `HTTP request` .

Look at the diagram below to understand how service discovery work which is provided by `Spring Cloud Netflix Eureka`.

![describe-services-discovery](https://user-images.githubusercontent.com/86077654/190223973-89e9b6fb-6170-43b3-b611-d31c60e6e093.png)

## 5. Microservices communication using OpenFeign

A better way to communication between microservices is that using `OpenFeign` instead using `RestTemplate`.

`Spring Cloud OpenFeign` — a declarative `REST client` for Spring Boot app.

Declarative REST Client: `Feign` is a declarative `web service` that creates a dynamic implementation of an `interface` decorated with JAX-RS or `Spring MVC annotations`.

Spring Cloud integrates `Eureka`, `Spring Cloud CircuitBreaker`, as well as `Spring Cloud LoadBalancer` to provide a load-balanced http client when using `Feign`.

## 6. Distributed Tracing
In microserivces architecture, microserivces talk to another microserivces via HTTP request. We need to know exactly the entire flow of request go though microservices. To do so, we need to use `Sleuth` and `Zipkin`.
* `Spring Cloud Sleuth`: Spring Cloud Sleuth provides API for distributed tracing solution for Spring Cloud. Spring Cloud Sleuth is able to trace your requests and messages so that you can correlate that communication to corresponding log entries.
    * Spring Cloud Sleuth adds `TraceID` and `SpanID` to the Slf4J MDC, so you can extract all the logs from a given trace or span in a log aggregator when a request come in. The following image shows how Span and Trace look in a system.
      ![sleith-diagram](https://user-images.githubusercontent.com/86077654/190224518-462b19d4-2931-4b07-a438-1d3504a906e6.png)

* `Zipkin`: Zipkin is a distributed tracing system. It helps gather timing data needed to troubleshoot latency problems in service architectures. If you have a `trace ID` in a log file, you can jump directly to it.

If `spring-cloud-sleuth-zipkin` `dependency` is available and add `spring.zipkin.baseUrl` in `spring profile` then the app will generate and report Zipkin-compatible traces via HTTP.

The following Zipkin UI shows the flow of the request with TraceID when client request to the system.

![sleuth-zipkin-workflow](https://user-images.githubusercontent.com/86077654/190493883-5d059f2d-0d89-45de-98a7-706cb5eeae63.png)

![zipkin-ui-dependency](https://user-images.githubusercontent.com/86077654/190493633-4e065f08-1f96-4758-8256-117ea69f2bb4.png)

## 7. API Gateway
`Spring Cloud Gateway` as know `API Gateway` or `Load Balancer` is a service that allows you to route traffic requests to different microservices though the endpoint.

When a client send a request. The request go though the Load Balancer and the Load Blanacer will redirect the request to microserivces.

The following diagram shows how a request route based on the `path` from Load Balancer to microservices in a system.

![load-balancer-api-gateway](https://user-images.githubusercontent.com/86077654/190224032-afd8d032-0444-40b2-8edd-580d827f0f98.png)

## 8. Message Queue
`Message queue` is a form of asynchronous service-to-service communication used in serverless and microservices architectures.

In this project I used `RabbitMQ` to perform message queue.

Benefit of `RabbitMQ`:

* Loose coupling
* Performance
* Asynchronous
* Language Agnostic
* Acknowledge
* Management UI
* Plugin
* Cloud

`RabbitMQ` is the most widely deployed open source message broker. It support multiple messaging `protocols`.

`AMQP 0-9-1` (Advanced Message Queuing Protocol) is a messaging `protocol` that enables conforming client applications to communicate with conforming messaging middleware `broker`.

`Broker` receive messages from publishers (applications that publish them, also known as producers) and route them to consumers (applications that process them)

When a messages are published from `producers` to `exchange`. The `Exchange` will distribute message to `queues` when `binding` the `exchange` to `queue` with `routing-key`. Then the broker either deliver messages to `consumers` subscribed to `queue`, or `consumers` fetch/pull messages from `queue` on demand.

The following diagram shows how a message route from `producer` to `consumer` though message `broker` in a system.

![message-queue-rabbitmq-workflow](https://user-images.githubusercontent.com/86077654/190224049-f2a08139-7f5d-4447-8793-0d7c745f6fa8.png)

## 9. Package, run microservices with jar file

To package and run microservices with jar file, you need to add 2 things in your child `pom.xml`.

* `<packaging>jar</packaging>`: To package project as `jar` file. A `jar` is a package file format typically used to aggregate many Java class files and associated metadata and resources into one file for distribution

* `spring-boot-maven-plugin` : It allows you to package executable `jar` and `war` archives to run Spring Boot application.

Most important is that you also need to define `spring-boot-maven-plugin` with the `goal` of `executions` is `repackage` in parent `pom.xml`, from that microservices can reference to parent `pom.xml` to repackage existing `jar` and `war` archives after running `mvn clean package` or `mvn clean install` command so that they can be executed from the command line using `java -jar`.

## 10. Containerize microservices, build, push docker image to local and DockerHub using Jib

![jib](https://user-images.githubusercontent.com/86077654/190485763-6a6e5f1a-47fc-4cca-a1df-1e0a13772b38.png)

`Jib` containerize your `Maven` or `Gradle` project to builds optimized `Docker` and `OCI` images for your Java applications without using a `Dockerfile` or requiring a Docker installation and without  deep mastery of Docker best-practices.

In your Maven Java project, add the `jib-maven-plugin` to your `pom.xml`.

You can check out the [github link](https://github.com/GoogleContainerTools/jib/blob/master/README.md) and [google link](https://cloud.google.com/java/getting-started/jib) to know more information and how to implement it.

In my case, because I need to push the image to `local` and to `DockerHub`. So, I created a `Maven Profile` in `pom.xml` to fully control when I want to executed and where I want to store the image.

After you have an `image` of each microservices. You can deploy your microservices to `Docker` running as a `container` with `docker-compose.yml`file.

## 11. Monitor microservices using Prometheus and Grafana

Because our microservices run as an `independent service`. So, we need to check i.e, monitor how many resources microservices are using such as `CPU`, `Memory`, `Disk I/O`, etc. All the `information`/`metrics` can be exposed and collected by `Prometheus` and visualize the data in `Grafana`.

* `Prometheus`: Open-source systems monitoring. It collects and stores its metrics as time series data, i.e. metrics information is stored with the timestamp at which it was recorded. It used `PromQL` that lets the user select and aggregate time series data in real time and the result can either be shown as a `graph`.

* `Grafana`: Open-source system enables you to query, visualize, alert on, and explore your metrics, logs, and traces wherever they are stored.

The following diagram shows how `Prometheus` and `Grafana` work together to monitor microservices.

![promehteus-grafana-workflow](https://user-images.githubusercontent.com/86077654/190485880-9900abc1-6258-48df-8a6e-2a6e902f5390.png)

First, we need to add a `library` to expose all the metrics to `Prometheus` and that is `Micrometer`. In a Spring Boot application, a Prometheus `actuator` endpoint is `auto-configured` in the presence of `Spring Boot Actuator`.

* `Micrometer` is a `library` for `Java` that allows you to capture metrics and expose them to several different tools such as `Prometheus`.

* `Spring Boot Actuator` is mainly used to expose operational information about the running application such as health, metrics, info, dump, etc though `HTTP endpoints`.

Then, `Prometheus` use `prometheus.yml` file to know what microservices are available based on `target` to `scrape/pull` all the metrics.

![prometheus-target](https://user-images.githubusercontent.com/86077654/190486208-ac4f2502-3181-45dd-bd24-b7ab391c7e4e.png)

![prometheus-service-discovery](https://user-images.githubusercontent.com/86077654/190485917-de061dea-c6ab-4e8c-b9e8-db73476be34d.png)

![prometheus-table](https://user-images.githubusercontent.com/86077654/190486606-9bb2a799-938a-470c-8b23-34c0b2dadac4.png)

![prometheus-gragh](https://user-images.githubusercontent.com/86077654/190485908-1d640988-77e4-47a7-a35a-d93e6a63c18b.png)

After all the metrics are store in `Prometheus`, `Grafana` will pick up `Prometheus` as a `datasource` and create a series of dashboards to visualize the metrics.

![grafana-datasources](https://user-images.githubusercontent.com/86077654/190490015-8d7abfd5-29c4-47cc-bbce-bd4b516f1975.png)

![grafana-jvm-1](https://user-images.githubusercontent.com/86077654/190485966-0492a59f-1981-4002-a7f2-4dba1db7cf0c.png)

![grafana-jvm-2](https://user-images.githubusercontent.com/86077654/190485950-4ac41d62-ad30-429c-aba2-fe612f3ca146.png)

One thing I explore is that we can also pick `Zipkin` as a `datasource` to see the flow of the request. So, we won't need to open Zipkin UI anymore if you want.

![grafana-zipkin](https://user-images.githubusercontent.com/86077654/190490614-79448886-6d64-4924-a33a-449a3f2d1928.png)

![grafana-zipkin-trace](https://user-images.githubusercontent.com/86077654/190491543-23d0c344-5ba9-41f6-9fed-edeb75b32b48.png)

![grafana-zipkin-trace-request](https://user-images.githubusercontent.com/86077654/190491569-68fa9b4a-8e38-4c13-bce4-2b6fa1c8b2c4.png)

## 12. Deploy microservices to local Kubernetes














