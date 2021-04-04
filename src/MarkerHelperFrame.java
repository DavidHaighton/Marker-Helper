import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MarkerHelperFrame extends JFrame implements MarkerHelperView
{
    JPanel questionPanel=new JPanel();

    JTextArea textArea = new JTextArea("AREA");
    JMenuBar menuBar = new JMenuBar();
    JButton clearButton=new JButton("CLEAR ALL"),selectAllButton=new JButton("SELECT ALL");
    JMenu optionsMenu =new JMenu("OPTIONS");
    ArrayList<QuestionPanel> panels=new ArrayList();

    Model model;
    public MarkerHelperFrame(Model model)
    {
        super("Marker Helper");


        this.setJMenuBar(menuBar);
        menuBar.add(optionsMenu);
        optionsMenu.add(clearButton);
        optionsMenu.add(selectAllButton);


        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                for(QuestionPanel qp: panels)
                {
                    qp.setSelected(false);
                }
            }
        });
        selectAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                for(QuestionPanel qp: panels)
                {
                    qp.setSelected(true);
                }
            }
        });


        questionPanel.setLayout(new GridLayout(model.getSize(),1));
        for(int i=0;i<model.getSize();i++)
        {
            Question question=model.getQuestion(i);
            QuestionPanel qp=new QuestionPanel(question);
            panels.add(qp);
            questionPanel.add(qp);
        }

        this.setLayout(new GridLayout(1,2));

        this.add(questionPanel);
        this.add(textArea);

        this.textArea.setEditable(false);
        this.model=model;
        this.model.addView(this);
        setSize(700,400);
        show();
    }

    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog("Enter path to config file");
        Model m=null;
        try {
            m=new Model(input);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,"Incorrect path");
            System.exit(0);
        }

        MarkerHelperFrame mh=new MarkerHelperFrame(m);

        mh.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });
    }

    @Override
    public void questionChanged(QuestionChangedEvent e)
    {
        textArea.setText("");
        int sum=0;
        for(Question q: this.model.getQuestions())
        {
            if(!q.isCorrect())
            {

                textArea.append("-"+q.getPoints()+":"+q.getText()+"\n");
            }
            else
            {
                sum+=q.getPoints();
            }
        }
        textArea.append("Total:"+sum);
        textArea.updateUI();
    }
}
