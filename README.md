# API-CRUD-EVENTO-LISTA-PERSONAS-V2

Según lo planteado en la primera versión realizada y publicada en WilberJr69/API-CRUD-EVENTO-LISTA-PERSONAS-V1 , se realiza los siguiente:

A COMPARACIÓN DE LA V1, esta contendrá lo siguiente:

- Uso de DTOs para poder modelar la salida de respuestas de salida, y no exponer directamente las entidades JPA
- Uso de Mappers con MapStruct para para el mapeo de Entitys y DTOs
- Lógica de @EmbeddedId para crear clase intermedia de relación muchos a muchos (tabla = lista_personas)


A COMPARACIÓN DE LA V1, esta contendrá lo siguiente:

- Uso de DTOs para poder modelar la salida de respuestas de salida, y no exponer directamente las entidades JPA
- Uso de Mappers con MapStruct para para el mapeo de Entitys y DTOs
- Lógica de @EmbeddedId para crear clase intermedia de relación muchos a muchos (tabla = lista_personas)

Condiciones a restringir para la version 2 de esta api:

- No uso de Spring Security como Mokito ni JWT
- No uso de Pruebas Unitarias o OAuth
- No modificar la base de datos
- No Documentación de APIs

Ahora toda la información adicional puedes encontrarla en mi page de Notion para que puedas visuaizar todos mis avances sobre esta api y futuras versiones con implementaciones, donde encontrarás, el json del postman de los request: https://burly-haircut-0b8.notion.site/Versiones-Api-Backend-para-Eventos-con-Spring-1274a97b2168802f8808f714874d0891
