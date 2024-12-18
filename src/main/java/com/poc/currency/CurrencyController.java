package com.poc.currency;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping("")
    public ResponseEntity<CurrencyModel[]> getCurrencies() {

        return ResponseEntity.ok(currencyService.getExchangeRate());
    }
}
