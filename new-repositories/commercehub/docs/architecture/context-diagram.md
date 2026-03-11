# Context Diagram (texto)

Cliente -> API Gateway -> {Auth, Catalog, Cart, Order, Payment, Inventory}

Soporte:
- Config Server para configuración centralizada.
- Discovery Service para service registry.
- MySQL independiente por microservicio.
- Batch Service para procesos offline (importación catálogo, agregados diarios).
