# NEXUS — Enterprise Support & AI Automation Platform

## 1. Project Overview

NEXUS is an enterprise-style Support Ticket Management and AI Automation Platform designed to manage customer support operations through a secure backend, intelligent AI agents, multi-agent orchestration, and production-grade AI infrastructure.

The platform will allow customers to create and track support tickets, support agents to manage and resolve customer issues, and administrators to manage users, tickets, assignments, and analytics.

The long-term objective is to evolve NEXUS from a traditional backend application into a secure, AI-powered enterprise support platform.

## 2. Core Objectives

- Build a secure and scalable backend using Java and Spring Boot.
- Implement REST APIs and PostgreSQL database integration.
- Introduce authentication, authorization, and role-based access control.
- Build AI agents capable of interacting with the support platform.
- Implement RAG over support documentation and historical tickets.
- Develop a multi-agent architecture using A2A communication.
- Establish production-grade AI infrastructure, observability, evaluation, and deployment workflows.
- Treat security as a fundamental part of every development phase.

## 3. Platform User Roles

### Customer
- Register and log in.
- Create support tickets.
- View and track their tickets.
- Add comments to tickets.
- Receive status updates.

### Support Agent
- View assigned tickets.
- Reply to customers.
- Update ticket priority and status.
- Resolve support issues.

### Administrator
- View all tickets.
- Manage customers and support agents.
- Assign tickets to agents.
- Access platform analytics.

## 4. Technology Stack

### Backend
- Java 21
- Spring Boot
- Spring Web
- Spring Security
- Spring Data JPA
- Hibernate
- Maven
- PostgreSQL
- JWT / OAuth2
- Validation

### AI and Agent Development
- Python
- FastAPI
- Ollama
- Google ADK
- MCP
- A2A
- LangGraph
- RAG
- pgvector

### Infrastructure and DevOps
- Docker
- Docker Compose
- Redis
- Apache Kafka or RabbitMQ
- Keycloak
- LiteLLM or OmniRoute
- Gitea or GitLab CE
- Prometheus
- Grafana

### Observability and Evaluation
- Langfuse
- OpenTelemetry
- Ragas
- DeepEval
- promptfoo

### Security
- OpenBao
- Trivy
- Semgrep
- OWASP ZAP
- Garak
- PyRIT
- NeMo Guardrails or Llama Guard

### Frontend
- React
- TypeScript
- Vite
- Tailwind CSS
- shadcn/ui

## 5. Development Roadmap

# PHASE 1 — Secure Backend Foundation

**Layer 1: Traditional Backend Engineering and Security Fundamentals**

### Objective

Build a secure Support Ticket Management Platform using Java, Spring Boot, and PostgreSQL.

### Core Development

1. Develop Spring Boot REST APIs.
2. Implement PostgreSQL integration using Spring Data JPA and Hibernate.
3. Build the Ticket Management module:
   - Create tickets.
   - Retrieve tickets.
   - Update ticket details.
   - Delete tickets.
   - Add comments.
   - Track ticket status.
4. Implement user management and role-based functionality.
5. Integrate Redis for caching and rate limiting.
6. Introduce Kafka or RabbitMQ for asynchronous events such as `ticket.created`.
7. Integrate Keycloak for authentication and authorization.
8. Implement OAuth2/JWT and RBAC for CUSTOMER, AGENT, and ADMIN.
9. Dockerize the application and its dependencies using Docker Compose.
10. Write JUnit tests and Testcontainers tests against real PostgreSQL and Redis instances.

### Security Requirements

- Validate all incoming user input.
- Store secrets in environment variables or OpenBao.
- Enable TLS.
- Scan container images using Trivy.
- Scan source code using Semgrep.
- Fix identified vulnerabilities.
- Test APIs against the OWASP Top 10 using OWASP ZAP.

### Learning Outcomes

- Java backend development.
- Spring Boot architecture.
- REST API development.
- JPA and Hibernate.
- PostgreSQL.
- Redis and messaging systems.
- Authentication and authorization.
- Docker and containerization.
- Automated testing.
- Secure backend engineering.

---

# PHASE 2 — Single Agent with Tools and RAG

**Layer 2: AI Engineering and Agent Development**

### Objective

Build a Python-based AI service capable of answering questions about support tickets and the knowledge base.

### Core Development

1. Develop an AI service using Python and FastAPI.
2. Run a local language model using Ollama.
3. Build an agent using Google ADK.
4. Expose Spring Boot APIs as tools through an MCP server.
5. Implement tools that allow the agent to:
   - Look up tickets.
   - Retrieve ticket information.
   - Update tickets when authorized.
