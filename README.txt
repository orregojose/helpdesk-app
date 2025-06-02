APLICACIÓN DE MESA DE AYUDA - DOCUMENTACIÓN
==========================================

Esta aplicación Android fue desarrollada como un sistema de mesa de ayuda (help desk) que permite la gestión de tickets de soporte. A continuación, se detallan todos los componentes y su funcionamiento:

1. ACTIVIDADES PRINCIPALES
-------------------------

1.1. LoginActivity
    - Propósito: Maneja la autenticación de usuarios
    - Ubicación: app/src/main/java/com/example/mesaayuda/LoginActivity.kt
    - Características:
        * Pantalla de inicio de sesión con logo de Trilogit
        * Campos para usuario y contraseña
        * Validación de credenciales (admin/1234)
        * Diseño Material Design con colores corporativos

1.2. MainActivity
    - Propósito: Pantalla principal después del login
    - Ubicación: app/src/main/java/com/example/mesaayuda/MainActivity.kt
    - Características:
        * Menú principal con opciones de gestión
        * Navegación a las diferentes funcionalidades
        * Interfaz intuitiva con botones de acción

1.3. CreateTicketActivity
    - Propósito: Creación de nuevos tickets
    - Ubicación: app/src/main/java/com/example/mesaayuda/CreateTicketActivity.kt
    - Características:
        * Formulario para crear tickets
        * Campos para título, descripción y prioridad
        * Asignación automática de fecha y estado

1.4. SearchTicketActivity
    - Propósito: Búsqueda y visualización de tickets
    - Ubicación: app/src/main/java/com/example/mesaayuda/SearchTicketActivity.kt
    - Características:
        * Búsqueda por diferentes criterios
        * Lista de tickets con detalles
        * Opciones de filtrado

1.5. TicketReportActivity
    - Propósito: Generación de reportes
    - Ubicación: app/src/main/java/com/example/mesaayuda/TicketReportActivity.kt
    - Características:
        * Visualización de estadísticas
        * Filtros por fecha y estado
        * Resumen de tickets

2. CLASES DE DATOS Y UTILIDADES
-------------------------------

2.1. Ticket (Clase de datos)
    - Propósito: Modelo de datos para tickets
    - Ubicación: app/src/main/java/com/example/mesaayuda/Ticket.kt
    - Atributos:
        * ID
        * Título
        * Descripción
        * Estado
        * Prioridad
        * Fecha de creación
        * Usuario asignado

2.2. DatabaseHelper
    - Propósito: Gestión de base de datos SQLite
    - Ubicación: app/src/main/java/com/example/mesaayuda/DatabaseHelper.kt
    - Funcionalidades:
        * Creación y actualización de la base de datos
        * Operaciones CRUD para tickets
        * Consultas y búsquedas

2.3. TicketAdapter
    - Propósito: Adaptador para listas de tickets
    - Ubicación: app/src/main/java/com/example/mesaayuda/TicketAdapter.kt
    - Funcionalidades:
        * Visualización de tickets en RecyclerView
        * Manejo de eventos de clic
        * Actualización de datos en tiempo real

3. RECURSOS Y DISEÑO
-------------------

3.1. Layouts
    - activity_login.xml: Pantalla de inicio de sesión
    - activity_main.xml: Menú principal
    - activity_create_ticket.xml: Formulario de tickets
    - activity_search_ticket.xml: Búsqueda de tickets
    - activity_ticket_report.xml: Visualización de reportes

3.2. Recursos de Color
    - primary: #003366 (Azul corporativo)
    - accent: #FF6B00 (Naranja corporativo)
    - gray: #757575 (Gris corporativo)
    - Variaciones claras y oscuras para temas

3.3. Drawables
    - ic_trilogit_logo.xml: Logo vectorial de Trilogit
    - Otros recursos gráficos para la interfaz

4. TEMAS Y ESTILOS
-----------------

4.1. Tema Principal (Theme.MesaAyuda)
    - Basado en Material Design
    - Colores corporativos
    - Sin barra de acción
    - Estilos personalizados para botones y campos

4.2. Tema Nocturno
    - Adaptación oscura del tema principal
    - Contraste optimizado
    - Mantiene identidad corporativa

5. BASE DE DATOS
---------------

5.1. Estructura
    - Tabla: tickets
    - Campos principales:
        * _id (PRIMARY KEY)
        * title
        * description
        * status
        * priority
        * created_date
        * assigned_to

5.2. Operaciones
    - Inserción de nuevos tickets
    - Actualización de estado
    - Consultas y búsquedas
    - Generación de reportes

6. SEGURIDAD
------------

6.1. Autenticación
    - Sistema básico de login
    - Credenciales predeterminadas:
        * Usuario: admin
        * Contraseña: 1234

6.2. Validaciones
    - Entrada de datos en formularios
    - Permisos de acceso
    - Manejo de errores

7. REQUISITOS TÉCNICOS
---------------------

- Android SDK mínimo: API 21 (Android 5.0)
- Android SDK objetivo: API 33
- Kotlin version: 1.8.0
- Gradle version: 7.4.2
- Dependencias principales:
    * androidx.core:core-ktx
    * androidx.appcompat:appcompat
    * com.google.android.material:material
    * androidx.constraintlayout:constraintlayout

===========================================
Última actualización: [Fecha actual]
Versión de la aplicación: 1.0 