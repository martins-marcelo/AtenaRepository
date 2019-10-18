package br.ufac.si.atena.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

public class AtenaMainView extends JFrame {

	private JPanel contentPane;
	private JTextField tfBuscarRepo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AtenaMainView frame = new AtenaMainView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AtenaMainView() {
		setResizable(false);
		setTitle("Atena");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 536, 348);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JLabel lbWelcome = new JLabel("Bem vindo!");
		lbWelcome.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		tfBuscarRepo = new JTextField();
		tfBuscarRepo.setColumns(10);
		
		JButton btBuscarRepo = new JButton("Buscar Repositorio");
		
		JLabel lbDescricao = new JLabel("Insira a URI de um reposit\u00F3rio do GitHub para come\u00E7ar");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(170)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lbWelcome)
								.addComponent(btBuscarRepo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(153)
							.addComponent(tfBuscarRepo, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(118)
							.addComponent(lbDescricao)))
					.addContainerGap(141, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(36)
					.addComponent(lbWelcome)
					.addGap(18)
					.addComponent(lbDescricao)
					.addGap(35)
					.addComponent(tfBuscarRepo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btBuscarRepo)
					.addContainerGap(108, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
	}
}
