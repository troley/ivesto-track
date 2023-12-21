package com.example.investotrack.webapp.service;

import com.example.investotrack.dataprovidercore.CurrencyDataProvider;
import com.example.investotrack.webapp.viewmodel.CurrencyViewModel;
import com.example.investotrack.webapp.viewmodel.convertor.CurrencyModelConvertor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Locale;

@Service
class CurrencyDataProviderServiceImpl implements CurrencyDataProviderService {

    private final CurrencyDataProvider currencyDataProvider;
    private final CurrencyModelConvertor convertor;

    public CurrencyDataProviderServiceImpl(CurrencyDataProvider currencyDataProvider,
                                           CurrencyModelConvertor convertor) {
        this.currencyDataProvider = currencyDataProvider;
        this.convertor = convertor;
    }

    @Override
    @Cacheable("currencies")
    public Collection<CurrencyViewModel> listAllCurrencies() {
        return currencyDataProvider.listAllCurrencies().stream().map(convertor::toViewModel).toList();
    }

    @Override
    public Collection<CurrencyViewModel> findCaseInsensitiveCurrenciesBy(String query) {
        String ciq = query.toLowerCase(Locale.ROOT);
        return listAllCurrencies().stream()
                                  .filter(c -> lowerCased(c.name()).contains(ciq) ||
                                               lowerCased(c.symbol()).contains(ciq))
                                  .toList();
    }

    private String lowerCased(String str) {
        return str.toLowerCase(Locale.ROOT);
    }
}
