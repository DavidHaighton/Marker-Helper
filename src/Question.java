import java.util.ArrayList;
import java.util.List;

public class Question
{
    private boolean correct;
    private String text;
    private int points;
    private List<QuestionListener> listeners=new ArrayList<>();
    public Question(String text,int points)
    {
        this.points=points;
        this.text=text;
        correct=false;
    }

    public void addQuestionListener(QuestionListener ql)
    {
        listeners.add(ql);
    }
    public void removeQuestionListener(QuestionListener ql)
    {
        listeners.remove(ql);
    }


    public void setCorrect(boolean correct)
    {
        this.correct=correct;
        for(QuestionListener ql: listeners)
        {
            ql.questionCorrectChanged(new QuestionChangedEvent(this));
        }
    }

    public boolean isCorrect()
    {
        return correct;
    }

    public String getText()
    {
        return text;
    }

    public int getPoints()
    {
        return points;
    }

}
