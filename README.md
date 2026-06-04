# NWaySetCache1

A production-grade, multi-module caching framework for distributed systems. Supports Redis, in-memory caching, and pluggable cache eviction policies.

## Project Overview

NWaySetCache1 is a comprehensive caching solution built on Kotlin and Java, designed for scalability and extensibility. It provides unified cache interfaces, multiple backend implementations, and monitoring capabilities.

### Architecture

```
nwaysetcache1/
├── service/              # Main service module (REST API + business logic)
├── platform/             # Core platform modules
│   ├── cache/           # Caching framework
│   │   ├── core/        # Core cache abstractions
│   │   ├── adapters/    # Cache implementation adapters
│   │   └── api/         # Public API module
├── config/              # Environment-specific configurations
├── db/                  # Database migrations
├── docs/                # Architecture and operational documentation
└── scripts/             # Development and CI/CD scripts
```

## Modules

### Service Module (`service/`)
REST API service built with Spring Boot. Provides HTTP endpoints for cache operations.

### Cache Platform (`platform/cache/`)
- **core**: Core caching abstractions and interfaces
- **adapters**: Pluggable cache implementations (Redis, In-Memory)
- **api**: Public API contracts

## Quick Start

```bash
# Build all modules
./gradlew build

# Run tests
./gradlew test

# Start service (requires config)
./gradlew :service:bootRun

# View health status
curl http://localhost:8080/health
```

## Documentation

- [Architecture Guide](docs/architecture/README.md)
- [API Documentation](docs/api/README.md)
- [Runbooks](docs/runbooks/README.md)

## Technology Stack

- **Language**: Kotlin + Java
- **Build**: Gradle 8.x
- **Cache Backends**: Redis (via adapter), In-Memory
- **REST Framework**: Spring Boot 3.x
- **Database**: PostgreSQL (migrations included)

## Development

See [Development Guide](scripts/dev/README.md) for local setup.

## License

Proprietary - NWaySet Inc.