6. Implement RAG over help articles and historical support tickets using pgvector.
7. Integrate the AI agent with the existing NEXUS backend.
8. Pass the user's JWT to Spring Boot when the agent invokes tools.

### Security Requirements

- Follow the principle of least privilege.
- Keep tools read-only by default.
- Require human approval before an agent modifies or closes tickets.
- Ensure the agent never receives more access than the authenticated user.
- Test prompt injection by placing malicious instructions inside ticket comments and verifying that the agent ignores them.

### Learning Outcomes

- AI agents and tool calling.
- Google ADK.
- MCP architecture.
- Retrieval-Augmented Generation.
- Vector databases.
- Agent-to-backend integration.
- Permission-aware AI systems.
- AI security fundamentals.

---

# PHASE 3 — Multi-Agent System with A2A

**Layer 2: Advanced Multi-Agent Engineering**

### Objective

Transform the single-agent system into a coordinated multi-agent architecture in which specialized agents collaborate to process support tickets.

### Agent Architecture

**1. Triage Agent**
- Classifies incoming tickets.
- Determines ticket categories and priorities.
- Routes tickets to appropriate workflows.

**2. Research Agent**
- Searches the knowledge base.
- Retrieves relevant help articles and historical ticket information.
- Provides useful context.

**3. Resolution Agent**
- Drafts responses to customer issues.
- Uses information provided by the research agent.

**4. Supervisor Agent**
- Coordinates specialized agents.
- Manages the workflow.
- Oversees the overall task execution.

### Core Development

1. Implement specialized agents.
2. Introduce A2A communication between agents.
3. Compare the same workflow using Google ADK and LangGraph.
4. Integrate Kafka to trigger workflows when `ticket.created` events arrive.
5. Implement agent coordination and task handoffs.
6. Maintain an audit trail of agent actions.

### Security Requirements

- Give each agent its own identity.
- Apply limited permissions to each agent.
- Implement service-to-service authentication using mTLS or signed tokens.
- Log every agent action for auditing.
- Prevent agents from accessing resources beyond their permissions.

### Learning Outcomes

- Multi-agent orchestration.
- A2A communication.
- Agent specialization.
- Event-driven architectures.
- Kafka integration.
- Google ADK versus LangGraph.
- Secure service-to-service communication.
- Agent auditability.

---

# PHASE 4 — AI Infrastructure Layer

**Layer 3: Production-Grade AI Infrastructure**

### Objective

Make the entire NEXUS platform production-ready by introducing centralized model management, agent memory, observability, evaluation, security testing, and deployment automation.

### Core Development

**1. Model Gateway**

- Introduce LiteLLM or OmniRoute.
- Centralize model calls.
- Implement model routing and fallbacks.
- Configure per-agent budgets and API keys.

**2. Agent Memory**

- Redis for short-term conversation state.
- Mem0 or Letta for long-term memory.

**3. Observability**

- Integrate Langfuse.
- Implement OpenTelemetry tracing.
- Track agent execution steps, token consumption, and latency.

**4. Evaluation**

- Implement Ragas, DeepEval, and promptfoo.
- Evaluate answer quality, groundedness, and tool-call accuracy.
- Integrate evaluation into CI workflows.

**5. CI/CD**

- Set up Gitea or GitLab CE.
- Automate tests, evaluations, and security scans.
- Establish deployment pipelines.

**6. Monitoring**

- Integrate Prometheus.
- Build Grafana dashboards.
- Monitor application and AI system performance.

### Security Requirements

- Perform red-team testing using Garak and PyRIT.
- Introduce NeMo Guardrails or Llama Guard.
- Filter inputs and outputs.
- Redact personal data before sending information to logs.
- Rotate model API keys through the gateway.
- Maintain secure model and agent access controls.

### Learning Outcomes

- LLM gateways.
- Model routing and cost management.
- Short-term and long-term agent memory.
- AI observability.
- Evaluation frameworks.
- CI/CD.
- Monitoring and alerting.
- AI security and red-teaming.
- Production AI infrastructure.

---

## 6. Git Workflow

The project follows a simplified Git Flow.

- `main`: Stable, production-ready code.
- `develop`: Integration and development branch.
- `feature/*`: Individual feature branches.

Completed features will be tested before merging into the development branch.

## 7. Overall Development Philosophy

NEXUS will be developed incrementally across four phases.

The focus will be on understanding and implementing each technology rather than introducing advanced tools prematurely.

The development priorities are:

1. Build a reliable traditional backend.
2. Establish security fundamentals.
3. Introduce AI agents and RAG.
4. Develop multi-agent orchestration.
5. Build production-grade AI infrastructure.

**NEXUS is intended to demonstrate the complete engineering journey from traditional backend development to secure, production-ready AI systems.**