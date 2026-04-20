
## IMPORTANTE

## PRIMERO
## Ejecutar script de la base de datos ubicado en resources/database.sql

## SEGNDO :
## EN GIT PARA TRABJAR CON EL PROYECTO API, CAMBIARSE A LA RAMA API


## API

POST /api/auth/login
```json
{
  "username": "admin",
  "password": "admin123"
}
```
{
  "success": true,
  "message": "Autenticación correcta",
  "data": {
    "token": "jwt-aqui",
    "type": "Bearer",
    "username": "admin",
    "role": "ROLE_ADMIN"
  }
}