package com.equipo7.AParkApp.feature.auth.config;

import com.equipo7.AParkApp.feature.VehicleType.VehicleTypeEnum;
import com.equipo7.AParkApp.feature.address.AddressEntity;
import com.equipo7.AParkApp.feature.address.AddressRepository;
import com.equipo7.AParkApp.feature.auth.credentials.CredentialsEntity;
import com.equipo7.AParkApp.feature.auth.credentials.CredentialsRepository;
import com.equipo7.AParkApp.feature.auth.permissions.*;
import com.equipo7.AParkApp.feature.offer.OfferEntity;
import com.equipo7.AParkApp.feature.offer.OfferRepository;
import com.equipo7.AParkApp.feature.parkingLot.Domain.ParkingLotEntity;
import com.equipo7.AParkApp.feature.parkingLot.IParkingLotRepository;
import com.equipo7.AParkApp.feature.parkingSpot.Domain.ParkingSpotEntity;
import com.equipo7.AParkApp.feature.parkingSpot.Domain.Status;
import com.equipo7.AParkApp.feature.parkingSpot.IParkingSpotRepository;
import com.equipo7.AParkApp.feature.price.PriceEntity;
import com.equipo7.AParkApp.feature.price.PriceRepository;
import com.equipo7.AParkApp.feature.stay.StayType;
import com.equipo7.AParkApp.feature.user.UserEntity;
import com.equipo7.AParkApp.feature.user.UserRepository;
import com.equipo7.AParkApp.feature.vehicle.VehicleEntity;
import com.equipo7.AParkApp.feature.vehicle.VehicleRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Configuration
public class DatabaseInitializerConfig {

