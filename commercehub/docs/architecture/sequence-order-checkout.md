# Secuencia Checkout (simplificada)

1. Cliente llama `POST /orders/checkout` al Gateway.
2. `order-service` valida JWT y obtiene `customerId`.
3. `order-service` consulta `cart-service` para items activos.
4. `order-service` reserva stock en `inventory-service`.
5. `order-service` solicita cobro en `payment-service`.
6. Si pago OK: confirma reserva y estado `CONFIRMED/PAID`.
7. Si pago KO: libera reserva y marca pedido `CANCELLED`.
