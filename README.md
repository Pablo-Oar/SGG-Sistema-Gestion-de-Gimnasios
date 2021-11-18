# Sistema para la Gestion de Gimnasios (SGG).
**Sistema Web para facilitar, organizar y administrar las diferentes tareas en un gimnasio.**
  * [x] Usuario para Recepcionista: Visualizará los clientes ya registrados en el sistema, buscar clientes por apellido, verificar si estan activos o no. Podrá realizar el ingreso de nuevos clientes asignarles un tipo de membresia y tambien modificar datos de clientes ya ingresados. 
  * [x] Usuario para el Area De Ventas: Podrá visualizar, editar, añadir productos y asignarles una categorias. Además será el encargado de realizar las ventas pudiendo añadir al carrito los diferentes productos solicitados por el cliente, deberá registrar la venta y emitir dicho comprobante.
  * [ ] Usuario para Profesor: Podrá visualizar, añadir y editar rutinas de entrenamiento, lo mismo con las diferentes clases que se dicten en el gimnasio. 

## Contenido

| **Requerimientos**   | **Detalle/Listado de casos incluidos** |
| :---:                |     :---                               |   
| **ABMC simple**      | 1. ABMC de Usuario.                    |
|                      | 2. ABMC de Cliente.                    |
|                      | 3. ABMC de Membresía.                  |
|                      | 4. ABMC de Producto.                   | 
|                      | 5. ABMC de Categoria.                  |
|                      | 6. ABMC de Profesor.                   |
|                      | 7. ABMC de Rutina.                     |
|                      | 8. ABMC de Clase.                      |
|                      |                                        |
| **ABMC Dependiente** | 1- ABMC Membresia de Cliente.          |
|                      | 2- ABMC Categoria de Producto.         |
|                      | 3- ABMC Rutina de Clase.               |
|                      |                                        |
| **CU NO-ABMC**       | 1. Al agregar un producto al carrito verificar que el stock sea mayor a la cantidad solicitada.|
|                      | 2. Al agregar un producto al carrito verificar que el estado del mismo esté activo (sea disponible).|
|                      | 3. Al asignarle una Clase a un profesor verificar que el día y el horario estén disponibles.|
|                      |                                        |
| **Listado Simple**   | 1. Listado de Membresia.               |
|                      | 2. Listado de Categoria.               |
|                      |                                        |
| **Listado Complejo** | 1. Listado de Clientes.  (Apellido)    |
|                      | 3. Listado de Producto.  (Id)          |
|                      | 5. Listado de Clase.     (Nombre)      |
|                      |                                        |
| **CU Complejo**      |                                        |
|                      |                                        |
| **Nivel de Acceso**  | 1. Administrador.                      |
|                      | 2. Ventas.                             |
|                      | 3. Profesor.                           |
|                      |                                        |
| **Envio de Email**   | El sistema envía una confirmación al nuevo socio notificando confirmación e informando cuando será el vencimiento de su membresía.|

## Diagrama Entidad Relación:

![DER](https://user-images.githubusercontent.com/80583829/142291113-3d6ae472-68ba-4743-8c81-256a9d0be0e7.jpg)

## Características del sistema:

* Desarrollado en: Java utilizando el paradigma orientación a objetos.
* Entorno de Desarrolo: Eclipse.
* Aplicación: cliente-servidor.
* Tecnología web: JSP, Servlets, JSTL, HTML, Bootstrap. Server TomCat v8.0.
* Manipulación de datos: Realizando consultas y modificaciones con MySQL.
* Realizado en capas siguiendo el patrón MVC.
* Manejo de distintos niveles de usuario.
* Manejo de excepciones.
