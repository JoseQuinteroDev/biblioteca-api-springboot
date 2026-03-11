# Domain boundaries

- auth-service: identidad, credenciales, roles, refresh tokens.
- customer-service: perfil y direcciones del usuario autenticado.
- catalog-service: definición del producto y variantes vendibles.
- inventory-service: disponibilidad y reservas de stock por SKU.
- cart-service: carrito activo y snapshots de ítems.
- order-service: ciclo de vida del pedido.
- payment-service: estado y transacciones de cobro/reembolso.
- batch-service: procesos offline de importación/agregación.

Regla: **no compartir entidad JPA ni base de datos entre microservicios**.
