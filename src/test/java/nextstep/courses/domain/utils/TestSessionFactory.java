package nextstep.courses.domain.utils;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionDateTest;
import nextstep.courses.domain.SessionImageTest;
import nextstep.courses.domain.SessionStatus;

public class TestSessionFactory {

    static public Session of(Long id) {
        return new TestSession(id, SessionImageTest.S1, SessionStatus.RECRUIT, SessionDateTest.of());
    }
}
