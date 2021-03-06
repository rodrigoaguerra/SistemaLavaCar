/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lavacar;

import javax.swing.JOptionPane;

/**
 *
 * @author a1762281
 */
public class Espera extends javax.swing.JFrame {
    private Company c;
    public Espera(Company c) {
        initComponents();
        this.c = c;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        boardLabel = new javax.swing.JLabel();
        waitTimeLabel = new javax.swing.JLabel();
        board = new javax.swing.JTextField();
        waitTime = new javax.swing.JTextField();

        boardLabel.setText("Placa: ");

        waitTimeLabel.setText("Tempo: ");

        board.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boardActionPerformed(evt);
            }
        });

        waitTime.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(waitTimeLabel)
                    .addComponent(boardLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(waitTime)
                    .addComponent(board, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(boardLabel)
                    .addComponent(board, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(waitTimeLabel)
                    .addComponent(waitTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void boardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boardActionPerformed
        Vehicle v = c.procuraVeiculo(board.getText());
        //Testa se está cadastrado
        if(v == null)
        {
            JOptionPane.showMessageDialog(null, "O veículo não está cadastrado!",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
            waitTime.setText("");
        }
        else 
        {   //Testa se está na fila
            if(c.getService().estaNaFila(v) == false)
            {
                JOptionPane.showMessageDialog(null, "O veículo não está na fila!",
                        "ERROR", JOptionPane.ERROR_MESSAGE);
                waitTime.setText("");
            }
            else
                waitTime.setText(c.getService().estimarTempoDeEspera(v));
        }
    }//GEN-LAST:event_boardActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField board;
    private javax.swing.JLabel boardLabel;
    private javax.swing.JTextField waitTime;
    private javax.swing.JLabel waitTimeLabel;
    // End of variables declaration//GEN-END:variables
}
