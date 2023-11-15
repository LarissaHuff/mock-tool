package com.mocktool.service;

import com.mocktool.dto.FilterMockDTO;
import com.mocktool.dto.MockDTO;
import com.mocktool.exception.NotFoundException;
import com.mocktool.model.Mock;
import com.mocktool.repository.MockRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MockService {

    private final MockRepository mockRepository;
    private final ModelMapper modelMapper;

    public void create(MockDTO mockDTO) {
        Mock mock = modelMapper.map(mockDTO, Mock.class);
        mock.setIsActive(false);
        mockRepository.save(mock);
    }

    public void setStatus(Long id, Boolean status) {
        Mock mock = mockRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mock not found."));

        if (status && thereIsAnotherActiveMockForTheSameParameters(mock)) {
            throw new RuntimeException("There is already a mock active for the same service and environment, " +
                    "please deactivate it before activating a new one.");
        }

        mock.setIsActive(status);

        mockRepository.save(mock);
    }

    public List<Mock> findAll() {
        return mockRepository.findAll();
    }

    public String getMockedResponse(FilterMockDTO filterMockDTO) {
        return mockRepository.findAllByServiceAndEnvironmentAndIsActive(
                        filterMockDTO.getService(), filterMockDTO.getEnvironment(), true)
                .stream()
                .filter(it -> it.getRequest().equals(filterMockDTO.getRequest()))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("There is no active mock."))
                .getResponse();
    }

    private boolean thereIsAnotherActiveMockForTheSameParameters(Mock mock) {
        return !mockRepository.findAllByServiceAndEnvironmentAndIsActive(
                mock.getService(), mock.getEnvironment(), true).isEmpty();
    }
}
