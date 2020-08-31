import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class JResortCalculator2 extends JFrame implements ItemListener, ActionListener
{
   boolean opt = true;
   final int BASE_PRICE = 200;
   final int WEEKEND_PREMIUM = 100;
   final int BREAKFAST_PREMIUM = 20;
   final int GOLF_PREMIUM = 75;
   int totalPrice = BASE_PRICE;
   JCheckBox weekendBox = new JCheckBox
       ("Weekend premium $" + WEEKEND_PREMIUM, false);
   JCheckBox breakfastBox = new
       JCheckBox("Breakfast $" + BREAKFAST_PREMIUM, false);
   JCheckBox golfBox = new
       JCheckBox("Golf $" + GOLF_PREMIUM, false);
   JLabel resortLabel = new JLabel
      ("Resort Price Calculator");
   JLabel priceLabel = new JLabel
      ("The price for your stay is");
   JTextField totPrice = new JTextField(4);
   JLabel optionExplainLabel = new JLabel
       ("Base price for a room is $" +
       BASE_PRICE + ".");
   JLabel optionExplainLabel2 = new JLabel
       ("Check the options you want.");
   JButton btnOptions = new JButton("Add options?");
   public JResortCalculator2()
   {
      super("Resort Price Estimator");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLayout(new FlowLayout());
      add(resortLabel);
      add(optionExplainLabel);
      add(btnOptions);
      add(optionExplainLabel2);
      add(weekendBox);
      add(breakfastBox);
      add(golfBox);
      add(priceLabel);
      add(totPrice);
      totPrice.setText("$" + totalPrice);
      btnOptions.addActionListener(this);
      weekendBox.addItemListener(this);
      breakfastBox.addItemListener(this);
      golfBox.addItemListener(this);
      optionExplainLabel2.setVisible(false);
      weekendBox.setVisible(false);
      breakfastBox.setVisible(false);
      golfBox.setVisible(false);

   }

   @Override
   public void actionPerformed(ActionEvent e) {
      if(opt) {
         optionExplainLabel2.setVisible(true);
         weekendBox.setVisible(true);
         breakfastBox.setVisible(true);
         golfBox.setVisible(true);
         opt = false;
      }
      else {
         optionExplainLabel2.setVisible(false);
         weekendBox.setVisible(false);
         breakfastBox.setVisible(false);
         golfBox.setVisible(false);
         weekendBox.setSelected(false);
         breakfastBox.setSelected(false);
         golfBox.setSelected(false);
         totalPrice = BASE_PRICE;
         totPrice.setText("$" + totalPrice);
         opt = true;
      }
   }

   @Override
   public void itemStateChanged(ItemEvent event)
   {
      Object source = event.getSource();
      int select = event.getStateChange();
      if(source == weekendBox)
           if(select == ItemEvent.SELECTED)
              totalPrice += WEEKEND_PREMIUM;
           else
	      totalPrice -= WEEKEND_PREMIUM;
      else if(source == breakfastBox)
      {
          if(select == ItemEvent.SELECTED)
              totalPrice += BREAKFAST_PREMIUM;
          else
              totalPrice -= BREAKFAST_PREMIUM;	
      }
      else     // if(source == golfBox) by default
         if(select == ItemEvent.SELECTED)
           totalPrice += GOLF_PREMIUM;
         else
           totalPrice -= GOLF_PREMIUM;
      totPrice.setText("$" + totalPrice);	
   }
   public static void main(String[] args)
   {
      JResortCalculator2 aFrame = new JResortCalculator2();
      final int WIDTH = 250;
      final int HEIGHT = 400;
      aFrame.setSize(WIDTH, HEIGHT);
      aFrame.setVisible(true);
   }


}
