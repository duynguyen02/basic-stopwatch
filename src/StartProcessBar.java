import javax.swing.*;

public class StartProcessBar extends JFrame {
    JProgressBar progressBar = new JProgressBar(0,100);
    public StartProcessBar(){
        this.setLocationRelativeTo(null);
        progressBar.setStringPainted(true);
        this.add(progressBar);
        this.pack();
        this.setVisible(true);
        for(int i=0;i<=100;i++){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            progressBar.setValue(i);
        }
        this.dispose();
        new MainActivity();
    }
}
