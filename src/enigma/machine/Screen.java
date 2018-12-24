package enigma.machine;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.TextField;

public class Screen {
    
    private static Rotor r1 = new Rotor();
    private static Rotor r2 = new Rotor();
    private static Rotor r3 = new Rotor();
    
    private static int keycount1=0;
    private static int keycount2=0;
    private static int keycount3=0;
    
    private static String stext2="";
    private static TextField text2 = new TextField(stext2);
    
    
    public static void CreateScreen(){
    
        //frame
        JFrame frame = new JFrame();
        frame.setTitle("the enigma machine");
        frame.setSize(600,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        //panel
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBackground(Color.BLACK);
        //panel1
        JPanel panel1 = new JPanel(new GridBagLayout());
        panel1.setBackground(Color.BLACK);
        //panel2
        JPanel panel2 = new JPanel();
        panel1.setBackground(Color.BLACK);
        //panel3
        JPanel panel3 = new JPanel(new GridLayout(3,0));
        panel3.setBackground(Color.BLACK);
        
        GridBagConstraints gbc = new GridBagConstraints();
        //text feilds
        TextField text1 = new TextField("");
        text1.setPreferredSize(new Dimension(150,50));
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridx=0;
        gbc.gridy=0;
        panel1.add(text1,gbc);
        
        text2.setPreferredSize(new Dimension(150,50));
        text2.setEnabled(false);
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridx=1;
        gbc.gridy=0;
        panel1.add(text2,gbc);
        //end textfeilds
        panel3.add(panel);
        panel3.add(panel1);
        panel3.add(panel2);
        
        frame.add(panel3);
        
        
        //buttons
        ArrayList<JButton> buttonlist = new ArrayList<JButton>();
        ArrayList<String> letters = new ArrayList<String>();
        for(int i=0;i<26;i++){
            buttonlist.add(new JButton());
            letters.add(getCharForNumber(i));
        }
        
        for(JButton but:buttonlist){
            but.setText(letters.get(buttonlist.indexOf(but)));
            but.setBackground(Color.green);
            panel.add(but);
        }
//text1 key listenenr
        text1.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                buttonlist.get(getnumberforchar(rotorbox(e.getKeyChar()))).setBackground(Color.yellow);
                frame.setVisible(true);
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                buttonlist.get(getnumberforchar(rotorbox(e.getKeyChar()))).setBackground(Color.green);
                frame.setVisible(true);
                //update textbox2
                stext2+=rotorbox(e.getKeyChar());
                text2.setText(stext2);
                //rotate the rotors
                keycountcheck();
                
            }
        });
       
        
        
        frame.setVisible(true);
        
    }
    
    
    public static char rotorbox(char a){
    
    int anum=getnumberforchar(a);
    anum=anum+keycount1;
    a=getCharForNumber(anum).charAt(0);       
    
    char i=r1.getRotorForward(a);
    
    int inum=getnumberforchar(i);
    inum=inum+keycount2;
    i=getCharForNumber(inum).charAt(0);
    
    
    char j=r2.getRotorForward(i);
    
    int jnum=getnumberforchar(j);
    jnum=jnum+keycount3;
    j=getCharForNumber(jnum).charAt(0);
    
    char k=r3.getRotorForward(j);
    
    j=r3.getRotorForward(k);
    i=r2.getRotorForward(j);
    k=r1.getRotorForward(i);
    
    return k;
    }
    
    
    
    private static void keycountcheck(){
        keycount1++;
     if(keycount1>=25){
        keycount2++;
        keycount1=0;
     }
     if(keycount2>=25){
        keycount3++;
        keycount2=0;
     }
     if(keycount3>=25)
         keycount3=0;
                    
    System.out.println(keycount1+" "+keycount2+" "+keycount3);
     
    }
    
    
    private static String getCharForNumber(int i) {
    char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    if (i >= 25) {
        i=i-25;
    }
    return Character.toString(alphabet[i]);
    }
    
    public static int getnumberforchar(char i){
    
    char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    int bingo=10;
    for(int j=0;j<26;j++){
        if(alphabet[j]==i ||alphabet[j]==Character.toUpperCase(i) )
            bingo=j;
    }
        return bingo;
    }

}