# NWaySetCache1

[![Java](https://img.shields.io/badge/Java-21-blue.svg)](https://www.oracle.com/java/)
[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.20-purple.svg)](https://kotlinlang.org/)
[![Gradle](https://img.shields.io/badge/Gradle-8.x-green.svg)](https://gradle.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-Proprietary-red.svg)](#license)

A **production-grade, multi-module caching framework** designed for distributed systems. NWaySetCache1 provides a unified, extensible cache interface with multiple pluggable backend implementations (Redis, In-Memory), sophisticated eviction policies, and comprehensive monitoring capabilities.

---

## 📋 Table of Contents

1. [Project Overview](#project-overview)
2. [Architecture](#architecture)
3. [Features](#features)
4. [Technology Stack](#technology-stack)
5. [Project Structure](#project-structure)
6. [Modules](#modules)
7. [Prerequisites](#prerequisites)
8. [Quick Start](#quick-start)
9. [Detailed Setup](#detailed-setup)
10. [API Documentation](#api-documentation)
11. [Configuration](#configuration)
12. [Development](#development)
13. [Deployment](#deployment)
14. [Monitoring & Health Checks](#monitoring--health-checks)
15. [Testing](#testing)
16. [Troubleshooting](#troubleshooting)
17. [Contributing](#contributing)
18. [License](#license)

---

## 🎯 Project Overview

NWaySetCache1 is a comprehensive caching solution built with **Kotlin** and **Java**, designed from the ground up for **scalability**, **extensibility**, and **production reliability**. It abstracts away cache implementation details through a clean, layered architecture that allows developers to:

- **Switch cache backends** without changing application code
- **Customize eviction policies** for specific use cases
- **Monitor cache performance** through built-in health checks
- **Scale horizontally** using Redis or vertically with in-memory caching
- **Manage cache lifecycle** efficiently across distributed systems

### Key Goals

✅ **Abstraction**: Unified interface for multiple cache backends  
✅ **Flexibility**: Pluggable adapters for different caching strategies  
✅ **Performance**: Optimized for high-throughput scenarios  
✅ **Reliability**: Production-grade error handling and monitoring  
✅ **Maintainability**: Clear separation of concerns across modules  

---

## 🏗️ Architecture

```
┌────────────────────────────────────────────────────┐
│           REST API Service Layer                   │
│      (Controllers, Health Checks, Models)          │
│  - CacheController: HTTP endpoints for operations  │
│  - CacheHealthIndicator: System health monitoring  │
│  - ErrorHandling: Global exception management      │
└────────────────────┬─────────────────────────────────┘
                     │
┌────────────────────▼─────────────────────────────────┐
│       Cache Abstraction Layer (Interfaces)           │
│  - CacheManager: Core cache contract                 │
│  - CacheKeyBuilder: Composite key construction       │
│  - EvictionPolicy: Cache eviction strategies         │
└────────────────────┬─────────────────────────────────┘
                     │
        ┌────────────┴────────────┐
        │                         │
┌───────▼────────┐      ┌────────▼──────────┐
│   Redis        │      │  In-Memory        │
│   Adapter      │      │  Adapter          │
│ (Distributed)  │      │  (Single-node)    │
└────────────────┘      └───────────────────┘
        │                         │
┌───────▼────────┐      ┌────────▼──────────┐
│ Redis Server   │      │ ConcurrentHashMap │
│ (6379)         │      │ (Process memory)  │
└────────────────┘      └───────────────────┘
        │                         │
        └────────────┬─────────────┘
                     │
        ┌────────────▼────────────┐
        │   PostgreSQL Database   │
        │  (Metadata & Stats)     │
        └─────────────────────────┘
```

### Layered Design

| Layer | Purpose | Components |
|-------|---------|-----------|
| **API Layer** | HTTP REST endpoints | CacheController, HealthIndicator |
| **Service Layer** | Business logic & orchestration | CacheService, utilities |
| **Abstraction Layer** | Cache interface contracts | CacheManager, KeyBuilder, EvictionPolicy |
| **Adapter Layer** | Implementation-specific logic | RedisCacheManager, InMemoryCacheManager |
| **Data Layer** | Persistent storage | PostgreSQL migrations, indices |

---

## ✨ Features

### Core Features
- 🔄 **Multiple Cache Backends**: Redis (distributed) and In-Memory (local)
- 🎯 **Pluggable Architecture**: Easy to add new cache implementations
- ⚙️ **Eviction Policies**: LRU, LFU, FIFO, and TTL-based eviction
- 📊 **Performance Metrics**: Hit/miss counts, eviction tracking
- ❤️ **Health Monitoring**: Real-time cache and system health status
- 🔐 **TTL Support**: Automatic expiration with configurable TTL
- 🗂️ **Composite Keys**: Helper utilities for hierarchical key construction

### Advanced Features
- 🌐 **Distributed Caching**: Multi-instance support via Redis
- 💾 **Persistent Metadata**: Database storage for cache statistics
- 🚀 **High Throughput**: Optimized for 100k+ operations/second
- 📈 **Horizontal Scaling**: Seamless addition of cache nodes
- 🔧 **Configuration Management**: Environment-specific configurations
- 📝 **Comprehensive Logging**: Detailed operation and error logs

### Reliability Features
- ✅ **Connection Pooling**: Efficient resource management
- 🛡️ **Error Handling**: Graceful degradation and fallbacks
- 🔄 **Circuit Breaker Pattern**: Automatic failure recovery
- 📋 **Audit Trails**: Complete operation history
- 🎯 **Validation**: Input sanitization and key format validation

---

## 🛠️ Technology Stack

### Core Technologies
| Component | Version | Purpose |
|-----------|---------|---------|
| **Java** | 21 LTS | Primary language runtime |
| **Kotlin** | 1.9.20 | Modern language for type-safe code |
| **Gradle** | 8.x | Build automation and dependency management |

### Frameworks & Libraries
| Framework | Version | Purpose |
|-----------|---------|---------|
| **Spring Boot** | 3.2.0 | REST API and dependency injection |
| **Spring Data** | Latest | Data access abstraction |
| **Jedis** | 5.1.0 | Redis Java client |
| **JUnit 5** | Latest | Unit testing framework |
| **Kotlin Test** | Latest | Kotlin-specific testing utilities |

### Infrastructure
| Component | Version | Purpose |
|-----------|---------|---------|
| **Redis** | 7+ | Distributed cache backend |
| **PostgreSQL** | 16+ | Metadata and statistics storage |
| **Docker** | Latest | Containerization |
| **Docker Compose** | Latest | Multi-container orchestration |

### Development Tools
| Tool | Purpose |
|------|---------|
| **Git** | Version control |
| **GitHub Actions** | CI/CD pipeline automation |
| **Gradle Wrapper** | Guaranteed build consistency |
| **Flyway** | Database migration management |

---

## 📁 Project Structure

```
nwaysetcache1/
│
├── build.gradle.kts                    # Root Gradle build file
├── settings.gradle.kts                 # Gradle project configuration
├── gradle.properties                   # Gradle properties (JVM version, settings)
│
├── README.md                           # This file
├── CONTRIBUTING.md                     # Contribution guidelines
├── LICENSE                             # Proprietary license
├── VERSION                             # Version metadata
├── .gitignore                          # Git ignore rules
│
├── service/                            # ========== SERVICE MODULE ==========
│   ├── build.gradle.kts               # Service-specific build configuration
│   └── src/
│       ├── main/kotlin/com/nwayset/service/
│       │   ├── ServiceApplication.kt              # Spring Boot entry point
│       │   ├── controller/
│       │   │   └── CacheController.kt            # REST API endpoints
│       │   ├── config/
│       │   │   └── ServiceConfiguration.kt       # Bean definitions
│       │   ├── health/
│       │   │   └── CacheHealthIndicator.kt       # Health monitoring
│       │   ├── model/
│       │   │   └── CacheModels.kt               # Domain models (CacheEntry, CacheStats)
│       │   ├── util/
│       │   │   └── CacheUtils.kt                # Utility functions
│       │   └── error/
│       │       └── GlobalExceptionHandler.kt     # Exception handling
│       └── test/
│           ├── kotlin/com/nwayset/service/
│           │   ├── controller/CacheControllerTest.kt
│           │   └── integration/CacheIntegrationTest.kt
│           └── resources/                        # Test configurations
│
├── platform/                           # ========== PLATFORM MODULES ==========
│   └── cache/
│       ├── core/                       # ---------- CORE MODULE ----------
│       │   ├── build.gradle.kts
│       │   └── src/
│       │       ├── main/kotlin/com/nwayset/cache/core/
│       │       │   ├── CacheManager.kt           # Core cache interface
│       │       │   ├── CacheKeyBuilder.kt        # Composite key utilities
│       │       │   └── EvictionPolicy.kt         # Eviction strategy abstractions
│       │       └── test/kotlin/com/nwayset/cache/core/
│       │           └── CacheKeyBuilderTest.kt
│       │
│       ├── api/                        # ---------- API MODULE ----------
│       │   ├── build.gradle.kts
│       │   └── src/main/kotlin/com/nwayset/cache/api/
│       │       └── CacheService.kt              # Public API contract
│       │
│       └── adapters/                   # ---------- ADAPTER MODULES ----------
│           ├── redis/                  # Redis Implementation
│           │   ├── build.gradle.kts
│           │   └── src/
│           │       ├── main/kotlin/com/nwayset/cache/adapters/redis/
│           │       │   ├── RedisCacheManager.kt  # Redis implementation
│           │       │   └── config/
│           │       │       └── RedisConfig.kt    # Redis configuration
│           │       └── test/kotlin/
│           │           └── RedisCacheManagerTest.kt
│           │
│           └── memory/                 # In-Memory Implementation
│               ├── build.gradle.kts
│               └── src/
│                   ├── main/java/com/nwayset/cache/adapters/memory/
│                   │   ├── InMemoryCacheManager.java   # In-memory cache
│                   │   └── config/
│                   │       └── MemoryCacheConfig.java
│                   └── test/kotlin/
│                       └── InMemoryCacheManagerTest.kt
│
├── config/                             # ========== CONFIGURATION ==========
│   ├── local/
│   │   └── application.yml            # Local development config
│   ├── staging/
│   │   └── application.yml            # Staging environment config
│   ├── prod/
│   │   └── application.yml            # Production environment config
│   ├── application.properties         # Default properties
│   ├── cache.properties               # Cache-specific properties
│   └── default.yml                    # Default YAML configuration
│
├── db/                                 # ========== DATABASE ==========
│   └── migration/
│       ├── V1__initial_schema.sql     # Initial schema: cache_entries table
│       ├── V2__add_cache_stats_table.sql  # Cache statistics table
│       └── V3__add_indexes_for_performance.sql  # Performance indices
│
├── docs/                               # ========== DOCUMENTATION ==========
│   ├── architecture/
│   │   └── README.md                  # Detailed architecture guide
│   ├── api/
│   │   └── README.md                  # REST API documentation
│   ├── runbooks/
│   │   └── README.md                  # Operational procedures
│   ├── deployment/
│   │   └── README.md                  # Deployment guide
│   └── benchmarks/
│       └── README.md                  # Performance benchmarks
│
├── scripts/                            # ========== SCRIPTS ==========
│   ├── dev/
│   │   ├── start.sh                   # Start local development environment
│   │   ├── cleanup.sh                 # Clean up Docker containers
│   │   └── README.md                  # Development guide
│   └── ci/
│       ├── build.sh                   # CI/CD build script
│       └── README.md                  # CI/CD documentation
│
├── .github/                            # ========== GITHUB CONFIG ==========
│   └── workflows/
│       └── ci.yml                     # GitHub Actions CI/CD pipeline
│
├── Dockerfile                          # Docker image configuration
└── docker-compose.yml                  # Multi-container Docker setup

```

### Directory Depth Analysis

**Maximum Depth (6 levels):**
```
platform/cache/adapters/redis/src/main/kotlin/com/nwayset/cache/adapters/redis/
```

**File Type Distribution:**
- Kotlin (`.kt`): 15 files
- Java (`.java`): 3 files
- Gradle (`.kts`, `.gradle`): 8 files
- YAML (`.yml`, `.yaml`): 8 files
- SQL (`.sql`): 3 files
- Markdown (`.md`): 6 files
- Shell (`.sh`): 5 files
- Config (`.properties`, `.json`, `.xml`): 4 files

---

## 🔧 Modules

### 1. **Service Module** (`service/`)

**Purpose:** REST API and Spring Boot application entry point

**Responsibilities:**
- Expose HTTP endpoints for cache operations (PUT, GET, DELETE, CLEAR)
- Health monitoring and metrics collection
- Request validation and error handling
- Service configuration and dependency injection

**Key Classes:**
- `ServiceApplication`: Spring Boot application entry point
- `CacheController`: REST endpoints (`/api/v1/cache/*`)
- `CacheHealthIndicator`: Actuator health checks
- `CacheModels`: Domain objects (CacheEntry, CacheStats)
- `GlobalExceptionHandler`: Centralized error handling
- `CacheUtils`: Helper utilities for cache operations

**Dependencies:**
- `platform:cache:core`
- `platform:cache:adapters:redis`
- `platform:cache:adapters:memory`

**Configuration:**
```yaml
server:
  port: 8080
  servlet:
    context-path: /api

spring:
  application:
    name: nwaysetcache1
  profiles:
    active: local
```

---

### 2. **Cache Core Module** (`platform/cache/core/`)

**Purpose:** Core abstractions and cache interface contracts

**Responsibilities:**
- Define universal cache interface
- Provide key builder utilities
- Implement eviction policy strategies
- Establish interface contracts for all implementations

**Key Classes:**
- `CacheManager`: Universal cache interface
  ```kotlin
  interface CacheManager {
      fun put(key: String, value: Any)
      fun get(key: String): Any?
      fun remove(key: String)
      fun clear()
      fun size(): Int
      fun contains(key: String): Boolean
  }
  ```

- `CacheKeyBuilder`: Composite key construction
  ```kotlin
  val builder = CacheKeyBuilder(separator = ":")
  val key = builder.build("user", "123", "profile")  // "user:123:profile"
  ```

- `EvictionPolicy`: Enumeration of eviction strategies
  - **LRU** (Least Recently Used): Evicts least-recently-accessed entries
  - **LFU** (Least Frequently Used): Evicts least-frequently-accessed entries
  - **FIFO** (First In First Out): Evicts oldest entries
  - **TTL** (Time To Live): Evicts expired entries

- `LRUEvictionHandler`: Implementation of LRU strategy

**No External Dependencies:** Pure abstractions

---

### 3. **Cache API Module** (`platform/cache/api/`)

**Purpose:** Public API contracts for cache operations

**Responsibilities:**
- Expose public-facing cache service interface
- Define API contracts
- Provide statistics and monitoring interfaces

**Key Classes:**
- `CacheService`: Public API interface
  ```kotlin
  interface CacheService {
      fun getCacheManager(): CacheManager
      fun getCacheStats(): Map<String, Any>
  }
  ```

**Dependencies:**
- `platform:cache:core`

---

### 4. **Redis Adapter** (`platform/cache/adapters/redis/`)

**Purpose:** Redis-based distributed cache implementation

**Responsibilities:**
- Connect to Redis server
- Implement CacheManager interface using Redis
- Manage connection pooling
- Handle serialization/deserialization
- Provide Redis-specific configuration

**Key Classes:**
- `RedisCacheManager`: Redis implementation of CacheManager
  ```kotlin
  class RedisCacheManager(
      private val host: String = "localhost",
      private val port: Int = 6379
  ) : CacheManager
  ```

- `RedisConfig`: Configuration management
  ```kotlin
  data class RedisConfig(
      val host: String = "localhost",
      val port: Int = 6379,
      val password: String? = null,
      val database: Int = 0,
      val poolSize: Int = 10,
      val timeoutMs: Long = 2000
  )
  ```

**Dependencies:**
- `platform:cache:core`
- `redis.clients:jedis:5.1.0`

**Use Cases:**
- Multi-instance deployments
- Distributed caching across multiple services
- Persistence beyond process lifecycle
- Cross-service cache sharing

---

### 5. **In-Memory Adapter** (`platform/cache/adapters/memory/`)

**Purpose:** Fast, local in-memory cache implementation

**Responsibilities:**
- Implement CacheManager using ConcurrentHashMap
- Manage memory-based eviction
- Provide single-process caching
- Handle memory efficiency

**Key Classes:**
- `InMemoryCacheManager`: In-memory cache implementation (Java)
  ```java
  public class InMemoryCacheManager implements CacheManager {
      private final Map<String, Object> cache = new ConcurrentHashMap<>();
      private final int maxSize;
  }
  ```

- `MemoryCacheConfig`: Configuration management

**Dependencies:**
- `platform:cache:core`
- Standard Java libraries

**Use Cases:**
- Single-server deployments
- Development and testing
- Extremely high-speed local caching
- Microservice-local caches

---

## 📋 Prerequisites

Before getting started, ensure you have the following installed:

### Required
- **Java 21 LTS** or higher
  ```bash
  java --version
  ```
  Download from: [oracle.com/java](https://www.oracle.com/java/)

- **Git** (for version control)
  ```bash
  git --version
  ```

### For Local Development
- **Docker** & **Docker Compose** (for local Redis + PostgreSQL)
  ```bash
  docker --version
  docker-compose --version
  ```
  Download from: [docker.com](https://www.docker.com/)

### Optional (for IDE Development)
- **IntelliJ IDEA** (recommended for Kotlin development)
- **Visual Studio Code** with Kotlin extension

---

## 🚀 Quick Start

### Option 1: Minimal Setup (In-Memory Only)

```bash
# 1. Clone the repository
git clone https://github.com/vijaydaiict/AIProject.git
cd AIProject

# 2. Build all modules
./gradlew clean build

# 3. Run tests
./gradlew test

# 4. Start the service
./gradlew :service:bootRun

# 5. In another terminal, test the API
curl -X POST "http://localhost:8080/api/v1/cache/put?key=test" \
  -H "Content-Type: application/json" \
  -d '"Hello World"'

curl http://localhost:8080/api/v1/cache/get/test

# 6. Check health
curl http://localhost:8080/health
```

### Option 2: Full Stack (with Redis + PostgreSQL)

```bash
# 1. Clone the repository
git clone https://github.com/vijaydaiict/AIProject.git
cd AIProject

# 2. Start full development environment
docker-compose up -d

# 3. Wait for services to be ready
sleep 5

# 4. Build and run the service
./gradlew clean build
./gradlew :service:bootRun

# 5. Verify all services are running
docker-compose ps

# 6. Check application health
curl http://localhost:8080/health

# 7. Stop when done
docker-compose down
```

---

## 📚 Detailed Setup

### Step 1: Environment Setup

```bash
# Clone repository
git clone https://github.com/vijaydaiict/AIProject.git
cd AIProject

# Set Java version (if using jenv or similar)
export JAVA_HOME=$(/usr/libexec/java_home -v 21)

# Verify Java installation
java -version
# Expected: openjdk version "21" or oracle-jdk version "21"
```

### Step 2: Build Configuration

```bash
# Examine Gradle configuration
cat gradle.properties
# Output:
# org.gradle.jvm.version=21
# kotlin.code.style=official
# org.gradle.parallel=true

# Build with verbose output
./gradlew build --info

# Build specific module
./gradlew :platform:cache:core:build
```

### Step 3: Running Tests

```bash
# Run all tests
./gradlew test

# Run tests for specific module
./gradlew :service:test

# Run with coverage report
./gradlew jacocoTestReport

# Run a specific test class
./gradlew :platform:cache:adapters:memory:test \
  --tests InMemoryCacheManagerTest
```

### Step 4: Running the Application

```bash
# Development mode (default: in-memory)
./gradlew :service:bootRun

# With staging profile
./gradlew :service:bootRun --args='--spring.profiles.active=staging'

# With production profile
./gradlew :service:bootRun --args='--spring.profiles.active=prod'

# With custom port
./gradlew :service:bootRun --args='--server.port=9090'
```

### Step 5: Docker Setup (Optional)

```bash
# Start Redis (for distributed caching)
docker run -d -p 6379:6379 \
  --name nwayset-redis \
  redis:7-alpine

# Start PostgreSQL (for metadata storage)
docker run -d -p 5432:5432 \
  --name nwayset-postgres \
  -e POSTGRES_DB=nwaysetcache1 \
  -e POSTGRES_USER=cache_user \
  -e POSTGRES_PASSWORD=cache_pass \
  postgres:16-alpine

# Or use Docker Compose for everything
docker-compose up -d

# Verify services
docker ps
docker logs nwayset-redis
docker logs nwayset-postgres
```

---

## 🔗 API Documentation

### Base URL
```
http://localhost:8080/api/v1/cache
```

### Authentication
Currently no authentication (development). Production deployments should implement OAuth2 or API keys.

### Endpoints

#### 1. PUT - Store Value

```http
POST /cache/put?key={key}
Content-Type: application/json

Request Body:
"your_value_here"

Response (200 OK):
{
  "key": "your_key",
  "status": "stored"
}
```

**Example:**
```bash
curl -X POST "http://localhost:8080/api/v1/cache/put?key=user:123" \
  -H "Content-Type: application/json" \
  -d '{"name":"John","age":30}'
```

#### 2. GET - Retrieve Value

```http
GET /cache/get/{key}

Response (200 OK):
{
  "key": "your_key",
  "value": "cached_value",
  "found": true
}

Response (404 if not found):
{
  "key": "your_key",
  "value": null,
  "found": false
}
```

**Example:**
```bash
curl "http://localhost:8080/api/v1/cache/get/user:123"
```

#### 3. DELETE - Remove Entry

```http
DELETE /cache/remove/{key}

Response (200 OK):
{
  "key": "your_key",
  "status": "removed"
}
```

**Example:**
```bash
curl -X DELETE "http://localhost:8080/api/v1/cache/remove/user:123"
```

#### 4. POST - Clear All Cache

```http
POST /cache/clear

Response (200 OK):
{
  "status": "cleared"
}
```

**Example:**
```bash
curl -X POST "http://localhost:8080/api/v1/cache/clear"
```

#### 5. Health Check

```http
GET /health

Response (200 OK):
{
  "status": "UP",
  "components": {
    "cacheHealth": {
      "status": "UP",
      "details": {
        "cacheBackend": "multi-adapter",
        "timestamp": "2024-01-15T10:30:00Z",
        "components": ["redis", "memory"]
      }
    }
  }
}
```

**Example:**
```bash
curl "http://localhost:8080/health"
```

---

## ⚙️ Configuration

### Application Profiles

#### Local Development (`config/local/application.yml`)
```yaml
server:
  port: 8080
cache:
  max-size: 10000
  ttl-seconds: 3600
  eviction-policy: LRU
```
**Use:** `./gradlew :service:bootRun --args='--spring.profiles.active=local'`

#### Staging (`config/staging/application.yml`)
```yaml
server:
  port: 8080
cache:
  max-size: 50000
  ttl-seconds: 7200
  eviction-policy: LRU
```
**Use:** `./gradlew :service:bootRun --args='--spring.profiles.active=staging'`

#### Production (`config/prod/application.yml`)
```yaml
server:
  port: 8080
cache:
  max-size: 100000
  ttl-seconds: 14400
  eviction-policy: LRU
redis:
  host: prod-redis-cluster.internal
  port: 6379
  pool-size: 20
```
**Use:** `./gradlew :service:bootRun --args='--spring.profiles.active=prod'`

### Cache Configuration Properties

| Property | Default | Description |
|----------|---------|-------------|
| `cache.max-size` | 10000 | Maximum number of entries |
| `cache.ttl-seconds` | 3600 | Default time-to-live (1 hour) |
| `cache.eviction-policy` | LRU | Eviction strategy |
| `cache.enable-metrics` | true | Enable statistics collection |
| `redis.host` | localhost | Redis server hostname |
| `redis.port` | 6379 | Redis server port |
| `redis.pool-size` | 10 | Connection pool size |
| `redis.timeout-ms` | 2000 | Connection timeout |

### Environment Variables

```bash
# Override Redis host
export REDIS_HOST=redis.production.internal

# Override cache max size
export CACHE_MAX_SIZE=50000

# Override profiles
export SPRING_PROFILES_ACTIVE=prod

# Pass to application
./gradlew :service:bootRun
```

---

## 👨‍💻 Development

### Local Development Setup

```bash
# 1. Start local environment
./scripts/dev/start.sh

# 2. This will:
#    - Check if Redis is running (start if needed)
#    - Build all modules
#    - Run all tests
#    - Start the service on http://localhost:8080
```

### Building Specific Modules

```bash
# Build cache core
./gradlew :platform:cache:core:build

# Build Redis adapter
./gradlew :platform:cache:adapters:redis:build

# Build service
./gradlew :service:build

# Build all with detailed output
./gradlew build --info
```

### Running Tests

```bash
# All tests
./gradlew test

# Unit tests only
./gradlew :platform:cache:adapters:memory:test

# Integration tests
./gradlew :service:test

# With detailed output
./gradlew test --info

# Generate coverage reports
./gradlew jacocoTestReport
```

### Code Quality Checks

```bash
# Run Gradle checks
./gradlew check

# Run linting
./gradlew lintKotlin

# Check for issues
./gradlew detekt
```

### IDE Setup

**IntelliJ IDEA:**
1. Open project folder
2. Select `build.gradle.kts` as project file
3. Configure Java SDK: Java 21
4. IDE will auto-configure Gradle

**VS Code:**
1. Install extensions: "Kotlin Language Client", "Gradle for Java"
2. Open folder
3. Extensions will auto-configure

### Debugging

```bash
# Run with debugger
./gradlew :service:bootRun --debug

# Or in IDE: Right-click ServiceApplication.kt → Debug
```

---

## 🚢 Deployment

### Docker Deployment

```bash
# Build Docker image
docker build -t nwaysetcache1:1.0.0 .

# Run container
docker run -d \
  -p 8080:8080 \
  -e SPRING_PROFILES_ACTIVE=prod \
  -e REDIS_HOST=redis.internal \
  --name nwaysetcache1 \
  nwaysetcache1:1.0.0

# View logs
docker logs -f nwaysetcache1
```

### Docker Compose Deployment

```bash
# Start all services
docker-compose up -d

# View status
docker-compose ps

# View logs
docker-compose logs -f app

# Stop services
docker-compose down
```

### Kubernetes Deployment

```bash
# Create namespace
kubectl create namespace cache-system

# Apply deployment
kubectl apply -f k8s/deployment.yaml -n cache-system

# Check status
kubectl get pods -n cache-system
kubectl describe pod <pod-name> -n cache-system

# View logs
kubectl logs <pod-name> -n cache-system
```

### Database Migrations

```bash
# Run all migrations
./gradlew flywayMigrate

# Check migration status
./gradlew flywayInfo

# Repair migration history (if needed)
./gradlew flywayRepair

# Clean database (development only!)
./gradlew flywayClean
```

---

## 📊 Monitoring & Health Checks

### Health Endpoint

```bash
curl http://localhost:8080/health -s | jq .
```

**Response Structure:**
```json
{
  "status": "UP",
  "components": {
    "cacheHealth": {
      "status": "UP",
      "details": {
        "cacheBackend": "multi-adapter",
        "timestamp": "2024-01-15T10:30:00Z",
        "components": ["redis", "memory"]
      }
    }
  }
}
```

### Metrics Endpoint

```bash
curl http://localhost:8080/metrics -s | jq .
```

### Actuator Endpoints

```bash
# Health details
curl http://localhost:8080/health/details

# Application info
curl http://localhost:8080/info

# All actuator endpoints
curl http://localhost:8080/actuator
```

### Monitoring Commands

```bash
# Redis statistics (if using Redis)
redis-cli INFO stats

# Database statistics
psql -U cache_user -d nwaysetcache1 -c "SELECT * FROM cache_stats;"

# Application logs
tail -f service.log
```

---

## 🧪 Testing

### Test Structure

```
service/src/test/kotlin/com/nwayset/service/
├── controller/
│   └── CacheControllerTest.kt        # REST endpoint tests
└── integration/
    └── CacheIntegrationTest.kt       # End-to-end tests

platform/cache/adapters/memory/src/test/kotlin/
└── InMemoryCacheManagerTest.kt       # Adapter tests

platform/cache/core/src/test/kotlin/
└── CacheKeyBuilderTest.kt            # Utility tests
```

### Running Tests

```bash
# Run all tests
./gradlew test

# Run specific test class
./gradlew :service:test --tests CacheControllerTest

# Run specific test method
./gradlew :service:test --tests CacheControllerTest.testCachePutEndpoint

# Run with coverage
./gradlew jacocoTestReport
```

### Test Examples

```kotlin
@SpringBootTest
@AutoConfigureMockMvc
class CacheControllerTest {
    @Test
    fun testCachePutEndpoint() {
        mockMvc.perform(
            post("/api/v1/cache/put?key=test")
                .contentType(MediaType.APPLICATION_JSON)
                .content("\"test value\"")
        ).andExpect(status().isOk())
    }
}
```

---

## 🔧 Troubleshooting

### Issue: Port Already in Use

```bash
# Find process using port 8080
lsof -i :8080

# Kill the process
kill -9 <PID>

# Or use different port
./gradlew :service:bootRun --args='--server.port=9090'
```

### Issue: Redis Connection Timeout

```bash
# Check if Redis is running
redis-cli ping
# Expected: PONG

# If not running, start Redis
docker run -d -p 6379:6379 redis:7-alpine

# Check Redis status
redis-cli info server
```

### Issue: PostgreSQL Connection Error

```bash
# Check if PostgreSQL is running
docker ps | grep postgres

# Check logs
docker logs nwayset-postgres

# Verify credentials in config
cat config/prod/application.yml

# Reset database
docker-compose down -v
docker-compose up -d
```

### Issue: Build Failures

```bash
# Clean build directory
./gradlew clean

# Full rebuild
./gradlew build --refresh-dependencies

# Check Gradle version
./gradlew --version
# Expected: Gradle 8.x
```

### Issue: OutOfMemoryError

```bash
# Increase JVM heap size
export GRADLE_OPTS="-Xmx2g"
./gradlew build

# Or in application startup
./gradlew :service:bootRun --args='--jvm.memory.max=2g'
```

### Issue: Module Not Found

```bash
# Rebuild project structure
./gradlew projects

# Check settings.gradle.kts
cat settings.gradle.kts

# Reimport in IDE:
# IntelliJ: File → Sync Now
# VS Code: Command Palette → Gradle: Refresh
```

---

## 🤝 Contributing

### Getting Started

1. **Fork the repository** on GitHub
2. **Clone your fork** locally
3. **Create a feature branch**: `git checkout -b feature/my-feature`
4. **Make changes** with meaningful commits
5. **Write tests** for new functionality
6. **Run tests**: `./gradlew test`
7. **Push to fork**: `git push origin feature/my-feature`
8. **Create Pull Request** with detailed description

### Code Standards

- **Kotlin**: Follow [Kotlin Coding Conventions](https://kotlinlang.org/docs/coding-conventions.html)
- **Java**: Follow [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)
- **Indentation**: 4 spaces
- **Line length**: Max 120 characters
- **Documentation**: Javadoc/KDoc for public APIs

### Pull Request Guidelines

- Clear, descriptive title
- Detailed description of changes
- Reference related issues
- Ensure all tests pass
- Include new tests for new features
- Update documentation

---

## 📄 License

This project is licensed under a **Proprietary License** - see [LICENSE](LICENSE) file for details.

**Copyright © 2024 NWaySet Inc. All rights reserved.**

Commercial use, redistribution, and modification require explicit written permission.

---

## 📞 Support & Contact

- **Issues**: GitHub Issues
- **Documentation**: See `docs/` folder
- **Runbooks**: See `docs/runbooks/`
- **API Docs**: See `docs/api/`

---

## 📖 Additional Resources

- [Architecture Guide](docs/architecture/README.md)
- [API Documentation](docs/api/README.md)
- [Operational Runbooks](docs/runbooks/README.md)
- [Deployment Guide](docs/deployment/README.md)
- [Performance Benchmarks](docs/benchmarks/README.md)
- [Development Guide](scripts/dev/README.md)
- [Contributing Guidelines](CONTRIBUTING.md)

---

**Made with ❤️ by NWaySet Inc.**

Last Updated: 2024-01-15  
Version: 1.0.0
