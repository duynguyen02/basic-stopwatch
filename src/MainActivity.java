import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class StopWatch extends Thread{
    JLabel jLabel;
    int i;
    boolean isRun = true;
    public StopWatch(JLabel jLabel){
        this.i = 0;
        this.jLabel = jLabel;
    }
    //this constructor use for "pause" button
    public StopWatch(JLabel jLabel, int i){
        this(jLabel);
        this.i = i;
    }

    /**
     * terminate run method
     */
    public void terminate(){
        isRun = false;
    }
    @Override
    public void run() {
        while (isRun){
            jLabel.setText(String.valueOf(i++));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
public class MainActivity extends JFrame implements ActionListener {
    JLabel timeDisplay = new JLabel();
    JButton btnStart = new JButton("Start");
    JButton btnStop = new JButton("Stop");
    JButton btnPause = new JButton("Pause");
    StopWatch stopWatch = new StopWatch(timeDisplay);
    boolean isPause = false;
    public MainActivity(){
        super("Stop Watch");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setSize(250,500);
        ////////////////////////////////
        setButton();
        setLabel();
        ////////////////////////////////
        this.add(btnStop);
        this.add(btnStart);
        this.add(btnPause);
        this.add(timeDisplay);
        this.setVisible(true);
    }

    /**
     * setting properties for Time display
     */
    private void setLabel() {
        timeDisplay.setText("0");
        timeDisplay.setFont(new Font(null,Font.PLAIN,30));
        timeDisplay.setBounds(0,50,250,50);
        timeDisplay.setBackground(Color.cyan);
        timeDisplay.setHorizontalTextPosition(JLabel.CENTER);
        timeDisplay.setOpaque(true);
    }

    /**
     * setting properties for buttons
     */
    private void setButton(){
        btnStart.setBounds(50,125,150,25);
        btnStart.addActionListener(this);
        btnStop.setBounds(50,175,150,25);
        btnStop.addActionListener(this);
        btnPause.setBounds(50,150,150,25);
        btnPause.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==btnStart){
            stopWatch.terminate();
            if(isPause){
                isPause = false;
                stopWatch = new StopWatch(timeDisplay,Integer.parseInt(timeDisplay.getText()));
            }
            else {
                stopWatch = new StopWatch(timeDisplay);
            }
            stopWatch.start();
        }
        if (e.getSource()==btnStop){
            stopWatch.terminate();
            btnStart.setEnabled(true);
            timeDisplay.setText("0");
        }
        if (e.getSource()==btnPause){
            isPause = true;
            stopWatch.terminate();
            btnStart.setEnabled(true);
        }
    }
}
