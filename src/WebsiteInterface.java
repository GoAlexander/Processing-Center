
//task1
import java.text.ParseException;
import java.util.Random;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class WebsiteInterface extends javax.swing.JFrame {
    
    /**
     * Creates new form НовыйJFrame
     * @throws Exception
     */
    public WebsiteInterface() throws Exception {
        initComponents();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() throws Exception {
        
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JFormattedTextField(new MaskFormatter("#### #### #### ####"));
        jLabel2 = new javax.swing.JLabel();
        //  jRadioButton1 = new javax.swing.JRadioButton();
        // jRadioButton2 = new javax.swing.JRadioButton();
        jTextField2 = new javax.swing.JFormattedTextField(new MaskFormatter("###"));
        jLabel3 = new javax.swing.JLabel();
        Month = new javax.swing.JComboBox<>();
        Year = new javax.swing.JComboBox<>();
        // jLabel4 = new javax.swing.JLabel();
        //  jTextField3 = new javax.swing.JFormattedTextField(new MaskFormatter("#####"));
        jLabel5 = new javax.swing.JLabel();
        Random sum= new Random();
        int s=sum.nextInt(2000);
        
        // edt = new JFormattedTextField(new MaskFormatter("###.##"));
        //  jTextField1 = new JFormattedTextField(new MaskFormatter("###.##"));
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        jButton1.setText("Подтвердить");
        jButton1.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                save(jTextField1.getText(),jTextField2.getText(),Month.getSelectedIndex(),Year.getSelectedIndex());
            }
        });
        
        jLabel1.setText("Номер карты");
        
        jLabel2.setText("Срок действия");
        
        // jRadioButton1.setText("Visa");
        
        // jRadioButton2.setText("MasterCard");
        
        jLabel3.setText("CVV");
        
        Month.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        
        Year.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "16", "17", "18", "19", "20", "21", "22", "23" }));
        
        //     jLabel4.setText("Подарочная карта");
        
        jLabel5.setText("Итого к оплате:"+s+"руб");
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                                  layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                  .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                      .addGroup(layout.createSequentialGroup()
                                                                .addGap(24, 24, 24)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                          .addGroup(layout.createSequentialGroup()
                                                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                              .addComponent(jLabel1)
                                                                                              .addComponent(jLabel2)
                                                                                              .addComponent(jLabel3)
                                                                                              // .addComponent(jLabel4)
                                                                                              )
                                                                                    .addGap(28, 28, 28)
                                                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                              .addGroup(layout.createSequentialGroup()
                                                                                                        .addGap(48, 48, 48)
                                                                                                        .addComponent(jLabel5))
                                                                                              .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                              .addGroup(layout.createSequentialGroup()
                                                                                                        .addComponent(Month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                        .addGap(35, 35, 35)
                                                                                                        .addComponent(Year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                                                        // .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                                                                                                        .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING))))
                                                                          //    .addGroup(layout.createSequentialGroup()
                                                                          //            .addComponent(jRadioButton1)
                                                                          //         .addGap(38, 38, 38)
                                                                          //        .addComponent(jRadioButton2))
                                                                          ))
                                                      
                                                      .addGroup(layout.createSequentialGroup()
                                                                .addGap(144, 144, 144)
                                                                .addComponent(jButton1)))
                                            .addContainerGap(41, Short.MAX_VALUE))
                                  );
        layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                          .addContainerGap(20, Short.MAX_VALUE)
                                          /* .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                           .addComponent(jRadioButton1)
                                           .addComponent(jRadioButton2))
                                           */.addGap(35, 35, 35)
                                          .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jLabel1)
                                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                          .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jLabel2)
                                                    .addComponent(Month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(Year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                          .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jLabel3)
                                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                          .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    // .addComponent(jLabel4)
                                                    // .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    )
                                          .addGap(43, 43, 43)
                                          .addComponent(jLabel5)
                                          .addGap(18, 18, 18)
                                          .addComponent(jButton1))
                                );
        
        pack();
    }// </editor-fold>
    public void save(String text,String text1,int mnth,int ye) {
        cardnumber = Integer.parseInt(text);
        cvv = Integer.parseInt(text1);
        month=mnth;
        year=ye;
        
        
    }
    /**
     * @param args the command line arguments
     */
    
    
    // Variables declaration - do not modify
    private javax.swing.JComboBox<String> Month;
    private javax.swing.JComboBox<String> Year;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    //   private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    //  private javax.swing.JRadioButton jRadioButton1;
    // private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JFormattedTextField jTextField1;
    private javax.swing.JFormattedTextField jTextField2;
    //  private javax.swing.JFormattedTextField jTextField3;
    // End of variables declaration
    public int cardnumber;
    public int cvv;
    public int month;
    public int year;
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(WebsiteInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WebsiteInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WebsiteInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WebsiteInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new WebsiteInterface().setVisible(true);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        cvvChecking(cardnumber, cvv);
        usefulTimeOfCardChecking(cardnumber);
        moneyOnCardChecking(cardnumber, s);
    }
}