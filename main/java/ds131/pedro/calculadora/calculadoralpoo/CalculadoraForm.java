/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ds131.pedro.calculadora.calculadoralpoo;

/**
 *
 * @author Pedro Eduardo Dall Agnol GRR - 20240844
 */

import java.awt.Component;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

/**
 * CalculadoraSwing usando JForm Builder,
 * ActionListener único, operações encadeadas,
 * decimal + binário em bytes, botão C para limpar.
 */
public class CalculadoraForm extends javax.swing.JFrame implements ActionListener {
    private EstadoCalculadora estado;
    private double valorAnterior;
    private String operadorAtual;

    public CalculadoraForm() {
        initComponents();
        
        // inicializa estados
        estado = EstadoCalculadora.INICIO;
        valorAnterior = 0;
        operadorAtual = "";

        // registra ActionListener em todos os botões
        for (Component c : panelBotoes.getComponents()) {
            if (c instanceof JButton btn) {
                btn.addActionListener(this);
            }
        }

        // cor de fundo do JFrame
        getContentPane().setBackground(new Color(240,240,255));
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        // bloqueia * e / no início
        if (estado == EstadoCalculadora.INICIO && (cmd.equals("*")||cmd.equals("/")))
            return;

        switch (estado) {
            case INICIO -> {
                if (cmd.matches("[0-9]")) {
                    txtDecimal.setText(cmd);
                    estado = EstadoCalculadora.DIGITANDO;
                } else if (cmd.equals("-")) {
                    txtDecimal.setText("-");
                    estado = EstadoCalculadora.DIGITANDO;
                } else if (cmd.equals("C")) {
                    clearAll();
                }
            }
            case DIGITANDO -> {
                if (cmd.matches("[0-9]")) {
                    txtDecimal.setText(txtDecimal.getText()+cmd);
                } else if (cmd.matches("[+\\-*/]")) {
                    valorAnterior = Double.parseDouble(txtDecimal.getText());
                    operadorAtual = cmd;
                    estado = EstadoCalculadora.OPERADOR;
                } else if (cmd.equals("=")) {
                    calculateAndDisplay();
                } else if (cmd.equals("C")) {
                    clearAll();
                }
            }
            case OPERADOR -> {
                if (cmd.matches("[0-9]")||
                   (cmd.equals("-") && txtDecimal.getText().isEmpty())) {
                    txtDecimal.setText(cmd);
                    estado = EstadoCalculadora.DIGITANDO;
                } else if (cmd.equals("C")) {
                    clearAll();
                }
            }
            case RESULTADO -> {
                if (cmd.matches("[+\\-*/]")) {
                    valorAnterior = Double.parseDouble(txtDecimal.getText());
                    operadorAtual = cmd;
                    estado = EstadoCalculadora.OPERADOR;
                } else if (cmd.matches("[0-9]")||cmd.equals("-")) {
                    txtDecimal.setText(cmd);
                    txtBinario.setText("");
                    operadorAtual = "";
                    valorAnterior = 0;
                    estado = EstadoCalculadora.DIGITANDO;
                } else if (cmd.equals("C")) {
                    clearAll();
                }
            }
            case ERRO -> {
                if (cmd.equals("C")) clearAll();
            }
        }
    }

    private void clearAll() {
        txtDecimal.setText("");
        txtBinario.setText("");
        valorAnterior = 0;
        operadorAtual = "";
        estado = EstadoCalculadora.INICIO;
    }

