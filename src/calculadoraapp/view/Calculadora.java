package calculadoraapp.view;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Calculadora   {

    public Calculadora() {


    }


    private  static List<String> simbolosBtn = new ArrayList<>(
            List.of("+","-","*","/"));
    private  static List<String>  simboloOp = new ArrayList<>(
            List.of("AC","."," 0","=")
    );

    private  static  List<String>  numberOrder = new ArrayList<>(
            List.of("7","8","9","4","5","6","1","2","3")
    );

    private  static  Double digit ;

    private static JLabel result;

    public  static void  showView() {

        JFrame frame = new JFrame("Calculadora");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLocationRelativeTo(null);



        GridLayout borderLayout = new GridLayout(1,1);
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        JPanel panelLabel = new JPanel(new FlowLayout(20,5,50));

        JPanel botones = new JPanel(new GridLayout(5,3,5,5));
        JPanel operaciones = new JPanel(new GridLayout(4,1,15,5));



      result = new JLabel("0");


        result.setFont(new Font("Arial",Font.BOLD,20));
        panelLabel.add(result);


        JButton btn = new JButton();

        for (String  numero : numberOrder) {


            btn = new JButton(" "+numero);
            btn.setPreferredSize(new Dimension(100,100));

            btn.addActionListener( new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(e.getActionCommand());

                }
            });

             botones.add(btn);
        }





        JButton operacionesBtn;

        for(String  simbolo : simbolosBtn){
            operacionesBtn = new JButton(simbolo);
            operacionesBtn.setPreferredSize(new Dimension(100,100));
            operaciones.add(operacionesBtn);
        }

        JButton operacionesBtn2;

        for(String  simbolo : simboloOp){
            operacionesBtn2 = new JButton(simbolo);

            operacionesBtn2.addActionListener( new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
            //operacionesBtn2.setPreferredSize(new Dimension(50,50));

            botones.add(operacionesBtn2);
        }



        panelPrincipal.add(panelLabel,BorderLayout.NORTH);
        panelPrincipal.add(botones,BorderLayout.CENTER);
        panelPrincipal.add(operaciones,BorderLayout.EAST);







        frame.setResizable(false);

        frame.add(panelPrincipal);
        frame.pack();
        frame.setVisible(true);

    }
    public static void main(String[] args) {
        showView();
    }

}
