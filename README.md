
# 🐾 Clínica Veterinaria ABC - Proyecto Java

Este proyecto consiste en una aplicación de escritorio en **Java (Swing)** que permite gestionar la información de una clínica veterinaria. Se pueden registrar, actualizar, eliminar y consultar **mascotas** y **personas dueñas**, con conexión a una base de datos **MySQL**.

---

## 🧰 Tecnologías utilizadas

- ☕ Java SE 17
- 🖼️ Swing para la interfaz gráfica
- 🐬 MySQL 8+ como base de datos relacional
- 🔗 JDBC para la conexión a la base de datos
- 🧱 Maven para la gestión del proyecto y dependencias

---

## 🗂️ Estructura del proyecto

```
├── src/
│   ├── dao/            → Clases para acceso a datos (DAO)
│   ├── model/          → Clases de entidad (Persona, Mascota)
│   ├── util/           → Clase de conexión a la BD (Conexion.java)
│   └── view/           → Interfaz gráfica (ClinicaApp.java)
└── README.md
```

---

## 🛠️ Cómo crear la base de datos

1. Abre tu cliente de MySQL (Workbench, DBeaver, consola, etc.)
2. Ejecuta el siguiente script SQL:

```sql
CREATE DATABASE IF NOT EXISTS clinica_veterinaria;

USE clinica_veterinaria;

CREATE TABLE persona (
    documento VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(100),
    telefono VARCHAR(20)
);

CREATE TABLE mascota (
    codigo VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(100),
    tipo VARCHAR(50),
    edad INT,
    documento VARCHAR(20),
    FOREIGN KEY (documento) REFERENCES persona(documento)
);
```

---

## 🔐 Configurar la conexión a la base de datos

La conexión a la base de datos se configura directamente en la clase `Conexion.java`, ubicada en el paquete `util`.

### Ubicación: `src/util/Conexion.java`


```java
private static final String URL = "jdbc:mysql://localhost:3306/veterinaria";
private static final String USER = "root";
private static final String PASSWORD = "tu_contraseña";

## 🚀 Instrucciones de ejecución

1. Clona el repositorio:
   ```bash
   git clone https://github.com/cdavidbc/mascotas_java.git
   ```

---

## 🧪 Funcionalidades implementadas

- Registrar personas (documento, nombre, teléfono)
- Registrar mascotas (código, nombre, tipo, edad, documento del dueño)
- Listar mascotas y personas
- Actualizar registros
- Eliminar registros
- Interfaz en pestañas (JTabbedPane)

---

