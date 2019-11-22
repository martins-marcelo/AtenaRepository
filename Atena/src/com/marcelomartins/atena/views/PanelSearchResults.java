package com.marcelomartins.atena.views;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.marcelomartins.atena.domain.PullRequest;
import com.marcelomartins.atena.domain.repository.PullRequestRepository;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.MouseAdapter;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class PanelSearchResults extends JFrame{

	private List<PullRequest> lstPulls;

	private JPanel pCenter, pNorth, pInfo;

	private JLabel lbTit, lbLink, lbExit;

	private JTable table;

	private DefaultTableModel model;

	private JScrollPane scrollPane;

	private JButton btCSV;
	
	private String link;

	public PanelSearchResults(String link, List<PullRequest> lstPulls) {

		this.link = link;
		this.lstPulls = lstPulls;

		addPanel();
		
		System.out.println(lstPulls.size()+" resultados retornado do repositório: "+link);
		
		setTitle("Atena Search");
		setSize(850, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}	

	private void buildPanel() {

		pCenter = new JPanel();
		pCenter.setBounds(0, 45, 850, 430);
		pCenter.setBackground(Color.WHITE);
		
		pNorth = new JPanel();
		pNorth.setBounds(0, 0, 850, 44);
		
		btCSV = new JButton("Convert to CSV");
		btCSV.setFont(new Font("DejaVu Sans", Font.BOLD, 5));
		btCSV.setBounds(736, 176, 114, 101);
		pCenter.add(btCSV);

		addLabel();
		addTable();
	}

	private void addPanel() {
		buildPanel();
		getContentPane().setLayout(null);
		getContentPane().add(pCenter);
		
		getContentPane().add(pNorth);
	}

	private void buildLabel() {
		lbTit = new JLabel("ATENA");
		lbTit.setHorizontalAlignment(SwingConstants.LEFT);
		lbTit.setFont(new Font("DejaVu Sans", Font.BOLD, 20));
		lbTit.setBounds(12, 12, 270, 32);

		lbLink = new JLabel(link);
		lbLink.setHorizontalAlignment(SwingConstants.CENTER);
		lbLink.setFont(new Font("DejaVu Sans", Font.BOLD, 20));
		lbLink.setBounds(165, 12, 520, 32);

		lbExit = new JLabel("Exit");
		lbExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//VOLTAR PARA A JANELA ANTERIOR
				dispose();
				PanelSearch ps = new PanelSearch();
				ps.setVisible(true);
			}
		});
		lbExit.setHorizontalAlignment(SwingConstants.RIGHT);
		lbExit.setFont(new Font("DejaVu Sans", Font.BOLD, 20));
		lbExit.setBounds(759, 12, 79, 32);
	}

	
	private void addLabel() {
		buildLabel();
		pCenter.setLayout(null);
		pNorth.setLayout(null);
		pNorth.add(lbTit);
		pNorth.add(lbLink);
		pNorth.add(lbExit);
	}
	

	private void buildTable() {
		model = new DefaultTableModel();
		table = new JTable(model);
		table.setFont(new Font("DejaVu Sans", Font.PLAIN, 12));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		model.addColumn("Number");
		model.addColumn("Commits");
		model.addColumn("Changed Files");
		model.addColumn("Changed Lines");
		model.addColumn("Lifetime Type");
		model.addColumn("Lifetime");
		model.addColumn("Merged by");
		model.addColumn("Merged");
		model.addColumn("Comments");
		
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		table.getColumnModel().getColumn(3).setPreferredWidth(120);
		table.getColumnModel().getColumn(4).setPreferredWidth(120);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		table.getColumnModel().getColumn(8).setPreferredWidth(100);
		
		loadData();

		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(22, 22, 716, 385);
	}

	private void loadData() {
		model.setNumRows(0);

		for(PullRequest pull: lstPulls) 
			model.addRow(new Object[] {pull.getNumber(), pull.getCommits(),
					pull.getChanged_files(), pull.getChanged_lines(),
					pull.getLifetimeType(), pull.getLifetime(),
					pull.getMerged_by(), pull.isMerged(), pull.getComments()});
	}
	

	private void addTable() {
		buildTable();
		pCenter.add(scrollPane);
	}
	

	public static void main(String[] args) throws IOException {
		List<PullRequest> lista = new ArrayList<PullRequest>();
		PullRequest pull;

		for(int i=0; i<100; i++) {
			pull = new PullRequest();
			pull.setNumber(i);
			pull.setCommits(i+1);
			pull.setChanged_files(i+2);


			lista.add(pull);
		}
		
		PanelSearchResults psr = new PanelSearchResults("Teste", lista);
	}
}
