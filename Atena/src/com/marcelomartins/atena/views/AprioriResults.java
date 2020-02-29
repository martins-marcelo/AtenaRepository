package com.marcelomartins.atena.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import weka.associations.Apriori;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.UIManager;
import java.awt.Color;

public class AprioriResults extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public AprioriResults(Apriori apResults) {
		setTitle("Resultados Apriori");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(70, 130, 180));
		contentPane.add(panel, BorderLayout.CENTER);
		
		JTextArea taResults = new JTextArea();
		taResults.setFont(new Font("DejaVu Sans", Font.PLAIN, 13));
		taResults.setEditable(false);
		taResults.setText(apResults.toString());
		
		JLabel lblResultadosDeProcessamento = new JLabel("Resultados de processamento Apriori");
		lblResultadosDeProcessamento.setForeground(new Color(255, 255, 255));
		lblResultadosDeProcessamento.setBackground(UIManager.getColor("ComboBox.selectionBackground"));
		lblResultadosDeProcessamento.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblResultadosDeProcessamento.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblExit = new JLabel("Exit");
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//VOLTAR PARA A JANELA ANTERIOR
				dispose();
				PanelSearch ps = new PanelSearch();
				ps.setVisible(true);
			}
		});
		lblExit.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		lblExit.setForeground(new Color(255, 255, 255));
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(116)
							.addComponent(lblResultadosDeProcessamento, GroupLayout.PREFERRED_SIZE, 440, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 733, Short.MAX_VALUE)
							.addComponent(lblExit))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(taResults, GroupLayout.DEFAULT_SIZE, 1318, Short.MAX_VALUE)))
					.addGap(18))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(12)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblResultadosDeProcessamento, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblExit))
					.addGap(18)
					.addComponent(taResults, GroupLayout.DEFAULT_SIZE, 632, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
	}
}
