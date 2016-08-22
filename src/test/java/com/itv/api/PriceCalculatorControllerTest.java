package com.itv.api;

import com.google.gson.Gson;
import com.itv.api.model.PricingRequest;
import com.itv.domain.mappers.IItemMapper;
import com.itv.domain.mappers.IPricingRuleMapper;
import com.itv.domain.model.CalculatedPrice;
import com.itv.domain.model.Item;
import com.itv.domain.model.ItemIdentifier;
import com.itv.domain.model.PricingRule;
import com.itv.domain.model.UnitPrice;
import com.itv.domain.services.IPriceCalculator;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anySet;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PriceCalculatorControllerTest
{
    @Mock
    private IPriceCalculator priceCalculator;

    @Mock
    private IItemMapper itemMapper;

    @Mock
    private IPricingRuleMapper pricingRuleMapper;

    @InjectMocks
    private PriceCalculatorController priceCalculatorController;

    private MockMvc mockMvc;

    @BeforeMethod
    public void setup()
    {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(priceCalculatorController).build();
    }

    @Test
    public void calculatePriceWithNotApplicablePricingRule() throws Exception {

        BigDecimal calculatedPrice = new BigDecimal(100);

        when(priceCalculator.calculate(anyList(), anySet()))
                .thenReturn(new CalculatedPrice(calculatedPrice));

        when(itemMapper.map(any(PricingRequest.class)))
                .thenReturn(Arrays.asList(new Item(new ItemIdentifier("A"), 2, new UnitPrice(new BigDecimal(50)))));

        when(pricingRuleMapper.map(any(PricingRequest.class)))
                .thenReturn(new HashSet<>(Arrays.asList(new PricingRule(3, new BigDecimal(130), new ItemIdentifier("A")))));



        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.post("/calculatePrice")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createRequest(false));

        this.mockMvc.perform(builder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string("{\"price\":" + calculatedPrice + "}"));

    }

    @Test
    public void calculatePriceWithApplicablePricingRule() throws Exception {

        BigDecimal calculatedPrice = new BigDecimal(130);

        when(priceCalculator.calculate(anyList(), anySet()))
                .thenReturn(new CalculatedPrice(calculatedPrice));

        when(itemMapper.map(any(PricingRequest.class)))
                .thenReturn(Arrays.asList(new Item(new ItemIdentifier("A"), 3, new UnitPrice(new BigDecimal(50)))));

        when(pricingRuleMapper.map(any(PricingRequest.class)))
                .thenReturn(new HashSet<>(Arrays.asList(new PricingRule(3, new BigDecimal(130), new ItemIdentifier("A")))));



        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.post("/calculatePrice")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createRequest(true));

        this.mockMvc.perform(builder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string("{\"price\":" + calculatedPrice + "}"));

    }

    private String createRequest(boolean isPricingRuleApplicable)
    {
        PricingRequest pricingRequest = new PricingRequest();

        List<com.itv.api.model.Item> items = new ArrayList<>();
        com.itv.api.model.Item item = new com.itv.api.model.Item("A", new BigDecimal(50));
        items.add(item);
        items.add(item);

        if (isPricingRuleApplicable)
        {
            items.add(item);
        }

        Set<com.itv.api.model.PricingRule> pricingRules = new HashSet<>();
        com.itv.api.model.PricingRule pricingRuleA = new com.itv.api.model.PricingRule("A", 3, new BigDecimal(130));
        com.itv.api.model.PricingRule pricingRuleB = new com.itv.api.model.PricingRule("B", 2, new BigDecimal(45));
        pricingRules.add(pricingRuleA);
        pricingRules.add(pricingRuleB);

        pricingRequest.setItems(items);
        pricingRequest.setPricingRules(pricingRules);

        Gson gson = new Gson();

        return gson.toJson(pricingRequest);
    }
}
