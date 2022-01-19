import com.techreturners.bubbleteaordersystem.model.*;
import com.techreturners.bubbleteaordersystem.service.BubbleTeaMessenger;
import com.techreturners.bubbleteaordersystem.service.BubbleTeaOrderService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import testhelper.DummySimpleLogger;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BubbleTeaOrderServiceSpyTest {

    private DebitCard testDebitCard;
    private PaymentDetails paymentDetails;
    private DummySimpleLogger dummySimpleLogger;
    private BubbleTeaMessenger spiedMessenger;
    private BubbleTeaOrderService bubbleTeaOrderService;

    @BeforeEach
    public void setup() {
        testDebitCard = new DebitCard("0123456789");
        paymentDetails = new PaymentDetails("hello kitty", "sanrio puroland", testDebitCard);
        dummySimpleLogger = new DummySimpleLogger();
        spiedMessenger = spy(new BubbleTeaMessenger(dummySimpleLogger));
        bubbleTeaOrderService = new BubbleTeaOrderService(dummySimpleLogger, spiedMessenger);
    }

    @ParameterizedTest
    @EnumSource(value = BubbleTeaTypeEnum.class)
    public void shouldCreateBubbleTeaOrderRequestWhenCreateOrderRequestIsCalled(BubbleTeaTypeEnum bubbleTeaTypeEnum) {

        //Arrange
        BubbleTea bubbleTea = new BubbleTea(bubbleTeaTypeEnum, 6.78);
        BubbleTeaRequest bubbleTeaRequest = new BubbleTeaRequest(paymentDetails, bubbleTea);

        BubbleTeaOrderRequest expectedResult = new BubbleTeaOrderRequest(
                "hello kitty",
                "sanrio puroland",
                "0123456789",
                bubbleTeaTypeEnum
        );

        //Act
        BubbleTeaOrderRequest result = bubbleTeaOrderService.createOrderRequest(bubbleTeaRequest);

        //Assert
        assertEquals(expectedResult.getName(), result.getName());
        assertEquals(expectedResult.getAddress(), result.getAddress());
        assertEquals(expectedResult.getDebitCardDigits(), result.getDebitCardDigits());
        assertEquals(expectedResult.getBubbleTeaType(), result.getBubbleTeaType());

        //Check the spied messenger was called with BubbleTeaOrderRequest result
        verify(spiedMessenger).sendBubbleTeaOrderRequestEmail(result);
        verify(spiedMessenger, times(1)).sendBubbleTeaOrderRequestEmail(result);
    }

}
