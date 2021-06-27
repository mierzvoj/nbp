package com.pjatk.nbpApp.controller;

import com.pjatk.nbpApp.model.NbpResponse;
import com.pjatk.nbpApp.service.NbpService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nbp")
public class NbpController {
    private final NbpService nbpService;

    public NbpController(NbpService nbpService) {
        this.nbpService = nbpService;
    }
    @ApiOperation(value = "get rates", response = NbpResponse.class, notes = "This method will return currency calculations")


    @GetMapping(value = "/{currency}/calculate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NbpResponse> calculateRootForCurrency(
            @ApiParam(name = "currency",
                    type = "String",
                    value = "currency",
                    required = true,
                    defaultValue = "not cheap")

            @PathVariable String currency, @RequestParam(defaultValue = "1")
            int numberOfDays) {
        return ResponseEntity.ok(nbpService.calculateRootForCurrency(currency, numberOfDays));
    }
}