    @Bean
    @Transactional
    public CommandLineRunner initDatabase(
            PermitRepository permitRepository,
            RoleRepository roleRepository,
            CredentialsRepository credentialsRepository,
            UserRepository userRepository,
            AddressRepository addressRepository,
            IParkingLotRepository parkingLotRepository,
            IParkingSpotRepository parkingSpotRepository,
            OfferRepository offerRepository,
            VehicleRepository vehicleRepository,
            PriceRepository priceRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {
            String passwordPlano = "password123";
            String passwordEncriptada = passwordEncoder.encode(passwordPlano);

            // 1. Cargar Permisos y Roles básicos si no existen
            RoleEntity roleAdmin = null;
            RoleEntity roleOwner = null;
            RoleEntity roleClient = null;
            RoleEntity roleEmployee = null;

            if (permitRepository.count() == 0) {
                System.out.println(">> Cargando permisos y roles iniciales...");

                PermitEntity crearCuenta = permitRepository.save(PermitEntity.builder().permit(Permits.ACTUALIZAR_CUENTA).build());
                PermitEntity verUsuarios = permitRepository.save(PermitEntity.builder().permit(Permits.ELIMINAR_USUARIO).build());

                roleClient = new RoleEntity(Roles.ROLE_CLIENT);
                roleClient.getPermits().add(crearCuenta);
                roleClient = roleRepository.save(roleClient);

                roleAdmin = new RoleEntity(Roles.ROLE_ADMIN);
                roleAdmin.getPermits().add(crearCuenta);
                roleAdmin.getPermits().add(verUsuarios);
                roleAdmin = roleRepository.save(roleAdmin);

                roleOwner = new RoleEntity(Roles.ROLE_OWNER);
                roleOwner.getPermits().add(crearCuenta);
                roleOwner.getPermits().add(verUsuarios);
                roleOwner = roleRepository.save(roleOwner);

                roleEmployee = new RoleEntity(Roles.ROLE_EMPLOYEE);
                roleEmployee.getPermits().add(crearCuenta);
                roleEmployee.getPermits().add(verUsuarios);
                roleEmployee = roleRepository.save(roleEmployee);

                // Usuario Admin
                UserEntity adminUser = UserEntity.builder().email("admin@example.com").name("mariano").active(true).build();
                userRepository.save(adminUser);

                CredentialsEntity adminCreds = new CredentialsEntity();
                adminCreds.setUsername("admin");
                adminCreds.setPassword(passwordEncriptada);
                adminCreds.setEnabled(true);
                adminCreds.setUsuario(adminUser);
                adminCreds.getRoles().add(roleAdmin);
                credentialsRepository.save(adminCreds);

                // Usuario Owner por defecto
                UserEntity ownerUser = UserEntity.builder().email("owner@example.com").name("casca").active(true).build();
                userRepository.save(ownerUser);

                CredentialsEntity ownerCreds = new CredentialsEntity();
                ownerCreds.setUsername("owner");
                ownerCreds.setPassword(passwordEncriptada);
                ownerCreds.setEnabled(true);
                ownerCreds.setUsuario(ownerUser);
                ownerCreds.getRoles().add(roleOwner);
                credentialsRepository.save(ownerCreds);

                // Usuario Client por defecto
                UserEntity clientUser = UserEntity.builder()
                        .email("client@example.com")
                        .name("juan")
                        .active(true)
                        .build();
                userRepository.save(clientUser);

                CredentialsEntity clientCreds = new CredentialsEntity();
                clientCreds.setUsername("client");
                clientCreds.setPassword(passwordEncriptada);
                clientCreds.setEnabled(true);
                clientCreds.setUsuario(clientUser);
                clientCreds.getRoles().add(roleClient);
                credentialsRepository.save(clientCreds);
            } else {
                roleOwner = roleRepository.findAll().stream()
                        .filter(r -> r.getRole() == Roles.ROLE_OWNER)
                        .findFirst()
                        .orElse(null);
                roleClient = roleRepository.findAll().stream()
                        .filter(r -> r.getRole() == Roles.ROLE_CLIENT)
                        .findFirst()
                        .orElse(null);
            }

            // 2. Crear Owners adicionales de prueba si no existen
            UserEntity owner1;
            Optional<CredentialsEntity> owner1CredsOpt = credentialsRepository.findByUsername("owner1");
            if (owner1CredsOpt.isEmpty()) {
                owner1 = UserEntity.builder()
                        .email("owner1@example.com")
                        .name("Carlos Owner")
                        .active(true)
                        .build();
                userRepository.save(owner1);

                CredentialsEntity owner1Creds = new CredentialsEntity();
                owner1Creds.setUsername("owner1");
                owner1Creds.setPassword(passwordEncriptada);
                owner1Creds.setEnabled(true);
                owner1Creds.setUsuario(owner1);
                if (roleOwner != null) owner1Creds.getRoles().add(roleOwner);
                credentialsRepository.save(owner1Creds);
            } else {
                owner1 = owner1CredsOpt.get().getUsuario();
            }

            UserEntity owner2;
            Optional<CredentialsEntity> owner2CredsOpt = credentialsRepository.findByUsername("owner2");
            if (owner2CredsOpt.isEmpty()) {
                owner2 = UserEntity.builder()
                        .email("owner2@example.com")
                        .name("Sofia Owner")
                        .active(true)
                        .build();
                userRepository.save(owner2);

                CredentialsEntity owner2Creds = new CredentialsEntity();
                owner2Creds.setUsername("owner2");
                owner2Creds.setPassword(passwordEncriptada);
                owner2Creds.setEnabled(true);
                owner2Creds.setUsuario(owner2);
                if (roleOwner != null) owner2Creds.getRoles().add(roleOwner);
                credentialsRepository.save(owner2Creds);
            } else {
                owner2 = owner2CredsOpt.get().getUsuario();
            }

            // 3. Crear Estacionamientos, Plazas y Ofertas de prueba si no existen
            boolean existsDemoLots = parkingLotRepository.findAll().stream()
                    .anyMatch(p -> "Estacionamiento Central".equals(p.getName()) || "Parking Santa Fe".equals(p.getName()));

            if (!existsDemoLots) {
                System.out.println(">> Cargando estacionamientos, plazas y ofertas de prueba...");

                // Direcciones
                AddressEntity addr1 = AddressEntity.builder()
                        .street("Av. Corrientes")
                        .number(1234)
                        .zipCode("C1043")
                        .notes("Zona Centro")
                        .build();
                addr1 = addressRepository.save(addr1);

                AddressEntity addr2 = AddressEntity.builder()
                        .street("Av. Santa Fe")
                        .number(2500)
                        .zipCode("C1425")
                        .notes("Frente al shopping")
                        .build();
                addr2 = addressRepository.save(addr2);

                // Estacionamientos
                ParkingLotEntity lot1 = ParkingLotEntity.builder()
                        .name("Estacionamiento Central")
                        .capacity(5)
                        .active(true)
                        .owner(owner1)
                        .address(addr1)
                        .build();
                lot1 = parkingLotRepository.save(lot1);

                ParkingLotEntity lot2 = ParkingLotEntity.builder()
                        .name("Parking Santa Fe")
                        .capacity(5)
                        .active(true)
                        .owner(owner2)
                        .address(addr2)
                        .build();
                lot2 = parkingLotRepository.save(lot2);

                // Plazas para Estacionamiento 1 (números 1 al 5)
                List<ParkingSpotEntity> spotsLot1 = new ArrayList<>();
                for (int i = 1; i <= 5; i++) {
                    ParkingSpotEntity spot = ParkingSpotEntity.builder()
                            .name("A-" + i)
                            .number(i)
                            .status(i % 2 == 0 ? Status.OCCUPIED : Status.FREE)
                            .active(true)
                            .parkingLot(lot1)
                            .build();
                    spotsLot1.add(parkingSpotRepository.save(spot));
                }

                // Plazas para Estacionamiento 2 (números 6 al 10)
                List<ParkingSpotEntity> spotsLot2 = new ArrayList<>();
                for (int i = 6; i <= 10; i++) {
                    ParkingSpotEntity spot = ParkingSpotEntity.builder()
                            .name("B-" + (i - 5))
                            .number(i)
                            .status(i == 8 ? Status.OCCUPIED : Status.FREE)
                            .active(true)
                            .parkingLot(lot2)
                            .build();
                    spotsLot2.add(parkingSpotRepository.save(spot));
                }

                // Ofertas de prueba
                LocalDateTime now = LocalDateTime.now();

                // Oferta 1: Por hora vinculada al Spot A-1
                OfferEntity offer1 = OfferEntity.builder()
                        .parkingLot(lot1)
                        .parkingSpot(spotsLot1.get(0))
                        .stayType(StayType.Hour)
                        .startTime(now)
                        .endTime(now.plusDays(30))
                        .active(true)
                        .build();
                offerRepository.save(offer1);

                // Oferta 2: Por día general para el estacionamiento Central
                OfferEntity offer2 = OfferEntity.builder()
                        .parkingLot(lot1)
                        .parkingSpot(null)
                        .stayType(StayType.Day)
                        .startTime(now)
                        .endTime(now.plusDays(30))
                        .active(true)
                        .build();
                offerRepository.save(offer2);

                // Oferta 3: Por semana vinculada al Spot B-1
                OfferEntity offer3 = OfferEntity.builder()
                        .parkingLot(lot2)
                        .parkingSpot(spotsLot2.get(0))
                        .stayType(StayType.Week)
                        .startTime(now)
                        .endTime(now.plusDays(60))
                        .active(true)
                        .build();
                offerRepository.save(offer3);

                // Oferta 4: Por mes general para el parking Santa Fe
                OfferEntity offer4 = OfferEntity.builder()
                        .parkingLot(lot2)
                        .parkingSpot(null)
                        .stayType(StayType.Month)
                        .startTime(now)
                        .endTime(now.plusMonths(3))
                        .active(true)
                        .build();
                offerRepository.save(offer4);

                System.out.println(">> ¡Estacionamientos, plazas y ofertas cargados exitosamente!");
            }

            // 4. Crear cliente de prueba con vehículo si no existe
            RoleEntity finalRoleClient = roleClient != null ? roleClient : roleRepository.findAll().stream()
                    .filter(r -> r.getRole() == Roles.ROLE_CLIENT)
                    .findFirst()
                    .orElse(null);

            UserEntity client1;
            Optional<CredentialsEntity> client1CredsOpt = credentialsRepository.findByUsername("client1");
            if (client1CredsOpt.isEmpty()) {
                client1 = UserEntity.builder()
                        .email("client1@example.com")
                        .name("Lucas Cliente")
                        .active(true)
                        .build();
                userRepository.save(client1);

                CredentialsEntity client1Creds = new CredentialsEntity();
                client1Creds.setUsername("client1");
                client1Creds.setPassword(passwordEncriptada);
                client1Creds.setEnabled(true);
                client1Creds.setUsuario(client1);
                if (finalRoleClient != null) {
                    client1Creds.getRoles().add(finalRoleClient);
                }
                credentialsRepository.save(client1Creds);
            } else {
                client1 = client1CredsOpt.get().getUsuario();
            }

            if (!vehicleRepository.existsByPlate("AA123BB")) {
                VehicleEntity vehicle1 = VehicleEntity.builder()
                        .plate("AA123BB")
                        .brand("Toyota")
                        .model("Corolla")
                        .color("Blanco")
                        .vehicleType(VehicleTypeEnum.Car)
                        .note("Vehículo de prueba de cliente")
                        .user(client1)
                        .build();
                vehicleRepository.save(vehicle1);
            }

            // Asegurar también que el usuario "client" por defecto tenga un vehículo
            Optional<CredentialsEntity> defaultClientOpt = credentialsRepository.findByUsername("client");
            if (defaultClientOpt.isPresent() && !vehicleRepository.existsByPlate("AF987XX")) {
                VehicleEntity defaultVehicle = VehicleEntity.builder()
                        .plate("AF987XX")
                        .brand("Volkswagen")
                        .model("Gol")
                        .color("Gris")
                        .vehicleType(VehicleTypeEnum.Car)
                        .note("Auto particular de Juan")
                        .user(defaultClientOpt.get().getUsuario())
                        .build();
                vehicleRepository.save(defaultVehicle);
            }

            // 5. Sembrar tarifas base si no existen
            System.out.println(">> Cargando tarifas base de precios...");

            // Car
            seedPriceIfAbsent(priceRepository, VehicleTypeEnum.Car, StayType.Hour, 1000.0);
            seedPriceIfAbsent(priceRepository, VehicleTypeEnum.Car, StayType.Day, 8000.0);
            seedPriceIfAbsent(priceRepository, VehicleTypeEnum.Car, StayType.Week, 30000.0);
            seedPriceIfAbsent(priceRepository, VehicleTypeEnum.Car, StayType.Month, 90000.0);

            // MotorCycle
            seedPriceIfAbsent(priceRepository, VehicleTypeEnum.MotorCycle, StayType.Hour, 500.0);
            seedPriceIfAbsent(priceRepository, VehicleTypeEnum.MotorCycle, StayType.Day, 4000.0);
            seedPriceIfAbsent(priceRepository, VehicleTypeEnum.MotorCycle, StayType.Week, 15000.0);
            seedPriceIfAbsent(priceRepository, VehicleTypeEnum.MotorCycle, StayType.Month, 45000.0);

            // SUV (tarifas proporcionales a Car)
            seedPriceIfAbsent(priceRepository, VehicleTypeEnum.SUV, StayType.Hour, 1200.0);
            seedPriceIfAbsent(priceRepository, VehicleTypeEnum.SUV, StayType.Day, 10000.0);
            seedPriceIfAbsent(priceRepository, VehicleTypeEnum.SUV, StayType.Week, 36000.0);
            seedPriceIfAbsent(priceRepository, VehicleTypeEnum.SUV, StayType.Month, 110000.0);

            // Truck (tarifas proporcionales a Car)
            seedPriceIfAbsent(priceRepository, VehicleTypeEnum.Truck, StayType.Hour, 1500.0);
            seedPriceIfAbsent(priceRepository, VehicleTypeEnum.Truck, StayType.Day, 12000.0);
            seedPriceIfAbsent(priceRepository, VehicleTypeEnum.Truck, StayType.Week, 45000.0);
            seedPriceIfAbsent(priceRepository, VehicleTypeEnum.Truck, StayType.Month, 135000.0);

            // Custom (tarifas equivalentes a Car)
            seedPriceIfAbsent(priceRepository, VehicleTypeEnum.Custom, StayType.Hour, 1000.0);
            seedPriceIfAbsent(priceRepository, VehicleTypeEnum.Custom, StayType.Day, 8000.0);
            seedPriceIfAbsent(priceRepository, VehicleTypeEnum.Custom, StayType.Week, 30000.0);
            seedPriceIfAbsent(priceRepository, VehicleTypeEnum.Custom, StayType.Month, 90000.0);

            System.out.println(">> Base de datos inicializada correctamente.");
        };
    }

    private void seedPriceIfAbsent(PriceRepository repo, VehicleTypeEnum vehicleType, StayType stayType, Double price) {
        if (repo.findByVehicleTypeAndStayType(vehicleType, stayType).isEmpty()) {
            repo.save(PriceEntity.builder()
                    .vehicleType(vehicleType)
                    .stayType(stayType)
                    .price(price)
                    .build());
        }
    }
}

