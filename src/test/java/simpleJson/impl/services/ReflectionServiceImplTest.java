package simpleJson.impl.services;

import model.ProductDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ReflectionServiceImplTest {

    ReflectionServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new ReflectionServiceImpl();

    }

    @Test
    void init() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<ProductDto> productDtoClass = ProductDto.class;
        Assertions.assertAll(
                () -> assertDoesNotThrow(() -> service.init(productDtoClass)),
                () -> assertNotNull(service.getTargetObject())
        );
    }

    @Test
    void getTargetObject() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<ProductDto> productDtoClass = ProductDto.class;
        service.init(productDtoClass);

        Assertions.assertAll(
                () -> assertNotNull(service.getTargetObject()),
                () -> assertInstanceOf(ProductDto.class, service.getTargetObject())
        );

    }

    @Test
    void getTargetClassByFieldName() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<ProductDto> productDtoClass = ProductDto.class;
        service.init(productDtoClass);
        assertInstanceOf(String.class, service.getTargetClassByFieldName("name").getTypeName());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6})
    void setValueToField(Double price) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<ProductDto> productDtoClass = ProductDto.class;
        service.init(productDtoClass);
        Assertions.assertAll(
                () -> assertDoesNotThrow(() -> service.setValueToField("price$", price)),
                () -> assertThrows(RuntimeException.class, () -> service.setValueToField("price", price))
        );
    }

    @Test
    void createInnerObjectByFieldName() throws InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        Class<ProductDto> productDtoClass = ProductDto.class;
        service.init(productDtoClass);
        assertDoesNotThrow(() -> service.createInnerObjectByFieldName("supplier"));
        ProductDto productDto = (ProductDto) service.getTargetObject();
        assertNotNull(productDto.getSupplier());
    }

    @Test
    void completeWorkWithNestedElement() throws InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        Class<ProductDto> productDtoClass = ProductDto.class;
        service.init(productDtoClass);
        service.createInnerObjectByFieldName("supplier");
        assertDoesNotThrow(() -> service.completeWorkWithNestedElement());
    }

    @Test
    void addElementToCurrentCollection() throws InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        Class<ProductDto> productDtoClass = ProductDto.class;
        service.init(productDtoClass);
        service.createInnerObjectByFieldName("availableFunctions");
        service.addElementToCurrentCollection("WiFi");
        service.addElementToCurrentCollection("NFC");
        service.addElementToCurrentCollection("GPS");
        ProductDto productDto = (ProductDto) service.getTargetObject();
        Assertions.assertAll(
                () -> assertEquals(3, productDto.getAvailableFunctions().size()),
                ()-> assertLinesMatch(List.of("WiFi", "NFC", "GPS"), productDto.getAvailableFunctions())
        );
        System.out.println(productDto.getAvailableFunctions());

    }


}