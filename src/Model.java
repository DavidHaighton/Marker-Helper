import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Model implements QuestionListener
{
    private List<MarkerHelperView> views=new ArrayList<>();

    private List<Question> questions=new ArrayList<>();
    public Model(String filepath) throws FileNotFoundException {
        File file=new File(filepath);
        Scanner reader=new Scanner(file);
        while(reader.hasNextLine())
        {
            int marks;
            String text;
            String line=reader.nextLine();

            String[] parts = line.split(",");
            marks=Integer.parseInt(parts[0]);
            text=parts[1];
            Question q = new Question(text,marks);
            questions.add(q);
            q.addQuestionListener(this);

        }
    }

    public void setQuestionCorrect(int index,boolean correct)
    {
        this.questions.get(index).setCorrect(correct);
    }

    public Question getQuestion(int index)
    {
        return questions.get(index);
    }

    public int getSize()
    {
        return questions.size();
    }

    public List<Question> getQuestions()
    {
        return this.questions;
    }


    public void addView(MarkerHelperView view)
    {
        this.views.add(view);
    }

    public void removeView(MarkerHelperView view)
    {
        this.views.remove(view);
    }

    @Override
    public void questionCorrectChanged(QuestionChangedEvent e)
    {
        for(MarkerHelperView view: views)
        {
            view.questionChanged(new QuestionChangedEvent(e.getSource()));
        }
    }
}
