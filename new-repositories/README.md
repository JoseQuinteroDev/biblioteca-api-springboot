# New repositories workspace

Esta carpeta contiene proyectos **independientes** del proyecto principal.

## Proyecto incluido
- `commercehub/`: monorepo e-commerce (Spring microservices).

## Cómo convertirlo en repositorio Git independiente
```bash
cd new-repositories/commercehub
git init
git add .
git commit -m "Initial commit: CommerceHub scaffold"
```

Opcionalmente, conecta remoto:
```bash
git remote add origin <tu-url>
git branch -M main
git push -u origin main
```
