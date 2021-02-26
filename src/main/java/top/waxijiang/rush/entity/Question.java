package top.waxijiang.rush.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Question
 * @author 
 */
public class Question implements Serializable {
    private Integer id;

    private Integer sectionId;

    private String questionUrl;

    private String questionText;

    private String answerUrl;

    private String answerText;

    private Integer score;

    private Integer type;

    private Date createdTime;

    private Integer createUserId;

    private Boolean enabled;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public String getQuestionUrl() {
        return questionUrl;
    }

    public void setQuestionUrl(String questionUrl) {
        this.questionUrl = questionUrl;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getAnswerUrl() {
        return answerUrl;
    }

    public void setAnswerUrl(String answerUrl) {
        this.answerUrl = answerUrl;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Question other = (Question) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSectionId() == null ? other.getSectionId() == null : this.getSectionId().equals(other.getSectionId()))
            && (this.getQuestionUrl() == null ? other.getQuestionUrl() == null : this.getQuestionUrl().equals(other.getQuestionUrl()))
            && (this.getQuestionText() == null ? other.getQuestionText() == null : this.getQuestionText().equals(other.getQuestionText()))
            && (this.getAnswerUrl() == null ? other.getAnswerUrl() == null : this.getAnswerUrl().equals(other.getAnswerUrl()))
            && (this.getAnswerText() == null ? other.getAnswerText() == null : this.getAnswerText().equals(other.getAnswerText()))
            && (this.getScore() == null ? other.getScore() == null : this.getScore().equals(other.getScore()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getCreatedTime() == null ? other.getCreatedTime() == null : this.getCreatedTime().equals(other.getCreatedTime()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
            && (this.getEnabled() == null ? other.getEnabled() == null : this.getEnabled().equals(other.getEnabled()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSectionId() == null) ? 0 : getSectionId().hashCode());
        result = prime * result + ((getQuestionUrl() == null) ? 0 : getQuestionUrl().hashCode());
        result = prime * result + ((getQuestionText() == null) ? 0 : getQuestionText().hashCode());
        result = prime * result + ((getAnswerUrl() == null) ? 0 : getAnswerUrl().hashCode());
        result = prime * result + ((getAnswerText() == null) ? 0 : getAnswerText().hashCode());
        result = prime * result + ((getScore() == null) ? 0 : getScore().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getCreatedTime() == null) ? 0 : getCreatedTime().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getEnabled() == null) ? 0 : getEnabled().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", sectionId=").append(sectionId);
        sb.append(", questionUrl=").append(questionUrl);
        sb.append(", questionText=").append(questionText);
        sb.append(", answerUrl=").append(answerUrl);
        sb.append(", answerText=").append(answerText);
        sb.append(", score=").append(score);
        sb.append(", type=").append(type);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", enabled=").append(enabled);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}