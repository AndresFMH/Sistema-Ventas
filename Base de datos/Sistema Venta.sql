-- Crear la base de datos
create database bd_sistema_ventas;

-- Seleccionar la base de datos recién creada
use bd_sistema_ventas;

-- Crear la tabla de usuarios
create table tb_usuario(
idUsuario int (11) auto_increment primary key,
nombre varchar(30) not null,
apellido varchar(30) not null,
usuario varchar(15) not null,
password varchar(15) not null,
telefono varchar(15) not null,
estado int(1) not null
);
-- Insertar un usuario en la tabla de usuarios
insert into tb_usuario(nombre, apellido, usuario, password, telefono, estado)
values("Andres", "Martinez", "andres", "1234", "0986754321", 1);

-- Seleccionar todos los registros de la tabla de usuarios
select * from tb_usuario;

-- Seleccionar usuario y contraseña de la tabla de usuarios donde coincidan con "andres" y "1234"
select usuario, password from tb_usuario where usuario = "andres" and password = "1234";

-- Crear la tabla de clientes
create table tb_cliente(
idCliente int (11) auto_increment primary key,
nombre varchar(30) not null,
apellido varchar(30) not null,
cedula varchar(15) not null,
telefono varchar(15) not null,
direccion varchar(100) not null,
estado int(1) not null
);

-- Seleccionar todos los registros de la tabla de clientes
select * from tb_cliente;

-- Vaciar la tabla de clientes
truncate table tb_cliente;

-- Crear la tabla de categorías
create table tb_categoria(
idCategoria int (11) auto_increment primary key,
descripcion varchar(200) not null,
estado int(1) not null
);

-- Seleccionar todos los registros de la tabla de categorías
select * from tb_categoria;

-- Seleccionar descripción de la categoría donde la descripción sea vacía
select descripcion from tb_categoria where descripcion = '';

-- Vaciar la tabla de categorías
truncate table tb_categoria;

-- Crear la tabla de productos
create table tb_producto(
idProducto int (11) auto_increment primary key,
nombre varchar(100) not null,
cantidad int (11) not null,
precio double (10,2) not null,
descripcion varchar (200) not null,
porcentajeIva int (2) not null,
idCategoria int (11) not null,
estado int(1) not null
);

-- Seleccionar todos los registros de la tabla de productos y mostrar información de la categoría relacionada
select p.idProducto, p.nombre, p.cantidad, p.descripcion, p.porcentajeIva, c.descripcion, p.estado from tb_producto As p, tb_categoria As c where p.idCategoria = c.idCategoria;

-- Crear la tabla de cabecera de ventas
create table tb_cabecera_venta(
idCabeceraVenta int (11) auto_increment primary key,
idCliente int (11) not null,
valorPagar double (10,2) not null,
fechaVenta date not null,
estado int(1) not null
);

-- Seleccionar todos los registros de la tabla de cabecera de ventas
select * from tb_cabecera_venta;

-- Crear la tabla detalle de ventas
create table tb_detalle_venta(
idDetalleVenta int (11) auto_increment primary key,
idCabeceraVenta int (11) not null,
idProducto int (11) not null,
cantidad int (11) not null,
precioUnitario double (10,2) not null,
subtotal double (10,2) not null,
descuento double (10,2) not null,
iva double (10,2) not null,
totalPagar double (10,2) not null,
estado int(1) not null
);

-- Seleccionar todos los registros de la tabla de detalle de ventas
select * from tb_detalle_venta;

-- Mostrar la lista de tablas en la base de datos actual
show tables;

-- Seleccionar información de ventas relacionando la cabecera de venta con el cliente
select cv.idCabeceraVenta as id, concat(c.nombre,' ', c.apellido) as cliente, cv.valorPagar as total, cv.fechaVenta as fecha, cv.estado from tb_cabecera_venta as cv, tb_cliente as c where cv.idCliente = c.idCliente;

-- Seleccionar información de venta específica relacionando la cabecera de venta y el cliente
select cv.idCabeceraVenta, cv.idCliente, concat(c.nombre,' ', c.apellido) as cliente, cv.valorPagar, cv.fechaVenta, cv.estado  from tb_cabecera_venta as cv, tb_cliente as c  where cv.idCabeceraVenta = 1 and cv.idCliente = c.idCliente;
