@echo off
:: Cambia a la ruta de destino
cd /d C:\ruta\reservation-service

:: Crear las carpetas necesarias
mkdir controller
mkdir service
mkdir repository
mkdir dto
mkdir config
mkdir entity

:: Crear los archivos en cada carpeta
cd controller
echo // Controladores REST para reservas > ReservationController.java
cd ..

cd service
echo // Lógica de negocio para reservas > ReservationService.java
cd ..

cd repository
echo // Acceso a datos para reservas > ReservationRepository.java
cd ..

cd dto
echo // Objetos de transferencia de datos para reservas > ReservationDto.java
cd ..

cd config
echo // Configuración de base de datos para reservas > DatabaseConfig.java
cd ..

cd entity
echo // Entidades JPA relacionadas con reservas > Reservation.java
cd ..

@echo Carpetas y archivos para reservation-service creados correctamente.
pause
