package nextstep.courses.domain;

import nextstep.courses.domain.exception.NotRecruitException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class FreeSession extends Session {
    public FreeSession(SessionImage sessionImage, SessionStatus sessionStatus) {
        super(sessionImage, sessionStatus);
    }

    @Override
    protected void assertRecruit(NsUser user) {
        if (!getSessionStatus().isRecruit() || getStudents().contains(user)) {
            throw new NotRecruitException();
        }
    }

    @Override
    protected Payment payResult(NsUser user) {
        return new Payment(null, getId(), user.getId(), 0L);
    }
}
