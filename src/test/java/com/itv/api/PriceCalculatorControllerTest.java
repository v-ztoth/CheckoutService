package com.itv.api;

import com.itv.domain.mappers.ItemMapper;
import com.itv.domain.mappers.PricingRuleMapper;
import com.itv.services.PriceCalculator;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PriceCalculatorControllerTest
{
    @Mock
    private PriceCalculator priceCalculator;

    @Mock
    private ItemMapper itemMapper;

    @Mock
    private PricingRuleMapper pricingRuleMapper;

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
    public void calculatePriceTest() throws Exception {

//        BigDecimal calculatedPrice = new BigDecimal(100);
//
//        when(priceCalculator.calculate(any(Item.class), any(Optional.class)))
//                .thenReturn(new CalculatedPrice(calculatedPrice));
//
//        when(itemMapper.map(any(PricingRequest.class)))
//                .thenReturn(new Item(new ItemIdentifier("A"), 2, unitPrice));
//
//        when(pricingRuleMapper.map(any(PricingRequest.class)))
//                .thenReturn(Optional.empty());
//
//        this.mockMvc.perform(get("/calculatePrice")
//                    .param("itemIdentifier", "A")
//                    .param("itemCount", "3"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(content().string("{\"price\":" + calculatedPrice + "}"));

    }
}
