## OneWordApp — Quick AI agent guide

This project is a small Spring Boot web app (Java 17, Spring Boot 3.5.6). The goal of this file is to give an AI coding agent the exact, discoverable facts it needs to be productive in this codebase.

- Language & runtime: Java 17, Spring Boot 3.x (see `pom.xml`).
- Build tool: Maven; use the project wrapper on Windows: `mvnw.cmd` (root contains `mvnw.cmd`).

### How to run / build / test (Windows)
Use the wrapper from the repository root (Windows `cmd.exe`):

```cmd
mvnw.cmd spring-boot:run   :: runs the app (hot-reload depends on IDE)
mvnw.cmd -DskipTests package :: build a jar
mvnw.cmd test                :: run unit tests
```

The main entrypoint is `src/main/java/com/onewordapp/OneWordAppApplication.java`.

### Project structure & architecture (what you'll see)
- Package root: `com.onewordapp`.
- Standard layered layout under `src/main/java/com/onewordapp`:
  - `controller/` — web controllers (MVC/REST)
  - `service/` — business logic
  - `repository/` — persistence layer
  - `entity/` — JPA entities
  - `config/` — application configuration (e.g. security)
- Templates & static assets: `src/main/resources/templates` and `src/main/resources/static` (Thymeleaf is used; see `pom.xml` and `thymeleaf-extras-springsecurity6`).

Notes from scanning the codebase:
- `SecurityConfig.java` exists but is currently empty — the project includes `spring-boot-starter-security`, so expect security to be configured here.
- Controllers, services, repositories and entities are present as placeholder classes. The codebase follows the typical controller -> service -> repository flow; prefer implementing `@Controller`/`@RestController` for controllers, `@Service` for services, and Spring Data repositories as interfaces extending `JpaRepository` in `com.onewordapp.repository`.

### Data & integration
- Default DB properties (discoverable in `src/main/resources/application.properties`):
  - Points at PostgreSQL: `jdbc:postgresql://localhost:5433/oneworddb` with credentials placeholders.
  - `spring.jpa.hibernate.ddl-auto=update` and `spring.jpa.show-sql=true` are enabled.
- `pom.xml` includes runtime dependency on H2; tests or local dev builds may prefer switching to an in-memory H2 URL (e.g. `jdbc:h2:mem:testdb`) if Postgres isn't available.

### Conventions an agent should follow
- Keep classes under the existing package layout `com.onewordapp.*`.
- Follow the layered pattern: controllers call services; services call repositories; repositories operate on JPA entities in `entity`.
- Use Spring stereotypes (`@Controller` / `@RestController`, `@Service`, `@Repository`, `@Entity`) and constructor injection.
- For persistence, create repository interfaces in `com.onewordapp.repository` that extend `org.springframework.data.jpa.repository.JpaRepository<T, ID>` rather than standalone classes.
- Templates should be standard Thymeleaf under `src/main/resources/templates`; security expressions use `thymeleaf-extras-springsecurity6`.

### Useful file references (examples)
- Application entry: `src/main/java/com/onewordapp/OneWordAppApplication.java`
- App config: `src/main/java/com/onewordapp/config/SecurityConfig.java` (empty placeholder; modify here for security rules)
- DB config: `src/main/resources/application.properties`
- Layers to edit/extend: `src/main/java/com/onewordapp/controller/*`, `service/*`, `repository/*`, `entity/*`.

### Tests and tooling
- Tests live under `src/test/java/com/onewordapp` — run `mvnw.cmd test`.
- `spring-security-test` and `spring-boot-starter-test` are available in `pom.xml`.

### What the agent should *not* assume
- There is no pre-existing DB service in the repository — Postgres is referenced by properties but not provisioned here.
- Many classes are scaffolds/placeholders (empty classes). Before making large refactors, prefer small, testable changes and add or update unit tests under `src/test/java`.

### Example quick tasks an agent can do immediately
- Implement a new REST endpoint: add `@RestController` under `controller/`, create a `@Service` and a `JpaRepository` interface, add a simple `@Entity` and a test in `src/test/java` that starts Spring context.
- Wire basic security: populate `SecurityConfig.java` with a minimal in-memory user for local testing (tests should use `spring-security-test`).

If anything in this file is unclear or you want more low-level examples (sample `JpaRepository` interface, `SecurityConfig` starter, or a small integration test), tell me which example you want and I will add it.
