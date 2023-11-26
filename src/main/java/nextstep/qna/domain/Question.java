package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Question {
    private Long id;

    private QuestionContent questionContent;

    private NsUser writer;

    private Answers answers = new Answers();

    private boolean deleted = false;

    private DateRecord dateRecord;

    public Question() {
    }

    public Question(NsUser writer, String title, String contents) {
        this(0L, writer, new QuestionContent(title, contents));
    }

    public Question(Long id, NsUser writer, String title, String contents) {
        this(id, writer, new QuestionContent(title, contents));
    }

    public Question(Long id, NsUser writer, QuestionContent questionContent) {
        this.id = id;
        this.writer = writer;
        this.questionContent = questionContent;
    }

    public Long getId() {
        return id;
    }

    public List<DeleteHistory> delete(NsUser loginUser) throws CannotDeleteException {
        validateSamePerson(loginUser);
        this.deleted = true;

        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, this.id, this.writer, LocalDateTime.now()));

        deleteHistories.addAll(answers.delete(loginUser));

        return deleteHistories;
    }

    private void validateSamePerson(NsUser loginUser) throws CannotDeleteException {
        if (!this.isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    private boolean isOwner(NsUser loginUser) {
        return writer.equals(loginUser);
    }

    public NsUser getWriter() {
        return writer;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.add(answer);
    }

    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", questionContent=" + questionContent + ", writer=" + writer + "]";
    }
}
