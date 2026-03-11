# CommerceHub

Plataforma e-commerce backend basada en microservicios con enfoque **junior–mid profesional**.

## Stack
- Spring Boot 3
- Spring Cloud (Config Server, Eureka, Gateway)
- Spring Security + JWT
- Spring Batch
- OpenAPI / Swagger
- MySQL por microservicio
- Docker Compose

## Arquitectura de alto nivel
- `config-server`: configuración centralizada.
- `discovery-service`: registro y descubrimiento (Eureka).
- `api-gateway`: puerta de entrada única.
- `auth-service`: login/registro/JWT/refresh.
- `customer-service`: perfil y direcciones.
- `catalog-service`: productos, categorías, variantes.
- `inventory-service`: stock, reservas y movimientos.
- `cart-service`: carrito y snapshots de precio.
- `order-service`: checkout, pedidos, estados.
- `payment-service`: pagos y transacciones.
- `batch-service`: jobs de importación y agregación.

## Estructura
```text
commercehub/
├─ docs/
├─ docker/
├─ config-repo/
├─ services/
└─ scripts/
```

## Cómo levantar entorno local (base)
```bash
cd new-repositories/commercehub
cp .env.example .env
./scripts/up.sh
```

## Seguridad JWT (base profesional)
- Access token corto (15 min).
- Refresh token persistido y hasheado en `auth-service`.
- Endpoints públicos mínimos.
- `@PreAuthorize` en endpoints sensibles.
- Diseño stateless.

## Endpoints objetivo (MVP)
- `POST /auth/register`
- `POST /auth/login`
- `POST /auth/refresh`
- `GET /products`
- `POST /orders/checkout`
- `POST /batch/jobs/catalog-import/run`

## Roadmap sugerido
1. Fase 1: auth + catálogo + carrito + order + swagger + docker.
2. Fase 2: inventory + payment + feign + circuit breaker.
3. Fase 3: batch + observabilidad + testcontainers + CI.
