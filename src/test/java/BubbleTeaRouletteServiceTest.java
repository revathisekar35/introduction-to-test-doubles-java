import com.techreturners.bubbleteaordersystem.service.BubbleTeaRouletteService;
import com.techreturners.bubbleteaordersystem.model.BubbleTeaTypeEnum;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import testhelper.RandomStub;

import static org.junit.Assert.assertEquals;

public class BubbleTeaRouletteServiceTest {

    @ParameterizedTest
    @EnumSource(value = BubbleTeaTypeEnum.class)
    public void shouldGenerateARandomBubbleTeaTypeWhenGetRandomBubbleTeaTypeIsCalled(BubbleTeaTypeEnum bubbleTeaTypeEnum) {

        //Arrange
        RandomStub randomStub = new RandomStub(bubbleTeaTypeEnum.ordinal());
        BubbleTeaRouletteService bubbleTeaRouletteService = new BubbleTeaRouletteService(randomStub);

        //Act
        var actualResult = bubbleTeaRouletteService.getRandomBubbleTeaType();
        var expectedResult = bubbleTeaTypeEnum;

        //Assert
        assertEquals(expectedResult, actualResult);

    }
}
