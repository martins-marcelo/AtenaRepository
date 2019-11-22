package com.marcelomartins.atena.testes;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.marcelomartins.atena.domain.PullRequest;
import java.awt.Color;

public class TesteTabela extends JFrame{

	private JTable tabela;
	private DefaultTableModel modelo;
	private JScrollPane scroll;
	
	public TesteTabela() {
		
		criaTabela();
		getContentPane().setLayout(null);
		
		scroll = new JScrollPane(tabela);
		scroll.setBounds(12, 78, 651, 385);
		getContentPane().add(scroll);
		
		setTitle("Atena Search");
		setSize(750, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
	
	private void criaTabela() {
		modelo = new DefaultTableModel();
		tabela = new JTable(modelo);
		tabela.setBackground(Color.WHITE);
		modelo.addColumn("Number");
		modelo.addColumn("Commits");
		modelo.addColumn("Files Changed");
//		tabela.getColumnModel().getColumn(0)
//        .setPreferredWidth(2);
//        tabela.getColumnModel().getColumn(1)
//        .setPreferredWidth(2);
//        tabela.getColumnModel().getColumn(2)
//        .setPreferredWidth(2);
        load();
	}
	
	private void load() {
		List<PullRequest> lista = new ArrayList<PullRequest>();
		PullRequest pull;
		
		for(int i=0; i<100; i++) {
			pull = new PullRequest();
			pull.setNumber(i);
			pull.setCommits(i+1);
			pull.setChanged_files(i+1);
			
			lista.add(pull);
		}
		
		modelo.setNumRows(0);
		
		for(PullRequest p: lista)
			modelo.addRow(new Object[] {p.getNumber(), p.getCommits(), p.getChanged_files()});
		
	}
	
	
	public static void main(String[] args) {
		TesteTabela tt = new TesteTabela();
	}
}
