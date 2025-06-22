package org.javasource.controllers;

import org.javasource.models.dto.PredictionRequest;
import org.javasource.services.PythonMLService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ml")
public class PythonMLController {

    private final PythonMLService pythonMLService;

    public PythonMLController(PythonMLService pythonMLService) {
        this.pythonMLService = pythonMLService;
    }

    @PostMapping("/predict")
    public String predict(@RequestBody PredictionRequest input) {
        return pythonMLService.callPython(input);
    }
}
