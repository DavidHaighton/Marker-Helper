import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class QuestionPanel extends JPanel
{
    JCheckBox checkbox;
    JLabel text;

    Question question;
    public QuestionPanel(Question question)
    {
        this.question=question;
        text=new JLabel(question.getText());
        checkbox = new JCheckBox();

        checkbox.setSelected(question.isCorrect());
        setLayout(new BorderLayout());
        this.add(checkbox,BorderLayout.WEST);
        this.add(text,BorderLayout.CENTER);
        checkbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                question.setCorrect(checkbox.isSelected());
            }
        });

    }

    public void setSelected(boolean b)
    {
        checkbox.setSelected(b);
    }

}
