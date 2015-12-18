
import java.io.*;
import java.nio.charset.Charset;

import java.nio.file.Files;

import java.nio.file.Path;

import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Александр
 */
public final class CardCreatingGUI extends javax.swing.JFrame {

    /**
     * Creates new form CardCreatingGUI
     */
    public ArrayList<Cards> getCards() throws IOException, ParseException{
        ArrayList<Cards> result=new ArrayList<Cards>();
        
        try{
        String line;
        Path outputFile = Paths.get("Карты.csv");
        InputStream in = Files.newInputStream(outputFile);
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                                line = reader.readLine();
                                while ((line = reader.readLine()) != null){
                                    //String lineIn=temp.Name+";"+temp.limit+";"+temp.cur+";"+temp.pin+";"+temp.type+";"+temp.number+";"+temp.BL+"\n";
                                   Cards temp=new Cards();
                                    temp.Name=line.split(";")[0];
                                    temp.limit=Integer.parseInt(line.split(";")[1]);
                                    temp.cur=Integer.parseInt(line.split(";")[2]);
                                    temp.pin=Integer.parseInt(line.split(";")[3]);
                                    temp.type=Integer.parseInt(line.split(";")[4]);
                                    if(temp.type==0)
                                        temp.limit=0;
                                    temp.number=line.split(";")[5];
                                    temp.BL=Boolean.parseBoolean(line.split(";")[6]);
                                    temp.CVV=Integer.parseInt(line.split(";")[7]);
                                    DateFormat format = new SimpleDateFormat("yyyy/MM", Locale.ENGLISH);
                                    temp.data=format.parse(line.split(";")[8]);
                                    result.add(temp);
                                }
                                    
                                    }
       catch (IOException ex) {
            Logger.getLogger(CardCreatingGUI.class.getName()).log(Level.SEVERE, null, ex); }  
        return result;
    }
        int numOfRow() throws IOException{
        String line;
        int counter=0;
        Path outputFile = Paths.get("Карты.csv");
        InputStream in = Files.newInputStream(outputFile);
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                                line = reader.readLine();
                                while ((line = reader.readLine()) != null){
                             line = reader.readLine();
                             counter++;
        }
                                return counter;
            }
    public CardCreatingGUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setBackground(new java.awt.Color(255, 0, 0));
        jButton1.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(240, 240, 240));
        jButton1.setText("ВЫПОЛНИТЬ ЗАЯВКИ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(74, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
            String line;
            Path outputFile = Paths.get("ЗаявкиБаза.csv");
            InputStream in;
            try {
                in = Files.newInputStream(outputFile);
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                while ((line = reader.readLine()) != null){
                    Random r=new Random();
                    Cards temp=new Cards();
                    temp.Name=line.split(";")[0];
                    temp.limit=Integer.parseInt(line.split(";")[1]);
                    temp.cur=Integer.parseInt(line.split(";")[2]);
                    temp.pin=r.nextInt(8999)+1000;
                    temp.type=Integer.parseInt(line.split(";")[3]);
                    temp.CVV=r.nextInt(899)+100;
                    temp.numberArr[8]=(temp.cur+1)*3;
                    temp.numberArr[9]=(temp.type)*2;
                    temp.numberArr[10]=r.nextInt(10);
                    temp.numberArr[11]=r.nextInt(10);
                    temp.numberArr[12]=r.nextInt(10);
                    temp.numberArr[13]=r.nextInt(10);
                    temp.numberArr[14]=r.nextInt(10);
                    try {
                        temp.numberArr[15]=temp.findMoon();
                    } catch (Exception ex) {
                        System.out.println("Err");
                    }
                    temp.calculateNumber();
                    SimpleDateFormat data = new SimpleDateFormat("yyyy/MM");
                    String lineIn=temp.Name+";"+temp.limit+";"+temp.cur+";"+temp.pin+";"+temp.type+";"+temp.number+";"+temp.BL+";"+temp.CVV+";"+data.format(temp.data)+"\n";
                    String output_File = "Карты.csv";
                    FileWriter writer = new FileWriter(output_File, true);
                    writer.write(lineIn);
                    writer.close();
                    BufferedWriter writerDel = Files.newBufferedWriter(outputFile);
                    writerDel.close();
                   WorkWithExcel.writeIntoExcel(temp.number, temp.type, temp.cur, temp.limit,
   numOfRow());
                    WorkWithExcel.writeIntoExcel1(temp.number,temp.CVV , String.valueOf(temp.pin), 5000.0, temp.data, 0, 0, numOfRow());
                }
            } catch (IOException ex) {
                Logger.getLogger(CardCreatingGUI.class.getName()).log(Level.SEVERE, null, ex);
            } 
       this.hide();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(CardCreatingGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CardCreatingGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CardCreatingGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CardCreatingGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CardCreatingGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}
