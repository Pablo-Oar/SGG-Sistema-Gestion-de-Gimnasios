# Sistema para la Gestion de Gimnasios (SGG).
**Sistema Web para facilitar, organizar y administrar las diferentes tareas en un gimnasio.**
  * [x] **Usuario Administrador:** Tiene permitido visualizar y realizar todo tipo de cambios. 
  * [x] **Usuario Recepcionista:** Visualizará los clientes ya registrados en el sistema, buscar clientes por apellido, verificar si estan activos o no. Podrá realizar el ingreso de nuevos clientes asignarles un tipo de membresia y tambien modificar datos de clientes ya ingresados. 
  * [x] **Usuario Ventas:** Podrá visualizar, editar, añadir productos y asignarles una categorias. Además será el encargado de realizar las ventas pudiendo añadir al carrito los diferentes productos solicitados por el cliente, deberá registrar la venta y emitir dicho comprobante.
  * [ ] **Usuario Profesor:** Podrá visualizar, añadir y editar rutinas de entrenamiento, lo mismo con las diferentes clases que se dicten en el gimnasio. 

## Capturas:


>###### Login: 


![Login01](https://user-images.githubusercontent.com/80583829/143680092-cda485bd-5656-4a1b-a42b-83021582d20a.png)

>###### Home Administrador:
 ![HomeAdmin](https://user-images.githubusercontent.com/80583829/143680182-df2c6a72-1868-4dfb-8040-461b54180487.png)

>###### Home Recepcionista:
![HomeRecepcionista](https://user-images.githubusercontent.com/80583829/143728903-dff507c0-63c4-4a90-9a78-d25701cf782e.png)

>###### Home Vendedor: 
![HomeVendedor](https://user-images.githubusercontent.com/80583829/143680230-128c49b6-70cb-4b50-9109-1b0937ca1287.png)

>###### Clientes: 
![ListadoClientes](https://user-images.githubusercontent.com/80583829/143680250-33a163ea-3336-48a2-9171-d4af4d0ce2ad.png)

>###### Agregar Cliente:
 ![AgregarCliente](https://user-images.githubusercontent.com/80583829/143680293-696d7249-9d11-49a8-8fb7-b9f68f7345b7.png)

>###### Editar Cliente: 
![EditarCliente](https://user-images.githubusercontent.com/80583829/143680301-71e63183-d536-4e64-9942-4c8035ef5159.png)

>###### Membresia:
  ![ListadoMembresia](https://user-images.githubusercontent.com/80583829/143680350-7891fd4f-d6ca-48c8-865f-aabaa0944d6c.png)

>###### Agregar Membresia: 
![AgregarMembresia](https://user-images.githubusercontent.com/80583829/143680382-64a922f9-bd66-4345-8988-8470325a896e.png)

>###### Editar Membresia:
 ![EditarMembresia](https://user-images.githubusercontent.com/80583829/143680428-1b9fedca-e21d-4fac-b5de-4f1f7621125a.png)

>###### Productos: 
![ListadoProductos](https://user-images.githubusercontent.com/80583829/143680446-ee5f9fdd-b57d-46ed-965a-4925d261c17a.png)

>###### Agregar Producto:
 ![AgregarProducto](https://user-images.githubusercontent.com/80583829/143680466-5d12fd85-432a-4134-9ca9-10efd045fae7.png)

>###### Editar Producto:
 ![EditarProducto](https://user-images.githubusercontent.com/80583829/143680482-2023ac50-ce7e-455e-b841-158b40714d4b.png)

>###### Categoría:
 ![LisctadoCategoria](https://user-images.githubusercontent.com/80583829/143680524-4870dac8-dd3b-4b1a-a3fe-9bb6329b297b.png)

>###### Agregar Categoría:
![AgregarCategoria](https://user-images.githubusercontent.com/80583829/143680540-2d3ac33e-5f46-45e6-b5d6-51abf69ad1df.png)

>###### Editar categoría:
 ![EditarCategoria](https://user-images.githubusercontent.com/80583829/143680560-d52de5a5-e96a-447f-bcba-001a636ec2f1.png)

>###### Registrar Venta: 
![RegistarVenta](https://user-images.githubusercontent.com/80583829/143680585-f0edbfcc-310e-4055-8350-636127f22049.png)


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
