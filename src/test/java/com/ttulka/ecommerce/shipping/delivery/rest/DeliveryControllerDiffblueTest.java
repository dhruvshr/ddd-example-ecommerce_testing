package com.ttulka.ecommerce.shipping.delivery.rest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ttulka.ecommerce.shipping.delivery.Delivery;
import com.ttulka.ecommerce.shipping.delivery.DeliveryId;
import com.ttulka.ecommerce.shipping.delivery.DeliveryInfo;
import com.ttulka.ecommerce.shipping.delivery.DeliveryInfos;
import com.ttulka.ecommerce.shipping.delivery.FindDeliveries;
import com.ttulka.ecommerce.shipping.delivery.OrderId;

import java.util.ArrayList;
import java.util.stream.Stream;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {DeliveryController.class, FindDeliveries.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class DeliveryControllerDiffblueTest {
    @Autowired
    private DeliveryController deliveryController;

    @MockBean
    private FindDeliveries findDeliveries;

    /**
     * Method under test: {@link DeliveryController#all()}
     */
    @Test
    void testAll() throws Exception {
        // Arrange
        DeliveryInfos deliveryInfos = mock(DeliveryInfos.class);

        ArrayList<DeliveryInfo> deliveryInfoList = new ArrayList<>();
        Stream<DeliveryInfo> streamResult = deliveryInfoList.stream();
        when(deliveryInfos.stream()).thenReturn(streamResult);
        when(findDeliveries.all()).thenReturn(deliveryInfos);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/delivery");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(deliveryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link DeliveryController#all()}
     */
    @Test
    void testAll2() throws Exception {
        // Arrange
        ArrayList<DeliveryInfo> deliveryInfoList = new ArrayList<>();
        DeliveryId deliveryId = new DeliveryId("Id");
        deliveryInfoList.add(new DeliveryInfo(deliveryId, new OrderId("Id")));
        Stream<DeliveryInfo> streamResult = deliveryInfoList.stream();
        DeliveryInfos deliveryInfos = mock(DeliveryInfos.class);
        when(deliveryInfos.stream()).thenReturn(streamResult);
        when(findDeliveries.all()).thenReturn(deliveryInfos);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/delivery");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(deliveryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[{\"orderId\":\"Id\",\"id\":\"Id\"}]"));
    }

    /**
     * Method under test: {@link DeliveryController#all()}
     */
    @Test
    void testAll3() throws Exception {
        // Arrange
        ArrayList<DeliveryInfo> deliveryInfoList = new ArrayList<>();
        DeliveryId deliveryId = new DeliveryId("Id");
        deliveryInfoList.add(new DeliveryInfo(deliveryId, new OrderId("Id")));
        DeliveryId deliveryId2 = new DeliveryId("Id");
        deliveryInfoList.add(new DeliveryInfo(deliveryId2, new OrderId("Id")));
        Stream<DeliveryInfo> streamResult = deliveryInfoList.stream();
        DeliveryInfos deliveryInfos = mock(DeliveryInfos.class);
        when(deliveryInfos.stream()).thenReturn(streamResult);
        when(findDeliveries.all()).thenReturn(deliveryInfos);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/delivery");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(deliveryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("[{\"orderId\":\"Id\",\"id\":\"Id\"},{\"orderId\":\"Id\",\"id\":\"Id\"}]"));
    }

    /**
     * Method under test: {@link DeliveryController#byOrder(Object)}
     */
    @Test
    void testByOrder() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange, Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> (new DeliveryController(mock(FindDeliveries.class))).byOrder(null));
    }

    /**
     * Method under test: {@link DeliveryController#byOrder(Object)}
     */
    @Test
    void testByOrder2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        Delivery delivery = mock(Delivery.class);
        when(delivery.address()).thenThrow(new IllegalArgumentException("id"));
        when(delivery.id()).thenReturn(new DeliveryId("Id"));
        FindDeliveries findDeliveries = mock(FindDeliveries.class);
        when(findDeliveries.byOrder(Mockito.<OrderId>any())).thenReturn(delivery);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> (new DeliveryController(findDeliveries)).byOrder("Order Id"));
        verify(delivery).address();
        verify(delivery).id();
        verify(findDeliveries).byOrder(isA(OrderId.class));
    }

    /**
     * Method under test: {@link DeliveryController#byOrder(Object)}
     */
    @Test
    void testByOrder3() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        Delivery delivery = mock(Delivery.class);
        when(delivery.address()).thenThrow(new IllegalArgumentException("id"));
        when(delivery.id()).thenReturn(new DeliveryId("Id"));
        FindDeliveries findDeliveries = mock(FindDeliveries.class);
        when(findDeliveries.byOrder(Mockito.<OrderId>any())).thenReturn(delivery);
        DeliveryController deliveryController = new DeliveryController(findDeliveries);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> deliveryController.byOrder(new DeliveryId("Id")));
        verify(delivery).address();
        verify(delivery).id();
        verify(findDeliveries).byOrder(isA(OrderId.class));
    }

    /**
     * Method under test: {@link DeliveryController#byOrder(Object)}
     */
    @Test
    void testByOrder4() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        Delivery delivery = mock(Delivery.class);
        when(delivery.id()).thenThrow(new IllegalArgumentException("foo"));
        FindDeliveries findDeliveries = mock(FindDeliveries.class);
        when(findDeliveries.byOrder(Mockito.<OrderId>any())).thenReturn(delivery);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> (new DeliveryController(findDeliveries)).byOrder("42"));
        verify(delivery).id();
        verify(findDeliveries).byOrder(isA(OrderId.class));
    }

    /**
     * Method under test: {@link DeliveryController#byOrder(Object)}
     */
    @Test
    void testByOrder5() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        FindDeliveries findDeliveries = mock(FindDeliveries.class);
        when(findDeliveries.byOrder(Mockito.<OrderId>any())).thenThrow(new IllegalArgumentException("foo"));

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> (new DeliveryController(findDeliveries)).byOrder(1));
        verify(findDeliveries).byOrder(isA(OrderId.class));
    }

    /**
     * Method under test: {@link DeliveryController#byOrder(Object)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testByOrder6() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@2ecf8486 testClass = com.ttulka.ecommerce.shipping.delivery.rest.DiffblueFakeClass192, locations = [], classes = [com.ttulka.ecommerce.shipping.delivery.rest.DeliveryController, com.ttulka.ecommerce.shipping.delivery.FindDeliveries], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@680b8b03, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@6a388274, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@0, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@73737f31], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/delivery/order/{orderId}", "Order Id");

        // Act
        MockMvcBuilders.standaloneSetup(deliveryController).build().perform(requestBuilder);
    }
}
