# Parcial1_ARSW
**Nombre:** Samuel Antonio Gil Romero  
**Materia:** Arquitecturas de Software (ARSW)  
**Institución:** Escuela Colombiana de Ingeniería  
**Fecha:** Junio 2026

# Implementacion de Turnos En timepo Real 
-Contexto del problema 
una clinica queria digitalizar su sala de espera. los pacientes pueden tomar un turno desde una aplicacion web y visualizar en timpo real cua les el turno que esta siendo llamado la recepcion puede llamar al siguiente turno que este disponible.
# Diagrama 
<img width="469" height="297" alt="image" src="https://github.com/user-attachments/assets/389186bc-8b8a-4cb5-a16d-f5cabf143e41" />
para este ejerccicio se utilizo un back en java un front basico en react y utilizamos un cliente concurrente para el llamado continuo ellos usan al back para el uso del real time y generan los tickets correspondientes con los estados definidos. una arquitectura cliente servidor.

# Como se ejecuta 
-clonar el repositorio
-entra a la carpeta backend 

```
cd backend
mvn spring-boot:run

```
<img width="1198" height="808" alt="image" src="https://github.com/user-attachments/assets/8316401d-3454-4396-afdd-dfd24947b3a5" />

-para el front 
```
cd frontend
npm install
npm run dev

```
<img width="1148" height="533" alt="image" src="https://github.com/user-attachments/assets/df88501b-fb1a-401a-b4ef-52a6dc9296f3" />

# video
- en el video se logra evidenciar como en el front no se tiene conexion hasta generar el inicio del back donde se implementa la generacion de los turnos y la conexion del websocket donde como se mostro en el diagrama los pacientes y la recepcion estan conectados al front que se conecta con el back que tiene el cliente concurrente.

https://youtu.be/VaeNJ6jDAuo

- para ete segundo video es mas un caso de prueba donde podmeos ver que se realizan peticiones de turnos y en todas las pestañas o usuarios ven en qeu turno va actualmente el llamdo de turno y cada uno tiene su turno y ya es solo que las personas esperen su turno pero en simultaneo pueden ver que turnos se estan llamando 

https://youtu.be/bmuKqwzpu4o
 
