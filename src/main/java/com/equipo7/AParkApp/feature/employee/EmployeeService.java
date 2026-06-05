package com.equipo7.AParkApp.feature.employee;

import com.equipo7.AParkApp.feature.employee.domain.dto.EmployeeRequest;
import com.equipo7.AParkApp.feature.employee.domain.dto.EmployeeResponse;
import com.equipo7.AParkApp.feature.employee.domain.mapper.EmployeeMapper;
import com.equipo7.AParkApp.feature.employee.domain.mapper.EmployeeRequestMapper;
import com.equipo7.AParkApp.feature.employee.domain.mapper.EmployeeResponseMapper;
import com.equipo7.AParkApp.feature.parkingLot.ParkingLotEntity;
import com.equipo7.AParkApp.feature.user.UserEntity;
import com.equipo7.AParkApp.feature.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeService implements IEmployeeService{
    private final EmployeeRepository repository;
    private final UserRepository userRepository;
    private final ParkingLotRepository parkingRepository;
    private final EmployeeMapper mapper;

    @Override
    @Transactional
    public EmployeeResponse create(EmployeeRequest request) {
        UserEntity user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        ParkingLotEntity parking = parkingRepository.findById(request.getParkingLotId())
                .orElseThrow(() -> new EntityNotFoundException("Estacionamiento no encontrado"));

        Employee employee = mapper.toEntity(request);
        employee.setUser(user);
        employee.setParkingLot(parking);

        return mapper.toDTO(repository.save(employee));
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeResponse> getAllEmployees() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeResponse getEmployeeById(UUID id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Employee Not Found"));
    }

    @Override
    @Transactional
    public EmployeeResponse update(UUID id, EmployeeRequest request) {
        Employee entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee Not Found"));

        UserEntity user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        ParkingLotEntity parking = parkingRepository.findById(request.getParkingLotId())
                .orElseThrow(() -> new EntityNotFoundException("Parking lot not found"));

        entity.setUser(user);
        entity.setParkingLot(parking);
        entity.setNotes(request.getNotes());
        entity.setSalary(request.getSalary());
        entity.setRole(request.getRole());

        return mapper.toDTO(repository.save(entity));
    }
}