    private void calculateAndDisplay() {
        try {
            double atual = Double.parseDouble(txtDecimal.getText());
            double res = switch (operadorAtual) {
                case "+" -> valorAnterior + atual;
                case "-" -> valorAnterior - atual;
                case "*" -> valorAnterior * atual;
                case "/" -> valorAnterior / atual;
                default -> atual;
            };
            txtDecimal.setText(String.valueOf(res));

            long inteiro = Math.round(res);
            String bin = Long.toBinaryString(inteiro);
            int rem = bin.length()%8;
            if (rem!=0) bin = "0".repeat(8-rem)+bin;

            StringBuilder out = new StringBuilder();
            for(int i=0;i<bin.length();i++){
                out.append(bin.charAt(i));
                if((i+1)%8==0 && i<bin.length()-1) out.append(' ');
            }
            txtBinario.setText(out.toString());
            estado = EstadoCalculadora.RESULTADO;
        } catch(Exception ex){
            txtDecimal.setText("ERRO");
            txtBinario.setText("");
            estado = EstadoCalculadora.ERRO;
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelDisplay = new javax.swing.JPanel();
        txtDecimal = new javax.swing.JTextField();
        txtBinario = new javax.swing.JTextField();
        panelBotoes = new javax.swing.JPanel();
        btn7 = new javax.swing.JButton();
        btn8 = new javax.swing.JButton();
        btn9 = new javax.swing.JButton();
        btnDiv = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        btnMult = new javax.swing.JButton();
        btn1 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btnSub = new javax.swing.JButton();
        btn0 = new javax.swing.JButton();
        btnC = new javax.swing.JButton();
        btnEq = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        panelDisplay.setBackground(new java.awt.Color(51, 51, 51));
        panelDisplay.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelDisplay.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        txtDecimal.setEditable(false);
        txtDecimal.setBackground(new java.awt.Color(0, 0, 0));
        txtDecimal.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        txtDecimal.setForeground(new java.awt.Color(51, 255, 0));
        txtDecimal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtBinario.setEditable(false);
        txtBinario.setBackground(new java.awt.Color(0, 0, 0));
        txtBinario.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtBinario.setForeground(new java.awt.Color(51, 255, 0));
        txtBinario.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtBinario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBinarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelDisplayLayout = new javax.swing.GroupLayout(panelDisplay);
        panelDisplay.setLayout(panelDisplayLayout);
        panelDisplayLayout.setHorizontalGroup(
            panelDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDisplayLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDecimal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                    .addComponent(txtBinario))
                .addContainerGap())
        );
        panelDisplayLayout.setVerticalGroup(
            panelDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDisplayLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtDecimal, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtBinario, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        panelBotoes.setBackground(new java.awt.Color(0, 0, 0));
        panelBotoes.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelBotoes.setForeground(new java.awt.Color(255, 255, 255));
        panelBotoes.setLayout(new java.awt.GridLayout(4, 4, 2, 2));

        btn7.setBackground(new java.awt.Color(51, 51, 51));
        btn7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn7.setForeground(new java.awt.Color(51, 255, 0));
        btn7.setText("7");
        btn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn7ActionPerformed(evt);
            }
        });
        panelBotoes.add(btn7);

        btn8.setBackground(new java.awt.Color(51, 51, 51));
        btn8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn8.setForeground(new java.awt.Color(51, 255, 0));
        btn8.setText("8");
        panelBotoes.add(btn8);

        btn9.setBackground(new java.awt.Color(51, 51, 51));
        btn9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn9.setForeground(new java.awt.Color(51, 255, 0));
        btn9.setText("9");
        panelBotoes.add(btn9);

        btnDiv.setBackground(new java.awt.Color(102, 0, 102));
        btnDiv.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        btnDiv.setText("/");
        panelBotoes.add(btnDiv);

        btn4.setBackground(new java.awt.Color(51, 51, 51));
        btn4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn4.setForeground(new java.awt.Color(51, 255, 0));
        btn4.setText("4");
        panelBotoes.add(btn4);

        btn5.setBackground(new java.awt.Color(51, 51, 51));
        btn5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn5.setForeground(new java.awt.Color(51, 255, 0));
        btn5.setText("5");
        panelBotoes.add(btn5);

        btn6.setBackground(new java.awt.Color(51, 51, 51));
        btn6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn6.setForeground(new java.awt.Color(51, 255, 0));
        btn6.setText("6");
        panelBotoes.add(btn6);

        btnMult.setBackground(new java.awt.Color(102, 0, 102));
        btnMult.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        btnMult.setText("*");
        btnMult.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMultActionPerformed(evt);
            }
        });
        panelBotoes.add(btnMult);

        btn1.setBackground(new java.awt.Color(51, 51, 51));
        btn1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn1.setForeground(new java.awt.Color(51, 255, 0));
        btn1.setText("1");
        panelBotoes.add(btn1);

        btn2.setBackground(new java.awt.Color(51, 51, 51));
        btn2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn2.setForeground(new java.awt.Color(51, 255, 0));
        btn2.setText("2");
        panelBotoes.add(btn2);

        btn3.setBackground(new java.awt.Color(51, 51, 51));
        btn3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn3.setForeground(new java.awt.Color(51, 255, 0));
        btn3.setText("3");
        panelBotoes.add(btn3);

        btnSub.setBackground(new java.awt.Color(102, 0, 102));
        btnSub.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        btnSub.setText("-");
        panelBotoes.add(btnSub);

        btn0.setBackground(new java.awt.Color(51, 51, 51));
        btn0.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn0.setForeground(new java.awt.Color(51, 255, 0));
        btn0.setText("0");
        panelBotoes.add(btn0);

        btnC.setBackground(new java.awt.Color(0, 0, 204));
        btnC.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        btnC.setText("C");
        panelBotoes.add(btnC);

        btnEq.setBackground(new java.awt.Color(204, 0, 102));
        btnEq.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        btnEq.setText("=");
        panelBotoes.add(btnEq);

        btnAdd.setBackground(new java.awt.Color(102, 0, 102));
        btnAdd.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        btnAdd.setText("+");
        panelBotoes.add(btnAdd);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelBotoes, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                    .addComponent(panelDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBotoes, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBinarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBinarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBinarioActionPerformed

    private void btn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn7ActionPerformed

    private void btnMultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMultActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMultActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn0;
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btn6;
    private javax.swing.JButton btn7;
    private javax.swing.JButton btn8;
    private javax.swing.JButton btn9;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnC;
    private javax.swing.JButton btnDiv;
    private javax.swing.JButton btnEq;
    private javax.swing.JButton btnMult;
    private javax.swing.JButton btnSub;
    private javax.swing.JPanel panelBotoes;
    private javax.swing.JPanel panelDisplay;
    private javax.swing.JTextField txtBinario;
    private javax.swing.JTextField txtDecimal;
    // End of variables declaration//GEN-END:variables

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new CalculadoraForm().setVisible(true));
    }

}
