# Capa de Dominio (`domain`) - Arquitectura Hexagonal

Este paquete contiene el núcleo de reglas de negocio de la Red Social, aislado por completo de frameworks, bases de datos o librerías de interfaz gráfica.

## Estructura de Subpaquetes
- **`model/`**: Contiene la Raíz de Agregado (`Articulo`) y los Value Objects inmutables (`Email`, `ArticuloId`, `UsuarioId`, `TituloArticulo`).
- **`exception/`**: Excepciones de negocio explícitas (`InvalidEmailException`, `ArticuloRechazadoException`, `PublicacionNoPermitidaException`).
- **`service/`**: Servicios de dominio sin estado (`ModeracionContenidoService`) para reglas que aplican a múltiples entidades.
- **`event/`**: Eventos de dominio (`ArticuloPublicadoEvent`, `ArticuloRechazadoEvent`) que registran hechos ocurridos en el ciclo de vida de los agregados.
- **`factory/`**: Implementación del patrón Factory (`ArticuloFactory`) para encapsular la creación válida de agregados.
- **`specification/`**: Implementación del patrón Specification (`ArticuloVisibleSpecification`) para evaluar criterios de negocio reutilizables.

## Garantía de Aislamiento
Ninguna clase dentro de este paquete importa anotaciones de JPA, Spring, Swing o JDBC. Toda la lógica depende exclusivamente de Java estándar (`java.*`).