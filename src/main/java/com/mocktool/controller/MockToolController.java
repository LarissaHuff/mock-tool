package com.mocktool.controller;

import com.mocktool.dto.FilterMockDTO;
import com.mocktool.dto.MockDTO;
import com.mocktool.service.MockService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("mock")
public class MockToolController {

    private final MockService mockService;
    private final ModelMapper modelMapper;

    @PostMapping
    public void create(@RequestBody MockDTO mockDTO) {
        mockService.create(mockDTO);
    }

    @PostMapping("/response")
    public String getMockedResponse(@RequestBody FilterMockDTO filterMockDTO) {
        return mockService.getMockedResponse(filterMockDTO);
    }

    @PatchMapping("/{id}")
    public void setStatus(@PathVariable Long id, @RequestParam Boolean status) {
        mockService.setStatus(id, status);
    }

    @GetMapping
    public List<MockDTO> findAll() {
        return mockService.findAll().stream()
                .map(it -> modelMapper.map(it, MockDTO.class))
                .collect(Collectors.toList());
    }
}
