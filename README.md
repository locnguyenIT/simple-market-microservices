#Overview
This project is build from scratch to finish based on microservices architecture using Spring Boot, Spring Cloud.

## Diagrams

![](C:\Users\ntloc\OneDrive\Desktop\microservices-architecture.png)

![](C:\Users\ntloc\OneDrive\Desktop\microservices-deploy-k8s.png)

## What are microservices ?
Microservices are an architectural and organizational approach to software development where software is composed of small independent services that communicate over well-defined APIs. These services are owned by small, self-contained teams.

Microservices architectures make applications easier to scale and faster to develop, enabling innovation and accelerating time-to-market for new features.

## Why use microservices ?
With monolithic architectures, all processes are tightly coupled and run as a single service. This means that when application grows up it's more difficult to manage and deploy application.

With a microservices architecture, an application is built as independent components that run each application process as a service. Because they are independently run, each service can be updated, deployed, and scaled to meet demand for specific functions of an application.

## How to build microservices ?
In this project, I have a lot of branches to describe step by step and I will explain one by one for you to understand. 

# Step:
- [1. Setup parent module](#1-setup-parent-module)
- [2. Create microservices instances](#2-create-microservices-instances)
- [3. Microservices communation using RestTemplate](#3-microservices-communation-resttemplate)
- [4. Service Discovery](#4-service-discovery)
- [5. Microservices communation using OpenFeign](#5-microservices-communation-openfeign)
- [6. Distributed Tracing](#6-distributed-tracing)
- [7. API Gateway](#7-api-gateway)
- [8. Message Queue using RabbitMQ](#8-message-queue-rabbitmq)
- [9. Package, run microservices with jar file](#9-package-run-microservices-with-jar-file)
- [10. Containerize microserivces, build, push docker image to local and DockerHub using Jib](#10-containerize-microservice-build-docker-image-local-dockerhub-jib)
- [11. Monitor microservices using Prometheus & Grafana](#11-monitor-microservices-using-prometheus-grafana)
- [12. Deploy microservices to local Kubernetes](#12-deploy-microserivces-to-local-kubernetes)
- [13. Deploy microservices to AWS EKS (Elastic Kubernetes Service)](#13-deploy-microservice-to-aws-eks)
- [14. Monitor kubernetes cluster using Prometheus Operator](#14-monitor-k8s-cluster-prometheus-operator)
- [15. CI/CD microserivces using Github Actions](#15-ci-cd-microserivces)


