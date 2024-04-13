package nextstep.courses.domain.utils;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionDate;
import nextstep.courses.domain.SessionImage;
import nextstep.courses.domain.SessionStatus;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class TestSession extends Session {
    public TestSession(Long id, SessionImage sessionImage, SessionStatus sessionStatus, SessionDate sessionDate) {
        super(id, sessionImage, sessionStatus, sessionDate);
    }

    @Override
    protected void assertRecruit(NsUser user) {

    }

    @Override
    protected Payment payResult(NsUser user) {
        return null;
    }
}
