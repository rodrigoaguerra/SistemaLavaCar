package sistema.lavacar;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MenuPrincipal extends javax.swing.JFrame {
    private Company c;
    public MenuPrincipal(Company c) {
        initComponents();
        this.c = c;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        botaoPrecos = new javax.swing.JButton();
        botaoInfo = new javax.swing.JButton();
        botaoLogin = new javax.swing.JButton();
        botaoEspera = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu principal");

        botaoPrecos.setText("Visualizar preços");
        botaoPrecos.setToolTipText("");
        botaoPrecos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botaoPrecos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPrecosActionPerformed(evt);
            }
        });

        botaoInfo.setText("Informações da Empresa");
        botaoInfo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        botaoInfo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botaoInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoInfoActionPerformed(evt);
            }
        });

        botaoLogin.setText("Login");
        botaoLogin.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botaoLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoLoginActionPerformed(evt);
            }
        });

        botaoEspera.setText("Tempo de Espera");
        botaoEspera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoEsperaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(botaoInfo)
                    .addComponent(botaoLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoEspera)
                    .addComponent(botaoPrecos, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoEspera, botaoInfo, botaoLogin, botaoPrecos});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(botaoPrecos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoEspera)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoLogin)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botaoPrecosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPrecosActionPerformed
        String msg = "";
        msg = msg + "Veiculo pequeno: R$ " + c.getService().getPriceSmall() + "\n";
        msg = msg + "Veiculo médio: R$ " + c.getService().getPriceMedium() + "\n";
        msg = msg + "Veiculo grande: R$ " + c.getService().getPriceBig() + "\n";
        
        JOptionPane.showMessageDialog(this, msg, "Preços - " + c.getService().getName(), JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_botaoPrecosActionPerformed

    private void botaoInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoInfoActionPerformed
        String msg = "";
        msg = msg + "Nome:  " + c.getName_company() + "\n";
        msg = msg + "Endereço:  " + c.getAddress_company() + "\n";
        msg = msg + "Telefone:  " + c.getPhone_company() + "\n";
        msg = msg + "Site:  " + c.getSite_company() + "\n";
        msg = msg + "CNPJ:  " + c.getCnpj() + "\n"; 
        JOptionPane.showMessageDialog(this, msg, "Info", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_botaoInfoActionPerformed

    private void botaoLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLoginActionPerformed
        Login login = new Login(c, this);
        login.setLocationRelativeTo(null);
        login.setSize(300, 300);
        login.setVisible(true);
    }//GEN-LAST:event_botaoLoginActionPerformed

    private void botaoEsperaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEsperaActionPerformed
        //Abre uma nova janela e pergunta a placa do veículo
        //Procura na fila, calcula o tempo de espera até ali
    }//GEN-LAST:event_botaoEsperaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoEspera;
    private javax.swing.JButton botaoInfo;
    private javax.swing.JButton botaoLogin;
    private javax.swing.JButton botaoPrecos;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}