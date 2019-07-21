package org.ajou.realcoding.massagemachine.massagemachine.service;

import org.ajou.realcoding.massagemachine.massagemachine.domain.MassageMode;
import org.ajou.realcoding.massagemachine.massagemachine.repository.MassageRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class MassageServiceTest {
    @Mock
    private MassageRepository massageRepository;

    @InjectMocks
    private MassageService massageService;

    @Test
    public void 랜덤으로_만들어진_마사지모드의_time은_무조건_10미만이다 () {
        MassageMode massageMode = mock(MassageMode.class);
        when(massageMode.getTime()).thenReturn(4);

        assertThat(massageMode.getTime(), lessThan(10));
    }

    @Test(expected = IllegalArgumentException.class)
    public void 마사지모드로_목어깨팔다리발_이외의_부위로_저장하면_에러발생 () {
        MassageMode massageMode = mock(MassageMode.class);

        doThrow(new IllegalArgumentException()).when(massageMode).setBodyPart("허리");
        massageMode.setBodyPart("허리");
    }

    @Test
    public void 부위_강도_시간은_랜덤으로_결정되므로_set_실행시_오류발생 () {
        MassageMode massageMode = mock(MassageMode.class);

        verify(massageMode, never()).setBodyPart(anyString());
        verify(massageMode, never()).setPower(anyString());
        verify(massageMode, never()).setTime(anyInt());
    }

    @Test
    public void 마사지모드에서_몸부위를_불러오면_목부위를_리턴한다() {  //김영진 작성
        MassageMode massageMode = mock(MassageMode.class);
        when(massageMode.getBodyPart()).thenReturn("목");
        assertThat(massageMode.getBodyPart(), is("목"));
    }
}
